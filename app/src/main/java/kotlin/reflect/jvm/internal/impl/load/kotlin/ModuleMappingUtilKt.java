package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;

/* compiled from: ModuleMappingUtil.kt */
/* loaded from: classes2.dex */
public final class ModuleMappingUtilKt {
    public static final ModuleMapping loadModuleMapping(ModuleMapping.Companion receiver, byte[] bArr, String debugName, DeserializationConfiguration configuration, Function1<? super JvmMetadataVersion, Unit> reportIncompatibleVersionError) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(debugName, "debugName");
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        Intrinsics.checkParameterIsNotNull(reportIncompatibleVersionError, "reportIncompatibleVersionError");
        return receiver.loadModuleMapping(bArr, debugName, configuration.getSkipMetadataVersionCheck(), configuration.isJvmPackageNameSupported(), reportIncompatibleVersionError);
    }
}
