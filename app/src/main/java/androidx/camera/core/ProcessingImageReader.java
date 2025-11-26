package androidx.camera.core;

import android.media.ImageReader;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
class ProcessingImageReader implements ImageReaderProxy {
    private static final int EXIF_MAX_SIZE_BYTES = 64000;
    private static final String TAG = "ProcessingImageReader";
    final CaptureProcessor mCaptureProcessor;
    CallbackToFutureAdapter.Completer<Void> mCloseCompleter;
    private ListenableFuture<Void> mCloseFuture;
    Executor mExecutor;
    final MetadataImageReader mInputImageReader;
    ImageReaderProxy.OnImageAvailableListener mListener;
    final ImageReaderProxy mOutputImageReader;
    final Executor mPostProcessExecutor;
    final Object mLock = new Object();
    private ImageReaderProxy.OnImageAvailableListener mTransformedListener = new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.ProcessingImageReader.1
        @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
        public void onImageAvailable(ImageReaderProxy imageReaderProxy) {
            ProcessingImageReader.this.imageIncoming(imageReaderProxy);
        }
    };
    private ImageReaderProxy.OnImageAvailableListener mImageProcessedListener = new AnonymousClass2();
    private FutureCallback<List<ImageProxy>> mCaptureStageReadyCallback = new FutureCallback<List<ImageProxy>>() { // from class: androidx.camera.core.ProcessingImageReader.3
        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onSuccess(List<ImageProxy> list) {
            synchronized (ProcessingImageReader.this.mLock) {
                if (ProcessingImageReader.this.mClosed) {
                    return;
                }
                ProcessingImageReader.this.mProcessing = true;
                ProcessingImageReader.this.mCaptureProcessor.process(ProcessingImageReader.this.mSettableImageProxyBundle);
                synchronized (ProcessingImageReader.this.mLock) {
                    ProcessingImageReader.this.mProcessing = false;
                    if (ProcessingImageReader.this.mClosed) {
                        ProcessingImageReader.this.mInputImageReader.close();
                        ProcessingImageReader.this.mSettableImageProxyBundle.close();
                        ProcessingImageReader.this.mOutputImageReader.close();
                        if (ProcessingImageReader.this.mCloseCompleter != null) {
                            ProcessingImageReader.this.mCloseCompleter.set(null);
                        }
                    }
                }
            }
        }
    };
    boolean mClosed = false;
    boolean mProcessing = false;
    private String mTagBundleKey = new String();
    SettableImageProxyBundle mSettableImageProxyBundle = new SettableImageProxyBundle(Collections.emptyList(), this.mTagBundleKey);
    private final List<Integer> mCaptureIdList = new ArrayList();

    /* renamed from: androidx.camera.core.ProcessingImageReader$2, reason: invalid class name */
    class AnonymousClass2 implements ImageReaderProxy.OnImageAvailableListener {
        AnonymousClass2() {
        }

        @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
        public void onImageAvailable(ImageReaderProxy imageReaderProxy) {
            final ImageReaderProxy.OnImageAvailableListener onImageAvailableListener;
            Executor executor;
            synchronized (ProcessingImageReader.this.mLock) {
                onImageAvailableListener = ProcessingImageReader.this.mListener;
                executor = ProcessingImageReader.this.mExecutor;
                ProcessingImageReader.this.mSettableImageProxyBundle.reset();
                ProcessingImageReader.this.setupSettableImageProxyBundleCallbacks();
            }
            if (onImageAvailableListener != null) {
                if (executor != null) {
                    executor.execute(new Runnable() { // from class: androidx.camera.core.-$$Lambda$ProcessingImageReader$2$9BhRoTqwTfB3AroiolqV1-Oor0w
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onImageAvailable$0$ProcessingImageReader$2(onImageAvailableListener);
                        }
                    });
                } else {
                    onImageAvailableListener.onImageAvailable(ProcessingImageReader.this);
                }
            }
        }

        public /* synthetic */ void lambda$onImageAvailable$0$ProcessingImageReader$2(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
            onImageAvailableListener.onImageAvailable(ProcessingImageReader.this);
        }
    }

    ProcessingImageReader(Builder builder) {
        if (builder.mInputImageReader.getMaxImages() < builder.mCaptureBundle.getCaptureStages().size()) {
            throw new IllegalArgumentException("MetadataImageReader is smaller than CaptureBundle.");
        }
        MetadataImageReader metadataImageReader = builder.mInputImageReader;
        this.mInputImageReader = metadataImageReader;
        int width = metadataImageReader.getWidth();
        int height = metadataImageReader.getHeight();
        if (builder.mOutputFormat == 256) {
            width = ((int) (width * height * 1.5f)) + EXIF_MAX_SIZE_BYTES;
            height = 1;
        }
        AndroidImageReaderProxy androidImageReaderProxy = new AndroidImageReaderProxy(ImageReader.newInstance(width, height, builder.mOutputFormat, metadataImageReader.getMaxImages()));
        this.mOutputImageReader = androidImageReaderProxy;
        this.mPostProcessExecutor = builder.mPostProcessExecutor;
        CaptureProcessor captureProcessor = builder.mCaptureProcessor;
        this.mCaptureProcessor = captureProcessor;
        captureProcessor.onOutputSurface(androidImageReaderProxy.getSurface(), builder.mOutputFormat);
        captureProcessor.onResolutionUpdate(new Size(metadataImageReader.getWidth(), metadataImageReader.getHeight()));
        setCaptureBundle(builder.mCaptureBundle);
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireLatestImage() {
        ImageProxy imageProxyAcquireLatestImage;
        synchronized (this.mLock) {
            imageProxyAcquireLatestImage = this.mOutputImageReader.acquireLatestImage();
        }
        return imageProxyAcquireLatestImage;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireNextImage() {
        ImageProxy imageProxyAcquireNextImage;
        synchronized (this.mLock) {
            imageProxyAcquireNextImage = this.mOutputImageReader.acquireNextImage();
        }
        return imageProxyAcquireNextImage;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void close() {
        synchronized (this.mLock) {
            if (this.mClosed) {
                return;
            }
            this.mOutputImageReader.clearOnImageAvailableListener();
            if (!this.mProcessing) {
                this.mInputImageReader.close();
                this.mSettableImageProxyBundle.close();
                this.mOutputImageReader.close();
                CallbackToFutureAdapter.Completer<Void> completer = this.mCloseCompleter;
                if (completer != null) {
                    completer.set(null);
                }
            }
            this.mClosed = true;
        }
    }

    ListenableFuture<Void> getCloseFuture() {
        ListenableFuture<Void> listenableFutureNonCancellationPropagating;
        synchronized (this.mLock) {
            if (this.mClosed && !this.mProcessing) {
                listenableFutureNonCancellationPropagating = Futures.immediateFuture(null);
            } else {
                if (this.mCloseFuture == null) {
                    this.mCloseFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.-$$Lambda$ProcessingImageReader$GJSGY37GK3LQoHLbtisZVUHr1uM
                        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                        public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                            return this.f$0.lambda$getCloseFuture$0$ProcessingImageReader(completer);
                        }
                    });
                }
                listenableFutureNonCancellationPropagating = Futures.nonCancellationPropagating(this.mCloseFuture);
            }
        }
        return listenableFutureNonCancellationPropagating;
    }

    public /* synthetic */ Object lambda$getCloseFuture$0$ProcessingImageReader(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.mLock) {
            this.mCloseCompleter = completer;
        }
        return "ProcessingImageReader-close";
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getHeight() {
        int height;
        synchronized (this.mLock) {
            height = this.mInputImageReader.getHeight();
        }
        return height;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getWidth() {
        int width;
        synchronized (this.mLock) {
            width = this.mInputImageReader.getWidth();
        }
        return width;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getImageFormat() {
        int imageFormat;
        synchronized (this.mLock) {
            imageFormat = this.mOutputImageReader.getImageFormat();
        }
        return imageFormat;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getMaxImages() {
        int maxImages;
        synchronized (this.mLock) {
            maxImages = this.mInputImageReader.getMaxImages();
        }
        return maxImages;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public Surface getSurface() {
        Surface surface;
        synchronized (this.mLock) {
            surface = this.mInputImageReader.getSurface();
        }
        return surface;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, Executor executor) {
        synchronized (this.mLock) {
            this.mListener = (ImageReaderProxy.OnImageAvailableListener) Preconditions.checkNotNull(onImageAvailableListener);
            this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
            this.mInputImageReader.setOnImageAvailableListener(this.mTransformedListener, executor);
            this.mOutputImageReader.setOnImageAvailableListener(this.mImageProcessedListener, executor);
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        synchronized (this.mLock) {
            this.mListener = null;
            this.mExecutor = null;
            this.mInputImageReader.clearOnImageAvailableListener();
            this.mOutputImageReader.clearOnImageAvailableListener();
            if (!this.mProcessing) {
                this.mSettableImageProxyBundle.close();
            }
        }
    }

    public void setCaptureBundle(CaptureBundle captureBundle) {
        synchronized (this.mLock) {
            if (captureBundle.getCaptureStages() != null) {
                if (this.mInputImageReader.getMaxImages() < captureBundle.getCaptureStages().size()) {
                    throw new IllegalArgumentException("CaptureBundle is larger than InputImageReader.");
                }
                this.mCaptureIdList.clear();
                for (CaptureStage captureStage : captureBundle.getCaptureStages()) {
                    if (captureStage != null) {
                        this.mCaptureIdList.add(Integer.valueOf(captureStage.getId()));
                    }
                }
            }
            String string = Integer.toString(captureBundle.hashCode());
            this.mTagBundleKey = string;
            this.mSettableImageProxyBundle = new SettableImageProxyBundle(this.mCaptureIdList, string);
            setupSettableImageProxyBundleCallbacks();
        }
    }

    public String getTagBundleKey() {
        return this.mTagBundleKey;
    }

    CameraCaptureCallback getCameraCaptureCallback() {
        CameraCaptureCallback cameraCaptureCallback;
        synchronized (this.mLock) {
            cameraCaptureCallback = this.mInputImageReader.getCameraCaptureCallback();
        }
        return cameraCaptureCallback;
    }

    void setupSettableImageProxyBundleCallbacks() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.mCaptureIdList.iterator();
        while (it.hasNext()) {
            arrayList.add(this.mSettableImageProxyBundle.getImageProxy(it.next().intValue()));
        }
        Futures.addCallback(Futures.allAsList(arrayList), this.mCaptureStageReadyCallback, this.mPostProcessExecutor);
    }

    void imageIncoming(ImageReaderProxy imageReaderProxy) {
        synchronized (this.mLock) {
            if (this.mClosed) {
                return;
            }
            try {
                ImageProxy imageProxyAcquireNextImage = imageReaderProxy.acquireNextImage();
                if (imageProxyAcquireNextImage != null) {
                    Integer num = (Integer) imageProxyAcquireNextImage.getImageInfo().getTagBundle().getTag(this.mTagBundleKey);
                    if (!this.mCaptureIdList.contains(num)) {
                        Logger.w(TAG, "ImageProxyBundle does not contain this id: " + num);
                        imageProxyAcquireNextImage.close();
                    } else {
                        this.mSettableImageProxyBundle.addImageProxy(imageProxyAcquireNextImage);
                    }
                }
            } catch (IllegalStateException e) {
                Logger.e(TAG, "Failed to acquire latest image.", e);
            }
        }
    }

    static final class Builder {
        protected final CaptureBundle mCaptureBundle;
        protected final CaptureProcessor mCaptureProcessor;
        protected final MetadataImageReader mInputImageReader;
        protected int mOutputFormat;
        protected Executor mPostProcessExecutor;

        Builder(MetadataImageReader metadataImageReader, CaptureBundle captureBundle, CaptureProcessor captureProcessor) {
            this.mPostProcessExecutor = Executors.newSingleThreadExecutor();
            this.mInputImageReader = metadataImageReader;
            this.mCaptureBundle = captureBundle;
            this.mCaptureProcessor = captureProcessor;
            this.mOutputFormat = metadataImageReader.getImageFormat();
        }

        Builder(int i, int i2, int i3, int i4, CaptureBundle captureBundle, CaptureProcessor captureProcessor) {
            this(new MetadataImageReader(i, i2, i3, i4), captureBundle, captureProcessor);
        }

        Builder setPostProcessExecutor(Executor executor) {
            this.mPostProcessExecutor = executor;
            return this;
        }

        Builder setOutputFormat(int i) {
            this.mOutputFormat = i;
            return this;
        }

        ProcessingImageReader build() {
            return new ProcessingImageReader(this);
        }
    }
}
