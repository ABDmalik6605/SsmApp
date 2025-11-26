package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzp implements Parcelable.Creator<StreetViewPanoramaOrientation> {
    static void zza(StreetViewPanoramaOrientation streetViewPanoramaOrientation, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, streetViewPanoramaOrientation.tilt);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, streetViewPanoramaOrientation.bearing);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhM, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaOrientation createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        float fZzl = 0.0f;
        float fZzl2 = 0.0f;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            int iZzdc = com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX);
            if (iZzdc == 2) {
                fZzl = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
            } else if (iZzdc != 3) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                fZzl2 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new StreetViewPanoramaOrientation(fZzl, fZzl2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlu, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaOrientation[] newArray(int i) {
        return new StreetViewPanoramaOrientation[i];
    }
}
