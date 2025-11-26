package kotlin.reflect.jvm.internal;

import java.lang.ref.SoftReference;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class ReflectProperties {

    public static abstract class Val<T> {
        private static final Object NULL_VALUE = new Object() { // from class: kotlin.reflect.jvm.internal.ReflectProperties.Val.1
        };

        public abstract T invoke();

        public final T getValue(Object obj, Object obj2) {
            return invoke();
        }

        protected Object escape(T t) {
            return t == null ? NULL_VALUE : t;
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected T unescape(Object obj) {
            if (obj == NULL_VALUE) {
                return null;
            }
            return obj;
        }
    }

    public static class LazyVal<T> extends Val<T> {
        private final Function0<T> initializer;
        private Object value = null;

        public LazyVal(Function0<T> function0) {
            this.initializer = function0;
        }

        @Override // kotlin.reflect.jvm.internal.ReflectProperties.Val
        public T invoke() {
            Object obj = this.value;
            if (obj != null) {
                return unescape(obj);
            }
            T tInvoke = this.initializer.invoke();
            this.value = escape(tInvoke);
            return tInvoke;
        }
    }

    public static class LazySoftVal<T> extends Val<T> {
        private final Function0<T> initializer;
        private SoftReference<Object> value;

        public LazySoftVal(T t, Function0<T> function0) {
            this.value = null;
            this.initializer = function0;
            if (t != null) {
                this.value = new SoftReference<>(escape(t));
            }
        }

        @Override // kotlin.reflect.jvm.internal.ReflectProperties.Val
        public T invoke() {
            Object obj;
            SoftReference<Object> softReference = this.value;
            if (softReference != null && (obj = softReference.get()) != null) {
                return unescape(obj);
            }
            T tInvoke = this.initializer.invoke();
            this.value = new SoftReference<>(escape(tInvoke));
            return tInvoke;
        }
    }

    public static <T> LazyVal<T> lazy(Function0<T> function0) {
        return new LazyVal<>(function0);
    }

    public static <T> LazySoftVal<T> lazySoft(T t, Function0<T> function0) {
        return new LazySoftVal<>(t, function0);
    }

    public static <T> LazySoftVal<T> lazySoft(Function0<T> function0) {
        return lazySoft(null, function0);
    }
}
