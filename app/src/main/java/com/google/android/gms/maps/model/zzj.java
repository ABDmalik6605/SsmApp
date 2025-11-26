package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzj implements Parcelable.Creator<PointOfInterest> {
    static void zza(PointOfInterest pointOfInterest, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, pointOfInterest.latLng, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, pointOfInterest.placeId, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, pointOfInterest.name, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhG, reason: merged with bridge method [inline-methods] */
    public PointOfInterest createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        LatLng latLng = null;
        String strZzq = null;
        String strZzq2 = null;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            int iZzdc = com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX);
            if (iZzdc == 2) {
                latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
            } else if (iZzdc == 3) {
                strZzq = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, iZzaX);
            } else if (iZzdc != 4) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                strZzq2 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, iZzaX);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new PointOfInterest(latLng, strZzq, strZzq2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlo, reason: merged with bridge method [inline-methods] */
    public PointOfInterest[] newArray(int i) {
        return new PointOfInterest[i];
    }
}
