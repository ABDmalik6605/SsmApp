package id.zelory.compressor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mindorks.paracamera.Camera;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang.ClassUtils;

/* compiled from: Util.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u0010\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007\u001a\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0000\u001a\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007\u001a\u0016\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0010\u001a\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r\u001a*\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0007\u001a*\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0007\u001a\n\u0010\u001b\u001a\u00020\u0016*\u00020\r\u001a\n\u0010\u001c\u001a\u00020\u0001*\u00020\u0016\"\u0016\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"separator", "", "kotlin.jvm.PlatformType", "cachePath", "context", "Landroid/content/Context;", "calculateInSampleSize", "", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "copyToCache", "Ljava/io/File;", "imageFile", "decodeSampledBitmapFromFile", "Landroid/graphics/Bitmap;", "determineImageRotation", "bitmap", "loadBitmap", "overWrite", "format", "Landroid/graphics/Bitmap$CompressFormat;", "quality", "saveBitmap", "", FirebaseAnalytics.Param.DESTINATION, "compressFormat", "extension", "compressor_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class UtilKt {
    private static final String separator = File.separator;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Bitmap.CompressFormat.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Bitmap.CompressFormat.PNG.ordinal()] = 1;
            iArr[Bitmap.CompressFormat.WEBP.ordinal()] = 2;
        }
    }

    private static final String cachePath(Context context) {
        StringBuilder sb = new StringBuilder();
        File cacheDir = context.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir, "context.cacheDir");
        sb.append(cacheDir.getPath());
        String str = separator;
        sb.append(str);
        sb.append("compressor");
        sb.append(str);
        return sb.toString();
    }

    public static final Bitmap.CompressFormat compressFormat(File compressFormat) {
        Intrinsics.checkParameterIsNotNull(compressFormat, "$this$compressFormat");
        String extension = FilesKt.getExtension(compressFormat);
        if (extension == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = extension.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        int iHashCode = lowerCase.hashCode();
        if (iHashCode == 111145) {
            if (lowerCase.equals(Camera.IMAGE_PNG)) {
                return Bitmap.CompressFormat.PNG;
            }
        } else if (iHashCode == 3645340 && lowerCase.equals("webp")) {
            return Bitmap.CompressFormat.WEBP;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    public static final String extension(Bitmap.CompressFormat extension) {
        Intrinsics.checkParameterIsNotNull(extension, "$this$extension");
        int i = WhenMappings.$EnumSwitchMapping$0[extension.ordinal()];
        return i != 1 ? i != 2 ? Camera.IMAGE_JPG : "webp" : Camera.IMAGE_PNG;
    }

    public static final Bitmap loadBitmap(File imageFile) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        Intrinsics.checkExpressionValueIsNotNull(bitmapDecodeFile, "this");
        return determineImageRotation(imageFile, bitmapDecodeFile);
    }

    public static final Bitmap decodeSampledBitmapFromFile(File imageFile, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        Intrinsics.checkExpressionValueIsNotNull(bitmapDecodeFile, "BitmapFactory.decodeFile…eFile.absolutePath, this)");
        Intrinsics.checkExpressionValueIsNotNull(bitmapDecodeFile, "BitmapFactory.Options().…absolutePath, this)\n    }");
        return bitmapDecodeFile;
    }

    public static final int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(options, "options");
        Pair pair = TuplesKt.to(Integer.valueOf(options.outHeight), Integer.valueOf(options.outWidth));
        int iIntValue = ((Number) pair.component1()).intValue();
        int iIntValue2 = ((Number) pair.component2()).intValue();
        int i3 = 1;
        if (iIntValue > i2 || iIntValue2 > i) {
            int i4 = iIntValue / 2;
            int i5 = iIntValue2 / 2;
            while (i4 / i3 >= i2 && i5 / i3 >= i) {
                i3 *= 2;
            }
        }
        return i3;
    }

    public static final Bitmap determineImageRotation(File imageFile, Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int attributeInt = new ExifInterface(imageFile.getAbsolutePath()).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 0);
        Matrix matrix = new Matrix();
        if (attributeInt == 3) {
            matrix.postRotate(180.0f);
        } else if (attributeInt == 6) {
            matrix.postRotate(90.0f);
        } else if (attributeInt == 8) {
            matrix.postRotate(270.0f);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Intrinsics.checkExpressionValueIsNotNull(bitmapCreateBitmap, "Bitmap.createBitmap(bitm…map.height, matrix, true)");
        return bitmapCreateBitmap;
    }

    public static final File copyToCache(Context context, File imageFile) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        return FilesKt.copyTo$default(imageFile, new File(cachePath(context) + imageFile.getName()), true, 0, 4, null);
    }

    public static /* synthetic */ File overWrite$default(File file, Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            compressFormat = compressFormat(file);
        }
        if ((i2 & 8) != 0) {
            i = 100;
        }
        return overWrite(file, bitmap, compressFormat, i);
    }

    public static final File overWrite(File imageFile, Bitmap bitmap, Bitmap.CompressFormat format, int i) throws Throwable {
        File file;
        Intrinsics.checkParameterIsNotNull(imageFile, "imageFile");
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        Intrinsics.checkParameterIsNotNull(format, "format");
        if (format == compressFormat(imageFile)) {
            file = imageFile;
        } else {
            StringBuilder sb = new StringBuilder();
            String absolutePath = imageFile.getAbsolutePath();
            Intrinsics.checkExpressionValueIsNotNull(absolutePath, "imageFile.absolutePath");
            sb.append(StringsKt.substringBeforeLast$default(absolutePath, ".", (String) null, 2, (Object) null));
            sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            sb.append(extension(format));
            file = new File(sb.toString());
        }
        imageFile.delete();
        saveBitmap(bitmap, file, format, i);
        return file;
    }

    public static /* synthetic */ void saveBitmap$default(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, int i, int i2, Object obj) throws Throwable {
        if ((i2 & 4) != 0) {
            compressFormat = compressFormat(file);
        }
        if ((i2 & 8) != 0) {
            i = 100;
        }
        saveBitmap(bitmap, file, compressFormat, i);
    }

    public static final void saveBitmap(Bitmap bitmap, File destination, Bitmap.CompressFormat format, int i) throws Throwable {
        FileOutputStream fileOutputStream;
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(format, "format");
        File parentFile = destination.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream2 = (FileOutputStream) null;
        try {
            fileOutputStream = new FileOutputStream(destination.getAbsolutePath());
        } catch (Throwable th) {
            th = th;
        }
        try {
            bitmap.compress(format, i, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                fileOutputStream2.flush();
                fileOutputStream2.close();
            }
            throw th;
        }
    }
}
