package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

/* compiled from: TypeSubstitution.kt */
/* loaded from: classes2.dex */
public final class TypeSubstitutionKt {
    public static /* bridge */ /* synthetic */ KotlinType replace$default(KotlinType kotlinType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = kotlinType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = kotlinType.getAnnotations();
        }
        return replace(kotlinType, (List<? extends TypeProjection>) list, annotations);
    }

    public static final KotlinType replace(KotlinType receiver, List<? extends TypeProjection> newArguments, Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(newArguments, "newArguments");
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        if ((newArguments.isEmpty() || newArguments == receiver.getArguments()) && newAnnotations == receiver.getAnnotations()) {
            return receiver;
        }
        UnwrappedType unwrappedTypeUnwrap = receiver.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedTypeUnwrap;
            return KotlinTypeFactory.flexibleType(replace(flexibleType.getLowerBound(), newArguments, newAnnotations), replace(flexibleType.getUpperBound(), newArguments, newAnnotations));
        }
        if (unwrappedTypeUnwrap instanceof SimpleType) {
            return replace((SimpleType) unwrappedTypeUnwrap, newArguments, newAnnotations);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* bridge */ /* synthetic */ SimpleType replace$default(SimpleType simpleType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = simpleType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = simpleType.getAnnotations();
        }
        return replace(simpleType, (List<? extends TypeProjection>) list, annotations);
    }

    public static final SimpleType replace(SimpleType receiver, List<? extends TypeProjection> newArguments, Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(newArguments, "newArguments");
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        if (newArguments.isEmpty() && newAnnotations == receiver.getAnnotations()) {
            return receiver;
        }
        if (newArguments.isEmpty()) {
            return receiver.replaceAnnotations(newAnnotations);
        }
        return KotlinTypeFactory.simpleType(newAnnotations, receiver.getConstructor(), newArguments, receiver.isMarkedNullable());
    }

    public static final SimpleType asSimpleType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        UnwrappedType unwrappedTypeUnwrap = receiver.unwrap();
        if (!(unwrappedTypeUnwrap instanceof SimpleType)) {
            unwrappedTypeUnwrap = null;
        }
        SimpleType simpleType = (SimpleType) unwrappedTypeUnwrap;
        if (simpleType != null) {
            return simpleType;
        }
        throw new IllegalStateException(("This is should be simple type: " + receiver).toString());
    }
}
