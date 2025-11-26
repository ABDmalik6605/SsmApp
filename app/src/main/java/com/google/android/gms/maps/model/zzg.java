package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzg implements Parcelable.Creator<MapStyleOptions> {
    static void zza(MapStyleOptions mapStyleOptions, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, mapStyleOptions.zzJL(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhD, reason: merged with bridge method [inline-methods] */
    public MapStyleOptions createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        String strZzq = null;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            if (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX) != 2) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                strZzq = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, iZzaX);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new MapStyleOptions(strZzq);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzll, reason: merged with bridge method [inline-methods] */
    public MapStyleOptions[] newArray(int i) {
        return new MapStyleOptions[i];
    }
}
