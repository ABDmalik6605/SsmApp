package kotlin.reflect.jvm.internal.components;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.kotlin.ModuleMappingUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.PackageParts;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;

/* compiled from: RuntimePackagePartProvider.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e2\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u0005\u001a*\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u0006j\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimePackagePartProvider;", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/PackagePartProvider;", "classLoader", "Ljava/lang/ClassLoader;", "(Ljava/lang/ClassLoader;)V", "packageParts", "Ljava/util/HashMap;", "", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/HashMap;", "visitedModules", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "findPackageParts", "", "packageFqName", "getAnnotationsOnBinaryModule", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "moduleName", "registerModule", "", "EmptyEnumeration", "descriptors.runtime"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class RuntimePackagePartProvider implements PackagePartProvider {
    private final ClassLoader classLoader;
    private final HashMap<String, LinkedHashSet<String>> packageParts;
    private final HashSet<String> visitedModules;

    public RuntimePackagePartProvider(ClassLoader classLoader) {
        Intrinsics.checkParameterIsNotNull(classLoader, "classLoader");
        this.classLoader = classLoader;
        this.visitedModules = new HashSet<>();
        this.packageParts = new HashMap<>();
    }

    public final synchronized void registerModule(String moduleName) {
        EmptyEnumeration resources;
        Intrinsics.checkParameterIsNotNull(moduleName, "moduleName");
        if (this.visitedModules.add(moduleName)) {
            String str = "META-INF/" + moduleName + ".kotlin_module";
            try {
                resources = this.classLoader.getResources(str);
            } catch (IOException unused) {
                resources = EmptyEnumeration.INSTANCE;
            }
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            Iterator it = CollectionsKt.iterator(resources);
            while (it.hasNext()) {
                try {
                    InputStream inputStreamOpenStream = ((URL) it.next()).openStream();
                    if (inputStreamOpenStream != null) {
                        InputStream inputStream = inputStreamOpenStream;
                        Throwable th = (Throwable) null;
                        try {
                            for (Map.Entry<String, PackageParts> entry : ModuleMappingUtilKt.loadModuleMapping(ModuleMapping.Companion, ByteStreamsKt.readBytes$default(inputStream, 0, 1, null), str, DeserializationConfiguration.Default.INSTANCE, new Function1<JvmMetadataVersion, Unit>() { // from class: kotlin.reflect.jvm.internal.components.RuntimePackagePartProvider$registerModule$1$mapping$1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(JvmMetadataVersion jvmMetadataVersion) {
                                    invoke2(jvmMetadataVersion);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(JvmMetadataVersion version) {
                                    Intrinsics.checkParameterIsNotNull(version, "version");
                                    throw new UnsupportedOperationException("Module was compiled with an incompatible version of Kotlin. The binary version of its metadata is " + version + ", expected version is " + JvmMetadataVersion.INSTANCE + ". Please update Kotlin to the latest version");
                                }
                            }).getPackageFqName2Parts().entrySet()) {
                                String key = entry.getKey();
                                PackageParts value = entry.getValue();
                                HashMap<String, LinkedHashSet<String>> map = this.packageParts;
                                LinkedHashSet<String> linkedHashSet = map.get(key);
                                if (linkedHashSet == null) {
                                    linkedHashSet = new LinkedHashSet<>();
                                    map.put(key, linkedHashSet);
                                }
                                linkedHashSet.addAll(value.getParts());
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(inputStream, th);
                        } catch (Throwable th2) {
                            try {
                                throw th2;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(inputStream, th2);
                                throw th3;
                            }
                        }
                    } else {
                        continue;
                    }
                } catch (UnsupportedOperationException e) {
                    throw e;
                } catch (Exception unused2) {
                }
            }
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider
    public synchronized List<String> findPackageParts(String packageFqName) {
        List<String> list;
        Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
        LinkedHashSet<String> linkedHashSet = this.packageParts.get(packageFqName);
        list = linkedHashSet != null ? CollectionsKt.toList(linkedHashSet) : null;
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        return list;
    }

    /* compiled from: RuntimePackagePartProvider.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimePackagePartProvider$EmptyEnumeration;", "Ljava/util/Enumeration;", "", "()V", "hasMoreElements", "", "nextElement", "descriptors.runtime"}, k = 1, mv = {1, 1, 11})
    private static final class EmptyEnumeration implements Enumeration {
        public static final EmptyEnumeration INSTANCE = new EmptyEnumeration();

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        private EmptyEnumeration() {
        }

        @Override // java.util.Enumeration
        public Void nextElement() {
            throw new NoSuchElementException();
        }
    }
}
