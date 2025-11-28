package com.mindorks.paracamera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.File;
import java.util.Objects;

/* loaded from: classes2.dex */
public class Camera {
    private static final int IMAGE_COMPRESSION = 75;
    private static final String IMAGE_DEFAULT_DIR = "capture";
    private static final String IMAGE_DEFAULT_NAME = "img_";
    private static final String IMAGE_FORMAT_JPEG = ".jpeg";
    private static final String IMAGE_FORMAT_JPG = ".jpg";
    private static final String IMAGE_FORMAT_PNG = ".png";
    private static final int IMAGE_HEIGHT = 1000;
    public static final String IMAGE_JPEG = "jpeg";
    public static final String IMAGE_JPG = "jpg";
    public static final String IMAGE_PNG = "png";
    public static final int REQUEST_TAKE_PHOTO = 1;
    private Activity activity;
    private Context context;
    private String cameraBitmapPath = null;
    private Bitmap cameraBitmap = null;
    private String dirName = IMAGE_DEFAULT_DIR;
    private String imageName = IMAGE_DEFAULT_NAME + System.currentTimeMillis();
    private int imageHeight = 1000;
    private int compression = 75;
    private String imageType = IMAGE_FORMAT_JPG;

    public Camera(Activity activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    public CameraBuilder builder() {
        return new CameraBuilder();
    }

    public void takePicture() throws NullPointerException {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Objects.requireNonNull(intent.resolveActivity(this.activity.getPackageManager()), "Unable to open camera");
        File fileCreateImageFile = Utils.createImageFile(this.context, this.dirName, this.imageName, this.imageType);
        Objects.requireNonNull(fileCreateImageFile, "Bitmap received from camera is null");
        this.cameraBitmapPath = fileCreateImageFile.getAbsolutePath();
        intent.putExtra("output", Uri.fromFile(fileCreateImageFile));
        this.activity.startActivityForResult(intent, 1);
    }

    public String getCameraBitmapPath() {
        getCameraBitmap().recycle();
        return this.cameraBitmapPath;
    }

    public Bitmap getCameraBitmap() throws Throwable {
        try {
            Bitmap bitmap = this.cameraBitmap;
            if (bitmap != null) {
                bitmap.recycle();
            }
            Bitmap bitmapDecodeFile = Utils.decodeFile(new File(this.cameraBitmapPath), this.imageHeight);
            this.cameraBitmap = bitmapDecodeFile;
            if (bitmapDecodeFile != null) {
                Utils.saveBitmap(bitmapDecodeFile, this.cameraBitmapPath, this.imageType, this.compression);
            }
            return this.cameraBitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    public String resizeAndGetCameraBitmapPath(int i) {
        resizeAndGetCameraBitmap(i).recycle();
        return this.cameraBitmapPath;
    }

    public Bitmap resizeAndGetCameraBitmap(int i) throws Throwable {
        try {
            Bitmap bitmap = this.cameraBitmap;
            if (bitmap != null) {
                bitmap.recycle();
            }
            Bitmap bitmapDecodeFile = Utils.decodeFile(new File(this.cameraBitmapPath), i);
            this.cameraBitmap = bitmapDecodeFile;
            if (bitmapDecodeFile != null) {
                Utils.saveBitmap(bitmapDecodeFile, this.cameraBitmapPath, this.imageType, this.compression);
            }
            return this.cameraBitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    public void deleteImage() {
        if (this.cameraBitmapPath != null) {
            File file = new File(this.cameraBitmapPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public class CameraBuilder {
        public CameraBuilder() {
        }

        public CameraBuilder setDirectory(String str) {
            Camera.this.dirName = str;
            return this;
        }

        public CameraBuilder setName(String str) {
            Camera.this.imageName = str;
            return this;
        }

        public CameraBuilder setImageFormat(String str) {
            if (str == null) {
                Camera.this.imageType = Camera.IMAGE_FORMAT_JPG;
            }
            if (str.equals(Camera.IMAGE_PNG) || str.equals("PNG") || str.equals(Camera.IMAGE_FORMAT_PNG)) {
                Camera.this.imageType = Camera.IMAGE_FORMAT_PNG;
            } else if (str.equals(Camera.IMAGE_JPG) || str.equals("JPG") || str.equals(Camera.IMAGE_FORMAT_JPG)) {
                Camera.this.imageType = Camera.IMAGE_FORMAT_JPG;
            } else if (str.equals(Camera.IMAGE_JPEG) || str.equals("JPEG") || str.equals(".jpeg")) {
                Camera.this.imageType = ".jpeg";
            } else {
                Camera.this.imageType = Camera.IMAGE_FORMAT_JPG;
            }
            return this;
        }

        public CameraBuilder setImageHeight(int i) {
            Camera.this.imageHeight = i;
            return this;
        }

        public CameraBuilder setCompression(int i) {
            if (i > 100) {
                i = 100;
            } else if (i < 0) {
                i = 0;
            }
            Camera.this.compression = i;
            return this;
        }
    }
}