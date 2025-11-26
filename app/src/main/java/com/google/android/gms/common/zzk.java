package com.google.android.gms.common;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
/* loaded from: classes.dex */
abstract class zzk extends zzi {
    private static final WeakReference<byte[]> zza = new WeakReference<>(null);
    private WeakReference<byte[]> zzb;

    zzk(byte[] bArr) {
        super(bArr);
        this.zzb = zza;
    }

    protected abstract byte[] zzb();

    @Override // com.google.android.gms.common.zzi
    final byte[] zzf() {
        byte[] bArrZzb;
        synchronized (this) {
            bArrZzb = this.zzb.get();
            if (bArrZzb == null) {
                bArrZzb = zzb();
                this.zzb = new WeakReference<>(bArrZzb);
            }
        }
        return bArrZzb;
    }
}
