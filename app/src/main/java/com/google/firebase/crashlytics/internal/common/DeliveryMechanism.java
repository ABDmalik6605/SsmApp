package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes2.dex */
public enum DeliveryMechanism {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);


    /* renamed from: id, reason: collision with root package name */
    private final int f15id;

    DeliveryMechanism(int i) {
        this.f15id = i;
    }

    public int getId() {
        return this.f15id;
    }

    @Override // java.lang.Enum
    public String toString() {
        return Integer.toString(this.f15id);
    }

    public static DeliveryMechanism determineFrom(String str) {
        return str != null ? APP_STORE : DEVELOPER;
    }
}
