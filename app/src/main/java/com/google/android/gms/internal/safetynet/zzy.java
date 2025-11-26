package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafeBrowsingData;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
final class zzy extends zzd {
    final /* synthetic */ zzz zza;

    zzy(zzz zzzVar) {
        this.zza = zzzVar;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzj(Status status, SafeBrowsingData safeBrowsingData) {
        this.zza.setResult((zzz) new zzac(status, safeBrowsingData));
    }
}
