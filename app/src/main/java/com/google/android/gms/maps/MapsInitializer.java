package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.maps.internal.zzai;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* loaded from: classes.dex */
public final class MapsInitializer {
    private static boolean zzpT = false;

    private MapsInitializer() {
    }

    public static synchronized int initialize(Context context) {
        zzac.zzb(context, "Context is null");
        if (zzpT) {
            return 0;
        }
        try {
            zza(zzai.zzbI(context));
            zzpT = true;
            return 0;
        } catch (GooglePlayServicesNotAvailableException e) {
            return e.errorCode;
        }
    }

    public static void zza(zzc zzcVar) {
        try {
            CameraUpdateFactory.zza(zzcVar.zzJE());
            BitmapDescriptorFactory.zza(zzcVar.zzJF());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
