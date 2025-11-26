package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;

/* compiled from: TypeUtils.kt */
/* loaded from: classes2.dex */
public final class TypeUtilsKt {
    public static final KotlinBuiltIns getBuiltIns(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        KotlinBuiltIns builtIns = receiver.getConstructor().getBuiltIns();
        Intrinsics.checkExpressionValueIsNotNull(builtIns, "constructor.builtIns");
        return builtIns;
    }

    public static final KotlinType makeNullable(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return TypeUtils.makeNullable(receiver);
    }

    public static final KotlinType makeNotNullable(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return TypeUtils.makeNotNullable(receiver);
    }

    public static final boolean isAnyOrNullableAny(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return KotlinBuiltIns.isAnyOrNullableAny(receiver);
    }

    public static final boolean isTypeParameter(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return TypeUtils.isTypeParameter(receiver);
    }

    public static final boolean isSubtypeOf(KotlinType receiver, KotlinType superType) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(receiver, superType);
    }

    public static final KotlinType replaceAnnotations(KotlinType receiver, Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return (receiver.getAnnotations().isEmpty() && newAnnotations.isEmpty()) ? receiver : receiver.unwrap().replaceAnnotations(newAnnotations);
    }

    public static final TypeProjection createProjection(KotlinType type, Variance projectionKind, TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(projectionKind, "projectionKind");
        if ((typeParameterDescriptor != null ? typeParameterDescriptor.getVariance() : null) == projectionKind) {
            projectionKind = Variance.INVARIANT;
        }
        return new TypeProjectionImpl(projectionKind, type);
    }

    public static final TypeProjection asTypeProjection(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new TypeProjectionImpl(receiver);
    }

    public static final boolean canHaveUndefinedNullability(UnwrappedType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (receiver.getConstructor() instanceof NewTypeVariableConstructor) || (receiver.getConstructor().mo1333getDeclarationDescriptor() instanceof TypeParameterDescriptor) || (receiver instanceof NewCapturedType);
    }

    public static final KotlinType replaceArgumentsWithStarProjections(KotlinType receiver) {
        SimpleType simpleTypeFlexibleType;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        UnwrappedType unwrappedTypeUnwrap = receiver.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedTypeUnwrap;
            SimpleType lowerBound = flexibleType.getLowerBound();
            if (!lowerBound.getConstructor().getParameters().isEmpty() && lowerBound.getConstructor().mo1333getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters = lowerBound.getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters, "constructor.parameters");
                List<TypeParameterDescriptor> list = parameters;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(new StarProjectionImpl((TypeParameterDescriptor) it.next()));
                }
                lowerBound = TypeSubstitutionKt.replace$default(lowerBound, (List) arrayList, (Annotations) null, 2, (Object) null);
            }
            SimpleType upperBound = flexibleType.getUpperBound();
            if (!upperBound.getConstructor().getParameters().isEmpty() && upperBound.getConstructor().mo1333getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters2 = upperBound.getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters2, "constructor.parameters");
                List<TypeParameterDescriptor> list2 = parameters2;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it2 = list2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new StarProjectionImpl((TypeParameterDescriptor) it2.next()));
                }
                upperBound = TypeSubstitutionKt.replace$default(upperBound, (List) arrayList2, (Annotations) null, 2, (Object) null);
            }
            simpleTypeFlexibleType = KotlinTypeFactory.flexibleType(lowerBound, upperBound);
        } else if (unwrappedTypeUnwrap instanceof SimpleType) {
            SimpleType simpleTypeReplace$default = (SimpleType) unwrappedTypeUnwrap;
            if (!simpleTypeReplace$default.getConstructor().getParameters().isEmpty() && simpleTypeReplace$default.getConstructor().mo1333getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters3 = simpleTypeReplace$default.getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters3, "constructor.parameters");
                List<TypeParameterDescriptor> list3 = parameters3;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                Iterator<T> it3 = list3.iterator();
                while (it3.hasNext()) {
                    arrayList3.add(new StarProjectionImpl((TypeParameterDescriptor) it3.next()));
                }
                simpleTypeReplace$default = TypeSubstitutionKt.replace$default(simpleTypeReplace$default, (List) arrayList3, (Annotations) null, 2, (Object) null);
            }
            simpleTypeFlexibleType = simpleTypeReplace$default;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(simpleTypeFlexibleType, unwrappedTypeUnwrap);
    }
}
