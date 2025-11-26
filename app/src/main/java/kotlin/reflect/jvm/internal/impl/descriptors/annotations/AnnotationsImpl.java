package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* compiled from: AnnotationsImpl.kt */
/* loaded from: classes2.dex */
public final class AnnotationsImpl implements Annotations {
    public static final Companion Companion = new Companion(null);
    private final List<AnnotationDescriptor> annotations;
    private final List<AnnotationWithTarget> targetedAnnotations;

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    /* renamed from: findAnnotation */
    public AnnotationDescriptor mo1326findAnnotation(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return Annotations.DefaultImpls.findAnnotation(this, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return Annotations.DefaultImpls.hasAnnotation(this, fqName);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AnnotationsImpl(List<? extends AnnotationDescriptor> annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        this.annotations = annotations;
        List<? extends AnnotationDescriptor> list = annotations;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new AnnotationWithTarget((AnnotationDescriptor) it.next(), null));
        }
        this.targetedAnnotations = arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        return this.targetedAnnotations.isEmpty();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public List<AnnotationWithTarget> getUseSiteTargetedAnnotations() {
        List<AnnotationWithTarget> list = this.targetedAnnotations;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((AnnotationWithTarget) obj).getTarget() != null) {
                arrayList.add(obj);
            }
        }
        ArrayList<AnnotationWithTarget> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (AnnotationWithTarget annotationWithTarget : arrayList2) {
            AnnotationDescriptor annotation = annotationWithTarget.getAnnotation();
            AnnotationUseSiteTarget target = annotationWithTarget.getTarget();
            if (target == null) {
                Intrinsics.throwNpe();
            }
            arrayList3.add(new AnnotationWithTarget(annotation, target));
        }
        return arrayList3;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public List<AnnotationWithTarget> getAllAnnotations() {
        return this.targetedAnnotations;
    }

    @Override // java.lang.Iterable
    public Iterator<AnnotationDescriptor> iterator() {
        return this.annotations.iterator();
    }

    public String toString() {
        return this.annotations.toString();
    }

    /* compiled from: AnnotationsImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
