package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.List;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* compiled from: functionTypes.kt */
/* loaded from: classes2.dex */
public final class FunctionTypesKt {
    public static final boolean isFunctionType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = receiver.getConstructor().mo1333getDeclarationDescriptor();
        return (classifierDescriptorMo1333getDeclarationDescriptor != null ? getFunctionalClassKind(classifierDescriptorMo1333getDeclarationDescriptor) : null) == FunctionClassDescriptor.Kind.Function;
    }

    public static final boolean isSuspendFunctionType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = receiver.getConstructor().mo1333getDeclarationDescriptor();
        return (classifierDescriptorMo1333getDeclarationDescriptor != null ? getFunctionalClassKind(classifierDescriptorMo1333getDeclarationDescriptor) : null) == FunctionClassDescriptor.Kind.SuspendFunction;
    }

    public static final boolean isBuiltinFunctionalType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = receiver.getConstructor().mo1333getDeclarationDescriptor();
        FunctionClassDescriptor.Kind functionalClassKind = classifierDescriptorMo1333getDeclarationDescriptor != null ? getFunctionalClassKind(classifierDescriptorMo1333getDeclarationDescriptor) : null;
        return functionalClassKind == FunctionClassDescriptor.Kind.Function || functionalClassKind == FunctionClassDescriptor.Kind.SuspendFunction;
    }

    public static final boolean isBuiltinExtensionFunctionalType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return isBuiltinFunctionalType(receiver) && isTypeAnnotatedWithExtensionFunctionType(receiver);
    }

    private static final boolean isTypeAnnotatedWithExtensionFunctionType(KotlinType kotlinType) {
        Annotations annotations = kotlinType.getAnnotations();
        FqName fqName = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
        return annotations.mo1326findAnnotation(fqName) != null;
    }

    public static final FunctionClassDescriptor.Kind getFunctionalClassKind(DeclarationDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if ((receiver instanceof ClassDescriptor) && KotlinBuiltIns.isUnderKotlinPackage(receiver)) {
            return getFunctionalClassKind(DescriptorUtilsKt.getFqNameUnsafe(receiver));
        }
        return null;
    }

    private static final FunctionClassDescriptor.Kind getFunctionalClassKind(FqNameUnsafe fqNameUnsafe) {
        if (!fqNameUnsafe.isSafe() || fqNameUnsafe.isRoot()) {
            return null;
        }
        BuiltInFictitiousFunctionClassFactory.Companion companion = BuiltInFictitiousFunctionClassFactory.Companion;
        String strAsString = fqNameUnsafe.shortName().asString();
        Intrinsics.checkExpressionValueIsNotNull(strAsString, "shortName().asString()");
        FqName fqNameParent = fqNameUnsafe.toSafe().parent();
        Intrinsics.checkExpressionValueIsNotNull(fqNameParent, "toSafe().parent()");
        return companion.getFunctionalClassKind(strAsString, fqNameParent);
    }

    public static final KotlinType getReceiverTypeFromFunctionType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        isBuiltinFunctionalType(receiver);
        if (isTypeAnnotatedWithExtensionFunctionType(receiver)) {
            return ((TypeProjection) CollectionsKt.first((List) receiver.getArguments())).getType();
        }
        return null;
    }

    public static final KotlinType getReturnTypeFromFunctionType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        isBuiltinFunctionalType(receiver);
        KotlinType type = ((TypeProjection) CollectionsKt.last((List) receiver.getArguments())).getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "arguments.last().type");
        return type;
    }

    public static final List<TypeProjection> getValueParameterTypesFromFunctionType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        isBuiltinFunctionalType(receiver);
        return receiver.getArguments().subList(isBuiltinExtensionFunctionalType(receiver) ? 1 : 0, r0.size() - 1);
    }

    public static final Name extractParameterNameFromFunctionTypeArgument(KotlinType receiver) {
        String value;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Annotations annotations = receiver.getAnnotations();
        FqName fqName = KotlinBuiltIns.FQ_NAMES.parameterName;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.parameterName");
        AnnotationDescriptor annotationDescriptorMo1326findAnnotation = annotations.mo1326findAnnotation(fqName);
        if (annotationDescriptorMo1326findAnnotation != null) {
            Object objSingleOrNull = CollectionsKt.singleOrNull(annotationDescriptorMo1326findAnnotation.getAllValueArguments().values());
            if (!(objSingleOrNull instanceof StringValue)) {
                objSingleOrNull = null;
            }
            StringValue stringValue = (StringValue) objSingleOrNull;
            if (stringValue != null && (value = stringValue.getValue()) != null) {
                if (!Name.isValidIdentifier(value)) {
                    value = null;
                }
                if (value != null) {
                    return Name.identifier(value);
                }
            }
        }
        return null;
    }

    public static final List<TypeProjection> getFunctionTypeArgumentProjections(KotlinType kotlinType, List<? extends KotlinType> parameterTypes, List<Name> list, KotlinType returnType, KotlinBuiltIns builtIns) {
        Name name;
        Intrinsics.checkParameterIsNotNull(parameterTypes, "parameterTypes");
        Intrinsics.checkParameterIsNotNull(returnType, "returnType");
        Intrinsics.checkParameterIsNotNull(builtIns, "builtIns");
        int i = 0;
        ArrayList arrayList = new ArrayList(parameterTypes.size() + (kotlinType != null ? 1 : 0) + 1);
        ArrayList arrayList2 = arrayList;
        kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList2, kotlinType != null ? TypeUtilsKt.asTypeProjection(kotlinType) : null);
        for (KotlinType kotlinTypeReplaceAnnotations : parameterTypes) {
            int i2 = i + 1;
            if (list == null || (name = list.get(i)) == null || name.isSpecial()) {
                name = null;
            }
            if (name != null) {
                FqName fqName = KotlinBuiltIns.FQ_NAMES.parameterName;
                Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.parameterName");
                Name nameIdentifier = Name.identifier("name");
                String strAsString = name.asString();
                Intrinsics.checkExpressionValueIsNotNull(strAsString, "name.asString()");
                kotlinTypeReplaceAnnotations = TypeUtilsKt.replaceAnnotations(kotlinTypeReplaceAnnotations, new AnnotationsImpl(CollectionsKt.plus(kotlinTypeReplaceAnnotations.getAnnotations(), new BuiltInAnnotationDescriptor(builtIns, fqName, MapsKt.mapOf(TuplesKt.to(nameIdentifier, new StringValue(strAsString)))))));
            }
            arrayList2.add(TypeUtilsKt.asTypeProjection(kotlinTypeReplaceAnnotations));
            i = i2;
        }
        arrayList.add(TypeUtilsKt.asTypeProjection(returnType));
        return arrayList;
    }

    public static final SimpleType createFunctionType(KotlinBuiltIns builtIns, Annotations annotations, KotlinType kotlinType, List<? extends KotlinType> parameterTypes, List<Name> list, KotlinType returnType, boolean z) {
        Intrinsics.checkParameterIsNotNull(builtIns, "builtIns");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(parameterTypes, "parameterTypes");
        Intrinsics.checkParameterIsNotNull(returnType, "returnType");
        List<TypeProjection> functionTypeArgumentProjections = getFunctionTypeArgumentProjections(kotlinType, parameterTypes, list, returnType, builtIns);
        int size = parameterTypes.size();
        if (kotlinType != null) {
            size++;
        }
        ClassDescriptor classDescriptor = z ? builtIns.getSuspendFunction(size) : builtIns.getFunction(size);
        if (kotlinType != null) {
            FqName fqName = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
            Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
            if (annotations.mo1326findAnnotation(fqName) == null) {
                FqName fqName2 = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
                Intrinsics.checkExpressionValueIsNotNull(fqName2, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
                annotations = new AnnotationsImpl(CollectionsKt.plus(annotations, new BuiltInAnnotationDescriptor(builtIns, fqName2, MapsKt.emptyMap())));
            }
        }
        Intrinsics.checkExpressionValueIsNotNull(classDescriptor, "classDescriptor");
        return KotlinTypeFactory.simpleNotNullType(annotations, classDescriptor, functionTypeArgumentProjections);
    }
}
