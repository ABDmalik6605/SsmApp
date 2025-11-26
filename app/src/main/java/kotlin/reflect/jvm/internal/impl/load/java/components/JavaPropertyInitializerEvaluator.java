package kotlin.reflect.jvm.internal.impl.load.java.components;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;

/* compiled from: JavaPropertyInitializerEvaluator.kt */
/* loaded from: classes2.dex */
public interface JavaPropertyInitializerEvaluator {
    /* renamed from: getInitializerConstant */
    ConstantValue<?> mo1328getInitializerConstant(JavaField javaField, PropertyDescriptor propertyDescriptor);

    /* compiled from: JavaPropertyInitializerEvaluator.kt */
    public static final class DoNothing implements JavaPropertyInitializerEvaluator {
        public static final DoNothing INSTANCE = new DoNothing();

        public Void getInitializerConstant(JavaField field, PropertyDescriptor descriptor) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            return null;
        }

        private DoNothing() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator
        /* renamed from: getInitializerConstant, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ ConstantValue mo1328getInitializerConstant(JavaField javaField, PropertyDescriptor propertyDescriptor) {
            return (ConstantValue) getInitializerConstant(javaField, propertyDescriptor);
        }
    }
}
