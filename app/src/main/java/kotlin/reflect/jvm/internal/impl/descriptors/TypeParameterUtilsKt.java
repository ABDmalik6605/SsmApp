package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: typeParameterUtils.kt */
/* loaded from: classes2.dex */
public final class TypeParameterUtilsKt {
    public static final List<TypeParameterDescriptor> computeConstructorTypeParameters(ClassifierDescriptorWithTypeParameters receiver) {
        List<TypeParameterDescriptor> listEmptyList;
        DeclarationDescriptor next;
        TypeConstructor typeConstructor;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        List<TypeParameterDescriptor> declaredParameters = receiver.getDeclaredTypeParameters();
        if (!receiver.mo1339isInner() && !(receiver.getContainingDeclaration() instanceof CallableDescriptor)) {
            Intrinsics.checkExpressionValueIsNotNull(declaredParameters, "declaredParameters");
            return declaredParameters;
        }
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters = receiver;
        List list = SequencesKt.toList(SequencesKt.flatMap(SequencesKt.takeWhile(DescriptorUtilsKt.getParents(classifierDescriptorWithTypeParameters), new Function1<DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor) {
                return Boolean.valueOf(invoke2(declarationDescriptor));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(DeclarationDescriptor it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it instanceof CallableDescriptor;
            }
        }), new Function1<DeclarationDescriptor, Sequence<? extends TypeParameterDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2
            @Override // kotlin.jvm.functions.Function1
            public final Sequence<TypeParameterDescriptor> invoke(DeclarationDescriptor it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                List<TypeParameterDescriptor> typeParameters = ((CallableDescriptor) it).getTypeParameters();
                Intrinsics.checkExpressionValueIsNotNull(typeParameters, "(it as CallableDescriptor).typeParameters");
                return CollectionsKt.asSequence(typeParameters);
            }
        }));
        Iterator<DeclarationDescriptor> it = DescriptorUtilsKt.getParents(classifierDescriptorWithTypeParameters).iterator();
        while (true) {
            listEmptyList = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next instanceof ClassDescriptor) {
                break;
            }
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) next;
        if (classDescriptor != null && (typeConstructor = classDescriptor.getTypeConstructor()) != null) {
            listEmptyList = typeConstructor.getParameters();
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        if (list.isEmpty() && listEmptyList.isEmpty()) {
            List<TypeParameterDescriptor> declaredTypeParameters = receiver.getDeclaredTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "declaredTypeParameters");
            return declaredTypeParameters;
        }
        List<TypeParameterDescriptor> listPlus = CollectionsKt.plus((Collection) list, (Iterable) listEmptyList);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        for (TypeParameterDescriptor it2 : listPlus) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            arrayList.add(capturedCopyForInnerDeclaration(it2, classifierDescriptorWithTypeParameters, declaredParameters.size()));
        }
        Intrinsics.checkExpressionValueIsNotNull(declaredParameters, "declaredParameters");
        return CollectionsKt.plus((Collection) declaredParameters, (Iterable) arrayList);
    }

    private static final CapturedTypeParameterDescriptor capturedCopyForInnerDeclaration(TypeParameterDescriptor typeParameterDescriptor, DeclarationDescriptor declarationDescriptor, int i) {
        return new CapturedTypeParameterDescriptor(typeParameterDescriptor, declarationDescriptor, i);
    }

    public static final PossiblyInnerType buildPossiblyInnerType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = receiver.getConstructor().mo1333getDeclarationDescriptor();
        if (!(classifierDescriptorMo1333getDeclarationDescriptor instanceof ClassifierDescriptorWithTypeParameters)) {
            classifierDescriptorMo1333getDeclarationDescriptor = null;
        }
        return buildPossiblyInnerType(receiver, (ClassifierDescriptorWithTypeParameters) classifierDescriptorMo1333getDeclarationDescriptor, 0);
    }

    private static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType, ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters, int i) {
        if (classifierDescriptorWithTypeParameters != null) {
            ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters2 = classifierDescriptorWithTypeParameters;
            if (!ErrorUtils.isError(classifierDescriptorWithTypeParameters2)) {
                int size = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters().size() + i;
                if (!classifierDescriptorWithTypeParameters.mo1339isInner()) {
                    if (size != kotlinType.getArguments().size()) {
                        DescriptorUtils.isLocal(classifierDescriptorWithTypeParameters2);
                    }
                    return new PossiblyInnerType(classifierDescriptorWithTypeParameters, kotlinType.getArguments().subList(i, kotlinType.getArguments().size()), null);
                }
                List<TypeProjection> listSubList = kotlinType.getArguments().subList(i, size);
                DeclarationDescriptor containingDeclaration = classifierDescriptorWithTypeParameters.getContainingDeclaration();
                return new PossiblyInnerType(classifierDescriptorWithTypeParameters, listSubList, buildPossiblyInnerType(kotlinType, (ClassifierDescriptorWithTypeParameters) (containingDeclaration instanceof ClassifierDescriptorWithTypeParameters ? containingDeclaration : null), size));
            }
        }
        return null;
    }
}
