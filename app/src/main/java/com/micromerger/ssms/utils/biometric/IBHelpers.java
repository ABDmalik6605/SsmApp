package com.micromerger.ssms.utils.biometric;

import android.graphics.Bitmap;
import android.util.Log;
import com.integratedbiometrics.ibscancommon.IBCommon;
import com.integratedbiometrics.ibscanmatcher.IBMatcher;
import com.integratedbiometrics.ibscanmatcher.IBMatcherException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class IBHelpers {
    private static final String HELPERS_TAG = "IBHelpers";

    public static boolean createPng(IBCommon.ImageDataExt imageData, File file) throws IOException {
        try {
            IBMatcher iBMatcher = IBMatcher.getInstance();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            iBMatcher.convertImageToBitmap(imageData).compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (IOException unused) {
            Log.e(HELPERS_TAG, "Could not create image for e-mail");
            return false;
        }
    }

    public static boolean createWsq(IBCommon.ImageDataExt imageData, File file) throws IOException {
        boolean z = false;
        try {
            IBCommon.ImageDataExt imageDataExtCompressImage = IBMatcher.getInstance().compressImage(imageData, IBCommon.ImageFormat.WSQ);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(imageDataExtCompressImage.imageData);
                fileOutputStream.close();
                z = true;
            } catch (IOException unused) {
                Log.e(HELPERS_TAG, "Could not create image");
            }
        } catch (IBMatcherException e) {
            Log.e(HELPERS_TAG, "Could not create image " + e.getType().toString());
        }
        return z;
    }

    public static boolean createFir(IBCommon.ImageDataExt imageData, File file) {
        try {
            IBMatcher.getInstance().saveImageAsFir(imageData, file.getAbsolutePath());
            return true;
        } catch (IBMatcherException e) {
            Log.e(HELPERS_TAG, "Could not create image " + e.getType().toString());
            return false;
        }
    }

    public static boolean createFmr(IBCommon.ImageDataExt imageData, File file) {
        try {
            IBMatcher iBMatcher = IBMatcher.getInstance();
            try {
                iBMatcher.saveTemplateAsFmr(iBMatcher.extractTemplate(imageData), file.getAbsolutePath());
                return true;
            } catch (IBMatcherException e) {
                Log.e(HELPERS_TAG, "Could not create image " + e.getType().toString());
                return false;
            }
        } catch (IBMatcherException e2) {
            Log.e(HELPERS_TAG, "Could not create template for image " + e2.getType().toString());
            return false;
        }
    }

    public static boolean createFmr(IBMatcher.Template template, File file) {
        try {
            IBMatcher.getInstance().saveTemplateAsFmr(template, file.getAbsolutePath());
            return true;
        } catch (IBMatcherException e) {
            Log.e(HELPERS_TAG, "Could not create template for image " + e.getType().toString());
            return false;
        }
    }

    public static boolean createIbsmImage(IBCommon.ImageDataExt imageData, File file) {
        try {
            IBMatcher.getInstance().saveImage(imageData, file.getAbsolutePath());
            return true;
        } catch (IBMatcherException e) {
            Log.e(HELPERS_TAG, "Could not create image " + e.getType().toString());
            return false;
        }
    }

    public static boolean createIbsmTemplate(IBCommon.ImageDataExt imageData, File file) {
        try {
            IBMatcher iBMatcher = IBMatcher.getInstance();
            try {
                iBMatcher.saveTemplate(iBMatcher.extractTemplate(imageData), file.getAbsolutePath());
                return true;
            } catch (IBMatcherException e) {
                Log.e(HELPERS_TAG, "Could not create image " + e.getType().toString());
                return false;
            }
        } catch (IBMatcherException e2) {
            Log.e(HELPERS_TAG, "Could not create template for image " + e2.getType().toString());
            return false;
        }
    }

    public static boolean createIbsmTemplate(IBMatcher.Template template, File file) {
        try {
            IBMatcher.getInstance().saveTemplate(template, file.getAbsolutePath());
            return true;
        } catch (IBMatcherException e) {
            Log.e(HELPERS_TAG, "Could not create template for image " + e.getType().toString());
            return false;
        }
    }
}
