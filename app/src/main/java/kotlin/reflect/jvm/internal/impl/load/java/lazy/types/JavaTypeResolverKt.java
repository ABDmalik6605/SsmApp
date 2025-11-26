package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* compiled from: JavaTypeResolver.kt */
/* loaded from: classes2.dex */
public final class JavaTypeResolverKt {
    private static final FqName JAVA_LANG_CLASS_FQ_NAME = new FqName("java.lang.Class");

    public static final TypeProjection makeStarProjection(TypeParameterDescriptor typeParameter, JavaTypeAttributes attr) {
        Intrinsics.checkParameterIsNotNull(typeParameter, "typeParameter");
        Intrinsics.checkParameterIsNotNull(attr, "attr");
        if (attr.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) {
            return new TypeProjectionImpl(StarProjectionImplKt.starProjectionType(typeParameter));
        }
        return new StarProjectionImpl(typeParameter);
    }

    public static /* bridge */ /* synthetic */ JavaTypeAttributes toAttributes$default(TypeUsage typeUsage, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            typeParameterDescriptor = (TypeParameterDescriptor) null;
        }
        return toAttributes(typeUsage, z, typeParameterDescriptor);
    }

    public static final JavaTypeAttributes toAttributes(TypeUsage receiver, boolean z, TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new JavaTypeAttributes(receiver, null, z, typeParameterDescriptor, 2, null);
    }

    public static /* bridge */ /* synthetic */ KotlinType getErasedUpperBound$default(final TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            typeParameterDescriptor2 = (TypeParameterDescriptor) null;
        }
        if ((i & 2) != 0) {
            function0 = new Function0<SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt.getErasedUpperBound.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final SimpleType invoke() {
                    SimpleType simpleTypeCreateErrorType = ErrorUtils.createErrorType("Can't compute erased upper bound of type parameter `" + typeParameterDescriptor + '`');
                    Intrinsics.checkExpressionValueIsNotNull(simpleTypeCreateErrorType, "ErrorUtils.createErrorTy… type parameter `$this`\")");
                    return simpleTypeCreateErrorType;
                }
            };
        }
        return getErasedUpperBound(typeParameterDescriptor, typeParameterDescriptor2, function0);
    }

    public static final KotlinType getErasedUpperBound(TypeParameterDescriptor receiver, TypeParameterDescriptor typeParameterDescriptor, Function0<? extends KotlinType> defaultValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(defaultValue, "defaultValue");
        if (receiver == typeParameterDescriptor) {
            return defaultValue.invoke();
        }
        List<KotlinType> upperBounds = receiver.getUpperBounds();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds, "upperBounds");
        KotlinType firstUpperBound = (KotlinType) CollectionsKt.first((List) upperBounds);
        if (firstUpperBound.getConstructor().mo1333getDeclarationDescriptor() instanceof ClassDescriptor) {
            Intrinsics.checkExpressionValueIsNotNull(firstUpperBound, "firstUpperBound");
            return TypeUtilsKt.replaceArgumentsWithStarProjections(firstUpperBound);
        }
        if (typeParameterDescriptor != null) {
            receiver = typeParameterDescriptor;
        }
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = firstUpperBound.getConstructor().mo1333getDeclarationDescriptor();
        if (classifierDescriptorMo1333getDeclarationDescriptor == null) {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
        }
        do {
            TypeParameterDescriptor typeParameterDescriptor2 = (TypeParameterDescriptor) classifierDescriptorMo1333getDeclarationDescriptor;
            if (!Intrinsics.areEqual(typeParameterDescriptor2, receiver)) {
                List<KotlinType> upperBounds2 = typeParameterDescriptor2.getUpperBounds();
                Intrinsics.checkExpressionValueIsNotNull(upperBounds2, "current.upperBounds");
                KotlinType nextUpperBound = (KotlinType) CollectionsKt.first((List) upperBounds2);
                if (nextUpperBound.getConstructor().mo1333getDeclarationDescriptor() instanceof ClassDescriptor) {
                    Intrinsics.checkExpressionValueIsNotNull(nextUpperBound, "nextUpperBound");
                    return TypeUtilsKt.replaceArgumentsWithStarProjections(nextUpperBound);
                }
                classifierDescriptorMo1333getDeclarationDescriptor = nextUpperBound.getConstructor().mo1333getDeclarationDescriptor();
            } else {
                return defaultValue.invoke();
            }
        } while (classifierDescriptorMo1333getDeclarationDescriptor != null);
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
    }
}
