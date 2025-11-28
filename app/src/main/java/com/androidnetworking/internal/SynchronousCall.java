package com.androidnetworking.internal;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.ResponseType;
import com.androidnetworking.error.ANError;
import com.androidnetworking.utils.SourceCloseUtil;
import com.androidnetworking.utils.Utils;
import okhttp3.Response;

/* loaded from: classes.dex */
public final class SynchronousCall {
    private SynchronousCall() {
    }

    public static <T> ANResponse<T> execute(ANRequest aNRequest) {
        int requestType = aNRequest.getRequestType();
        if (requestType == 0) {
            return executeSimpleRequest(aNRequest);
        }
        if (requestType == 1) {
            return executeDownloadRequest(aNRequest);
        }
        if (requestType == 2) {
            return executeUploadRequest(aNRequest);
        }
        return new ANResponse<>(new ANError());
    }

    private static <T> ANResponse<T> executeSimpleRequest(ANRequest aNRequest) {
        try {
            try {
                Response responsePerformSimpleRequest = InternalNetworking.performSimpleRequest(aNRequest);
                if (responsePerformSimpleRequest == null) {
                    ANResponse<T> aNResponse = new ANResponse<>(Utils.getErrorForConnection(new ANError()));
                    SourceCloseUtil.close(responsePerformSimpleRequest, aNRequest);
                    return aNResponse;
                }
                if (aNRequest.getResponseAs() == ResponseType.OK_HTTP_RESPONSE) {
                    ANResponse<T> aNResponse2 = new ANResponse<>(responsePerformSimpleRequest);
                    aNResponse2.setOkHttpResponse(responsePerformSimpleRequest);
                    SourceCloseUtil.close(responsePerformSimpleRequest, aNRequest);
                    return aNResponse2;
                }
                if (responsePerformSimpleRequest.code() >= 400) {
                    ANResponse<T> aNResponse3 = new ANResponse<>(Utils.getErrorForServerResponse(new ANError(responsePerformSimpleRequest), aNRequest, responsePerformSimpleRequest.code()));
                    aNResponse3.setOkHttpResponse(responsePerformSimpleRequest);
                    SourceCloseUtil.close(responsePerformSimpleRequest, aNRequest);
                    return aNResponse3;
                }
                ANResponse<T> response = aNRequest.parseResponse(responsePerformSimpleRequest);
                response.setOkHttpResponse(responsePerformSimpleRequest);
                SourceCloseUtil.close(responsePerformSimpleRequest, aNRequest);
                return response;
            } catch (ANError e) {
                ANResponse<T> aNResponse4 = new ANResponse<>(Utils.getErrorForConnection(new ANError(e)));
                SourceCloseUtil.close(null, aNRequest);
                return aNResponse4;
            } catch (Exception e2) {
                ANResponse<T> aNResponse5 = new ANResponse<>(Utils.getErrorForConnection(new ANError(e2)));
                SourceCloseUtil.close(null, aNRequest);
                return aNResponse5;
            }
        } catch (Throwable th) {
            SourceCloseUtil.close(null, aNRequest);
            throw th;
        }
    }

    private static <T> ANResponse<T> executeDownloadRequest(ANRequest aNRequest) {
        try {
            Response responsePerformDownloadRequest = InternalNetworking.performDownloadRequest(aNRequest);
            if (responsePerformDownloadRequest == null) {
                return new ANResponse<>(Utils.getErrorForConnection(new ANError()));
            }
            if (responsePerformDownloadRequest.code() >= 400) {
                ANResponse<T> aNResponse = new ANResponse<>(Utils.getErrorForServerResponse(new ANError(responsePerformDownloadRequest), aNRequest, responsePerformDownloadRequest.code()));
                aNResponse.setOkHttpResponse(responsePerformDownloadRequest);
                return aNResponse;
            }
            ANResponse<T> aNResponse2 = new ANResponse<>("success");
            aNResponse2.setOkHttpResponse(responsePerformDownloadRequest);
            return aNResponse2;
        } catch (ANError e) {
            return new ANResponse<>(Utils.getErrorForConnection(new ANError(e)));
        } catch (Exception e2) {
            return new ANResponse<>(Utils.getErrorForConnection(new ANError(e2)));
        }
    }

    private static <T> ANResponse<T> executeUploadRequest(ANRequest aNRequest) {
        try {
            try {
                Response responsePerformUploadRequest = InternalNetworking.performUploadRequest(aNRequest);
                if (responsePerformUploadRequest == null) {
                    ANResponse<T> aNResponse = new ANResponse<>(Utils.getErrorForConnection(new ANError()));
                    SourceCloseUtil.close(responsePerformUploadRequest, aNRequest);
                    return aNResponse;
                }
                if (aNRequest.getResponseAs() == ResponseType.OK_HTTP_RESPONSE) {
                    ANResponse<T> aNResponse2 = new ANResponse<>(responsePerformUploadRequest);
                    aNResponse2.setOkHttpResponse(responsePerformUploadRequest);
                    SourceCloseUtil.close(responsePerformUploadRequest, aNRequest);
                    return aNResponse2;
                }
                if (responsePerformUploadRequest.code() >= 400) {
                    ANResponse<T> aNResponse3 = new ANResponse<>(Utils.getErrorForServerResponse(new ANError(responsePerformUploadRequest), aNRequest, responsePerformUploadRequest.code()));
                    aNResponse3.setOkHttpResponse(responsePerformUploadRequest);
                    SourceCloseUtil.close(responsePerformUploadRequest, aNRequest);
                    return aNResponse3;
                }
                ANResponse<T> response = aNRequest.parseResponse(responsePerformUploadRequest);
                response.setOkHttpResponse(responsePerformUploadRequest);
                SourceCloseUtil.close(responsePerformUploadRequest, aNRequest);
                return response;
            } catch (ANError e) {
                ANResponse<T> aNResponse4 = new ANResponse<>(Utils.getErrorForConnection(e));
                SourceCloseUtil.close(null, aNRequest);
                return aNResponse4;
            } catch (Exception e2) {
                ANResponse<T> aNResponse5 = new ANResponse<>(Utils.getErrorForConnection(new ANError(e2)));
                SourceCloseUtil.close(null, aNRequest);
                return aNResponse5;
            }
        } catch (Throwable th) {
            SourceCloseUtil.close(null, aNRequest);
            throw th;
        }
    }
}