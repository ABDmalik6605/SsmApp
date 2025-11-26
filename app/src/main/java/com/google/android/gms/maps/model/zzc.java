package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class zzc implements Parcelable.Creator<CircleOptions> {
    static void zza(CircleOptions circleOptions, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, circleOptions.getCenter(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, circleOptions.getRadius());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, circleOptions.getStrokeWidth());
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 5, circleOptions.getStrokeColor());
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 6, circleOptions.getFillColor());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 7, circleOptions.getZIndex());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 8, circleOptions.isVisible());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 9, circleOptions.isClickable());
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 10, circleOptions.getStrokePattern(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhz, reason: merged with bridge method [inline-methods] */
    public CircleOptions createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        LatLng latLng = null;
        ArrayList arrayListZzc = null;
        double dZzn = 0.0d;
        float fZzl = 0.0f;
        int iZzg = 0;
        int iZzg2 = 0;
        float fZzl2 = 0.0f;
        boolean zZzc = false;
        boolean zZzc2 = false;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX)) {
                case 2:
                    latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
                    break;
                case 3:
                    dZzn = com.google.android.gms.common.internal.safeparcel.zzb.zzn(parcel, iZzaX);
                    break;
                case 4:
                    fZzl = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 5:
                    iZzg = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
                    break;
                case 6:
                    iZzg2 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
                    break;
                case 7:
                    fZzl2 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 8:
                    zZzc = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX);
                    break;
                case 9:
                    zZzc2 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX);
                    break;
                case 10:
                    arrayListZzc = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX, PatternItem.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new CircleOptions(latLng, dZzn, fZzl, iZzg, iZzg2, fZzl2, zZzc, zZzc2, arrayListZzc);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlh, reason: merged with bridge method [inline-methods] */
    public CircleOptions[] newArray(int i) {
        return new CircleOptions[i];
    }
}
