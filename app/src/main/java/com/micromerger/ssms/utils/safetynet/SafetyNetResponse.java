package com.micromerger.ssms.utils.safetynet;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SafetyNetUtils.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003JG\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/micromerger/ssms/utils/safetynet/SafetyNetResponse;", "", "nonce", "", "apkPackageName", "apkCertificateDigestSha256", "", "ctsProfileMatch", "", "basicIntegrity", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZZ)V", "getApkCertificateDigestSha256", "()Ljava/util/List;", "getApkPackageName", "()Ljava/lang/String;", "getBasicIntegrity", "()Z", "getCtsProfileMatch", "getNonce", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class SafetyNetResponse {
    private final List<String> apkCertificateDigestSha256;
    private final String apkPackageName;
    private final boolean basicIntegrity;
    private final boolean ctsProfileMatch;
    private final String nonce;

    public SafetyNetResponse() {
        this(null, null, null, false, false, 31, null);
    }

    public static /* synthetic */ SafetyNetResponse copy$default(SafetyNetResponse safetyNetResponse, String str, String str2, List list, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = safetyNetResponse.nonce;
        }
        if ((i & 2) != 0) {
            str2 = safetyNetResponse.apkPackageName;
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            list = safetyNetResponse.apkCertificateDigestSha256;
        }
        List list2 = list;
        if ((i & 8) != 0) {
            z = safetyNetResponse.ctsProfileMatch;
        }
        boolean z3 = z;
        if ((i & 16) != 0) {
            z2 = safetyNetResponse.basicIntegrity;
        }
        return safetyNetResponse.copy(str, str3, list2, z3, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getNonce() {
        return this.nonce;
    }

    /* renamed from: component2, reason: from getter */
    public final String getApkPackageName() {
        return this.apkPackageName;
    }

    public final List<String> component3() {
        return this.apkCertificateDigestSha256;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getCtsProfileMatch() {
        return this.ctsProfileMatch;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getBasicIntegrity() {
        return this.basicIntegrity;
    }

    public final SafetyNetResponse copy(String nonce, String apkPackageName, List<String> apkCertificateDigestSha256, boolean ctsProfileMatch, boolean basicIntegrity) {
        return new SafetyNetResponse(nonce, apkPackageName, apkCertificateDigestSha256, ctsProfileMatch, basicIntegrity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SafetyNetResponse)) {
            return false;
        }
        SafetyNetResponse safetyNetResponse = (SafetyNetResponse) other;
        return Intrinsics.areEqual(this.nonce, safetyNetResponse.nonce) && Intrinsics.areEqual(this.apkPackageName, safetyNetResponse.apkPackageName) && Intrinsics.areEqual(this.apkCertificateDigestSha256, safetyNetResponse.apkCertificateDigestSha256) && this.ctsProfileMatch == safetyNetResponse.ctsProfileMatch && this.basicIntegrity == safetyNetResponse.basicIntegrity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.nonce;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.apkPackageName;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<String> list = this.apkCertificateDigestSha256;
        int iHashCode3 = (iHashCode2 + (list != null ? list.hashCode() : 0)) * 31;
        boolean z = this.ctsProfileMatch;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode3 + i) * 31;
        boolean z2 = this.basicIntegrity;
        return i2 + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        return "SafetyNetResponse(nonce=" + ((Object) this.nonce) + ", apkPackageName=" + ((Object) this.apkPackageName) + ", apkCertificateDigestSha256=" + this.apkCertificateDigestSha256 + ", ctsProfileMatch=" + this.ctsProfileMatch + ", basicIntegrity=" + this.basicIntegrity + ')';
    }

    public SafetyNetResponse(String str, String str2, List<String> list, boolean z, boolean z2) {
        this.nonce = str;
        this.apkPackageName = str2;
        this.apkCertificateDigestSha256 = list;
        this.ctsProfileMatch = z;
        this.basicIntegrity = z2;
    }

    public /* synthetic */ SafetyNetResponse(String str, String str2, List list, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) == 0 ? list : null, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2);
    }

    public final String getNonce() {
        return this.nonce;
    }

    public final String getApkPackageName() {
        return this.apkPackageName;
    }

    public final List<String> getApkCertificateDigestSha256() {
        return this.apkCertificateDigestSha256;
    }

    public final boolean getCtsProfileMatch() {
        return this.ctsProfileMatch;
    }

    public final boolean getBasicIntegrity() {
        return this.basicIntegrity;
    }
}
