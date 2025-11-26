package com.google.android.gms.internal.safetynet;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
public final class zzh extends zza implements IInterface {
    zzh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.safetynet.internal.ISafetyNetService");
    }

    public final void zzc(zzg zzgVar, byte[] bArr, String str) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, zzgVar);
        parcelZza.writeByteArray(bArr);
        parcelZza.writeString(str);
        zzb(7, parcelZza);
    }

    public final void zzd(zzg zzgVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, zzgVar);
        zzb(4, parcelZza);
    }

    public final void zze(zzg zzgVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, zzgVar);
        zzb(12, parcelZza);
    }

    public final void zzf(zzg zzgVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, zzgVar);
        zzb(14, parcelZza);
    }

    public final void zzg(zzg zzgVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, zzgVar);
        zzb(5, parcelZza);
    }

    public final void zzh(zzg zzgVar, String str, int[] iArr, int i, String str2) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, zzgVar);
        parcelZza.writeString(str);
        parcelZza.writeIntArray(iArr);
        parcelZza.writeInt(i);
        parcelZza.writeString(str2);
        zzb(3, parcelZza);
    }

    public final void zzi() throws RemoteException {
        zzb(13, zza());
    }

    public final void zzj(zzg zzgVar, String str) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, zzgVar);
        parcelZza.writeString(str);
        zzb(6, parcelZza);
    }
}
