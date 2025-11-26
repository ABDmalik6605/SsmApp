package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* compiled from: inlineClassesUtils.kt */
/* loaded from: classes2.dex */
public final class InlineClassesUtilsKt {
    public static final ValueParameterDescriptor underlyingRepresentation(ClassDescriptor receiver) {
        ClassConstructorDescriptor classConstructorDescriptorMo1325getUnsubstitutedPrimaryConstructor;
        List<ValueParameterDescriptor> valueParameters;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!receiver.mo1338isInline() || (classConstructorDescriptorMo1325getUnsubstitutedPrimaryConstructor = receiver.mo1325getUnsubstitutedPrimaryConstructor()) == null || (valueParameters = classConstructorDescriptorMo1325getUnsubstitutedPrimaryConstructor.getValueParameters()) == null) {
            return null;
        }
        return (ValueParameterDescriptor) CollectionsKt.singleOrNull((List) valueParameters);
    }

    public static final boolean isInlineClass(DeclarationDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (receiver instanceof ClassDescriptor) && ((ClassDescriptor) receiver).mo1338isInline();
    }

    public static final ValueParameterDescriptor unsubstitutedUnderlyingParameter(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = receiver.getConstructor().mo1333getDeclarationDescriptor();
        if (!(classifierDescriptorMo1333getDeclarationDescriptor instanceof ClassDescriptor)) {
            classifierDescriptorMo1333getDeclarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo1333getDeclarationDescriptor;
        if (classDescriptor != null) {
            return underlyingRepresentation(classDescriptor);
        }
        return null;
    }

    public static final KotlinType unsubstitutedUnderlyingType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ValueParameterDescriptor valueParameterDescriptorUnsubstitutedUnderlyingParameter = unsubstitutedUnderlyingParameter(receiver);
        if (valueParameterDescriptorUnsubstitutedUnderlyingParameter != null) {
            return valueParameterDescriptorUnsubstitutedUnderlyingParameter.getType();
        }
        return null;
    }

    public static final boolean isInlineClassType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = receiver.getConstructor().mo1333getDeclarationDescriptor();
        if (classifierDescriptorMo1333getDeclarationDescriptor != null) {
            return isInlineClass(classifierDescriptorMo1333getDeclarationDescriptor);
        }
        return false;
    }

    public static final KotlinType substitutedUnderlyingType(KotlinType receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ValueParameterDescriptor valueParameterDescriptorUnsubstitutedUnderlyingParameter = unsubstitutedUnderlyingParameter(receiver);
        if (valueParameterDescriptorUnsubstitutedUnderlyingParameter == null) {
            return null;
        }
        MemberScope memberScope = receiver.getMemberScope();
        Name name = valueParameterDescriptorUnsubstitutedUnderlyingParameter.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "parameter.name");
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) CollectionsKt.singleOrNull(memberScope.getContributedVariables(name, NoLookupLocation.FOR_ALREADY_TRACKED));
        if (propertyDescriptor != null) {
            return propertyDescriptor.getType();
        }
        return null;
    }
}
