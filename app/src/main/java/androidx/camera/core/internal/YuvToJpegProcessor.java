package androidx.camera.core.internal;

import android.graphics.Rect;
import android.media.ImageWriter;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.utils.ExifData;
import androidx.camera.core.internal.compat.ImageWriterCompat;
import androidx.core.util.Preconditions;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

/* loaded from: classes.dex */
public class YuvToJpegProcessor implements CaptureProcessor {
    private static final String TAG = "YuvToJpegProcessor";
    private static final Rect UNINITIALIZED_RECT = new Rect(0, 0, 0, 0);
    private ImageWriter mImageWriter;
    private final int mMaxImages;
    private int mQuality;
    private final Object mLock = new Object();
    private boolean mClosed = false;
    private int mProcessingImages = 0;
    private Rect mImageRect = UNINITIALIZED_RECT;

    public YuvToJpegProcessor(int i, int i2) {
        this.mQuality = i;
        this.mMaxImages = i2;
    }

    public void setJpegQuality(int i) {
        this.mQuality = i;
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void onOutputSurface(Surface surface, int i) {
        Preconditions.checkState(i == 256, "YuvToJpegProcessor only supports JPEG output format.");
        synchronized (this.mLock) {
            if (!this.mClosed) {
                if (this.mImageWriter != null) {
                    throw new IllegalStateException("Output surface already set.");
                }
                this.mImageWriter = ImageWriterCompat.newInstance(surface, this.mMaxImages, i);
            } else {
                Logger.w(TAG, "Cannot set output surface. Processor is closed.");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0131 A[Catch: all -> 0x0154, TRY_ENTER, TRY_LEAVE, TryCatch #9 {all -> 0x0154, blocks: (B:43:0x00e3, B:80:0x0131), top: B:133:0x00e3 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0158  */
    @Override // androidx.camera.core.impl.CaptureProcessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void process(androidx.camera.core.impl.ImageProxyBundle r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.YuvToJpegProcessor.process(androidx.camera.core.impl.ImageProxyBundle):void");
    }

    public void close() {
        synchronized (this.mLock) {
            if (!this.mClosed) {
                this.mClosed = true;
                if (this.mProcessingImages == 0 && this.mImageWriter != null) {
                    Logger.d(TAG, "No processing in progress. Closing immediately.");
                    this.mImageWriter.close();
                } else {
                    Logger.d(TAG, "close() called while processing. Will close after completion.");
                }
            }
        }
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void onResolutionUpdate(Size size) {
        synchronized (this.mLock) {
            this.mImageRect = new Rect(0, 0, size.getWidth(), size.getHeight());
        }
    }

    private static ExifData getExifData(ImageProxy imageProxy) {
        ExifData.Builder builderBuilderForDevice = ExifData.builderForDevice();
        imageProxy.getImageInfo().populateExifData(builderBuilderForDevice);
        return builderBuilderForDevice.setImageWidth(imageProxy.getWidth()).setImageHeight(imageProxy.getHeight()).build();
    }

    private static final class ByteBufferOutputStream extends OutputStream {
        private final ByteBuffer mByteBuffer;

        ByteBufferOutputStream(ByteBuffer byteBuffer) {
            this.mByteBuffer = byteBuffer;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            if (!this.mByteBuffer.hasRemaining()) {
                throw new EOFException("Output ByteBuffer has no bytes remaining.");
            }
            this.mByteBuffer.put((byte) i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            int i3;
            Objects.requireNonNull(bArr);
            if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (i2 == 0) {
                return;
            }
            if (this.mByteBuffer.remaining() < i2) {
                throw new EOFException("Output ByteBuffer has insufficient bytes remaining.");
            }
            this.mByteBuffer.put(bArr, i, i2);
        }
    }
}
