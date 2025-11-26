package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes.dex */
public class zza implements Parcelable.Creator<GoogleMapOptions> {
    static void zza(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int iZzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, googleMapOptions.zzJo());
        zzc.zza(parcel, 3, googleMapOptions.zzJp());
        zzc.zzc(parcel, 4, googleMapOptions.getMapType());
        zzc.zza(parcel, 5, googleMapOptions.getCamera(), i, false);
        zzc.zza(parcel, 6, googleMapOptions.zzJq());
        zzc.zza(parcel, 7, googleMapOptions.zzJr());
        zzc.zza(parcel, 8, googleMapOptions.zzJs());
        zzc.zza(parcel, 9, googleMapOptions.zzJt());
        zzc.zza(parcel, 10, googleMapOptions.zzJu());
        zzc.zza(parcel, 11, googleMapOptions.zzJv());
        zzc.zza(parcel, 12, googleMapOptions.zzJw());
        zzc.zza(parcel, 14, googleMapOptions.zzJx());
        zzc.zza(parcel, 15, googleMapOptions.zzJy());
        zzc.zza(parcel, 16, googleMapOptions.getMinZoomPreference(), false);
        zzc.zza(parcel, 17, googleMapOptions.getMaxZoomPreference(), false);
        zzc.zza(parcel, 18, googleMapOptions.getLatLngBoundsForCameraTarget(), i, false);
        zzc.zzJ(parcel, iZzaZ);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.common.internal.safeparcel.zzb$zza */
    @Override // android.os.Parcelable.Creator
    /* renamed from: zzhv, reason: merged with bridge method [inline-methods] */
    public GoogleMapOptions createFromParcel(Parcel parcel) throws zzb.zza {
        int iZzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        CameraPosition cameraPosition = null;
        Float fZzm = null;
        Float fZzm2 = null;
        LatLngBounds latLngBounds = null;
        byte bZze = -1;
        byte bZze2 = -1;
        int iZzg = 0;
        byte bZze3 = -1;
        byte bZze4 = -1;
        byte bZze5 = -1;
        byte bZze6 = -1;
        byte bZze7 = -1;
        byte bZze8 = -1;
        byte bZze9 = -1;
        byte bZze10 = -1;
        byte bZze11 = -1;
        while (parcel.dataPosition() < iZzaY) {
            int iZzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(iZzaX)) {
                case 2:
                    bZze = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 3:
                    bZze2 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 4:
                    iZzg = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, iZzaX);
                    break;
                case 5:
                    cameraPosition = (CameraPosition) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, CameraPosition.CREATOR);
                    break;
                case 6:
                    bZze3 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 7:
                    bZze4 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 8:
                    bZze5 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 9:
                    bZze6 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 10:
                    bZze7 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 11:
                    bZze8 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 12:
                    bZze9 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 13:
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, iZzaX);
                    break;
                case 14:
                    bZze10 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 15:
                    bZze11 = com.google.android.gms.common.internal.safeparcel.zzb.zze(parcel, iZzaX);
                    break;
                case 16:
                    fZzm = com.google.android.gms.common.internal.safeparcel.zzb.zzm(parcel, iZzaX);
                    break;
                case 17:
                    fZzm2 = com.google.android.gms.common.internal.safeparcel.zzb.zzm(parcel, iZzaX);
                    break;
                case 18:
                    latLngBounds = (LatLngBounds) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, iZzaX, LatLngBounds.CREATOR);
                    break;
            }
        }
        if (parcel.dataPosition() == iZzaY) {
            return new GoogleMapOptions(bZze, bZze2, iZzg, cameraPosition, bZze3, bZze4, bZze5, bZze6, bZze7, bZze8, bZze9, bZze10, bZze11, fZzm, fZzm2, latLngBounds);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iZzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzld, reason: merged with bridge method [inline-methods] */
    public GoogleMapOptions[] newArray(int i) {
        return new GoogleMapOptions[i];
    }
}
