package com.google.common.collect;

import com.google.common.collect.Maps;
import java.util.function.Function;

/* compiled from: lambda */
/* renamed from: com.google.common.collect.-$$Lambda$FO5RnslEf6Qy6udMXLwIdUgNeGQ, reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$FO5RnslEf6Qy6udMXLwIdUgNeGQ implements Function {
    public static final /* synthetic */ $$Lambda$FO5RnslEf6Qy6udMXLwIdUgNeGQ INSTANCE = new $$Lambda$FO5RnslEf6Qy6udMXLwIdUgNeGQ();

    private /* synthetic */ $$Lambda$FO5RnslEf6Qy6udMXLwIdUgNeGQ() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Maps.Accumulator) obj).toImmutableMap();
    }
}
