package com.google.firebase.iid;

/* compiled from: com.google.firebase:firebase-iid@@21.0.0 */
/* loaded from: classes2.dex */
final class InstanceIdResultImpl implements InstanceIdResult {

    /* renamed from: id, reason: collision with root package name */
    private final String f16id;
    private final String token;

    InstanceIdResultImpl(String str, String str2) {
        this.f16id = str;
        this.token = str2;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getId() {
        return this.f16id;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getToken() {
        return this.token;
    }
}
