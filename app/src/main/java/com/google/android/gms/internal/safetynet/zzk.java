package com.google.android.gms.internal.safetynet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
final class zzk extends zzz {
    final /* synthetic */ int[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ String zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzk(GoogleApiClient googleApiClient, int[] iArr, int i, String str, String str2) {
        super(googleApiClient);
        this.zza = iArr;
        this.zzb = i;
        this.zzc = str;
        this.zzd = str2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzaf zzafVar = (zzaf) anyClient;
        ArrayList arrayList = new ArrayList();
        for (int i : this.zza) {
            arrayList.add(Integer.valueOf(i));
        }
        zzafVar.zzq(this.zze, arrayList, this.zzb, this.zzc, this.zzd);
    }
}
