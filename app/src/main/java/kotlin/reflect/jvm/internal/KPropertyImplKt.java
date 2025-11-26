package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

/* compiled from: KPropertyImpl.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a \u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0005"}, d2 = {"computeCallerForAccessor", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "isGetter", "", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class KPropertyImplKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.reflect.jvm.internal.FunctionCaller<?> computeCallerForAccessor(final kotlin.reflect.jvm.internal.KPropertyImpl.Accessor<?, ?> r9, final boolean r10) {
        /*
            Method dump skipped, instructions count: 569
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KPropertyImplKt.computeCallerForAccessor(kotlin.reflect.jvm.internal.KPropertyImpl$Accessor, boolean):kotlin.reflect.jvm.internal.FunctionCaller");
    }

    /* compiled from: KPropertyImpl.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isInsideClassCompanionObject", "", "invoke"}, k = 3, mv = {1, 1, 11})
    /* renamed from: kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function0<Boolean> {
        final /* synthetic */ KPropertyImpl.Accessor receiver$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(KPropertyImpl.Accessor accessor) {
            super(0);
            this.receiver$0 = accessor;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            DeclarationDescriptor possibleCompanionObject = this.receiver$0.getProperty().getDescriptor().getContainingDeclaration();
            if (DescriptorUtils.isCompanionObject(possibleCompanionObject)) {
                Intrinsics.checkExpressionValueIsNotNull(possibleCompanionObject, "possibleCompanionObject");
                if (!DescriptorUtils.isInterface(possibleCompanionObject.getContainingDeclaration())) {
                    return true;
                }
            }
            return false;
        }
    }

    /* compiled from: KPropertyImpl.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isInsideJvmInterfaceCompanionObject", "", "invoke"}, k = 3, mv = {1, 1, 11})
    /* renamed from: kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$2, reason: invalid class name */
    static final class AnonymousClass2 extends Lambda implements Function0<Boolean> {
        final /* synthetic */ KPropertyImpl.Accessor receiver$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(KPropertyImpl.Accessor accessor) {
            super(0);
            this.receiver$0 = accessor;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            DeclarationDescriptor possibleCompanionObject = this.receiver$0.getProperty().getDescriptor().getContainingDeclaration();
            if (DescriptorUtils.isCompanionObject(possibleCompanionObject)) {
                Intrinsics.checkExpressionValueIsNotNull(possibleCompanionObject, "possibleCompanionObject");
                if (DescriptorUtils.isInterface(possibleCompanionObject.getContainingDeclaration()) || DescriptorUtils.isAnnotationClass(possibleCompanionObject.getContainingDeclaration())) {
                    return true;
                }
            }
            return false;
        }
    }

    /* compiled from: KPropertyImpl.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isInsideInterfaceCompanionObjectWithJvmField", "", "invoke"}, k = 3, mv = {1, 1, 11})
    /* renamed from: kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$3, reason: invalid class name */
    static final class AnonymousClass3 extends Lambda implements Function0<Boolean> {
        final /* synthetic */ AnonymousClass2 $isInsideJvmInterfaceCompanionObject$2;
        final /* synthetic */ KPropertyImpl.Accessor receiver$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(KPropertyImpl.Accessor accessor, AnonymousClass2 anonymousClass2) {
            super(0);
            this.receiver$0 = accessor;
            this.$isInsideJvmInterfaceCompanionObject$2 = anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            PropertyDescriptor descriptor = this.receiver$0.getProperty().getDescriptor();
            if ((descriptor instanceof DeserializedPropertyDescriptor) && this.$isInsideJvmInterfaceCompanionObject$2.invoke2()) {
                return JvmProtoBufUtil.isMovedFromInterfaceCompanion(((DeserializedPropertyDescriptor) descriptor).getProto());
            }
            return false;
        }
    }

    /* compiled from: KPropertyImpl.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isJvmStaticProperty", "", "invoke"}, k = 3, mv = {1, 1, 11})
    /* renamed from: kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$4, reason: invalid class name */
    static final class AnonymousClass4 extends Lambda implements Function0<Boolean> {
        final /* synthetic */ KPropertyImpl.Accessor receiver$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(KPropertyImpl.Accessor accessor) {
            super(0);
            this.receiver$0 = accessor;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            return this.receiver$0.getProperty().getDescriptor().getAnnotations().mo1326findAnnotation(UtilKt.getJVM_STATIC()) != null;
        }
    }

    /* compiled from: KPropertyImpl.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isNotNullProperty", "", "invoke"}, k = 3, mv = {1, 1, 11})
    /* renamed from: kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$5, reason: invalid class name */
    static final class AnonymousClass5 extends Lambda implements Function0<Boolean> {
        final /* synthetic */ KPropertyImpl.Accessor receiver$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(KPropertyImpl.Accessor accessor) {
            super(0);
            this.receiver$0 = accessor;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            return !TypeUtils.isNullableType(this.receiver$0.getProperty().getDescriptor().getType());
        }
    }
}
