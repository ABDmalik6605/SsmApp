package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolverImpl;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf;
import kotlin.reflect.jvm.internal.impl.protobuf.ProtocolStringList;

/* compiled from: ModuleMapping.kt */
/* loaded from: classes2.dex */
public final class ModuleMapping {
    private final String debugName;
    private final BinaryModuleData moduleData;
    private final Map<String, PackageParts> packageFqName2Parts;
    public static final Companion Companion = new Companion(null);
    public static final ModuleMapping EMPTY = new ModuleMapping(MapsKt.emptyMap(), new BinaryModuleData(CollectionsKt.emptyList()), "EMPTY");
    public static final ModuleMapping CORRUPTED = new ModuleMapping(MapsKt.emptyMap(), new BinaryModuleData(CollectionsKt.emptyList()), "CORRUPTED");

    private ModuleMapping(Map<String, PackageParts> map, BinaryModuleData binaryModuleData, String str) {
        this.packageFqName2Parts = map;
        this.moduleData = binaryModuleData;
        this.debugName = str;
    }

    public /* synthetic */ ModuleMapping(Map map, BinaryModuleData binaryModuleData, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, binaryModuleData, str);
    }

    public final Map<String, PackageParts> getPackageFqName2Parts() {
        return this.packageFqName2Parts;
    }

    public String toString() {
        return this.debugName;
    }

    /* compiled from: ModuleMapping.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ModuleMapping loadModuleMapping(byte[] bArr, String debugName, boolean z, boolean z2, Function1<? super JvmMetadataVersion, Unit> reportIncompatibleVersionError) throws IOException {
            DefaultConstructorMarker defaultConstructorMarker;
            String str;
            Intrinsics.checkParameterIsNotNull(debugName, "debugName");
            Intrinsics.checkParameterIsNotNull(reportIncompatibleVersionError, "reportIncompatibleVersionError");
            if (bArr == null) {
                return ModuleMapping.EMPTY;
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            try {
                int i = dataInputStream.readInt();
                int[] iArr = new int[i];
                for (int i2 = 0; i2 < i; i2++) {
                    iArr[i2] = dataInputStream.readInt();
                }
                JvmMetadataVersion jvmMetadataVersion = new JvmMetadataVersion(Arrays.copyOf(iArr, i));
                if (!z && !jvmMetadataVersion.isCompatible()) {
                    reportIncompatibleVersionError.invoke(jvmMetadataVersion);
                    return ModuleMapping.EMPTY;
                }
                JvmModuleProtoBuf.Module from = JvmModuleProtoBuf.Module.parseFrom(dataInputStream);
                if (from == null) {
                    return ModuleMapping.EMPTY;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Iterator<JvmModuleProtoBuf.PackageParts> it = from.getPackagePartsList().iterator();
                while (true) {
                    defaultConstructorMarker = null;
                    if (!it.hasNext()) {
                        break;
                    }
                    JvmModuleProtoBuf.PackageParts proto = it.next();
                    Intrinsics.checkExpressionValueIsNotNull(proto, "proto");
                    String packageFqName = proto.getPackageFqName();
                    LinkedHashMap linkedHashMap2 = linkedHashMap;
                    Intrinsics.checkExpressionValueIsNotNull(packageFqName, "packageFqName");
                    Object packageParts = linkedHashMap2.get(packageFqName);
                    if (packageParts == null) {
                        packageParts = new PackageParts(packageFqName);
                        linkedHashMap2.put(packageFqName, packageParts);
                    }
                    PackageParts packageParts2 = (PackageParts) packageParts;
                    ProtocolStringList shortClassNameList = proto.getShortClassNameList();
                    Intrinsics.checkExpressionValueIsNotNull(shortClassNameList, "proto.shortClassNameList");
                    int i3 = 0;
                    for (String partShortName : shortClassNameList) {
                        List<Integer> multifileFacadeShortNameIdList = proto.getMultifileFacadeShortNameIdList();
                        Intrinsics.checkExpressionValueIsNotNull(multifileFacadeShortNameIdList, "proto.multifileFacadeShortNameIdList");
                        Integer numValueOf = ((Integer) CollectionsKt.getOrNull(multifileFacadeShortNameIdList, i3)) != null ? Integer.valueOf(r13.intValue() - 1) : null;
                        if (numValueOf != null) {
                            ProtocolStringList multifileFacadeShortNameList = proto.getMultifileFacadeShortNameList();
                            Intrinsics.checkExpressionValueIsNotNull(multifileFacadeShortNameList, "proto.multifileFacadeShortNameList");
                            str = (String) CollectionsKt.getOrNull(multifileFacadeShortNameList, numValueOf.intValue());
                        } else {
                            str = null;
                        }
                        String strInternalNameOf = str != null ? ModuleMappingKt.internalNameOf(packageFqName, str) : null;
                        Intrinsics.checkExpressionValueIsNotNull(partShortName, "partShortName");
                        packageParts2.addPart(ModuleMappingKt.internalNameOf(packageFqName, partShortName), strInternalNameOf);
                        i3++;
                    }
                    if (z2) {
                        ProtocolStringList classWithJvmPackageNameShortNameList = proto.getClassWithJvmPackageNameShortNameList();
                        Intrinsics.checkExpressionValueIsNotNull(classWithJvmPackageNameShortNameList, "proto.classWithJvmPackageNameShortNameList");
                        int i4 = 0;
                        for (String partShortName2 : classWithJvmPackageNameShortNameList) {
                            List<Integer> classWithJvmPackageNamePackageIdList = proto.getClassWithJvmPackageNamePackageIdList();
                            Intrinsics.checkExpressionValueIsNotNull(classWithJvmPackageNamePackageIdList, "proto.classWithJvmPackageNamePackageIdList");
                            Integer num = (Integer) CollectionsKt.getOrNull(classWithJvmPackageNamePackageIdList, i4);
                            if (num == null) {
                                List<Integer> classWithJvmPackageNamePackageIdList2 = proto.getClassWithJvmPackageNamePackageIdList();
                                Intrinsics.checkExpressionValueIsNotNull(classWithJvmPackageNamePackageIdList2, "proto.classWithJvmPackageNamePackageIdList");
                                num = (Integer) CollectionsKt.lastOrNull((List) classWithJvmPackageNamePackageIdList2);
                            }
                            if (num != null) {
                                int iIntValue = num.intValue();
                                ProtocolStringList jvmPackageNameList = from.getJvmPackageNameList();
                                Intrinsics.checkExpressionValueIsNotNull(jvmPackageNameList, "moduleProto.jvmPackageNameList");
                                String str2 = (String) CollectionsKt.getOrNull(jvmPackageNameList, iIntValue);
                                if (str2 != null) {
                                    Intrinsics.checkExpressionValueIsNotNull(partShortName2, "partShortName");
                                    packageParts2.addPart(ModuleMappingKt.internalNameOf(str2, partShortName2), null);
                                }
                            }
                            i4++;
                        }
                    }
                }
                for (JvmModuleProtoBuf.PackageParts proto2 : from.getMetadataPartsList()) {
                    LinkedHashMap linkedHashMap3 = linkedHashMap;
                    Intrinsics.checkExpressionValueIsNotNull(proto2, "proto");
                    String packageFqName2 = proto2.getPackageFqName();
                    Intrinsics.checkExpressionValueIsNotNull(packageFqName2, "proto.packageFqName");
                    Object packageParts3 = linkedHashMap3.get(packageFqName2);
                    if (packageParts3 == null) {
                        String packageFqName3 = proto2.getPackageFqName();
                        Intrinsics.checkExpressionValueIsNotNull(packageFqName3, "proto.packageFqName");
                        packageParts3 = new PackageParts(packageFqName3);
                        linkedHashMap3.put(packageFqName2, packageParts3);
                    }
                    PackageParts packageParts4 = (PackageParts) packageParts3;
                    ProtocolStringList shortClassNameList2 = proto2.getShortClassNameList();
                    Intrinsics.checkExpressionValueIsNotNull(shortClassNameList2, "proto.shortClassNameList");
                    Iterator<String> it2 = shortClassNameList2.iterator();
                    while (it2.hasNext()) {
                        packageParts4.addMetadataPart(it2.next());
                    }
                }
                ProtoBuf.StringTable stringTable = from.getStringTable();
                Intrinsics.checkExpressionValueIsNotNull(stringTable, "moduleProto.stringTable");
                ProtoBuf.QualifiedNameTable qualifiedNameTable = from.getQualifiedNameTable();
                Intrinsics.checkExpressionValueIsNotNull(qualifiedNameTable, "moduleProto.qualifiedNameTable");
                NameResolverImpl nameResolverImpl = new NameResolverImpl(stringTable, qualifiedNameTable);
                List<ProtoBuf.Annotation> annotationList = from.getAnnotationList();
                Intrinsics.checkExpressionValueIsNotNull(annotationList, "moduleProto.annotationList");
                List<ProtoBuf.Annotation> list = annotationList;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (ProtoBuf.Annotation proto3 : list) {
                    Intrinsics.checkExpressionValueIsNotNull(proto3, "proto");
                    arrayList.add(nameResolverImpl.getQualifiedClassName(proto3.getId()));
                }
                return new ModuleMapping(linkedHashMap, new BinaryModuleData(arrayList), debugName, defaultConstructorMarker);
            } catch (IOException unused) {
                return ModuleMapping.CORRUPTED;
            }
        }
    }
}
