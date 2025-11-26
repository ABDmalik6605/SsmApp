package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

/* compiled from: StarProjectionImpl.kt */
/* loaded from: classes2.dex */
public final class StarProjectionImplKt {
    public static final KotlinType starProjectionType(TypeParameterDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        DeclarationDescriptor containingDeclaration = receiver.getContainingDeclaration();
        if (containingDeclaration == null) {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters");
        }
        TypeConstructor typeConstructor = ((ClassifierDescriptorWithTypeParameters) containingDeclaration).getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "classDescriptor.typeConstructor");
        List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "classDescriptor.typeConstructor.parameters");
        List<TypeParameterDescriptor> list = parameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (TypeParameterDescriptor it : list) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            arrayList.add(it.getTypeConstructor());
        }
        final ArrayList arrayList2 = arrayList;
        TypeSubstitutor typeSubstitutorCreate = TypeSubstitutor.create(new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt.starProjectionType.1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
            public TypeProjection get(TypeConstructor key) {
                Intrinsics.checkParameterIsNotNull(key, "key");
                if (!arrayList2.contains(key)) {
                    return null;
                }
                ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = key.mo1333getDeclarationDescriptor();
                if (classifierDescriptorMo1333getDeclarationDescriptor != null) {
                    return TypeUtils.makeStarProjection((TypeParameterDescriptor) classifierDescriptorMo1333getDeclarationDescriptor);
                }
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
            }
        });
        List<KotlinType> upperBounds = receiver.getUpperBounds();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds, "this.upperBounds");
        KotlinType kotlinTypeSubstitute = typeSubstitutorCreate.substitute((KotlinType) CollectionsKt.first((List) upperBounds), Variance.OUT_VARIANCE);
        if (kotlinTypeSubstitute != null) {
            return kotlinTypeSubstitute;
        }
        SimpleType defaultBound = DescriptorUtilsKt.getBuiltIns(receiver).getDefaultBound();
        Intrinsics.checkExpressionValueIsNotNull(defaultBound, "builtIns.defaultBound");
        return defaultBound;
    }
}
