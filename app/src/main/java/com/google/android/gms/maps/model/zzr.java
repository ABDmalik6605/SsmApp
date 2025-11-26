package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzr implements Parcelable.Creator<TileOverlayOptions> {
    static void zza(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, tileOverlayOptions.zzJQ(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, tileOverlayOptions.isVisible());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, tileOverlayOptions.getZIndex());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 5, tileOverlayOptions.getFadeIn());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 6, tileOverlayOptions.getTransparency());
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhO, reason: merged with bridge method [inline-methods] */
    public TileOverlayOptions createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        IBinder iBinderZzr = null;
        boolean zZzc = false;
        float fZzl = 0.0f;
        boolean zZzc2 = true;
        float fZzl2 = 0.0f;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            int iZzdc = com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX);
            if (iZzdc == 2) {
                iBinderZzr = com.google.android.gms.common.internal.safeparcel.zzb.zzr(parcel, iZzaX);
            } else if (iZzdc == 3) {
                zZzc = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX);
            } else if (iZzdc == 4) {
                fZzl = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
            } else if (iZzdc == 5) {
                zZzc2 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX);
            } else if (iZzdc != 6) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                fZzl2 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new TileOverlayOptions(iBinderZzr, zZzc, fZzl, zZzc2, fZzl2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlw, reason: merged with bridge method [inline-methods] */
    public TileOverlayOptions[] newArray(int i) {
        return new TileOverlayOptions[i];
    }
}
