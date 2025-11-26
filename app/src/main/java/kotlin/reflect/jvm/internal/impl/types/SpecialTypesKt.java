package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpecialTypes.kt */
/* loaded from: classes2.dex */
public final class SpecialTypesKt {
    public static final AbbreviatedType getAbbreviatedType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        UnwrappedType unwrappedTypeUnwrap = receiver.unwrap();
        if (!(unwrappedTypeUnwrap instanceof AbbreviatedType)) {
            unwrappedTypeUnwrap = null;
        }
        return (AbbreviatedType) unwrappedTypeUnwrap;
    }

    public static final SimpleType getAbbreviation(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        AbbreviatedType abbreviatedType = getAbbreviatedType(receiver);
        if (abbreviatedType != null) {
            return abbreviatedType.getAbbreviation();
        }
        return null;
    }

    public static final SimpleType withAbbreviation(SimpleType receiver, SimpleType abbreviatedType) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(abbreviatedType, "abbreviatedType");
        return KotlinTypeKt.isError(receiver) ? receiver : new AbbreviatedType(receiver, abbreviatedType);
    }

    public static final boolean isDefinitelyNotNullType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.unwrap() instanceof DefinitelyNotNullType;
    }

    public static final SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull(SimpleType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        DefinitelyNotNullType definitelyNotNullTypeMakeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(receiver);
        return definitelyNotNullTypeMakeDefinitelyNotNull$descriptors != null ? definitelyNotNullTypeMakeDefinitelyNotNull$descriptors : receiver.makeNullableAsSpecified(false);
    }

    public static final UnwrappedType makeDefinitelyNotNullOrNotNull(UnwrappedType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        DefinitelyNotNullType definitelyNotNullTypeMakeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(receiver);
        return definitelyNotNullTypeMakeDefinitelyNotNull$descriptors != null ? definitelyNotNullTypeMakeDefinitelyNotNull$descriptors : receiver.makeNullableAsSpecified(false);
    }
}
