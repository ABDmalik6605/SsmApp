package com.micromerger.ssms.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.location.Location;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class PictureUtils {
    private static Location resultLocation;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.graphics.Bitmap] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String saveFile(android.graphics.Bitmap r4, java.lang.String r5) {
        /*
            android.content.ContextWrapper r0 = new android.content.ContextWrapper
            android.content.Context r1 = com.micromerger.ssms.main.SSMS.getAppContext()
            r0.<init>(r1)
            java.io.File r0 = r0.getFilesDir()
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "SSMS/"
            r2.append(r3)
            r2.append(r5)
            java.lang.String r5 = ".jpeg"
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            r1.<init>(r0, r5)
            r5 = 0
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L44 java.io.FileNotFoundException -> L46
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L44 java.io.FileNotFoundException -> L46
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.io.FileNotFoundException -> L42 java.lang.Throwable -> L56
            r3 = 50
            r4.compress(r2, r3, r0)     // Catch: java.io.FileNotFoundException -> L42 java.lang.Throwable -> L56
            java.lang.String r4 = r1.getPath()     // Catch: java.io.FileNotFoundException -> L42 java.lang.Throwable -> L56
            r0.close()     // Catch: java.io.IOException -> L3d
            goto L41
        L3d:
            r5 = move-exception
            r5.printStackTrace()
        L41:
            return r4
        L42:
            r4 = move-exception
            goto L48
        L44:
            r4 = move-exception
            goto L58
        L46:
            r4 = move-exception
            r0 = r5
        L48:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L56
            if (r0 == 0) goto L55
            r0.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r4 = move-exception
            r4.printStackTrace()
        L55:
            return r5
        L56:
            r4 = move-exception
            r5 = r0
        L58:
            if (r5 == 0) goto L62
            r5.close()     // Catch: java.io.IOException -> L5e
            goto L62
        L5e:
            r5 = move-exception
            r5.printStackTrace()
        L62:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.utils.PictureUtils.saveFile(android.graphics.Bitmap, java.lang.String):java.lang.String");
    }

    private static WatermarkOptions watermarkOptions() {
        WatermarkOptions watermarkOptions = new WatermarkOptions();
        watermarkOptions.setCorner(Corner.BOTTOM_RIGHT);
        watermarkOptions.setTextSizeToWidthRatio(0.065f);
        watermarkOptions.setBottomPaddingRatio(0.05f);
        watermarkOptions.setRightPaddingRadio(0.05f);
        watermarkOptions.setTextColor(SupportMenu.CATEGORY_MASK);
        watermarkOptions.setShadowColor(ViewCompat.MEASURED_STATE_MASK);
        return watermarkOptions;
    }

    private static String getWatermarkDate() {
        String strValueOf;
        String strValueOf2;
        String strValueOf3;
        String strValueOf4;
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(5);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(1);
        int i4 = calendar.get(10) == 0 ? 12 : calendar.get(10);
        int i5 = calendar.get(12);
        String str = calendar.get(9) == 0 ? "AM" : "PM";
        if (String.valueOf(i).length() > 1) {
            strValueOf = String.valueOf(i);
        } else {
            strValueOf = Constant.ECE_Katchi + i;
        }
        if (String.valueOf(i2).length() > 1) {
            strValueOf2 = String.valueOf(i2);
        } else {
            strValueOf2 = Constant.ECE_Katchi + i2;
        }
        if (String.valueOf(i4).length() > 1) {
            strValueOf3 = String.valueOf(i4);
        } else {
            strValueOf3 = Constant.ECE_Katchi + i4;
        }
        if (String.valueOf(i5).length() > 1) {
            strValueOf4 = String.valueOf(i5);
        } else {
            strValueOf4 = Constant.ECE_Katchi + i5;
        }
        return strValueOf + "-" + strValueOf2 + "-" + i3 + " " + strValueOf3 + ":" + strValueOf4 + " " + str;
    }

    private static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(1);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float f = -paint.ascent();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap((int) (paint.measureText(text) + 0.5f), (int) (paint.descent() + f + 0.5f), Bitmap.Config.ARGB_8888);
        new Canvas(bitmapCreateBitmap).drawText(text, 0.0f, f, paint);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream);
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
    }

    public static Bitmap addWaterMark(Bitmap bitmap) {
        Bitmap bitmapCopy = bitmap.copy(bitmap.getConfig(), true);
        try {
            waterMark(getWatermarkDate(), new Paint(7), new Canvas(bitmapCopy), bitmapCopy, watermarkOptions());
        } catch (Exception e) {
            util.logException(new Exception("Exception while adding DateTime watermark: " + e));
        }
        return bitmapCopy;
    }

    /* renamed from: com.micromerger.ssms.utils.PictureUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$micromerger$ssms$utils$Corner;

        static {
            int[] iArr = new int[Corner.values().length];
            $SwitchMap$com$micromerger$ssms$utils$Corner = iArr;
            try {
                iArr[Corner.TOP_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$utils$Corner[Corner.BOTTOM_LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$utils$Corner[Corner.TOP_RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$micromerger$ssms$utils$Corner[Corner.BOTTOM_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static void waterMark(String watermark, Paint paint, Canvas canvas, Bitmap result, WatermarkOptions watermarkOptions) {
        int i = AnonymousClass1.$SwitchMap$com$micromerger$ssms$utils$Corner[watermarkOptions.getCorner().ordinal()];
        if (i == 1 || i == 2) {
            paint.setTextAlign(Paint.Align.LEFT);
        } else if (i == 3 || i == 4) {
            paint.setTextAlign(Paint.Align.RIGHT);
        }
        float width = result.getWidth() * watermarkOptions.getTextSizeToWidthRatio();
        paint.setTextSize(width);
        paint.setColor(watermarkOptions.getTextColor());
        paint.setShadowLayer(width / 2.0f, 0.0f, 0.0f, watermarkOptions.getShadowColor());
        PointF pointFCalculateCoordinates = calculateCoordinates(watermark, paint, canvas.getWidth(), canvas.getHeight(), result.getWidth() * watermarkOptions.getBottomPaddingRatio(), result.getWidth() * watermarkOptions.getRightPaddingRadio());
        canvas.drawBitmap(textAsBitmap(watermark, width, watermarkOptions.getTextColor()), pointFCalculateCoordinates.x, pointFCalculateCoordinates.y, paint);
    }

    private static PointF calculateCoordinates(String watermarkText, Paint paint, int width, int height, float bottomPadding, float rightPadding) {
        paint.getTextBounds(watermarkText, 0, watermarkText.length(), new Rect());
        return new PointF((width - r0.width()) - rightPadding, (height - r0.height()) - bottomPadding);
    }
}
