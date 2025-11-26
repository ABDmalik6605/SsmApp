package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzo implements Parcelable.Creator<StreetViewPanoramaLocation> {
    /* JADX WARN: Type inference failed for: r1v0, types: [android.os.Parcelable[], com.google.android.gms.maps.model.StreetViewPanoramaLink[]] */
    static void zza(StreetViewPanoramaLocation streetViewPanoramaLocation, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, (Parcelable[]) streetViewPanoramaLocation.links, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, streetViewPanoramaLocation.position, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, streetViewPanoramaLocation.panoId, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhL, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaLocation createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr = null;
        LatLng latLng = null;
        String strZzq = null;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            int iZzdc = com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX);
            if (iZzdc == 2) {
                streetViewPanoramaLinkArr = (StreetViewPanoramaLink[]) com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX, StreetViewPanoramaLink.CREATOR);
            } else if (iZzdc == 3) {
                latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
            } else if (iZzdc != 4) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                strZzq = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, iZzaX);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new StreetViewPanoramaLocation(streetViewPanoramaLinkArr, latLng, strZzq);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlt, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaLocation[] newArray(int i) {
        return new StreetViewPanoramaLocation[i];
    }
}
