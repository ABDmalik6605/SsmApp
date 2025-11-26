package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class zzl implements Parcelable.Creator<PolylineOptions> {
    static void zza(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 2, polylineOptions.getPoints(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, polylineOptions.getWidth());
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 4, polylineOptions.getColor());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 5, polylineOptions.getZIndex());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 6, polylineOptions.isVisible());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 7, polylineOptions.isGeodesic());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 8, polylineOptions.isClickable());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 9, polylineOptions.getStartCap(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 10, polylineOptions.getEndCap(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 11, polylineOptions.getJointType());
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 12, polylineOptions.getPattern(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhI, reason: merged with bridge method [inline-methods] */
    public PolylineOptions createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        ArrayList arrayListZzc = null;
        Cap cap = null;
        Cap cap2 = null;
        ArrayList arrayListZzc2 = null;
        float fZzl = 0.0f;
        int iZzg = 0;
        float fZzl2 = 0.0f;
        boolean zZzc = false;
        boolean zZzc2 = false;
        boolean zZzc3 = false;
        int iZzg2 = 0;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX)) {
                case 2:
                    arrayListZzc = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX, LatLng.CREATOR);
                    break;
                case 3:
                    fZzl = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 4:
                    iZzg = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
                    break;
                case 5:
                    fZzl2 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 6:
                    zZzc = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX);
                    break;
                case 7:
                    zZzc2 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX);
                    break;
                case 8:
                    zZzc3 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX);
                    break;
                case 9:
                    cap = (Cap) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, Cap.CREATOR);
                    break;
                case 10:
                    cap2 = (Cap) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, Cap.CREATOR);
                    break;
                case 11:
                    iZzg2 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
                    break;
                case 12:
                    arrayListZzc2 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX, PatternItem.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new PolylineOptions(arrayListZzc, fZzl, iZzg, fZzl2, zZzc, zZzc2, zZzc3, cap, cap2, iZzg2, arrayListZzc2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlq, reason: merged with bridge method [inline-methods] */
    public PolylineOptions[] newArray(int i) {
        return new PolylineOptions[i];
    }
}
