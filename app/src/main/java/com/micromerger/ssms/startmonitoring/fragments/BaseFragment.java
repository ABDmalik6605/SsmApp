package com.micromerger.ssms.startmonitoring.fragments;

import android.content.Context;
import android.content.Intent;
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
    private final ActivityResultLauncher<Intent> getImagePath = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: com.micromerger.ssms.startmonitoring.fragments.BaseFragment.1
        @Override // androidx.activity.result.ActivityResultCallback
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == -1) {
                if (result.getData() != null) {
                    String stringExtra = result.getData().getStringExtra(CameraActivity.IMAGE_PATH);
                    if (stringExtra != null) {
                        BaseFragment.this.cameraActivityCallBack.onCameraActivityResult(BitmapFactory.decodeFile(stringExtra), stringExtra);
                        return;
                    } else {
                        CommonActions.snackMsgs(BaseFragment.this.parentView, "Error, please try again later!");
                        return;
                    }
                }
                CommonActions.snackMsgs(BaseFragment.this.parentView, "Error, please try again later!");
            }
        }
    });
    int requestCode = 0;
    private final ActivityResultLauncher<Intent> getMultipleImagesPath = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$BaseFragment$KtkMwVX7hoZ0We3M31DqN5wgYBE
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            this.f$0.lambda$new$0$BaseFragment((ActivityResult) obj);
        }
    });

    public interface CameraActivityCallBack {
        void onCameraActivityResult(Bitmap bitmap, String bitmapPath);
    }

    public interface CameraActivityCallBackMultiImages {
        void onCameraActivityResult(Bitmap bitmap, String bitmapPath, int requestCode);
    }

    public abstract boolean onBackPressed();

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
    }

    public /* synthetic */ void lambda$new$0$BaseFragment(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            if (activityResult.getData() != null) {
                String stringExtra = activityResult.getData().getStringExtra(CameraActivity.IMAGE_PATH);
                if (stringExtra != null) {
                    this.cameraActivityCallBackMultiImages.onCameraActivityResult(BitmapFactory.decodeFile(stringExtra), stringExtra, this.requestCode);
                    return;
                } else {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
        }
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
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
        ArrayList<String> arrayListFindUnAskedPermissions = findUnAskedPermissions(arrayList);
        this.permissionsToRequest = arrayListFindUnAskedPermissions;
        if (arrayListFindUnAskedPermissions.size() <= 0 || Build.VERSION.SDK_INT < 23) {
            return;
        }
        ArrayList<String> arrayList2 = this.permissionsToRequest;
        requestPermissions((String[]) arrayList2.toArray(new String[arrayList2.size()]), 0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        CommonActions.hideSoftKeyboard(getActivityContext(), getActivity().getCurrentFocus());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getActivityContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void loadingStarted() {
        if (getMainActivity() != null) {
            getMainActivity().onLoadingStarted();
        } else if (getMainActivity() != null) {
            getMainActivity().onLoadingStarted();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void loadingFinished() {
        if (getMainActivity() != null) {
            getMainActivity().onLoadingFinished();
        } else if (getMainActivity() != null) {
            getMainActivity().onLoadingFinished();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void setCameraActivityCallBack(Fragment fragment) {
        this.cameraActivityCallBack = (CameraActivityCallBack) fragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void setCameraActivityCallBackMultiImages(Fragment fragment) {
        this.cameraActivityCallBackMultiImages = (CameraActivityCallBackMultiImages) fragment;
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
        String strSaveFile;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12) {
            if (resultCode == -1) {
                try {
                    String strSaveFile2 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera = this.camera;
                    if (camera != null) {
                        Bitmap bitmapAddWaterMark = PictureUtils.addWaterMark(camera.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile2 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark, strSaveFile2, 12);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 13) {
            if (resultCode == -1) {
                try {
                    String strSaveFile3 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera2 = this.camera;
                    if (camera2 != null) {
                        Bitmap bitmapAddWaterMark2 = PictureUtils.addWaterMark(camera2.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile3 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark2, strSaveFile3, 13);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused2) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 14) {
            if (resultCode == -1) {
                try {
                    String strSaveFile4 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera3 = this.camera;
                    if (camera3 != null) {
                        Bitmap bitmapAddWaterMark3 = PictureUtils.addWaterMark(camera3.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile4 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark3, strSaveFile4, 14);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused3) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 15) {
            if (resultCode == -1) {
                try {
                    String strSaveFile5 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera4 = this.camera;
                    if (camera4 != null) {
                        Bitmap bitmapAddWaterMark4 = PictureUtils.addWaterMark(camera4.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile5 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark4, strSaveFile5, 15);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused4) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 16) {
            if (resultCode == -1) {
                try {
                    String strSaveFile6 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera5 = this.camera;
                    if (camera5 != null) {
                        Bitmap bitmapAddWaterMark5 = PictureUtils.addWaterMark(camera5.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile6 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark5, strSaveFile6, 16);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused5) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 17) {
            if (resultCode == -1) {
                try {
                    String strSaveFile7 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera6 = this.camera;
                    if (camera6 != null) {
                        Bitmap bitmapAddWaterMark6 = PictureUtils.addWaterMark(camera6.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile7 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark6, strSaveFile7, 17);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused6) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 18) {
            if (resultCode == -1) {
                try {
                    String strSaveFile8 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera7 = this.camera;
                    if (camera7 != null) {
                        Bitmap bitmapAddWaterMark7 = PictureUtils.addWaterMark(camera7.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile8 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark7, strSaveFile8, 18);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused7) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 20) {
            if (resultCode == -1) {
                try {
                    String strSaveFile9 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera8 = this.camera;
                    if (camera8 != null) {
                        Bitmap bitmapAddWaterMark8 = PictureUtils.addWaterMark(camera8.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile9 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark8, strSaveFile9, 20);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused8) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 19) {
            if (resultCode == -1) {
                try {
                    String strSaveFile10 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera9 = this.camera;
                    if (camera9 != null) {
                        Bitmap bitmapAddWaterMark9 = PictureUtils.addWaterMark(camera9.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile10 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark9, strSaveFile10, 19);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused9) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 21) {
            if (resultCode == -1) {
                try {
                    String strSaveFile11 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera10 = this.camera;
                    if (camera10 != null) {
                        Bitmap bitmapAddWaterMark10 = PictureUtils.addWaterMark(camera10.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile11 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark10, strSaveFile11, 21);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused10) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 22) {
            if (resultCode == -1) {
                try {
                    String strSaveFile12 = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
                    Camera camera11 = this.camera;
                    if (camera11 != null) {
                        Bitmap bitmapAddWaterMark11 = PictureUtils.addWaterMark(camera11.getCameraBitmap());
                        if (this.bitmapPath != null && strSaveFile12 != null) {
                            this.cameraActivityCallBackMultiImages.onCameraActivityResult(bitmapAddWaterMark11, strSaveFile12, 22);
                        } else {
                            CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                        }
                    } else {
                        Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
                    }
                    return;
                } catch (Exception unused11) {
                    CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
                    return;
                }
            }
            return;
        }
        if (requestCode == 1 && resultCode == -1) {
            try {
                strSaveFile = PictureUtils.saveFile(PictureUtils.addWaterMark(this.camera.getCameraBitmap()), "image_" + System.currentTimeMillis());
            } catch (Exception unused12) {
                strSaveFile = null;
            }
            if (this.camera != null && strSaveFile != null) {
                this.bitmapPath = strSaveFile;
            } else {
                Log.e("CAMERA", "onActivityResult: camera not initialized or path is null");
                Toast.makeText(this.mContext, "Error while getting image from Camera\nPlease Try again", 0).show();
            }
            Camera camera12 = this.camera;
            if (camera12 != null) {
                try {
                    this.bitmap = PictureUtils.addWaterMark(camera12.getCameraBitmap());
                } catch (Exception e) {
                    Log.e("CAMERA", "onActivityResult: " + e);
                }
            }
            Bitmap bitmap = this.bitmap;
            if (bitmap != null) {
                this.cameraActivityCallBack.onCameraActivityResult(bitmap, this.bitmapPath);
            } else {
                CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
            }
        }
    }

    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
        int attributeInt = new ExifInterface(image_absolute_path).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
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
        switch (requestCode) {
            case 101:
                hasPermission("android.permission.CAMERA");
                break;
            case 102:
                hasPermission("android.permission.WRITE_EXTERNAL_STORAGE");
                break;
            case 103:
                hasPermission("android.permission.READ_EXTERNAL_STORAGE");
                break;
        }
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = wanted.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!hasPermission(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private boolean hasPermission(String permission) {
        return !canMakeSmores() || Build.VERSION.SDK_INT < 23 || getActivity().checkSelfPermission(permission) == 0;
    }

    private boolean canMakeSmores() {
        return Build.VERSION.SDK_INT > 22;
    }
}
