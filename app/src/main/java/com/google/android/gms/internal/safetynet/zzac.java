package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafeBrowsingData;
import com.google.android.gms.safetynet.SafeBrowsingThreat;
import com.google.android.gms.safetynet.SafetyNetApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
public final class zzac implements SafetyNetApi.SafeBrowsingResult {
    private Status zza;
    private final SafeBrowsingData zzb;
    private String zzc;
    private long zzd;
    private byte[] zze;

    public zzac(Status status, SafeBrowsingData safeBrowsingData) {
        this.zza = status;
        this.zzb = safeBrowsingData;
        this.zzc = null;
        if (safeBrowsingData != null) {
            this.zzc = safeBrowsingData.getMetadata();
            this.zzd = safeBrowsingData.getLastUpdateTimeMs();
            this.zze = safeBrowsingData.getState();
        } else if (status.isSuccess()) {
            this.zza = new Status(8);
        }
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult
    public final List<SafeBrowsingThreat> getDetectedThreats() throws JSONException {
        ArrayList arrayList = new ArrayList();
        String str = this.zzc;
        if (str != null) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("matches");
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        arrayList.add(new SafeBrowsingThreat(Integer.parseInt(jSONArray.getJSONObject(i).getString("threat_type"))));
                    } catch (NumberFormatException | JSONException unused) {
                    }
                }
            } catch (JSONException unused2) {
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult
    public final long getLastUpdateTimeMs() {
        return this.zzd;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult
    public final String getMetadata() {
        return this.zzc;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult
    public final byte[] getState() {
        return this.zze;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
