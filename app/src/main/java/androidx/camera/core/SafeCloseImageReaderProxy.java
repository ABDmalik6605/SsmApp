package androidx.camera.core;

import android.view.Surface;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.impl.ImageReaderProxy;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class SafeCloseImageReaderProxy implements ImageReaderProxy {
    private final ImageReaderProxy mImageReaderProxy;
    private final Surface mSurface;
    private final Object mLock = new Object();
    private int mOutstandingImages = 0;
    private boolean mIsClosed = false;
    private final ForwardingImageProxy.OnImageCloseListener mImageCloseListener = new ForwardingImageProxy.OnImageCloseListener() { // from class: androidx.camera.core.-$$Lambda$SafeCloseImageReaderProxy$zycm9fElMBko0VBGypZhA8l8cGU
        @Override // androidx.camera.core.ForwardingImageProxy.OnImageCloseListener
        public final void onImageClose(ImageProxy imageProxy) {
            this.f$0.lambda$new$0$SafeCloseImageReaderProxy(imageProxy);
        }
    };

    public /* synthetic */ void lambda$new$0$SafeCloseImageReaderProxy(ImageProxy imageProxy) {
        synchronized (this.mLock) {
            int i = this.mOutstandingImages - 1;
            this.mOutstandingImages = i;
            if (this.mIsClosed && i == 0) {
                close();
            }
        }
    }

    SafeCloseImageReaderProxy(ImageReaderProxy imageReaderProxy) {
        this.mImageReaderProxy = imageReaderProxy;
        this.mSurface = imageReaderProxy.getSurface();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireLatestImage() {
        ImageProxy imageProxyWrapImageProxy;
        synchronized (this.mLock) {
            imageProxyWrapImageProxy = wrapImageProxy(this.mImageReaderProxy.acquireLatestImage());
        }
        return imageProxyWrapImageProxy;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireNextImage() {
        ImageProxy imageProxyWrapImageProxy;
        synchronized (this.mLock) {
            imageProxyWrapImageProxy = wrapImageProxy(this.mImageReaderProxy.acquireNextImage());
        }
        return imageProxyWrapImageProxy;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void close() {
        synchronized (this.mLock) {
            Surface surface = this.mSurface;
            if (surface != null) {
                surface.release();
            }
            this.mImageReaderProxy.close();
        }
    }

    private ImageProxy wrapImageProxy(ImageProxy imageProxy) {
        if (imageProxy == null) {
            return null;
        }
        this.mOutstandingImages++;
        SingleCloseImageProxy singleCloseImageProxy = new SingleCloseImageProxy(imageProxy);
        singleCloseImageProxy.addOnImageCloseListener(this.mImageCloseListener);
        return singleCloseImageProxy;
    }

    void safeClose() {
        synchronized (this.mLock) {
            this.mIsClosed = true;
            this.mImageReaderProxy.clearOnImageAvailableListener();
            if (this.mOutstandingImages == 0) {
                close();
            }
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getHeight() {
        int height;
        synchronized (this.mLock) {
            height = this.mImageReaderProxy.getHeight();
        }
        return height;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getWidth() {
        int width;
        synchronized (this.mLock) {
            width = this.mImageReaderProxy.getWidth();
        }
        return width;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getImageFormat() {
        int imageFormat;
        synchronized (this.mLock) {
            imageFormat = this.mImageReaderProxy.getImageFormat();
        }
        return imageFormat;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getMaxImages() {
        int maxImages;
        synchronized (this.mLock) {
            maxImages = this.mImageReaderProxy.getMaxImages();
        }
        return maxImages;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public Surface getSurface() {
        Surface surface;
        synchronized (this.mLock) {
            surface = this.mImageReaderProxy.getSurface();
        }
        return surface;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(final ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, Executor executor) {
        synchronized (this.mLock) {
            this.mImageReaderProxy.setOnImageAvailableListener(new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.-$$Lambda$SafeCloseImageReaderProxy$vlVuGMKvMVqmwbJFm3dTgGgUzu4
                @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
                public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                    this.f$0.lambda$setOnImageAvailableListener$1$SafeCloseImageReaderProxy(onImageAvailableListener, imageReaderProxy);
                }
            }, executor);
        }
    }

    public /* synthetic */ void lambda$setOnImageAvailableListener$1$SafeCloseImageReaderProxy(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, ImageReaderProxy imageReaderProxy) {
        onImageAvailableListener.onImageAvailable(this);
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        synchronized (this.mLock) {
            this.mImageReaderProxy.clearOnImageAvailableListener();
        }
    }
}
