package com.micromerger.ssms.camerax.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.stats.CodePackage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.micromerger.ssms.R;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.util;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: DeviceLocation.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 82\u00020\u0001:\u00018B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B!\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0005¢\u0006\u0002\u0010\nJ\b\u0010 \u001a\u00020!H\u0002J\u0006\u0010\"\u001a\u00020#J\u0010\u0010$\u001a\u00020#2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010%\u001a\u00020!H\u0002J\b\u0010&\u001a\u00020!H\u0002J\u0010\u0010'\u001a\u00020!2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010(\u001a\u00020#2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u000e\u0010)\u001a\u00020#2\u0006\u0010\u0002\u001a\u00020\u0003J:\u0010*\u001a\u00020!2\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020/2\b\b\u0002\u00101\u001a\u00020/J\u0010\u00102\u001a\u00020!2\b\b\u0002\u00103\u001a\u00020#J\u0012\u00104\u001a\u00020!2\b\u00105\u001a\u0004\u0018\u000106H\u0007J\u0012\u00107\u001a\u00020!2\b\u00105\u001a\u0004\u0018\u000106H\u0007R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/micromerger/ssms/camerax/utils/DeviceLocation;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activity", "Landroid/app/Activity;", "postLocation", "Lcom/micromerger/ssms/camerax/utils/PostLocation;", "(Landroid/app/Activity;Landroid/content/Context;Lcom/micromerger/ssms/camerax/utils/PostLocation;)V", "()V", "act", "ctx", "distanceFilter", "", "fastestUpdateIntervalMilliseconds", "", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "locationAccuracy", "", "locationCallback", "Lcom/google/android/gms/location/LocationCallback;", "locationManager", "Landroid/location/LocationManager;", "locationRequest", "Lcom/google/android/gms/location/LocationRequest;", "locationSettingRequest", "Lcom/google/android/gms/location/LocationSettingsRequest;", "settingsClient", "Lcom/google/android/gms/location/SettingsClient;", "updateIntervalMilliseconds", "buildLocationSettingsRequest", "", "canStreamLocation", "", "checkIfHighAccuracyOn", "createLocationCallback", "createLocationRequest", "getLocation", "isAirplaneModeOn", "isGPSEnabled", "showGPSAlert", "requestGPSPermission", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "title", "", "message", "buttonText", "stopLocation", "cancel", "streamLocation", "onGpsListener", "Lcom/micromerger/ssms/camerax/utils/OnGpsListener;", "turnOnGPS", "Companion", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class DeviceLocation {
    public static final int goodAccuracy = 30;
    public static final int oneSecond = 1000;
    public static final int waitTimeForCameraLocationCallback = 2500;
    public static final int waitTimeForLocationCallback = 15000;
    public static final int weiredAccuracy = 50;
    private Activity act;
    private Context ctx;
    private final float distanceFilter;
    private final long fastestUpdateIntervalMilliseconds;
    private FusedLocationProviderClient fusedLocationClient;
    private final int locationAccuracy;
    private LocationCallback locationCallback;
    private LocationManager locationManager;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingRequest;
    private PostLocation postLocation;
    private SettingsClient settingsClient;
    private final long updateIntervalMilliseconds;

    public DeviceLocation() {
        this.updateIntervalMilliseconds = 2000L;
        this.fastestUpdateIntervalMilliseconds = 2000 / 2;
        this.locationAccuracy = 100;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceLocation(Context context) {
        this();
        Intrinsics.checkNotNullParameter(context, "context");
        this.ctx = context;
        Object systemService = context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.locationManager = (LocationManager) systemService;
        this.settingsClient = LocationServices.getSettingsClient(context);
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        createLocationCallback();
        createLocationRequest();
        buildLocationSettingsRequest();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceLocation(Activity activity, Context context, PostLocation postLocation) {
        this();
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(context, "context");
        this.act = activity;
        this.ctx = context;
        this.postLocation = postLocation;
        Object systemService = context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.locationManager = (LocationManager) systemService;
        this.settingsClient = LocationServices.getSettingsClient(context);
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        createLocationCallback();
        createLocationRequest();
        buildLocationSettingsRequest();
    }

    private final boolean checkIfHighAccuracyOn(Context context) {
        Integer numValueOf;
        try {
            numValueOf = Integer.valueOf(Settings.Secure.getInt(context.getContentResolver(), "location_mode"));
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            numValueOf = null;
        }
        return (numValueOf == null || numValueOf.intValue() != 0) && numValueOf != null && numValueOf.intValue() == 3;
    }

    private final void createLocationCallback() {
        if (!CommonActions.isConnected(this.ctx) && isAirplaneModeOn(this.ctx)) {
            PostLocation postLocation = this.postLocation;
            if (postLocation != null) {
                Context context = this.ctx;
                postLocation.message(context == null ? null : context.getString(R.string.turn_off_airplane_mode));
            }
        } else {
            PostLocation postLocation2 = this.postLocation;
            if (postLocation2 != null) {
                postLocation2.message("");
            }
        }
        LocationCallback locationCallback = this.locationCallback;
        if (locationCallback != null) {
            FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
            if (fusedLocationProviderClient != null) {
                fusedLocationProviderClient.removeLocationUpdates(locationCallback);
            }
            this.locationCallback = null;
        }
        this.locationCallback = new LocationCallback() { // from class: com.micromerger.ssms.camerax.utils.DeviceLocation.createLocationCallback.2
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(LocationResult p0) throws Resources.NotFoundException {
                Resources resources;
                Resources resources2;
                Intrinsics.checkNotNullParameter(p0, "p0");
                super.onLocationResult(p0);
                Location lastLocation = p0.getLastLocation();
                Intrinsics.checkNotNullExpressionValue(lastLocation, "p0.lastLocation");
                PostLocation postLocation3 = DeviceLocation.this.postLocation;
                if (postLocation3 != null) {
                    postLocation3.locationCallback(lastLocation);
                }
                String string = null;
                if (lastLocation.getAccuracy() <= 30.0f) {
                    PostLocation postLocation4 = DeviceLocation.this.postLocation;
                    if (postLocation4 == null) {
                        return;
                    }
                    postLocation4.message(null);
                    return;
                }
                if (lastLocation.getAccuracy() <= 30.0f || lastLocation.getAccuracy() > 50.0f) {
                    PostLocation postLocation5 = DeviceLocation.this.postLocation;
                    if (postLocation5 == null) {
                        return;
                    }
                    Context context2 = DeviceLocation.this.ctx;
                    if (context2 != null && (resources = context2.getResources()) != null) {
                        string = resources.getString(R.string.bad_accuracy);
                    }
                    postLocation5.message(string);
                    return;
                }
                PostLocation postLocation6 = DeviceLocation.this.postLocation;
                if (postLocation6 == null) {
                    return;
                }
                Context context3 = DeviceLocation.this.ctx;
                if (context3 != null && (resources2 = context3.getResources()) != null) {
                    string = resources2.getString(R.string.weired_accuracy);
                }
                postLocation6.message(string);
            }

            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationAvailability(LocationAvailability p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                super.onLocationAvailability(p0);
                if (p0.isLocationAvailable()) {
                    return;
                }
                PostLocation postLocation3 = DeviceLocation.this.postLocation;
                if (postLocation3 != null) {
                    postLocation3.locationCallback(null);
                }
                if (!CommonActions.isConnected(DeviceLocation.this.ctx)) {
                    DeviceLocation deviceLocation = DeviceLocation.this;
                    if (deviceLocation.isAirplaneModeOn(deviceLocation.ctx)) {
                        PostLocation postLocation4 = DeviceLocation.this.postLocation;
                        if (postLocation4 == null) {
                            return;
                        }
                        Context context2 = DeviceLocation.this.ctx;
                        postLocation4.message(context2 != null ? context2.getString(R.string.turn_off_airplane_mode) : null);
                        return;
                    }
                }
                PostLocation postLocation5 = DeviceLocation.this.postLocation;
                if (postLocation5 == null) {
                    return;
                }
                postLocation5.message("New Location is temporarily Not Available, Trying to Fetch...");
            }
        };
    }

    private final void createLocationRequest() {
        LocationRequest locationRequestCreate = LocationRequest.create();
        this.locationRequest = locationRequestCreate;
        if (locationRequestCreate != null) {
            locationRequestCreate.setInterval(this.updateIntervalMilliseconds);
        }
        LocationRequest locationRequest = this.locationRequest;
        if (locationRequest != null) {
            locationRequest.setFastestInterval(this.fastestUpdateIntervalMilliseconds);
        }
        LocationRequest locationRequest2 = this.locationRequest;
        if (locationRequest2 != null) {
            locationRequest2.setPriority(this.locationAccuracy);
        }
        LocationRequest locationRequest3 = this.locationRequest;
        if (locationRequest3 == null) {
            return;
        }
        locationRequest3.setSmallestDisplacement(this.distanceFilter);
    }

    private final void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        LocationRequest locationRequest = this.locationRequest;
        if (locationRequest == null) {
            return;
        }
        builder.addLocationRequest(locationRequest);
        this.locationSettingRequest = builder.build();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, android.os.CountDownTimer] */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.micromerger.ssms.camerax.utils.DeviceLocation$getLocation$1] */
    public final void getLocation(final PostLocation postLocation) {
        Task<LocationAvailability> locationAvailability;
        Task<LocationAvailability> taskAddOnSuccessListener;
        Intrinsics.checkNotNullParameter(postLocation, "postLocation");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new CountDownTimer(2500L, 1000L) { // from class: com.micromerger.ssms.camerax.utils.DeviceLocation.getLocation.1
            @Override // android.os.CountDownTimer
            public void onTick(long p0) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                CountDownTimer countDownTimer = objectRef.element;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                postLocation.locationCallback(null);
                FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
                if (fusedLocationProviderClient == null) {
                    return;
                }
                LocationCallback locationCallback = this.locationCallback;
                Intrinsics.checkNotNull(locationCallback);
                fusedLocationProviderClient.removeLocationUpdates(locationCallback);
            }
        }.start();
        this.locationCallback = new LocationCallback() { // from class: com.micromerger.ssms.camerax.utils.DeviceLocation.getLocation.2
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(LocationResult locationResult) {
                Intrinsics.checkNotNullParameter(locationResult, "locationResult");
                postLocation.locationCallback(locationResult.getLastLocation());
                FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
                if (fusedLocationProviderClient != null) {
                    LocationCallback locationCallback = this.locationCallback;
                    Intrinsics.checkNotNull(locationCallback);
                    fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                }
                objectRef.element.cancel();
            }

            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationAvailability(LocationAvailability p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                Log.d(CodePackage.LOCATION, Intrinsics.stringPlus("getLocation onLocationAvailability: ", Boolean.valueOf(p0.isLocationAvailable())));
                if (p0.isLocationAvailable()) {
                    return;
                }
                objectRef.element.cancel();
                postLocation.locationCallback(null);
                FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
                if (fusedLocationProviderClient == null) {
                    return;
                }
                LocationCallback locationCallback = this.locationCallback;
                Intrinsics.checkNotNull(locationCallback);
                fusedLocationProviderClient.removeLocationUpdates(locationCallback);
            }
        };
        this.locationRequest = LocationRequest.create();
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        LocationRequest locationRequest = this.locationRequest;
        Intrinsics.checkNotNull(locationRequest);
        LocationSettingsRequest.Builder builderAddLocationRequest = builder.addLocationRequest(locationRequest);
        Intrinsics.checkNotNullExpressionValue(builderAddLocationRequest, "Builder()\n            .a…equest(locationRequest!!)");
        this.locationSettingRequest = builderAddLocationRequest.build();
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
        if (fusedLocationProviderClient == null || (locationAvailability = fusedLocationProviderClient.getLocationAvailability()) == null || (taskAddOnSuccessListener = locationAvailability.addOnSuccessListener(new OnSuccessListener() { // from class: com.micromerger.ssms.camerax.utils.-$$Lambda$DeviceLocation$0bOgMNeQt8ajbSwyYZ20DrdTQ0w
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                DeviceLocation.m52getLocation$lambda2(this.f$0, objectRef, postLocation, (LocationAvailability) obj);
            }
        })) == null) {
            return;
        }
        taskAddOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: com.micromerger.ssms.camerax.utils.-$$Lambda$DeviceLocation$qYUoPvDbF8D-ohuS9TM6XK-J6Ww
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                DeviceLocation.m53getLocation$lambda3(objectRef, postLocation, this, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getLocation$lambda-2, reason: not valid java name */
    public static final void m52getLocation$lambda2(DeviceLocation this$0, Ref.ObjectRef countDownTimer, PostLocation postLocation, LocationAvailability locationAvailability) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(countDownTimer, "$countDownTimer");
        Intrinsics.checkNotNullParameter(postLocation, "$postLocation");
        Log.d("locationAvailability", Intrinsics.stringPlus("getLocation: isLocationAvailable ", Boolean.valueOf(locationAvailability.isLocationAvailable())));
        if (locationAvailability.isLocationAvailable()) {
            FusedLocationProviderClient fusedLocationProviderClient = this$0.fusedLocationClient;
            if (fusedLocationProviderClient == null) {
                return;
            }
            LocationRequest locationRequest = this$0.locationRequest;
            Intrinsics.checkNotNull(locationRequest);
            LocationCallback locationCallback = this$0.locationCallback;
            Intrinsics.checkNotNull(locationCallback);
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
            return;
        }
        CountDownTimer countDownTimer2 = (CountDownTimer) countDownTimer.element;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        postLocation.locationCallback(null);
        FusedLocationProviderClient fusedLocationProviderClient2 = this$0.fusedLocationClient;
        if (fusedLocationProviderClient2 == null) {
            return;
        }
        LocationCallback locationCallback2 = this$0.locationCallback;
        Intrinsics.checkNotNull(locationCallback2);
        fusedLocationProviderClient2.removeLocationUpdates(locationCallback2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getLocation$lambda-3, reason: not valid java name */
    public static final void m53getLocation$lambda3(Ref.ObjectRef countDownTimer, PostLocation postLocation, DeviceLocation this$0, Exception it) {
        Intrinsics.checkNotNullParameter(countDownTimer, "$countDownTimer");
        Intrinsics.checkNotNullParameter(postLocation, "$postLocation");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        CountDownTimer countDownTimer2 = (CountDownTimer) countDownTimer.element;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        postLocation.locationCallback(null);
        FusedLocationProviderClient fusedLocationProviderClient = this$0.fusedLocationClient;
        if (fusedLocationProviderClient != null) {
            LocationCallback locationCallback = this$0.locationCallback;
            Intrinsics.checkNotNull(locationCallback);
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
        Log.e("locationAvailability", "getLocation: ", it);
    }

    public final void streamLocation(final OnGpsListener onGpsListener) {
        Activity activity;
        SettingsClient settingsClient;
        Task<LocationSettingsResponse> taskCheckLocationSettings;
        Task<LocationSettingsResponse> taskAddOnSuccessListener;
        Task<LocationAvailability> locationAvailability;
        Task<LocationAvailability> taskAddOnSuccessListener2;
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
        if (fusedLocationProviderClient != null && (locationAvailability = fusedLocationProviderClient.getLocationAvailability()) != null && (taskAddOnSuccessListener2 = locationAvailability.addOnSuccessListener(new OnSuccessListener() { // from class: com.micromerger.ssms.camerax.utils.-$$Lambda$DeviceLocation$yzYlcQNfJbceK886oy0oI_nOMKo
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                DeviceLocation.m58streamLocation$lambda4(this.f$0, (LocationAvailability) obj);
            }
        })) != null) {
            taskAddOnSuccessListener2.addOnFailureListener(new OnFailureListener() { // from class: com.micromerger.ssms.camerax.utils.-$$Lambda$DeviceLocation$ywmhU8N0WYz8R8ZHw0D4a8bVuts
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    DeviceLocation.m59streamLocation$lambda5(this.f$0, exc);
                }
            });
        }
        LocationSettingsRequest locationSettingsRequest = this.locationSettingRequest;
        if (locationSettingsRequest == null || (activity = this.act) == null || (settingsClient = this.settingsClient) == null || (taskCheckLocationSettings = settingsClient.checkLocationSettings(locationSettingsRequest)) == null || (taskAddOnSuccessListener = taskCheckLocationSettings.addOnSuccessListener(activity, new OnSuccessListener() { // from class: com.micromerger.ssms.camerax.utils.-$$Lambda$DeviceLocation$q6L4jeLYdHXNa_jdN8r01tqZgIc
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                DeviceLocation.m60streamLocation$lambda9$lambda8$lambda6(this.f$0, (LocationSettingsResponse) obj);
            }
        })) == null) {
            return;
        }
        taskAddOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: com.micromerger.ssms.camerax.utils.-$$Lambda$DeviceLocation$oZVk4JV138L7mzg4jYNk8wWJ2w8
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                DeviceLocation.m61streamLocation$lambda9$lambda8$lambda7(onGpsListener, this, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: streamLocation$lambda-4, reason: not valid java name */
    public static final void m58streamLocation$lambda4(DeviceLocation this$0, LocationAvailability locationAvailability) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.d("locationAvailability", Intrinsics.stringPlus("streamLocation: isLocationAvailable ", Boolean.valueOf(locationAvailability.isLocationAvailable())));
        if (locationAvailability.isLocationAvailable()) {
            return;
        }
        PostLocation postLocation = this$0.postLocation;
        if (postLocation != null) {
            postLocation.locationCallback(null);
        }
        FusedLocationProviderClient fusedLocationProviderClient = this$0.fusedLocationClient;
        if (fusedLocationProviderClient == null) {
            return;
        }
        LocationCallback locationCallback = this$0.locationCallback;
        Intrinsics.checkNotNull(locationCallback);
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: streamLocation$lambda-5, reason: not valid java name */
    public static final void m59streamLocation$lambda5(DeviceLocation this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        PostLocation postLocation = this$0.postLocation;
        if (postLocation != null) {
            postLocation.locationCallback(null);
        }
        FusedLocationProviderClient fusedLocationProviderClient = this$0.fusedLocationClient;
        if (fusedLocationProviderClient != null) {
            LocationCallback locationCallback = this$0.locationCallback;
            Intrinsics.checkNotNull(locationCallback);
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
        Log.e("locationAvailability", "streamLocation: ", it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: streamLocation$lambda-9$lambda-8$lambda-6, reason: not valid java name */
    public static final void m60streamLocation$lambda9$lambda8$lambda6(DeviceLocation this$0, LocationSettingsResponse locationSettingsResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FusedLocationProviderClient fusedLocationProviderClient = this$0.fusedLocationClient;
        if (fusedLocationProviderClient == null) {
            return;
        }
        LocationRequest locationRequest = this$0.locationRequest;
        Intrinsics.checkNotNull(locationRequest);
        LocationCallback locationCallback = this$0.locationCallback;
        Intrinsics.checkNotNull(locationCallback);
        Looper looperMyLooper = Looper.myLooper();
        Intrinsics.checkNotNull(looperMyLooper);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, looperMyLooper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: streamLocation$lambda-9$lambda-8$lambda-7, reason: not valid java name */
    public static final void m61streamLocation$lambda9$lambda8$lambda7(OnGpsListener onGpsListener, DeviceLocation this$0, Exception e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(e, "e");
        int statusCode = ((ApiException) e).getStatusCode();
        if (statusCode != 6) {
            if (statusCode != 8502) {
                return;
            }
            Log.e("ContentValues", "Location settings are inadequate, and cannot be fixed here. Fix in Settings.");
            Toast.makeText(this$0.ctx, "Location settings are inadequate, and cannot be fixed here. Fix in Settings.", 1).show();
            if (onGpsListener == null) {
                return;
            }
            onGpsListener.showGPSAlert();
            return;
        }
        try {
            ResolvableApiException resolvableApiException = (ResolvableApiException) e;
            if (onGpsListener == null) {
                return;
            }
            onGpsListener.startResolutionForResult(resolvableApiException);
        } catch (IntentSender.SendIntentException e2) {
            util.logException(e2);
            Log.e("TAG", "PendingIntent unable to execute request.");
        }
    }

    public static /* synthetic */ void stopLocation$default(DeviceLocation deviceLocation, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        deviceLocation.stopLocation(z);
    }

    public final void stopLocation(boolean cancel) {
        try {
            FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
            if (fusedLocationProviderClient != null) {
                LocationCallback locationCallback = this.locationCallback;
                Intrinsics.checkNotNull(locationCallback);
                fusedLocationProviderClient.removeLocationUpdates(locationCallback);
            }
            if (cancel) {
                this.fusedLocationClient = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context == null ? null : context.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    public final boolean isGPSEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 28) {
            Object systemService = context.getSystemService(FirebaseAnalytics.Param.LOCATION);
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
            return ((LocationManager) systemService).isProviderEnabled("gps");
        }
        return checkIfHighAccuracyOn(context);
    }

    public final void turnOnGPS(final OnGpsListener onGpsListener) {
        Task<LocationSettingsResponse> taskAddOnSuccessListener;
        Context context = this.ctx;
        Intrinsics.checkNotNull(context);
        if (isGPSEnabled(context)) {
            if (onGpsListener == null) {
                return;
            }
            onGpsListener.gpsStatus(true);
            return;
        }
        SettingsClient settingsClient = this.settingsClient;
        if (settingsClient == null) {
            return;
        }
        LocationSettingsRequest locationSettingsRequest = this.locationSettingRequest;
        Intrinsics.checkNotNull(locationSettingsRequest);
        Task<LocationSettingsResponse> taskCheckLocationSettings = settingsClient.checkLocationSettings(locationSettingsRequest);
        if (taskCheckLocationSettings == null || (taskAddOnSuccessListener = taskCheckLocationSettings.addOnSuccessListener(new OnSuccessListener() { // from class: com.micromerger.ssms.camerax.utils.-$$Lambda$DeviceLocation$nEwpk39nI2DT-CprGloBHe-vZy0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                DeviceLocation.m62turnOnGPS$lambda10(onGpsListener, (LocationSettingsResponse) obj);
            }
        })) == null) {
            return;
        }
        taskAddOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: com.micromerger.ssms.camerax.utils.-$$Lambda$DeviceLocation$9TVGoIHHl0YXX1msdK8-PmO2Nkk
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                DeviceLocation.m63turnOnGPS$lambda11(onGpsListener, this, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: turnOnGPS$lambda-10, reason: not valid java name */
    public static final void m62turnOnGPS$lambda10(OnGpsListener onGpsListener, LocationSettingsResponse locationSettingsResponse) {
        if (onGpsListener == null) {
            return;
        }
        onGpsListener.gpsStatus(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: turnOnGPS$lambda-11, reason: not valid java name */
    public static final void m63turnOnGPS$lambda11(OnGpsListener onGpsListener, DeviceLocation this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        int statusCode = ((ApiException) it).getStatusCode();
        if (statusCode == 6) {
            try {
                ResolvableApiException resolvableApiException = (ResolvableApiException) it;
                if (onGpsListener == null) {
                    return;
                }
                onGpsListener.startResolutionForResult(resolvableApiException);
                return;
            } catch (IntentSender.SendIntentException unused) {
                Log.e("TAG", "PendingIntent unable to execute request.");
                return;
            }
        }
        if (statusCode != 8502) {
            return;
        }
        Log.e("ContentValues", "Location settings are inadequate, and cannot be fixed here. Fix in Settings.");
        Toast.makeText(this$0.ctx, "Location settings are inadequate, and cannot be fixed here. Fix in Settings.", 1).show();
        if (onGpsListener == null) {
            return;
        }
        onGpsListener.showGPSAlert();
    }

    public final boolean canStreamLocation() {
        if (!CommonActions.isConnected(this.ctx) && isAirplaneModeOn(this.ctx)) {
            PostLocation postLocation = this.postLocation;
            if (postLocation != null) {
                Context context = this.ctx;
                postLocation.message(context == null ? null : context.getString(R.string.turn_off_airplane_mode));
            }
            return false;
        }
        PostLocation postLocation2 = this.postLocation;
        if (postLocation2 != null) {
            postLocation2.message("");
        }
        return true;
    }

    public static /* synthetic */ void showGPSAlert$default(DeviceLocation deviceLocation, Context context, ActivityResultLauncher activityResultLauncher, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str = context.getString(R.string.gps_required);
            Intrinsics.checkNotNullExpressionValue(str, "fun showGPSAlert(\n      …      dialog.show()\n    }");
        }
        String str4 = str;
        if ((i & 8) != 0) {
            str2 = context.getString(R.string.turn_on_gps_for_camera);
            Intrinsics.checkNotNullExpressionValue(str2, "fun showGPSAlert(\n      …      dialog.show()\n    }");
        }
        String str5 = str2;
        if ((i & 16) != 0) {
            str3 = context.getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(str3, "fun showGPSAlert(\n      …      dialog.show()\n    }");
        }
        deviceLocation.showGPSAlert(context, activityResultLauncher, str4, str5, str3);
    }

    public final void showGPSAlert(Context context, final ActivityResultLauncher<Intent> requestGPSPermission, String title, String message, String buttonText) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(requestGPSPermission, "requestGPSPermission");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(buttonText, "buttonText");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(buttonText, new DialogInterface.OnClickListener() { // from class: com.micromerger.ssms.camerax.utils.-$$Lambda$DeviceLocation$G0nx8BvKDL0V2Gga4yNSPdRnhM0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DeviceLocation.m57showGPSAlert$lambda12(requestGPSPermission, dialogInterface, i);
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showGPSAlert$lambda-12, reason: not valid java name */
    public static final void m57showGPSAlert$lambda12(ActivityResultLauncher requestGPSPermission, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(requestGPSPermission, "$requestGPSPermission");
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
        requestGPSPermission.launch(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }
}
