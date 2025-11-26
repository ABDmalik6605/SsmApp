package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinaryModuleData.kt */
/* loaded from: classes2.dex */
public final class BinaryModuleData {
    private final List<String> annotations;

    public BinaryModuleData(List<String> annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        this.annotations = annotations;
    }
}
