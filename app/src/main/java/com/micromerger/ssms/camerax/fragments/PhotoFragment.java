package com.micromerger.ssms.camerax.fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.micromerger.ssms.R;
import com.micromerger.ssms.camerax.CameraActivity;
import com.micromerger.ssms.camerax.utils.DeviceLocation;
import com.micromerger.ssms.camerax.utils.PostLocation;
import com.micromerger.ssms.databinding.FragmentPhotoBinding;
import com.micromerger.ssms.utils.Constant;
import java.io.File;
import java.util.Calendar;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: PhotoFragment.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u001a\u001a\u00020\u000fH\u0002J\u001c\u0010\u001b\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\rH\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/micromerger/ssms/camerax/fragments/PhotoFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_fragmentPhotoBinding", "Lcom/micromerger/ssms/databinding/FragmentPhotoBinding;", "deviceLocation", "Lcom/micromerger/ssms/camerax/utils/DeviceLocation;", "fragmentPhotoBinding", "getFragmentPhotoBinding", "()Lcom/micromerger/ssms/databinding/FragmentPhotoBinding;", "imageFile", "Ljava/io/File;", "getWatermarkDate", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "requestLocationPermissions", "saveImage", FirebaseAnalytics.Param.LOCATION, "startSaveProcess", "Companion", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class PhotoFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FILE_NAME_KEY = "file_name";
    private FragmentPhotoBinding _fragmentPhotoBinding;
    private DeviceLocation deviceLocation;
    private File imageFile;

    private final FragmentPhotoBinding getFragmentPhotoBinding() {
        FragmentPhotoBinding fragmentPhotoBinding = this._fragmentPhotoBinding;
        Intrinsics.checkNotNull(fragmentPhotoBinding);
        return fragmentPhotoBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CameraActivity.Companion companion = CameraActivity.INSTANCE;
        ActivityResultLauncher<String[]> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$PhotoFragment$x4OsnmRRpBxdE4g861MK7j2UR5I
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                PhotoFragment.m47onCreate$lambda1(this.f$0, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResul…          }\n            }");
        companion.setRequestMultiplePermissions(activityResultLauncherRegisterForActivityResult);
        CameraActivity.Companion companion2 = CameraActivity.INSTANCE;
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$PhotoFragment$ZDY_YQlmzmQg_kysYEPwMTv8zx0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                PhotoFragment.m48onCreate$lambda2(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult2, "registerForActivityResul…rmissions()\n            }");
        companion2.setOpenSettings(activityResultLauncherRegisterForActivityResult2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1, reason: not valid java name */
    public static final void m47onCreate$lambda1(PhotoFragment this$0, Map permissions) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(permissions, "permissions");
        for (Map.Entry entry : permissions.entrySet()) {
            String str = (String) entry.getKey();
            if (Intrinsics.areEqual(str, "android.permission.ACCESS_COARSE_LOCATION") ? true : Intrinsics.areEqual(str, "android.permission.ACCESS_FINE_LOCATION")) {
                Object value = entry.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "actionMap.value");
                if (((Boolean) value).booleanValue()) {
                    this$0.startSaveProcess();
                } else if (!this$0.shouldShowRequestPermissionRationale((String) entry.getKey())) {
                    CameraActivity.INSTANCE.showToastAndOpenSettings(this$0.getActivity(), this$0.getContext(), "android.permission.ACCESS_FINE_LOCATION");
                } else {
                    Toast.makeText(this$0.getContext(), "Location Permission denied", 0).show();
                    FragmentActivity activity = this$0.getActivity();
                    if (activity != null) {
                        activity.onBackPressed();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2, reason: not valid java name */
    public static final void m48onCreate$lambda2(PhotoFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requestLocationPermissions();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._fragmentPhotoBinding = FragmentPhotoBinding.inflate(inflater, container, false);
        View root = getFragmentPhotoBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "fragmentPhotoBinding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        this.deviceLocation = new DeviceLocation(contextRequireContext);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        String string = arguments.getString("file_name");
        if (string != null) {
            this.imageFile = new File(string);
        }
        final Object objValueOf = this.imageFile;
        if (objValueOf == null || objValueOf == null) {
            objValueOf = Integer.valueOf(R.drawable.ic_photo);
        }
        getFragmentPhotoBinding().getRoot().post(new Runnable() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$PhotoFragment$hEmeMYL63VCJ-oy0qByv0sFSHWk
            @Override // java.lang.Runnable
            public final void run() {
                PhotoFragment.m49onViewCreated$lambda7(view, objValueOf, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-7, reason: not valid java name */
    public static final void m49onViewCreated$lambda7(View view, Object resource, final PhotoFragment this$0) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(resource, "$resource");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Glide.with(view).load(resource).into(this$0.getFragmentPhotoBinding().photo);
        this$0.getFragmentPhotoBinding().retakeButton.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$PhotoFragment$T17GLudvCCP8cyASL9T9MrVjL-4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PhotoFragment.m50onViewCreated$lambda7$lambda5(this.f$0, view2);
            }
        });
        this$0.getFragmentPhotoBinding().doneButton.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$PhotoFragment$XJtZc76ip_wBZN3-X6MEzlsaeco
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PhotoFragment.m51onViewCreated$lambda7$lambda6(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-7$lambda-5, reason: not valid java name */
    public static final void m50onViewCreated$lambda7$lambda5(PhotoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Navigation.findNavController(this$0.requireActivity(), R.id.fragment_container).navigate(PhotoFragmentDirections.actionPhotoToCamera());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-7$lambda-6, reason: not valid java name */
    public static final void m51onViewCreated$lambda7$lambda6(PhotoFragment this$0, View view) {
        DeviceLocation deviceLocation;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceLocation deviceLocation2 = this$0.deviceLocation;
        if (deviceLocation2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceLocation");
            deviceLocation2 = null;
        }
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        if (deviceLocation2.isGPSEnabled(contextRequireContext)) {
            Companion companion = INSTANCE;
            Context contextRequireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
            if (!companion.hasPermissions(contextRequireContext2)) {
                this$0.requestLocationPermissions();
                return;
            } else {
                this$0.startSaveProcess();
                return;
            }
        }
        DeviceLocation deviceLocation3 = this$0.deviceLocation;
        if (deviceLocation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceLocation");
            deviceLocation = null;
        } else {
            deviceLocation = deviceLocation3;
        }
        Context contextRequireContext3 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
        DeviceLocation.showGPSAlert$default(deviceLocation, contextRequireContext3, CameraActivity.INSTANCE.getRequestGPSPermission(), null, null, null, 28, null);
    }

    private final void requestLocationPermissions() {
        CameraActivity.INSTANCE.getRequestMultiplePermissions().launch(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
    }

    private final void startSaveProcess() {
        FragmentPhotoBinding fragmentPhotoBinding = this._fragmentPhotoBinding;
        DeviceLocation deviceLocation = null;
        ProgressBar progressBar = fragmentPhotoBinding == null ? null : fragmentPhotoBinding.progress;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
        FragmentPhotoBinding fragmentPhotoBinding2 = this._fragmentPhotoBinding;
        ImageButton imageButton = fragmentPhotoBinding2 == null ? null : fragmentPhotoBinding2.retakeButton;
        if (imageButton != null) {
            imageButton.setVisibility(8);
        }
        FragmentPhotoBinding fragmentPhotoBinding3 = this._fragmentPhotoBinding;
        ImageButton imageButton2 = fragmentPhotoBinding3 == null ? null : fragmentPhotoBinding3.doneButton;
        if (imageButton2 != null) {
            imageButton2.setVisibility(8);
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        DeviceLocation deviceLocation2 = this.deviceLocation;
        if (deviceLocation2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceLocation");
        } else {
            deviceLocation = deviceLocation2;
        }
        deviceLocation.getLocation(new PostLocation() { // from class: com.micromerger.ssms.camerax.fragments.PhotoFragment.startSaveProcess.1
            @Override // com.micromerger.ssms.camerax.utils.PostLocation
            public void message(String message) {
            }

            @Override // com.micromerger.ssms.camerax.utils.PostLocation
            public void locationCallback(Location location) {
                Unit unit;
                if (location == null) {
                    unit = null;
                } else {
                    Ref.BooleanRef booleanRef2 = booleanRef;
                    PhotoFragment photoFragment = this;
                    String str = location.getLatitude() + ", " + location.getLongitude();
                    Log.d(FirebaseAnalytics.Param.LOCATION, str);
                    if (!booleanRef2.element) {
                        booleanRef2.element = true;
                        photoFragment.saveImage(photoFragment.imageFile, str);
                    }
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    Ref.BooleanRef booleanRef3 = booleanRef;
                    PhotoFragment photoFragment2 = this;
                    if (booleanRef3.element) {
                        return;
                    }
                    booleanRef3.element = true;
                    photoFragment2.saveImage(photoFragment2.imageFile, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void saveImage(java.io.File r21, java.lang.String r22) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.camerax.fragments.PhotoFragment.saveImage(java.io.File, java.lang.String):void");
    }

    private final String getWatermarkDate() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(5);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(1);
        int i4 = calendar.get(10) == 0 ? 12 : calendar.get(10);
        int i5 = calendar.get(12);
        String str = calendar.get(9) == 0 ? "AM" : "PM";
        return (String.valueOf(i).length() > 1 ? String.valueOf(i) : Intrinsics.stringPlus(Constant.ECE_Katchi, Integer.valueOf(i))) + '-' + (String.valueOf(i2).length() > 1 ? String.valueOf(i2) : Intrinsics.stringPlus(Constant.ECE_Katchi, Integer.valueOf(i2))) + '-' + i3 + ' ' + (String.valueOf(i4).length() > 1 ? String.valueOf(i4) : Intrinsics.stringPlus(Constant.ECE_Katchi, Integer.valueOf(i4))) + ':' + (String.valueOf(i5).length() > 1 ? String.valueOf(i5) : Intrinsics.stringPlus(Constant.ECE_Katchi, Integer.valueOf(i5))) + ' ' + str;
    }

    /* compiled from: PhotoFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/micromerger/ssms/camerax/fragments/PhotoFragment$Companion;", "", "()V", "FILE_NAME_KEY", "", "hasPermissions", "", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean hasPermissions(Context context) {
            boolean z;
            Intrinsics.checkNotNullParameter(context, "context");
            String[] strArr = PhotoFragmentKt.PERMISSIONS_REQUIRED;
            int length = strArr.length;
            int i = 0;
            do {
                z = true;
                if (i >= length) {
                    return true;
                }
                String str = strArr[i];
                i++;
                if (ContextCompat.checkSelfPermission(context, str) != 0) {
                    z = false;
                }
            } while (z);
            return false;
        }
    }
}
