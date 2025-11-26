package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.HarmfulAppsData;
import com.google.android.gms.safetynet.SafetyNetApi;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
final class zzaa implements SafetyNetApi.HarmfulAppsResult {
    private final Status zza;
    private final com.google.android.gms.safetynet.zzd zzb;

    public zzaa(Status status, com.google.android.gms.safetynet.zzd zzdVar) {
        this.zza = status;
        this.zzb = zzdVar;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.HarmfulAppsResult
    public final List<HarmfulAppsData> getHarmfulAppsList() {
        com.google.android.gms.safetynet.zzd zzdVar = this.zzb;
        return zzdVar == null ? Collections.emptyList() : Arrays.asList(zzdVar.zzb);
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.HarmfulAppsResult
    public final int getHoursSinceLastScanWithHarmfulApp() {
        com.google.android.gms.safetynet.zzd zzdVar = this.zzb;
        if (zzdVar == null) {
            return -1;
        }
        return zzdVar.zzc;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.HarmfulAppsResult
    public final long getLastScanTimeMs() {
        com.google.android.gms.safetynet.zzd zzdVar = this.zzb;
        if (zzdVar == null) {
            return 0L;
        }
        return zzdVar.zza;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
