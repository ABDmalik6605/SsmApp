package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzd implements Parcelable.Creator<GroundOverlayOptions> {
    static void zza(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, groundOverlayOptions.zzJK(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, groundOverlayOptions.getLocation(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, groundOverlayOptions.getWidth());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 5, groundOverlayOptions.getHeight());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 6, groundOverlayOptions.getBounds(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 7, groundOverlayOptions.getBearing());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 8, groundOverlayOptions.getZIndex());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 9, groundOverlayOptions.isVisible());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 10, groundOverlayOptions.getTransparency());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 11, groundOverlayOptions.getAnchorU());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 12, groundOverlayOptions.getAnchorV());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 13, groundOverlayOptions.isClickable());
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhA, reason: merged with bridge method [inline-methods] */
    public GroundOverlayOptions createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        IBinder iBinderZzr = null;
        LatLng latLng = null;
        LatLngBounds latLngBounds = null;
        float fZzl = 0.0f;
        float fZzl2 = 0.0f;
        float fZzl3 = 0.0f;
        float fZzl4 = 0.0f;
        boolean zZzc = false;
        float fZzl5 = 0.0f;
        float fZzl6 = 0.0f;
        float fZzl7 = 0.0f;
        boolean zZzc2 = false;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX)) {
                case 2:
                    iBinderZzr = com.google.android.gms.common.internal.safeparcel.zzb.zzr(parcel, iZzaX);
                    break;
                case 3:
                    latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
                    break;
                case 4:
                    fZzl = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 5:
                    fZzl2 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLngBounds.CREATOR);
                    break;
                case 7:
                    fZzl3 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 8:
                    fZzl4 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 9:
                    zZzc = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX);
                    break;
                case 10:
                    fZzl5 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 11:
                    fZzl6 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 12:
                    fZzl7 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 13:
                    zZzc2 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new GroundOverlayOptions(iBinderZzr, latLng, fZzl, fZzl2, latLngBounds, fZzl3, fZzl4, zZzc, fZzl5, fZzl6, fZzl7, zZzc2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzli, reason: merged with bridge method [inline-methods] */
    public GroundOverlayOptions[] newArray(int i) {
        return new GroundOverlayOptions[i];
    }
}
