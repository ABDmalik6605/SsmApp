package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
class SynchronizedCaptureSessionImpl extends SynchronizedCaptureSessionBaseImpl {
    private static final String TAG = "SyncCaptureSessionImpl";
    private final CameraCaptureSession.CaptureCallback mCaptureCallback;
    private List<DeferrableSurface> mDeferrableSurfaces;
    private final Set<String> mEnabledFeature;
    private boolean mHasSubmittedRepeating;
    private final Object mObjectLock;
    ListenableFuture<Void> mOpeningCaptureSession;
    CallbackToFutureAdapter.Completer<Void> mStartStreamingCompleter;
    private final ListenableFuture<Void> mStartStreamingFuture;
    ListenableFuture<List<Surface>> mStartingSurface;

    SynchronizedCaptureSessionImpl(Set<String> set, CaptureSessionRepository captureSessionRepository, Executor executor, ScheduledExecutorService scheduledExecutorService, Handler handler) {
        super(captureSessionRepository, executor, scheduledExecutorService, handler);
        this.mObjectLock = new Object();
        this.mCaptureCallback = new CameraCaptureSession.CaptureCallback() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionImpl.1
            @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
            public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
                if (SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter != null) {
                    SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter.set(null);
                    SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter = null;
                }
            }

            @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
            public void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
                if (SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter != null) {
                    SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter.setCancelled();
                    SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter = null;
                }
            }
        };
        this.mEnabledFeature = set;
        if (set.contains("wait_for_request")) {
            this.mStartStreamingFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.-$$Lambda$SynchronizedCaptureSessionImpl$umCoUJuiVefiG9UHR1PsJPQCUIY
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    return this.f$0.lambda$new$0$SynchronizedCaptureSessionImpl(completer);
                }
            });
        } else {
            this.mStartStreamingFuture = Futures.immediateFuture(null);
        }
    }

    public /* synthetic */ Object lambda$new$0$SynchronizedCaptureSessionImpl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mStartStreamingCompleter = completer;
        return "StartStreamingFuture[session=" + this + "]";
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public ListenableFuture<Void> openCaptureSession(final CameraDevice cameraDevice, final SessionConfigurationCompat sessionConfigurationCompat, final List<DeferrableSurface> list) {
        ListenableFuture<Void> listenableFutureNonCancellationPropagating;
        synchronized (this.mObjectLock) {
            FutureChain futureChainTransformAsync = FutureChain.from(Futures.successfulAsList(getBlockerFuture("wait_for_request", this.mCaptureSessionRepository.getClosingCaptureSession()))).transformAsync(new AsyncFunction() { // from class: androidx.camera.camera2.internal.-$$Lambda$SynchronizedCaptureSessionImpl$9uHDKo003-ogBhFfZsG8L3LeluA
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    return this.f$0.lambda$openCaptureSession$1$SynchronizedCaptureSessionImpl(cameraDevice, sessionConfigurationCompat, list, (List) obj);
                }
            }, CameraXExecutors.directExecutor());
            this.mOpeningCaptureSession = futureChainTransformAsync;
            listenableFutureNonCancellationPropagating = Futures.nonCancellationPropagating(futureChainTransformAsync);
        }
        return listenableFutureNonCancellationPropagating;
    }

    public /* synthetic */ ListenableFuture lambda$openCaptureSession$1$SynchronizedCaptureSessionImpl(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List list, List list2) throws Exception {
        return super.openCaptureSession(cameraDevice, sessionConfigurationCompat, list);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession
    public ListenableFuture<Void> getSynchronizedBlocker(String str) {
        str.hashCode();
        if (str.equals("wait_for_request")) {
            return Futures.nonCancellationPropagating(this.mStartStreamingFuture);
        }
        return super.getSynchronizedBlocker(str);
    }

    private List<ListenableFuture<Void>> getBlockerFuture(String str, List<SynchronizedCaptureSession> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<SynchronizedCaptureSession> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getSynchronizedBlocker(str));
        }
        return arrayList;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public ListenableFuture<List<Surface>> startWithDeferrableSurface(List<DeferrableSurface> list, long j) {
        ListenableFuture<List<Surface>> listenableFutureNonCancellationPropagating;
        synchronized (this.mObjectLock) {
            this.mDeferrableSurfaces = list;
            listenableFutureNonCancellationPropagating = Futures.nonCancellationPropagating(super.startWithDeferrableSurface(list, j));
        }
        return listenableFutureNonCancellationPropagating;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public boolean stop() {
        boolean zStop;
        synchronized (this.mObjectLock) {
            if (isCameraCaptureSessionOpen()) {
                closeConfiguredDeferrableSurfaces();
            } else {
                ListenableFuture<Void> listenableFuture = this.mOpeningCaptureSession;
                if (listenableFuture != null) {
                    listenableFuture.cancel(true);
                }
                ListenableFuture<List<Surface>> listenableFuture2 = this.mStartingSurface;
                if (listenableFuture2 != null) {
                    listenableFuture2.cancel(true);
                }
            }
            zStop = super.stop();
        }
        return zStop;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int setSingleRepeatingRequest(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        int singleRepeatingRequest;
        if (this.mEnabledFeature.contains("wait_for_request")) {
            synchronized (this.mObjectLock) {
                this.mHasSubmittedRepeating = true;
                singleRepeatingRequest = super.setSingleRepeatingRequest(captureRequest, Camera2CaptureCallbacks.createComboCallback(this.mCaptureCallback, captureCallback));
            }
            return singleRepeatingRequest;
        }
        return super.setSingleRepeatingRequest(captureRequest, captureCallback);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onConfigured(SynchronizedCaptureSession synchronizedCaptureSession) {
        SynchronizedCaptureSession next;
        SynchronizedCaptureSession next2;
        debugLog("Session onConfigured()");
        if (this.mEnabledFeature.contains("force_close")) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<SynchronizedCaptureSession> it = this.mCaptureSessionRepository.getCreatingCaptureSessions().iterator();
            while (it.hasNext() && (next2 = it.next()) != synchronizedCaptureSession) {
                linkedHashSet.add(next2);
            }
            forceOnConfigureFailed(linkedHashSet);
        }
        super.onConfigured(synchronizedCaptureSession);
        if (this.mEnabledFeature.contains("force_close")) {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            Iterator<SynchronizedCaptureSession> it2 = this.mCaptureSessionRepository.getCaptureSessions().iterator();
            while (it2.hasNext() && (next = it2.next()) != synchronizedCaptureSession) {
                linkedHashSet2.add(next);
            }
            forceOnClosed(linkedHashSet2);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void close() {
        debugLog("Session call close()");
        if (this.mEnabledFeature.contains("wait_for_request")) {
            synchronized (this.mObjectLock) {
                if (!this.mHasSubmittedRepeating) {
                    this.mStartStreamingFuture.cancel(true);
                }
            }
        }
        this.mStartStreamingFuture.addListener(new Runnable() { // from class: androidx.camera.camera2.internal.-$$Lambda$SynchronizedCaptureSessionImpl$TZfsXkvCuLmwT2CKOCg6WDNcs00
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$close$2$SynchronizedCaptureSessionImpl();
            }
        }, getExecutor());
    }

    public /* synthetic */ void lambda$close$2$SynchronizedCaptureSessionImpl() {
        debugLog("Session call super.close()");
        super.close();
    }

    void closeConfiguredDeferrableSurfaces() {
        synchronized (this.mObjectLock) {
            if (this.mDeferrableSurfaces == null) {
                debugLog("deferrableSurface == null, maybe forceClose, skip close");
                return;
            }
            if (this.mEnabledFeature.contains("deferrableSurface_close")) {
                Iterator<DeferrableSurface> it = this.mDeferrableSurfaces.iterator();
                while (it.hasNext()) {
                    it.next().close();
                }
                debugLog("deferrableSurface closed");
            }
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onClosed(SynchronizedCaptureSession synchronizedCaptureSession) {
        closeConfiguredDeferrableSurfaces();
        debugLog("onClosed()");
        super.onClosed(synchronizedCaptureSession);
    }

    static void forceOnClosed(Set<SynchronizedCaptureSession> set) {
        for (SynchronizedCaptureSession synchronizedCaptureSession : set) {
            synchronizedCaptureSession.getStateCallback().onClosed(synchronizedCaptureSession);
        }
    }

    private void forceOnConfigureFailed(Set<SynchronizedCaptureSession> set) {
        for (SynchronizedCaptureSession synchronizedCaptureSession : set) {
            synchronizedCaptureSession.getStateCallback().onConfigureFailed(synchronizedCaptureSession);
        }
    }

    void debugLog(String str) {
        Logger.d(TAG, "[" + this + "] " + str);
    }
}
