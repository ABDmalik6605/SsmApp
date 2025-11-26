package com.google.android.gms.internal.safetynet;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
final class zzi extends zzr {
    final /* synthetic */ byte[] zza;
    final /* synthetic */ String zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzi(GoogleApiClient googleApiClient, byte[] bArr, String str) {
        super(googleApiClient);
        this.zza = bArr;
        this.zzb = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzaf zzafVar = (zzaf) anyClient;
        zzg zzgVar = this.zzc;
        byte[] bArr = this.zza;
        String strZzp = this.zzb;
        if (TextUtils.isEmpty(strZzp)) {
            strZzp = zzafVar.zzp("com.google.android.safetynet.ATTEST_API_KEY");
        }
        ((zzh) zzafVar.getService()).zzc(zzgVar, bArr, strZzp);
    }
}
