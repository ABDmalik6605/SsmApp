package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

/* compiled from: NewKotlinTypeChecker.kt */
/* loaded from: classes2.dex */
public final class NewKotlinTypeCheckerKt {
    public static final boolean isClassType(SimpleType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.getConstructor().mo1333getDeclarationDescriptor() instanceof ClassDescriptor;
    }

    public static final boolean isSingleClassifierType(SimpleType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (KotlinTypeKt.isError(receiver) || (receiver.getConstructor().mo1333getDeclarationDescriptor() instanceof TypeAliasDescriptor) || (receiver.getConstructor().mo1333getDeclarationDescriptor() == null && !(receiver instanceof CapturedType) && !(receiver instanceof NewCapturedType) && !(receiver instanceof DefinitelyNotNullType))) ? false : true;
    }

    public static final boolean isIntersectionType(SimpleType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.getConstructor() instanceof IntersectionTypeConstructor;
    }
}
