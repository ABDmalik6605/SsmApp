package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TypeWithEnhancement.kt */
/* loaded from: classes2.dex */
public final class TypeWithEnhancementKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final KotlinType getEnhancement(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver instanceof TypeWithEnhancement) {
            return ((TypeWithEnhancement) receiver).getEnhancement();
        }
        return null;
    }

    public static final KotlinType unwrapEnhancement(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        KotlinType enhancement = getEnhancement(receiver);
        return enhancement != null ? enhancement : receiver;
    }

    public static final UnwrappedType inheritEnhancement(UnwrappedType receiver, KotlinType origin) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(origin, "origin");
        return wrapEnhancement(receiver, getEnhancement(origin));
    }

    public static final UnwrappedType wrapEnhancement(UnwrappedType receiver, KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (kotlinType == null) {
            return receiver;
        }
        if (receiver instanceof SimpleType) {
            return new SimpleTypeWithEnhancement((SimpleType) receiver, kotlinType);
        }
        if (receiver instanceof FlexibleType) {
            return new FlexibleTypeWithEnhancement((FlexibleType) receiver, kotlinType);
        }
        throw new NoWhenBranchMatchedException();
    }
}
