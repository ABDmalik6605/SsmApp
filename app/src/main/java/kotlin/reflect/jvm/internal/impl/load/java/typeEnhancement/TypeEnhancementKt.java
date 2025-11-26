package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* compiled from: typeEnhancement.kt */
/* loaded from: classes2.dex */
public final class TypeEnhancementKt {
    private static final EnhancedTypeAnnotations ENHANCED_MUTABILITY_ANNOTATIONS;
    private static final EnhancedTypeAnnotations ENHANCED_NULLABILITY_ANNOTATIONS;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[MutabilityQualifier.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MutabilityQualifier.READ_ONLY.ordinal()] = 1;
            iArr[MutabilityQualifier.MUTABLE.ordinal()] = 2;
            int[] iArr2 = new int[NullabilityQualifier.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[NullabilityQualifier.NULLABLE.ordinal()] = 1;
            iArr2[NullabilityQualifier.NOT_NULL.ordinal()] = 2;
        }
    }

    public static final KotlinType enhance(KotlinType receiver, Function1<? super Integer, JavaTypeQualifiers> qualifiers) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(qualifiers, "qualifiers");
        return enhancePossiblyFlexible(receiver.unwrap(), qualifiers, 0).getTypeIfChanged();
    }

    public static final boolean hasEnhancedNullability(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Annotations annotations = receiver.getAnnotations();
        FqName fqName = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION");
        return annotations.mo1326findAnnotation(fqName) != null;
    }

    private static final Result enhancePossiblyFlexible(UnwrappedType unwrappedType, Function1<? super Integer, JavaTypeQualifiers> function1, int i) {
        UnwrappedType unwrappedType2 = unwrappedType;
        if (KotlinTypeKt.isError(unwrappedType2)) {
            return new Result(unwrappedType2, 1, false);
        }
        if (unwrappedType instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedType;
            SimpleResult simpleResultEnhanceInflexible = enhanceInflexible(flexibleType.getLowerBound(), function1, i, TypeComponentPosition.FLEXIBLE_LOWER);
            SimpleResult simpleResultEnhanceInflexible2 = enhanceInflexible(flexibleType.getUpperBound(), function1, i, TypeComponentPosition.FLEXIBLE_UPPER);
            simpleResultEnhanceInflexible.getSubtreeSize();
            simpleResultEnhanceInflexible2.getSubtreeSize();
            boolean z = simpleResultEnhanceInflexible.getWereChanges() || simpleResultEnhanceInflexible2.getWereChanges();
            KotlinType enhancement = TypeWithEnhancementKt.getEnhancement(simpleResultEnhanceInflexible.getType());
            if (enhancement == null) {
                enhancement = TypeWithEnhancementKt.getEnhancement(simpleResultEnhanceInflexible2.getType());
            }
            if (z) {
                unwrappedType = TypeWithEnhancementKt.wrapEnhancement(unwrappedType instanceof RawTypeImpl ? new RawTypeImpl(simpleResultEnhanceInflexible.getType(), simpleResultEnhanceInflexible2.getType()) : KotlinTypeFactory.flexibleType(simpleResultEnhanceInflexible.getType(), simpleResultEnhanceInflexible2.getType()), enhancement);
            }
            return new Result(unwrappedType, simpleResultEnhanceInflexible.getSubtreeSize(), z);
        }
        if (unwrappedType instanceof SimpleType) {
            return enhanceInflexible((SimpleType) unwrappedType, function1, i, TypeComponentPosition.INFLEXIBLE);
        }
        throw new NoWhenBranchMatchedException();
    }

    private static final SimpleResult enhanceInflexible(SimpleType simpleType, Function1<? super Integer, JavaTypeQualifiers> function1, int i, TypeComponentPosition typeComponentPosition) {
        TypeProjection typeProjectionCreateProjection;
        if (!shouldEnhance(typeComponentPosition) && simpleType.getArguments().isEmpty()) {
            return new SimpleResult(simpleType, 1, false);
        }
        ClassifierDescriptor originalClass = simpleType.getConstructor().mo1333getDeclarationDescriptor();
        if (originalClass == null) {
            return new SimpleResult(simpleType, 1, false);
        }
        JavaTypeQualifiers javaTypeQualifiersInvoke = function1.invoke(Integer.valueOf(i));
        Intrinsics.checkExpressionValueIsNotNull(originalClass, "originalClass");
        EnhancementResult<ClassifierDescriptor> enhancementResultEnhanceMutability = enhanceMutability(originalClass, javaTypeQualifiersInvoke, typeComponentPosition);
        ClassifierDescriptor classifierDescriptorComponent1 = enhancementResultEnhanceMutability.component1();
        Annotations annotationsComponent2 = enhancementResultEnhanceMutability.component2();
        TypeConstructor typeConstructor = classifierDescriptorComponent1.getTypeConstructor();
        int subtreeSize = i + 1;
        boolean z = annotationsComponent2 != null;
        List<TypeProjection> arguments = simpleType.getArguments();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        int i2 = 0;
        for (TypeProjection typeProjection : arguments) {
            int i3 = i2 + 1;
            if (typeProjection.isStarProjection()) {
                subtreeSize++;
                TypeConstructor typeConstructor2 = classifierDescriptorComponent1.getTypeConstructor();
                Intrinsics.checkExpressionValueIsNotNull(typeConstructor2, "enhancedClassifier.typeConstructor");
                typeProjectionCreateProjection = TypeUtils.makeStarProjection(typeConstructor2.getParameters().get(i2));
            } else {
                Result resultEnhancePossiblyFlexible = enhancePossiblyFlexible(typeProjection.getType().unwrap(), function1, subtreeSize);
                z = z || resultEnhancePossiblyFlexible.getWereChanges();
                subtreeSize += resultEnhancePossiblyFlexible.getSubtreeSize();
                KotlinType type = resultEnhancePossiblyFlexible.getType();
                Variance projectionKind = typeProjection.getProjectionKind();
                Intrinsics.checkExpressionValueIsNotNull(projectionKind, "arg.projectionKind");
                Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "typeConstructor");
                typeProjectionCreateProjection = TypeUtilsKt.createProjection(type, projectionKind, typeConstructor.getParameters().get(i2));
            }
            arrayList.add(typeProjectionCreateProjection);
            i2 = i3;
        }
        ArrayList arrayList2 = arrayList;
        EnhancementResult<Boolean> enhancedNullability = getEnhancedNullability(simpleType, javaTypeQualifiersInvoke, typeComponentPosition);
        boolean zBooleanValue = enhancedNullability.component1().booleanValue();
        Annotations annotationsComponent22 = enhancedNullability.component2();
        int i4 = subtreeSize - i;
        if (!(z || annotationsComponent22 != null)) {
            return new SimpleResult(simpleType, i4, false);
        }
        Annotations annotationsCompositeAnnotationsOrSingle = compositeAnnotationsOrSingle(CollectionsKt.filterNotNull(CollectionsKt.listOf((Object[]) new Annotations[]{simpleType.getAnnotations(), annotationsComponent2, annotationsComponent22})));
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "typeConstructor");
        NotNullTypeParameter notNullTypeParameterSimpleType = KotlinTypeFactory.simpleType(annotationsCompositeAnnotationsOrSingle, typeConstructor, arrayList2, zBooleanValue);
        if (javaTypeQualifiersInvoke.isNotNullTypeParameter$descriptors_jvm()) {
            notNullTypeParameterSimpleType = new NotNullTypeParameter(notNullTypeParameterSimpleType);
        }
        KotlinType kotlinTypeWrapEnhancement = annotationsComponent22 != null && javaTypeQualifiersInvoke.isNullabilityQualifierForWarning$descriptors_jvm() ? TypeWithEnhancementKt.wrapEnhancement(simpleType, notNullTypeParameterSimpleType) : notNullTypeParameterSimpleType;
        if (kotlinTypeWrapEnhancement != null) {
            return new SimpleResult((SimpleType) kotlinTypeWrapEnhancement, i4, true);
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }

    private static final Annotations compositeAnnotationsOrSingle(List<? extends Annotations> list) {
        int size = list.size();
        if (size == 0) {
            throw new IllegalStateException("At least one Annotations object expected".toString());
        }
        if (size == 1) {
            return (Annotations) CollectionsKt.single((List) list);
        }
        return new CompositeAnnotations((List<? extends Annotations>) CollectionsKt.toList(list));
    }

    private static final boolean shouldEnhance(TypeComponentPosition typeComponentPosition) {
        return typeComponentPosition != TypeComponentPosition.INFLEXIBLE;
    }

    private static final <T> EnhancementResult<T> noChange(T t) {
        return new EnhancementResult<>(t, null);
    }

    private static final <T> EnhancementResult<T> enhancedNullability(T t) {
        return new EnhancementResult<>(t, ENHANCED_NULLABILITY_ANNOTATIONS);
    }

    private static final <T> EnhancementResult<T> enhancedMutability(T t) {
        return new EnhancementResult<>(t, ENHANCED_MUTABILITY_ANNOTATIONS);
    }

    private static final EnhancementResult<ClassifierDescriptor> enhanceMutability(ClassifierDescriptor classifierDescriptor, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        if (shouldEnhance(typeComponentPosition) && (classifierDescriptor instanceof ClassDescriptor)) {
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            MutabilityQualifier mutability = javaTypeQualifiers.getMutability();
            if (mutability != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[mutability.ordinal()];
                if (i == 1) {
                    if (typeComponentPosition == TypeComponentPosition.FLEXIBLE_LOWER) {
                        ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptor;
                        if (javaToKotlinClassMap.isMutable(classDescriptor)) {
                            return enhancedMutability(javaToKotlinClassMap.convertMutableToReadOnly(classDescriptor));
                        }
                    }
                } else if (i == 2 && typeComponentPosition == TypeComponentPosition.FLEXIBLE_UPPER) {
                    ClassDescriptor classDescriptor2 = (ClassDescriptor) classifierDescriptor;
                    if (javaToKotlinClassMap.isReadOnly(classDescriptor2)) {
                        return enhancedMutability(javaToKotlinClassMap.convertReadOnlyToMutable(classDescriptor2));
                    }
                }
            }
            return noChange(classifierDescriptor);
        }
        return noChange(classifierDescriptor);
    }

    private static final EnhancementResult<Boolean> getEnhancedNullability(KotlinType kotlinType, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        if (!shouldEnhance(typeComponentPosition)) {
            return noChange(Boolean.valueOf(kotlinType.isMarkedNullable()));
        }
        NullabilityQualifier nullability = javaTypeQualifiers.getNullability();
        if (nullability != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[nullability.ordinal()];
            if (i == 1) {
                return enhancedNullability(true);
            }
            if (i == 2) {
                return enhancedNullability(false);
            }
        }
        return noChange(Boolean.valueOf(kotlinType.isMarkedNullable()));
    }

    static {
        FqName fqName = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION");
        ENHANCED_NULLABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(fqName);
        FqName fqName2 = JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION");
        ENHANCED_MUTABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(fqName2);
    }
}
