package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: KotlinType.kt */
/* loaded from: classes2.dex */
public final class KotlinTypeKt {
    public static final boolean isError(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        UnwrappedType unwrappedTypeUnwrap = receiver.unwrap();
        return (unwrappedTypeUnwrap instanceof ErrorType) || ((unwrappedTypeUnwrap instanceof FlexibleType) && (((FlexibleType) unwrappedTypeUnwrap).getDelegate() instanceof ErrorType));
    }
}
