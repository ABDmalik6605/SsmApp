package com.google.android.gms.safetynet;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
public class SafeBrowsingThreat {
    public static final int TYPE_POTENTIALLY_HARMFUL_APPLICATION = 4;
    public static final int TYPE_SOCIAL_ENGINEERING = 5;
    private int zza;

    public SafeBrowsingThreat(int i) {
        this.zza = i;
    }

    public int getThreatType() {
        return this.zza;
    }
}
