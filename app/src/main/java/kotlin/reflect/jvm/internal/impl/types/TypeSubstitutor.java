package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.FilteredAnnotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;
import kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt;

/* loaded from: classes2.dex */
public class TypeSubstitutor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final TypeSubstitutor EMPTY = create(TypeSubstitution.EMPTY);
    private final TypeSubstitution substitution;

    private enum VarianceConflictType {
        NO_CONFLICT,
        IN_IN_OUT_POSITION,
        OUT_IN_IN_POSITION
    }

    private static final class SubstitutionException extends Exception {
        public SubstitutionException(String str) {
            super(str);
        }
    }

    public static TypeSubstitutor create(TypeSubstitution typeSubstitution) {
        return new TypeSubstitutor(typeSubstitution);
    }

    public static TypeSubstitutor createChainedSubstitutor(TypeSubstitution typeSubstitution, TypeSubstitution typeSubstitution2) {
        return create(DisjointKeysUnionTypeSubstitution.create(typeSubstitution, typeSubstitution2));
    }

    public static TypeSubstitutor create(KotlinType kotlinType) {
        return create(TypeConstructorSubstitution.create(kotlinType.getConstructor(), kotlinType.getArguments()));
    }

    protected TypeSubstitutor(TypeSubstitution typeSubstitution) {
        this.substitution = typeSubstitution;
    }

    public boolean isEmpty() {
        return this.substitution.isEmpty();
    }

    public TypeSubstitution getSubstitution() {
        return this.substitution;
    }

    public KotlinType safeSubstitute(KotlinType kotlinType, Variance variance) {
        if (isEmpty()) {
            return kotlinType;
        }
        try {
            return unsafeSubstitute(new TypeProjectionImpl(variance, kotlinType), 0).getType();
        } catch (SubstitutionException e) {
            return ErrorUtils.createErrorType(e.getMessage());
        }
    }

    public KotlinType substitute(KotlinType kotlinType, Variance variance) {
        TypeProjection typeProjectionSubstitute = substitute(new TypeProjectionImpl(variance, getSubstitution().prepareTopLevelType(kotlinType, variance)));
        if (typeProjectionSubstitute == null) {
            return null;
        }
        return typeProjectionSubstitute.getType();
    }

    public TypeProjection substitute(TypeProjection typeProjection) {
        TypeProjection typeProjectionSubstituteWithoutApproximation = substituteWithoutApproximation(typeProjection);
        return (this.substitution.approximateCapturedTypes() || this.substitution.approximateContravariantCapturedTypes()) ? CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary(typeProjectionSubstituteWithoutApproximation, this.substitution.approximateContravariantCapturedTypes()) : typeProjectionSubstituteWithoutApproximation;
    }

    public TypeProjection substituteWithoutApproximation(TypeProjection typeProjection) {
        if (isEmpty()) {
            return typeProjection;
        }
        try {
            return unsafeSubstitute(typeProjection, 0);
        } catch (SubstitutionException unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TypeProjection unsafeSubstitute(TypeProjection typeProjection, int i) throws SubstitutionException {
        KotlinType kotlinTypeMakeNullableIfNeeded;
        assertRecursionDepth(i, typeProjection, this.substitution);
        if (typeProjection.isStarProjection()) {
            return typeProjection;
        }
        KotlinType type = typeProjection.getType();
        if (type instanceof TypeWithEnhancement) {
            TypeWithEnhancement typeWithEnhancement = (TypeWithEnhancement) type;
            UnwrappedType origin = typeWithEnhancement.getOrigin();
            KotlinType enhancement = typeWithEnhancement.getEnhancement();
            TypeProjection typeProjectionUnsafeSubstitute = unsafeSubstitute(new TypeProjectionImpl(typeProjection.getProjectionKind(), origin), i + 1);
            return new TypeProjectionImpl(typeProjectionUnsafeSubstitute.getProjectionKind(), TypeWithEnhancementKt.wrapEnhancement(typeProjectionUnsafeSubstitute.getType().unwrap(), substitute(enhancement, typeProjection.getProjectionKind())));
        }
        if (DynamicTypesKt.isDynamic(type) || (type.unwrap() instanceof RawType)) {
            return typeProjection;
        }
        TypeProjection typeProjectionMo1341get = this.substitution.mo1341get(type);
        Variance projectionKind = typeProjection.getProjectionKind();
        if (typeProjectionMo1341get == null && FlexibleTypesKt.isFlexible(type) && !TypeCapabilitiesKt.isCustomTypeVariable(type)) {
            FlexibleType flexibleTypeAsFlexibleType = FlexibleTypesKt.asFlexibleType(type);
            int i2 = i + 1;
            TypeProjection typeProjectionUnsafeSubstitute2 = unsafeSubstitute(new TypeProjectionImpl(projectionKind, flexibleTypeAsFlexibleType.getLowerBound()), i2);
            TypeProjection typeProjectionUnsafeSubstitute3 = unsafeSubstitute(new TypeProjectionImpl(projectionKind, flexibleTypeAsFlexibleType.getUpperBound()), i2);
            return (typeProjectionUnsafeSubstitute2.getType() == flexibleTypeAsFlexibleType.getLowerBound() && typeProjectionUnsafeSubstitute3.getType() == flexibleTypeAsFlexibleType.getUpperBound()) ? typeProjection : new TypeProjectionImpl(typeProjectionUnsafeSubstitute2.getProjectionKind(), KotlinTypeFactory.flexibleType(TypeSubstitutionKt.asSimpleType(typeProjectionUnsafeSubstitute2.getType()), TypeSubstitutionKt.asSimpleType(typeProjectionUnsafeSubstitute3.getType())));
        }
        if (KotlinBuiltIns.isNothing(type) || KotlinTypeKt.isError(type)) {
            return typeProjection;
        }
        if (typeProjectionMo1341get != null) {
            VarianceConflictType varianceConflictTypeConflictType = conflictType(projectionKind, typeProjectionMo1341get.getProjectionKind());
            if (!CapturedTypeConstructorKt.isCaptured(type)) {
                int i3 = AnonymousClass2.$SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType[varianceConflictTypeConflictType.ordinal()];
                if (i3 == 1) {
                    throw new SubstitutionException("Out-projection in in-position");
                }
                if (i3 == 2) {
                    return new TypeProjectionImpl(Variance.OUT_VARIANCE, type.getConstructor().getBuiltIns().getNullableAnyType());
                }
            }
            CustomTypeVariable customTypeVariable = TypeCapabilitiesKt.getCustomTypeVariable(type);
            if (typeProjectionMo1341get.isStarProjection()) {
                return typeProjectionMo1341get;
            }
            if (customTypeVariable != null) {
                kotlinTypeMakeNullableIfNeeded = customTypeVariable.substitutionResult(typeProjectionMo1341get.getType());
            } else {
                kotlinTypeMakeNullableIfNeeded = TypeUtils.makeNullableIfNeeded(typeProjectionMo1341get.getType(), type.isMarkedNullable());
            }
            if (!type.getAnnotations().isEmpty()) {
                kotlinTypeMakeNullableIfNeeded = TypeUtilsKt.replaceAnnotations(kotlinTypeMakeNullableIfNeeded, new CompositeAnnotations(kotlinTypeMakeNullableIfNeeded.getAnnotations(), filterOutUnsafeVariance(this.substitution.filterAnnotations(type.getAnnotations()))));
            }
            if (varianceConflictTypeConflictType == VarianceConflictType.NO_CONFLICT) {
                projectionKind = combine(projectionKind, typeProjectionMo1341get.getProjectionKind());
            }
            return new TypeProjectionImpl(projectionKind, kotlinTypeMakeNullableIfNeeded);
        }
        return substituteCompoundType(typeProjection, i);
    }

    /* renamed from: kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType;

        static {
            int[] iArr = new int[VarianceConflictType.values().length];
            $SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType = iArr;
            try {
                iArr[VarianceConflictType.OUT_IN_IN_POSITION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType[VarianceConflictType.IN_IN_OUT_POSITION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType[VarianceConflictType.NO_CONFLICT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static Annotations filterOutUnsafeVariance(Annotations annotations) {
        return !annotations.hasAnnotation(KotlinBuiltIns.FQ_NAMES.unsafeVariance) ? annotations : new FilteredAnnotations(annotations, new Function1<FqName, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.1
            @Override // kotlin.jvm.functions.Function1
            public Boolean invoke(FqName fqName) {
                return Boolean.valueOf(!fqName.equals(KotlinBuiltIns.FQ_NAMES.unsafeVariance));
            }
        });
    }

    private TypeProjection substituteCompoundType(TypeProjection typeProjection, int i) throws SubstitutionException {
        KotlinType type = typeProjection.getType();
        Variance projectionKind = typeProjection.getProjectionKind();
        if (type.getConstructor().mo1333getDeclarationDescriptor() instanceof TypeParameterDescriptor) {
            return typeProjection;
        }
        SimpleType abbreviation = SpecialTypesKt.getAbbreviation(type);
        KotlinType kotlinTypeSubstitute = abbreviation != null ? substitute(abbreviation, Variance.INVARIANT) : null;
        KotlinType kotlinTypeReplace = TypeSubstitutionKt.replace(type, substituteTypeArguments(type.getConstructor().getParameters(), type.getArguments(), i), this.substitution.filterAnnotations(type.getAnnotations()));
        if ((kotlinTypeReplace instanceof SimpleType) && (kotlinTypeSubstitute instanceof SimpleType)) {
            kotlinTypeReplace = SpecialTypesKt.withAbbreviation((SimpleType) kotlinTypeReplace, (SimpleType) kotlinTypeSubstitute);
        }
        return new TypeProjectionImpl(projectionKind, kotlinTypeReplace);
    }

    private List<TypeProjection> substituteTypeArguments(List<TypeParameterDescriptor> list, List<TypeProjection> list2, int i) throws SubstitutionException {
        ArrayList arrayList = new ArrayList(list.size());
        boolean z = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            TypeParameterDescriptor typeParameterDescriptor = list.get(i2);
            TypeProjection typeProjection = list2.get(i2);
            TypeProjection typeProjectionUnsafeSubstitute = unsafeSubstitute(typeProjection, i + 1);
            int i3 = AnonymousClass2.$SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType[conflictType(typeParameterDescriptor.getVariance(), typeProjectionUnsafeSubstitute.getProjectionKind()).ordinal()];
            if (i3 != 1 && i3 != 2) {
                if (i3 == 3 && typeParameterDescriptor.getVariance() != Variance.INVARIANT && !typeProjectionUnsafeSubstitute.isStarProjection()) {
                    typeProjectionUnsafeSubstitute = new TypeProjectionImpl(Variance.INVARIANT, typeProjectionUnsafeSubstitute.getType());
                }
            } else {
                typeProjectionUnsafeSubstitute = TypeUtils.makeStarProjection(typeParameterDescriptor);
            }
            if (typeProjectionUnsafeSubstitute != typeProjection) {
                z = true;
            }
            arrayList.add(typeProjectionUnsafeSubstitute);
        }
        return !z ? list2 : arrayList;
    }

    public static Variance combine(Variance variance, TypeProjection typeProjection) {
        return typeProjection.isStarProjection() ? Variance.OUT_VARIANCE : combine(variance, typeProjection.getProjectionKind());
    }

    public static Variance combine(Variance variance, Variance variance2) {
        if (variance == Variance.INVARIANT) {
            return variance2;
        }
        if (variance2 == Variance.INVARIANT) {
            return variance;
        }
        if (variance == variance2) {
            return variance2;
        }
        throw new AssertionError("Variance conflict: type parameter variance '" + variance + "' and projection kind '" + variance2 + "' cannot be combined");
    }

    private static VarianceConflictType conflictType(Variance variance, Variance variance2) {
        if (variance == Variance.IN_VARIANCE && variance2 == Variance.OUT_VARIANCE) {
            return VarianceConflictType.OUT_IN_IN_POSITION;
        }
        if (variance == Variance.OUT_VARIANCE && variance2 == Variance.IN_VARIANCE) {
            return VarianceConflictType.IN_IN_OUT_POSITION;
        }
        return VarianceConflictType.NO_CONFLICT;
    }

    private static void assertRecursionDepth(int i, TypeProjection typeProjection, TypeSubstitution typeSubstitution) {
        if (i <= 100) {
            return;
        }
        throw new IllegalStateException("Recursion too deep. Most likely infinite loop while substituting " + safeToString(typeProjection) + "; substitution: " + safeToString(typeSubstitution));
    }

    private static String safeToString(Object obj) {
        try {
            return obj.toString();
        } catch (Throwable th) {
            if (ExceptionUtilsKt.isProcessCanceledException(th)) {
                throw th;
            }
            return "[Exception while computing toString(): " + th + "]";
        }
    }
}
