package com.micromerger.ssms.utils;

import android.app.Activity;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/* loaded from: classes2.dex */
public class MarshmallowPermissions {
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2;
    Activity activity;

    public MarshmallowPermissions(Activity activity) {
        this.activity = activity;
    }

    public boolean checkPermissionForExternalStorage() {
        return ContextCompat.checkSelfPermission(this.activity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public boolean checkPermissionForFineLocation() {
        return ContextCompat.checkSelfPermission(this.activity, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public boolean checkPermissionForCoarseLocation() {
        return ContextCompat.checkSelfPermission(this.activity, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    public boolean checkPermissionForCamera() {
        return ContextCompat.checkSelfPermission(this.activity, "android.permission.CAMERA") == 0;
    }

    public void requestPermissionForExternalStorage() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            Toast.makeText(this.activity, "External Storage permission needed. Please allow in App Settings for additional functionality.", 1).show();
        } else {
            ActivityCompat.requestPermissions(this.activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 2);
        }
    }

    public void requestPermissionForCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, "android.permission.CAMERA")) {
            Toast.makeText(this.activity, "Camera permission needed. Please allow in App Settings for additional functionality.", 1).show();
        } else {
            ActivityCompat.requestPermissions(this.activity, new String[]{"android.permission.CAMERA"}, 3);
        }
    }
}
