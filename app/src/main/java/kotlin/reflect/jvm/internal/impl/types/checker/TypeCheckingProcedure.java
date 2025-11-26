package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeCapabilitiesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* loaded from: classes2.dex */
public class TypeCheckingProcedure {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final TypeCheckingProcedureCallbacks constraints;

    public static KotlinType findCorrespondingSupertype(KotlinType kotlinType, KotlinType kotlinType2) {
        return findCorrespondingSupertype(kotlinType, kotlinType2, new TypeCheckerProcedureCallbacksImpl());
    }

    public static KotlinType findCorrespondingSupertype(KotlinType kotlinType, KotlinType kotlinType2, TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks) {
        return UtilsKt.findCorrespondingSupertype(kotlinType, kotlinType2, typeCheckingProcedureCallbacks);
    }

    private static KotlinType getOutType(TypeParameterDescriptor typeParameterDescriptor, TypeProjection typeProjection) {
        return typeProjection.getProjectionKind() == Variance.IN_VARIANCE || typeParameterDescriptor.getVariance() == Variance.IN_VARIANCE ? DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNullableAnyType() : typeProjection.getType();
    }

    private static KotlinType getInType(TypeParameterDescriptor typeParameterDescriptor, TypeProjection typeProjection) {
        return typeProjection.getProjectionKind() == Variance.OUT_VARIANCE || typeParameterDescriptor.getVariance() == Variance.OUT_VARIANCE ? DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNothingType() : typeProjection.getType();
    }

    public TypeCheckingProcedure(TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks) {
        this.constraints = typeCheckingProcedureCallbacks;
    }

    public boolean equalTypes(KotlinType kotlinType, KotlinType kotlinType2) {
        if (kotlinType == kotlinType2) {
            return true;
        }
        if (FlexibleTypesKt.isFlexible(kotlinType)) {
            if (FlexibleTypesKt.isFlexible(kotlinType2)) {
                return !KotlinTypeKt.isError(kotlinType) && !KotlinTypeKt.isError(kotlinType2) && isSubtypeOf(kotlinType, kotlinType2) && isSubtypeOf(kotlinType2, kotlinType);
            }
            return heterogeneousEquivalence(kotlinType2, kotlinType);
        }
        if (FlexibleTypesKt.isFlexible(kotlinType2)) {
            return heterogeneousEquivalence(kotlinType, kotlinType2);
        }
        if (kotlinType.isMarkedNullable() != kotlinType2.isMarkedNullable()) {
            return false;
        }
        if (kotlinType.isMarkedNullable()) {
            return this.constraints.assertEqualTypes(TypeUtils.makeNotNullable(kotlinType), TypeUtils.makeNotNullable(kotlinType2), this);
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        TypeConstructor constructor2 = kotlinType2.getConstructor();
        if (!this.constraints.assertEqualTypeConstructors(constructor, constructor2)) {
            return false;
        }
        List<TypeProjection> arguments = kotlinType.getArguments();
        List<TypeProjection> arguments2 = kotlinType2.getArguments();
        if (arguments.size() != arguments2.size()) {
            return false;
        }
        for (int i = 0; i < arguments.size(); i++) {
            TypeProjection typeProjection = arguments.get(i);
            TypeProjection typeProjection2 = arguments2.get(i);
            if (!typeProjection.isStarProjection() || !typeProjection2.isStarProjection()) {
                TypeParameterDescriptor typeParameterDescriptor = constructor.getParameters().get(i);
                TypeParameterDescriptor typeParameterDescriptor2 = constructor2.getParameters().get(i);
                if (!capture(typeProjection, typeProjection2, typeParameterDescriptor) && (getEffectiveProjectionKind(typeParameterDescriptor, typeProjection) != getEffectiveProjectionKind(typeParameterDescriptor2, typeProjection2) || !this.constraints.assertEqualTypes(typeProjection.getType(), typeProjection2.getType(), this))) {
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean heterogeneousEquivalence(KotlinType kotlinType, KotlinType kotlinType2) {
        return isSubtypeOf(FlexibleTypesKt.asFlexibleType(kotlinType2).getLowerBound(), kotlinType) && isSubtypeOf(kotlinType, FlexibleTypesKt.asFlexibleType(kotlinType2).getUpperBound());
    }

    /* renamed from: kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedure$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jetbrains$kotlin$types$Variance;

        static {
            int[] iArr = new int[Variance.values().length];
            $SwitchMap$org$jetbrains$kotlin$types$Variance = iArr;
            try {
                iArr[Variance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$types$Variance[Variance.IN_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$types$Variance[Variance.OUT_VARIANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public enum EnrichedProjectionKind {
        IN,
        OUT,
        INV,
        STAR;

        public static EnrichedProjectionKind fromVariance(Variance variance) {
            int i = AnonymousClass1.$SwitchMap$org$jetbrains$kotlin$types$Variance[variance.ordinal()];
            if (i == 1) {
                return INV;
            }
            if (i == 2) {
                return IN;
            }
            if (i == 3) {
                return OUT;
            }
            throw new IllegalStateException("Unknown variance");
        }
    }

    public static EnrichedProjectionKind getEffectiveProjectionKind(TypeParameterDescriptor typeParameterDescriptor, TypeProjection typeProjection) {
        Variance variance = typeParameterDescriptor.getVariance();
        Variance projectionKind = typeProjection.getProjectionKind();
        if (projectionKind == Variance.INVARIANT) {
            projectionKind = variance;
            variance = projectionKind;
        }
        if (variance == Variance.IN_VARIANCE && projectionKind == Variance.OUT_VARIANCE) {
            return EnrichedProjectionKind.STAR;
        }
        if (variance == Variance.OUT_VARIANCE && projectionKind == Variance.IN_VARIANCE) {
            return EnrichedProjectionKind.STAR;
        }
        return EnrichedProjectionKind.fromVariance(projectionKind);
    }

    public boolean isSubtypeOf(KotlinType kotlinType, KotlinType kotlinType2) {
        if (TypeCapabilitiesKt.sameTypeConstructors(kotlinType, kotlinType2)) {
            return !kotlinType.isMarkedNullable() || kotlinType2.isMarkedNullable();
        }
        KotlinType subtypeRepresentative = TypeCapabilitiesKt.getSubtypeRepresentative(kotlinType);
        KotlinType supertypeRepresentative = TypeCapabilitiesKt.getSupertypeRepresentative(kotlinType2);
        if (subtypeRepresentative != kotlinType || supertypeRepresentative != kotlinType2) {
            return isSubtypeOf(subtypeRepresentative, supertypeRepresentative);
        }
        return isSubtypeOfForRepresentatives(kotlinType, kotlinType2);
    }

    private boolean isSubtypeOfForRepresentatives(KotlinType kotlinType, KotlinType kotlinType2) {
        if (KotlinTypeKt.isError(kotlinType) || KotlinTypeKt.isError(kotlinType2)) {
            return true;
        }
        if (!kotlinType2.isMarkedNullable() && kotlinType.isMarkedNullable()) {
            return false;
        }
        if (KotlinBuiltIns.isNothingOrNullableNothing(kotlinType)) {
            return true;
        }
        KotlinType kotlinTypeFindCorrespondingSupertype = findCorrespondingSupertype(kotlinType, kotlinType2, this.constraints);
        if (kotlinTypeFindCorrespondingSupertype == null) {
            return this.constraints.noCorrespondingSupertype(kotlinType, kotlinType2);
        }
        if (kotlinType2.isMarkedNullable() || !kotlinTypeFindCorrespondingSupertype.isMarkedNullable()) {
            return checkSubtypeForTheSameConstructor(kotlinTypeFindCorrespondingSupertype, kotlinType2);
        }
        return false;
    }

    private boolean checkSubtypeForTheSameConstructor(KotlinType kotlinType, KotlinType kotlinType2) {
        TypeConstructor constructor = kotlinType.getConstructor();
        List<TypeProjection> arguments = kotlinType.getArguments();
        List<TypeProjection> arguments2 = kotlinType2.getArguments();
        if (arguments.size() != arguments2.size()) {
            return false;
        }
        List<TypeParameterDescriptor> parameters = constructor.getParameters();
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= parameters.size()) {
                return true;
            }
            TypeParameterDescriptor typeParameterDescriptor = parameters.get(i);
            TypeProjection typeProjection = arguments2.get(i);
            TypeProjection typeProjection2 = arguments.get(i);
            if (!typeProjection.isStarProjection() && !capture(typeProjection2, typeProjection, typeParameterDescriptor)) {
                if (!KotlinTypeKt.isError(typeProjection2.getType()) && !KotlinTypeKt.isError(typeProjection.getType())) {
                    z = false;
                }
                if (!z && typeParameterDescriptor.getVariance() == Variance.INVARIANT && typeProjection2.getProjectionKind() == Variance.INVARIANT && typeProjection.getProjectionKind() == Variance.INVARIANT) {
                    if (!this.constraints.assertEqualTypes(typeProjection2.getType(), typeProjection.getType(), this)) {
                        return false;
                    }
                } else {
                    KotlinType outType = getOutType(typeParameterDescriptor, typeProjection);
                    if (!this.constraints.assertSubtype(getOutType(typeParameterDescriptor, typeProjection2), outType, this)) {
                        return false;
                    }
                    KotlinType inType = getInType(typeParameterDescriptor, typeProjection);
                    KotlinType inType2 = getInType(typeParameterDescriptor, typeProjection2);
                    if (typeProjection.getProjectionKind() != Variance.OUT_VARIANCE && !this.constraints.assertSubtype(inType, inType2, this)) {
                        return false;
                    }
                }
            }
            i++;
        }
    }

    private boolean capture(TypeProjection typeProjection, TypeProjection typeProjection2, TypeParameterDescriptor typeParameterDescriptor) {
        if (typeParameterDescriptor.getVariance() == Variance.INVARIANT && typeProjection.getProjectionKind() != Variance.INVARIANT && typeProjection2.getProjectionKind() == Variance.INVARIANT) {
            return this.constraints.capture(typeProjection2.getType(), typeProjection);
        }
        return false;
    }
}
