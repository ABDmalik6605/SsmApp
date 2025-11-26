package androidx.camera.core;

import android.media.ImageReader;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.ImageProxyBundle;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes.dex */
class CaptureProcessorPipeline implements CaptureProcessor {
    private static final String TAG = "CaptureProcessorPipeline";
    final Executor mExecutor;
    private final int mMaxImages;
    private final CaptureProcessor mPostCaptureProcessor;
    private final CaptureProcessor mPreCaptureProcessor;
    private ImageReaderProxy mIntermediateImageReader = null;
    private ImageInfo mSourceImageInfo = null;

    CaptureProcessorPipeline(CaptureProcessor captureProcessor, int i, CaptureProcessor captureProcessor2, Executor executor) {
        this.mPreCaptureProcessor = captureProcessor;
        this.mPostCaptureProcessor = captureProcessor2;
        this.mExecutor = executor;
        this.mMaxImages = i;
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void onOutputSurface(Surface surface, int i) {
        this.mPostCaptureProcessor.onOutputSurface(surface, i);
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void process(ImageProxyBundle imageProxyBundle) {
        ListenableFuture<ImageProxy> imageProxy = imageProxyBundle.getImageProxy(imageProxyBundle.getCaptureIds().get(0).intValue());
        Preconditions.checkArgument(imageProxy.isDone());
        try {
            this.mSourceImageInfo = imageProxy.get().getImageInfo();
            this.mPreCaptureProcessor.process(imageProxyBundle);
        } catch (InterruptedException | ExecutionException unused) {
            throw new IllegalArgumentException("Can not successfully extract ImageProxy from the ImageProxyBundle.");
        }
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void onResolutionUpdate(Size size) {
        AndroidImageReaderProxy androidImageReaderProxy = new AndroidImageReaderProxy(ImageReader.newInstance(size.getWidth(), size.getHeight(), 35, this.mMaxImages));
        this.mIntermediateImageReader = androidImageReaderProxy;
        this.mPreCaptureProcessor.onOutputSurface(androidImageReaderProxy.getSurface(), 35);
        this.mPreCaptureProcessor.onResolutionUpdate(size);
        this.mPostCaptureProcessor.onResolutionUpdate(size);
        this.mIntermediateImageReader.setOnImageAvailableListener(new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.-$$Lambda$CaptureProcessorPipeline$A4InuV_Df8liM9KJsKAT0yaTpW8
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                this.f$0.lambda$onResolutionUpdate$1$CaptureProcessorPipeline(imageReaderProxy);
            }
        }, CameraXExecutors.directExecutor());
    }

    public /* synthetic */ void lambda$onResolutionUpdate$1$CaptureProcessorPipeline(ImageReaderProxy imageReaderProxy) {
        final ImageProxy imageProxyAcquireNextImage = imageReaderProxy.acquireNextImage();
        try {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.core.-$$Lambda$CaptureProcessorPipeline$aoCSRy3yzMQLOvSaktzXwtkldSA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onResolutionUpdate$0$CaptureProcessorPipeline(imageProxyAcquireNextImage);
                }
            });
        } catch (RejectedExecutionException unused) {
            Logger.e(TAG, "The executor for post-processing might have been shutting down or terminated!");
            imageProxyAcquireNextImage.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: postProcess, reason: merged with bridge method [inline-methods] */
    public void lambda$onResolutionUpdate$0$CaptureProcessorPipeline(ImageProxy imageProxy) {
        Size size = new Size(imageProxy.getWidth(), imageProxy.getHeight());
        Preconditions.checkNotNull(this.mSourceImageInfo);
        String next = this.mSourceImageInfo.getTagBundle().listKeys().iterator().next();
        int iIntValue = ((Integer) this.mSourceImageInfo.getTagBundle().getTag(next)).intValue();
        SettableImageProxy settableImageProxy = new SettableImageProxy(imageProxy, size, this.mSourceImageInfo);
        this.mSourceImageInfo = null;
        SettableImageProxyBundle settableImageProxyBundle = new SettableImageProxyBundle(Collections.singletonList(Integer.valueOf(iIntValue)), next);
        settableImageProxyBundle.addImageProxy(settableImageProxy);
        this.mPostCaptureProcessor.process(settableImageProxyBundle);
    }

    void close() {
        ImageReaderProxy imageReaderProxy = this.mIntermediateImageReader;
        if (imageReaderProxy != null) {
            imageReaderProxy.clearOnImageAvailableListener();
            this.mIntermediateImageReader.close();
        }
    }
}
