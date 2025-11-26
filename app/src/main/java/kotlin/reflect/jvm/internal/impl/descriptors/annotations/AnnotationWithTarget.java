package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnnotationWithTarget.kt */
/* loaded from: classes2.dex */
public final class AnnotationWithTarget {
    private final AnnotationDescriptor annotation;
    private final AnnotationUseSiteTarget target;

    public final AnnotationDescriptor component1() {
        return this.annotation;
    }

    public final AnnotationUseSiteTarget component2() {
        return this.target;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnnotationWithTarget)) {
            return false;
        }
        AnnotationWithTarget annotationWithTarget = (AnnotationWithTarget) obj;
        return Intrinsics.areEqual(this.annotation, annotationWithTarget.annotation) && Intrinsics.areEqual(this.target, annotationWithTarget.target);
    }

    public int hashCode() {
        AnnotationDescriptor annotationDescriptor = this.annotation;
        int iHashCode = (annotationDescriptor != null ? annotationDescriptor.hashCode() : 0) * 31;
        AnnotationUseSiteTarget annotationUseSiteTarget = this.target;
        return iHashCode + (annotationUseSiteTarget != null ? annotationUseSiteTarget.hashCode() : 0);
    }

    public String toString() {
        return "AnnotationWithTarget(annotation=" + this.annotation + ", target=" + this.target + ")";
    }

    public AnnotationWithTarget(AnnotationDescriptor annotation, AnnotationUseSiteTarget annotationUseSiteTarget) {
        Intrinsics.checkParameterIsNotNull(annotation, "annotation");
        this.annotation = annotation;
        this.target = annotationUseSiteTarget;
    }

    public final AnnotationDescriptor getAnnotation() {
        return this.annotation;
    }

    public final AnnotationUseSiteTarget getTarget() {
        return this.target;
    }
}
