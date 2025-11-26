package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.zzf;

/* loaded from: classes.dex */
public interface zzv extends IInterface {

    public static abstract class zza extends Binder implements zzv {

        /* renamed from: com.google.android.gms.maps.internal.zzv$zza$zza, reason: collision with other inner class name */
        private static class C0053zza implements zzv {
            private IBinder zzrk;

            C0053zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.zzrk;
            }

            @Override // com.google.android.gms.maps.internal.zzv
            public void zzb(com.google.android.gms.maps.model.internal.zzf zzfVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                    parcelObtain.writeStrongBinder(zzfVar != null ? zzfVar.asBinder() : null);
                    this.zzrk.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.zzv
            public void zzc(com.google.android.gms.maps.model.internal.zzf zzfVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                    parcelObtain.writeStrongBinder(zzfVar != null ? zzfVar.asBinder() : null);
                    this.zzrk.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.zzv
            public void zzd(com.google.android.gms.maps.model.internal.zzf zzfVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                    parcelObtain.writeStrongBinder(zzfVar != null ? zzfVar.asBinder() : null);
                    this.zzrk.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerDragListener");
        }

        public static zzv zzdQ(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof zzv)) ? new C0053zza(iBinder) : (zzv) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                zzb(zzf.zza.zzen(parcel.readStrongBinder()));
            } else if (i == 2) {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                zzd(zzf.zza.zzen(parcel.readStrongBinder()));
            } else {
                if (i != 3) {
                    if (i != 1598968902) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                    return true;
                }
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                zzc(zzf.zza.zzen(parcel.readStrongBinder()));
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void zzb(com.google.android.gms.maps.model.internal.zzf zzfVar) throws RemoteException;

    void zzc(com.google.android.gms.maps.model.internal.zzf zzfVar) throws RemoteException;

    void zzd(com.google.android.gms.maps.model.internal.zzf zzfVar) throws RemoteException;
}
