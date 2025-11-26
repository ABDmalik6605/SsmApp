package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* compiled from: storage.kt */
/* loaded from: classes2.dex */
public final class StorageKt {
    public static final <T> T getValue(NotNullLazyValue<? extends T> receiver, Object obj, KProperty<?> p) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(p, "p");
        return receiver.invoke();
    }

    public static final <T> T getValue(NullableLazyValue<? extends T> receiver, Object obj, KProperty<?> p) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(p, "p");
        return receiver.invoke();
    }
}
