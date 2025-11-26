package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: flexibleTypes.kt */
/* loaded from: classes2.dex */
public final class FlexibleTypesKt {
    public static final boolean isFlexible(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.unwrap() instanceof FlexibleType;
    }

    public static final FlexibleType asFlexibleType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        UnwrappedType unwrappedTypeUnwrap = receiver.unwrap();
        if (unwrappedTypeUnwrap != null) {
            return (FlexibleType) unwrappedTypeUnwrap;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.FlexibleType");
    }

    public static final SimpleType lowerIfFlexible(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        UnwrappedType unwrappedTypeUnwrap = receiver.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrappedTypeUnwrap).getLowerBound();
        }
        if (unwrappedTypeUnwrap instanceof SimpleType) {
            return (SimpleType) unwrappedTypeUnwrap;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final SimpleType upperIfFlexible(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        UnwrappedType unwrappedTypeUnwrap = receiver.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrappedTypeUnwrap).getUpperBound();
        }
        if (unwrappedTypeUnwrap instanceof SimpleType) {
            return (SimpleType) unwrappedTypeUnwrap;
        }
        throw new NoWhenBranchMatchedException();
    }
}
