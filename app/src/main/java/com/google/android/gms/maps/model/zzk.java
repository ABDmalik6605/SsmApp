package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class zzk implements Parcelable.Creator<PolygonOptions> {
    static void zza(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int iZzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 2, polygonOptions.getPoints(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzd(parcel, 3, polygonOptions.zzJP(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, polygonOptions.getStrokeWidth());
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 5, polygonOptions.getStrokeColor());
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 6, polygonOptions.getFillColor());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 7, polygonOptions.getZIndex());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 8, polygonOptions.isVisible());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 9, polygonOptions.isGeodesic());
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 10, polygonOptions.isClickable());
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 11, polygonOptions.getStrokeJointType());
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 12, polygonOptions.getStrokePattern(), false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhH, reason: merged with bridge method [inline-methods] */
    public PolygonOptions createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayListZzc = null;
        ArrayList arrayListZzc2 = null;
        float fZzl = 0.0f;
        int iZzg = 0;
        int iZzg2 = 0;
        float fZzl2 = 0.0f;
        boolean zZzc = false;
        boolean zZzc2 = false;
        boolean zZzc3 = false;
        int iZzg3 = 0;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX)) {
                case 2:
                    arrayListZzc = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, iZzaX, LatLng.CREATOR);
                    break;
                case 3:
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, arrayList, getClass().getClassLoader());
                    break;
                case 4:
                    fZzl = com.google.android.gms.common.internal.safeparcel.zzb.zzl(parcel, iZzaX);
                    break;
                case 5:
                    iZzg = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
                    break;
                case 6:
                    iZzg2 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
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
                    iZzg3 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
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
            return new PolygonOptions(arrayListZzc, arrayList, fZzl, iZzg, iZzg2, fZzl2, zZzc, zZzc2, zZzc3, iZzg3, arrayListZzc2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzlp, reason: merged with bridge method [inline-methods] */
    public PolygonOptions[] newArray(int i) {
        return new PolygonOptions[i];
    }
}
