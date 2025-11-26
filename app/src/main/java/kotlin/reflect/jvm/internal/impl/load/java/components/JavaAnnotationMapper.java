package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: JavaAnnotationMapper.kt */
/* loaded from: classes2.dex */
public final class JavaAnnotationMapper {
    private static final Name DEPRECATED_ANNOTATION_MESSAGE;
    public static final JavaAnnotationMapper INSTANCE = new JavaAnnotationMapper();
    private static final FqName JAVA_DEPRECATED_FQ_NAME;
    private static final FqName JAVA_DOCUMENTED_FQ_NAME;
    private static final FqName JAVA_REPEATABLE_FQ_NAME;
    private static final FqName JAVA_RETENTION_FQ_NAME;
    private static final FqName JAVA_TARGET_FQ_NAME;
    private static final Name RETENTION_ANNOTATION_VALUE;
    private static final Name TARGET_ANNOTATION_ALLOWED_TARGETS;
    private static final Map<FqName, FqName> javaToKotlinNameMap;
    private static final Map<FqName, FqName> kotlinToJavaNameMap;

    static {
        FqName fqName = new FqName(Target.class.getCanonicalName());
        JAVA_TARGET_FQ_NAME = fqName;
        FqName fqName2 = new FqName(Retention.class.getCanonicalName());
        JAVA_RETENTION_FQ_NAME = fqName2;
        FqName fqName3 = new FqName(Deprecated.class.getCanonicalName());
        JAVA_DEPRECATED_FQ_NAME = fqName3;
        FqName fqName4 = new FqName(Documented.class.getCanonicalName());
        JAVA_DOCUMENTED_FQ_NAME = fqName4;
        FqName fqName5 = new FqName("java.lang.annotation.Repeatable");
        JAVA_REPEATABLE_FQ_NAME = fqName5;
        DEPRECATED_ANNOTATION_MESSAGE = Name.identifier("message");
        TARGET_ANNOTATION_ALLOWED_TARGETS = Name.identifier("allowedTargets");
        RETENTION_ANNOTATION_VALUE = Name.identifier("value");
        kotlinToJavaNameMap = MapsKt.mapOf(TuplesKt.to(KotlinBuiltIns.FQ_NAMES.target, fqName), TuplesKt.to(KotlinBuiltIns.FQ_NAMES.retention, fqName2), TuplesKt.to(KotlinBuiltIns.FQ_NAMES.repeatable, fqName5), TuplesKt.to(KotlinBuiltIns.FQ_NAMES.mustBeDocumented, fqName4));
        javaToKotlinNameMap = MapsKt.mapOf(TuplesKt.to(fqName, KotlinBuiltIns.FQ_NAMES.target), TuplesKt.to(fqName2, KotlinBuiltIns.FQ_NAMES.retention), TuplesKt.to(fqName3, KotlinBuiltIns.FQ_NAMES.deprecated), TuplesKt.to(fqName5, KotlinBuiltIns.FQ_NAMES.repeatable), TuplesKt.to(fqName4, KotlinBuiltIns.FQ_NAMES.mustBeDocumented));
    }

    private JavaAnnotationMapper() {
    }

    public final Name getDEPRECATED_ANNOTATION_MESSAGE$descriptors_jvm() {
        return DEPRECATED_ANNOTATION_MESSAGE;
    }

    public final Name getTARGET_ANNOTATION_ALLOWED_TARGETS$descriptors_jvm() {
        return TARGET_ANNOTATION_ALLOWED_TARGETS;
    }

    public final Name getRETENTION_ANNOTATION_VALUE$descriptors_jvm() {
        return RETENTION_ANNOTATION_VALUE;
    }

    public final AnnotationDescriptor mapOrResolveJavaAnnotation(JavaAnnotation annotation, LazyJavaResolverContext c) {
        Intrinsics.checkParameterIsNotNull(annotation, "annotation");
        Intrinsics.checkParameterIsNotNull(c, "c");
        ClassId classId = annotation.getClassId();
        if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_TARGET_FQ_NAME))) {
            return new JavaTargetAnnotationDescriptor(annotation, c);
        }
        if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_RETENTION_FQ_NAME))) {
            return new JavaRetentionAnnotationDescriptor(annotation, c);
        }
        if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_REPEATABLE_FQ_NAME))) {
            FqName fqName = KotlinBuiltIns.FQ_NAMES.repeatable;
            Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.repeatable");
            return new JavaAnnotationDescriptor(c, annotation, fqName);
        }
        if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_DOCUMENTED_FQ_NAME))) {
            FqName fqName2 = KotlinBuiltIns.FQ_NAMES.mustBeDocumented;
            Intrinsics.checkExpressionValueIsNotNull(fqName2, "KotlinBuiltIns.FQ_NAMES.mustBeDocumented");
            return new JavaAnnotationDescriptor(c, annotation, fqName2);
        }
        if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_DEPRECATED_FQ_NAME))) {
            return null;
        }
        return new LazyJavaAnnotationDescriptor(c, annotation);
    }

    public final AnnotationDescriptor findMappedJavaAnnotation(FqName kotlinName, JavaAnnotationOwner annotationOwner, LazyJavaResolverContext c) {
        JavaAnnotation javaAnnotationMo1343findAnnotation;
        JavaAnnotation javaAnnotationMo1343findAnnotation2;
        Intrinsics.checkParameterIsNotNull(kotlinName, "kotlinName");
        Intrinsics.checkParameterIsNotNull(annotationOwner, "annotationOwner");
        Intrinsics.checkParameterIsNotNull(c, "c");
        if (Intrinsics.areEqual(kotlinName, KotlinBuiltIns.FQ_NAMES.deprecated) && ((javaAnnotationMo1343findAnnotation2 = annotationOwner.mo1343findAnnotation(JAVA_DEPRECATED_FQ_NAME)) != null || annotationOwner.isDeprecatedInJavaDoc())) {
            return new JavaDeprecatedAnnotationDescriptor(javaAnnotationMo1343findAnnotation2, c);
        }
        FqName fqName = kotlinToJavaNameMap.get(kotlinName);
        if (fqName == null || (javaAnnotationMo1343findAnnotation = annotationOwner.mo1343findAnnotation(fqName)) == null) {
            return null;
        }
        return INSTANCE.mapOrResolveJavaAnnotation(javaAnnotationMo1343findAnnotation, c);
    }
}
