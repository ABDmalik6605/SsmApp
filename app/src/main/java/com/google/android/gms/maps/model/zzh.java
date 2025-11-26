package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzh implements Parcelable.Creator<MarkerOptions> {
    static void zza(MarkerOptions markerOptions, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, markerOptions.getPosition(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, markerOptions.getTitle(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, markerOptions.getSnippet(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 5, markerOptions.zzJM(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 6, markerOptions.getAnchorU());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 7, markerOptions.getAnchorV());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 8, markerOptions.isDraggable());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 9, markerOptions.isVisible());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 10, markerOptions.isFlat());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 11, markerOptions.getRotation());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 12, markerOptions.getInfoWindowAnchorU());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 13, markerOptions.getInfoWindowAnchorV());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 14, markerOptions.getAlpha());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 15, markerOptions.getZIndex());
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhE, reason: merged with bridge method [inline-methods] */
    public MarkerOptions createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        LatLng latLng = null;
        String strZzq = null;
        String strZzq2 = null;
        IBinder iBinderZzr = null;
        float fZzl = 0.0f;
        float fZzl2 = 0.0f;
        boolean zZzc = false;
        boolean zZzc2 = false;
        boolean zZzc3 = false;
        float fZzl3 = 0.0f;
        float fZzl4 = 0.5f;
        float fZzl5 = 0.0f;
        float fZzl6 = 1.0f;
        float fZzl7 = 0.0f;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX)) {
                case 2:
                    latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
                    break;
                case 3:
                    strZzq = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, iZzaX);
                    break;
                case 4:
                    strZzq2 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, iZzaX);
                    break;
                case 5:
                    iBinderZzr = com.google.android.gms.common.internal.safeparcel.zzb.zzr(parcel, iZzaX);
                    break;
                case 6:
                    fZzl = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
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
                    zZzc3 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX);
                    break;
                case 11:
                    fZzl3 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 12:
                    fZzl4 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 13:
                    fZzl5 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 14:
                    fZzl6 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 15:
                    fZzl7 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new MarkerOptions(latLng, strZzq, strZzq2, iBinderZzr, fZzl, fZzl2, zZzc, zZzc2, zZzc3, fZzl3, fZzl4, fZzl5, fZzl6, fZzl7);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlm, reason: merged with bridge method [inline-methods] */
    public MarkerOptions[] newArray(int i) {
        return new MarkerOptions[i];
    }
}
