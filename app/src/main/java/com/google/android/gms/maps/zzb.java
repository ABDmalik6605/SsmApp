package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* loaded from: classes.dex */
public class zzb implements Parcelable.Creator<StreetViewPanoramaOptions> {
    static void zza(StreetViewPanoramaOptions streetViewPanoramaOptions, Parcel parcel, int i) {
        int iZzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, streetViewPanoramaOptions.getStreetViewPanoramaCamera(), i, false);
        zzc.zza(parcel, 3, streetViewPanoramaOptions.getPanoramaId(), false);
        zzc.zza(parcel, 4, streetViewPanoramaOptions.getPosition(), i, false);
        zzc.zza(parcel, 5, streetViewPanoramaOptions.getRadius(), false);
        zzc.zza(parcel, 6, streetViewPanoramaOptions.zzJB());
        zzc.zza(parcel, 7, streetViewPanoramaOptions.zzJt());
        zzc.zza(parcel, 8, streetViewPanoramaOptions.zzJC());
        zzc.zza(parcel, 9, streetViewPanoramaOptions.zzJD());
        zzc.zza(parcel, 10, streetViewPanoramaOptions.zzJp());
        zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhw, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaOptions createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        String strZzq = null;
        LatLng latLng = null;
        Integer numZzh = null;
        byte bZze = 0;
        byte bZze2 = 0;
        byte bZze3 = 0;
        byte bZze4 = 0;
        byte bZze5 = 0;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX)) {
                case 2:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, StreetViewPanoramaCamera.CREATOR);
                    break;
                case 3:
                    strZzq = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, iZzaX);
                    break;
                case 4:
                    latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLng.CREATOR);
                    break;
                case 5:
                    numZzh = com.google.android.gms.common.internal.safeparcel.zzb.zzh(parcel, iZzaX);
                    break;
                case 6:
                    bZze = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 7:
                    bZze2 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 8:
                    bZze3 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 9:
                    bZze4 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 10:
                    bZze5 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new StreetViewPanoramaOptions(streetViewPanoramaCamera, strZzq, latLng, numZzh, bZze, bZze2, bZze3, bZze4, bZze5);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzle, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaOptions[] newArray(int i) {
        return new StreetViewPanoramaOptions[i];
    }
}
