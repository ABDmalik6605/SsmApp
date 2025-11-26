package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: DescriptorUtils.kt */
/* loaded from: classes2.dex */
public final class DescriptorUtilsKt {
    private static final Name RETENTION_PARAMETER_NAME = Name.identifier("value");

    public static final FqNameUnsafe getFqNameUnsafe(DeclarationDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        FqNameUnsafe fqName = DescriptorUtils.getFqName(receiver);
        Intrinsics.checkExpressionValueIsNotNull(fqName, "DescriptorUtils.getFqName(this)");
        return fqName;
    }

    public static final FqName getFqNameSafe(DeclarationDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        FqName fqNameSafe = DescriptorUtils.getFqNameSafe(receiver);
        Intrinsics.checkExpressionValueIsNotNull(fqNameSafe, "DescriptorUtils.getFqNameSafe(this)");
        return fqNameSafe;
    }

    public static final ModuleDescriptor getModule(DeclarationDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ModuleDescriptor containingModule = DescriptorUtils.getContainingModule(receiver);
        Intrinsics.checkExpressionValueIsNotNull(containingModule, "DescriptorUtils.getContainingModule(this)");
        return containingModule;
    }

    public static final ClassDescriptor resolveTopLevelClass(ModuleDescriptor receiver, FqName topLevelClassFqName, LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(topLevelClassFqName, "topLevelClassFqName");
        Intrinsics.checkParameterIsNotNull(location, "location");
        topLevelClassFqName.isRoot();
        FqName fqNameParent = topLevelClassFqName.parent();
        Intrinsics.checkExpressionValueIsNotNull(fqNameParent, "topLevelClassFqName.parent()");
        MemberScope memberScope = receiver.getPackage(fqNameParent).getMemberScope();
        Name nameShortName = topLevelClassFqName.shortName();
        Intrinsics.checkExpressionValueIsNotNull(nameShortName, "topLevelClassFqName.shortName()");
        ClassifierDescriptor contributedClassifier = memberScope.mo1334getContributedClassifier(nameShortName, location);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        return (ClassDescriptor) contributedClassifier;
    }

    public static final ClassId getClassId(ClassifierDescriptorWithTypeParameters receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        DeclarationDescriptor owner = receiver.getContainingDeclaration();
        if (owner instanceof PackageFragmentDescriptor) {
            return new ClassId(((PackageFragmentDescriptor) owner).getFqName(), receiver.getName());
        }
        if (!(owner instanceof ClassifierDescriptorWithTypeParameters)) {
            return null;
        }
        Intrinsics.checkExpressionValueIsNotNull(owner, "owner");
        ClassId classId = getClassId((ClassifierDescriptorWithTypeParameters) owner);
        if (classId != null) {
            return classId.createNestedClassId(receiver.getName());
        }
        return null;
    }

    public static final ClassDescriptor getSuperClassNotAny(ClassDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        for (KotlinType kotlinType : receiver.getDefaultType().getConstructor().getSupertypes()) {
            if (!KotlinBuiltIns.isAnyOrNullableAny(kotlinType)) {
                ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = kotlinType.getConstructor().mo1333getDeclarationDescriptor();
                if (DescriptorUtils.isClassOrEnumClass(classifierDescriptorMo1333getDeclarationDescriptor)) {
                    if (classifierDescriptorMo1333getDeclarationDescriptor != null) {
                        return (ClassDescriptor) classifierDescriptorMo1333getDeclarationDescriptor;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
            }
        }
        return null;
    }

    public static final KotlinBuiltIns getBuiltIns(DeclarationDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return getModule(receiver).getBuiltIns();
    }

    public static final boolean declaresOrInheritsDefaultValue(ValueParameterDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Boolean boolIfAny = DFS.ifAny(CollectionsKt.listOf(receiver), new DFS.Neighbors<N>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final List<ValueParameterDescriptor> getNeighbors(ValueParameterDescriptor current) {
                Intrinsics.checkExpressionValueIsNotNull(current, "current");
                Collection<ValueParameterDescriptor> overriddenDescriptors = current.getOverriddenDescriptors();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenDescriptors, 10));
                Iterator<T> it = overriddenDescriptors.iterator();
                while (it.hasNext()) {
                    arrayList.add(((ValueParameterDescriptor) it.next()).getOriginal());
                }
                return arrayList;
            }
        }, AnonymousClass2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(boolIfAny, "DFS.ifAny(\n        listO…eclaresDefaultValue\n    )");
        return boolIfAny.booleanValue();
    }

    /* compiled from: DescriptorUtils.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$declaresOrInheritsDefaultValue$2, reason: invalid class name */
    static final class AnonymousClass2 extends FunctionReference implements Function1<ValueParameterDescriptor, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "declaresDefaultValue";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(ValueParameterDescriptor.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "declaresDefaultValue()Z";
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(ValueParameterDescriptor valueParameterDescriptor) {
            return Boolean.valueOf(invoke2(valueParameterDescriptor));
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(ValueParameterDescriptor p1) {
            Intrinsics.checkParameterIsNotNull(p1, "p1");
            return p1.declaresDefaultValue();
        }
    }

    public static final Sequence<DeclarationDescriptor> getParentsWithSelf(DeclarationDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return SequencesKt.generateSequence(receiver, new Function1<DeclarationDescriptor, DeclarationDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$parentsWithSelf$1
            @Override // kotlin.jvm.functions.Function1
            public final DeclarationDescriptor invoke(DeclarationDescriptor it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getContainingDeclaration();
            }
        });
    }

    public static final Sequence<DeclarationDescriptor> getParents(DeclarationDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return SequencesKt.drop(getParentsWithSelf(receiver), 1);
    }

    public static final CallableMemberDescriptor getPropertyIfAccessor(CallableMemberDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(receiver instanceof PropertyAccessorDescriptor)) {
            return receiver;
        }
        PropertyDescriptor correspondingProperty = ((PropertyAccessorDescriptor) receiver).getCorrespondingProperty();
        Intrinsics.checkExpressionValueIsNotNull(correspondingProperty, "correspondingProperty");
        return correspondingProperty;
    }

    public static final FqName fqNameOrNull(CallableDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        FqNameUnsafe fqNameUnsafe = getFqNameUnsafe(receiver);
        if (!fqNameUnsafe.isSafe()) {
            fqNameUnsafe = null;
        }
        if (fqNameUnsafe != null) {
            return fqNameUnsafe.toSafe();
        }
        return null;
    }

    public static /* bridge */ /* synthetic */ CallableMemberDescriptor firstOverridden$default(CallableMemberDescriptor callableMemberDescriptor, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return firstOverridden(callableMemberDescriptor, z, function1);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor] */
    public static final CallableMemberDescriptor firstOverridden(CallableMemberDescriptor receiver, final boolean z, final Function1<? super CallableMemberDescriptor, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (CallableMemberDescriptor) 0;
        return (CallableMemberDescriptor) DFS.dfs(CollectionsKt.listOf(receiver), new DFS.Neighbors<N>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstOverridden.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final Iterable<CallableMemberDescriptor> getNeighbors(CallableMemberDescriptor callableMemberDescriptor) {
                Collection<? extends CallableMemberDescriptor> collectionEmptyList;
                if (z) {
                    callableMemberDescriptor = callableMemberDescriptor != null ? callableMemberDescriptor.getOriginal() : null;
                }
                if (callableMemberDescriptor == null || (collectionEmptyList = callableMemberDescriptor.getOverriddenDescriptors()) == null) {
                    collectionEmptyList = CollectionsKt.emptyList();
                }
                return collectionEmptyList;
            }
        }, new DFS.AbstractNodeHandler<CallableMemberDescriptor, CallableMemberDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstOverridden.2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(CallableMemberDescriptor current) {
                Intrinsics.checkParameterIsNotNull(current, "current");
                return ((CallableMemberDescriptor) objectRef.element) == null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public void afterChildren(CallableMemberDescriptor current) {
                Intrinsics.checkParameterIsNotNull(current, "current");
                if (((CallableMemberDescriptor) objectRef.element) == null && ((Boolean) predicate.invoke(current)).booleanValue()) {
                    objectRef.element = current;
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public CallableMemberDescriptor result() {
                return (CallableMemberDescriptor) objectRef.element;
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$computeSealedSubclasses$1] */
    public static final Collection<ClassDescriptor> computeSealedSubclasses(final ClassDescriptor sealedClass) {
        Intrinsics.checkParameterIsNotNull(sealedClass, "sealedClass");
        if (sealedClass.getModality() != Modality.SEALED) {
            return CollectionsKt.emptyList();
        }
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        ?? r1 = new Function2<MemberScope, Boolean, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.computeSealedSubclasses.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(MemberScope memberScope, Boolean bool) {
                invoke(memberScope, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(MemberScope scope, boolean z) {
                Intrinsics.checkParameterIsNotNull(scope, "scope");
                for (DeclarationDescriptor declarationDescriptor : ResolutionScope.DefaultImpls.getContributedDescriptors$default(scope, DescriptorKindFilter.CLASSIFIERS, null, 2, null)) {
                    if (declarationDescriptor instanceof ClassDescriptor) {
                        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
                        if (DescriptorUtils.isDirectSubclass(classDescriptor, sealedClass)) {
                            linkedHashSet.add(declarationDescriptor);
                        }
                        if (z) {
                            MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
                            Intrinsics.checkExpressionValueIsNotNull(unsubstitutedInnerClassesScope, "descriptor.unsubstitutedInnerClassesScope");
                            invoke(unsubstitutedInnerClassesScope, z);
                        }
                    }
                }
            }
        };
        DeclarationDescriptor containingDeclaration = sealedClass.getContainingDeclaration();
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            r1.invoke(((PackageFragmentDescriptor) containingDeclaration).getMemberScope(), false);
        }
        MemberScope unsubstitutedInnerClassesScope = sealedClass.getUnsubstitutedInnerClassesScope();
        Intrinsics.checkExpressionValueIsNotNull(unsubstitutedInnerClassesScope, "sealedClass.unsubstitutedInnerClassesScope");
        r1.invoke(unsubstitutedInnerClassesScope, true);
        return linkedHashSet;
    }

    public static final ClassDescriptor getAnnotationClass(AnnotationDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = receiver.getType().getConstructor().mo1333getDeclarationDescriptor();
        if (!(classifierDescriptorMo1333getDeclarationDescriptor instanceof ClassDescriptor)) {
            classifierDescriptorMo1333getDeclarationDescriptor = null;
        }
        return (ClassDescriptor) classifierDescriptorMo1333getDeclarationDescriptor;
    }

    public static final ConstantValue<?> firstArgument(AnnotationDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (ConstantValue) CollectionsKt.firstOrNull(receiver.getAllValueArguments().values());
    }
}
