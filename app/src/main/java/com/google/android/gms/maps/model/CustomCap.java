package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.zzac;

/* loaded from: classes.dex */
public final class CustomCap extends Cap {
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    public CustomCap(BitmapDescriptor bitmapDescriptor) {
        this(bitmapDescriptor, 10.0f);
    }

    public CustomCap(BitmapDescriptor bitmapDescriptor, float f) {
        super((BitmapDescriptor) zzac.zzb(bitmapDescriptor, "bitmapDescriptor must not be null"), zza(f, "refWidth must be positive"));
        this.bitmapDescriptor = bitmapDescriptor;
        this.refWidth = f;
    }

    private static float zza(float f, String str) {
        if (f > 0.0f) {
            return f;
        }
        throw new IllegalArgumentException(str);
    }

    @Override // com.google.android.gms.maps.model.Cap
    public String toString() {
        String strValueOf = String.valueOf(this.bitmapDescriptor);
        float f = this.refWidth;
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 55);
        sb.append("[CustomCap: bitmapDescriptor=");
        sb.append(strValueOf);
        sb.append(" refWidth=");
        sb.append(f);
        sb.append("]");
        return sb.toString();
    }
}
