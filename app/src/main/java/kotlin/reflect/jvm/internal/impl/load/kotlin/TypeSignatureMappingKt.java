package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang.ClassUtils;

/* compiled from: typeSignatureMapping.kt */
/* loaded from: classes2.dex */
public final class TypeSignatureMappingKt {
    private static final <T> T boxTypeIfNeeded(JvmTypeFactory<T> jvmTypeFactory, T t, boolean z) {
        return z ? jvmTypeFactory.boxType(t) : t;
    }

    public static /* bridge */ /* synthetic */ Object mapType$default(KotlinType kotlinType, JvmTypeFactory jvmTypeFactory, TypeMappingMode typeMappingMode, TypeMappingConfiguration typeMappingConfiguration, JvmDescriptorTypeWriter jvmDescriptorTypeWriter, Function3 function3, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            function3 = FunctionsKt.getDO_NOTHING_3();
        }
        return mapType(kotlinType, jvmTypeFactory, typeMappingMode, typeMappingConfiguration, jvmDescriptorTypeWriter, function3, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v47, types: [T, java.lang.Object] */
    public static final <T> T mapType(KotlinType kotlinType, JvmTypeFactory<T> factory, TypeMappingMode mode, TypeMappingConfiguration<? extends T> typeMappingConfiguration, JvmDescriptorTypeWriter<T> jvmDescriptorTypeWriter, Function3<? super KotlinType, ? super T, ? super TypeMappingMode, Unit> writeGenericType, boolean z) {
        T predefinedTypeForClass;
        KotlinType kotlinTypeComputeUnderlyingType;
        Object objMapType;
        TypeMappingMode typeMappingModeWrapInlineClassesMode = mode;
        Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(typeMappingConfiguration, "typeMappingConfiguration");
        Intrinsics.checkParameterIsNotNull(writeGenericType, "writeGenericType");
        if (FunctionTypesKt.isSuspendFunctionType(kotlinType)) {
            return (T) mapType(SuspendFunctionTypesKt.transformSuspendFunctionToRuntimeFunctionType(kotlinType, typeMappingConfiguration.releaseCoroutines()), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType, z);
        }
        Object objMapBuiltInType = mapBuiltInType(kotlinType, factory, mode);
        if (objMapBuiltInType != null) {
            ?? r1 = (Object) boxTypeIfNeeded(factory, objMapBuiltInType, mode.getNeedPrimitiveBoxing());
            writeGenericType.invoke(kotlinType, r1, mode);
            return r1;
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            Collection<KotlinType> supertypes = ((IntersectionTypeConstructor) constructor).getSupertypes();
            Intrinsics.checkExpressionValueIsNotNull(supertypes, "constructor.supertypes");
            return (T) mapType(TypeUtilsKt.replaceArgumentsWithStarProjections(typeMappingConfiguration.commonSupertype(supertypes)), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType, z);
        }
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = constructor.mo1333getDeclarationDescriptor();
        if (classifierDescriptorMo1333getDeclarationDescriptor == null) {
            throw new UnsupportedOperationException("no descriptor for type constructor of " + kotlinType);
        }
        if (ErrorUtils.isError(classifierDescriptorMo1333getDeclarationDescriptor)) {
            T t = (T) factory.createObjectType("error/NonExistentClass");
            if (classifierDescriptorMo1333getDeclarationDescriptor != null) {
                typeMappingConfiguration.processErrorType(kotlinType, (ClassDescriptor) classifierDescriptorMo1333getDeclarationDescriptor);
                if (jvmDescriptorTypeWriter != 0) {
                    jvmDescriptorTypeWriter.writeClass(t);
                }
                return t;
            }
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        }
        boolean z2 = classifierDescriptorMo1333getDeclarationDescriptor instanceof ClassDescriptor;
        if (z2 && KotlinBuiltIns.isArray(kotlinType)) {
            if (kotlinType.getArguments().size() != 1) {
                throw new UnsupportedOperationException("arrays must have one type argument");
            }
            TypeProjection typeProjection = kotlinType.getArguments().get(0);
            KotlinType memberType = typeProjection.getType();
            if (typeProjection.getProjectionKind() == Variance.IN_VARIANCE) {
                objMapType = factory.createObjectType("java/lang/Object");
                if (jvmDescriptorTypeWriter != 0) {
                    jvmDescriptorTypeWriter.writeArrayType();
                    jvmDescriptorTypeWriter.writeClass(objMapType);
                    jvmDescriptorTypeWriter.writeArrayEnd();
                }
            } else {
                if (jvmDescriptorTypeWriter != 0) {
                    jvmDescriptorTypeWriter.writeArrayType();
                }
                Intrinsics.checkExpressionValueIsNotNull(memberType, "memberType");
                Variance projectionKind = typeProjection.getProjectionKind();
                Intrinsics.checkExpressionValueIsNotNull(projectionKind, "memberProjection.projectionKind");
                objMapType = mapType(memberType, factory, mode.toGenericArgumentMode(projectionKind), typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType, z);
                if (jvmDescriptorTypeWriter != 0) {
                    jvmDescriptorTypeWriter.writeArrayEnd();
                }
            }
            return (T) factory.createFromString("[" + factory.toString(objMapType));
        }
        if (z2) {
            ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo1333getDeclarationDescriptor;
            if (classDescriptor.mo1338isInline() && !mode.getNeedInlineClassWrapping() && (kotlinTypeComputeUnderlyingType = computeUnderlyingType(kotlinType)) != null) {
                if (!InlineClassesUtilsKt.isInlineClassType(kotlinTypeComputeUnderlyingType)) {
                    typeMappingModeWrapInlineClassesMode = mode.wrapInlineClassesMode();
                }
                return (T) mapType(kotlinTypeComputeUnderlyingType, factory, typeMappingModeWrapInlineClassesMode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType, z);
            }
            if (mode.isForAnnotationParameter() && KotlinBuiltIns.isKClass(classDescriptor)) {
                predefinedTypeForClass = (Object) factory.getJavaLangClassType();
            } else {
                ClassDescriptor original = classDescriptor.getOriginal();
                Intrinsics.checkExpressionValueIsNotNull(original, "descriptor.original");
                predefinedTypeForClass = typeMappingConfiguration.getPredefinedTypeForClass(original);
                if (predefinedTypeForClass == null) {
                    if (classDescriptor.getKind() == ClassKind.ENUM_ENTRY) {
                        DeclarationDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
                        if (containingDeclaration == null) {
                            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                        }
                        classDescriptor = (ClassDescriptor) containingDeclaration;
                    }
                    ClassDescriptor original2 = classDescriptor.getOriginal();
                    Intrinsics.checkExpressionValueIsNotNull(original2, "enumClassIfEnumEntry.original");
                    predefinedTypeForClass = (Object) factory.createObjectType(computeInternalName(original2, typeMappingConfiguration, z));
                }
            }
            writeGenericType.invoke(kotlinType, predefinedTypeForClass, mode);
            return predefinedTypeForClass;
        }
        if (classifierDescriptorMo1333getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            T t2 = (T) mapType(getRepresentativeUpperBound((TypeParameterDescriptor) classifierDescriptorMo1333getDeclarationDescriptor), factory, mode, typeMappingConfiguration, null, FunctionsKt.getDO_NOTHING_3(), z);
            if (jvmDescriptorTypeWriter != 0) {
                Name name = classifierDescriptorMo1333getDeclarationDescriptor.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "descriptor.getName()");
                jvmDescriptorTypeWriter.writeTypeVariable(name, t2);
            }
            return t2;
        }
        throw new UnsupportedOperationException("Unknown type " + kotlinType);
    }

    public static final boolean hasVoidReturnType(CallableDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (descriptor instanceof ConstructorDescriptor) {
            return true;
        }
        KotlinType returnType = descriptor.getReturnType();
        if (returnType == null) {
            Intrinsics.throwNpe();
        }
        if (KotlinBuiltIns.isUnit(returnType)) {
            KotlinType returnType2 = descriptor.getReturnType();
            if (returnType2 == null) {
                Intrinsics.throwNpe();
            }
            if (!TypeUtils.isNullableType(returnType2) && !(descriptor instanceof PropertyGetterDescriptor)) {
                return true;
            }
        }
        return false;
    }

    private static final String continuationInternalName(boolean z) {
        FqName fqName;
        if (z) {
            fqName = DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_RELEASE;
        } else {
            fqName = DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL;
        }
        JvmClassName jvmClassNameByClassId = JvmClassName.byClassId(ClassId.topLevel(fqName));
        Intrinsics.checkExpressionValueIsNotNull(jvmClassNameByClassId, "JvmClassName.byClassId(ClassId.topLevel(fqName))");
        String internalName = jvmClassNameByClassId.getInternalName();
        Intrinsics.checkExpressionValueIsNotNull(internalName, "JvmClassName.byClassId(C…vel(fqName)).internalName");
        return internalName;
    }

    private static final <T> T mapBuiltInType(KotlinType kotlinType, JvmTypeFactory<T> jvmTypeFactory, TypeMappingMode typeMappingMode) {
        ClassId classIdMapKotlinToJava;
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = kotlinType.getConstructor().mo1333getDeclarationDescriptor();
        if (!(classifierDescriptorMo1333getDeclarationDescriptor instanceof ClassDescriptor)) {
            classifierDescriptorMo1333getDeclarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo1333getDeclarationDescriptor;
        if (classDescriptor != null) {
            if (classDescriptor == SuspendFunctionTypesKt.getFAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL()) {
                return jvmTypeFactory.createObjectType(continuationInternalName(false));
            }
            if (Intrinsics.areEqual(classDescriptor, SuspendFunctionTypesKt.getFAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE())) {
                return jvmTypeFactory.createObjectType(continuationInternalName(true));
            }
            ClassDescriptor classDescriptor2 = classDescriptor;
            PrimitiveType primitiveType = KotlinBuiltIns.getPrimitiveType(classDescriptor2);
            if (primitiveType != null) {
                JvmPrimitiveType jvmPrimitiveType = JvmPrimitiveType.get(primitiveType);
                Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType, "JvmPrimitiveType.get(primitiveType)");
                String desc = jvmPrimitiveType.getDesc();
                Intrinsics.checkExpressionValueIsNotNull(desc, "JvmPrimitiveType.get(primitiveType).desc");
                return (T) boxTypeIfNeeded(jvmTypeFactory, jvmTypeFactory.createFromString(desc), TypeUtils.isNullableType(kotlinType) || TypeEnhancementKt.hasEnhancedNullability(kotlinType));
            }
            PrimitiveType primitiveArrayType = KotlinBuiltIns.getPrimitiveArrayType(classDescriptor2);
            if (primitiveArrayType != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                JvmPrimitiveType jvmPrimitiveType2 = JvmPrimitiveType.get(primitiveArrayType);
                Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType2, "JvmPrimitiveType.get(arrayElementType)");
                sb.append(jvmPrimitiveType2.getDesc());
                return jvmTypeFactory.createFromString(sb.toString());
            }
            if (KotlinBuiltIns.isUnderKotlinPackage(classDescriptor2) && (classIdMapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(DescriptorUtilsKt.getFqNameUnsafe(classDescriptor2))) != null) {
                if (!typeMappingMode.getKotlinCollectionsToJavaCollections()) {
                    List<JavaToKotlinClassMap.PlatformMutabilityMapping> mutabilityMappings = JavaToKotlinClassMap.INSTANCE.getMutabilityMappings();
                    if (!(mutabilityMappings instanceof Collection) || !mutabilityMappings.isEmpty()) {
                        Iterator<T> it = mutabilityMappings.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (Intrinsics.areEqual(((JavaToKotlinClassMap.PlatformMutabilityMapping) it.next()).getJavaClass(), classIdMapKotlinToJava)) {
                                z = true;
                                break;
                            }
                        }
                    }
                    if (z) {
                        return null;
                    }
                }
                JvmClassName jvmClassNameByClassId = JvmClassName.byClassId(classIdMapKotlinToJava);
                Intrinsics.checkExpressionValueIsNotNull(jvmClassNameByClassId, "JvmClassName.byClassId(classId)");
                String internalName = jvmClassNameByClassId.getInternalName();
                Intrinsics.checkExpressionValueIsNotNull(internalName, "JvmClassName.byClassId(classId).internalName");
                return jvmTypeFactory.createObjectType(internalName);
            }
        }
        return null;
    }

    public static final KotlinType computeUnderlyingType(KotlinType inlineClassType) {
        KotlinType kotlinTypeUnsubstitutedUnderlyingType;
        TypeConstructor constructor;
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor;
        Intrinsics.checkParameterIsNotNull(inlineClassType, "inlineClassType");
        if (!shouldUseUnderlyingType(inlineClassType) || (kotlinTypeUnsubstitutedUnderlyingType = InlineClassesUtilsKt.unsubstitutedUnderlyingType(inlineClassType)) == null || (constructor = kotlinTypeUnsubstitutedUnderlyingType.getConstructor()) == null || (classifierDescriptorMo1333getDeclarationDescriptor = constructor.mo1333getDeclarationDescriptor()) == null) {
            return null;
        }
        if (classifierDescriptorMo1333getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return getRepresentativeUpperBound((TypeParameterDescriptor) classifierDescriptorMo1333getDeclarationDescriptor);
        }
        return InlineClassesUtilsKt.substitutedUnderlyingType(inlineClassType);
    }

    public static final boolean shouldUseUnderlyingType(KotlinType inlineClassType) {
        Intrinsics.checkParameterIsNotNull(inlineClassType, "inlineClassType");
        KotlinType kotlinTypeUnsubstitutedUnderlyingType = InlineClassesUtilsKt.unsubstitutedUnderlyingType(inlineClassType);
        if (kotlinTypeUnsubstitutedUnderlyingType != null) {
            return (inlineClassType.isMarkedNullable() && (TypeUtils.isNullableType(kotlinTypeUnsubstitutedUnderlyingType) || KotlinBuiltIns.isPrimitiveType(kotlinTypeUnsubstitutedUnderlyingType))) ? false : true;
        }
        return false;
    }

    public static /* bridge */ /* synthetic */ String computeInternalName$default(ClassDescriptor classDescriptor, TypeMappingConfiguration typeMappingConfiguration, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            typeMappingConfiguration = TypeMappingConfigurationImpl.INSTANCE;
        }
        return computeInternalName(classDescriptor, typeMappingConfiguration, z);
    }

    public static final String computeInternalName(ClassDescriptor klass, TypeMappingConfiguration<?> typeMappingConfiguration, boolean z) {
        Intrinsics.checkParameterIsNotNull(klass, "klass");
        Intrinsics.checkParameterIsNotNull(typeMappingConfiguration, "typeMappingConfiguration");
        DeclarationDescriptor containingDeclaration = klass.getContainingDeclaration();
        if (z) {
            containingDeclaration = getContainer(containingDeclaration);
        }
        Name nameSafeIdentifier = SpecialNames.safeIdentifier(klass.getName());
        Intrinsics.checkExpressionValueIsNotNull(nameSafeIdentifier, "SpecialNames.safeIdentifier(klass.name)");
        String name = nameSafeIdentifier.getIdentifier();
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            FqName fqName = ((PackageFragmentDescriptor) containingDeclaration).getFqName();
            if (fqName.isRoot()) {
                Intrinsics.checkExpressionValueIsNotNull(name, "name");
                return name;
            }
            StringBuilder sb = new StringBuilder();
            String strAsString = fqName.asString();
            Intrinsics.checkExpressionValueIsNotNull(strAsString, "fqName.asString()");
            sb.append(StringsKt.replace$default(strAsString, ClassUtils.PACKAGE_SEPARATOR_CHAR, '/', false, 4, (Object) null));
            sb.append('/');
            sb.append(name);
            return sb.toString();
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) (!(containingDeclaration instanceof ClassDescriptor) ? null : containingDeclaration);
        if (classDescriptor == null) {
            throw new IllegalArgumentException("Unexpected container: " + containingDeclaration + " for " + klass);
        }
        String predefinedInternalNameForClass = typeMappingConfiguration.getPredefinedInternalNameForClass(classDescriptor);
        if (predefinedInternalNameForClass == null) {
            predefinedInternalNameForClass = computeInternalName(classDescriptor, typeMappingConfiguration, z);
        }
        return predefinedInternalNameForClass + "$" + name;
    }

    private static final DeclarationDescriptor getContainer(DeclarationDescriptor declarationDescriptor) {
        DeclarationDescriptor declarationDescriptor2 = (ClassDescriptor) (!(declarationDescriptor instanceof ClassDescriptor) ? null : declarationDescriptor);
        if (declarationDescriptor2 == null) {
            declarationDescriptor2 = (PackageFragmentDescriptor) (!(declarationDescriptor instanceof PackageFragmentDescriptor) ? null : declarationDescriptor);
        }
        DeclarationDescriptor declarationDescriptor3 = declarationDescriptor2;
        if (declarationDescriptor3 != null) {
            return declarationDescriptor3;
        }
        if (declarationDescriptor != null) {
            return getContainer(declarationDescriptor.getContainingDeclaration());
        }
        return null;
    }

    private static final KotlinType getRepresentativeUpperBound(TypeParameterDescriptor typeParameterDescriptor) {
        Object obj;
        List<KotlinType> upperBounds = typeParameterDescriptor.getUpperBounds();
        upperBounds.isEmpty();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds, "upperBounds");
        Iterator<T> it = upperBounds.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = ((KotlinType) next).getConstructor().mo1333getDeclarationDescriptor();
            ClassDescriptor classDescriptor = (ClassDescriptor) (classifierDescriptorMo1333getDeclarationDescriptor instanceof ClassDescriptor ? classifierDescriptorMo1333getDeclarationDescriptor : null);
            boolean z = false;
            if (classDescriptor != null && classDescriptor.getKind() != ClassKind.INTERFACE && classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
                z = true;
            }
            if (z) {
                obj = next;
                break;
            }
        }
        KotlinType kotlinType = (KotlinType) obj;
        if (kotlinType != null) {
            return kotlinType;
        }
        Object objFirst = CollectionsKt.first((List<? extends Object>) upperBounds);
        Intrinsics.checkExpressionValueIsNotNull(objFirst, "upperBounds.first()");
        return (KotlinType) objFirst;
    }
}
