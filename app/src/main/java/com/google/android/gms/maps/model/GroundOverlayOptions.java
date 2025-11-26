package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public final class GroundOverlayOptions extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
    public static final float NO_DIMENSION = -1.0f;
    private LatLngBounds zzbnp;
    private float zzbpd;
    private float zzbpi;
    private boolean zzbpj;
    private boolean zzbpk;
    private BitmapDescriptor zzbpn;
    private LatLng zzbpo;
    private float zzbpp;
    private float zzbpq;
    private float zzbpr;
    private float zzbps;
    private float zzbpt;

    public GroundOverlayOptions() {
        this.zzbpj = true;
        this.zzbpr = 0.0f;
        this.zzbps = 0.5f;
        this.zzbpt = 0.5f;
        this.zzbpk = false;
    }

    GroundOverlayOptions(IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7, boolean z2) {
        this.zzbpj = true;
        this.zzbpr = 0.0f;
        this.zzbps = 0.5f;
        this.zzbpt = 0.5f;
        this.zzbpk = false;
        this.zzbpn = new BitmapDescriptor(IObjectWrapper.zza.zzcd(iBinder));
        this.zzbpo = latLng;
        this.zzbpp = f;
        this.zzbpq = f2;
        this.zzbnp = latLngBounds;
        this.zzbpd = f3;
        this.zzbpi = f4;
        this.zzbpj = z;
        this.zzbpr = f5;
        this.zzbps = f6;
        this.zzbpt = f7;
        this.zzbpk = z2;
    }

    private GroundOverlayOptions zza(LatLng latLng, float f, float f2) {
        this.zzbpo = latLng;
        this.zzbpp = f;
        this.zzbpq = f2;
        return this;
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        this.zzbps = f;
        this.zzbpt = f2;
        return this;
    }

    public GroundOverlayOptions bearing(float f) {
        this.zzbpd = ((f % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public GroundOverlayOptions clickable(boolean z) {
        this.zzbpk = z;
        return this;
    }

    public float getAnchorU() {
        return this.zzbps;
    }

    public float getAnchorV() {
        return this.zzbpt;
    }

    public float getBearing() {
        return this.zzbpd;
    }

    public LatLngBounds getBounds() {
        return this.zzbnp;
    }

    public float getHeight() {
        return this.zzbpq;
    }

    public BitmapDescriptor getImage() {
        return this.zzbpn;
    }

    public LatLng getLocation() {
        return this.zzbpo;
    }

    public float getTransparency() {
        return this.zzbpr;
    }

    public float getWidth() {
        return this.zzbpp;
    }

    public float getZIndex() {
        return this.zzbpi;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        zzac.zzb(bitmapDescriptor, "imageDescriptor must not be null");
        this.zzbpn = bitmapDescriptor;
        return this;
    }

    public boolean isClickable() {
        return this.zzbpk;
    }

    public boolean isVisible() {
        return this.zzbpj;
    }

    public GroundOverlayOptions position(LatLng latLng, float f) {
        zzac.zza(this.zzbnp == null, "Position has already been set using positionFromBounds");
        zzac.zzb(latLng != null, "Location must be specified");
        zzac.zzb(f >= 0.0f, "Width must be non-negative");
        return zza(latLng, f, -1.0f);
    }

    public GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        zzac.zza(this.zzbnp == null, "Position has already been set using positionFromBounds");
        zzac.zzb(latLng != null, "Location must be specified");
        zzac.zzb(f >= 0.0f, "Width must be non-negative");
        zzac.zzb(f2 >= 0.0f, "Height must be non-negative");
        return zza(latLng, f, f2);
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        LatLng latLng = this.zzbpo;
        boolean z = latLng == null;
        String strValueOf = String.valueOf(latLng);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 46);
        sb.append("Position has already been set using position: ");
        sb.append(strValueOf);
        zzac.zza(z, sb.toString());
        this.zzbnp = latLngBounds;
        return this;
    }

    public GroundOverlayOptions transparency(float f) {
        zzac.zzb(f >= 0.0f && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzbpr = f;
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.zzbpj = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }

    public GroundOverlayOptions zIndex(float f) {
        this.zzbpi = f;
        return this;
    }

    IBinder zzJK() {
        return this.zzbpn.zzJm().asBinder();
    }
}
