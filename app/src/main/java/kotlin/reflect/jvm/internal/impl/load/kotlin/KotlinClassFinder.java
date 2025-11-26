package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* compiled from: KotlinClassFinder.kt */
/* loaded from: classes2.dex */
public interface KotlinClassFinder {
    KotlinJvmBinaryClass findKotlinClass(JavaClass javaClass);

    KotlinJvmBinaryClass findKotlinClass(ClassId classId);
}
