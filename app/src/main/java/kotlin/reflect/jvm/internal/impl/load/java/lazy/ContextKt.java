package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassOrPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

/* compiled from: context.kt */
/* loaded from: classes2.dex */
public final class ContextKt {
    public static final LazyJavaResolverContext child(LazyJavaResolverContext receiver, TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeParameterResolver, "typeParameterResolver");
        return new LazyJavaResolverContext(receiver.getComponents(), typeParameterResolver, receiver.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    public static final JavaTypeQualifiersByElementType computeNewDefaultTypeQualifiers(LazyJavaResolverContext receiver, Annotations additionalAnnotations) {
        EnumMap<AnnotationTypeQualifierResolver.QualifierApplicabilityType, NullabilityQualifierWithMigrationStatus> nullabilityQualifiers$descriptors_jvm;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(additionalAnnotations, "additionalAnnotations");
        if (receiver.getComponents().getAnnotationTypeQualifierResolver().getDisabled()) {
            return receiver.getDefaultTypeQualifiers();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<AnnotationDescriptor> it = additionalAnnotations.iterator();
        while (it.hasNext()) {
            NullabilityQualifierWithApplicability nullabilityQualifierWithApplicabilityExtractDefaultNullabilityQualifier = extractDefaultNullabilityQualifier(receiver, it.next());
            if (nullabilityQualifierWithApplicabilityExtractDefaultNullabilityQualifier != null) {
                arrayList.add(nullabilityQualifierWithApplicabilityExtractDefaultNullabilityQualifier);
            }
        }
        ArrayList<NullabilityQualifierWithApplicability> arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return receiver.getDefaultTypeQualifiers();
        }
        JavaTypeQualifiersByElementType defaultTypeQualifiers = receiver.getDefaultTypeQualifiers();
        EnumMap enumMap = (defaultTypeQualifiers == null || (nullabilityQualifiers$descriptors_jvm = defaultTypeQualifiers.getNullabilityQualifiers$descriptors_jvm()) == null) ? new EnumMap(AnnotationTypeQualifierResolver.QualifierApplicabilityType.class) : new EnumMap((EnumMap) nullabilityQualifiers$descriptors_jvm);
        boolean z = false;
        for (NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability : arrayList2) {
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusComponent1 = nullabilityQualifierWithApplicability.component1();
            Iterator<AnnotationTypeQualifierResolver.QualifierApplicabilityType> it2 = nullabilityQualifierWithApplicability.component2().iterator();
            while (it2.hasNext()) {
                enumMap.put((EnumMap) it2.next(), (AnnotationTypeQualifierResolver.QualifierApplicabilityType) nullabilityQualifierWithMigrationStatusComponent1);
                z = true;
            }
        }
        return !z ? receiver.getDefaultTypeQualifiers() : new JavaTypeQualifiersByElementType(enumMap);
    }

    private static final NullabilityQualifierWithApplicability extractDefaultNullabilityQualifier(LazyJavaResolverContext lazyJavaResolverContext, AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullability;
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusCopy$default;
        AnnotationTypeQualifierResolver annotationTypeQualifierResolver = lazyJavaResolverContext.getComponents().getAnnotationTypeQualifierResolver();
        NullabilityQualifierWithApplicability nullabilityQualifierWithApplicabilityResolveQualifierBuiltInDefaultAnnotation = annotationTypeQualifierResolver.resolveQualifierBuiltInDefaultAnnotation(annotationDescriptor);
        if (nullabilityQualifierWithApplicabilityResolveQualifierBuiltInDefaultAnnotation != null) {
            return nullabilityQualifierWithApplicabilityResolveQualifierBuiltInDefaultAnnotation;
        }
        AnnotationTypeQualifierResolver.TypeQualifierWithApplicability typeQualifierWithApplicabilityResolveTypeQualifierDefaultAnnotation = annotationTypeQualifierResolver.resolveTypeQualifierDefaultAnnotation(annotationDescriptor);
        if (typeQualifierWithApplicabilityResolveTypeQualifierDefaultAnnotation != null) {
            AnnotationDescriptor annotationDescriptorComponent1 = typeQualifierWithApplicabilityResolveTypeQualifierDefaultAnnotation.component1();
            List<AnnotationTypeQualifierResolver.QualifierApplicabilityType> listComponent2 = typeQualifierWithApplicabilityResolveTypeQualifierDefaultAnnotation.component2();
            ReportLevel reportLevelResolveJsr305CustomState = annotationTypeQualifierResolver.resolveJsr305CustomState(annotationDescriptor);
            if (reportLevelResolveJsr305CustomState == null) {
                reportLevelResolveJsr305CustomState = annotationTypeQualifierResolver.resolveJsr305AnnotationState(annotationDescriptorComponent1);
            }
            if (!reportLevelResolveJsr305CustomState.isIgnore() && (nullabilityQualifierWithMigrationStatusExtractNullability = lazyJavaResolverContext.getComponents().getSignatureEnhancement().extractNullability(annotationDescriptorComponent1)) != null && (nullabilityQualifierWithMigrationStatusCopy$default = NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatusExtractNullability, null, reportLevelResolveJsr305CustomState.isWarning(), 1, null)) != null) {
                return new NullabilityQualifierWithApplicability(nullabilityQualifierWithMigrationStatusCopy$default, listComponent2);
            }
        }
        return null;
    }

    public static final LazyJavaResolverContext replaceComponents(LazyJavaResolverContext receiver, JavaResolverComponents components) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(components, "components");
        return new LazyJavaResolverContext(components, receiver.getTypeParameterResolver(), receiver.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    private static final LazyJavaResolverContext child(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, Lazy<JavaTypeQualifiersByElementType> lazy) {
        LazyJavaTypeParameterResolver typeParameterResolver;
        JavaResolverComponents components = lazyJavaResolverContext.getComponents();
        if (javaTypeParameterListOwner != null) {
            typeParameterResolver = new LazyJavaTypeParameterResolver(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
        } else {
            typeParameterResolver = lazyJavaResolverContext.getTypeParameterResolver();
        }
        return new LazyJavaResolverContext(components, typeParameterResolver, lazy);
    }

    public static /* bridge */ /* synthetic */ LazyJavaResolverContext childForMethod$default(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForMethod(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
    }

    public static final LazyJavaResolverContext childForMethod(LazyJavaResolverContext receiver, DeclarationDescriptor containingDeclaration, JavaTypeParameterListOwner typeParameterOwner, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(typeParameterOwner, "typeParameterOwner");
        return child(receiver, containingDeclaration, typeParameterOwner, i, receiver.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    public static /* bridge */ /* synthetic */ LazyJavaResolverContext childForClassOrPackage$default(LazyJavaResolverContext lazyJavaResolverContext, ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            javaTypeParameterListOwner = (JavaTypeParameterListOwner) null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForClassOrPackage(lazyJavaResolverContext, classOrPackageFragmentDescriptor, javaTypeParameterListOwner, i);
    }

    public static final LazyJavaResolverContext childForClassOrPackage(final LazyJavaResolverContext receiver, final ClassOrPackageFragmentDescriptor containingDeclaration, JavaTypeParameterListOwner javaTypeParameterListOwner, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        return child(receiver, containingDeclaration, javaTypeParameterListOwner, i, LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<JavaTypeQualifiersByElementType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt.childForClassOrPackage.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final JavaTypeQualifiersByElementType invoke() {
                return ContextKt.computeNewDefaultTypeQualifiers(receiver, containingDeclaration.getAnnotations());
            }
        }));
    }

    public static final LazyJavaResolverContext copyWithNewDefaultTypeQualifiers(final LazyJavaResolverContext receiver, final Annotations additionalAnnotations) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(additionalAnnotations, "additionalAnnotations");
        return additionalAnnotations.isEmpty() ? receiver : new LazyJavaResolverContext(receiver.getComponents(), receiver.getTypeParameterResolver(), LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<JavaTypeQualifiersByElementType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt.copyWithNewDefaultTypeQualifiers.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final JavaTypeQualifiersByElementType invoke() {
                return ContextKt.computeNewDefaultTypeQualifiers(receiver, additionalAnnotations);
            }
        }));
    }
}
