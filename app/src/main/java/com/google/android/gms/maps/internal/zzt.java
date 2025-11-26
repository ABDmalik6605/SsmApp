package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;

/* loaded from: classes.dex */
public interface zzt extends IInterface {

    public static abstract class zza extends Binder implements zzt {

        /* renamed from: com.google.android.gms.maps.internal.zzt$zza$zza, reason: collision with other inner class name */
        private static class C0051zza implements zzt {
            private IBinder zzrk;

            C0051zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.zzrk;
            }

            @Override // com.google.android.gms.maps.internal.zzt
            public void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMapReadyCallback");
                    parcelObtain.writeStrongBinder(iGoogleMapDelegate != null ? iGoogleMapDelegate.asBinder() : null);
                    this.zzrk.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnMapReadyCallback");
        }

        public static zzt zzdO(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMapReadyCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof zzt)) ? new C0051zza(iBinder) : (zzt) iInterfaceQueryLocalInterface;
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnMapReadyCallback");
                return true;
            }
            parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMapReadyCallback");
            zza(IGoogleMapDelegate.zza.zzdu(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
    }

    void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException;
}
