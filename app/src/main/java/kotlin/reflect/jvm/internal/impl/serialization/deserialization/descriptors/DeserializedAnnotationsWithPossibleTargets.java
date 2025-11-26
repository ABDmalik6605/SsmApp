package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.sequences.SequencesKt;

/* compiled from: DeserializedAnnotations.kt */
/* loaded from: classes2.dex */
public class DeserializedAnnotationsWithPossibleTargets implements Annotations {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedAnnotationsWithPossibleTargets.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    private final NotNullLazyValue annotations$delegate;

    private final List<AnnotationWithTarget> getAnnotations() {
        return (List) StorageKt.getValue(this.annotations$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    public DeserializedAnnotationsWithPossibleTargets(StorageManager storageManager, Function0<? extends List<AnnotationWithTarget>> compute) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(compute, "compute");
        this.annotations$delegate = storageManager.createLazyValue(compute);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return Annotations.DefaultImpls.hasAnnotation(this, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        return getAnnotations().isEmpty();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    /* renamed from: findAnnotation */
    public AnnotationDescriptor mo1326findAnnotation(FqName fqName) {
        Object next;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Iterator<T> it = getAnnotations().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            AnnotationWithTarget annotationWithTarget = (AnnotationWithTarget) next;
            if (annotationWithTarget.component2() == null && Intrinsics.areEqual(annotationWithTarget.component1().getFqName(), fqName)) {
                break;
            }
        }
        AnnotationWithTarget annotationWithTarget2 = (AnnotationWithTarget) next;
        if (annotationWithTarget2 != null) {
            return annotationWithTarget2.getAnnotation();
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public List<AnnotationWithTarget> getUseSiteTargetedAnnotations() {
        List<AnnotationWithTarget> annotations = getAnnotations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : annotations) {
            if (((AnnotationWithTarget) obj).getTarget() != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public List<AnnotationWithTarget> getAllAnnotations() {
        return getAnnotations();
    }

    @Override // java.lang.Iterable
    public Iterator<AnnotationDescriptor> iterator() {
        return SequencesKt.map(SequencesKt.filter(CollectionsKt.asSequence(getAnnotations()), new Function1<AnnotationWithTarget, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotationsWithPossibleTargets.iterator.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(AnnotationWithTarget annotationWithTarget) {
                return Boolean.valueOf(invoke2(annotationWithTarget));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(AnnotationWithTarget it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getTarget() == null;
            }
        }), new Function1<AnnotationWithTarget, AnnotationDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotationsWithPossibleTargets.iterator.2
            @Override // kotlin.jvm.functions.Function1
            public final AnnotationDescriptor invoke(AnnotationWithTarget it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getAnnotation();
            }
        }).iterator();
    }
}
