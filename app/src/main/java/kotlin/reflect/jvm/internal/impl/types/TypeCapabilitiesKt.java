package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TypeCapabilities.kt */
/* loaded from: classes2.dex */
public final class TypeCapabilitiesKt {
    public static final boolean isCustomTypeVariable(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Object objUnwrap = receiver.unwrap();
        if (!(objUnwrap instanceof CustomTypeVariable)) {
            objUnwrap = null;
        }
        CustomTypeVariable customTypeVariable = (CustomTypeVariable) objUnwrap;
        if (customTypeVariable != null) {
            return customTypeVariable.isTypeVariable();
        }
        return false;
    }

    public static final CustomTypeVariable getCustomTypeVariable(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Object objUnwrap = receiver.unwrap();
        if (!(objUnwrap instanceof CustomTypeVariable)) {
            objUnwrap = null;
        }
        CustomTypeVariable customTypeVariable = (CustomTypeVariable) objUnwrap;
        if (customTypeVariable == null || !customTypeVariable.isTypeVariable()) {
            return null;
        }
        return customTypeVariable;
    }

    public static final KotlinType getSubtypeRepresentative(KotlinType receiver) {
        KotlinType subTypeRepresentative;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Object objUnwrap = receiver.unwrap();
        if (!(objUnwrap instanceof SubtypingRepresentatives)) {
            objUnwrap = null;
        }
        SubtypingRepresentatives subtypingRepresentatives = (SubtypingRepresentatives) objUnwrap;
        return (subtypingRepresentatives == null || (subTypeRepresentative = subtypingRepresentatives.getSubTypeRepresentative()) == null) ? receiver : subTypeRepresentative;
    }

    public static final KotlinType getSupertypeRepresentative(KotlinType receiver) {
        KotlinType superTypeRepresentative;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Object objUnwrap = receiver.unwrap();
        if (!(objUnwrap instanceof SubtypingRepresentatives)) {
            objUnwrap = null;
        }
        SubtypingRepresentatives subtypingRepresentatives = (SubtypingRepresentatives) objUnwrap;
        return (subtypingRepresentatives == null || (superTypeRepresentative = subtypingRepresentatives.getSuperTypeRepresentative()) == null) ? receiver : superTypeRepresentative;
    }

    public static final boolean sameTypeConstructors(KotlinType first, KotlinType second) {
        Intrinsics.checkParameterIsNotNull(first, "first");
        Intrinsics.checkParameterIsNotNull(second, "second");
        Object objUnwrap = first.unwrap();
        if (!(objUnwrap instanceof SubtypingRepresentatives)) {
            objUnwrap = null;
        }
        SubtypingRepresentatives subtypingRepresentatives = (SubtypingRepresentatives) objUnwrap;
        if (!(subtypingRepresentatives != null ? subtypingRepresentatives.sameTypeConstructor(second) : false)) {
            UnwrappedType unwrappedTypeUnwrap = second.unwrap();
            SubtypingRepresentatives subtypingRepresentatives2 = (SubtypingRepresentatives) (unwrappedTypeUnwrap instanceof SubtypingRepresentatives ? unwrappedTypeUnwrap : null);
            if (!(subtypingRepresentatives2 != null ? subtypingRepresentatives2.sameTypeConstructor(first) : false)) {
                return false;
            }
        }
        return true;
    }
}
