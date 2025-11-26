package com.micromerger.ssms.camerax.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.navigation.Navigation;
import com.google.android.gms.common.api.ResolvableApiException;
import com.micromerger.ssms.R;
import com.micromerger.ssms.camerax.CameraActivity;
import com.micromerger.ssms.camerax.utils.DeviceLocation;
import com.micromerger.ssms.camerax.utils.OnGpsListener;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PermissionsFragment.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/micromerger/ssms/camerax/fragments/PermissionsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "deviceLocation", "Lcom/micromerger/ssms/camerax/utils/DeviceLocation;", "navigateToCamera", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "requestLocationPermissions", "Companion", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class PermissionsFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean cameraAllowed;
    private static boolean locationAllowed;
    private DeviceLocation deviceLocation;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        this.deviceLocation = new DeviceLocation(contextRequireContext);
        CameraActivity.Companion companion = CameraActivity.INSTANCE;
        ActivityResultLauncher<String[]> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$PermissionsFragment$nIOR1kfosB5WNxr08RsyEViVYmo
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                PermissionsFragment.m40onCreate$lambda1(this.f$0, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResul…          }\n            }");
        companion.setRequestMultiplePermissions(activityResultLauncherRegisterForActivityResult);
        CameraActivity.Companion companion2 = CameraActivity.INSTANCE;
        ActivityResultLauncher<IntentSenderRequest> activityResultLauncherRegisterForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$PermissionsFragment$H_AcGLgUyWK7jBvKNSHy1We4o2c
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                PermissionsFragment.m41onCreate$lambda2(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult2, "registerForActivityResul…          }\n            }");
        companion2.setIntentSender(activityResultLauncherRegisterForActivityResult2);
        CameraActivity.Companion companion3 = CameraActivity.INSTANCE;
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$PermissionsFragment$Nhbk5yVvjihcq35Je7I0tBizK6k
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                PermissionsFragment.m42onCreate$lambda3(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult3, "registerForActivityResul…         })\n            }");
        companion3.setRequestGPSPermission(activityResultLauncherRegisterForActivityResult3);
        CameraActivity.Companion companion4 = CameraActivity.INSTANCE;
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult4 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$PermissionsFragment$I3u9sPYn_WRjtRRoLoDKlKMZYtM
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                PermissionsFragment.m43onCreate$lambda4(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult4, "registerForActivityResul…rmissions()\n            }");
        companion4.setOpenSettings(activityResultLauncherRegisterForActivityResult4);
        DeviceLocation deviceLocation = this.deviceLocation;
        DeviceLocation deviceLocation2 = null;
        if (deviceLocation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceLocation");
            deviceLocation = null;
        }
        Context contextRequireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
        if (deviceLocation.isGPSEnabled(contextRequireContext2)) {
            requestLocationPermissions();
            return;
        }
        DeviceLocation deviceLocation3 = this.deviceLocation;
        if (deviceLocation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceLocation");
        } else {
            deviceLocation2 = deviceLocation3;
        }
        deviceLocation2.turnOnGPS(new OnGpsListener() { // from class: com.micromerger.ssms.camerax.fragments.PermissionsFragment.onCreate.5
            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void gpsStatus(boolean status) {
                PermissionsFragment.this.requestLocationPermissions();
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void startResolutionForResult(ResolvableApiException resolvableApiException) {
                Intrinsics.checkNotNullParameter(resolvableApiException, "resolvableApiException");
                CameraActivity.INSTANCE.getIntentSender().launch(new IntentSenderRequest.Builder(resolvableApiException.getResolution().getIntentSender()).build());
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void showGPSAlert() {
                DeviceLocation deviceLocation4 = PermissionsFragment.this.deviceLocation;
                if (deviceLocation4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceLocation");
                    deviceLocation4 = null;
                }
                Context contextRequireContext3 = PermissionsFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
                DeviceLocation.showGPSAlert$default(deviceLocation4, contextRequireContext3, CameraActivity.INSTANCE.getRequestGPSPermission(), null, null, null, 28, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b2 A[SYNTHETIC] */
    /* renamed from: onCreate$lambda-1, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m40onCreate$lambda1(com.micromerger.ssms.camerax.fragments.PermissionsFragment r8, java.util.Map r9) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "permissions"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            java.util.Set r9 = r9.entrySet()
            java.util.Iterator r9 = r9.iterator()
        L12:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto Lef
            java.lang.Object r0 = r9.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L12
            int r2 = r1.hashCode()
            r3 = -1888586689(0xffffffff8f6e743f, float:-1.1756694E-29)
            r4 = 0
            r5 = 1
            java.lang.String r6 = "actionMap.value"
            java.lang.String r7 = "android.permission.ACCESS_FINE_LOCATION"
            if (r2 == r3) goto L9b
            r3 = -63024214(0xfffffffffc3e53aa, float:-3.9529332E36)
            if (r2 == r3) goto L91
            r3 = 463403621(0x1b9efa65, float:2.630072E-22)
            if (r2 == r3) goto L40
            goto L12
        L40:
            java.lang.String r2 = "android.permission.CAMERA"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L49
            goto L12
        L49:
            java.lang.Object r1 = r0.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L5b
            com.micromerger.ssms.camerax.fragments.PermissionsFragment.cameraAllowed = r5
            goto L12
        L5b:
            java.lang.Object r0 = r0.getKey()
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = r8.shouldShowRequestPermissionRationale(r0)
            if (r0 != 0) goto L77
            com.micromerger.ssms.camerax.CameraActivity$Companion r0 = com.micromerger.ssms.camerax.CameraActivity.INSTANCE
            androidx.fragment.app.FragmentActivity r1 = r8.getActivity()
            android.app.Activity r1 = (android.app.Activity) r1
            android.content.Context r3 = r8.getContext()
            r0.showToastAndOpenSettings(r1, r3, r2)
            goto L12
        L77:
            android.content.Context r0 = r8.getContext()
            java.lang.String r1 = "Camera Permission denied"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r4)
            r0.show()
            androidx.fragment.app.FragmentActivity r0 = r8.getActivity()
            if (r0 != 0) goto L8d
            goto L12
        L8d:
            r0.onBackPressed()
            goto L12
        L91:
            java.lang.String r2 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto La3
            goto L12
        L9b:
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto La3
            goto L12
        La3:
            java.lang.Object r1 = r0.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto Lb6
            com.micromerger.ssms.camerax.fragments.PermissionsFragment.locationAllowed = r5
            goto L12
        Lb6:
            java.lang.Object r0 = r0.getKey()
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = r8.shouldShowRequestPermissionRationale(r0)
            if (r0 != 0) goto Ld3
            com.micromerger.ssms.camerax.CameraActivity$Companion r0 = com.micromerger.ssms.camerax.CameraActivity.INSTANCE
            androidx.fragment.app.FragmentActivity r1 = r8.getActivity()
            android.app.Activity r1 = (android.app.Activity) r1
            android.content.Context r2 = r8.getContext()
            r0.showToastAndOpenSettings(r1, r2, r7)
            goto L12
        Ld3:
            android.content.Context r0 = r8.getContext()
            java.lang.String r1 = "Location Permission denied"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r4)
            r0.show()
            androidx.fragment.app.FragmentActivity r0 = r8.getActivity()
            if (r0 != 0) goto Lea
            goto L12
        Lea:
            r0.onBackPressed()
            goto L12
        Lef:
            boolean r9 = com.micromerger.ssms.camerax.fragments.PermissionsFragment.cameraAllowed
            if (r9 == 0) goto Lfa
            boolean r9 = com.micromerger.ssms.camerax.fragments.PermissionsFragment.locationAllowed
            if (r9 == 0) goto Lfa
            r8.navigateToCamera()
        Lfa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.camerax.fragments.PermissionsFragment.m40onCreate$lambda1(com.micromerger.ssms.camerax.fragments.PermissionsFragment, java.util.Map):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2, reason: not valid java name */
    public static final void m41onCreate$lambda2(PermissionsFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.requestLocationPermissions();
            return;
        }
        DeviceLocation deviceLocation = this$0.deviceLocation;
        if (deviceLocation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceLocation");
            deviceLocation = null;
        }
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        DeviceLocation.showGPSAlert$default(deviceLocation, contextRequireContext, CameraActivity.INSTANCE.getRequestGPSPermission(), null, null, null, 28, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-3, reason: not valid java name */
    public static final void m42onCreate$lambda3(final PermissionsFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceLocation deviceLocation = this$0.deviceLocation;
        if (deviceLocation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceLocation");
            deviceLocation = null;
        }
        deviceLocation.turnOnGPS(new OnGpsListener() { // from class: com.micromerger.ssms.camerax.fragments.PermissionsFragment$onCreate$3$1
            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void gpsStatus(boolean status) {
                this.this$0.requestLocationPermissions();
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void startResolutionForResult(ResolvableApiException resolvableApiException) {
                Intrinsics.checkNotNullParameter(resolvableApiException, "resolvableApiException");
                CameraActivity.INSTANCE.getIntentSender().launch(new IntentSenderRequest.Builder(resolvableApiException.getResolution().getIntentSender()).build());
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void showGPSAlert() {
                DeviceLocation deviceLocation2 = this.this$0.deviceLocation;
                if (deviceLocation2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceLocation");
                    deviceLocation2 = null;
                }
                Context contextRequireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                DeviceLocation.showGPSAlert$default(deviceLocation2, contextRequireContext, CameraActivity.INSTANCE.getRequestGPSPermission(), null, null, null, 28, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-4, reason: not valid java name */
    public static final void m43onCreate$lambda4(PermissionsFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requestLocationPermissions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestLocationPermissions() {
        Companion companion = INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        if (!companion.hasPermissions(contextRequireContext)) {
            CameraActivity.INSTANCE.getRequestMultiplePermissions().launch(new String[]{"android.permission.CAMERA", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
        } else {
            navigateToCamera();
        }
    }

    /* compiled from: PermissionsFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.micromerger.ssms.camerax.fragments.PermissionsFragment$navigateToCamera$1", f = "PermissionsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.micromerger.ssms.camerax.fragments.PermissionsFragment$navigateToCamera$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PermissionsFragment.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Navigation.findNavController(PermissionsFragment.this.requireActivity(), R.id.fragment_container).navigate(PermissionsFragmentDirections.actionPermissionsToCamera());
            return Unit.INSTANCE;
        }
    }

    private final void navigateToCamera() {
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new AnonymousClass1(null));
    }

    /* compiled from: PermissionsFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/micromerger/ssms/camerax/fragments/PermissionsFragment$Companion;", "", "()V", "cameraAllowed", "", "locationAllowed", "hasPermissions", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean hasPermissions(Context context) {
            boolean z;
            Intrinsics.checkNotNullParameter(context, "context");
            String[] strArr = PermissionsFragmentKt.PERMISSIONS_REQUIRED;
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
