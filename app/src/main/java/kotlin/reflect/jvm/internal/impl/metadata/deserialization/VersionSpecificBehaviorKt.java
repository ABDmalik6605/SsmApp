package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: versionSpecificBehavior.kt */
/* loaded from: classes2.dex */
public final class VersionSpecificBehaviorKt {
    public static final boolean isVersionRequirementTableWrittenCorrectly(BinaryVersion version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        return isKotlin1Dot4OrLater(version);
    }

    private static final boolean isKotlin1Dot4OrLater(BinaryVersion binaryVersion) {
        return binaryVersion.getMajor() == 1 && binaryVersion.getMinor() >= 4;
    }
}
