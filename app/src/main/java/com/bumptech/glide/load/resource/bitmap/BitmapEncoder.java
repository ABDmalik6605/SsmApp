package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;

/* loaded from: classes.dex */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    private static final String TAG = "BitmapEncoder";
    private final ArrayPool arrayPool;
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

    public BitmapEncoder(ArrayPool arrayPool) {
        this.arrayPool = arrayPool;
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0072 A[Catch: all -> 0x00c2, TRY_LEAVE, TryCatch #0 {all -> 0x00c2, blocks: (B:3:0x0021, B:14:0x004f, B:30:0x006c, B:32:0x0072, B:36:0x00be, B:37:0x00c1), top: B:43:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x004f A[EXC_TOP_SPLITTER, PHI: r5 r6
  0x004f: PHI (r5v1 boolean) = (r5v0 boolean), (r5v3 boolean) binds: [B:27:0x0068, B:13:0x004d] A[DONT_GENERATE, DONT_INLINE]
  0x004f: PHI (r6v4 java.io.OutputStream) = (r6v3 java.io.OutputStream), (r6v8 java.io.OutputStream) binds: [B:27:0x0068, B:13:0x004d] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    @Override // com.bumptech.glide.load.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean encode(com.bumptech.glide.load.engine.Resource<android.graphics.Bitmap> r9, java.io.File r10, com.bumptech.glide.load.Options r11) {
        /*
            r8 = this;
            java.lang.String r0 = "BitmapEncoder"
            java.lang.Object r9 = r9.get()
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            android.graphics.Bitmap$CompressFormat r1 = r8.getFormat(r9, r11)
            int r2 = r9.getWidth()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r3 = r9.getHeight()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.String r4 = "encode: [%dx%d] %s"
            com.bumptech.glide.util.pool.GlideTrace.beginSectionFormat(r4, r2, r3, r1)
            long r2 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch: java.lang.Throwable -> Lc2
            com.bumptech.glide.load.Option<java.lang.Integer> r4 = com.bumptech.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_QUALITY     // Catch: java.lang.Throwable -> Lc2
            java.lang.Object r4 = r11.get(r4)     // Catch: java.lang.Throwable -> Lc2
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch: java.lang.Throwable -> Lc2
            int r4 = r4.intValue()     // Catch: java.lang.Throwable -> Lc2
            r5 = 0
            r6 = 0
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5b
            r7.<init>(r10)     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5b
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r10 = r8.arrayPool     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            if (r10 == 0) goto L45
            com.bumptech.glide.load.data.BufferedOutputStream r10 = new com.bumptech.glide.load.data.BufferedOutputStream     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r6 = r8.arrayPool     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            r10.<init>(r7, r6)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            r6 = r10
            goto L46
        L45:
            r6 = r7
        L46:
            r9.compress(r1, r4, r6)     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5b
            r6.close()     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5b
            r5 = 1
            if (r6 == 0) goto L6b
        L4f:
            r6.close()     // Catch: java.io.IOException -> L6b java.lang.Throwable -> Lc2
            goto L6b
        L53:
            r9 = move-exception
            r6 = r7
            goto Lbc
        L56:
            r10 = move-exception
            r6 = r7
            goto L5c
        L59:
            r9 = move-exception
            goto Lbc
        L5b:
            r10 = move-exception
        L5c:
            r4 = 3
            boolean r4 = android.util.Log.isLoggable(r0, r4)     // Catch: java.lang.Throwable -> L59
            if (r4 == 0) goto L68
            java.lang.String r4 = "Failed to encode Bitmap"
            android.util.Log.d(r0, r4, r10)     // Catch: java.lang.Throwable -> L59
        L68:
            if (r6 == 0) goto L6b
            goto L4f
        L6b:
            r10 = 2
            boolean r10 = android.util.Log.isLoggable(r0, r10)     // Catch: java.lang.Throwable -> Lc2
            if (r10 == 0) goto Lb8
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc2
            r10.<init>()     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r4 = "Compressed with type: "
            r10.append(r4)     // Catch: java.lang.Throwable -> Lc2
            r10.append(r1)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r1 = " of size "
            r10.append(r1)     // Catch: java.lang.Throwable -> Lc2
            int r1 = com.bumptech.glide.util.Util.getBitmapByteSize(r9)     // Catch: java.lang.Throwable -> Lc2
            r10.append(r1)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r1 = " in "
            r10.append(r1)     // Catch: java.lang.Throwable -> Lc2
            double r1 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)     // Catch: java.lang.Throwable -> Lc2
            r10.append(r1)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r1 = ", options format: "
            r10.append(r1)     // Catch: java.lang.Throwable -> Lc2
            com.bumptech.glide.load.Option<android.graphics.Bitmap$CompressFormat> r1 = com.bumptech.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_FORMAT     // Catch: java.lang.Throwable -> Lc2
            java.lang.Object r11 = r11.get(r1)     // Catch: java.lang.Throwable -> Lc2
            r10.append(r11)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r11 = ", hasAlpha: "
            r10.append(r11)     // Catch: java.lang.Throwable -> Lc2
            boolean r9 = r9.hasAlpha()     // Catch: java.lang.Throwable -> Lc2
            r10.append(r9)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r9 = r10.toString()     // Catch: java.lang.Throwable -> Lc2
            android.util.Log.v(r0, r9)     // Catch: java.lang.Throwable -> Lc2
        Lb8:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r5
        Lbc:
            if (r6 == 0) goto Lc1
            r6.close()     // Catch: java.io.IOException -> Lc1 java.lang.Throwable -> Lc2
        Lc1:
            throw r9     // Catch: java.lang.Throwable -> Lc2
        Lc2:
            r9 = move-exception
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.BitmapEncoder.encode(com.bumptech.glide.load.engine.Resource, java.io.File, com.bumptech.glide.load.Options):boolean");
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        if (compressFormat != null) {
            return compressFormat;
        }
        if (bitmap.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    @Override // com.bumptech.glide.load.ResourceEncoder
    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
