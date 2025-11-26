package com.micromerger.ssms.camerax.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.text.TextPaint;
import androidx.core.internal.view.SupportMenu;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatermarkImage.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J0\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0002J \u0010\u0014\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002JB\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0002J(\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\bJ\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\""}, d2 = {"Lcom/micromerger/ssms/camerax/utils/WatermarkImage;", "", "()V", "bitmapToFile", "Ljava/io/File;", "context", "Landroid/content/Context;", "filename", "", "bitmap", "Landroid/graphics/Bitmap;", "calculateCoordinates", "Landroid/graphics/PointF;", "watermarkText", "paint", "Landroid/graphics/Paint;", "watermarkType", "Lcom/micromerger/ssms/camerax/utils/WatermarkType;", "mainBitmap", "watermarkBitmap", "textAsBitmap", "text", "waterMark", "", "watermark", "canvas", "Landroid/graphics/Canvas;", "resultBitmap", "watermarkOptions", "Lcom/micromerger/ssms/camerax/utils/WatermarkOptions;", "watermarkImage", "imageFile", "dateTime", FirebaseAnalytics.Param.LOCATION, "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class WatermarkImage {
    public static final WatermarkImage INSTANCE = new WatermarkImage();

    /* compiled from: WatermarkImage.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Corner.values().length];
            iArr[Corner.TOP_LEFT.ordinal()] = 1;
            iArr[Corner.BOTTOM_LEFT.ordinal()] = 2;
            iArr[Corner.TOP_RIGHT.ordinal()] = 3;
            iArr[Corner.BOTTOM_RIGHT.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private WatermarkImage() {
    }

    public final File watermarkImage(Context context, File imageFile, String dateTime, String location) {
        TextPaint textPaint;
        Canvas canvas;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageFile, "imageFile");
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        Bitmap mainBitmap = BitmapFactory.decodeFile(imageFile.getPath());
        Bitmap resultBitmap = mainBitmap.copy(mainBitmap.getConfig(), true);
        Canvas canvas2 = new Canvas(resultBitmap);
        TextPaint textPaint2 = new TextPaint(1);
        try {
            TextPaint textPaint3 = textPaint2;
            Intrinsics.checkNotNullExpressionValue(resultBitmap, "resultBitmap");
            WatermarkType watermarkType = WatermarkType.DateTime;
            Intrinsics.checkNotNullExpressionValue(mainBitmap, "mainBitmap");
            textPaint = textPaint2;
            canvas = canvas2;
            try {
                waterMark(dateTime, textPaint3, canvas2, resultBitmap, watermarkOptions(watermarkType, mainBitmap), WatermarkType.DateTime, mainBitmap);
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                Intrinsics.checkNotNullExpressionValue(resultBitmap, "resultBitmap");
                WatermarkType watermarkType2 = WatermarkType.Location;
                Intrinsics.checkNotNullExpressionValue(mainBitmap, "mainBitmap");
                waterMark(location, textPaint, canvas, resultBitmap, watermarkOptions(watermarkType2, mainBitmap), WatermarkType.Location, mainBitmap);
                String name = imageFile.getName();
                Intrinsics.checkNotNullExpressionValue(name, "imageFile.name");
                Intrinsics.checkNotNullExpressionValue(resultBitmap, "resultBitmap");
                return bitmapToFile(context, name, resultBitmap);
            }
        } catch (Exception e2) {
            e = e2;
            textPaint = textPaint2;
            canvas = canvas2;
        }
        try {
            Intrinsics.checkNotNullExpressionValue(resultBitmap, "resultBitmap");
            WatermarkType watermarkType22 = WatermarkType.Location;
            Intrinsics.checkNotNullExpressionValue(mainBitmap, "mainBitmap");
            waterMark(location, textPaint, canvas, resultBitmap, watermarkOptions(watermarkType22, mainBitmap), WatermarkType.Location, mainBitmap);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        String name2 = imageFile.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "imageFile.name");
        Intrinsics.checkNotNullExpressionValue(resultBitmap, "resultBitmap");
        return bitmapToFile(context, name2, resultBitmap);
    }

    private final File bitmapToFile(Context context, String filename, Bitmap bitmap) throws IOException {
        File file = new File(context.getCacheDir(), filename);
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
                return file;
            } catch (IOException e) {
                throw e;
            }
        } finally {
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }

    private final void waterMark(String watermark, Paint paint, Canvas canvas, Bitmap resultBitmap, WatermarkOptions watermarkOptions, WatermarkType watermarkType, Bitmap mainBitmap) {
        if (watermark != null) {
            Corner corner = watermarkOptions.getCorner();
            int i = corner == null ? -1 : WhenMappings.$EnumSwitchMapping$0[corner.ordinal()];
            if (i == 1 || i == 2) {
                paint.setTextAlign(Paint.Align.LEFT);
            } else if (i == 3 || i == 4) {
                paint.setTextAlign(Paint.Align.RIGHT);
            }
            float width = resultBitmap.getWidth() * watermarkOptions.getTextSizeToWidthRatio();
            paint.setTextSize(width);
            paint.setColor(watermarkOptions.getTextColor());
            paint.setShadowLayer(width / 2, 0.0f, 0.0f, watermarkOptions.getShadowColor());
            Bitmap bitmapTextAsBitmap = textAsBitmap(paint, watermark, watermarkType);
            PointF pointFCalculateCoordinates = calculateCoordinates(watermark, paint, watermarkType, mainBitmap, bitmapTextAsBitmap);
            canvas.drawBitmap(bitmapTextAsBitmap, pointFCalculateCoordinates.x, pointFCalculateCoordinates.y, paint);
        }
    }

    private final WatermarkOptions watermarkOptions(WatermarkType watermarkType, Bitmap bitmap) {
        WatermarkOptions watermarkOptions = new WatermarkOptions();
        if (bitmap.getHeight() > bitmap.getWidth()) {
            watermarkOptions.setTextSizeToWidthRatio(0.065f);
        } else {
            watermarkOptions.setTextSizeToWidthRatio(0.045f);
        }
        watermarkOptions.setShadowColor(-12303292);
        if (watermarkType == WatermarkType.DateTime) {
            watermarkOptions.setCorner(Corner.BOTTOM_RIGHT);
            watermarkOptions.setBottomPaddingRatio(0.05f);
            watermarkOptions.setRightPaddingRatio(0.05f);
            watermarkOptions.setTextColor(SupportMenu.CATEGORY_MASK);
        }
        if (watermarkType == WatermarkType.Location) {
            watermarkOptions.setCorner(Corner.TOP_LEFT);
            watermarkOptions.setTextColor(-1);
        }
        return watermarkOptions;
    }

    private final PointF calculateCoordinates(String watermarkText, Paint paint, WatermarkType watermarkType, Bitmap mainBitmap, Bitmap watermarkBitmap) {
        paint.getTextBounds(watermarkText, 0, watermarkText.length(), new Rect());
        return new PointF(watermarkType == WatermarkType.DateTime ? (mainBitmap.getWidth() - watermarkBitmap.getWidth()) - 16.0f : 4.0f, watermarkType == WatermarkType.DateTime ? (mainBitmap.getHeight() - watermarkBitmap.getHeight()) - 16.0f : 4.0f);
    }

    private final Bitmap textAsBitmap(Paint paint, String text, WatermarkType watermarkType) {
        float textSize;
        paint.setStyle(Paint.Style.FILL);
        if (watermarkType == WatermarkType.Location) {
            textSize = paint.getTextSize() / 1.5f;
        } else {
            textSize = paint.getTextSize();
        }
        paint.setTextSize(textSize);
        paint.setColor(watermarkType == WatermarkType.Location ? -1 : SupportMenu.CATEGORY_MASK);
        paint.setTextAlign(Paint.Align.LEFT);
        float f = -paint.ascent();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap((int) (paint.measureText(text) + 0.5f), (int) (paint.descent() + f + 0.5f), Bitmap.Config.ARGB_8888);
        new Canvas(bitmapCreateBitmap).drawText(text, 0.0f, f, paint);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Intrinsics.checkNotNullExpressionValue(bitmapDecodeStream, "decodeStream(ByteArrayIn…tream(out.toByteArray()))");
        return bitmapDecodeStream;
    }
}
