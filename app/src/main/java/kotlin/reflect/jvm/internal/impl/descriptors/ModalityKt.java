package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: Modality.kt */
/* loaded from: classes2.dex */
public final class ModalityKt {
    public static final boolean isFinalClass(ClassDescriptor receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.getModality() == Modality.FINAL && receiver.getKind() != ClassKind.ENUM_CLASS;
    }
}
