package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;

/* loaded from: classes2.dex */
public abstract class DeclarationDescriptorImpl extends AnnotatedImpl implements DeclarationDescriptor {
    private final Name name;

    public DeclarationDescriptor getOriginal() {
        return this;
    }

    public DeclarationDescriptorImpl(Annotations annotations, Name name) {
        super(annotations);
        this.name = name;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.Named
    public Name getName() {
        return this.name;
    }

    public String toString() {
        return toString(this);
    }

    public static String toString(DeclarationDescriptor declarationDescriptor) {
        try {
            return DescriptorRenderer.DEBUG_TEXT.render(declarationDescriptor) + "[" + declarationDescriptor.getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(declarationDescriptor)) + "]";
        } catch (Throwable unused) {
            return declarationDescriptor.getClass().getSimpleName() + " " + declarationDescriptor.getName();
        }
    }
}
