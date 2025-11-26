package com.google.common.collect;

import java.util.Map;
import java.util.function.Function;

/* compiled from: lambda */
/* renamed from: com.google.common.collect.-$$Lambda$kPj8Ioh2qrHaOpflIdB8Zk2L0PY, reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$kPj8Ioh2qrHaOpflIdB8Zk2L0PY implements Function {
    public static final /* synthetic */ $$Lambda$kPj8Ioh2qrHaOpflIdB8Zk2L0PY INSTANCE = new $$Lambda$kPj8Ioh2qrHaOpflIdB8Zk2L0PY();

    private /* synthetic */ $$Lambda$kPj8Ioh2qrHaOpflIdB8Zk2L0PY() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Map.Entry) obj).getValue();
    }
}
