package kotlin.reflect.jvm.internal.impl.descriptors;

/* loaded from: classes2.dex */
public interface MemberDescriptor extends DeclarationDescriptorNonRoot, DeclarationDescriptorWithVisibility {
    Modality getModality();

    Visibility getVisibility();

    boolean isActual();

    /* renamed from: isExpect */
    boolean mo1336isExpect();

    /* renamed from: isExternal */
    boolean mo1337isExternal();
}
