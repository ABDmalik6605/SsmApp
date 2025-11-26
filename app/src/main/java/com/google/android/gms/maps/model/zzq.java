package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzq implements Parcelable.Creator<Tile> {
    static void zza(Tile tile, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 2, tile.width);
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 3, tile.height);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, tile.data, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhN, reason: merged with bridge method [inline-methods] */
    public Tile createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        int iZzg = 0;
        byte[] bArrZzt = null;
        int iZzg2 = 0;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            int iZzdc = com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX);
            if (iZzdc == 2) {
                iZzg = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
            } else if (iZzdc == 3) {
                iZzg2 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
            } else if (iZzdc != 4) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                bArrZzt = com.google.android.gms.common.internal.safeparcel.zzb.zzt(parcel, iZzaX);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new Tile(iZzg, iZzg2, bArrZzt);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlv, reason: merged with bridge method [inline-methods] */
    public Tile[] newArray(int i) {
        return new Tile[i];
    }
}
