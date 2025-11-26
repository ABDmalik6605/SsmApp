package kotlin.reflect.jvm.internal.impl.utils;

/* loaded from: classes2.dex */
public class WrappedValues {
    private static final Object NULL_VALUE = new Object() { // from class: kotlin.reflect.jvm.internal.impl.utils.WrappedValues.1
        public String toString() {
            return "NULL_VALUE";
        }
    };
    public static volatile boolean throwWrappedProcessCanceledException = false;

    private static final class ThrowableWrapper {
        private final Throwable throwable;

        private ThrowableWrapper(Throwable th) {
            this.throwable = th;
        }

        public Throwable getThrowable() {
            return this.throwable;
        }

        public String toString() {
            return this.throwable.toString();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <V> V unescapeNull(Object obj) {
        if (obj == NULL_VALUE) {
            return null;
        }
        return obj;
    }

    public static <V> Object escapeNull(V v) {
        return v == null ? NULL_VALUE : v;
    }

    public static Object escapeThrowable(Throwable th) {
        return new ThrowableWrapper(th);
    }

    public static <V> V unescapeExceptionOrNull(Object obj) {
        return (V) unescapeNull(unescapeThrowable(obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <V> V unescapeThrowable(Object obj) {
        if (!(obj instanceof ThrowableWrapper)) {
            return obj;
        }
        Throwable throwable = ((ThrowableWrapper) obj).getThrowable();
        if (throwWrappedProcessCanceledException && ExceptionUtilsKt.isProcessCanceledException(throwable)) {
            throw new WrappedProcessCanceledException(throwable);
        }
        throw ExceptionUtilsKt.rethrow(throwable);
    }

    public static class WrappedProcessCanceledException extends RuntimeException {
        public WrappedProcessCanceledException(Throwable th) {
            super("Rethrow stored exception", th);
        }
    }
}
