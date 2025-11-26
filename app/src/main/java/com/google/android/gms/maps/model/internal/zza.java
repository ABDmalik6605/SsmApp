package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public interface zza extends IInterface {

    /* renamed from: com.google.android.gms.maps.model.internal.zza$zza, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0059zza extends Binder implements zza {

        /* renamed from: com.google.android.gms.maps.model.internal.zza$zza$zza, reason: collision with other inner class name */
        private static class C0060zza implements zza {
            private IBinder zzrk;

            C0060zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.zzrk;
            }

            @Override // com.google.android.gms.maps.model.internal.zza
            public IObjectWrapper zzJR() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    this.zzrk.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IObjectWrapper.zza.zzcd(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.model.internal.zza
            public IObjectWrapper zze(Bitmap bitmap) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    if (bitmap != null) {
                        parcelObtain.writeInt(1);
                        bitmap.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IObjectWrapper.zza.zzcd(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.model.internal.zza
            public IObjectWrapper zzfa(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    parcelObtain.writeString(str);
                    this.zzrk.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IObjectWrapper.zza.zzcd(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.model.internal.zza
            public IObjectWrapper zzfb(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    parcelObtain.writeString(str);
                    this.zzrk.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IObjectWrapper.zza.zzcd(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.model.internal.zza
            public IObjectWrapper zzfc(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    parcelObtain.writeString(str);
                    this.zzrk.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IObjectWrapper.zza.zzcd(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.model.internal.zza
            public IObjectWrapper zzi(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    parcelObtain.writeFloat(f);
                    this.zzrk.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IObjectWrapper.zza.zzcd(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.model.internal.zza
            public IObjectWrapper zzly(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    parcelObtain.writeInt(i);
                    this.zzrk.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IObjectWrapper.zza.zzcd(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static zza zzei(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof zza)) ? new C0060zza(iBinder) : (zza) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    IObjectWrapper iObjectWrapperZzly = zzly(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iObjectWrapperZzly != null ? iObjectWrapperZzly.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    IObjectWrapper iObjectWrapperZzfa = zzfa(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iObjectWrapperZzfa != null ? iObjectWrapperZzfa.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    IObjectWrapper iObjectWrapperZzfb = zzfb(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iObjectWrapperZzfb != null ? iObjectWrapperZzfb.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    IObjectWrapper iObjectWrapperZzJR = zzJR();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iObjectWrapperZzJR != null ? iObjectWrapperZzJR.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    IObjectWrapper iObjectWrapperZzi = zzi(parcel.readFloat());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iObjectWrapperZzi != null ? iObjectWrapperZzi.asBinder() : null);
                    return true;
                case 6:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    IObjectWrapper iObjectWrapperZze = zze(parcel.readInt() != 0 ? (Bitmap) Bitmap.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iObjectWrapperZze != null ? iObjectWrapperZze.asBinder() : null);
                    return true;
                case 7:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    IObjectWrapper iObjectWrapperZzfc = zzfc(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iObjectWrapperZzfc != null ? iObjectWrapperZzfc.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IObjectWrapper zzJR() throws RemoteException;

    IObjectWrapper zze(Bitmap bitmap) throws RemoteException;

    IObjectWrapper zzfa(String str) throws RemoteException;

    IObjectWrapper zzfb(String str) throws RemoteException;

    IObjectWrapper zzfc(String str) throws RemoteException;

    IObjectWrapper zzi(float f) throws RemoteException;

    IObjectWrapper zzly(int i) throws RemoteException;
}
