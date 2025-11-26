package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;

/* loaded from: classes.dex */
public final class LatLngBounds extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new zze();
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        private double zzbpw = Double.POSITIVE_INFINITY;
        private double zzbpx = Double.NEGATIVE_INFINITY;
        private double zzbpy = Double.NaN;
        private double zzbpz = Double.NaN;

        private boolean zzi(double d) {
            double d2 = this.zzbpy;
            double d3 = this.zzbpz;
            return d2 <= d3 ? d2 <= d && d <= d3 : d2 <= d || d <= d3;
        }

        public LatLngBounds build() {
            zzac.zza(!Double.isNaN(this.zzbpy), "no included points");
            return new LatLngBounds(new LatLng(this.zzbpw, this.zzbpy), new LatLng(this.zzbpx, this.zzbpz));
        }

        public Builder include(LatLng latLng) {
            this.zzbpw = Math.min(this.zzbpw, latLng.latitude);
            this.zzbpx = Math.max(this.zzbpx, latLng.latitude);
            double d = latLng.longitude;
            if (!Double.isNaN(this.zzbpy)) {
                if (!zzi(d)) {
                    if (LatLngBounds.zzb(this.zzbpy, d) < LatLngBounds.zzc(this.zzbpz, d)) {
                        this.zzbpy = d;
                    }
                }
                return this;
            }
            this.zzbpy = d;
            this.zzbpz = d;
            return this;
        }
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        zzac.zzb(latLng, "null southwest");
        zzac.zzb(latLng2, "null northeast");
        zzac.zzb(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", new Object[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude)});
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static LatLngBounds createFromAttributes(Context context, AttributeSet attributeSet) {
        if (context != null && attributeSet != null) {
            TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
            Float fValueOf = typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsSouthWestLatitude) ? Float.valueOf(typedArrayObtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsSouthWestLatitude, 0.0f)) : null;
            Float fValueOf2 = typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsSouthWestLongitude) ? Float.valueOf(typedArrayObtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsSouthWestLongitude, 0.0f)) : null;
            Float fValueOf3 = typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsNorthEastLatitude) ? Float.valueOf(typedArrayObtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsNorthEastLatitude, 0.0f)) : null;
            Float fValueOf4 = typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsNorthEastLongitude) ? Float.valueOf(typedArrayObtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsNorthEastLongitude, 0.0f)) : null;
            if (fValueOf != null && fValueOf2 != null && fValueOf3 != null && fValueOf4 != null) {
                return new LatLngBounds(new LatLng(fValueOf.floatValue(), fValueOf2.floatValue()), new LatLng(fValueOf3.floatValue(), fValueOf4.floatValue()));
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double zzb(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double zzc(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    private boolean zzh(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    private boolean zzi(double d) {
        double d2 = this.southwest.longitude;
        double d3 = this.northeast.longitude;
        double d4 = this.southwest.longitude;
        return d2 <= d3 ? d4 <= d && d <= this.northeast.longitude : d4 <= d || d <= this.northeast.longitude;
    }

    public boolean contains(LatLng latLng) {
        return zzh(latLng.latitude) && zzi(latLng.longitude);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public LatLng getCenter() {
        double d = (this.southwest.latitude + this.northeast.latitude) / 2.0d;
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        if (d3 > d2) {
            d2 += 360.0d;
        }
        return new LatLng(d, (d2 + d3) / 2.0d);
    }

    public int hashCode() {
        return zzaa.hashCode(new Object[]{this.southwest, this.northeast});
    }

    public LatLngBounds including(LatLng latLng) {
        double dMin = Math.min(this.southwest.latitude, latLng.latitude);
        double dMax = Math.max(this.northeast.latitude, latLng.latitude);
        double d = this.northeast.longitude;
        double d2 = this.southwest.longitude;
        double d3 = latLng.longitude;
        if (!zzi(d3)) {
            if (zzb(d2, d3) < zzc(d, d3)) {
                d2 = d3;
            } else {
                d = d3;
            }
        }
        return new LatLngBounds(new LatLng(dMin, d2), new LatLng(dMax, d));
    }

    public String toString() {
        return zzaa.zzv(this).zzg("southwest", this.southwest).zzg("northeast", this.northeast).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }
}
