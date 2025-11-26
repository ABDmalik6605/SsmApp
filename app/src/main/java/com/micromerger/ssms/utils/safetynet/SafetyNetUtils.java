package com.micromerger.ssms.utils.safetynet;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.Constants;
import com.micromerger.ssms.utils.util;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: SafetyNetUtils.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0002J\u0016\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/micromerger/ssms/utils/safetynet/SafetyNetUtils;", "", "()V", "TAG", "", "mRandom", "Ljava/security/SecureRandom;", "mResult", "parser", "Lcom/squareup/moshi/Moshi;", "kotlin.jvm.PlatformType", "getParser", "()Lcom/squareup/moshi/Moshi;", "parser$delegate", "Lkotlin/Lazy;", "checkRootMethod1", "", "checkRootMethod2", "checkRootMethod3", "checkRootOffline", "getRequestNonce", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "parseJsonWebSignature", "Lcom/micromerger/ssms/utils/safetynet/SafetyNetResponse;", "jwsResult", "sendSafetyNetRequest", "activity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class SafetyNetUtils {
    private static final String TAG = "Rooted";
    private static String mResult;
    public static final SafetyNetUtils INSTANCE = new SafetyNetUtils();
    private static final SecureRandom mRandom = new SecureRandom();

    /* renamed from: parser$delegate, reason: from kotlin metadata */
    private static final Lazy parser = LazyKt.lazy(new Function0<Moshi>() { // from class: com.micromerger.ssms.utils.safetynet.SafetyNetUtils$parser$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Moshi invoke() {
            return new Moshi.Builder().add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
        }
    });

    private SafetyNetUtils() {
    }

    public final boolean sendSafetyNetRequest(Activity activity, Context context) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(context, "context");
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
            byte[] requestNonce = getRequestNonce(Intrinsics.stringPlus("Rooted Safety Net Sample: ", Long.valueOf(System.currentTimeMillis())));
            if (requestNonce != null) {
                Log.i(TAG, "Sending SafetyNet API request.");
                SafetyNet.getClient(activity).attest(requestNonce, "AIzaSyCSP39zWdcjtAIQPLd--f2Xu_VidoyV8nU").addOnSuccessListener(activity, new OnSuccessListener() { // from class: com.micromerger.ssms.utils.safetynet.-$$Lambda$SafetyNetUtils$xzSNfHXRHBJg0mOVCPtSegHAwKY
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        SafetyNetUtils.m65sendSafetyNetRequest$lambda1(booleanRef, (SafetyNetApi.AttestationResponse) obj);
                    }
                }).addOnFailureListener(activity, new OnFailureListener() { // from class: com.micromerger.ssms.utils.safetynet.-$$Lambda$SafetyNetUtils$rVxvMMSVGrdk67gimfGSABxZ34M
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        SafetyNetUtils.m66sendSafetyNetRequest$lambda2(booleanRef, exc);
                    }
                });
            }
        } else {
            booleanRef.element = checkRootOffline();
        }
        return booleanRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendSafetyNetRequest$lambda-1, reason: not valid java name */
    public static final void m65sendSafetyNetRequest$lambda1(Ref.BooleanRef isRooted, SafetyNetApi.AttestationResponse attestationResponse) {
        Intrinsics.checkNotNullParameter(isRooted, "$isRooted");
        SafetyNetUtils safetyNetUtils = INSTANCE;
        String jwsResult = attestationResponse.getJwsResult();
        mResult = jwsResult;
        SafetyNetResponse jsonWebSignature = safetyNetUtils.parseJsonWebSignature(jwsResult);
        Log.i(TAG, "Success: \n" + jsonWebSignature + '\n');
        if (jsonWebSignature == null) {
            return;
        }
        isRooted.element = !jsonWebSignature.getBasicIntegrity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendSafetyNetRequest$lambda-2, reason: not valid java name */
    public static final void m66sendSafetyNetRequest$lambda2(Ref.BooleanRef isRooted, Exception e) {
        Intrinsics.checkNotNullParameter(isRooted, "$isRooted");
        Intrinsics.checkNotNullParameter(e, "e");
        util.logException(e);
        isRooted.element = INSTANCE.checkRootOffline();
        if (e instanceof ApiException) {
            e.printStackTrace();
        } else {
            Log.i(TAG, Intrinsics.stringPlus("Error: ", e.getMessage()));
        }
    }

    private final byte[] getRequestNonce(String data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[24];
        mRandom.nextBytes(bArr);
        try {
            byteArrayOutputStream.write(bArr);
            byte[] bytes = data.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            byteArrayOutputStream.write(bytes);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0005  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.micromerger.ssms.utils.safetynet.SafetyNetResponse parseJsonWebSignature(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            if (r6 != 0) goto L7
        L5:
            r6 = r2
            goto L4e
        L7:
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            kotlin.text.Regex r3 = new kotlin.text.Regex
            java.lang.String r4 = "\\."
            r3.<init>(r4)
            java.util.List r6 = r3.split(r6, r1)
            if (r6 != 0) goto L17
            goto L5
        L17:
            boolean r3 = r6.isEmpty()
            if (r3 != 0) goto L4a
            int r3 = r6.size()
            java.util.ListIterator r3 = r6.listIterator(r3)
        L25:
            boolean r4 = r3.hasPrevious()
            if (r4 == 0) goto L4a
            java.lang.Object r4 = r3.previous()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            int r4 = r4.length()
            if (r4 != 0) goto L3b
            r4 = 1
            goto L3c
        L3b:
            r4 = 0
        L3c:
            if (r4 != 0) goto L25
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            int r3 = r3.nextIndex()
            int r3 = r3 + r0
            java.util.List r6 = kotlin.collections.CollectionsKt.take(r6, r3)
            goto L4e
        L4a:
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
        L4e:
            if (r6 != 0) goto L51
            goto L8b
        L51:
            int r3 = r6.size()
            r4 = 2
            if (r3 < r4) goto L5a
            r3 = 1
            goto L5b
        L5a:
            r3 = 0
        L5b:
            if (r3 == 0) goto L5e
            goto L5f
        L5e:
            r6 = r2
        L5f:
            if (r6 != 0) goto L62
            goto L8b
        L62:
            java.lang.Object r6 = r6.get(r0)
            java.lang.String r6 = (java.lang.String) r6
            byte[] r6 = android.util.Base64.decode(r6, r1)
            java.lang.String r0 = "decode(it[1], Base64.DEFAULT)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            java.lang.String r0 = new java.lang.String
            java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
            r0.<init>(r6, r1)
            com.micromerger.ssms.utils.safetynet.SafetyNetUtils r6 = com.micromerger.ssms.utils.safetynet.SafetyNetUtils.INSTANCE
            com.squareup.moshi.Moshi r6 = r6.getParser()
            java.lang.Class<com.micromerger.ssms.utils.safetynet.SafetyNetResponse> r1 = com.micromerger.ssms.utils.safetynet.SafetyNetResponse.class
            com.squareup.moshi.JsonAdapter r6 = r6.adapter(r1)
            java.lang.Object r6 = r6.fromJson(r0)
            r2 = r6
            com.micromerger.ssms.utils.safetynet.SafetyNetResponse r2 = (com.micromerger.ssms.utils.safetynet.SafetyNetResponse) r2
        L8b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.utils.safetynet.SafetyNetUtils.parseJsonWebSignature(java.lang.String):com.micromerger.ssms.utils.safetynet.SafetyNetResponse");
    }

    private final Moshi getParser() {
        return (Moshi) parser.getValue();
    }

    private final boolean checkRootOffline() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    private final boolean checkRootMethod1() {
        String str = Build.TAGS;
        return str != null && StringsKt.contains$default((CharSequence) str, (CharSequence) "test-keys", false, 2, (Object) null);
    }

    private final boolean checkRootMethod2() {
        String[] strArr = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        int i = 0;
        while (i < 10) {
            String str = strArr[i];
            i++;
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    private final boolean checkRootMethod3() {
        Process processExec = null;
        try {
            processExec = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            z = new BufferedReader(new InputStreamReader(processExec.getInputStream())).readLine() != null;
        } catch (Throwable unused) {
            if (processExec != null) {
            }
        }
        if (processExec != null) {
            processExec.destroy();
        }
        return z;
    }
}
