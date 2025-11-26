package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;

/* loaded from: classes.dex */
public final class MapStyleOptions extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator<MapStyleOptions> CREATOR = new zzg();
    private static final String TAG = "MapStyleOptions";
    private String zzbpA;

    public MapStyleOptions(String str) {
        this.zzbpA = str;
    }

    public static MapStyleOptions loadRawResourceStyle(Context context, int i) throws Resources.NotFoundException {
        try {
            return new MapStyleOptions(new String(com.google.android.gms.common.util.zzp.zzj(context.getResources().openRawResource(i)), "UTF-8"));
        } catch (IOException e) {
            String strValueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 37);
            sb.append("Failed to read resource ");
            sb.append(i);
            sb.append(": ");
            sb.append(strValueOf);
            throw new Resources.NotFoundException(sb.toString());
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public String zzJL() {
        return this.zzbpA;
    }
}
