package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;

/* compiled from: BuiltInsInitializer.kt */
/* loaded from: classes2.dex */
public final class BuiltInsInitializer<T extends KotlinBuiltIns> {
    private final Function0<T> constructor;
    private Throwable initializationFailed;
    private volatile boolean initializing;
    private volatile T instance;

    /* JADX WARN: Multi-variable type inference failed */
    public BuiltInsInitializer(Function0<? extends T> constructor) {
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        this.constructor = constructor;
    }

    private final synchronized void initialize() {
        if (this.instance == null) {
            if (this.initializationFailed != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Built-in library initialization failed previously: ");
                Throwable th = this.initializationFailed;
                if (th == null) {
                    Intrinsics.throwNpe();
                }
                sb.append(th);
                throw new IllegalStateException(sb.toString(), this.initializationFailed);
            }
            if (this.initializing) {
                throw new IllegalStateException("Built-in library initialization loop");
            }
            this.initializing = true;
            try {
                this.instance = this.constructor.invoke();
            } finally {
            }
        }
    }

    public final T get() {
        T t;
        if (this.initializing) {
            synchronized (this) {
                t = this.instance;
                if (t == null) {
                    throw new AssertionError("Built-ins are not initialized (note: We are under the same lock as initializing and instance)");
                }
            }
            return t;
        }
        if (this.instance == null) {
            initialize();
        }
        T t2 = this.instance;
        if (t2 == null) {
            Intrinsics.throwNpe();
        }
        return t2;
    }
}
