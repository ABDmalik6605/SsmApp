package com.androidnetworking.interceptors;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/* loaded from: classes.dex */
public class GzipRequestInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (request.body() == null || request.header(HttpHeaders.CONTENT_ENCODING) != null) {
            return chain.proceed(request);
        }
        return chain.proceed(request.newBuilder().header(HttpHeaders.CONTENT_ENCODING, "gzip").method(request.method(), forceContentLength(gzip(request.body()))).build());
    }

    private RequestBody forceContentLength(final RequestBody requestBody) throws IOException {
        final Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return new RequestBody() { // from class: com.androidnetworking.interceptors.GzipRequestInterceptor.1
            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return requestBody.contentType();
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                return buffer.size();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(buffer.snapshot());
            }
        };
    }

    private RequestBody gzip(final RequestBody requestBody) {
        return new RequestBody() { // from class: com.androidnetworking.interceptors.GzipRequestInterceptor.2
            @Override // okhttp3.RequestBody
            public long contentLength() {
                return -1L;
            }

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return requestBody.contentType();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                BufferedSink bufferedSinkBuffer = Okio.buffer(new GzipSink(bufferedSink));
                requestBody.writeTo(bufferedSinkBuffer);
                bufferedSinkBuffer.close();
            }
        };
    }
}
