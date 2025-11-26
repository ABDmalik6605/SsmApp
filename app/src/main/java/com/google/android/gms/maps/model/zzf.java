package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzf implements Parcelable.Creator<LatLng> {
    static void zza(LatLng latLng, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, latLng.latitude);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, latLng.longitude);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhC, reason: merged with bridge method [inline-methods] */
    public LatLng createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        double dZzn = 0.0d;
        double dZzn2 = 0.0d;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            int iZzdc = com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX);
            if (iZzdc == 2) {
                dZzn = com.google.android.gms.common.internal.safeparcel.zzb.zzn(parcel, iZzaX);
            } else if (iZzdc != 3) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                dZzn2 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(parcel, iZzaX);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new LatLng(dZzn, dZzn2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlk, reason: merged with bridge method [inline-methods] */
    public LatLng[] newArray(int i) {
        return new LatLng[i];
    }
}
