package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement@@18.0.0 */
/* loaded from: classes.dex */
final class zzkq implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ Bundle zzb;
    private final /* synthetic */ zzko zzc;

    zzkq(zzko zzkoVar, String str, Bundle bundle) {
        this.zzc = zzkoVar;
        this.zza = str;
        this.zzb = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        this.zzc.zza.zza(this.zzc.zza.zzk().zza(this.zza, "_err", this.zzb, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, this.zzc.zza.zzl().currentTimeMillis(), false, false, false), this.zza);
    }
}
