package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzah {
    private zzah() {
    }

    public static void zza(Bundle bundle, String str, Parcelable parcelable) {
        bundle.setClassLoader(zzah.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(zzah.class.getClassLoader());
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }

    public static void zzd(Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return;
        }
        Parcelable parcelableZzh = zzh(bundle, "MapOptions");
        if (parcelableZzh != null) {
            zza(bundle2, "MapOptions", parcelableZzh);
        }
        Parcelable parcelableZzh2 = zzh(bundle, "StreetViewPanoramaOptions");
        if (parcelableZzh2 != null) {
            zza(bundle2, "StreetViewPanoramaOptions", parcelableZzh2);
        }
        Parcelable parcelableZzh3 = zzh(bundle, "camera");
        if (parcelableZzh3 != null) {
            zza(bundle2, "camera", parcelableZzh3);
        }
        if (bundle.containsKey("position")) {
            bundle2.putString("position", bundle.getString("position"));
        }
        if (bundle.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
            bundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", bundle.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
        }
    }

    public static <T extends Parcelable> T zzh(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(zzah.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(zzah.class.getClassLoader());
        return (T) bundle2.getParcelable(str);
    }
}
