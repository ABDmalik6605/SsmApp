package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
abstract class zzt extends zze<SafetyNetApi.VerifyAppsUserResult> {
    protected final zzg zza;

    public zzt(GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zza = new zzs(this);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
        return new zzad(status, false);
    }
}
