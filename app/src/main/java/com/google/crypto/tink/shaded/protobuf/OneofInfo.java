package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Field;

/* loaded from: classes2.dex */
final class OneofInfo {
    private final Field caseField;

    /* renamed from: id, reason: collision with root package name */
    private final int f14id;
    private final Field valueField;

    public OneofInfo(int i, Field field, Field field2) {
        this.f14id = i;
        this.caseField = field;
        this.valueField = field2;
    }

    public int getId() {
        return this.f14id;
    }

    public Field getCaseField() {
        return this.caseField;
    }

    public Field getValueField() {
        return this.valueField;
    }
}
