package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope;

/* compiled from: DeserializedDescriptorResolver.kt */
/* loaded from: classes2.dex */
public final class DeserializedDescriptorResolver {
    public DeserializationComponents components;
    public static final Companion Companion = new Companion(null);
    private static final Set<KotlinClassHeader.Kind> KOTLIN_CLASS = SetsKt.setOf(KotlinClassHeader.Kind.CLASS);
    private static final Set<KotlinClassHeader.Kind> KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART = SetsKt.setOf((Object[]) new KotlinClassHeader.Kind[]{KotlinClassHeader.Kind.FILE_FACADE, KotlinClassHeader.Kind.MULTIFILE_CLASS_PART});
    private static final JvmMetadataVersion KOTLIN_1_1_EAP_METADATA_VERSION = new JvmMetadataVersion(1, 1, 2);

    public final DeserializationComponents getComponents() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents;
    }

    public final void setComponents(DeserializationComponentsForJava components) {
        Intrinsics.checkParameterIsNotNull(components, "components");
        this.components = components.getComponents();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSkipMetadataVersionCheck() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents.getConfiguration().getSkipMetadataVersionCheck();
    }

    public final ClassDescriptor resolveClass(KotlinJvmBinaryClass kotlinClass) {
        Intrinsics.checkParameterIsNotNull(kotlinClass, "kotlinClass");
        ClassData classData$descriptors_jvm = readClassData$descriptors_jvm(kotlinClass);
        if (classData$descriptors_jvm == null) {
            return null;
        }
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents.getClassDeserializer().deserializeClass(kotlinClass.getClassId(), classData$descriptors_jvm);
    }

    public final ClassData readClassData$descriptors_jvm(KotlinJvmBinaryClass kotlinClass) {
        String[] strings;
        Pair<JvmNameResolver, ProtoBuf.Class> classDataFrom;
        Intrinsics.checkParameterIsNotNull(kotlinClass, "kotlinClass");
        String[] data$descriptors_jvm = readData$descriptors_jvm(kotlinClass, KOTLIN_CLASS);
        if (data$descriptors_jvm == null || (strings = kotlinClass.getClassHeader().getStrings()) == null) {
            return null;
        }
        try {
            try {
                classDataFrom = JvmProtoBufUtil.readClassDataFrom(data$descriptors_jvm, strings);
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalStateException("Could not read data from " + kotlinClass.getLocation(), e);
            }
        } catch (Throwable th) {
            if (getSkipMetadataVersionCheck() || kotlinClass.getClassHeader().getMetadataVersion().isCompatible()) {
                throw th;
            }
            classDataFrom = null;
        }
        if (classDataFrom != null) {
            return new ClassData(classDataFrom.component1(), classDataFrom.component2(), kotlinClass.getClassHeader().getMetadataVersion(), new KotlinJvmBinarySourceElement(kotlinClass, getIncompatibility(kotlinClass), isPreReleaseInvisible(kotlinClass)));
        }
        return null;
    }

    public final MemberScope createKotlinPackagePartScope(PackageFragmentDescriptor descriptor, KotlinJvmBinaryClass kotlinClass) {
        Pair<JvmNameResolver, ProtoBuf.Package> packageDataFrom;
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(kotlinClass, "kotlinClass");
        String[] data$descriptors_jvm = readData$descriptors_jvm(kotlinClass, KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART);
        if (data$descriptors_jvm != null) {
            String[] strings = kotlinClass.getClassHeader().getStrings();
            try {
            } catch (Throwable th) {
                if (getSkipMetadataVersionCheck() || kotlinClass.getClassHeader().getMetadataVersion().isCompatible()) {
                    throw th;
                }
                packageDataFrom = null;
            }
            if (strings != null) {
                try {
                    packageDataFrom = JvmProtoBufUtil.readPackageDataFrom(data$descriptors_jvm, strings);
                    if (packageDataFrom == null) {
                        return null;
                    }
                    JvmNameResolver jvmNameResolverComponent1 = packageDataFrom.component1();
                    ProtoBuf.Package packageComponent2 = packageDataFrom.component2();
                    JvmNameResolver jvmNameResolver = jvmNameResolverComponent1;
                    JvmPackagePartSource jvmPackagePartSource = new JvmPackagePartSource(kotlinClass, packageComponent2, jvmNameResolver, getIncompatibility(kotlinClass), isPreReleaseInvisible(kotlinClass));
                    JvmMetadataVersion metadataVersion = kotlinClass.getClassHeader().getMetadataVersion();
                    JvmPackagePartSource jvmPackagePartSource2 = jvmPackagePartSource;
                    DeserializationComponents deserializationComponents = this.components;
                    if (deserializationComponents == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("components");
                    }
                    return new DeserializedPackageMemberScope(descriptor, packageComponent2, jvmNameResolver, metadataVersion, jvmPackagePartSource2, deserializationComponents, new Function0<List<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver.createKotlinPackagePartScope.2
                        @Override // kotlin.jvm.functions.Function0
                        public final List<? extends Name> invoke() {
                            return CollectionsKt.emptyList();
                        }
                    });
                } catch (InvalidProtocolBufferException e) {
                    throw new IllegalStateException("Could not read data from " + kotlinClass.getLocation(), e);
                }
            }
        }
        return null;
    }

    private final IncompatibleVersionErrorData<JvmMetadataVersion> getIncompatibility(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        if (getSkipMetadataVersionCheck() || kotlinJvmBinaryClass.getClassHeader().getMetadataVersion().isCompatible()) {
            return null;
        }
        return new IncompatibleVersionErrorData<>(kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), JvmMetadataVersion.INSTANCE, kotlinJvmBinaryClass.getLocation(), kotlinJvmBinaryClass.getClassId());
    }

    private final boolean isPreReleaseInvisible(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents.getConfiguration().getReportErrorsOnPreReleaseDependencies() && (kotlinJvmBinaryClass.getClassHeader().isPreRelease() || Intrinsics.areEqual(kotlinJvmBinaryClass.getClassHeader().getMetadataVersion(), KOTLIN_1_1_EAP_METADATA_VERSION));
    }

    public final String[] readData$descriptors_jvm(KotlinJvmBinaryClass kotlinClass, Set<? extends KotlinClassHeader.Kind> expectedKinds) {
        Intrinsics.checkParameterIsNotNull(kotlinClass, "kotlinClass");
        Intrinsics.checkParameterIsNotNull(expectedKinds, "expectedKinds");
        KotlinClassHeader classHeader = kotlinClass.getClassHeader();
        String[] data = classHeader.getData();
        if (data == null) {
            data = classHeader.getIncompatibleData();
        }
        if (data == null) {
            return null;
        }
        if (!expectedKinds.contains(classHeader.getKind())) {
            data = null;
        }
        return data;
    }

    /* compiled from: DeserializedDescriptorResolver.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
