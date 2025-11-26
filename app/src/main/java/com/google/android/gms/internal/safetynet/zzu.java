package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
final class zzu extends zzd {
    final /* synthetic */ zzv zza;

    zzu(zzv zzvVar) {
        this.zza = zzvVar;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzg(Status status, com.google.android.gms.safetynet.zzd zzdVar) {
        this.zza.setResult((zzv) new zzaa(status, zzdVar));
    }
}
