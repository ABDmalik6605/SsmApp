package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: dynamicTypes.kt */
/* loaded from: classes2.dex */
public final class DynamicTypesKt {
    public static final boolean isDynamic(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.unwrap() instanceof DynamicType;
    }
}
