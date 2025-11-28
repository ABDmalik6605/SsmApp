package com.micromerger.ssms.startmonitoring.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Location;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.micromerger.ssms.camerax.CameraActivity;
import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.utils.BasePreferenceHelper;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.MarshmallowPermissions;
import com.micromerger.ssms.utils.PictureUtils;
import com.mindorks.paracamera.Camera;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public abstract class BaseFragment extends Fragment {
    private static final int CAMERA_RESULT = 101;
    private static final int READ_EXTERNAL_STORAGE_RESULT = 103;
    private static final int WRITE_EXTERNAL_STORAGE_RESULT = 102;
    public static Bitmap photo;
    private TextView bt_my_obs;
    private TextView bt_view_obs;
    private Camera camera;
    private CameraActivityCallBack cameraActivityCallBack;
    private CameraActivityCallBackMultiImages cameraActivityCallBackMultiImages;
    public FragmentManager fm;
    public FragmentTransaction ft;
    protected Location location;
    private Context mContext;
    MarshmallowPermissions marshMallowPermission;
    View parentView;
    private ArrayList<String> permissionsToRequest;
    protected BasePreferenceHelper preferenceHelper;
    int CAMERA_REQUEST = 1;
    int CAMERA_CROP = 2;
    private Bitmap bitmap = null;
    private String bitmapPath = "";

    // FIXED: Converted broken lambda to standard ActivityResultCallback
    private final ActivityResultLauncher<Intent> getImagePath = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == -1) {
                        if (result.getData() != null) {
                            String stringExtra = result.getData().getStringExtra(CameraActivity.IMAGE_PATH);
                            if (stringExtra != null) {
                                if (BaseFragment.this.cameraActivityCallBack != null) {
                                    BaseFragment.this.cameraActivityCallBack.onCameraActivityResult(BitmapFactory.decodeFile(stringExtra), stringExtra);
                                }
                                return;
                            } else {
                                CommonActions.snackMsgs(BaseFragment.this.parentView, "Error, please try again later!");
                                return;
                            }
                        }
                        CommonActions.snackMsgs(BaseFragment.this.parentView, "Error, please try again later!");
                    }
                }
            }
    );

    int requestCode = 0;

    // FIXED: Converted broken lambda to standard ActivityResultCallback
    private final ActivityResultLauncher<Intent> getMultipleImagesPath = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == -1) {
                        if (result.getData() != null) {
                            String stringExtra = result.getData().getStringExtra(CameraActivity.IMAGE_PATH);
                            if (stringExtra != null) {
                                if (BaseFragment.this.cameraActivityCallBackMultiImages != null) {
                                    BaseFragment.this.cameraActivityCallBackMultiImages.onCameraActivityResult(BitmapFactory.decodeFile(stringExtra), stringExtra, BaseFragment.this.requestCode);
                                }
                                return;
                            } else {
                                CommonActions.snackMsgs(BaseFragment.this.parentView, "Error, please try again later!");
                                return;
                            }
                        }
                        CommonActions.snackMsgs(BaseFragment.this.parentView, "Error, please try again later!");
                    }
                }
            }
    );

    public interface CameraActivityCallBack {
        void onCameraActivityResult(Bitmap bitmap, String bitmapPath);
    }

    public interface CameraActivityCallBackMultiImages {
        void onCameraActivityResult(Bitmap bitmap, String bitmapPath, int requestCode);
    }

    public abstract boolean onBackPressed();

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        this.preferenceHelper = new BasePreferenceHelper(activity);
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        this.fm = supportFragmentManager;
        this.ft = supportFragmentManager.beginTransaction();
        this.marshMallowPermission = new MarshmallowPermissions(getActivity());

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("android.permission.CAMERA");
        // Write permission is generally not needed on newer Android versions for app-specific storage, but keeping logic intact
        if (Build.VERSION.SDK_INT < 29) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        arrayList.add("android.permission.ACCESS_COARSE_LOCATION");

        ArrayList<String> arrayListFindUnAskedPermissions = findUnAskedPermissions(arrayList);
        this.permissionsToRequest = arrayListFindUnAskedPermissions;

        if (arrayListFindUnAskedPermissions.size() > 0 && Build.VERSION.SDK_INT >= 23) {
            requestPermissions(this.permissionsToRequest.toArray(new String[0]), 0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (getActivity() != null && getActivity().getCurrentFocus() != null) {
            CommonActions.hideSoftKeyboard(getActivityContext(), getActivity().getCurrentFocus());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getActivityContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void loadingStarted() {
        if (getMainActivity() != null) {
            getMainActivity().onLoadingStarted();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void loadingFinished() {
        if (getMainActivity() != null) {
            getMainActivity().onLoadingFinished();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    protected void setCameraActivityCallBack(Fragment fragment) {
        if (fragment instanceof CameraActivityCallBack) {
            this.cameraActivityCallBack = (CameraActivityCallBack) fragment;
        }
    }

    protected void setCameraActivityCallBackMultiImages(Fragment fragment) {
        if (fragment instanceof CameraActivityCallBackMultiImages) {
            this.cameraActivityCallBackMultiImages = (CameraActivityCallBackMultiImages) fragment;
        }
    }

    public void getPhotoFromCamera(int requestCode, String fileName) {
        this.requestCode = requestCode;
        this.getMultipleImagesPath.launch(new Intent(getActivityContext(), (Class<?>) CameraActivity.class).putExtra(CameraActivity.IMAGE_FILE_NAME, fileName));
    }

    public void getPhotoFromCamera(int requestCode, String fileName, Boolean isSquare) {
        this.requestCode = requestCode;
        this.getMultipleImagesPath.launch(new Intent(getActivityContext(), (Class<?>) CameraActivity.class).putExtra(CameraActivity.IMAGE_FILE_NAME, fileName).putExtra(CameraActivity.IS_SQUARE_IMAGE, isSquare));
    }

    public void getPhotoFromCamera(String fileName) {
        this.getImagePath.launch(new Intent(getActivityContext(), (Class<?>) CameraActivity.class).putExtra(CameraActivity.IMAGE_FILE_NAME, fileName));
    }

    public void getPhotoFromCameraOld() {
        if (!this.marshMallowPermission.checkPermissionForCamera()) {
            this.marshMallowPermission.requestPermissionForCamera();
            return;
        }
        if (!this.marshMallowPermission.checkPermissionForExternalStorage()) {
            this.marshMallowPermission.requestPermissionForExternalStorage();
            return;
        }
        Camera camera = new Camera(getActivity());
        this.camera = camera;
        camera.builder().setDirectory("SSMS").setName("image_" + System.currentTimeMillis()).setImageFormat(Camera.IMAGE_JPEG).setCompression(50).setImageHeight(480);
        try {
            this.camera.takePicture();
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handles old camera implementation logic (12-22)
        if (requestCode >= 12 && requestCode <= 22) {
            if (resultCode == -1) {
                try {
                    if (this.camera != null && this.camera.getCameraBitmap() != null) {
                        String strSaveFile = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                        Bitmap bitmapAddWaterMark = PictureUtils.addWaterMark(this.camera.getCameraBitmap());

                        if (this.cameraActivityCallBackMultiImages != null && strSaveFile != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark, strSaveFile, requestCode);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error: Camera data empty", Toast.LENGTH_SHORT).show();
                    }
                } catch (Throwable unused) {
                    CommonActions.snackMsgs(this.parentView, "Error saving image!");
                }
            }
            return;
        }

        if (requestCode == 1 && resultCode == -1) {
            String strSaveFile;
            try {
                strSaveFile = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
            } catch (Throwable unused12) {
                strSaveFile = null;
            }
            if (this.camera != null && strSaveFile != null) {
                this.bitmapPath = strSaveFile;
            } else {
                Log.e("CAMERA", "onActivityResult: camera not initialized or path is null");
                Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", Toast.LENGTH_SHORT).show();
            }
            Camera camera12 = this.camera;
            if (camera12 != null) {
                try {
                    this.bitmap = PictureUtils.addWaterMark(camera12.getCameraBitmap());
                } catch (Throwable e) {
                    Log.e("CAMERA", "onActivityResult: " + e);
                }
            }
            Bitmap bitmap = this.bitmap;
            if (bitmap != null && this.cameraActivityCallBack != null) {
                this.cameraActivityCallBack.onCameraActivityResult(bitmap, this.bitmapPath);
            } else {
                CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
            }
        }
    }

    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
        int attributeInt = new ExifInterface(image_absolute_path).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        if (attributeInt == 2) {
            return flip(bitmap, true, false);
        }
        if (attributeInt == 3) {
            return rotate(bitmap, 180.0f);
        }
        if (attributeInt == 4) {
            return flip(bitmap, false, true);
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? bitmap : rotate(bitmap, 270.0f);
        }
        return rotate(bitmap, 90.0f);
    }

    public static Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1.0f : 1.0f, vertical ? -1.0f : 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Simple logging or handling if needed
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                arrayList.add(perm);
            }
        }
        return arrayList;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= 23) {
                return getActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return Build.VERSION.SDK_INT > 22;
    }
}