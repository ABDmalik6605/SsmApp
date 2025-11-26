package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.zzc;

/* loaded from: classes.dex */
public interface zzk extends IInterface {

    public static abstract class zza extends Binder implements zzk {

        /* renamed from: com.google.android.gms.maps.internal.zzk$zza$zza, reason: collision with other inner class name */
        private static class C0042zza implements zzk {
            private IBinder zzrk;

            C0042zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.zzrk;
            }

            @Override // com.google.android.gms.maps.internal.zzk
            public void zza(com.google.android.gms.maps.model.internal.zzc zzcVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
                    parcelObtain.writeStrongBinder(zzcVar != null ? zzcVar.asBinder() : null);
                    this.zzrk.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
        }

        public static zzk zzdF(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof zzk)) ? new C0042zza(iBinder) : (zzk) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
                return true;
            }
            parcel.enforceInterface("com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
            zza(zzc.zza.zzek(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
    }

    void zza(com.google.android.gms.maps.model.internal.zzc zzcVar) throws RemoteException;
}
