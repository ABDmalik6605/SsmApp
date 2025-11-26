package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.internal.zzag;
import com.google.android.gms.maps.internal.zzb;
import com.google.android.gms.maps.internal.zzd;
import com.google.android.gms.maps.internal.zze;
import com.google.android.gms.maps.internal.zzf;
import com.google.android.gms.maps.internal.zzg;
import com.google.android.gms.maps.internal.zzh;
import com.google.android.gms.maps.internal.zzi;
import com.google.android.gms.maps.internal.zzj;
import com.google.android.gms.maps.internal.zzk;
import com.google.android.gms.maps.internal.zzl;
import com.google.android.gms.maps.internal.zzm;
import com.google.android.gms.maps.internal.zzn;
import com.google.android.gms.maps.internal.zzo;
import com.google.android.gms.maps.internal.zzq;
import com.google.android.gms.maps.internal.zzr;
import com.google.android.gms.maps.internal.zzs;
import com.google.android.gms.maps.internal.zzt;
import com.google.android.gms.maps.internal.zzu;
import com.google.android.gms.maps.internal.zzv;
import com.google.android.gms.maps.internal.zzw;
import com.google.android.gms.maps.internal.zzx;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.internal.zzz;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.zzb;
import com.google.android.gms.maps.model.internal.zzc;
import com.google.android.gms.maps.model.internal.zzd;
import com.google.android.gms.maps.model.internal.zzf;
import com.google.android.gms.maps.model.internal.zzg;
import com.google.android.gms.maps.model.internal.zzh;

/* loaded from: classes.dex */
public interface IGoogleMapDelegate extends IInterface {

    public static abstract class zza extends Binder implements IGoogleMapDelegate {

        /* renamed from: com.google.android.gms.maps.internal.IGoogleMapDelegate$zza$zza, reason: collision with other inner class name */
        private static class C0017zza implements IGoogleMapDelegate {
            private IBinder zzrk;

            C0017zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public com.google.android.gms.maps.model.internal.zzb addCircle(CircleOptions circleOptions) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (circleOptions != null) {
                        parcelObtain.writeInt(1);
                        circleOptions.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(35, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return zzb.zza.zzej(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public com.google.android.gms.maps.model.internal.zzc addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (groundOverlayOptions != null) {
                        parcelObtain.writeInt(1);
                        groundOverlayOptions.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return zzc.zza.zzek(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public com.google.android.gms.maps.model.internal.zzf addMarker(MarkerOptions markerOptions) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (markerOptions != null) {
                        parcelObtain.writeInt(1);
                        markerOptions.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return zzf.zza.zzen(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public com.google.android.gms.maps.model.internal.zzg addPolygon(PolygonOptions polygonOptions) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (polygonOptions != null) {
                        parcelObtain.writeInt(1);
                        polygonOptions.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return zzg.zza.zzeo(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public IPolylineDelegate addPolyline(PolylineOptions polylineOptions) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (polylineOptions != null) {
                        parcelObtain.writeInt(1);
                        polylineOptions.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IPolylineDelegate.zza.zzep(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public com.google.android.gms.maps.model.internal.zzh addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (tileOverlayOptions != null) {
                        parcelObtain.writeInt(1);
                        tileOverlayOptions.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return zzh.zza.zzeq(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzb zzbVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    parcelObtain.writeStrongBinder(zzbVar != null ? zzbVar.asBinder() : null);
                    this.zzrk.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzb zzbVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(zzbVar != null ? zzbVar.asBinder() : null);
                    this.zzrk.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.zzrk;
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void clear() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(14, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public CameraPosition getCameraPosition() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CameraPosition.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public com.google.android.gms.maps.model.internal.zzd getFocusedBuilding() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(44, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return zzd.zza.zzel(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void getMapAsync(zzt zztVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zztVar != null ? zztVar.asBinder() : null);
                    this.zzrk.transact(53, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public int getMapType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(15, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public float getMaxZoomLevel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public float getMinZoomLevel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public Location getMyLocation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(23, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public IProjectionDelegate getProjection() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(26, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IProjectionDelegate.zza.zzeb(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public IUiSettingsDelegate getUiSettings() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(25, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IUiSettingsDelegate.zza.zzeg(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public boolean isBuildingsEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(40, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public boolean isIndoorEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(19, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public boolean isMyLocationEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(21, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public boolean isTrafficEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(17, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void onCreate(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(54, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void onDestroy() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(57, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void onEnterAmbient(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(81, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void onExitAmbient() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(82, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void onLowMemory() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(58, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void onPause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(56, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void onResume() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(55, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void onSaveInstanceState(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(60, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void onStart() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(101, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void onStop() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(102, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void resetMinMaxZoomPreference() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(94, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setBuildingsEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(41, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setContentDescription(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeString(str);
                    this.zzrk.transact(61, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public boolean setIndoorEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(20, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setInfoWindowAdapter(zzd zzdVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzdVar != null ? zzdVar.asBinder() : null);
                    this.zzrk.transact(33, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (latLngBounds != null) {
                        parcelObtain.writeInt(1);
                        latLngBounds.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(95, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(iLocationSourceDelegate != null ? iLocationSourceDelegate.asBinder() : null);
                    this.zzrk.transact(24, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (mapStyleOptions != null) {
                        parcelObtain.writeInt(1);
                        mapStyleOptions.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zzrk.transact(91, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setMapType(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeInt(i);
                    this.zzrk.transact(16, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setMaxZoomPreference(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeFloat(f);
                    this.zzrk.transact(93, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setMinZoomPreference(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeFloat(f);
                    this.zzrk.transact(92, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setMyLocationEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(22, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnCameraChangeListener(zze zzeVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzeVar != null ? zzeVar.asBinder() : null);
                    this.zzrk.transact(27, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnCameraIdleListener(zzf zzfVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzfVar != null ? zzfVar.asBinder() : null);
                    this.zzrk.transact(99, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnCameraMoveCanceledListener(zzg zzgVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzgVar != null ? zzgVar.asBinder() : null);
                    this.zzrk.transact(98, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnCameraMoveListener(zzh zzhVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzhVar != null ? zzhVar.asBinder() : null);
                    this.zzrk.transact(97, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnCameraMoveStartedListener(zzi zziVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zziVar != null ? zziVar.asBinder() : null);
                    this.zzrk.transact(96, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnCircleClickListener(zzj zzjVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzjVar != null ? zzjVar.asBinder() : null);
                    this.zzrk.transact(89, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnGroundOverlayClickListener(zzk zzkVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzkVar != null ? zzkVar.asBinder() : null);
                    this.zzrk.transact(83, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnIndoorStateChangeListener(zzl zzlVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzlVar != null ? zzlVar.asBinder() : null);
                    this.zzrk.transact(45, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnInfoWindowClickListener(zzm zzmVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzmVar != null ? zzmVar.asBinder() : null);
                    this.zzrk.transact(32, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnInfoWindowCloseListener(zzn zznVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zznVar != null ? zznVar.asBinder() : null);
                    this.zzrk.transact(86, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnInfoWindowLongClickListener(zzo zzoVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzoVar != null ? zzoVar.asBinder() : null);
                    this.zzrk.transact(84, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnMapClickListener(zzq zzqVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzqVar != null ? zzqVar.asBinder() : null);
                    this.zzrk.transact(28, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnMapLoadedCallback(zzr zzrVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzrVar != null ? zzrVar.asBinder() : null);
                    this.zzrk.transact(42, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnMapLongClickListener(zzs zzsVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzsVar != null ? zzsVar.asBinder() : null);
                    this.zzrk.transact(29, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnMarkerClickListener(zzu zzuVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzuVar != null ? zzuVar.asBinder() : null);
                    this.zzrk.transact(30, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnMarkerDragListener(zzv zzvVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzvVar != null ? zzvVar.asBinder() : null);
                    this.zzrk.transact(31, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnMyLocationButtonClickListener(zzw zzwVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzwVar != null ? zzwVar.asBinder() : null);
                    this.zzrk.transact(37, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnMyLocationChangeListener(zzx zzxVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzxVar != null ? zzxVar.asBinder() : null);
                    this.zzrk.transact(36, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnPoiClickListener(zzy zzyVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzyVar != null ? zzyVar.asBinder() : null);
                    this.zzrk.transact(80, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnPolygonClickListener(zzz zzzVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzzVar != null ? zzzVar.asBinder() : null);
                    this.zzrk.transact(85, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setOnPolylineClickListener(zzaa zzaaVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzaaVar != null ? zzaaVar.asBinder() : null);
                    this.zzrk.transact(87, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setPadding(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    this.zzrk.transact(39, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setTrafficEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(18, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void setWatermarkEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(51, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void snapshot(zzag zzagVar, IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzagVar != null ? zzagVar.asBinder() : null);
                    parcelObtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(38, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void snapshotForTest(zzag zzagVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    parcelObtain.writeStrongBinder(zzagVar != null ? zzagVar.asBinder() : null);
                    this.zzrk.transact(71, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public void stopAnimation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
            public boolean useViewLifecycleWhenInFragment() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(59, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static IGoogleMapDelegate zzdu(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IGoogleMapDelegate)) ? new C0017zza(iBinder) : (IGoogleMapDelegate) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 44) {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                com.google.android.gms.maps.model.internal.zzd focusedBuilding = getFocusedBuilding();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(focusedBuilding != null ? focusedBuilding.asBinder() : null);
                return true;
            }
            if (i == 45) {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnIndoorStateChangeListener(zzl.zza.zzdG(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 51) {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setWatermarkEnabled(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            if (i == 71) {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                snapshotForTest(zzag.zza.zzec(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 89) {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnCircleClickListener(zzj.zza.zzdE(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                return true;
            }
            if (i == 101) {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                onStart();
                parcel2.writeNoException();
                return true;
            }
            if (i == 102) {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                onStop();
                parcel2.writeNoException();
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    CameraPosition cameraPosition = getCameraPosition();
                    parcel2.writeNoException();
                    if (cameraPosition != null) {
                        parcel2.writeInt(1);
                        cameraPosition.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    float maxZoomLevel = getMaxZoomLevel();
                    parcel2.writeNoException();
                    parcel2.writeFloat(maxZoomLevel);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    float minZoomLevel = getMinZoomLevel();
                    parcel2.writeNoException();
                    parcel2.writeFloat(minZoomLevel);
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    moveCamera(IObjectWrapper.zza.zzcd(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    animateCamera(IObjectWrapper.zza.zzcd(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    animateCameraWithCallback(IObjectWrapper.zza.zzcd(parcel.readStrongBinder()), zzb.zza.zzds(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    animateCameraWithDurationAndCallback(IObjectWrapper.zza.zzcd(parcel.readStrongBinder()), parcel.readInt(), zzb.zza.zzds(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    stopAnimation();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IPolylineDelegate iPolylineDelegateAddPolyline = addPolyline(parcel.readInt() != 0 ? PolylineOptions.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iPolylineDelegateAddPolyline != null ? iPolylineDelegateAddPolyline.asBinder() : null);
                    return true;
                case 10:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    com.google.android.gms.maps.model.internal.zzg zzgVarAddPolygon = addPolygon(parcel.readInt() != 0 ? PolygonOptions.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zzgVarAddPolygon != null ? zzgVarAddPolygon.asBinder() : null);
                    return true;
                case 11:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    com.google.android.gms.maps.model.internal.zzf zzfVarAddMarker = addMarker(parcel.readInt() != 0 ? MarkerOptions.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zzfVarAddMarker != null ? zzfVarAddMarker.asBinder() : null);
                    return true;
                case 12:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    com.google.android.gms.maps.model.internal.zzc zzcVarAddGroundOverlay = addGroundOverlay(parcel.readInt() != 0 ? GroundOverlayOptions.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zzcVarAddGroundOverlay != null ? zzcVarAddGroundOverlay.asBinder() : null);
                    return true;
                case 13:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    com.google.android.gms.maps.model.internal.zzh zzhVarAddTileOverlay = addTileOverlay(parcel.readInt() != 0 ? TileOverlayOptions.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zzhVarAddTileOverlay != null ? zzhVarAddTileOverlay.asBinder() : null);
                    return true;
                case 14:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    clear();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    int mapType = getMapType();
                    parcel2.writeNoException();
                    parcel2.writeInt(mapType);
                    return true;
                case 16:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setMapType(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    boolean zIsTrafficEnabled = isTrafficEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsTrafficEnabled ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setTrafficEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    boolean zIsIndoorEnabled = isIndoorEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsIndoorEnabled ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    boolean indoorEnabled = setIndoorEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(indoorEnabled ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    boolean zIsMyLocationEnabled = isMyLocationEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsMyLocationEnabled ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setMyLocationEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    Location myLocation = getMyLocation();
                    parcel2.writeNoException();
                    if (myLocation != null) {
                        parcel2.writeInt(1);
                        myLocation.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 24:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setLocationSource(ILocationSourceDelegate.zza.zzdw(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IUiSettingsDelegate uiSettings = getUiSettings();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(uiSettings != null ? uiSettings.asBinder() : null);
                    return true;
                case 26:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IProjectionDelegate projection = getProjection();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(projection != null ? projection.asBinder() : null);
                    return true;
                case 27:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setOnCameraChangeListener(zze.zza.zzdz(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setOnMapClickListener(zzq.zza.zzdL(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setOnMapLongClickListener(zzs.zza.zzdN(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setOnMarkerClickListener(zzu.zza.zzdP(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setOnMarkerDragListener(zzv.zza.zzdQ(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setOnInfoWindowClickListener(zzm.zza.zzdH(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    setInfoWindowAdapter(zzd.zza.zzdv(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    switch (i) {
                        case 35:
                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                            com.google.android.gms.maps.model.internal.zzb zzbVarAddCircle = addCircle(parcel.readInt() != 0 ? CircleOptions.CREATOR.createFromParcel(parcel) : null);
                            parcel2.writeNoException();
                            parcel2.writeStrongBinder(zzbVarAddCircle != null ? zzbVarAddCircle.asBinder() : null);
                            return true;
                        case 36:
                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                            setOnMyLocationChangeListener(zzx.zza.zzdS(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            return true;
                        case 37:
                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                            setOnMyLocationButtonClickListener(zzw.zza.zzdR(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            return true;
                        case 38:
                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                            snapshot(zzag.zza.zzec(parcel.readStrongBinder()), IObjectWrapper.zza.zzcd(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            return true;
                        case 39:
                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                            setPadding(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                            parcel2.writeNoException();
                            return true;
                        case 40:
                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                            boolean zIsBuildingsEnabled = isBuildingsEnabled();
                            parcel2.writeNoException();
                            parcel2.writeInt(zIsBuildingsEnabled ? 1 : 0);
                            return true;
                        case 41:
                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                            setBuildingsEnabled(parcel.readInt() != 0);
                            parcel2.writeNoException();
                            return true;
                        case 42:
                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                            setOnMapLoadedCallback(zzr.zza.zzdM(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            return true;
                        default:
                            switch (i) {
                                case 53:
                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                    getMapAsync(zzt.zza.zzdO(parcel.readStrongBinder()));
                                    parcel2.writeNoException();
                                    return true;
                                case 54:
                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                    onCreate(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                                    parcel2.writeNoException();
                                    return true;
                                case 55:
                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                    onResume();
                                    parcel2.writeNoException();
                                    return true;
                                case 56:
                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                    onPause();
                                    parcel2.writeNoException();
                                    return true;
                                case 57:
                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                    onDestroy();
                                    parcel2.writeNoException();
                                    return true;
                                case 58:
                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                    onLowMemory();
                                    parcel2.writeNoException();
                                    return true;
                                case 59:
                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                    boolean zUseViewLifecycleWhenInFragment = useViewLifecycleWhenInFragment();
                                    parcel2.writeNoException();
                                    parcel2.writeInt(zUseViewLifecycleWhenInFragment ? 1 : 0);
                                    return true;
                                case 60:
                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                    Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                                    onSaveInstanceState(bundle);
                                    parcel2.writeNoException();
                                    if (bundle != null) {
                                        parcel2.writeInt(1);
                                        bundle.writeToParcel(parcel2, 1);
                                    } else {
                                        parcel2.writeInt(0);
                                    }
                                    return true;
                                case 61:
                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                    setContentDescription(parcel.readString());
                                    parcel2.writeNoException();
                                    return true;
                                default:
                                    switch (i) {
                                        case 80:
                                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                            setOnPoiClickListener(zzy.zza.zzdT(parcel.readStrongBinder()));
                                            parcel2.writeNoException();
                                            return true;
                                        case 81:
                                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                            onEnterAmbient(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                                            parcel2.writeNoException();
                                            return true;
                                        case 82:
                                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                            onExitAmbient();
                                            parcel2.writeNoException();
                                            return true;
                                        case 83:
                                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                            setOnGroundOverlayClickListener(zzk.zza.zzdF(parcel.readStrongBinder()));
                                            parcel2.writeNoException();
                                            return true;
                                        case 84:
                                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                            setOnInfoWindowLongClickListener(zzo.zza.zzdJ(parcel.readStrongBinder()));
                                            parcel2.writeNoException();
                                            return true;
                                        case 85:
                                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                            setOnPolygonClickListener(zzz.zza.zzdU(parcel.readStrongBinder()));
                                            parcel2.writeNoException();
                                            return true;
                                        case 86:
                                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                            setOnInfoWindowCloseListener(zzn.zza.zzdI(parcel.readStrongBinder()));
                                            parcel2.writeNoException();
                                            return true;
                                        case 87:
                                            parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                            setOnPolylineClickListener(zzaa.zza.zzdV(parcel.readStrongBinder()));
                                            parcel2.writeNoException();
                                            return true;
                                        default:
                                            switch (i) {
                                                case 91:
                                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                                    boolean mapStyle = setMapStyle(parcel.readInt() != 0 ? MapStyleOptions.CREATOR.createFromParcel(parcel) : null);
                                                    parcel2.writeNoException();
                                                    parcel2.writeInt(mapStyle ? 1 : 0);
                                                    return true;
                                                case 92:
                                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                                    setMinZoomPreference(parcel.readFloat());
                                                    parcel2.writeNoException();
                                                    return true;
                                                case 93:
                                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                                    setMaxZoomPreference(parcel.readFloat());
                                                    parcel2.writeNoException();
                                                    return true;
                                                case 94:
                                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                                    resetMinMaxZoomPreference();
                                                    parcel2.writeNoException();
                                                    return true;
                                                case 95:
                                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                                    setLatLngBoundsForCameraTarget(parcel.readInt() != 0 ? LatLngBounds.CREATOR.createFromParcel(parcel) : null);
                                                    parcel2.writeNoException();
                                                    return true;
                                                case 96:
                                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                                    setOnCameraMoveStartedListener(zzi.zza.zzdD(parcel.readStrongBinder()));
                                                    parcel2.writeNoException();
                                                    return true;
                                                case 97:
                                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                                    setOnCameraMoveListener(zzh.zza.zzdC(parcel.readStrongBinder()));
                                                    parcel2.writeNoException();
                                                    return true;
                                                case 98:
                                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                                    setOnCameraMoveCanceledListener(zzg.zza.zzdB(parcel.readStrongBinder()));
                                                    parcel2.writeNoException();
                                                    return true;
                                                case 99:
                                                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                                                    setOnCameraIdleListener(zzf.zza.zzdA(parcel.readStrongBinder()));
                                                    parcel2.writeNoException();
                                                    return true;
                                                default:
                                                    return super.onTransact(i, parcel, parcel2, i2);
                                            }
                                    }
                            }
                    }
            }
        }
    }

    com.google.android.gms.maps.model.internal.zzb addCircle(CircleOptions circleOptions) throws RemoteException;

    com.google.android.gms.maps.model.internal.zzc addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException;

    com.google.android.gms.maps.model.internal.zzf addMarker(MarkerOptions markerOptions) throws RemoteException;

    com.google.android.gms.maps.model.internal.zzg addPolygon(PolygonOptions polygonOptions) throws RemoteException;

    IPolylineDelegate addPolyline(PolylineOptions polylineOptions) throws RemoteException;

    com.google.android.gms.maps.model.internal.zzh addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException;

    void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException;

    void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzb zzbVar) throws RemoteException;

    void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzb zzbVar) throws RemoteException;

    void clear() throws RemoteException;

    CameraPosition getCameraPosition() throws RemoteException;

    com.google.android.gms.maps.model.internal.zzd getFocusedBuilding() throws RemoteException;

    void getMapAsync(zzt zztVar) throws RemoteException;

    int getMapType() throws RemoteException;

    float getMaxZoomLevel() throws RemoteException;

    float getMinZoomLevel() throws RemoteException;

    Location getMyLocation() throws RemoteException;

    IProjectionDelegate getProjection() throws RemoteException;

    IUiSettingsDelegate getUiSettings() throws RemoteException;

    boolean isBuildingsEnabled() throws RemoteException;

    boolean isIndoorEnabled() throws RemoteException;

    boolean isMyLocationEnabled() throws RemoteException;

    boolean isTrafficEnabled() throws RemoteException;

    void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException;

    void onCreate(Bundle bundle) throws RemoteException;

    void onDestroy() throws RemoteException;

    void onEnterAmbient(Bundle bundle) throws RemoteException;

    void onExitAmbient() throws RemoteException;

    void onLowMemory() throws RemoteException;

    void onPause() throws RemoteException;

    void onResume() throws RemoteException;

    void onSaveInstanceState(Bundle bundle) throws RemoteException;

    void onStart() throws RemoteException;

    void onStop() throws RemoteException;

    void resetMinMaxZoomPreference() throws RemoteException;

    void setBuildingsEnabled(boolean z) throws RemoteException;

    void setContentDescription(String str) throws RemoteException;

    boolean setIndoorEnabled(boolean z) throws RemoteException;

    void setInfoWindowAdapter(zzd zzdVar) throws RemoteException;

    void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException;

    void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException;

    boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException;

    void setMapType(int i) throws RemoteException;

    void setMaxZoomPreference(float f) throws RemoteException;

    void setMinZoomPreference(float f) throws RemoteException;

    void setMyLocationEnabled(boolean z) throws RemoteException;

    void setOnCameraChangeListener(zze zzeVar) throws RemoteException;

    void setOnCameraIdleListener(zzf zzfVar) throws RemoteException;

    void setOnCameraMoveCanceledListener(zzg zzgVar) throws RemoteException;

    void setOnCameraMoveListener(zzh zzhVar) throws RemoteException;

    void setOnCameraMoveStartedListener(zzi zziVar) throws RemoteException;

    void setOnCircleClickListener(zzj zzjVar) throws RemoteException;

    void setOnGroundOverlayClickListener(zzk zzkVar) throws RemoteException;

    void setOnIndoorStateChangeListener(zzl zzlVar) throws RemoteException;

    void setOnInfoWindowClickListener(zzm zzmVar) throws RemoteException;

    void setOnInfoWindowCloseListener(zzn zznVar) throws RemoteException;

    void setOnInfoWindowLongClickListener(zzo zzoVar) throws RemoteException;

    void setOnMapClickListener(zzq zzqVar) throws RemoteException;

    void setOnMapLoadedCallback(zzr zzrVar) throws RemoteException;

    void setOnMapLongClickListener(zzs zzsVar) throws RemoteException;

    void setOnMarkerClickListener(zzu zzuVar) throws RemoteException;

    void setOnMarkerDragListener(zzv zzvVar) throws RemoteException;

    void setOnMyLocationButtonClickListener(zzw zzwVar) throws RemoteException;

    void setOnMyLocationChangeListener(zzx zzxVar) throws RemoteException;

    void setOnPoiClickListener(zzy zzyVar) throws RemoteException;

    void setOnPolygonClickListener(zzz zzzVar) throws RemoteException;

    void setOnPolylineClickListener(zzaa zzaaVar) throws RemoteException;

    void setPadding(int i, int i2, int i3, int i4) throws RemoteException;

    void setTrafficEnabled(boolean z) throws RemoteException;

    void setWatermarkEnabled(boolean z) throws RemoteException;

    void snapshot(zzag zzagVar, IObjectWrapper iObjectWrapper) throws RemoteException;

    void snapshotForTest(zzag zzagVar) throws RemoteException;

    void stopAnimation() throws RemoteException;

    boolean useViewLifecycleWhenInFragment() throws RemoteException;
}
