package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzs implements Parcelable.Creator<VisibleRegion> {
    static void zza(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, visibleRegion.nearLeft, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, visibleRegion.nearRight, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, visibleRegion.farLeft, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 5, visibleRegion.farRight, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 6, visibleRegion.latLngBounds, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhP, reason: merged with bridge method [inline-methods] */
    public VisibleRegion createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        LatLngBounds latLngBounds = null;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            int iZzdc = com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX);
            if (iZzdc == 2) {
                latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
            } else if (iZzdc == 3) {
                latLng2 = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
            } else if (iZzdc == 4) {
                latLng3 = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
            } else if (iZzdc == 5) {
                latLng4 = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
            } else if (iZzdc != 6) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                latLngBounds = (LatLngBounds) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLngBounds.CREATOR);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new VisibleRegion(latLng, latLng2, latLng3, latLng4, latLngBounds);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlx, reason: merged with bridge method [inline-methods] */
    public VisibleRegion[] newArray(int i) {
        return new VisibleRegion[i];
    }
}
