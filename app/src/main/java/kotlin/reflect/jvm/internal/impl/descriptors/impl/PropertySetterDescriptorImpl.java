package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* loaded from: classes2.dex */
public class PropertySetterDescriptorImpl extends PropertyAccessorDescriptorImpl implements PropertySetterDescriptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final PropertySetterDescriptor original;
    private ValueParameterDescriptor parameter;

    /* JADX WARN: Multi-variable type inference failed */
    public PropertySetterDescriptorImpl(PropertyDescriptor propertyDescriptor, Annotations annotations, Modality modality, Visibility visibility, boolean z, boolean z2, boolean z3, CallableMemberDescriptor.Kind kind, PropertySetterDescriptor propertySetterDescriptor, SourceElement sourceElement) {
        PropertySetterDescriptorImpl propertySetterDescriptorImpl;
        PropertySetterDescriptorImpl propertySetterDescriptorImpl2;
        super(modality, visibility, propertyDescriptor, annotations, Name.special("<set-" + propertyDescriptor.getName() + ">"), z, z2, z3, kind, sourceElement);
        if (propertySetterDescriptor != 0) {
            propertySetterDescriptorImpl2 = this;
            propertySetterDescriptorImpl = propertySetterDescriptor;
        } else {
            propertySetterDescriptorImpl = this;
            propertySetterDescriptorImpl2 = propertySetterDescriptorImpl;
        }
        propertySetterDescriptorImpl2.original = propertySetterDescriptorImpl;
    }

    public void initialize(ValueParameterDescriptor valueParameterDescriptor) {
        this.parameter = valueParameterDescriptor;
    }

    public void initializeDefault() {
        this.parameter = createSetterParameter(this, getCorrespondingProperty().getReturnType());
    }

    public static ValueParameterDescriptorImpl createSetterParameter(PropertySetterDescriptor propertySetterDescriptor, KotlinType kotlinType) {
        return new ValueParameterDescriptorImpl(propertySetterDescriptor, null, 0, Annotations.Companion.getEMPTY(), Name.special("<set-?>"), kotlinType, false, false, false, null, SourceElement.NO_SOURCE);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public Collection<? extends PropertySetterDescriptor> getOverriddenDescriptors() {
        return super.getOverriddenDescriptors(false);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List<ValueParameterDescriptor> getValueParameters() {
        ValueParameterDescriptor valueParameterDescriptor = this.parameter;
        if (valueParameterDescriptor == null) {
            throw new IllegalStateException();
        }
        return Collections.singletonList(valueParameterDescriptor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public KotlinType getReturnType() {
        return DescriptorUtilsKt.getBuiltIns(this).getUnitType();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitPropertySetterDescriptor(this, d);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public PropertySetterDescriptor getOriginal() {
        return this.original;
    }
}
