package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* compiled from: NewKotlinTypeChecker.kt */
/* loaded from: classes2.dex */
public final class StrictEqualityTypeChecker {
    public static final StrictEqualityTypeChecker INSTANCE = new StrictEqualityTypeChecker();

    private StrictEqualityTypeChecker() {
    }

    public final boolean strictEqualTypes(UnwrappedType a, UnwrappedType b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        if (a == b) {
            return true;
        }
        if ((a instanceof SimpleType) && (b instanceof SimpleType)) {
            return strictEqualTypes((SimpleType) a, (SimpleType) b);
        }
        if (!(a instanceof FlexibleType) || !(b instanceof FlexibleType)) {
            return false;
        }
        FlexibleType flexibleType = (FlexibleType) a;
        FlexibleType flexibleType2 = (FlexibleType) b;
        return strictEqualTypes(flexibleType.getLowerBound(), flexibleType2.getLowerBound()) && strictEqualTypes(flexibleType.getUpperBound(), flexibleType2.getUpperBound());
    }

    public final boolean strictEqualTypes(SimpleType a, SimpleType b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        if (a.isMarkedNullable() != b.isMarkedNullable() || SpecialTypesKt.isDefinitelyNotNullType(a) != SpecialTypesKt.isDefinitelyNotNullType(b) || (!Intrinsics.areEqual(a.getConstructor(), b.getConstructor())) || a.getArguments().size() != b.getArguments().size()) {
            return false;
        }
        if (a.getArguments() == b.getArguments()) {
            return true;
        }
        int size = a.getArguments().size();
        for (int i = 0; i < size; i++) {
            TypeProjection typeProjection = a.getArguments().get(i);
            TypeProjection typeProjection2 = b.getArguments().get(i);
            if (typeProjection.isStarProjection() != typeProjection2.isStarProjection()) {
                return false;
            }
            if (!typeProjection.isStarProjection() && (typeProjection.getProjectionKind() != typeProjection2.getProjectionKind() || !strictEqualTypes(typeProjection.getType().unwrap(), typeProjection2.getType().unwrap()))) {
                return false;
            }
        }
        return true;
    }
}
