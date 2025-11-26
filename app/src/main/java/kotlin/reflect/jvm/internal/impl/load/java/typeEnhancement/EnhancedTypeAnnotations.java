package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* compiled from: typeEnhancement.kt */
/* loaded from: classes2.dex */
final class EnhancedTypeAnnotations implements Annotations {
    private final FqName fqNameToMatch;

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        return false;
    }

    public EnhancedTypeAnnotations(FqName fqNameToMatch) {
        Intrinsics.checkParameterIsNotNull(fqNameToMatch, "fqNameToMatch");
        this.fqNameToMatch = fqNameToMatch;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return Annotations.DefaultImpls.hasAnnotation(this, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    /* renamed from: findAnnotation */
    public EnhancedTypeAnnotationDescriptor mo1326findAnnotation(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        if (Intrinsics.areEqual(fqName, this.fqNameToMatch)) {
            return EnhancedTypeAnnotationDescriptor.INSTANCE;
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public List<AnnotationWithTarget> getAllAnnotations() {
        EnhancedTypeAnnotations enhancedTypeAnnotations = this;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(enhancedTypeAnnotations, 10));
        Iterator<AnnotationDescriptor> it = enhancedTypeAnnotations.iterator();
        while (it.hasNext()) {
            arrayList.add(new AnnotationWithTarget(it.next(), null));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public List<AnnotationWithTarget> getUseSiteTargetedAnnotations() {
        return CollectionsKt.emptyList();
    }

    @Override // java.lang.Iterable
    public Iterator<AnnotationDescriptor> iterator() {
        return CollectionsKt.emptyList().iterator();
    }
}
