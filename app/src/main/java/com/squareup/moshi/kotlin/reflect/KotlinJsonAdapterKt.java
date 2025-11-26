package com.squareup.moshi.kotlin.reflect;

import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.TypeCastException;

/* compiled from: KotlinJsonAdapter.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"ABSENT_VALUE", "", "KOTLIN_METADATA", "Ljava/lang/Class;", "", "moshi-kotlin"}, k = 2, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class KotlinJsonAdapterKt {
    private static final Object ABSENT_VALUE;
    private static final Class<? extends Annotation> KOTLIN_METADATA;

    static {
        Class cls = Class.forName("kotlin.Metadata");
        if (cls == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<out kotlin.Annotation>");
        }
        KOTLIN_METADATA = cls;
        ABSENT_VALUE = new Object();
    }
}
