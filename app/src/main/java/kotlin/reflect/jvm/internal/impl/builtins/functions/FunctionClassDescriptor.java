package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.text.StringsKt;

/* compiled from: FunctionClassDescriptor.kt */
/* loaded from: classes2.dex */
public final class FunctionClassDescriptor extends AbstractClassDescriptor {
    private final int arity;
    private final PackageFragmentDescriptor containingDeclaration;
    private final Kind functionKind;
    private final FunctionClassScope memberScope;
    private final List<TypeParameterDescriptor> parameters;
    private final StorageManager storageManager;
    private final FunctionTypeConstructor typeConstructor;

    public Void getCompanionObjectDescriptor() {
        return null;
    }

    public Void getUnsubstitutedPrimaryConstructor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    /* renamed from: isData */
    public boolean mo1335isData() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    /* renamed from: isExpect */
    public boolean mo1336isExpect() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    /* renamed from: isExternal */
    public boolean mo1337isExternal() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    /* renamed from: isInline */
    public boolean mo1338isInline() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    /* renamed from: isInner */
    public boolean mo1339isInner() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    /* renamed from: getCompanionObjectDescriptor, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ ClassDescriptor mo1324getCompanionObjectDescriptor() {
        return (ClassDescriptor) getCompanionObjectDescriptor();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    /* renamed from: getUnsubstitutedPrimaryConstructor, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ ClassConstructorDescriptor mo1325getUnsubstitutedPrimaryConstructor() {
        return (ClassConstructorDescriptor) getUnsubstitutedPrimaryConstructor();
    }

    public final Kind getFunctionKind() {
        return this.functionKind;
    }

    public final int getArity() {
        return this.arity;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FunctionClassDescriptor(StorageManager storageManager, PackageFragmentDescriptor containingDeclaration, Kind functionKind, int i) {
        super(storageManager, functionKind.numberedClassName(i));
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(functionKind, "functionKind");
        this.storageManager = storageManager;
        this.containingDeclaration = containingDeclaration;
        this.functionKind = functionKind;
        this.arity = i;
        this.typeConstructor = new FunctionTypeConstructor();
        this.memberScope = new FunctionClassScope(storageManager, this);
        final ArrayList arrayList = new ArrayList();
        Function2<Variance, String, Unit> function2 = new Function2<Variance, String, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Variance variance, String str) {
                invoke2(variance, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Variance variance, String name) {
                Intrinsics.checkParameterIsNotNull(variance, "variance");
                Intrinsics.checkParameterIsNotNull(name, "name");
                arrayList.add(TypeParameterDescriptorImpl.createWithDefaultBound(FunctionClassDescriptor.this, Annotations.Companion.getEMPTY(), false, variance, Name.identifier(name), arrayList.size()));
            }
        };
        IntRange intRange = new IntRange(1, i);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            Variance variance = Variance.IN_VARIANCE;
            StringBuilder sb = new StringBuilder();
            sb.append('P');
            sb.append(iNextInt);
            function2.invoke2(variance, sb.toString());
            arrayList2.add(Unit.INSTANCE);
        }
        function2.invoke2(Variance.OUT_VARIANCE, "R");
        this.parameters = CollectionsKt.toList(arrayList);
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'Function' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* compiled from: FunctionClassDescriptor.kt */
    public static final class Kind {
        private static final /* synthetic */ Kind[] $VALUES;
        public static final Companion Companion;
        public static final Kind Function;
        public static final Kind KFunction;
        public static final Kind KSuspendFunction;
        public static final Kind SuspendFunction;
        private final String classNamePrefix;
        private final FqName packageFqName;

        public static Kind valueOf(String str) {
            return (Kind) Enum.valueOf(Kind.class, str);
        }

        public static Kind[] values() {
            return (Kind[]) $VALUES.clone();
        }

        protected Kind(String str, int i, FqName packageFqName, String classNamePrefix) {
            Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
            Intrinsics.checkParameterIsNotNull(classNamePrefix, "classNamePrefix");
            this.packageFqName = packageFqName;
            this.classNamePrefix = classNamePrefix;
        }

        public final String getClassNamePrefix() {
            return this.classNamePrefix;
        }

        public final FqName getPackageFqName() {
            return this.packageFqName;
        }

        static {
            FqName BUILT_INS_PACKAGE_FQ_NAME = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME;
            Intrinsics.checkExpressionValueIsNotNull(BUILT_INS_PACKAGE_FQ_NAME, "BUILT_INS_PACKAGE_FQ_NAME");
            Kind kind = new Kind("Function", 0, BUILT_INS_PACKAGE_FQ_NAME, "Function");
            Function = kind;
            FqName BUILT_INS_PACKAGE_FQ_NAME2 = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME;
            Intrinsics.checkExpressionValueIsNotNull(BUILT_INS_PACKAGE_FQ_NAME2, "BUILT_INS_PACKAGE_FQ_NAME");
            Kind kind2 = new Kind("SuspendFunction", 1, BUILT_INS_PACKAGE_FQ_NAME2, "SuspendFunction");
            SuspendFunction = kind2;
            Kind kind3 = new Kind("KFunction", 2, ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), "KFunction");
            KFunction = kind3;
            Kind kind4 = new Kind("KSuspendFunction", 3, ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), "KSuspendFunction");
            KSuspendFunction = kind4;
            $VALUES = new Kind[]{kind, kind2, kind3, kind4};
            Companion = new Companion(null);
        }

        public final Name numberedClassName(int i) {
            return Name.identifier(this.classNamePrefix + i);
        }

        /* compiled from: FunctionClassDescriptor.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final Kind byClassNamePrefix(FqName packageFqName, String className) {
                Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
                Intrinsics.checkParameterIsNotNull(className, "className");
                for (Kind kind : Kind.values()) {
                    if (Intrinsics.areEqual(kind.getPackageFqName(), packageFqName) && StringsKt.startsWith$default(className, kind.getClassNamePrefix(), false, 2, (Object) null)) {
                        return kind;
                    }
                }
                return null;
            }
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public PackageFragmentDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope.Empty getStaticScope() {
        return MemberScope.Empty.INSTANCE;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public FunctionClassScope getUnsubstitutedMemberScope() {
        return this.memberScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List<ClassConstructorDescriptor> getConstructors() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassKind getKind() {
        return ClassKind.INTERFACE;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public Modality getModality() {
        return Modality.ABSTRACT;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public Visibility getVisibility() {
        return Visibilities.PUBLIC;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    public SourceElement getSource() {
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
        return sourceElement;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return this.parameters;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FunctionClassDescriptor.kt */
    final class FunctionTypeConstructor extends AbstractClassTypeConstructor {

        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[Kind.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[Kind.SuspendFunction.ordinal()] = 1;
                iArr[Kind.KSuspendFunction.ordinal()] = 2;
                int[] iArr2 = new int[Kind.values().length];
                $EnumSwitchMapping$1 = iArr2;
                iArr2[Kind.KFunction.ordinal()] = 1;
                iArr2[Kind.KSuspendFunction.ordinal()] = 2;
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        public FunctionTypeConstructor() {
            super(FunctionClassDescriptor.this.storageManager);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected Collection<KotlinType> computeSupertypes() {
            Kind kind;
            final ArrayList arrayList = new ArrayList(2);
            Function2<PackageFragmentDescriptor, Name, Unit> function2 = new Function2<PackageFragmentDescriptor, Name, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor$FunctionTypeConstructor$computeSupertypes$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PackageFragmentDescriptor packageFragmentDescriptor, Name name) {
                    invoke2(packageFragmentDescriptor, name);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PackageFragmentDescriptor packageFragment, Name name) {
                    Intrinsics.checkParameterIsNotNull(packageFragment, "packageFragment");
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    ClassifierDescriptor contributedClassifier = packageFragment.getMemberScope().mo1334getContributedClassifier(name, NoLookupLocation.FROM_BUILTINS);
                    if (!(contributedClassifier instanceof ClassDescriptor)) {
                        contributedClassifier = null;
                    }
                    ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
                    if (classDescriptor == null) {
                        throw new IllegalStateException(("Class " + name + " not found in " + packageFragment).toString());
                    }
                    TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
                    List<TypeParameterDescriptor> parameters = this.this$0.getParameters();
                    Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "typeConstructor");
                    List listTakeLast = CollectionsKt.takeLast(parameters, typeConstructor.getParameters().size());
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listTakeLast, 10));
                    Iterator it = listTakeLast.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(new TypeProjectionImpl(((TypeParameterDescriptor) it.next()).getDefaultType()));
                    }
                    arrayList.add(KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), classDescriptor, arrayList2));
                }
            };
            int i = WhenMappings.$EnumSwitchMapping$0[FunctionClassDescriptor.this.getFunctionKind().ordinal()];
            if (i == 1) {
                PackageFragmentDescriptor packageFragmentDescriptor = FunctionClassDescriptor.this.containingDeclaration;
                Name nameIdentifier = Name.identifier("Function");
                Intrinsics.checkExpressionValueIsNotNull(nameIdentifier, "Name.identifier(\"Function\")");
                function2.invoke2(packageFragmentDescriptor, nameIdentifier);
            } else if (i != 2) {
                PackageFragmentDescriptor packageFragmentDescriptor2 = FunctionClassDescriptor.this.containingDeclaration;
                Name nameIdentifier2 = Name.identifier(FunctionClassDescriptor.this.getFunctionKind().getClassNamePrefix());
                Intrinsics.checkExpressionValueIsNotNull(nameIdentifier2, "Name.identifier(functionKind.classNamePrefix)");
                function2.invoke2(packageFragmentDescriptor2, nameIdentifier2);
            } else {
                PackageFragmentDescriptor packageFragmentDescriptor3 = FunctionClassDescriptor.this.containingDeclaration;
                Name nameIdentifier3 = Name.identifier("KFunction");
                Intrinsics.checkExpressionValueIsNotNull(nameIdentifier3, "Name.identifier(\"KFunction\")");
                function2.invoke2(packageFragmentDescriptor3, nameIdentifier3);
            }
            int i2 = WhenMappings.$EnumSwitchMapping$1[FunctionClassDescriptor.this.getFunctionKind().ordinal()];
            if (i2 == 1) {
                kind = Kind.Function;
            } else {
                kind = i2 != 2 ? null : Kind.SuspendFunction;
            }
            if (kind != null) {
                ModuleDescriptor containingDeclaration = FunctionClassDescriptor.this.containingDeclaration.getContainingDeclaration();
                FqName BUILT_INS_PACKAGE_FQ_NAME = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME;
                Intrinsics.checkExpressionValueIsNotNull(BUILT_INS_PACKAGE_FQ_NAME, "BUILT_INS_PACKAGE_FQ_NAME");
                List<PackageFragmentDescriptor> fragments = containingDeclaration.getPackage(BUILT_INS_PACKAGE_FQ_NAME).getFragments();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : fragments) {
                    if (obj instanceof BuiltInsPackageFragment) {
                        arrayList2.add(obj);
                    }
                }
                BuiltInsPackageFragment builtInsPackageFragment = (BuiltInsPackageFragment) CollectionsKt.first((List) arrayList2);
                Name nameNumberedClassName = kind.numberedClassName(FunctionClassDescriptor.this.getArity());
                Intrinsics.checkExpressionValueIsNotNull(nameNumberedClassName, "numberedSupertypeKind.numberedClassName(arity)");
                function2.invoke2((PackageFragmentDescriptor) builtInsPackageFragment, nameNumberedClassName);
            }
            return CollectionsKt.toList(arrayList);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public List<TypeParameterDescriptor> getParameters() {
            return FunctionClassDescriptor.this.parameters;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        /* renamed from: getDeclarationDescriptor */
        public FunctionClassDescriptor mo1333getDeclarationDescriptor() {
            return FunctionClassDescriptor.this;
        }

        public String toString() {
            return mo1333getDeclarationDescriptor().toString();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected SupertypeLoopChecker getSupertypeLoopChecker() {
            return SupertypeLoopChecker.EMPTY.INSTANCE;
        }
    }

    public String toString() {
        return getName().asString();
    }
}
