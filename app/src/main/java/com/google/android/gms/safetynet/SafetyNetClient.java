package com.google.android.gms.safetynet;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.internal.safetynet.zzae;
import com.google.android.gms.internal.safetynet.zzaf;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-safetynet@@17.0.1 */
/* loaded from: classes.dex */
public class SafetyNetClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    public static final /* synthetic */ int zza = 0;

    SafetyNetClient(Activity activity) {
        super(activity, (Api<Api.ApiOptions>) SafetyNet.API, (Api.ApiOptions) null, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    public Task<SafetyNetApi.AttestationResponse> attest(byte[] bArr, String str) {
        return PendingResultUtil.toResponseTask(zzae.zza(asGoogleApiClient(), bArr, str), new SafetyNetApi.AttestationResponse());
    }

    public Task<SafetyNetApi.VerifyAppsUserResponse> enableVerifyApps() {
        return PendingResultUtil.toResponseTask(SafetyNet.SafetyNetApi.enableVerifyApps(asGoogleApiClient()), new SafetyNetApi.VerifyAppsUserResponse());
    }

    public Task<Void> initSafeBrowsing() {
        return doRead(TaskApiCall.builder().setMethodKey(4202).run(new RemoteCall() { // from class: com.google.android.gms.safetynet.zzl
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.safetynet.zzh) ((zzaf) obj).getService()).zze(new zzp(this.zza, (TaskCompletionSource) obj2));
            }
        }).build());
    }

    public Task<SafetyNetApi.VerifyAppsUserResponse> isVerifyAppsEnabled() {
        return doRead(TaskApiCall.builder().setMethodKey(4201).run(new RemoteCall() { // from class: com.google.android.gms.safetynet.zzm
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((com.google.android.gms.internal.safetynet.zzh) ((zzaf) obj).getService()).zzf(new zzo(this.zza, (TaskCompletionSource) obj2));
            }
        }).build());
    }

    public Task<SafetyNetApi.HarmfulAppsResponse> listHarmfulApps() {
        return PendingResultUtil.toResponseTask(SafetyNet.SafetyNetApi.listHarmfulApps(asGoogleApiClient()), new SafetyNetApi.HarmfulAppsResponse());
    }

    public Task<SafetyNetApi.SafeBrowsingResponse> lookupUri(String str, String str2, int... iArr) {
        return PendingResultUtil.toResponseTask(zzae.zzb(asGoogleApiClient(), str, 3, str2, iArr), new SafetyNetApi.SafeBrowsingResponse());
    }

    public Task<Void> shutdownSafeBrowsing() {
        return doRead(TaskApiCall.builder().setMethodKey(4203).run(new RemoteCall() { // from class: com.google.android.gms.safetynet.zzn
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                int i = SafetyNetClient.zza;
                ((com.google.android.gms.internal.safetynet.zzh) ((zzaf) obj).getService()).zzi();
            }
        }).build());
    }

    public Task<SafetyNetApi.RecaptchaTokenResponse> verifyWithRecaptcha(String str) {
        return PendingResultUtil.toResponseTask(SafetyNet.SafetyNetApi.verifyWithRecaptcha(asGoogleApiClient(), str), new SafetyNetApi.RecaptchaTokenResponse());
    }

    SafetyNetClient(Context context) {
        super(context, SafetyNet.API, (Api.ApiOptions) null, new ApiExceptionMapper());
    }
}
