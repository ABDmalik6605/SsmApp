package com.micromerger.ssms.camerax;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.micromerger.ssms.R;
import com.micromerger.ssms.camerax.CameraActivity;
import com.micromerger.ssms.databinding.ActivityCameraBinding;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: CameraActivity.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0012\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\u0006H\u0014J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/micromerger/ssms/camerax/CameraActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "activityCameraBinding", "Lcom/micromerger/ssms/databinding/ActivityCameraBinding;", "hideSystemUI", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onKeyDown", "", "keyCode", "", "event", "Landroid/view/KeyEvent;", "onResume", "Companion", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraActivity extends AppCompatActivity {
    public static final String FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS";
    public static final String IMAGE_FILE_NAME = "file_name";
    public static final String IMAGE_PATH = "image_path";
    public static final String IS_SQUARE_IMAGE = "is_square_image";
    public static final String PHOTO_EXTENSION = ".jpeg";
    private static String imageName;
    public static ActivityResultLauncher<IntentSenderRequest> intentSender;
    public static ActivityResultLauncher<Intent> openSettings;
    public static ActivityResultLauncher<Intent> requestGPSPermission;
    public static ActivityResultLauncher<String[]> requestMultiplePermissions;
    private static boolean squareImage;
    private ActivityCameraBinding activityCameraBinding;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Intent resultIntent = new Intent();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCameraBinding activityCameraBindingInflate = ActivityCameraBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityCameraBindingInflate, "inflate(layoutInflater)");
        this.activityCameraBinding = activityCameraBindingInflate;
        if (activityCameraBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityCameraBinding");
            activityCameraBindingInflate = null;
        }
        setContentView(activityCameraBindingInflate.getRoot());
        try {
            imageName = getIntent().getStringExtra(IMAGE_FILE_NAME);
            squareImage = getIntent().getBooleanExtra(IS_SQUARE_IMAGE, false);
        } catch (Exception unused) {
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ActivityCameraBinding activityCameraBinding = this.activityCameraBinding;
        if (activityCameraBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityCameraBinding");
            activityCameraBinding = null;
        }
        activityCameraBinding.fragmentContainer.postDelayed(new Runnable() { // from class: com.micromerger.ssms.camerax.-$$Lambda$CameraActivity$P5y_dr37oN2bw06bNdcUyTwaWQM
            @Override // java.lang.Runnable
            public final void run() {
                CameraActivity.m22onResume$lambda0(this.f$0);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onResume$lambda-0, reason: not valid java name */
    public static final void m22onResume$lambda0(CameraActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideSystemUI();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (keyCode == 25) {
            Intent intent = new Intent(CameraActivityKt.KEY_EVENT_ACTION);
            intent.putExtra(CameraActivityKt.KEY_EVENT_EXTRA, keyCode);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT == 29) {
            finishAfterTransition();
        } else {
            super.onBackPressed();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Companion companion = INSTANCE;
        companion.getRequestGPSPermission().unregister();
        companion.getRequestMultiplePermissions().unregister();
        companion.getIntentSender().unregister();
        companion.getOpenSettings().unregister();
    }

    /* compiled from: CameraActivity.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.J\"\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u00103\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0012\"\u0004\b\u001b\u0010\u0014R&\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001d0\u000fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0012\"\u0004\b\u001f\u0010\u0014R\u0011\u0010 \u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u00064"}, d2 = {"Lcom/micromerger/ssms/camerax/CameraActivity$Companion;", "", "()V", "FILENAME", "", "IMAGE_FILE_NAME", "IMAGE_PATH", "IS_SQUARE_IMAGE", "PHOTO_EXTENSION", "imageName", "getImageName", "()Ljava/lang/String;", "setImageName", "(Ljava/lang/String;)V", "intentSender", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/IntentSenderRequest;", "getIntentSender", "()Landroidx/activity/result/ActivityResultLauncher;", "setIntentSender", "(Landroidx/activity/result/ActivityResultLauncher;)V", "openSettings", "Landroid/content/Intent;", "getOpenSettings", "setOpenSettings", "requestGPSPermission", "getRequestGPSPermission", "setRequestGPSPermission", "requestMultiplePermissions", "", "getRequestMultiplePermissions", "setRequestMultiplePermissions", "resultIntent", "getResultIntent", "()Landroid/content/Intent;", "squareImage", "", "getSquareImage", "()Z", "setSquareImage", "(Z)V", "getOutputDirectory", "Ljava/io/File;", "context", "Landroid/content/Context;", "directoryType", "Lcom/micromerger/ssms/camerax/DirectoryType;", "showToastAndOpenSettings", "", "activity", "Landroid/app/Activity;", "permission", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Intent getResultIntent() {
            return CameraActivity.resultIntent;
        }

        public final String getImageName() {
            return CameraActivity.imageName;
        }

        public final void setImageName(String str) {
            CameraActivity.imageName = str;
        }

        public final ActivityResultLauncher<Intent> getRequestGPSPermission() {
            ActivityResultLauncher<Intent> activityResultLauncher = CameraActivity.requestGPSPermission;
            if (activityResultLauncher != null) {
                return activityResultLauncher;
            }
            Intrinsics.throwUninitializedPropertyAccessException("requestGPSPermission");
            return null;
        }

        public final void setRequestGPSPermission(ActivityResultLauncher<Intent> activityResultLauncher) {
            Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
            CameraActivity.requestGPSPermission = activityResultLauncher;
        }

        public final ActivityResultLauncher<String[]> getRequestMultiplePermissions() {
            ActivityResultLauncher<String[]> activityResultLauncher = CameraActivity.requestMultiplePermissions;
            if (activityResultLauncher != null) {
                return activityResultLauncher;
            }
            Intrinsics.throwUninitializedPropertyAccessException("requestMultiplePermissions");
            return null;
        }

        public final void setRequestMultiplePermissions(ActivityResultLauncher<String[]> activityResultLauncher) {
            Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
            CameraActivity.requestMultiplePermissions = activityResultLauncher;
        }

        public final ActivityResultLauncher<IntentSenderRequest> getIntentSender() {
            ActivityResultLauncher<IntentSenderRequest> activityResultLauncher = CameraActivity.intentSender;
            if (activityResultLauncher != null) {
                return activityResultLauncher;
            }
            Intrinsics.throwUninitializedPropertyAccessException("intentSender");
            return null;
        }

        public final void setIntentSender(ActivityResultLauncher<IntentSenderRequest> activityResultLauncher) {
            Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
            CameraActivity.intentSender = activityResultLauncher;
        }

        public final ActivityResultLauncher<Intent> getOpenSettings() {
            ActivityResultLauncher<Intent> activityResultLauncher = CameraActivity.openSettings;
            if (activityResultLauncher != null) {
                return activityResultLauncher;
            }
            Intrinsics.throwUninitializedPropertyAccessException("openSettings");
            return null;
        }

        public final void setOpenSettings(ActivityResultLauncher<Intent> activityResultLauncher) {
            Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
            CameraActivity.openSettings = activityResultLauncher;
        }

        public final boolean getSquareImage() {
            return CameraActivity.squareImage;
        }

        public final void setSquareImage(boolean z) {
            CameraActivity.squareImage = z;
        }

        public static /* synthetic */ File getOutputDirectory$default(Companion companion, Context context, DirectoryType directoryType, int i, Object obj) {
            if ((i & 2) != 0) {
                directoryType = DirectoryType.Cache;
            }
            return companion.getOutputDirectory(context, directoryType);
        }

        public final File getOutputDirectory(Context context, DirectoryType directoryType) {
            File file;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(directoryType, "directoryType");
            Context applicationContext = context.getApplicationContext();
            File filesDir = directoryType == DirectoryType.Files ? context.getFilesDir() : context.getCacheDir();
            if (filesDir == null) {
                file = null;
            } else {
                File file2 = new File(filesDir, applicationContext.getResources().getString(R.string.app_name));
                file2.mkdirs();
                file = file2;
            }
            if (file != null && file.exists()) {
                return file;
            }
            File filesDir2 = applicationContext.getFilesDir();
            Intrinsics.checkNotNullExpressionValue(filesDir2, "appContext.filesDir");
            return filesDir2;
        }

        public final void showToastAndOpenSettings(final Activity activity, final Context context, final String permission) {
            Intrinsics.checkNotNullParameter(permission, "permission");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = "";
            if (activity == null) {
                return;
            }
            final String str = "Permission Denied";
            activity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.camerax.-$$Lambda$CameraActivity$Companion$BcRnsO_JPbAJ02obcyfETg8pipk
                @Override // java.lang.Runnable
                public final void run() {
                    CameraActivity.Companion.m24showToastAndOpenSettings$lambda4(permission, objectRef, context, str, activity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: showToastAndOpenSettings$lambda-4, reason: not valid java name */
        public static final void m24showToastAndOpenSettings$lambda4(String permission, Ref.ObjectRef description, Context context, String title, final Activity activity) {
            Intrinsics.checkNotNullParameter(permission, "$permission");
            Intrinsics.checkNotNullParameter(description, "$description");
            Intrinsics.checkNotNullParameter(title, "$title");
            if (Intrinsics.areEqual(permission, "android.permission.CAMERA")) {
                description.element = "In order to perform functionality, SSMS requires Camera access. Please enable Camera access in Settings";
            } else if (Intrinsics.areEqual(permission, "android.permission.ACCESS_FINE_LOCATION")) {
                description.element = "In order to perform functionality, SSMS requires Location access. Please enable Location access in Settings";
            }
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 1);
            sweetAlertDialog.setTitleText(title).setContentText((String) description.element).setConfirmText("Setting").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.camerax.-$$Lambda$CameraActivity$Companion$q7wsjkMXFJCNSqVZJKoPj179Up4
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public final void onClick(SweetAlertDialog sweetAlertDialog2) {
                    CameraActivity.Companion.m25showToastAndOpenSettings$lambda4$lambda2(activity, sweetAlertDialog2);
                }
            }).setCancelText("Cancel").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.camerax.-$$Lambda$CameraActivity$Companion$Oe82Va-ssDu_8R56Bn_vCAEbuVM
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public final void onClick(SweetAlertDialog sweetAlertDialog2) {
                    CameraActivity.Companion.m26showToastAndOpenSettings$lambda4$lambda3(activity, sweetAlertDialog2);
                }
            }).setCancelable(false);
            sweetAlertDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: showToastAndOpenSettings$lambda-4$lambda-2, reason: not valid java name */
        public static final void m25showToastAndOpenSettings$lambda4$lambda2(Activity activity, SweetAlertDialog sweetAlertDialog) {
            sweetAlertDialog.dismiss();
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
            CameraActivity.INSTANCE.getOpenSettings().launch(intent);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: showToastAndOpenSettings$lambda-4$lambda-3, reason: not valid java name */
        public static final void m26showToastAndOpenSettings$lambda4$lambda3(Activity activity, SweetAlertDialog sweetAlertDialog) {
            sweetAlertDialog.dismiss();
            activity.onBackPressed();
        }
    }

    private final void hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        Window window = getWindow();
        ActivityCameraBinding activityCameraBinding = this.activityCameraBinding;
        if (activityCameraBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityCameraBinding");
            activityCameraBinding = null;
        }
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(window, activityCameraBinding.fragmentContainer);
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsControllerCompat.setSystemBarsBehavior(2);
    }
}
