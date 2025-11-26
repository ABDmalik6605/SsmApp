package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.maps.model.internal.zzi;

/* loaded from: classes.dex */
public final class TileOverlayOptions extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzr();
    private com.google.android.gms.maps.model.internal.zzi zzbpW;
    private TileProvider zzbpX;
    private boolean zzbpY;
    private float zzbpi;
    private boolean zzbpj;
    private float zzbpr;

    public TileOverlayOptions() {
        this.zzbpj = true;
        this.zzbpY = true;
        this.zzbpr = 0.0f;
    }

    TileOverlayOptions(IBinder iBinder, boolean z, float f, boolean z2, float f2) {
        this.zzbpj = true;
        this.zzbpY = true;
        this.zzbpr = 0.0f;
        com.google.android.gms.maps.model.internal.zzi zziVarZzer = zzi.zza.zzer(iBinder);
        this.zzbpW = zziVarZzer;
        this.zzbpX = zziVarZzer == null ? null : new TileProvider() { // from class: com.google.android.gms.maps.model.TileOverlayOptions.1
            private final com.google.android.gms.maps.model.internal.zzi zzbpZ;

            {
                this.zzbpZ = TileOverlayOptions.this.zzbpW;
            }

            @Override // com.google.android.gms.maps.model.TileProvider
            public Tile getTile(int i, int i2, int i3) {
                try {
                    return this.zzbpZ.getTile(i, i2, i3);
                } catch (RemoteException unused) {
                    return null;
                }
            }
        };
        this.zzbpj = z;
        this.zzbpi = f;
        this.zzbpY = z2;
        this.zzbpr = f2;
    }

    public TileOverlayOptions fadeIn(boolean z) {
        this.zzbpY = z;
        return this;
    }

    public boolean getFadeIn() {
        return this.zzbpY;
    }

    public TileProvider getTileProvider() {
        return this.zzbpX;
    }

    public float getTransparency() {
        return this.zzbpr;
    }

    public float getZIndex() {
        return this.zzbpi;
    }

    public boolean isVisible() {
        return this.zzbpj;
    }

    public TileOverlayOptions tileProvider(final TileProvider tileProvider) {
        this.zzbpX = tileProvider;
        this.zzbpW = tileProvider == null ? null : new zzi.zza(this) { // from class: com.google.android.gms.maps.model.TileOverlayOptions.2
            @Override // com.google.android.gms.maps.model.internal.zzi
            public Tile getTile(int i, int i2, int i3) {
                return tileProvider.getTile(i, i2, i3);
            }
        };
        return this;
    }

    public TileOverlayOptions transparency(float f) {
        zzac.zzb(f >= 0.0f && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzbpr = f;
        return this;
    }

    public TileOverlayOptions visible(boolean z) {
        this.zzbpj = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzr.zza(this, parcel, i);
    }

    public TileOverlayOptions zIndex(float f) {
        this.zzbpi = f;
        return this;
    }

    IBinder zzJQ() {
        return this.zzbpW.asBinder();
    }
}
