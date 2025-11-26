package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;

/* loaded from: classes2.dex */
public class JavaVisibilities {
    public static final Visibility PACKAGE_VISIBILITY = new Visibility("package", false) { // from class: kotlin.reflect.jvm.internal.impl.load.java.JavaVisibilities.1
        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public String getDisplayName() {
            return "public/*package*/";
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            return JavaVisibilities.areInSamePackage(declarationDescriptorWithVisibility, declarationDescriptor);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        protected Integer compareTo(Visibility visibility) {
            if (this == visibility) {
                return 0;
            }
            return Visibilities.isPrivate(visibility) ? 1 : -1;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public Visibility normalize() {
            return Visibilities.PROTECTED;
        }
    };
    public static final Visibility PROTECTED_AND_PACKAGE;
    public static final Visibility PROTECTED_STATIC_VISIBILITY;

    static {
        boolean z = true;
        PROTECTED_STATIC_VISIBILITY = new Visibility("protected_static", z) { // from class: kotlin.reflect.jvm.internal.impl.load.java.JavaVisibilities.2
            @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
            public String getDisplayName() {
                return "protected/*protected static*/";
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
            public boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
                return JavaVisibilities.isVisibleForProtectedAndPackage(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
            public Visibility normalize() {
                return Visibilities.PROTECTED;
            }
        };
        PROTECTED_AND_PACKAGE = new Visibility("protected_and_package", z) { // from class: kotlin.reflect.jvm.internal.impl.load.java.JavaVisibilities.3
            @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
            public String getDisplayName() {
                return "protected/*protected and package*/";
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
            public boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
                return JavaVisibilities.isVisibleForProtectedAndPackage(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
            protected Integer compareTo(Visibility visibility) {
                if (this == visibility) {
                    return 0;
                }
                if (visibility == Visibilities.INTERNAL) {
                    return null;
                }
                return Visibilities.isPrivate(visibility) ? 1 : -1;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
            public Visibility normalize() {
                return Visibilities.PROTECTED;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isVisibleForProtectedAndPackage(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
        if (areInSamePackage(DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(declarationDescriptorWithVisibility), declarationDescriptor)) {
            return true;
        }
        return Visibilities.PROTECTED.isVisible(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean areInSamePackage(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
        PackageFragmentDescriptor packageFragmentDescriptor = (PackageFragmentDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor, PackageFragmentDescriptor.class, false);
        PackageFragmentDescriptor packageFragmentDescriptor2 = (PackageFragmentDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor2, PackageFragmentDescriptor.class, false);
        return (packageFragmentDescriptor2 == null || packageFragmentDescriptor == null || !packageFragmentDescriptor.getFqName().equals(packageFragmentDescriptor2.getFqName())) ? false : true;
    }
}
