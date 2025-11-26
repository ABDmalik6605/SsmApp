package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class PatternItem extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator<PatternItem> CREATOR = new zzi();
    private static final String TAG = "PatternItem";
    private final int type;
    private final Float zzbpJ;

    public PatternItem(int i, Float f) {
        boolean z = true;
        if (i != 1 && (f == null || f.floatValue() < 0.0f)) {
            z = false;
        }
        String strValueOf = String.valueOf(f);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 45);
        sb.append("Invalid PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(strValueOf);
        zzac.zzb(z, sb.toString());
        this.type = i;
        this.zzbpJ = f;
    }

    static List<PatternItem> zzI(List<PatternItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<PatternItem> it = list.iterator();
        while (it.hasNext()) {
            PatternItem next = it.next();
            arrayList.add(next == null ? null : next.zzJO());
        }
        return arrayList;
    }

    private PatternItem zzJO() {
        int i = this.type;
        if (i == 0) {
            return new Dash(this.zzbpJ.floatValue());
        }
        if (i == 1) {
            return new Dot();
        }
        if (i == 2) {
            return new Gap(this.zzbpJ.floatValue());
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unknown PatternItem type: ");
        sb.append(i);
        Log.w(str, sb.toString());
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.type == patternItem.type && zzaa.equal(this.zzbpJ, patternItem.zzbpJ);
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return zzaa.hashCode(new Object[]{Integer.valueOf(this.type), this.zzbpJ});
    }

    public String toString() {
        int i = this.type;
        String strValueOf = String.valueOf(this.zzbpJ);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 39);
        sb.append("[PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(strValueOf);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }

    public Float zzJN() {
        return this.zzbpJ;
    }
}
