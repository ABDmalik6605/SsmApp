package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* compiled from: Annotations.kt */
/* loaded from: classes2.dex */
public interface Annotations extends Iterable<AnnotationDescriptor>, KMappedMarker {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* renamed from: findAnnotation */
    AnnotationDescriptor mo1326findAnnotation(FqName fqName);

    List<AnnotationWithTarget> getAllAnnotations();

    List<AnnotationWithTarget> getUseSiteTargetedAnnotations();

    boolean hasAnnotation(FqName fqName);

    boolean isEmpty();

    /* compiled from: Annotations.kt */
    public static final class DefaultImpls {
        public static AnnotationDescriptor findAnnotation(Annotations annotations, FqName fqName) {
            AnnotationDescriptor next;
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            Iterator<AnnotationDescriptor> it = annotations.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(next.getFqName(), fqName)) {
                    break;
                }
            }
            return next;
        }

        public static boolean hasAnnotation(Annotations annotations, FqName fqName) {
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            return annotations.mo1326findAnnotation(fqName) != null;
        }
    }

    /* compiled from: Annotations.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Annotations EMPTY = new Annotations() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion$EMPTY$1
            public Void findAnnotation(FqName fqName) {
                Intrinsics.checkParameterIsNotNull(fqName, "fqName");
                return null;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            public boolean isEmpty() {
                return true;
            }

            public String toString() {
                return "EMPTY";
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            /* renamed from: findAnnotation, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ AnnotationDescriptor mo1326findAnnotation(FqName fqName) {
                return (AnnotationDescriptor) findAnnotation(fqName);
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            public boolean hasAnnotation(FqName fqName) {
                Intrinsics.checkParameterIsNotNull(fqName, "fqName");
                return Annotations.DefaultImpls.hasAnnotation(this, fqName);
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            public List<AnnotationWithTarget> getUseSiteTargetedAnnotations() {
                return CollectionsKt.emptyList();
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            public List<AnnotationWithTarget> getAllAnnotations() {
                return CollectionsKt.emptyList();
            }

            @Override // java.lang.Iterable
            public Iterator<AnnotationDescriptor> iterator() {
                return CollectionsKt.emptyList().iterator();
            }
        };

        private Companion() {
        }

        public final Annotations getEMPTY() {
            return EMPTY;
        }

        public final AnnotationDescriptor findUseSiteTargetedAnnotation(Annotations annotations, AnnotationUseSiteTarget target, FqName fqName) {
            Object next;
            Intrinsics.checkParameterIsNotNull(annotations, "annotations");
            Intrinsics.checkParameterIsNotNull(target, "target");
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            Iterator<T> it = getUseSiteTargetedAnnotations(annotations, target).iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(((AnnotationDescriptor) next).getFqName(), fqName)) {
                    break;
                }
            }
            return (AnnotationDescriptor) next;
        }

        private final List<AnnotationDescriptor> getUseSiteTargetedAnnotations(Annotations annotations, AnnotationUseSiteTarget annotationUseSiteTarget) {
            List<AnnotationWithTarget> useSiteTargetedAnnotations = annotations.getUseSiteTargetedAnnotations();
            ArrayList arrayList = new ArrayList();
            for (AnnotationWithTarget annotationWithTarget : useSiteTargetedAnnotations) {
                AnnotationDescriptor annotationDescriptorComponent1 = annotationWithTarget.component1();
                if (!(annotationUseSiteTarget == annotationWithTarget.component2())) {
                    annotationDescriptorComponent1 = null;
                }
                if (annotationDescriptorComponent1 != null) {
                    arrayList.add(annotationDescriptorComponent1);
                }
            }
            return arrayList;
        }
    }
}
