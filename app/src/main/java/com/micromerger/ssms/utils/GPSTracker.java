package com.micromerger.ssms.utils;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.micromerger.ssms.utils.widgets.DialogCustom;

/* loaded from: classes2.dex */
public class GPSTracker extends Service implements LocationListener {
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 60000;
    double latitude;
    Location location;
    protected LocationManager locationManager;
    double longitude;
    private final Context mContext;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    @Override // android.app.Service
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String provider) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String provider) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation(context);
    }

    public Location getLocation(Context context) {
        try {
            LocationManager locationManager = (LocationManager) this.mContext.getSystemService(FirebaseAnalytics.Param.LOCATION);
            this.locationManager = locationManager;
            this.isGPSEnabled = locationManager.isProviderEnabled("gps");
            boolean zIsProviderEnabled = this.locationManager.isProviderEnabled("network");
            this.isNetworkEnabled = zIsProviderEnabled;
            if (!this.isGPSEnabled && !zIsProviderEnabled) {
                Log.d("network", "no network");
            } else if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                Log.d("permission", "No");
            } else {
                this.canGetLocation = true;
                if (this.isNetworkEnabled) {
                    this.locationManager.requestLocationUpdates("network", 60000L, 10.0f, this);
                    Log.d("Network", "Network");
                    LocationManager locationManager2 = this.locationManager;
                    if (locationManager2 != null) {
                        Location lastKnownLocation = locationManager2.getLastKnownLocation("network");
                        this.location = lastKnownLocation;
                        if (lastKnownLocation != null) {
                            this.latitude = lastKnownLocation.getLatitude();
                            this.longitude = this.location.getLongitude();
                        }
                    }
                }
                if (this.isGPSEnabled && this.location == null) {
                    this.locationManager.requestLocationUpdates("gps", 60000L, 10.0f, this);
                    Log.d("GPS Enabled", "GPS Enabled");
                    LocationManager locationManager3 = this.locationManager;
                    if (locationManager3 != null) {
                        Location lastKnownLocation2 = locationManager3.getLastKnownLocation("gps");
                        this.location = lastKnownLocation2;
                        if (lastKnownLocation2 != null) {
                            this.latitude = lastKnownLocation2.getLatitude();
                            this.longitude = this.location.getLongitude();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Location location = this.location;
        if (location != null) {
            if (Utils.isMockSettingsON(context, location)) {
                CommonObjects.isMock = true;
                DialogCustom.showError(context, "SSMS does not allow fake GPS location. Please turn off your app that generates fake GPS location");
            } else {
                CommonObjects.isMock = false;
            }
        }
        return this.location;
    }

    public void stopUsingGPS() {
        LocationManager locationManager = this.locationManager;
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    public double getLatitude() {
        Location location = this.location;
        if (location != null) {
            this.latitude = location.getLatitude();
        }
        return this.latitude;
    }

    public double getLongitude() {
        Location location = this.location;
        if (location != null) {
            this.longitude = location.getLongitude();
        }
        return this.longitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle("GPS Settings");
        builder.setMessage("GPS is not enabled. Do you want to go to settings menu?");
        builder.setPositiveButton(Constant.Settings, new DialogInterface.OnClickListener() { // from class: com.micromerger.ssms.utils.GPSTracker.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                GPSTracker.this.mContext.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.micromerger.ssms.utils.GPSTracker.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        this.location = location;
    }
}
