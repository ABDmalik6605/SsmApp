package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class SurfaceRequest {
    private final CameraInternal mCamera;
    private final DeferrableSurface mInternalDeferrableSurface;
    private final boolean mRGBA8888Required;
    private final CallbackToFutureAdapter.Completer<Void> mRequestCancellationCompleter;
    private final Size mResolution;
    private final ListenableFuture<Void> mSessionStatusFuture;
    private final CallbackToFutureAdapter.Completer<Surface> mSurfaceCompleter;
    final ListenableFuture<Surface> mSurfaceFuture;
    private TransformationInfo mTransformationInfo;
    private Executor mTransformationInfoExecutor;
    private TransformationInfoListener mTransformationInfoListener;

    public interface TransformationInfoListener {
        void onTransformationInfoUpdate(TransformationInfo transformationInfo);
    }

    public SurfaceRequest(Size size, CameraInternal cameraInternal, boolean z) {
        this.mResolution = size;
        this.mCamera = cameraInternal;
        this.mRGBA8888Required = z;
        final String str = "SurfaceRequest[size: " + size + ", id: " + hashCode() + "]";
        final AtomicReference atomicReference = new AtomicReference(null);
        final ListenableFuture future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.-$$Lambda$SurfaceRequest$aBej7HDLh-OXtFkhhJqwnMya-w4
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return SurfaceRequest.lambda$new$0(atomicReference, str, completer);
            }
        });
        final CallbackToFutureAdapter.Completer<Void> completer = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
        this.mRequestCancellationCompleter = completer;
        final AtomicReference atomicReference2 = new AtomicReference(null);
        ListenableFuture<Void> future2 = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.-$$Lambda$SurfaceRequest$btaCbh-R1GUdl6ecPEzVyFFTQT0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer2) {
                return SurfaceRequest.lambda$new$1(atomicReference2, str, completer2);
            }
        });
        this.mSessionStatusFuture = future2;
        Futures.addCallback(future2, new FutureCallback<Void>() { // from class: androidx.camera.core.SurfaceRequest.1
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(Void r2) {
                Preconditions.checkState(completer.set(null));
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
                if (th instanceof RequestCancelledException) {
                    Preconditions.checkState(future.cancel(false));
                } else {
                    Preconditions.checkState(completer.set(null));
                }
            }
        }, CameraXExecutors.directExecutor());
        final CallbackToFutureAdapter.Completer completer2 = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference2.get());
        final AtomicReference atomicReference3 = new AtomicReference(null);
        ListenableFuture<Surface> future3 = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.-$$Lambda$SurfaceRequest$WWsQ783Y628yCYG-TL8JC-KqsM4
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer3) {
                return SurfaceRequest.lambda$new$2(atomicReference3, str, completer3);
            }
        });
        this.mSurfaceFuture = future3;
        this.mSurfaceCompleter = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference3.get());
        DeferrableSurface deferrableSurface = new DeferrableSurface(size, 34) { // from class: androidx.camera.core.SurfaceRequest.2
            @Override // androidx.camera.core.impl.DeferrableSurface
            protected ListenableFuture<Surface> provideSurface() {
                return SurfaceRequest.this.mSurfaceFuture;
            }
        };
        this.mInternalDeferrableSurface = deferrableSurface;
        final ListenableFuture<Void> terminationFuture = deferrableSurface.getTerminationFuture();
        Futures.addCallback(future3, new FutureCallback<Surface>() { // from class: androidx.camera.core.SurfaceRequest.3
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(Surface surface) {
                Futures.propagate(terminationFuture, completer2);
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
                if (th instanceof CancellationException) {
                    Preconditions.checkState(completer2.setException(new RequestCancelledException(str + " cancelled.", th)));
                    return;
                }
                completer2.set(null);
            }
        }, CameraXExecutors.directExecutor());
        terminationFuture.addListener(new Runnable() { // from class: androidx.camera.core.-$$Lambda$SurfaceRequest$VEa_ntChyl08DUZIPJD7_G6fNI0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$3$SurfaceRequest();
            }
        }, CameraXExecutors.directExecutor());
    }

    static /* synthetic */ Object lambda$new$0(AtomicReference atomicReference, String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return str + "-cancellation";
    }

    static /* synthetic */ Object lambda$new$1(AtomicReference atomicReference, String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return str + "-status";
    }

    static /* synthetic */ Object lambda$new$2(AtomicReference atomicReference, String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return str + "-Surface";
    }

    public /* synthetic */ void lambda$new$3$SurfaceRequest() {
        this.mSurfaceFuture.cancel(true);
    }

    public DeferrableSurface getDeferrableSurface() {
        return this.mInternalDeferrableSurface;
    }

    public Size getResolution() {
        return this.mResolution;
    }

    public CameraInternal getCamera() {
        return this.mCamera;
    }

    public boolean isRGBA8888Required() {
        return this.mRGBA8888Required;
    }

    public void provideSurface(final Surface surface, Executor executor, final Consumer<Result> consumer) {
        if (this.mSurfaceCompleter.set(surface) || this.mSurfaceFuture.isCancelled()) {
            Futures.addCallback(this.mSessionStatusFuture, new FutureCallback<Void>() { // from class: androidx.camera.core.SurfaceRequest.4
                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onSuccess(Void r3) {
                    consumer.accept(Result.of(0, surface));
                }

                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onFailure(Throwable th) {
                    Preconditions.checkState(th instanceof RequestCancelledException, "Camera surface session should only fail with request cancellation. Instead failed due to:\n" + th);
                    consumer.accept(Result.of(1, surface));
                }
            }, executor);
            return;
        }
        Preconditions.checkState(this.mSurfaceFuture.isDone());
        try {
            this.mSurfaceFuture.get();
            executor.execute(new Runnable() { // from class: androidx.camera.core.-$$Lambda$SurfaceRequest$5-vkwtq5rrXTwajM8RYtCZdBIQk
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(SurfaceRequest.Result.of(3, surface));
                }
            });
        } catch (InterruptedException | ExecutionException unused) {
            executor.execute(new Runnable() { // from class: androidx.camera.core.-$$Lambda$SurfaceRequest$LxT2LRdxcQOvSLUIsyR5L3AElno
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(SurfaceRequest.Result.of(4, surface));
                }
            });
        }
    }

    public boolean willNotProvideSurface() {
        return this.mSurfaceCompleter.setException(new DeferrableSurface.SurfaceUnavailableException("Surface request will not complete."));
    }

    public void addRequestCancellationListener(Executor executor, Runnable runnable) {
        this.mRequestCancellationCompleter.addCancellationListener(runnable, executor);
    }

    public void updateTransformationInfo(final TransformationInfo transformationInfo) {
        this.mTransformationInfo = transformationInfo;
        final TransformationInfoListener transformationInfoListener = this.mTransformationInfoListener;
        if (transformationInfoListener != null) {
            this.mTransformationInfoExecutor.execute(new Runnable() { // from class: androidx.camera.core.-$$Lambda$SurfaceRequest$Sf8X4BruHcmAGAAbXPI3bP1O5Fs
                @Override // java.lang.Runnable
                public final void run() {
                    transformationInfoListener.onTransformationInfoUpdate(transformationInfo);
                }
            });
        }
    }

    public void setTransformationInfoListener(Executor executor, final TransformationInfoListener transformationInfoListener) {
        this.mTransformationInfoListener = transformationInfoListener;
        this.mTransformationInfoExecutor = executor;
        final TransformationInfo transformationInfo = this.mTransformationInfo;
        if (transformationInfo != null) {
            executor.execute(new Runnable() { // from class: androidx.camera.core.-$$Lambda$SurfaceRequest$Nm_zbwMMnlUvjaaP2PGr_Rt4pq4
                @Override // java.lang.Runnable
                public final void run() {
                    transformationInfoListener.onTransformationInfoUpdate(transformationInfo);
                }
            });
        }
    }

    public void clearTransformationInfoListener() {
        this.mTransformationInfoListener = null;
        this.mTransformationInfoExecutor = null;
    }

    private static final class RequestCancelledException extends RuntimeException {
        RequestCancelledException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static abstract class Result {
        public static final int RESULT_INVALID_SURFACE = 2;
        public static final int RESULT_REQUEST_CANCELLED = 1;
        public static final int RESULT_SURFACE_ALREADY_PROVIDED = 3;
        public static final int RESULT_SURFACE_USED_SUCCESSFULLY = 0;
        public static final int RESULT_WILL_NOT_PROVIDE_SURFACE = 4;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ResultCode {
        }

        public abstract int getResultCode();

        public abstract Surface getSurface();

        static Result of(int i, Surface surface) {
            return new AutoValue_SurfaceRequest_Result(i, surface);
        }

        Result() {
        }
    }

    public static abstract class TransformationInfo {
        public abstract Rect getCropRect();

        public abstract int getRotationDegrees();

        public abstract int getTargetRotation();

        public static TransformationInfo of(Rect rect, int i, int i2) {
            return new AutoValue_SurfaceRequest_TransformationInfo(rect, i, i2);
        }

        TransformationInfo() {
        }
    }
}
