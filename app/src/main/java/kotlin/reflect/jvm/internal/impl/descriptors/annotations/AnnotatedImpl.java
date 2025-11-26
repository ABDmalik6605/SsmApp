package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

/* loaded from: classes2.dex */
public abstract class AnnotatedImpl implements Annotated {
    private final Annotations annotations;

    public AnnotatedImpl(Annotations annotations) {
        this.annotations = annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return this.annotations;
    }
}
