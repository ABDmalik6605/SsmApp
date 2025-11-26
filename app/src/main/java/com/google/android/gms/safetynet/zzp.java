package com.google.android.gms.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
final class zzp extends com.google.android.gms.internal.safetynet.zzd {
    final /* synthetic */ TaskCompletionSource zza;

    zzp(SafetyNetClient safetyNetClient, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzc(Status status) {
        TaskUtil.setResultOrApiException(status, this.zza);
    }
}
