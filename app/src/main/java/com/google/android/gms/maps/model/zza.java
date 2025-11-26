package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zza implements Parcelable.Creator<CameraPosition> {
    static void zza(CameraPosition cameraPosition, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, cameraPosition.target, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, cameraPosition.zoom);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, cameraPosition.tilt);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 5, cameraPosition.bearing);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhx, reason: merged with bridge method [inline-methods] */
    public CameraPosition createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        float fZzl = 0.0f;
        LatLng latLng = null;
        float fZzl2 = 0.0f;
        float fZzl3 = 0.0f;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            int iZzdc = com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX);
            if (iZzdc == 2) {
                latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
            } else if (iZzdc == 3) {
                fZzl = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
            } else if (iZzdc == 4) {
                fZzl2 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
            } else if (iZzdc != 5) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                fZzl3 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new CameraPosition(latLng, fZzl, fZzl2, fZzl3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlf, reason: merged with bridge method [inline-methods] */
    public CameraPosition[] newArray(int i) {
        return new CameraPosition[i];
    }
}
