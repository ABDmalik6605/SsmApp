package com.google.android.gms.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.safetynet.zzad;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
final class zzo extends com.google.android.gms.internal.safetynet.zzd {
    final /* synthetic */ TaskCompletionSource zza;

    zzo(SafetyNetClient safetyNetClient, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzb(Status status, boolean z) {
        zzad zzadVar = new zzad(status, z);
        SafetyNetApi.VerifyAppsUserResponse verifyAppsUserResponse = new SafetyNetApi.VerifyAppsUserResponse();
        verifyAppsUserResponse.setResult(zzadVar);
        TaskUtil.setResultOrApiException(status, verifyAppsUserResponse, this.zza);
    }
}
