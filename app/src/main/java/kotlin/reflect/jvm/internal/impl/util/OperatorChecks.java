package kotlin.reflect.jvm.internal.impl.util;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.util.MemberKindCheck;
import kotlin.reflect.jvm.internal.impl.util.ReturnsCheck;
import kotlin.reflect.jvm.internal.impl.util.ValueParameterCountCheck;

/* compiled from: modifierChecks.kt */
/* loaded from: classes2.dex */
public final class OperatorChecks extends AbstractModifierChecks {
    public static final OperatorChecks INSTANCE = new OperatorChecks();
    private static final List<Checks> checks;

    static {
        Name GET = OperatorNameConventions.GET;
        Intrinsics.checkExpressionValueIsNotNull(GET, "GET");
        Check[] checkArr = {MemberKindCheck.MemberOrExtension.INSTANCE, new ValueParameterCountCheck.AtLeast(1)};
        Name SET = OperatorNameConventions.SET;
        Intrinsics.checkExpressionValueIsNotNull(SET, "SET");
        Check[] checkArr2 = {MemberKindCheck.MemberOrExtension.INSTANCE, new ValueParameterCountCheck.AtLeast(2)};
        Name GET_VALUE = OperatorNameConventions.GET_VALUE;
        Intrinsics.checkExpressionValueIsNotNull(GET_VALUE, "GET_VALUE");
        Check[] checkArr3 = {MemberKindCheck.MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new ValueParameterCountCheck.AtLeast(2), IsKPropertyCheck.INSTANCE};
        Name SET_VALUE = OperatorNameConventions.SET_VALUE;
        Intrinsics.checkExpressionValueIsNotNull(SET_VALUE, "SET_VALUE");
        Check[] checkArr4 = {MemberKindCheck.MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new ValueParameterCountCheck.AtLeast(3), IsKPropertyCheck.INSTANCE};
        Name PROVIDE_DELEGATE = OperatorNameConventions.PROVIDE_DELEGATE;
        Intrinsics.checkExpressionValueIsNotNull(PROVIDE_DELEGATE, "PROVIDE_DELEGATE");
        Check[] checkArr5 = {MemberKindCheck.MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new ValueParameterCountCheck.Equals(2), IsKPropertyCheck.INSTANCE};
        Name INVOKE = OperatorNameConventions.INVOKE;
        Intrinsics.checkExpressionValueIsNotNull(INVOKE, "INVOKE");
        Check[] checkArr6 = {MemberKindCheck.MemberOrExtension.INSTANCE};
        Name CONTAINS = OperatorNameConventions.CONTAINS;
        Intrinsics.checkExpressionValueIsNotNull(CONTAINS, "CONTAINS");
        Check[] checkArr7 = {MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, ReturnsCheck.ReturnsBoolean.INSTANCE};
        Name ITERATOR = OperatorNameConventions.ITERATOR;
        Intrinsics.checkExpressionValueIsNotNull(ITERATOR, "ITERATOR");
        Check[] checkArr8 = {MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE};
        Name NEXT = OperatorNameConventions.NEXT;
        Intrinsics.checkExpressionValueIsNotNull(NEXT, "NEXT");
        Check[] checkArr9 = {MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE};
        Name HAS_NEXT = OperatorNameConventions.HAS_NEXT;
        Intrinsics.checkExpressionValueIsNotNull(HAS_NEXT, "HAS_NEXT");
        Check[] checkArr10 = {MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE, ReturnsCheck.ReturnsBoolean.INSTANCE};
        Name RANGE_TO = OperatorNameConventions.RANGE_TO;
        Intrinsics.checkExpressionValueIsNotNull(RANGE_TO, "RANGE_TO");
        Check[] checkArr11 = {MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE};
        Name EQUALS = OperatorNameConventions.EQUALS;
        Intrinsics.checkExpressionValueIsNotNull(EQUALS, "EQUALS");
        Check[] checkArr12 = {MemberKindCheck.Member.INSTANCE};
        Name COMPARE_TO = OperatorNameConventions.COMPARE_TO;
        Intrinsics.checkExpressionValueIsNotNull(COMPARE_TO, "COMPARE_TO");
        checks = CollectionsKt.listOf((Object[]) new Checks[]{new Checks(GET, checkArr, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(SET, checkArr2, new Function1<FunctionDescriptor, String>() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(FunctionDescriptor receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                List<ValueParameterDescriptor> valueParameters = receiver.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "valueParameters");
                ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) CollectionsKt.lastOrNull((List) valueParameters);
                boolean z = false;
                if (valueParameterDescriptor != null) {
                    if (!DescriptorUtilsKt.declaresOrInheritsDefaultValue(valueParameterDescriptor) && valueParameterDescriptor.getVarargElementType() == null) {
                        z = true;
                    }
                }
                OperatorChecks operatorChecks = OperatorChecks.INSTANCE;
                if (z) {
                    return null;
                }
                return "last parameter should not have a default value or be a vararg";
            }
        }), new Checks(GET_VALUE, checkArr3, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(SET_VALUE, checkArr4, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(PROVIDE_DELEGATE, checkArr5, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(INVOKE, checkArr6, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(CONTAINS, checkArr7, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(ITERATOR, checkArr8, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(NEXT, checkArr9, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(HAS_NEXT, checkArr10, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(RANGE_TO, checkArr11, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(EQUALS, checkArr12, new Function1<FunctionDescriptor, String>() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2

            /* compiled from: modifierChecks.kt */
            /* renamed from: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2$1, reason: invalid class name */
            static final class AnonymousClass1 extends Lambda implements Function1<DeclarationDescriptor, Boolean> {
                public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                AnonymousClass1() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor) {
                    return Boolean.valueOf(invoke2(declarationDescriptor));
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(DeclarationDescriptor receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    return (receiver instanceof ClassDescriptor) && KotlinBuiltIns.isAny((ClassDescriptor) receiver);
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:17:0x005e  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.String invoke(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r6) {
                /*
                    r5 = this;
                    java.lang.String r0 = "$receiver"
                    kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
                    kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2$1 r0 = kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2.AnonymousClass1.INSTANCE
                    kotlin.reflect.jvm.internal.impl.util.OperatorChecks r1 = kotlin.reflect.jvm.internal.impl.util.OperatorChecks.INSTANCE
                    kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r1 = r6.getContainingDeclaration()
                    java.lang.String r2 = "containingDeclaration"
                    kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                    boolean r0 = r0.invoke2(r1)
                    r1 = 1
                    r2 = 0
                    if (r0 != 0) goto L5f
                    java.util.Collection r6 = r6.getOverriddenDescriptors()
                    java.lang.String r0 = "overriddenDescriptors"
                    kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    boolean r0 = r6 instanceof java.util.Collection
                    if (r0 == 0) goto L34
                    r0 = r6
                    java.util.Collection r0 = (java.util.Collection) r0
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L34
                L32:
                    r6 = 0
                    goto L5b
                L34:
                    java.util.Iterator r6 = r6.iterator()
                L38:
                    boolean r0 = r6.hasNext()
                    if (r0 == 0) goto L32
                    java.lang.Object r0 = r6.next()
                    kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r0
                    kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2$1 r3 = kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2.AnonymousClass1.INSTANCE
                    java.lang.String r4 = "it"
                    kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
                    kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r0.getContainingDeclaration()
                    java.lang.String r4 = "it.containingDeclaration"
                    kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
                    boolean r0 = r3.invoke2(r0)
                    if (r0 == 0) goto L38
                    r6 = 1
                L5b:
                    if (r6 == 0) goto L5e
                    goto L5f
                L5e:
                    r1 = 0
                L5f:
                    if (r1 != 0) goto L64
                    java.lang.String r6 = "must override ''equals()'' in Any"
                    goto L65
                L64:
                    r6 = 0
                L65:
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2.invoke(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor):java.lang.String");
            }
        }), new Checks(COMPARE_TO, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ReturnsCheck.ReturnsInt.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.BINARY_OPERATION_NAMES, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.SIMPLE_UNARY_OPERATION_NAMES, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(CollectionsKt.listOf((Object[]) new Name[]{OperatorNameConventions.INC, OperatorNameConventions.DEC}), new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE}, new Function1<FunctionDescriptor, String>() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$3
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(FunctionDescriptor receiver) {
                boolean zIsSubtypeOf;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                ReceiverParameterDescriptor dispatchReceiverParameter = receiver.getDispatchReceiverParameter();
                if (dispatchReceiverParameter == null) {
                    dispatchReceiverParameter = receiver.getExtensionReceiverParameter();
                }
                OperatorChecks operatorChecks = OperatorChecks.INSTANCE;
                boolean z = false;
                if (dispatchReceiverParameter != null) {
                    KotlinType returnType = receiver.getReturnType();
                    if (returnType != null) {
                        KotlinType type = dispatchReceiverParameter.getType();
                        Intrinsics.checkExpressionValueIsNotNull(type, "receiver.type");
                        zIsSubtypeOf = TypeUtilsKt.isSubtypeOf(returnType, type);
                    } else {
                        zIsSubtypeOf = false;
                    }
                    if (zIsSubtypeOf) {
                        z = true;
                    }
                }
                if (z) {
                    return null;
                }
                return "receiver must be a supertype of the return type";
            }
        }), new Checks(OperatorNameConventions.ASSIGNMENT_OPERATIONS, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ReturnsCheck.ReturnsUnit.INSTANCE, ValueParameterCountCheck.SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.COMPONENT_REGEX, new Check[]{MemberKindCheck.MemberOrExtension.INSTANCE, ValueParameterCountCheck.NoValueParameters.INSTANCE}, (Function1) null, 4, (DefaultConstructorMarker) null)});
    }

    private OperatorChecks() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks
    public List<Checks> getChecks$descriptors() {
        return checks;
    }
}
