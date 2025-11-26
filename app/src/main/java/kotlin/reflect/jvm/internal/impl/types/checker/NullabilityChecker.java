package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckerContext;

/* compiled from: NewKotlinTypeChecker.kt */
/* loaded from: classes2.dex */
public final class NullabilityChecker {
    public static final NullabilityChecker INSTANCE = new NullabilityChecker();

    private NullabilityChecker() {
    }

    public final boolean isPossibleSubtype(TypeCheckerContext context, SimpleType subType, SimpleType superType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return runIsPossibleSubtype(context, subType, superType);
    }

    public final boolean isSubtypeOfAny(UnwrappedType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        boolean z = false;
        return hasNotNullSupertype(new TypeCheckerContext(z, z, 2, null), FlexibleTypesKt.lowerIfFlexible(type), TypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE);
    }

    private final boolean runIsPossibleSubtype(TypeCheckerContext typeCheckerContext, SimpleType simpleType, SimpleType simpleType2) {
        if (!NewKotlinTypeCheckerKt.isIntersectionType(simpleType) && !NewKotlinTypeCheckerKt.isSingleClassifierType(simpleType)) {
            typeCheckerContext.isAllowedTypeVariable(simpleType);
        }
        if (!NewKotlinTypeCheckerKt.isSingleClassifierType(simpleType2)) {
            typeCheckerContext.isAllowedTypeVariable(simpleType2);
        }
        if (simpleType2.isMarkedNullable() || SpecialTypesKt.isDefinitelyNotNullType(simpleType) || hasNotNullSupertype(typeCheckerContext, simpleType, TypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE)) {
            return true;
        }
        if (SpecialTypesKt.isDefinitelyNotNullType(simpleType2) || hasNotNullSupertype(typeCheckerContext, simpleType2, TypeCheckerContext.SupertypesPolicy.UpperIfFlexible.INSTANCE) || NewKotlinTypeCheckerKt.isClassType(simpleType)) {
            return false;
        }
        return hasPathByNotMarkedNullableNodes(typeCheckerContext, simpleType, simpleType2.getConstructor());
    }

    private final boolean hasNotNullSupertype(TypeCheckerContext typeCheckerContext, SimpleType simpleType, TypeCheckerContext.SupertypesPolicy supertypesPolicy) {
        if (!((NewKotlinTypeCheckerKt.isClassType(simpleType) && !simpleType.isMarkedNullable()) || SpecialTypesKt.isDefinitelyNotNullType(simpleType))) {
            typeCheckerContext.initialize();
            ArrayDeque arrayDeque = typeCheckerContext.supertypesDeque;
            if (arrayDeque == null) {
                Intrinsics.throwNpe();
            }
            Set set = typeCheckerContext.supertypesSet;
            if (set == null) {
                Intrinsics.throwNpe();
            }
            arrayDeque.push(simpleType);
            while (!arrayDeque.isEmpty()) {
                if (set.size() > 1000) {
                    throw new IllegalStateException(("Too many supertypes for type: " + simpleType + ". Supertypes = " + CollectionsKt.joinToString$default(set, null, null, null, 0, null, null, 63, null)).toString());
                }
                SimpleType current = (SimpleType) arrayDeque.pop();
                Intrinsics.checkExpressionValueIsNotNull(current, "current");
                if (set.add(current)) {
                    TypeCheckerContext.SupertypesPolicy.None none = current.isMarkedNullable() ? TypeCheckerContext.SupertypesPolicy.None.INSTANCE : supertypesPolicy;
                    if (!(!Intrinsics.areEqual(none, TypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                        none = null;
                    }
                    if (none != null) {
                        for (KotlinType supertype : current.getConstructor().getSupertypes()) {
                            Intrinsics.checkExpressionValueIsNotNull(supertype, "supertype");
                            SimpleType simpleTypeMo1342transformType = none.mo1342transformType(supertype);
                            if ((NewKotlinTypeCheckerKt.isClassType(simpleTypeMo1342transformType) && !simpleTypeMo1342transformType.isMarkedNullable()) || SpecialTypesKt.isDefinitelyNotNullType(simpleTypeMo1342transformType)) {
                                typeCheckerContext.clear();
                            } else {
                                arrayDeque.add(simpleTypeMo1342transformType);
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            typeCheckerContext.clear();
            return false;
        }
        return true;
    }

    private final boolean hasPathByNotMarkedNullableNodes(TypeCheckerContext typeCheckerContext, SimpleType simpleType, TypeConstructor typeConstructor) {
        if (!(!simpleType.isMarkedNullable() && Intrinsics.areEqual(simpleType.getConstructor(), typeConstructor))) {
            typeCheckerContext.initialize();
            ArrayDeque arrayDeque = typeCheckerContext.supertypesDeque;
            if (arrayDeque == null) {
                Intrinsics.throwNpe();
            }
            Set set = typeCheckerContext.supertypesSet;
            if (set == null) {
                Intrinsics.throwNpe();
            }
            arrayDeque.push(simpleType);
            while (!arrayDeque.isEmpty()) {
                if (set.size() > 1000) {
                    throw new IllegalStateException(("Too many supertypes for type: " + simpleType + ". Supertypes = " + CollectionsKt.joinToString$default(set, null, null, null, 0, null, null, 63, null)).toString());
                }
                SimpleType current = (SimpleType) arrayDeque.pop();
                Intrinsics.checkExpressionValueIsNotNull(current, "current");
                if (set.add(current)) {
                    TypeCheckerContext.SupertypesPolicy supertypesPolicy = current.isMarkedNullable() ? TypeCheckerContext.SupertypesPolicy.None.INSTANCE : TypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                    if (!(!Intrinsics.areEqual(supertypesPolicy, TypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinType supertype : current.getConstructor().getSupertypes()) {
                            Intrinsics.checkExpressionValueIsNotNull(supertype, "supertype");
                            SimpleType simpleTypeMo1342transformType = supertypesPolicy.mo1342transformType(supertype);
                            if (!simpleTypeMo1342transformType.isMarkedNullable() && Intrinsics.areEqual(simpleTypeMo1342transformType.getConstructor(), typeConstructor)) {
                                typeCheckerContext.clear();
                            } else {
                                arrayDeque.add(simpleTypeMo1342transformType);
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            typeCheckerContext.clear();
            return false;
        }
        return true;
    }
}
