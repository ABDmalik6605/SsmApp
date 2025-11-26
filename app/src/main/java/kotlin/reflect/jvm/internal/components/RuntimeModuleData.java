package kotlin.reflect.jvm.internal.components;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypes;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.SamConversionResolver;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.SingleModuleClassResolver;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JavaClassDataFinder;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JavaDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.text.Typography;

/* compiled from: RuntimeModuleData.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimeModuleData;", "", "deserialization", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/DeserializationComponents;", "packagePartProvider", "Lkotlin/reflect/jvm/internal/components/RuntimePackagePartProvider;", "(Lorg/jetbrains/kotlin/serialization/deserialization/DeserializationComponents;Lkotlin/reflect/jvm/internal/components/RuntimePackagePartProvider;)V", "getDeserialization", "()Lorg/jetbrains/kotlin/serialization/deserialization/DeserializationComponents;", "module", "Lkotlin/reflect/jvm/internal/impl/descriptors/ModuleDescriptor;", "getModule", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getPackagePartProvider", "()Lkotlin/reflect/jvm/internal/components/RuntimePackagePartProvider;", "Companion", "descriptors.runtime"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class RuntimeModuleData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final DeserializationComponents deserialization;
    private final RuntimePackagePartProvider packagePartProvider;

    private RuntimeModuleData(DeserializationComponents deserializationComponents, RuntimePackagePartProvider runtimePackagePartProvider) {
        this.deserialization = deserializationComponents;
        this.packagePartProvider = runtimePackagePartProvider;
    }

    public /* synthetic */ RuntimeModuleData(DeserializationComponents deserializationComponents, RuntimePackagePartProvider runtimePackagePartProvider, DefaultConstructorMarker defaultConstructorMarker) {
        this(deserializationComponents, runtimePackagePartProvider);
    }

    public final DeserializationComponents getDeserialization() {
        return this.deserialization;
    }

    public final RuntimePackagePartProvider getPackagePartProvider() {
        return this.packagePartProvider;
    }

    public final ModuleDescriptor getModule() {
        return this.deserialization.getModuleDescriptor();
    }

    /* compiled from: RuntimeModuleData.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimeModuleData$Companion;", "", "()V", "create", "Lkotlin/reflect/jvm/internal/components/RuntimeModuleData;", "classLoader", "Ljava/lang/ClassLoader;", "descriptors.runtime"}, k = 1, mv = {1, 1, 11})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RuntimeModuleData create(ClassLoader classLoader) {
            Intrinsics.checkParameterIsNotNull(classLoader, "classLoader");
            LockBasedStorageManager lockBasedStorageManager = new LockBasedStorageManager();
            JvmBuiltIns jvmBuiltIns = new JvmBuiltIns(lockBasedStorageManager, false, 2, null);
            Name nameSpecial = Name.special("<runtime module for " + classLoader + Typography.greater);
            Intrinsics.checkExpressionValueIsNotNull(nameSpecial, "Name.special(\"<runtime module for $classLoader>\")");
            ModuleDescriptorImpl moduleDescriptorImpl = new ModuleDescriptorImpl(nameSpecial, lockBasedStorageManager, jvmBuiltIns, null, null, null, 56, null);
            ReflectKotlinClassFinder reflectKotlinClassFinder = new ReflectKotlinClassFinder(classLoader);
            DeserializedDescriptorResolver deserializedDescriptorResolver = new DeserializedDescriptorResolver();
            SingleModuleClassResolver singleModuleClassResolver = new SingleModuleClassResolver();
            RuntimePackagePartProvider runtimePackagePartProvider = new RuntimePackagePartProvider(classLoader);
            JavaResolverCache javaResolverCache = JavaResolverCache.EMPTY;
            ModuleDescriptorImpl moduleDescriptorImpl2 = moduleDescriptorImpl;
            NotFoundClasses notFoundClasses = new NotFoundClasses(lockBasedStorageManager, moduleDescriptorImpl2);
            AnnotationTypeQualifierResolver annotationTypeQualifierResolver = new AnnotationTypeQualifierResolver(lockBasedStorageManager, Jsr305State.DISABLED);
            ReflectJavaClassFinder reflectJavaClassFinder = new ReflectJavaClassFinder(classLoader);
            ReflectKotlinClassFinder reflectKotlinClassFinder2 = reflectKotlinClassFinder;
            SignaturePropagator signaturePropagator = SignaturePropagator.DO_NOTHING;
            Intrinsics.checkExpressionValueIsNotNull(signaturePropagator, "SignaturePropagator.DO_NOTHING");
            RuntimeErrorReporter runtimeErrorReporter = RuntimeErrorReporter.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(javaResolverCache, "javaResolverCache");
            LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider = new LazyJavaPackageFragmentProvider(new JavaResolverComponents(lockBasedStorageManager, reflectJavaClassFinder, reflectKotlinClassFinder2, deserializedDescriptorResolver, signaturePropagator, runtimeErrorReporter, javaResolverCache, JavaPropertyInitializerEvaluator.DoNothing.INSTANCE, SamConversionResolver.Empty.INSTANCE, RuntimeSourceElementFactory.INSTANCE, singleModuleClassResolver, runtimePackagePartProvider, SupertypeLoopChecker.EMPTY.INSTANCE, LookupTracker.DO_NOTHING.INSTANCE, moduleDescriptorImpl2, new ReflectionTypes(moduleDescriptorImpl2, notFoundClasses), annotationTypeQualifierResolver, new SignatureEnhancement(annotationTypeQualifierResolver, Jsr305State.DISABLED), JavaClassesTracker.Default.INSTANCE, JavaResolverSettings.Default.INSTANCE));
            jvmBuiltIns.initialize(moduleDescriptorImpl2, true);
            JavaDescriptorResolver javaDescriptorResolver = new JavaDescriptorResolver(lazyJavaPackageFragmentProvider, javaResolverCache);
            DeserializationComponentsForJava deserializationComponentsForJava = new DeserializationComponentsForJava(lockBasedStorageManager, moduleDescriptorImpl2, DeserializationConfiguration.Default.INSTANCE, new JavaClassDataFinder(reflectKotlinClassFinder2, deserializedDescriptorResolver), new BinaryClassAnnotationAndConstantLoaderImpl(moduleDescriptorImpl2, notFoundClasses, lockBasedStorageManager, reflectKotlinClassFinder2), lazyJavaPackageFragmentProvider, notFoundClasses, RuntimeErrorReporter.INSTANCE, LookupTracker.DO_NOTHING.INSTANCE, ContractDeserializer.Companion.getDEFAULT());
            singleModuleClassResolver.setResolver(javaDescriptorResolver);
            deserializedDescriptorResolver.setComponents(deserializationComponentsForJava);
            ModuleDescriptorImpl builtInsModule = jvmBuiltIns.getBuiltInsModule();
            Intrinsics.checkExpressionValueIsNotNull(builtInsModule, "builtIns.builtInsModule");
            moduleDescriptorImpl.setDependencies(moduleDescriptorImpl, builtInsModule);
            moduleDescriptorImpl.initialize(javaDescriptorResolver.getPackageFragmentProvider());
            return new RuntimeModuleData(deserializationComponentsForJava.getComponents(), runtimePackagePartProvider, null);
        }
    }
}
