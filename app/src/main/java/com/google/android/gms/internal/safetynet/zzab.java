package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
final class zzab implements SafetyNetApi.RecaptchaTokenResult {
    private final Status zza;
    private final com.google.android.gms.safetynet.zzf zzb;

    public zzab(Status status, com.google.android.gms.safetynet.zzf zzfVar) {
        this.zza = status;
        this.zzb = zzfVar;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.RecaptchaTokenResult
    public final String getTokenResult() {
        com.google.android.gms.safetynet.zzf zzfVar = this.zzb;
        if (zzfVar == null) {
            return null;
        }
        return zzfVar.zza();
    }
}
