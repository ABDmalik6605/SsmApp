package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;

/* loaded from: classes2.dex */
public interface ClassifierDescriptorWithTypeParameters extends ClassifierDescriptor, DeclarationDescriptorWithVisibility, MemberDescriptor, Substitutable<ClassifierDescriptorWithTypeParameters> {
    List<TypeParameterDescriptor> getDeclaredTypeParameters();

    /* renamed from: isInner */
    boolean mo1339isInner();
}
