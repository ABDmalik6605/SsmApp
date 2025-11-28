package com.mindorks.paracamera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.micromerger.ssms.camerax.CameraActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class Utils {
    public static File createImageFile(Context context, String str, String str2, String str3) throws IOException {
        try {
            File file = new File(createDir(context, str).getAbsoluteFile() + File.separator + str2 + str3);
            file.createNewFile();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File createDir(Context context, String str) {
        File file = new File(context.getExternalFilesDir(null) + File.separator + str);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public static Bitmap decodeFile(File file, int i) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i2 = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, options);
            while ((options.outWidth / i2) / 2 >= i && (options.outHeight / i2) / 2 >= i) {
                i2 *= 2;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i2;
            return BitmapFactory.decodeStream(new FileInputStream(file), null, options2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveBitmap(Bitmap bitmap, String str, String str2, int i) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                    try {
                        if (str2.equals(Camera.IMAGE_PNG) || str2.equals("PNG") || str2.equals(".png")) {
                            bitmap.compress(Bitmap.CompressFormat.PNG, i, fileOutputStream2);
                        } else if (str2.equals(Camera.IMAGE_JPG) || str2.equals("JPG") || str2.equals(".jpg") || str2.equals(Camera.IMAGE_JPEG) || str2.equals("JPEG") || str2.equals(CameraActivity.PHOTO_EXTENSION)) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, i, fileOutputStream2);
                        }
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (IOException unused2) {
        }
    }
}