package com.google.common.collect;

import com.google.common.collect.Maps;
import java.util.function.BinaryOperator;

/* compiled from: lambda */
/* renamed from: com.google.common.collect.-$$Lambda$3BvnP0Kdxtu-uUBgjErmfKDo_Sc, reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$3BvnP0KdxtuuUBgjErmfKDo_Sc implements BinaryOperator {
    public static final /* synthetic */ $$Lambda$3BvnP0KdxtuuUBgjErmfKDo_Sc INSTANCE = new $$Lambda$3BvnP0KdxtuuUBgjErmfKDo_Sc();

    private /* synthetic */ $$Lambda$3BvnP0KdxtuuUBgjErmfKDo_Sc() {
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return ((Maps.Accumulator) obj).combine((Maps.Accumulator) obj2);
    }
}
