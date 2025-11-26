package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
public final class zzad implements SafetyNetApi.VerifyAppsUserResult {
    private Status zza;
    private boolean zzb;

    public zzad() {
    }

    public zzad(Status status, boolean z) {
        this.zza = status;
        this.zzb = z;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.VerifyAppsUserResult
    public final boolean isVerifyAppsEnabled() {
        Status status = this.zza;
        if (status == null || !status.isSuccess()) {
            return false;
        }
        return this.zzb;
    }
}
