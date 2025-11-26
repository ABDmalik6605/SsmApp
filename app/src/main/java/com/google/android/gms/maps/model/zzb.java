package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzb implements Parcelable.Creator<Cap> {
    static void zza(Cap cap, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 2, cap.getType());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, cap.zzJI(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, cap.zzJH(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhy, reason: merged with bridge method [inline-methods] */
    public Cap createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        IBinder iBinderZzr = null;
        Float fZzm = null;
        int iZzg = 0;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            int iZzdc = com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX);
            if (iZzdc == 2) {
                iZzg = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
            } else if (iZzdc == 3) {
                iBinderZzr = com.google.android.gms.common.internal.safeparcel.zzb.zzr(parcel, iZzaX);
            } else if (iZzdc != 4) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                fZzm = com.google.android.gms.common.internal.safeparcel.zzb.zzm(parcel, iZzaX);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new Cap(iZzg, iBinderZzr, fZzm);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlg, reason: merged with bridge method [inline-methods] */
    public Cap[] newArray(int i) {
        return new Cap[i];
    }
}
