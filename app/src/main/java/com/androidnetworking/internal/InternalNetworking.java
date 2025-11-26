package com.androidnetworking.internal;

import android.content.Context;
import android.net.TrafficStats;
import com.androidnetworking.common.ANConstants;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ConnectionClassManager;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.androidnetworking.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: classes.dex */
public final class InternalNetworking {
    public static OkHttpClient sHttpClient = getClient();
    public static String sUserAgent = null;

    private InternalNetworking() {
    }

    public static Response performSimpleRequest(ANRequest aNRequest) throws ANError {
        try {
            Request.Builder builderUrl = new Request.Builder().url(aNRequest.getUrl());
            addHeadersToRequestBuilder(builderUrl, aNRequest);
            RequestBody requestBody = null;
            switch (aNRequest.getMethod()) {
                case 0:
                    builderUrl = builderUrl.get();
                    break;
                case 1:
                    requestBody = aNRequest.getRequestBody();
                    builderUrl = builderUrl.post(requestBody);
                    break;
                case 2:
                    requestBody = aNRequest.getRequestBody();
                    builderUrl = builderUrl.put(requestBody);
                    break;
                case 3:
                    requestBody = aNRequest.getRequestBody();
                    builderUrl = builderUrl.delete(requestBody);
                    break;
                case 4:
                    builderUrl = builderUrl.head();
                    break;
                case 5:
                    requestBody = aNRequest.getRequestBody();
                    builderUrl = builderUrl.patch(requestBody);
                    break;
                case 6:
                    builderUrl = builderUrl.method(ANConstants.OPTIONS, null);
                    break;
            }
            if (aNRequest.getCacheControl() != null) {
                builderUrl.cacheControl(aNRequest.getCacheControl());
            }
            Request requestBuild = builderUrl.build();
            if (aNRequest.getOkHttpClient() != null) {
                aNRequest.setCall(aNRequest.getOkHttpClient().newBuilder().cache(sHttpClient.cache()).build().newCall(requestBuild));
            } else {
                aNRequest.setCall(sHttpClient.newCall(requestBuild));
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            long totalRxBytes = TrafficStats.getTotalRxBytes();
            Response responseExecute = aNRequest.getCall().execute();
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            long jContentLength = -1;
            if (responseExecute.cacheResponse() == null) {
                long totalRxBytes2 = TrafficStats.getTotalRxBytes();
                ConnectionClassManager.getInstance().updateBandwidth((totalRxBytes == -1 || totalRxBytes2 == -1) ? responseExecute.body().contentLength() : totalRxBytes2 - totalRxBytes, jCurrentTimeMillis2);
                AnalyticsListener analyticsListener = aNRequest.getAnalyticsListener();
                if (requestBody != null && requestBody.contentLength() != 0) {
                    jContentLength = requestBody.contentLength();
                }
                Utils.sendAnalytics(analyticsListener, jCurrentTimeMillis2, jContentLength, responseExecute.body().contentLength(), false);
            } else if (aNRequest.getAnalyticsListener() != null) {
                if (responseExecute.networkResponse() == null) {
                    Utils.sendAnalytics(aNRequest.getAnalyticsListener(), jCurrentTimeMillis2, 0L, 0L, true);
                } else {
                    AnalyticsListener analyticsListener2 = aNRequest.getAnalyticsListener();
                    if (requestBody != null && requestBody.contentLength() != 0) {
                        jContentLength = requestBody.contentLength();
                    }
                    Utils.sendAnalytics(analyticsListener2, jCurrentTimeMillis2, jContentLength, 0L, true);
                }
            }
            return responseExecute;
        } catch (IOException e) {
            throw new ANError(e);
        }
    }

    public static Response performDownloadRequest(final ANRequest aNRequest) throws ANError {
        OkHttpClient okHttpClientBuild;
        try {
            Request.Builder builderUrl = new Request.Builder().url(aNRequest.getUrl());
            addHeadersToRequestBuilder(builderUrl, aNRequest);
            Request.Builder builder = builderUrl.get();
            if (aNRequest.getCacheControl() != null) {
                builder.cacheControl(aNRequest.getCacheControl());
            }
            Request requestBuild = builder.build();
            if (aNRequest.getOkHttpClient() != null) {
                okHttpClientBuild = aNRequest.getOkHttpClient().newBuilder().cache(sHttpClient.cache()).addNetworkInterceptor(new Interceptor() { // from class: com.androidnetworking.internal.InternalNetworking.1
                    @Override // okhttp3.Interceptor
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Response responseProceed = chain.proceed(chain.request());
                        return responseProceed.newBuilder().body(new ResponseProgressBody(responseProceed.body(), aNRequest.getDownloadProgressListener())).build();
                    }
                }).build();
            } else {
                okHttpClientBuild = sHttpClient.newBuilder().addNetworkInterceptor(new Interceptor() { // from class: com.androidnetworking.internal.InternalNetworking.2
                    @Override // okhttp3.Interceptor
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Response responseProceed = chain.proceed(chain.request());
                        return responseProceed.newBuilder().body(new ResponseProgressBody(responseProceed.body(), aNRequest.getDownloadProgressListener())).build();
                    }
                }).build();
            }
            aNRequest.setCall(okHttpClientBuild.newCall(requestBuild));
            long jCurrentTimeMillis = System.currentTimeMillis();
            long totalRxBytes = TrafficStats.getTotalRxBytes();
            Response responseExecute = aNRequest.getCall().execute();
            Utils.saveFile(responseExecute, aNRequest.getDirPath(), aNRequest.getFileName());
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            if (responseExecute.cacheResponse() == null) {
                long totalRxBytes2 = TrafficStats.getTotalRxBytes();
                ConnectionClassManager.getInstance().updateBandwidth((totalRxBytes == -1 || totalRxBytes2 == -1) ? responseExecute.body().contentLength() : totalRxBytes2 - totalRxBytes, jCurrentTimeMillis2);
                Utils.sendAnalytics(aNRequest.getAnalyticsListener(), jCurrentTimeMillis2, -1L, responseExecute.body().contentLength(), false);
            } else if (aNRequest.getAnalyticsListener() != null) {
                Utils.sendAnalytics(aNRequest.getAnalyticsListener(), jCurrentTimeMillis2, -1L, 0L, true);
            }
            return responseExecute;
        } catch (IOException e) {
            try {
                File file = new File(aNRequest.getDirPath() + File.separator + aNRequest.getFileName());
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            throw new ANError(e);
        }
    }

    public static Response performUploadRequest(ANRequest aNRequest) throws ANError {
        try {
            Request.Builder builderUrl = new Request.Builder().url(aNRequest.getUrl());
            addHeadersToRequestBuilder(builderUrl, aNRequest);
            RequestBody multiPartRequestBody = aNRequest.getMultiPartRequestBody();
            long jContentLength = multiPartRequestBody.contentLength();
            Request.Builder builderPost = builderUrl.post(new RequestProgressBody(multiPartRequestBody, aNRequest.getUploadProgressListener()));
            if (aNRequest.getCacheControl() != null) {
                builderPost.cacheControl(aNRequest.getCacheControl());
            }
            Request requestBuild = builderPost.build();
            if (aNRequest.getOkHttpClient() != null) {
                aNRequest.setCall(aNRequest.getOkHttpClient().newBuilder().cache(sHttpClient.cache()).build().newCall(requestBuild));
            } else {
                aNRequest.setCall(sHttpClient.newCall(requestBuild));
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            Response responseExecute = aNRequest.getCall().execute();
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            if (aNRequest.getAnalyticsListener() != null) {
                if (responseExecute.cacheResponse() == null) {
                    Utils.sendAnalytics(aNRequest.getAnalyticsListener(), jCurrentTimeMillis2, jContentLength, responseExecute.body().contentLength(), false);
                } else if (responseExecute.networkResponse() == null) {
                    Utils.sendAnalytics(aNRequest.getAnalyticsListener(), jCurrentTimeMillis2, 0L, 0L, true);
                } else {
                    AnalyticsListener analyticsListener = aNRequest.getAnalyticsListener();
                    if (jContentLength == 0) {
                        jContentLength = -1;
                    }
                    Utils.sendAnalytics(analyticsListener, jCurrentTimeMillis2, jContentLength, 0L, true);
                }
            }
            return responseExecute;
        } catch (IOException e) {
            throw new ANError(e);
        }
    }

    public static OkHttpClient getClient() {
        OkHttpClient okHttpClient = sHttpClient;
        return okHttpClient == null ? getDefaultClient() : okHttpClient;
    }

    public static void addHeadersToRequestBuilder(Request.Builder builder, ANRequest aNRequest) {
        if (aNRequest.getUserAgent() != null) {
            builder.addHeader("User-Agent", aNRequest.getUserAgent());
        } else {
            String str = sUserAgent;
            if (str != null) {
                aNRequest.setUserAgent(str);
                builder.addHeader("User-Agent", sUserAgent);
            }
        }
        Headers headers = aNRequest.getHeaders();
        if (headers != null) {
            builder.headers(headers);
            if (aNRequest.getUserAgent() == null || headers.names().contains("User-Agent")) {
                return;
            }
            builder.addHeader("User-Agent", aNRequest.getUserAgent());
        }
    }

    public static OkHttpClient getDefaultClient() {
        return new OkHttpClient().newBuilder().connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).build();
    }

    public static void setClientWithCache(Context context) {
        sHttpClient = new OkHttpClient().newBuilder().cache(Utils.getCache(context, 10485760, ANConstants.CACHE_DIR_NAME)).connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).build();
    }

    public static void setUserAgent(String str) {
        sUserAgent = str;
    }

    public static void setClient(OkHttpClient okHttpClient) {
        sHttpClient = okHttpClient;
    }

    public static void enableLogging(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(level);
        sHttpClient = getClient().newBuilder().addInterceptor(httpLoggingInterceptor).build();
    }
}
