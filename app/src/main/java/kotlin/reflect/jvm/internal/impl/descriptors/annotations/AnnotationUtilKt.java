package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* compiled from: annotationUtil.kt */
/* loaded from: classes2.dex */
public final class AnnotationUtilKt {
    private static final Name DEPRECATED_MESSAGE_NAME = Name.identifier("message");
    private static final Name DEPRECATED_REPLACE_WITH_NAME = Name.identifier("replaceWith");
    private static final Name DEPRECATED_LEVEL_NAME = Name.identifier(FirebaseAnalytics.Param.LEVEL);
    private static final Name REPLACE_WITH_EXPRESSION_NAME = Name.identifier("expression");
    private static final Name REPLACE_WITH_IMPORTS_NAME = Name.identifier("imports");
    private static final FqName INLINE_ONLY_ANNOTATION_FQ_NAME = new FqName("kotlin.internal.InlineOnly");

    public static /* bridge */ /* synthetic */ AnnotationDescriptor createDeprecatedAnnotation$default(KotlinBuiltIns kotlinBuiltIns, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            str3 = "WARNING";
        }
        return createDeprecatedAnnotation(kotlinBuiltIns, str, str2, str3);
    }

    public static final AnnotationDescriptor createDeprecatedAnnotation(final KotlinBuiltIns receiver, String message, String replaceWith, String level) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(replaceWith, "replaceWith");
        Intrinsics.checkParameterIsNotNull(level, "level");
        FqName fqName = KotlinBuiltIns.FQ_NAMES.replaceWith;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.replaceWith");
        BuiltInAnnotationDescriptor builtInAnnotationDescriptor = new BuiltInAnnotationDescriptor(receiver, fqName, MapsKt.mapOf(TuplesKt.to(REPLACE_WITH_EXPRESSION_NAME, new StringValue(replaceWith)), TuplesKt.to(REPLACE_WITH_IMPORTS_NAME, new ArrayValue(CollectionsKt.emptyList(), new Function1<ModuleDescriptor, SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUtilKt$createDeprecatedAnnotation$replaceWithAnnotation$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final SimpleType invoke(ModuleDescriptor module) {
                Intrinsics.checkParameterIsNotNull(module, "module");
                SimpleType arrayType = module.getBuiltIns().getArrayType(Variance.INVARIANT, receiver.getStringType());
                Intrinsics.checkExpressionValueIsNotNull(arrayType, "module.builtIns.getArray…ce.INVARIANT, stringType)");
                return arrayType;
            }
        }))));
        FqName fqName2 = KotlinBuiltIns.FQ_NAMES.deprecated;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "KotlinBuiltIns.FQ_NAMES.deprecated");
        Name name = DEPRECATED_LEVEL_NAME;
        ClassId classId = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.deprecationLevel);
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(KotlinB…Q_NAMES.deprecationLevel)");
        Name nameIdentifier = Name.identifier(level);
        Intrinsics.checkExpressionValueIsNotNull(nameIdentifier, "Name.identifier(level)");
        return new BuiltInAnnotationDescriptor(receiver, fqName2, MapsKt.mapOf(TuplesKt.to(DEPRECATED_MESSAGE_NAME, new StringValue(message)), TuplesKt.to(DEPRECATED_REPLACE_WITH_NAME, new AnnotationValue(builtInAnnotationDescriptor)), TuplesKt.to(name, new EnumValue(classId, nameIdentifier))));
    }

    public static final boolean isInlineOnlyOrReifiable(MemberDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver instanceof CallableMemberDescriptor) {
            CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) receiver;
            if (!isReifiable(callableMemberDescriptor)) {
                CallableMemberDescriptor directMember = DescriptorUtils.getDirectMember(callableMemberDescriptor);
                Intrinsics.checkExpressionValueIsNotNull(directMember, "DescriptorUtils.getDirectMember(this)");
                if (isReifiable(directMember) || isInlineOnly(receiver)) {
                }
            }
            return true;
        }
        return false;
    }

    public static final boolean isEffectivelyInlineOnly(MemberDescriptor receiver) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (isInlineOnlyOrReifiable(receiver)) {
            return true;
        }
        if (receiver instanceof FunctionDescriptor) {
            FunctionDescriptor functionDescriptor = (FunctionDescriptor) receiver;
            if (functionDescriptor.isSuspend() && functionDescriptor.isInline()) {
                List<ValueParameterDescriptor> valueParameters = functionDescriptor.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "valueParameters");
                List<ValueParameterDescriptor> list = valueParameters;
                if ((list instanceof Collection) && list.isEmpty()) {
                    z = false;
                    return !z ? true : true;
                }
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (((ValueParameterDescriptor) it.next()).isCrossinline()) {
                        z = true;
                        break;
                    }
                }
                z = false;
                if (!z || Intrinsics.areEqual(functionDescriptor.getVisibility(), Visibilities.PRIVATE)) {
                }
            }
        }
        return false;
    }

    public static final boolean isInlineOnly(MemberDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(receiver instanceof FunctionDescriptor)) {
            return false;
        }
        CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) receiver;
        if (!hasInlineOnlyAnnotation(callableMemberDescriptor)) {
            CallableMemberDescriptor directMember = DescriptorUtils.getDirectMember(callableMemberDescriptor);
            Intrinsics.checkExpressionValueIsNotNull(directMember, "DescriptorUtils.getDirectMember(this)");
            if (!hasInlineOnlyAnnotation(directMember)) {
                return false;
            }
        }
        ((FunctionDescriptor) receiver).isInline();
        return true;
    }

    private static final boolean isReifiable(CallableMemberDescriptor callableMemberDescriptor) {
        List<TypeParameterDescriptor> typeParameters = callableMemberDescriptor.getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "typeParameters");
        List<TypeParameterDescriptor> list = typeParameters;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (TypeParameterDescriptor it : list) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            if (it.isReified()) {
                return true;
            }
        }
        return false;
    }

    private static final boolean hasInlineOnlyAnnotation(CallableMemberDescriptor callableMemberDescriptor) {
        return callableMemberDescriptor.getAnnotations().hasAnnotation(INLINE_ONLY_ANNOTATION_FQ_NAME);
    }
}
