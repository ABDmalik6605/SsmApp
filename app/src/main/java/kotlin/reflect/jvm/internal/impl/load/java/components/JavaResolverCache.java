package kotlin.reflect.jvm.internal.impl.load.java.components;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* loaded from: classes2.dex */
public interface JavaResolverCache {
    public static final JavaResolverCache EMPTY = new JavaResolverCache() { // from class: kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1
        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
        public ClassDescriptor getClassResolvedFromSource(FqName fqName) {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
        public void recordClass(JavaClass javaClass, ClassDescriptor classDescriptor) {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
        public void recordConstructor(JavaElement javaElement, ConstructorDescriptor constructorDescriptor) {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
        public void recordField(JavaField javaField, PropertyDescriptor propertyDescriptor) {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
        public void recordMethod(JavaMethod javaMethod, SimpleFunctionDescriptor simpleFunctionDescriptor) {
        }
    };

    ClassDescriptor getClassResolvedFromSource(FqName fqName);

    void recordClass(JavaClass javaClass, ClassDescriptor classDescriptor);

    void recordConstructor(JavaElement javaElement, ConstructorDescriptor constructorDescriptor);

    void recordField(JavaField javaField, PropertyDescriptor propertyDescriptor);

    void recordMethod(JavaMethod javaMethod, SimpleFunctionDescriptor simpleFunctionDescriptor);
}
