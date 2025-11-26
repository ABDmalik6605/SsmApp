package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: exceptionUtils.kt */
/* loaded from: classes2.dex */
public final class ExceptionUtilsKt {
    public static final RuntimeException rethrow(Throwable e) throws Throwable {
        Intrinsics.checkParameterIsNotNull(e, "e");
        throw e;
    }

    public static final boolean isProcessCanceledException(Throwable receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Class<?> superclass = receiver.getClass();
        while (!Intrinsics.areEqual(superclass.getCanonicalName(), "com.intellij.openapi.progress.ProcessCanceledException")) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                return false;
            }
        }
        return true;
    }
}
