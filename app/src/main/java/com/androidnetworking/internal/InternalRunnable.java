package com.androidnetworking.internal;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.common.ResponseType;
import com.androidnetworking.core.Core;
import com.androidnetworking.error.ANError;
import com.androidnetworking.utils.SourceCloseUtil;
import com.androidnetworking.utils.Utils;
import okhttp3.Response;

/* loaded from: classes.dex */
public class InternalRunnable implements Runnable {
    private final Priority priority;
    public final ANRequest request;
    public final int sequence;

    public InternalRunnable(ANRequest aNRequest) {
        this.request = aNRequest;
        this.sequence = aNRequest.getSequenceNumber();
        this.priority = aNRequest.getPriority();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.request.setRunning(true);
        int requestType = this.request.getRequestType();
        if (requestType == 0) {
            executeSimpleRequest();
        } else if (requestType == 1) {
            executeDownloadRequest();
        } else if (requestType == 2) {
            executeUploadRequest();
        }
        this.request.setRunning(false);
    }

    private void executeSimpleRequest() {
        Response responsePerformSimpleRequest = null;
        try {
            try {
                responsePerformSimpleRequest = InternalNetworking.performSimpleRequest(this.request);
            } catch (Exception e) {
                deliverError(this.request, Utils.getErrorForConnection(new ANError(e)));
            }
            if (responsePerformSimpleRequest == null) {
                deliverError(this.request, Utils.getErrorForConnection(new ANError()));
            } else if (this.request.getResponseAs() == ResponseType.OK_HTTP_RESPONSE) {
                this.request.deliverOkHttpResponse(responsePerformSimpleRequest);
            } else if (responsePerformSimpleRequest.code() >= 400) {
                deliverError(this.request, Utils.getErrorForServerResponse(new ANError(responsePerformSimpleRequest), this.request, responsePerformSimpleRequest.code()));
            } else {
                ANResponse response = this.request.parseResponse(responsePerformSimpleRequest);
                if (!response.isSuccess()) {
                    deliverError(this.request, response.getError());
                } else {
                    response.setOkHttpResponse(responsePerformSimpleRequest);
                    this.request.deliverResponse(response);
                }
            }
        } finally {
            SourceCloseUtil.close(null, this.request);
        }
    }

    private void executeDownloadRequest() {
        try {
            Response responsePerformDownloadRequest = InternalNetworking.performDownloadRequest(this.request);
            if (responsePerformDownloadRequest == null) {
                deliverError(this.request, Utils.getErrorForConnection(new ANError()));
            } else if (responsePerformDownloadRequest.code() >= 400) {
                deliverError(this.request, Utils.getErrorForServerResponse(new ANError(responsePerformDownloadRequest), this.request, responsePerformDownloadRequest.code()));
            } else {
                this.request.updateDownloadCompletion();
            }
        } catch (Exception e) {
            deliverError(this.request, Utils.getErrorForConnection(new ANError(e)));
        }
    }

    private void executeUploadRequest() {
        Response responsePerformUploadRequest = null;
        try {
            try {
                responsePerformUploadRequest = InternalNetworking.performUploadRequest(this.request);
            } catch (Exception e) {
                deliverError(this.request, Utils.getErrorForConnection(new ANError(e)));
            }
            if (responsePerformUploadRequest == null) {
                deliverError(this.request, Utils.getErrorForConnection(new ANError()));
            } else if (this.request.getResponseAs() == ResponseType.OK_HTTP_RESPONSE) {
                this.request.deliverOkHttpResponse(responsePerformUploadRequest);
            } else if (responsePerformUploadRequest.code() >= 400) {
                deliverError(this.request, Utils.getErrorForServerResponse(new ANError(responsePerformUploadRequest), this.request, responsePerformUploadRequest.code()));
            } else {
                ANResponse response = this.request.parseResponse(responsePerformUploadRequest);
                if (!response.isSuccess()) {
                    deliverError(this.request, response.getError());
                } else {
                    response.setOkHttpResponse(responsePerformUploadRequest);
                    this.request.deliverResponse(response);
                }
            }
        } finally {
            SourceCloseUtil.close(null, this.request);
        }
    }

    public Priority getPriority() {
        return this.priority;
    }

    private void deliverError(final ANRequest aNRequest, final ANError aNError) {
        Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() { // from class: com.androidnetworking.internal.InternalRunnable.1
            @Override // java.lang.Runnable
            public void run() {
                aNRequest.deliverError(aNError);
                aNRequest.finish();
            }
        });
    }
}
