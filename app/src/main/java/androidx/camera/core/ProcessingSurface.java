package androidx.camera.core;

import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.SingleImageProxyBundle;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
final class ProcessingSurface extends DeferrableSurface {
    private static final int MAX_IMAGES = 2;
    private static final String TAG = "ProcessingSurfaceTextur";
    private final CameraCaptureCallback mCameraCaptureCallback;
    final CaptureProcessor mCaptureProcessor;
    final CaptureStage mCaptureStage;
    private final Handler mImageReaderHandler;
    final MetadataImageReader mInputImageReader;
    final Surface mInputSurface;
    final Object mLock;
    private final DeferrableSurface mOutputDeferrableSurface;
    boolean mReleased;
    private final Size mResolution;
    private String mTagBundleKey;
    private final ImageReaderProxy.OnImageAvailableListener mTransformedListener;

    public /* synthetic */ void lambda$new$0$ProcessingSurface(ImageReaderProxy imageReaderProxy) {
        synchronized (this.mLock) {
            imageIncoming(imageReaderProxy);
        }
    }

    ProcessingSurface(int i, int i2, int i3, Handler handler, CaptureStage captureStage, CaptureProcessor captureProcessor, DeferrableSurface deferrableSurface, String str) {
        super(new Size(i, i2), i3);
        this.mLock = new Object();
        ImageReaderProxy.OnImageAvailableListener onImageAvailableListener = new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.-$$Lambda$ProcessingSurface$S_kJ_hzSyay6pbMlqHEH79wEJ28
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                this.f$0.lambda$new$0$ProcessingSurface(imageReaderProxy);
            }
        };
        this.mTransformedListener = onImageAvailableListener;
        this.mReleased = false;
        Size size = new Size(i, i2);
        this.mResolution = size;
        if (handler != null) {
            this.mImageReaderHandler = handler;
        } else {
            Looper looperMyLooper = Looper.myLooper();
            if (looperMyLooper == null) {
                throw new IllegalStateException("Creating a ProcessingSurface requires a non-null Handler, or be created  on a thread with a Looper.");
            }
            this.mImageReaderHandler = new Handler(looperMyLooper);
        }
        ScheduledExecutorService scheduledExecutorServiceNewHandlerExecutor = CameraXExecutors.newHandlerExecutor(this.mImageReaderHandler);
        MetadataImageReader metadataImageReader = new MetadataImageReader(i, i2, i3, 2);
        this.mInputImageReader = metadataImageReader;
        metadataImageReader.setOnImageAvailableListener(onImageAvailableListener, scheduledExecutorServiceNewHandlerExecutor);
        this.mInputSurface = metadataImageReader.getSurface();
        this.mCameraCaptureCallback = metadataImageReader.getCameraCaptureCallback();
        this.mCaptureProcessor = captureProcessor;
        captureProcessor.onResolutionUpdate(size);
        this.mCaptureStage = captureStage;
        this.mOutputDeferrableSurface = deferrableSurface;
        this.mTagBundleKey = str;
        Futures.addCallback(deferrableSurface.getSurface(), new FutureCallback<Surface>() { // from class: androidx.camera.core.ProcessingSurface.1
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(Surface surface) {
                synchronized (ProcessingSurface.this.mLock) {
                    ProcessingSurface.this.mCaptureProcessor.onOutputSurface(surface, 1);
                }
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
                Logger.e(ProcessingSurface.TAG, "Failed to extract Listenable<Surface>.", th);
            }
        }, CameraXExecutors.directExecutor());
        getTerminationFuture().addListener(new Runnable() { // from class: androidx.camera.core.-$$Lambda$ProcessingSurface$18LRdxYesVYyVxVloEQKOtEg0ww
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.release();
            }
        }, CameraXExecutors.directExecutor());
    }

    @Override // androidx.camera.core.impl.DeferrableSurface
    public ListenableFuture<Surface> provideSurface() {
        ListenableFuture<Surface> listenableFutureImmediateFuture;
        synchronized (this.mLock) {
            listenableFutureImmediateFuture = Futures.immediateFuture(this.mInputSurface);
        }
        return listenableFutureImmediateFuture;
    }

    CameraCaptureCallback getCameraCaptureCallback() {
        CameraCaptureCallback cameraCaptureCallback;
        synchronized (this.mLock) {
            if (this.mReleased) {
                throw new IllegalStateException("ProcessingSurface already released!");
            }
            cameraCaptureCallback = this.mCameraCaptureCallback;
        }
        return cameraCaptureCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        synchronized (this.mLock) {
            if (this.mReleased) {
                return;
            }
            this.mInputImageReader.close();
            this.mInputSurface.release();
            this.mOutputDeferrableSurface.close();
            this.mReleased = true;
        }
    }

    void imageIncoming(ImageReaderProxy imageReaderProxy) {
        if (this.mReleased) {
            return;
        }
        ImageProxy imageProxyAcquireNextImage = null;
        try {
            imageProxyAcquireNextImage = imageReaderProxy.acquireNextImage();
        } catch (IllegalStateException e) {
            Logger.e(TAG, "Failed to acquire next image.", e);
        }
        if (imageProxyAcquireNextImage == null) {
            return;
        }
        ImageInfo imageInfo = imageProxyAcquireNextImage.getImageInfo();
        if (imageInfo == null) {
            imageProxyAcquireNextImage.close();
            return;
        }
        Integer num = (Integer) imageInfo.getTagBundle().getTag(this.mTagBundleKey);
        if (num == null) {
            imageProxyAcquireNextImage.close();
            return;
        }
        if (this.mCaptureStage.getId() != num.intValue()) {
            Logger.w(TAG, "ImageProxyBundle does not contain this id: " + num);
            imageProxyAcquireNextImage.close();
            return;
        }
        SingleImageProxyBundle singleImageProxyBundle = new SingleImageProxyBundle(imageProxyAcquireNextImage, this.mTagBundleKey);
        this.mCaptureProcessor.process(singleImageProxyBundle);
        singleImageProxyBundle.close();
    }
}
