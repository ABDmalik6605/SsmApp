package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* compiled from: descriptorUtil.kt */
/* loaded from: classes2.dex */
public final class DescriptorUtilKt {
    public static final ClassDescriptor resolveClassByFqName(ModuleDescriptor receiver, FqName fqName, LookupLocation lookupLocation) {
        ClassifierDescriptor contributedClassifier;
        MemberScope unsubstitutedInnerClassesScope;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "lookupLocation");
        if (fqName.isRoot()) {
            return null;
        }
        FqName fqNameParent = fqName.parent();
        Intrinsics.checkExpressionValueIsNotNull(fqNameParent, "fqName.parent()");
        MemberScope memberScope = receiver.getPackage(fqNameParent).getMemberScope();
        Name nameShortName = fqName.shortName();
        Intrinsics.checkExpressionValueIsNotNull(nameShortName, "fqName.shortName()");
        ClassifierDescriptor contributedClassifier2 = memberScope.mo1334getContributedClassifier(nameShortName, lookupLocation);
        if (!(contributedClassifier2 instanceof ClassDescriptor)) {
            contributedClassifier2 = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier2;
        if (classDescriptor != null) {
            return classDescriptor;
        }
        FqName fqNameParent2 = fqName.parent();
        Intrinsics.checkExpressionValueIsNotNull(fqNameParent2, "fqName.parent()");
        ClassDescriptor classDescriptorResolveClassByFqName = resolveClassByFqName(receiver, fqNameParent2, lookupLocation);
        if (classDescriptorResolveClassByFqName == null || (unsubstitutedInnerClassesScope = classDescriptorResolveClassByFqName.getUnsubstitutedInnerClassesScope()) == null) {
            contributedClassifier = null;
        } else {
            Name nameShortName2 = fqName.shortName();
            Intrinsics.checkExpressionValueIsNotNull(nameShortName2, "fqName.shortName()");
            contributedClassifier = unsubstitutedInnerClassesScope.mo1334getContributedClassifier(nameShortName2, lookupLocation);
        }
        return (ClassDescriptor) (contributedClassifier instanceof ClassDescriptor ? contributedClassifier : null);
    }
}
