package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;

/* loaded from: classes.dex */
public class zzi implements Parcelable.Creator<PatternItem> {
    static void zza(PatternItem patternItem, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 2, patternItem.getType());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, patternItem.zzJN(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhF, reason: merged with bridge method [inline-methods] */
    public PatternItem createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        int iZzg = 0;
        Float fZzm = null;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            int iZzdc = com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX);
            if (iZzdc == 2) {
                iZzg = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
            } else if (iZzdc != 3) {
                com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
            } else {
                fZzm = com.google.android.gms.common.internal.safeparcel.zzb.zzm(parcel, iZzaX);
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new PatternItem(iZzg, fZzm);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzln, reason: merged with bridge method [inline-methods] */
    public PatternItem[] newArray(int i) {
        return new PatternItem[i];
    }
}
