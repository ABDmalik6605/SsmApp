package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.ApiCompat;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class CameraCaptureSessionCompat {
    private final CameraCaptureSessionCompatImpl mImpl;

    interface CameraCaptureSessionCompatImpl {
        int captureBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        int captureSingleRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        int setRepeatingBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        int setSingleRepeatingRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        CameraCaptureSession unwrap();
    }

    private CameraCaptureSessionCompat(CameraCaptureSession cameraCaptureSession, Handler handler) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mImpl = new CameraCaptureSessionCompatApi28Impl(cameraCaptureSession);
        } else {
            this.mImpl = CameraCaptureSessionCompatBaseImpl.create(cameraCaptureSession, handler);
        }
    }

    public static CameraCaptureSessionCompat toCameraCaptureSessionCompat(CameraCaptureSession cameraCaptureSession) {
        return toCameraCaptureSessionCompat(cameraCaptureSession, MainThreadAsyncHandler.getInstance());
    }

    public static CameraCaptureSessionCompat toCameraCaptureSessionCompat(CameraCaptureSession cameraCaptureSession, Handler handler) {
        return new CameraCaptureSessionCompat(cameraCaptureSession, handler);
    }

    public CameraCaptureSession toCameraCaptureSession() {
        return this.mImpl.unwrap();
    }

    public int captureBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.mImpl.captureBurstRequests(list, executor, captureCallback);
    }

    public int captureSingleRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.mImpl.captureSingleRequest(captureRequest, executor, captureCallback);
    }

    public int setRepeatingBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.mImpl.setRepeatingBurstRequests(list, executor, captureCallback);
    }

    public int setSingleRepeatingRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.mImpl.setSingleRepeatingRequest(captureRequest, executor, captureCallback);
    }

    static final class CaptureCallbackExecutorWrapper extends CameraCaptureSession.CaptureCallback {
        private final Executor mExecutor;
        final CameraCaptureSession.CaptureCallback mWrappedCallback;

        CaptureCallbackExecutorWrapper(Executor executor, CameraCaptureSession.CaptureCallback captureCallback) {
            this.mExecutor = executor;
            this.mWrappedCallback = captureCallback;
        }

        public /* synthetic */ void lambda$onCaptureStarted$0$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
            this.mWrappedCallback.onCaptureStarted(cameraCaptureSession, captureRequest, j, j2);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final long j, final long j2) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$dr8EHUF49YJmGfdJGyaR37s9vtg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCaptureStarted$0$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(cameraCaptureSession, captureRequest, j, j2);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final CaptureResult captureResult) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$8-0Z5Cn4Iql9Is-vEUXPIkv3xdY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCaptureProgressed$1$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(cameraCaptureSession, captureRequest, captureResult);
                }
            });
        }

        public /* synthetic */ void lambda$onCaptureProgressed$1$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            this.mWrappedCallback.onCaptureProgressed(cameraCaptureSession, captureRequest, captureResult);
        }

        public /* synthetic */ void lambda$onCaptureCompleted$2$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            this.mWrappedCallback.onCaptureCompleted(cameraCaptureSession, captureRequest, totalCaptureResult);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$r0aItLDxVHCY4toYYLefJDa5NfQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCaptureCompleted$2$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(cameraCaptureSession, captureRequest, totalCaptureResult);
                }
            });
        }

        public /* synthetic */ void lambda$onCaptureFailed$3$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            this.mWrappedCallback.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final CaptureFailure captureFailure) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$xxSzEcTYNNa7_F-bi9IwLKtM1ZQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCaptureFailed$3$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(cameraCaptureSession, captureRequest, captureFailure);
                }
            });
        }

        public /* synthetic */ void lambda$onCaptureSequenceCompleted$4$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, int i, long j) {
            this.mWrappedCallback.onCaptureSequenceCompleted(cameraCaptureSession, i, j);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceCompleted(final CameraCaptureSession cameraCaptureSession, final int i, final long j) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$jfQRN_EhQYAlSBOKPK8gU2QuReM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCaptureSequenceCompleted$4$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(cameraCaptureSession, i, j);
                }
            });
        }

        public /* synthetic */ void lambda$onCaptureSequenceAborted$5$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, int i) {
            this.mWrappedCallback.onCaptureSequenceAborted(cameraCaptureSession, i);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(final CameraCaptureSession cameraCaptureSession, final int i) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$N1Qq0huh8lMP4YrP2sNIeLJQhPk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCaptureSequenceAborted$5$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(cameraCaptureSession, i);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureBufferLost(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final Surface surface, final long j) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper$5w3xUvF2QIdYirlE_QHUJQ3HRE8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCaptureBufferLost$6$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(cameraCaptureSession, captureRequest, surface, j);
                }
            });
        }

        public /* synthetic */ void lambda$onCaptureBufferLost$6$CameraCaptureSessionCompat$CaptureCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, Surface surface, long j) {
            ApiCompat.Api24Impl.onCaptureBufferLost(this.mWrappedCallback, cameraCaptureSession, captureRequest, surface, j);
        }
    }

    static final class StateCallbackExecutorWrapper extends CameraCaptureSession.StateCallback {
        private final Executor mExecutor;
        final CameraCaptureSession.StateCallback mWrappedCallback;

        StateCallbackExecutorWrapper(Executor executor, CameraCaptureSession.StateCallback stateCallback) {
            this.mExecutor = executor;
            this.mWrappedCallback = stateCallback;
        }

        public /* synthetic */ void lambda$onConfigured$0$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            this.mWrappedCallback.onConfigured(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(final CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$StateCallbackExecutorWrapper$_Z6ZHm0dE2kV2Wh_l2CVswU5OfU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onConfigured$0$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(cameraCaptureSession);
                }
            });
        }

        public /* synthetic */ void lambda$onConfigureFailed$1$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            this.mWrappedCallback.onConfigureFailed(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(final CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$StateCallbackExecutorWrapper$tVQplODqOmdNiXOqoDZsfzYUkmE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onConfigureFailed$1$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(cameraCaptureSession);
                }
            });
        }

        public /* synthetic */ void lambda$onReady$2$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            this.mWrappedCallback.onReady(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onReady(final CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$StateCallbackExecutorWrapper$wpYQr6P2zqTpXO3Z-ud9pp5oCYw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onReady$2$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(cameraCaptureSession);
                }
            });
        }

        public /* synthetic */ void lambda$onActive$3$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            this.mWrappedCallback.onActive(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onActive(final CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$StateCallbackExecutorWrapper$EWJFAtUQFAQfLXM2IsEgCsbWTqo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onActive$3$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(cameraCaptureSession);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onCaptureQueueEmpty(final CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$StateCallbackExecutorWrapper$SiS3j4-FGGNjmCwtw1ioFKPdYUw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCaptureQueueEmpty$4$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(cameraCaptureSession);
                }
            });
        }

        public /* synthetic */ void lambda$onCaptureQueueEmpty$4$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            ApiCompat.Api26Impl.onCaptureQueueEmpty(this.mWrappedCallback, cameraCaptureSession);
        }

        public /* synthetic */ void lambda$onClosed$5$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession) {
            this.mWrappedCallback.onClosed(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(final CameraCaptureSession cameraCaptureSession) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$StateCallbackExecutorWrapper$PPyLQ0ShgRHvXy6f4FI0coBCWJM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onClosed$5$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(cameraCaptureSession);
                }
            });
        }

        public /* synthetic */ void lambda$onSurfacePrepared$6$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(CameraCaptureSession cameraCaptureSession, Surface surface) {
            ApiCompat.Api23Impl.onSurfacePrepared(this.mWrappedCallback, cameraCaptureSession, surface);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onSurfacePrepared(final CameraCaptureSession cameraCaptureSession, final Surface surface) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.-$$Lambda$CameraCaptureSessionCompat$StateCallbackExecutorWrapper$AQ38-QXGcQc0UXCQkOunqRkK5dU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSurfacePrepared$6$CameraCaptureSessionCompat$StateCallbackExecutorWrapper(cameraCaptureSession, surface);
                }
            });
        }
    }
}
