package com.micromerger.ssms.startmonitoring;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.stats.CodePackage;
import com.micromerger.ssms.R;
import com.micromerger.ssms.camerax.utils.DeviceLocation;
import com.micromerger.ssms.camerax.utils.OnGpsListener;
import com.micromerger.ssms.camerax.utils.PostLocation;
import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import java.util.Date;
import java.util.Map;

/* loaded from: classes2.dex */
public class GeofencingActivity extends AppCompatActivity {
    Button btn_try_again_location;
    CountDownTimer countDownTimer;
    DeviceLocation deviceLocation;
    double distanceMeters;
    Location fetchedLocation;
    ImageView gif_image;
    String latitude;
    boolean locationCapturing;
    String longitude;
    View parentView;
    ProgressBar progressBarId;
    boolean startLocationClicked;
    TextView tv_accuracy;
    TextView tv_count_down_timer;
    TextView tv_location_message;
    TextView tv_outside_fence;
    TextView tv_wait;
    final String[] PERMISSIONS = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    boolean requestingPermission = false;
    String negativeValue = Constant.ECE_Katchi;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofencing);
        ((Toolbar) findViewById(R.id.geofenceToolBarId)).setNavigationOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$GeofencingActivity$wcDyCq3zP9Z6bDdPZPjESMrTW64
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$GeofencingActivity(view);
            }
        });
        if (getIntent().hasExtra(Constant.Latitude)) {
            this.latitude = getIntent().getStringExtra(Constant.Latitude);
        }
        if (getIntent().hasExtra(Constant.Longitude)) {
            this.longitude = getIntent().getStringExtra(Constant.Longitude);
        }
        if (getIntent().hasExtra(Constant.Fence)) {
            this.distanceMeters = getIntent().getDoubleExtra(Constant.Fence, 0.0d);
        }
        this.parentView = findViewById(R.id.parentView);
        this.btn_try_again_location = (Button) findViewById(R.id.btn_try_again_location);
        this.tv_accuracy = (TextView) findViewById(R.id.tv_accuracy);
        this.tv_location_message = (TextView) findViewById(R.id.tv_location_message);
        this.progressBarId = (ProgressBar) findViewById(R.id.progressBarId);
        this.tv_count_down_timer = (TextView) findViewById(R.id.tv_count_down_timer);
        this.tv_wait = (TextView) findViewById(R.id.tv_wait);
        this.tv_outside_fence = (TextView) findViewById(R.id.tv_outside_fence);
        this.gif_image = (ImageView) findViewById(R.id.gif_image);
        Glide.with((FragmentActivity) this).load(Integer.valueOf(R.drawable.location_gif)).into(this.gif_image);
        this.deviceLocation = deviceLocation();
        startCapturingLocation();
        this.btn_try_again_location.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$GeofencingActivity$Se7UpVlxjpqj1hX6-K2b8zrePRw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$1$GeofencingActivity(view);
            }
        });
        MainActivity.requestGPSPermission = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$GeofencingActivity$YChKkbYQvDWfTYfy60ndFlkZStE
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.lambda$onCreate$2$GeofencingActivity((ActivityResult) obj);
            }
        });
        MainActivity.intentSender = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$GeofencingActivity$ken8V0ShxgjZs7bPSOJrGz2Ky7g
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.lambda$onCreate$3$GeofencingActivity((ActivityResult) obj);
            }
        });
        MainActivity.requestMultiplePermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$GeofencingActivity$5HiKCDyuA8HY2zLEjQ9b59ZocNs
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.lambda$onCreate$4$GeofencingActivity((Map) obj);
            }
        });
        MainActivity.openSettings = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$GeofencingActivity$7yJ2qcWmdut3_4WHNnySokAX8uc
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.lambda$onCreate$5$GeofencingActivity((ActivityResult) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$GeofencingActivity(View view) {
        finish();
    }

    public /* synthetic */ void lambda$onCreate$1$GeofencingActivity(View view) {
        DeviceLocation deviceLocation = this.deviceLocation;
        if (deviceLocation != null) {
            deviceLocation.stopLocation(true);
            this.deviceLocation = null;
        }
        this.deviceLocation = deviceLocation();
        startCapturingLocation();
    }

    public /* synthetic */ void lambda$onCreate$2$GeofencingActivity(ActivityResult activityResult) {
        this.deviceLocation.turnOnGPS(new OnGpsListener() { // from class: com.micromerger.ssms.startmonitoring.GeofencingActivity.1
            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void gpsStatus(boolean status) {
                GeofencingActivity.this.requestLocationPermissions();
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void startResolutionForResult(ResolvableApiException resolvableApiException) {
                MainActivity.intentSender.launch(new IntentSenderRequest.Builder(resolvableApiException.getResolution().getIntentSender()).build());
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void showGPSAlert() {
                GeofencingActivity.this.normalState();
                GeofencingActivity.this.deviceLocation.showGPSAlert(GeofencingActivity.this, MainActivity.requestGPSPermission, GeofencingActivity.this.getResources().getString(R.string.gps_required), GeofencingActivity.this.getResources().getString(R.string.turn_on_gps_for_camera), GeofencingActivity.this.getResources().getString(R.string.ok));
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$3$GeofencingActivity(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            requestLocationPermissions();
        } else {
            normalState();
            this.deviceLocation.showGPSAlert(this, MainActivity.requestGPSPermission, getString(R.string.gpssetting), getString(R.string.enablegps), getString(R.string.ok));
        }
    }

    public /* synthetic */ void lambda$onCreate$4$GeofencingActivity(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            str.hashCode();
            if (str.equals("android.permission.ACCESS_FINE_LOCATION") || str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                if (((Boolean) entry.getValue()).booleanValue()) {
                    permissionsGranted();
                } else {
                    Toast.makeText(this, "Location Permission denied", 0).show();
                    requestLocationPermissions();
                    this.progressBarId.setVisibility(8);
                    this.btn_try_again_location.setVisibility(0);
                }
            }
        }
    }

    public /* synthetic */ void lambda$onCreate$5$GeofencingActivity(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            if (activityResult.getData() != null) {
                requestLocationPermissions();
            } else {
                CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
            }
        }
    }

    private void startCapturingLocation() {
        this.startLocationClicked = true;
        loadingState();
        Log.d(CodePackage.LOCATION, "btn_start_location: " + new Date());
        if (this.deviceLocation.isGPSEnabled(this)) {
            requestLocationPermissions();
        } else {
            this.deviceLocation.turnOnGPS(new OnGpsListener() { // from class: com.micromerger.ssms.startmonitoring.GeofencingActivity.2
                @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
                public void gpsStatus(boolean status) {
                    GeofencingActivity.this.requestLocationPermissions();
                }

                @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
                public void startResolutionForResult(ResolvableApiException resolvableApiException) {
                    MainActivity.intentSender.launch(new IntentSenderRequest.Builder(resolvableApiException.getResolution().getIntentSender()).build());
                }

                @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
                public void showGPSAlert() {
                    GeofencingActivity.this.normalState();
                    GeofencingActivity.this.deviceLocation.showGPSAlert(GeofencingActivity.this, MainActivity.requestGPSPermission, GeofencingActivity.this.getResources().getString(R.string.gps_required), GeofencingActivity.this.getResources().getString(R.string.turn_on_gps_to_get_location), GeofencingActivity.this.getResources().getString(R.string.ok));
                }
            });
        }
    }

    private DeviceLocation deviceLocation() {
        return new DeviceLocation(this, this, new AnonymousClass3());
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.GeofencingActivity$3, reason: invalid class name */
    class AnonymousClass3 implements PostLocation {
        AnonymousClass3() {
        }

        @Override // com.micromerger.ssms.camerax.utils.PostLocation
        public void message(String message) {
            if (message != null && !message.isEmpty()) {
                GeofencingActivity.this.tv_location_message.setVisibility(0);
                GeofencingActivity.this.gif_image.setVisibility(0);
            } else {
                GeofencingActivity.this.tv_location_message.setVisibility(8);
                GeofencingActivity.this.gif_image.setVisibility(8);
            }
            GeofencingActivity.this.tv_location_message.setText(message);
            GeofencingActivity.this.tv_location_message.setTextColor(ContextCompat.getColor(GeofencingActivity.this, R.color.red));
        }

        @Override // com.micromerger.ssms.camerax.utils.PostLocation
        public void locationCallback(final Location location) {
            if (location != null) {
                GeofencingActivity.this.fetchedLocation = location;
                GeofencingActivity.this.startLocationClicked = false;
                Log.d(CodePackage.LOCATION, "locationCallback: " + location.getLatitude() + ", " + location.getLongitude() + ", " + new Date());
                GeofencingActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$GeofencingActivity$3$WeMKOp_j6ix40k5bX0fDVomfo98
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$locationCallback$0$GeofencingActivity$3(location);
                    }
                });
                return;
            }
            if (GeofencingActivity.this.fetchedLocation == null) {
                GeofencingActivity.this.progressBarId.setVisibility(0);
            }
            GeofencingActivity.this.locationCapturing = false;
            GeofencingActivity.this.startLocationClicked = false;
        }

        public /* synthetic */ void lambda$locationCallback$0$GeofencingActivity$3(Location location) {
            if (GeofencingActivity.this.countDownTimer != null) {
                GeofencingActivity.this.countDownTimer.cancel();
            }
            GeofencingActivity.this.tv_count_down_timer.setVisibility(8);
            GeofencingActivity.this.progressBarId.setVisibility(8);
            GeofencingActivity.this.tv_accuracy.setVisibility(0);
            GeofencingActivity.this.tv_accuracy.setText(GeofencingActivity.this.getResources().getString(R.string.accuracy, String.valueOf(location.getAccuracy())));
            GeofencingActivity.this.btn_try_again_location.setVisibility(0);
            if (location.getAccuracy() <= 30.0f) {
                GeofencingActivity.this.tv_accuracy.setTextColor(ContextCompat.getColor(GeofencingActivity.this, R.color.colorPrimaryGreenDark));
                GeofencingActivity.this.tv_location_message.setTextColor(ContextCompat.getColor(GeofencingActivity.this, R.color.dark_grey_color));
                GeofencingActivity.this.tv_location_message.setVisibility(8);
                GeofencingActivity.this.gif_image.setVisibility(8);
                GeofencingActivity.this.deviceLocation.stopLocation(true);
            } else if (location.getAccuracy() > 30.0f && location.getAccuracy() <= 50.0f) {
                GeofencingActivity.this.tv_accuracy.setTextColor(ContextCompat.getColor(GeofencingActivity.this, R.color.orange));
                GeofencingActivity.this.tv_location_message.setTextColor(ContextCompat.getColor(GeofencingActivity.this, R.color.orange));
                GeofencingActivity.this.gif_image.setVisibility(0);
            } else {
                GeofencingActivity.this.tv_accuracy.setTextColor(ContextCompat.getColor(GeofencingActivity.this, R.color.red));
                GeofencingActivity.this.tv_location_message.setTextColor(ContextCompat.getColor(GeofencingActivity.this, R.color.red));
                GeofencingActivity.this.gif_image.setVisibility(0);
            }
            GeofencingActivity.this.isUserInsideFence(location);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.micromerger.ssms.startmonitoring.GeofencingActivity$4] */
    private void startTimer() {
        this.tv_accuracy.setVisibility(8);
        this.countDownTimer = new CountDownTimer(15000L, 1000L) { // from class: com.micromerger.ssms.startmonitoring.GeofencingActivity.4
            @Override // android.os.CountDownTimer
            public void onTick(long millis) {
                GeofencingActivity.this.tv_count_down_timer.setText(GeofencingActivity.this.getResources().getString(R.string.please_wait_s, String.valueOf(((int) millis) / 1000)));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                GeofencingActivity.this.tv_count_down_timer.setVisibility(8);
                GeofencingActivity.this.tv_accuracy.setVisibility(0);
                GeofencingActivity.this.tv_location_message.setVisibility(0);
                GeofencingActivity.this.gif_image.setVisibility(0);
                GeofencingActivity.this.tv_accuracy.setText(GeofencingActivity.this.getResources().getString(R.string.accuracy, GeofencingActivity.this.negativeValue));
                GeofencingActivity.this.tv_location_message.setText(GeofencingActivity.this.getResources().getString(R.string.location_seems_not_working));
                GeofencingActivity.this.tv_location_message.setTextColor(ContextCompat.getColor(GeofencingActivity.this, R.color.red));
                GeofencingActivity.this.countDownTimer.cancel();
                GeofencingActivity.this.locationCapturing = false;
                GeofencingActivity.this.startLocationClicked = false;
            }
        }.start();
    }

    private void loadingState() {
        this.btn_try_again_location.setVisibility(8);
        this.progressBarId.setVisibility(0);
        this.tv_outside_fence.setVisibility(8);
        this.tv_wait.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void normalState() {
        this.btn_try_again_location.setVisibility(0);
        this.progressBarId.setVisibility(8);
        this.tv_count_down_timer.setVisibility(8);
        this.tv_location_message.setVisibility(8);
        this.gif_image.setVisibility(8);
    }

    private void outsideFenceState() {
        this.tv_wait.setVisibility(8);
        this.tv_outside_fence.setVisibility(0);
        this.btn_try_again_location.setVisibility(0);
        this.progressBarId.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void permissionsGranted() {
        if (this.deviceLocation.canStreamLocation()) {
            loadingState();
            this.tv_location_message.setTextColor(ContextCompat.getColor(this, R.color.dark_grey_color));
            this.tv_location_message.setVisibility(8);
            this.gif_image.setVisibility(8);
            startStreamLocation();
            return;
        }
        normalState();
        this.tv_location_message.setVisibility(0);
        this.gif_image.setVisibility(0);
        this.tv_location_message.setTextColor(ContextCompat.getColor(this, R.color.red));
    }

    private void startStreamLocation() {
        this.fetchedLocation = null;
        loadingState();
        if (this.fetchedLocation == null) {
            this.tv_count_down_timer.setVisibility(0);
            startTimer();
        }
        this.locationCapturing = true;
        this.deviceLocation.streamLocation(new OnGpsListener() { // from class: com.micromerger.ssms.startmonitoring.GeofencingActivity.5
            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void gpsStatus(boolean status) {
                GeofencingActivity.this.permissionsGranted();
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void startResolutionForResult(ResolvableApiException resolvableApiException) {
                MainActivity.intentSender.launch(new IntentSenderRequest.Builder(resolvableApiException.getResolution().getIntentSender()).build());
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void showGPSAlert() {
                GeofencingActivity.this.deviceLocation.showGPSAlert(GeofencingActivity.this, MainActivity.requestGPSPermission, GeofencingActivity.this.getResources().getString(R.string.gps_required), GeofencingActivity.this.getResources().getString(R.string.turn_on_gps_for_camera), GeofencingActivity.this.getResources().getString(R.string.ok));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestLocationPermissions() {
        if (!hasPermissions(this).booleanValue()) {
            MainActivity.requestMultiplePermissions.launch(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
            this.requestingPermission = true;
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")) {
                return;
            }
            this.requestingPermission = false;
            runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$GeofencingActivity$FZhTcz1cBhXiD7DpYRjv1Y_-8yo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$requestLocationPermissions$7$GeofencingActivity();
                }
            });
            return;
        }
        permissionsGranted();
        this.requestingPermission = false;
    }

    public /* synthetic */ void lambda$requestLocationPermissions$7$GeofencingActivity() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 1);
        sweetAlertDialog.setTitleText("Permission Denied").setContentText("In order to perform functionality, SSMS requires Location access. Please enable Location access in Settings").setConfirmText("Setting").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$GeofencingActivity$Hvkm3fdaLoKqPtx3zhTmabeF-yQ
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public final void onClick(SweetAlertDialog sweetAlertDialog2) {
                this.f$0.lambda$requestLocationPermissions$6$GeofencingActivity(sweetAlertDialog2);
            }
        }).setCancelText("Cancel").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public final void onClick(SweetAlertDialog sweetAlertDialog2) {
                sweetAlertDialog2.dismiss();
            }
        }).setCancelable(false);
        sweetAlertDialog.show();
    }

    public /* synthetic */ void lambda$requestLocationPermissions$6$GeofencingActivity(SweetAlertDialog sweetAlertDialog) {
        sweetAlertDialog.dismiss();
        openSettings();
    }

    private void openSettings() {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
            MainActivity.openSettings.launch(intent);
        } catch (Exception unused) {
            runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.-$$Lambda$GeofencingActivity$3_2JfKCpecOUG68hLqUyHa0MMGE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$openSettings$8$GeofencingActivity();
                }
            });
        }
    }

    public /* synthetic */ void lambda$openSettings$8$GeofencingActivity() {
        Toast.makeText(this, "SSMS is unable to open app settings. Open the app settings yourself to allow Permissions", 0).show();
    }

    private Boolean hasPermissions(Context context) {
        for (String str : this.PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isUserInsideFence(Location userLocation) {
        String str = this.latitude;
        if (str == null || this.longitude == null || str.isEmpty() || this.longitude.isEmpty()) {
            this.tv_outside_fence.setText(getResources().getString(R.string.outside_of_fence));
            this.tv_outside_fence.setTextColor(ContextCompat.getColor(this, R.color.red));
            outsideFenceState();
            return;
        }
        Location location = new Location("School Location");
        location.setLatitude(Double.parseDouble(this.latitude));
        location.setLongitude(Double.parseDouble(this.longitude));
        if (userLocation.distanceTo(location) > this.distanceMeters) {
            this.tv_outside_fence.setText(getResources().getString(R.string.outside_of_fence));
            this.tv_outside_fence.setTextColor(ContextCompat.getColor(this, R.color.red));
            outsideFenceState();
        } else {
            CommonObjects.mAGeofenceCheck = true;
            this.deviceLocation.stopLocation(true);
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.locationCapturing) {
            this.locationCapturing = false;
            this.startLocationClicked = false;
        }
        if (this.requestingPermission) {
            requestLocationPermissions();
            this.requestingPermission = false;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        DeviceLocation deviceLocation = this.deviceLocation;
        if (deviceLocation != null) {
            deviceLocation.stopLocation(true);
        }
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.onDestroy();
    }
}
