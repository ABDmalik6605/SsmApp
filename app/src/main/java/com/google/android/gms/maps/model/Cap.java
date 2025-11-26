package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public class Cap extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator<Cap> CREATOR = new zzb();
    private static final String TAG = "Cap";
    private final BitmapDescriptor bitmapDescriptor;
    private final int type;
    private final Float zzbpe;

    protected Cap(int i) {
        this(i, (BitmapDescriptor) null, (Float) null);
    }

    Cap(int i, IBinder iBinder, Float f) {
        this(i, zzeh(iBinder), f);
    }

    private Cap(int i, BitmapDescriptor bitmapDescriptor, Float f) {
        boolean z = true;
        boolean z2 = f != null && f.floatValue() > 0.0f;
        if (i == 3 && (bitmapDescriptor == null || !z2)) {
            z = false;
        }
        String strValueOf = String.valueOf(bitmapDescriptor);
        String strValueOf2 = String.valueOf(f);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 63 + String.valueOf(strValueOf2).length());
        sb.append("Invalid Cap: type=");
        sb.append(i);
        sb.append(" bitmapDescriptor=");
        sb.append(strValueOf);
        sb.append(" bitmapRefWidth=");
        sb.append(strValueOf2);
        zzac.zzb(z, sb.toString());
        this.type = i;
        this.bitmapDescriptor = bitmapDescriptor;
        this.zzbpe = f;
    }

    protected Cap(BitmapDescriptor bitmapDescriptor, float f) {
        this(3, bitmapDescriptor, Float.valueOf(f));
    }

    private static BitmapDescriptor zzeh(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        return new BitmapDescriptor(IObjectWrapper.zza.zzcd(iBinder));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.type == cap.type && zzaa.equal(this.bitmapDescriptor, cap.bitmapDescriptor) && zzaa.equal(this.zzbpe, cap.zzbpe);
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return zzaa.hashCode(new Object[]{Integer.valueOf(this.type), this.bitmapDescriptor, this.zzbpe});
    }

    public String toString() {
        int i = this.type;
        StringBuilder sb = new StringBuilder(23);
        sb.append("[Cap: type=");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public Float zzJH() {
        return this.zzbpe;
    }

    IBinder zzJI() {
        BitmapDescriptor bitmapDescriptor = this.bitmapDescriptor;
        if (bitmapDescriptor == null) {
            return null;
        }
        return bitmapDescriptor.zzJm().asBinder();
    }

    Cap zzJJ() {
        int i = this.type;
        if (i == 0) {
            return new ButtCap();
        }
        if (i == 1) {
            return new SquareCap();
        }
        if (i == 2) {
            return new RoundCap();
        }
        if (i == 3) {
            return new CustomCap(this.bitmapDescriptor, this.zzbpe.floatValue());
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder(29);
        sb.append("Unknown Cap type: ");
        sb.append(i);
        Log.w(str, sb.toString());
        return this;
    }
}
