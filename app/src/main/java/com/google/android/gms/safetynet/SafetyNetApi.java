package com.google.android.gms.safetynet;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
public interface SafetyNetApi {

    /* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
    public static class AttestationResponse extends Response<AttestationResult> {
        public String getJwsResult() {
            return getResult().getJwsResult();
        }
    }

    /* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
    @Deprecated
    public interface AttestationResult extends Result {
        String getJwsResult();
    }

    /* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
    public static class HarmfulAppsResponse extends Response<HarmfulAppsResult> {
        public List<HarmfulAppsData> getHarmfulAppsList() {
            return getResult().getHarmfulAppsList();
        }

        public int getHoursSinceLastScanWithHarmfulApp() {
            return getResult().getHoursSinceLastScanWithHarmfulApp();
        }

        public long getLastScanTimeMs() {
            return getResult().getLastScanTimeMs();
        }
    }

    /* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
    @Deprecated
    public interface HarmfulAppsResult extends Result {
        List<HarmfulAppsData> getHarmfulAppsList();

        int getHoursSinceLastScanWithHarmfulApp();

        long getLastScanTimeMs();
    }

    /* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
    public static class RecaptchaTokenResponse extends Response<RecaptchaTokenResult> {
        public String getTokenResult() {
            return getResult().getTokenResult();
        }
    }

    /* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
    @Deprecated
    public interface RecaptchaTokenResult extends Result {
        String getTokenResult();
    }

    /* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
    public static class SafeBrowsingResponse extends Response<SafeBrowsingResult> {
        public List<SafeBrowsingThreat> getDetectedThreats() {
            return getResult().getDetectedThreats();
        }

        public long getLastUpdateTimeMs() {
            return getResult().getLastUpdateTimeMs();
        }

        public String getMetadata() {
            return getResult().getMetadata();
        }

        public byte[] getState() {
            return getResult().getState();
        }
    }

    /* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
    @Deprecated
    public interface SafeBrowsingResult extends Result {
        List<SafeBrowsingThreat> getDetectedThreats();

        long getLastUpdateTimeMs();

        String getMetadata();

        byte[] getState();
    }

    /* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
    public static class VerifyAppsUserResponse extends Response<VerifyAppsUserResult> {
        public boolean isVerifyAppsEnabled() {
            return getResult().isVerifyAppsEnabled();
        }
    }

    /* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
    @Deprecated
    public interface VerifyAppsUserResult extends Result {
        boolean isVerifyAppsEnabled();
    }

    @Deprecated
    PendingResult<AttestationResult> attest(GoogleApiClient googleApiClient, byte[] bArr);

    @Deprecated
    PendingResult<VerifyAppsUserResult> enableVerifyApps(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<VerifyAppsUserResult> isVerifyAppsEnabled(GoogleApiClient googleApiClient);

    @Deprecated
    boolean isVerifyAppsEnabled(Context context);

    @Deprecated
    PendingResult<HarmfulAppsResult> listHarmfulApps(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<SafeBrowsingResult> lookupUri(GoogleApiClient googleApiClient, String str, String str2, int... iArr);

    PendingResult<SafeBrowsingResult> lookupUri(GoogleApiClient googleApiClient, List<Integer> list, String str);

    @Deprecated
    PendingResult<RecaptchaTokenResult> verifyWithRecaptcha(GoogleApiClient googleApiClient, String str);
}
