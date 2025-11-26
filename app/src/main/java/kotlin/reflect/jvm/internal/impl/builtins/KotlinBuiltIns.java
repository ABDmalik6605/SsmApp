package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ChainedMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* loaded from: classes2.dex */
public abstract class KotlinBuiltIns {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final FqName ANNOTATION_PACKAGE_FQ_NAME;
    public static final Name BUILTINS_MODULE_NAME;
    public static final FqName BUILT_INS_PACKAGE_FQ_NAME;
    public static final Set<FqName> BUILT_INS_PACKAGE_FQ_NAMES;
    public static final Name BUILT_INS_PACKAGE_NAME;
    public static final FqName COLLECTIONS_PACKAGE_FQ_NAME;
    public static final FqNames FQ_NAMES;
    public static final FqName RANGES_PACKAGE_FQ_NAME;
    public static final FqName TEXT_PACKAGE_FQ_NAME;
    private final MemoizedFunctionToNotNull<Name, ClassDescriptor> builtInClassesByName;
    private ModuleDescriptorImpl builtInsModule;
    private final NotNullLazyValue<PackageFragments> packageFragments;
    private final NotNullLazyValue<Primitives> primitives;
    private final StorageManager storageManager;
    private final MemoizedFunctionToNotNull<Integer, ClassDescriptor> suspendFunctionClasses;
    private final MemoizedFunctionToNotNull<ModuleDescriptor, UnsignedPrimitives> unsignedPrimitives;

    static {
        Name nameIdentifier = Name.identifier("kotlin");
        BUILT_INS_PACKAGE_NAME = nameIdentifier;
        FqName fqName = FqName.topLevel(nameIdentifier);
        BUILT_INS_PACKAGE_FQ_NAME = fqName;
        FqName fqNameChild = fqName.child(Name.identifier("annotation"));
        ANNOTATION_PACKAGE_FQ_NAME = fqNameChild;
        FqName fqNameChild2 = fqName.child(Name.identifier("collections"));
        COLLECTIONS_PACKAGE_FQ_NAME = fqNameChild2;
        FqName fqNameChild3 = fqName.child(Name.identifier("ranges"));
        RANGES_PACKAGE_FQ_NAME = fqNameChild3;
        TEXT_PACKAGE_FQ_NAME = fqName.child(Name.identifier("text"));
        BUILT_INS_PACKAGE_FQ_NAMES = SetsKt.setOf((Object[]) new FqName[]{fqName, fqNameChild2, fqNameChild3, fqNameChild, ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), fqName.child(Name.identifier("internal"))});
        FQ_NAMES = new FqNames();
        BUILTINS_MODULE_NAME = Name.special("<built-ins module>");
    }

    protected KotlinBuiltIns(StorageManager storageManager) {
        this.storageManager = storageManager;
        this.packageFragments = storageManager.createLazyValue(new Function0<PackageFragments>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.1
            @Override // kotlin.jvm.functions.Function0
            public PackageFragments invoke() {
                PackageFragmentProvider packageFragmentProvider = KotlinBuiltIns.this.builtInsModule.getPackageFragmentProvider();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                PackageFragmentDescriptor packageFragmentDescriptorCreatePackage = KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME);
                PackageFragmentDescriptor packageFragmentDescriptorCreatePackage2 = KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.COLLECTIONS_PACKAGE_FQ_NAME);
                KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.RANGES_PACKAGE_FQ_NAME);
                return new PackageFragments(packageFragmentDescriptorCreatePackage, packageFragmentDescriptorCreatePackage2, KotlinBuiltIns.this.createPackage(packageFragmentProvider, linkedHashMap, KotlinBuiltIns.ANNOTATION_PACKAGE_FQ_NAME), new LinkedHashSet(linkedHashMap.values()));
            }
        });
        this.primitives = storageManager.createLazyValue(new Function0<Primitives>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.2
            @Override // kotlin.jvm.functions.Function0
            public Primitives invoke() {
                EnumMap enumMap = new EnumMap(PrimitiveType.class);
                HashMap map = new HashMap();
                HashMap map2 = new HashMap();
                for (PrimitiveType primitiveType : PrimitiveType.values()) {
                    SimpleType builtInTypeByClassName = KotlinBuiltIns.this.getBuiltInTypeByClassName(primitiveType.getTypeName().asString());
                    SimpleType builtInTypeByClassName2 = KotlinBuiltIns.this.getBuiltInTypeByClassName(primitiveType.getArrayTypeName().asString());
                    enumMap.put((EnumMap) primitiveType, (PrimitiveType) builtInTypeByClassName2);
                    map.put(builtInTypeByClassName, builtInTypeByClassName2);
                    map2.put(builtInTypeByClassName2, builtInTypeByClassName);
                }
                return new Primitives(enumMap, map, map2);
            }
        });
        this.unsignedPrimitives = storageManager.createMemoizedFunction(new Function1<ModuleDescriptor, UnsignedPrimitives>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.3
            @Override // kotlin.jvm.functions.Function1
            public UnsignedPrimitives invoke(ModuleDescriptor moduleDescriptor) {
                ClassDescriptor classDescriptorFindClassAcrossModuleDependencies;
                HashMap map = new HashMap();
                HashMap map2 = new HashMap();
                for (UnsignedType unsignedType : UnsignedType.values()) {
                    ClassDescriptor classDescriptorFindClassAcrossModuleDependencies2 = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor, unsignedType.getClassId());
                    if (classDescriptorFindClassAcrossModuleDependencies2 != null && (classDescriptorFindClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor, unsignedType.getArrayClassId())) != null) {
                        SimpleType defaultType = classDescriptorFindClassAcrossModuleDependencies2.getDefaultType();
                        SimpleType defaultType2 = classDescriptorFindClassAcrossModuleDependencies.getDefaultType();
                        map.put(defaultType, defaultType2);
                        map2.put(defaultType2, defaultType);
                    }
                }
                return new UnsignedPrimitives(map, map2);
            }
        });
        this.suspendFunctionClasses = storageManager.createMemoizedFunction(new Function1<Integer, ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public ClassDescriptor invoke(Integer num) {
                return new FunctionClassDescriptor(KotlinBuiltIns.this.getStorageManager(), ((PackageFragments) KotlinBuiltIns.this.packageFragments.invoke()).builtInsPackageFragment, FunctionClassDescriptor.Kind.SuspendFunction, num.intValue());
            }
        });
        this.builtInClassesByName = storageManager.createMemoizedFunction(new Function1<Name, ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.5
            @Override // kotlin.jvm.functions.Function1
            public ClassDescriptor invoke(Name name) {
                return KotlinBuiltIns.getBuiltInClassByName(name, KotlinBuiltIns.this.getBuiltInsPackageFragment());
            }
        });
    }

    protected void createBuiltInsModule() {
        ModuleDescriptorImpl moduleDescriptorImpl = new ModuleDescriptorImpl(BUILTINS_MODULE_NAME, this.storageManager, this, null);
        this.builtInsModule = moduleDescriptorImpl;
        moduleDescriptorImpl.initialize(BuiltInsLoader.Companion.getInstance().createPackageFragmentProvider(this.storageManager, this.builtInsModule, getClassDescriptorFactories(), getPlatformDependentDeclarationFilter(), getAdditionalClassPartsProvider()));
        ModuleDescriptorImpl moduleDescriptorImpl2 = this.builtInsModule;
        moduleDescriptorImpl2.setDependencies(moduleDescriptorImpl2);
    }

    protected AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return AdditionalClassPartsProvider.None.INSTANCE;
    }

    protected PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return PlatformDependentDeclarationFilter.NoPlatformDependent.INSTANCE;
    }

    protected Iterable<ClassDescriptorFactory> getClassDescriptorFactories() {
        return Collections.singletonList(new BuiltInFictitiousFunctionClassFactory(this.storageManager, this.builtInsModule));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PackageFragmentDescriptor createPackage(PackageFragmentProvider packageFragmentProvider, Map<FqName, PackageFragmentDescriptor> map, final FqName fqName) {
        final List<PackageFragmentDescriptor> packageFragments = packageFragmentProvider.getPackageFragments(fqName);
        PackageFragmentDescriptor emptyPackageFragmentDescriptor = packageFragments.isEmpty() ? new EmptyPackageFragmentDescriptor(this.builtInsModule, fqName) : packageFragments.size() == 1 ? packageFragments.iterator().next() : new PackageFragmentDescriptorImpl(this.builtInsModule, fqName) { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.7
            @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
            public MemberScope getMemberScope() {
                return new ChainedMemberScope("built-in package " + fqName, CollectionsKt.map(packageFragments, new Function1<PackageFragmentDescriptor, MemberScope>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.7.1
                    @Override // kotlin.jvm.functions.Function1
                    public MemberScope invoke(PackageFragmentDescriptor packageFragmentDescriptor) {
                        return packageFragmentDescriptor.getMemberScope();
                    }
                }));
            }
        };
        if (map != null) {
            map.put(fqName, emptyPackageFragmentDescriptor);
        }
        return emptyPackageFragmentDescriptor;
    }

    protected StorageManager getStorageManager() {
        return this.storageManager;
    }

    private static class Primitives {
        public final Map<SimpleType, SimpleType> kotlinArrayTypeToPrimitiveKotlinType;
        public final Map<KotlinType, SimpleType> primitiveKotlinTypeToKotlinArrayType;
        public final Map<PrimitiveType, SimpleType> primitiveTypeToArrayKotlinType;

        private Primitives(Map<PrimitiveType, SimpleType> map, Map<KotlinType, SimpleType> map2, Map<SimpleType, SimpleType> map3) {
            this.primitiveTypeToArrayKotlinType = map;
            this.primitiveKotlinTypeToKotlinArrayType = map2;
            this.kotlinArrayTypeToPrimitiveKotlinType = map3;
        }
    }

    private static class UnsignedPrimitives {
        public final Map<SimpleType, SimpleType> kotlinArrayTypeToUnsignedKotlinType;
        public final Map<KotlinType, SimpleType> unsignedKotlinTypeToKotlinArrayType;

        private UnsignedPrimitives(Map<KotlinType, SimpleType> map, Map<SimpleType, SimpleType> map2) {
            this.unsignedKotlinTypeToKotlinArrayType = map;
            this.kotlinArrayTypeToUnsignedKotlinType = map2;
        }
    }

    private static class PackageFragments {
        public final Set<PackageFragmentDescriptor> allImportedByDefaultBuiltInsPackageFragments;
        public final PackageFragmentDescriptor annotationPackageFragment;
        public final PackageFragmentDescriptor builtInsPackageFragment;
        public final PackageFragmentDescriptor collectionsPackageFragment;

        private PackageFragments(PackageFragmentDescriptor packageFragmentDescriptor, PackageFragmentDescriptor packageFragmentDescriptor2, PackageFragmentDescriptor packageFragmentDescriptor3, Set<PackageFragmentDescriptor> set) {
            this.builtInsPackageFragment = packageFragmentDescriptor;
            this.collectionsPackageFragment = packageFragmentDescriptor2;
            this.annotationPackageFragment = packageFragmentDescriptor3;
            this.allImportedByDefaultBuiltInsPackageFragments = set;
        }
    }

    public static class FqNames {
        public final Map<FqNameUnsafe, PrimitiveType> arrayClassFqNameToPrimitiveType;
        public final Map<FqNameUnsafe, PrimitiveType> fqNameToPrimitiveType;
        public final FqNameUnsafe kCallable;
        public final FqNameUnsafe kClass;
        public final FqNameUnsafe kMutableProperty0;
        public final FqNameUnsafe kMutableProperty1;
        public final FqNameUnsafe kMutableProperty2;
        public final ClassId kProperty;
        public final FqNameUnsafe kProperty0;
        public final FqNameUnsafe kProperty1;
        public final FqNameUnsafe kProperty2;
        public final FqName map;
        public final FqName mapEntry;
        public final FqName mutableCollection;
        public final FqName mutableIterable;
        public final FqName mutableIterator;
        public final FqName mutableList;
        public final FqName mutableListIterator;
        public final FqName mutableMap;
        public final FqName mutableMapEntry;
        public final FqName mutableSet;
        public final Set<Name> primitiveArrayTypeShortNames;
        public final Set<Name> primitiveTypeShortNames;
        public final ClassId uByte;
        public final FqName uByteFqName;
        public final ClassId uInt;
        public final FqName uIntFqName;
        public final ClassId uLong;
        public final FqName uLongFqName;
        public final ClassId uShort;
        public final FqName uShortFqName;
        public final FqNameUnsafe any = fqNameUnsafe("Any");
        public final FqNameUnsafe nothing = fqNameUnsafe("Nothing");
        public final FqNameUnsafe cloneable = fqNameUnsafe("Cloneable");
        public final FqName suppress = fqName("Suppress");
        public final FqNameUnsafe unit = fqNameUnsafe("Unit");
        public final FqNameUnsafe charSequence = fqNameUnsafe("CharSequence");
        public final FqNameUnsafe string = fqNameUnsafe("String");
        public final FqNameUnsafe array = fqNameUnsafe("Array");
        public final FqNameUnsafe _boolean = fqNameUnsafe("Boolean");
        public final FqNameUnsafe _char = fqNameUnsafe("Char");
        public final FqNameUnsafe _byte = fqNameUnsafe("Byte");
        public final FqNameUnsafe _short = fqNameUnsafe("Short");
        public final FqNameUnsafe _int = fqNameUnsafe("Int");
        public final FqNameUnsafe _long = fqNameUnsafe("Long");
        public final FqNameUnsafe _float = fqNameUnsafe("Float");
        public final FqNameUnsafe _double = fqNameUnsafe("Double");
        public final FqNameUnsafe number = fqNameUnsafe("Number");
        public final FqNameUnsafe _enum = fqNameUnsafe("Enum");
        public final FqNameUnsafe functionSupertype = fqNameUnsafe("Function");
        public final FqName throwable = fqName("Throwable");
        public final FqName comparable = fqName("Comparable");
        public final FqNameUnsafe charRange = rangesFqName("CharRange");
        public final FqNameUnsafe intRange = rangesFqName("IntRange");
        public final FqNameUnsafe longRange = rangesFqName("LongRange");
        public final FqName deprecated = fqName("Deprecated");
        public final FqName deprecationLevel = fqName("DeprecationLevel");
        public final FqName replaceWith = fqName("ReplaceWith");
        public final FqName extensionFunctionType = fqName("ExtensionFunctionType");
        public final FqName parameterName = fqName("ParameterName");
        public final FqName annotation = fqName("Annotation");
        public final FqName target = annotationName("Target");
        public final FqName annotationTarget = annotationName("AnnotationTarget");
        public final FqName annotationRetention = annotationName("AnnotationRetention");
        public final FqName retention = annotationName("Retention");
        public final FqName repeatable = annotationName("Repeatable");
        public final FqName mustBeDocumented = annotationName("MustBeDocumented");
        public final FqName unsafeVariance = fqName("UnsafeVariance");
        public final FqName publishedApi = fqName("PublishedApi");
        public final FqName iterator = collectionsFqName("Iterator");
        public final FqName iterable = collectionsFqName("Iterable");
        public final FqName collection = collectionsFqName("Collection");
        public final FqName list = collectionsFqName("List");
        public final FqName listIterator = collectionsFqName("ListIterator");
        public final FqName set = collectionsFqName("Set");

        public FqNames() {
            FqName fqNameCollectionsFqName = collectionsFqName("Map");
            this.map = fqNameCollectionsFqName;
            this.mapEntry = fqNameCollectionsFqName.child(Name.identifier("Entry"));
            this.mutableIterator = collectionsFqName("MutableIterator");
            this.mutableIterable = collectionsFqName("MutableIterable");
            this.mutableCollection = collectionsFqName("MutableCollection");
            this.mutableList = collectionsFqName("MutableList");
            this.mutableListIterator = collectionsFqName("MutableListIterator");
            this.mutableSet = collectionsFqName("MutableSet");
            FqName fqNameCollectionsFqName2 = collectionsFqName("MutableMap");
            this.mutableMap = fqNameCollectionsFqName2;
            this.mutableMapEntry = fqNameCollectionsFqName2.child(Name.identifier("MutableEntry"));
            this.kClass = reflect("KClass");
            this.kCallable = reflect("KCallable");
            this.kProperty0 = reflect("KProperty0");
            this.kProperty1 = reflect("KProperty1");
            this.kProperty2 = reflect("KProperty2");
            this.kMutableProperty0 = reflect("KMutableProperty0");
            this.kMutableProperty1 = reflect("KMutableProperty1");
            this.kMutableProperty2 = reflect("KMutableProperty2");
            this.kProperty = ClassId.topLevel(reflect("KProperty").toSafe());
            FqName fqName = fqName("UByte");
            this.uByteFqName = fqName;
            FqName fqName2 = fqName("UShort");
            this.uShortFqName = fqName2;
            FqName fqName3 = fqName("UInt");
            this.uIntFqName = fqName3;
            FqName fqName4 = fqName("ULong");
            this.uLongFqName = fqName4;
            this.uByte = ClassId.topLevel(fqName);
            this.uShort = ClassId.topLevel(fqName2);
            this.uInt = ClassId.topLevel(fqName3);
            this.uLong = ClassId.topLevel(fqName4);
            this.primitiveTypeShortNames = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
            this.primitiveArrayTypeShortNames = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
            this.fqNameToPrimitiveType = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
            this.arrayClassFqNameToPrimitiveType = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
            for (PrimitiveType primitiveType : PrimitiveType.values()) {
                this.primitiveTypeShortNames.add(primitiveType.getTypeName());
                this.primitiveArrayTypeShortNames.add(primitiveType.getArrayTypeName());
                this.fqNameToPrimitiveType.put(fqNameUnsafe(primitiveType.getTypeName().asString()), primitiveType);
                this.arrayClassFqNameToPrimitiveType.put(fqNameUnsafe(primitiveType.getArrayTypeName().asString()), primitiveType);
            }
        }

        private static FqNameUnsafe fqNameUnsafe(String str) {
            return fqName(str).toUnsafe();
        }

        private static FqName fqName(String str) {
            return KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(str));
        }

        private static FqName collectionsFqName(String str) {
            return KotlinBuiltIns.COLLECTIONS_PACKAGE_FQ_NAME.child(Name.identifier(str));
        }

        private static FqNameUnsafe rangesFqName(String str) {
            return KotlinBuiltIns.RANGES_PACKAGE_FQ_NAME.child(Name.identifier(str)).toUnsafe();
        }

        private static FqNameUnsafe reflect(String str) {
            return ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME().child(Name.identifier(str)).toUnsafe();
        }

        private static FqName annotationName(String str) {
            return KotlinBuiltIns.ANNOTATION_PACKAGE_FQ_NAME.child(Name.identifier(str));
        }
    }

    public ModuleDescriptorImpl getBuiltInsModule() {
        return this.builtInsModule;
    }

    public PackageFragmentDescriptor getBuiltInsPackageFragment() {
        return this.packageFragments.invoke().builtInsPackageFragment;
    }

    public static boolean isBuiltIn(DeclarationDescriptor declarationDescriptor) {
        return DescriptorUtils.getParentOfType(declarationDescriptor, BuiltInsPackageFragment.class, false) != null;
    }

    public static boolean isUnderKotlinPackage(DeclarationDescriptor declarationDescriptor) {
        while (declarationDescriptor != null) {
            if (declarationDescriptor instanceof PackageFragmentDescriptor) {
                return ((PackageFragmentDescriptor) declarationDescriptor).getFqName().startsWith(BUILT_INS_PACKAGE_NAME);
            }
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
        return false;
    }

    public ClassDescriptor getBuiltInClassByName(Name name) {
        return this.builtInClassesByName.invoke(name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ClassDescriptor getBuiltInClassByName(Name name, PackageFragmentDescriptor packageFragmentDescriptor) {
        ClassDescriptor builtInClassByNameNullable = getBuiltInClassByNameNullable(name, packageFragmentDescriptor);
        if (builtInClassByNameNullable != null) {
            return builtInClassByNameNullable;
        }
        throw new AssertionError("Built-in class " + packageFragmentDescriptor.getFqName().child(name).asString() + " is not found");
    }

    public ClassDescriptor getBuiltInClassByFqNameNullable(FqName fqName) {
        return DescriptorUtilKt.resolveClassByFqName(this.builtInsModule, fqName, NoLookupLocation.FROM_BUILTINS);
    }

    public ClassDescriptor getBuiltInClassByFqName(FqName fqName) {
        return getBuiltInClassByFqNameNullable(fqName);
    }

    private static ClassDescriptor getBuiltInClassByNameNullable(Name name, PackageFragmentDescriptor packageFragmentDescriptor) {
        return (ClassDescriptor) packageFragmentDescriptor.getMemberScope().mo1334getContributedClassifier(name, NoLookupLocation.FROM_BUILTINS);
    }

    private ClassDescriptor getBuiltInClassByName(String str) {
        return getBuiltInClassByName(Name.identifier(str));
    }

    private static ClassDescriptor getBuiltInClassByName(String str, PackageFragmentDescriptor packageFragmentDescriptor) {
        return getBuiltInClassByName(Name.identifier(str), packageFragmentDescriptor);
    }

    public ClassDescriptor getAny() {
        return getBuiltInClassByName("Any");
    }

    public ClassDescriptor getNothing() {
        return getBuiltInClassByName("Nothing");
    }

    private ClassDescriptor getPrimitiveClassDescriptor(PrimitiveType primitiveType) {
        return getBuiltInClassByName(primitiveType.getTypeName().asString());
    }

    public ClassDescriptor getArray() {
        return getBuiltInClassByName("Array");
    }

    public ClassDescriptor getNumber() {
        return getBuiltInClassByName("Number");
    }

    public ClassDescriptor getUnit() {
        return getBuiltInClassByName("Unit");
    }

    public static String getFunctionName(int i) {
        return "Function" + i;
    }

    public static ClassId getFunctionClassId(int i) {
        return new ClassId(BUILT_INS_PACKAGE_FQ_NAME, Name.identifier(getFunctionName(i)));
    }

    public ClassDescriptor getFunction(int i) {
        return getBuiltInClassByName(getFunctionName(i));
    }

    public ClassDescriptor getSuspendFunction(int i) {
        return this.suspendFunctionClasses.invoke(Integer.valueOf(i));
    }

    public ClassDescriptor getString() {
        return getBuiltInClassByName("String");
    }

    private ClassDescriptor getCollectionClassByName(String str) {
        return getBuiltInClassByName(str, this.packageFragments.invoke().collectionsPackageFragment);
    }

    public ClassDescriptor getCollection() {
        return getCollectionClassByName("Collection");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SimpleType getBuiltInTypeByClassName(String str) {
        return getBuiltInClassByName(str).getDefaultType();
    }

    public SimpleType getNothingType() {
        return getNothing().getDefaultType();
    }

    public SimpleType getNullableNothingType() {
        return getNothingType().makeNullableAsSpecified(true);
    }

    public SimpleType getAnyType() {
        return getAny().getDefaultType();
    }

    public SimpleType getNullableAnyType() {
        return getAnyType().makeNullableAsSpecified(true);
    }

    public SimpleType getDefaultBound() {
        return getNullableAnyType();
    }

    public SimpleType getPrimitiveKotlinType(PrimitiveType primitiveType) {
        return getPrimitiveClassDescriptor(primitiveType).getDefaultType();
    }

    public SimpleType getByteType() {
        return getPrimitiveKotlinType(PrimitiveType.BYTE);
    }

    public SimpleType getShortType() {
        return getPrimitiveKotlinType(PrimitiveType.SHORT);
    }

    public SimpleType getIntType() {
        return getPrimitiveKotlinType(PrimitiveType.INT);
    }

    public SimpleType getLongType() {
        return getPrimitiveKotlinType(PrimitiveType.LONG);
    }

    public SimpleType getFloatType() {
        return getPrimitiveKotlinType(PrimitiveType.FLOAT);
    }

    public SimpleType getDoubleType() {
        return getPrimitiveKotlinType(PrimitiveType.DOUBLE);
    }

    public SimpleType getCharType() {
        return getPrimitiveKotlinType(PrimitiveType.CHAR);
    }

    public SimpleType getBooleanType() {
        return getPrimitiveKotlinType(PrimitiveType.BOOLEAN);
    }

    public SimpleType getUnitType() {
        return getUnit().getDefaultType();
    }

    public SimpleType getStringType() {
        return getString().getDefaultType();
    }

    public KotlinType getArrayElementType(KotlinType kotlinType) {
        SimpleType simpleType;
        if (isArray(kotlinType)) {
            if (kotlinType.getArguments().size() != 1) {
                throw new IllegalStateException();
            }
            return kotlinType.getArguments().get(0).getType();
        }
        KotlinType kotlinTypeMakeNotNullable = TypeUtils.makeNotNullable(kotlinType);
        SimpleType simpleType2 = this.primitives.invoke().kotlinArrayTypeToPrimitiveKotlinType.get(kotlinTypeMakeNotNullable);
        if (simpleType2 != null) {
            return simpleType2;
        }
        ModuleDescriptor containingModuleOrNull = DescriptorUtils.getContainingModuleOrNull(kotlinTypeMakeNotNullable);
        if (containingModuleOrNull != null && (simpleType = this.unsignedPrimitives.invoke(containingModuleOrNull).kotlinArrayTypeToUnsignedKotlinType.get(kotlinTypeMakeNotNullable)) != null) {
            return simpleType;
        }
        throw new IllegalStateException("not array: " + kotlinType);
    }

    public SimpleType getPrimitiveArrayKotlinType(PrimitiveType primitiveType) {
        return this.primitives.invoke().primitiveTypeToArrayKotlinType.get(primitiveType);
    }

    public SimpleType getPrimitiveArrayKotlinTypeByPrimitiveKotlinType(KotlinType kotlinType) {
        ModuleDescriptor containingModuleOrNull;
        SimpleType simpleType = this.primitives.invoke().primitiveKotlinTypeToKotlinArrayType.get(kotlinType);
        if (simpleType != null) {
            return simpleType;
        }
        if (!UnsignedTypes.INSTANCE.isUnsignedType(kotlinType) || (containingModuleOrNull = DescriptorUtils.getContainingModuleOrNull(kotlinType)) == null) {
            return null;
        }
        return this.unsignedPrimitives.invoke(containingModuleOrNull).unsignedKotlinTypeToKotlinArrayType.get(kotlinType);
    }

    public static boolean isPrimitiveArray(FqNameUnsafe fqNameUnsafe) {
        return FQ_NAMES.arrayClassFqNameToPrimitiveType.get(fqNameUnsafe) != null;
    }

    public static PrimitiveType getPrimitiveType(DeclarationDescriptor declarationDescriptor) {
        FqNames fqNames = FQ_NAMES;
        if (fqNames.primitiveTypeShortNames.contains(declarationDescriptor.getName())) {
            return fqNames.fqNameToPrimitiveType.get(DescriptorUtils.getFqName(declarationDescriptor));
        }
        return null;
    }

    public static PrimitiveType getPrimitiveArrayType(DeclarationDescriptor declarationDescriptor) {
        FqNames fqNames = FQ_NAMES;
        if (fqNames.primitiveArrayTypeShortNames.contains(declarationDescriptor.getName())) {
            return fqNames.arrayClassFqNameToPrimitiveType.get(DescriptorUtils.getFqName(declarationDescriptor));
        }
        return null;
    }

    public SimpleType getArrayType(Variance variance, KotlinType kotlinType) {
        return KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), getArray(), Collections.singletonList(new TypeProjectionImpl(variance, kotlinType)));
    }

    public static boolean isArray(KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES.array);
    }

    public static boolean isArrayOrPrimitiveArray(ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.array) || getPrimitiveArrayType(classDescriptor) != null;
    }

    public static boolean isPrimitiveArray(KotlinType kotlinType) {
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = kotlinType.getConstructor().mo1333getDeclarationDescriptor();
        return (classifierDescriptorMo1333getDeclarationDescriptor == null || getPrimitiveArrayType(classifierDescriptorMo1333getDeclarationDescriptor) == null) ? false : true;
    }

    public static boolean isPrimitiveType(KotlinType kotlinType) {
        return !kotlinType.isMarkedNullable() && isPrimitiveTypeOrNullablePrimitiveType(kotlinType);
    }

    public static boolean isPrimitiveTypeOrNullablePrimitiveType(KotlinType kotlinType) {
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = kotlinType.getConstructor().mo1333getDeclarationDescriptor();
        return (classifierDescriptorMo1333getDeclarationDescriptor instanceof ClassDescriptor) && isPrimitiveClass((ClassDescriptor) classifierDescriptorMo1333getDeclarationDescriptor);
    }

    public static boolean isPrimitiveClass(ClassDescriptor classDescriptor) {
        return getPrimitiveType(classDescriptor) != null;
    }

    public static boolean isConstructedFromGivenClass(KotlinType kotlinType, FqNameUnsafe fqNameUnsafe) {
        ClassifierDescriptor classifierDescriptorMo1333getDeclarationDescriptor = kotlinType.getConstructor().mo1333getDeclarationDescriptor();
        return (classifierDescriptorMo1333getDeclarationDescriptor instanceof ClassDescriptor) && classFqNameEquals(classifierDescriptorMo1333getDeclarationDescriptor, fqNameUnsafe);
    }

    private static boolean classFqNameEquals(ClassifierDescriptor classifierDescriptor, FqNameUnsafe fqNameUnsafe) {
        return classifierDescriptor.getName().equals(fqNameUnsafe.shortName()) && fqNameUnsafe.equals(DescriptorUtils.getFqName(classifierDescriptor));
    }

    private static boolean isNotNullConstructedFromGivenClass(KotlinType kotlinType, FqNameUnsafe fqNameUnsafe) {
        return !kotlinType.isMarkedNullable() && isConstructedFromGivenClass(kotlinType, fqNameUnsafe);
    }

    public static boolean isSpecialClassWithNoSupertypes(ClassDescriptor classDescriptor) {
        FqNames fqNames = FQ_NAMES;
        return classFqNameEquals(classDescriptor, fqNames.any) || classFqNameEquals(classDescriptor, fqNames.nothing);
    }

    public static boolean isAny(ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.any);
    }

    public static boolean isBoolean(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._boolean);
    }

    public static boolean isChar(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._char);
    }

    public static boolean isInt(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._int);
    }

    public static boolean isByte(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._byte);
    }

    public static boolean isLong(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._long);
    }

    public static boolean isShort(KotlinType kotlinType) {
        return isConstructedFromGivenClassAndNotNullable(kotlinType, FQ_NAMES._short);
    }

    public static boolean isFloat(KotlinType kotlinType) {
        return isFloatOrNullableFloat(kotlinType) && !kotlinType.isMarkedNullable();
    }

    public static boolean isFloatOrNullableFloat(KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES._float);
    }

    public static boolean isDouble(KotlinType kotlinType) {
        return isDoubleOrNullableDouble(kotlinType) && !kotlinType.isMarkedNullable();
    }

    public static boolean isDoubleOrNullableDouble(KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES._double);
    }

    private static boolean isConstructedFromGivenClassAndNotNullable(KotlinType kotlinType, FqNameUnsafe fqNameUnsafe) {
        return isConstructedFromGivenClass(kotlinType, fqNameUnsafe) && !kotlinType.isMarkedNullable();
    }

    public static boolean isNothing(KotlinType kotlinType) {
        return isNothingOrNullableNothing(kotlinType) && !TypeUtils.isNullableType(kotlinType);
    }

    public static boolean isNothingOrNullableNothing(KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES.nothing);
    }

    public static boolean isAnyOrNullableAny(KotlinType kotlinType) {
        return isConstructedFromGivenClass(kotlinType, FQ_NAMES.any);
    }

    public static boolean isNullableAny(KotlinType kotlinType) {
        return isAnyOrNullableAny(kotlinType) && kotlinType.isMarkedNullable();
    }

    public static boolean isDefaultBound(KotlinType kotlinType) {
        return isNullableAny(kotlinType);
    }

    public static boolean isUnit(KotlinType kotlinType) {
        return isNotNullConstructedFromGivenClass(kotlinType, FQ_NAMES.unit);
    }

    public static boolean isString(KotlinType kotlinType) {
        return kotlinType != null && isNotNullConstructedFromGivenClass(kotlinType, FQ_NAMES.string);
    }

    public static boolean isKClass(ClassDescriptor classDescriptor) {
        return classFqNameEquals(classDescriptor, FQ_NAMES.kClass);
    }

    public static boolean isDeprecated(DeclarationDescriptor declarationDescriptor) {
        if (containsAnnotation(declarationDescriptor, FQ_NAMES.deprecated)) {
            return true;
        }
        if (!(declarationDescriptor instanceof PropertyDescriptor)) {
            return false;
        }
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) declarationDescriptor;
        boolean zIsVar = propertyDescriptor.isVar();
        PropertyGetterDescriptor getter = propertyDescriptor.getGetter();
        PropertySetterDescriptor setter = propertyDescriptor.getSetter();
        if (getter != null && isDeprecated(getter)) {
            if (!zIsVar) {
                return true;
            }
            if (setter != null && isDeprecated(setter)) {
                return true;
            }
        }
        return false;
    }

    public static FqName getPrimitiveFqName(PrimitiveType primitiveType) {
        return BUILT_INS_PACKAGE_FQ_NAME.child(primitiveType.getTypeName());
    }

    private static boolean containsAnnotation(DeclarationDescriptor declarationDescriptor, FqName fqName) {
        Annotations annotations = declarationDescriptor.getOriginal().getAnnotations();
        if (annotations.mo1326findAnnotation(fqName) != null) {
            return true;
        }
        AnnotationUseSiteTarget associatedUseSiteTarget = AnnotationUseSiteTarget.Companion.getAssociatedUseSiteTarget(declarationDescriptor);
        return (associatedUseSiteTarget == null || Annotations.Companion.findUseSiteTargetedAnnotation(annotations, associatedUseSiteTarget, fqName) == null) ? false : true;
    }
}
