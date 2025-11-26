package kotlin.reflect.jvm.internal.impl.storage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.WrappedValues;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public class LockBasedStorageManager implements StorageManager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final String debugText;
    private final ExceptionHandlingStrategy exceptionHandlingStrategy;
    protected final Lock lock;
    private static final String PACKAGE_NAME = StringsKt.substringBeforeLast(LockBasedStorageManager.class.getCanonicalName(), ".", "");
    public static final StorageManager NO_LOCKS = new LockBasedStorageManager("NO_LOCKS", ExceptionHandlingStrategy.THROW, NoLock.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.1
        @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager
        protected <T> RecursionDetectedResult<T> recursionDetectedDefault() {
            return RecursionDetectedResult.fallThrough();
        }
    };

    public interface ExceptionHandlingStrategy {
        public static final ExceptionHandlingStrategy THROW = new ExceptionHandlingStrategy() { // from class: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.ExceptionHandlingStrategy.1
            @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.ExceptionHandlingStrategy
            public RuntimeException handleException(Throwable th) {
                throw ExceptionUtilsKt.rethrow(th);
            }
        };

        RuntimeException handleException(Throwable th);
    }

    private enum NotValue {
        NOT_COMPUTED,
        COMPUTING,
        RECURSION_WAS_DETECTED
    }

    private static String defaultDebugName() {
        return "<unknown creating class>";
    }

    private LockBasedStorageManager(String str, ExceptionHandlingStrategy exceptionHandlingStrategy, Lock lock) {
        this.lock = lock;
        this.exceptionHandlingStrategy = exceptionHandlingStrategy;
        this.debugText = str;
    }

    public LockBasedStorageManager() {
        this(defaultDebugName(), ExceptionHandlingStrategy.THROW, new ReentrantLock());
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (" + this.debugText + ")";
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public <K, V> MemoizedFunctionToNotNull<K, V> createMemoizedFunction(Function1<? super K, ? extends V> function1) {
        return createMemoizedFunction(function1, createConcurrentHashMap());
    }

    public <K, V> MemoizedFunctionToNotNull<K, V> createMemoizedFunction(Function1<? super K, ? extends V> function1, ConcurrentMap<K, Object> concurrentMap) {
        return new MapBasedMemoizedFunctionToNotNull(this, concurrentMap, function1);
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public <K, V> MemoizedFunctionToNullable<K, V> createMemoizedFunctionWithNullableValues(Function1<? super K, ? extends V> function1) {
        return createMemoizedFunctionWithNullableValues(function1, createConcurrentHashMap());
    }

    public <K, V> MemoizedFunctionToNullable<K, V> createMemoizedFunctionWithNullableValues(Function1<? super K, ? extends V> function1, ConcurrentMap<K, Object> concurrentMap) {
        return new MapBasedMemoizedFunction(this, concurrentMap, function1);
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public <T> NotNullLazyValue<T> createLazyValue(Function0<? extends T> function0) {
        return new LockBasedNotNullLazyValue(this, function0);
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public <T> NotNullLazyValue<T> createRecursionTolerantLazyValue(Function0<? extends T> function0, final T t) {
        return new LockBasedNotNullLazyValue<T>(this, function0) { // from class: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.2
            @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue
            protected RecursionDetectedResult<T> recursionDetected(boolean z) {
                return RecursionDetectedResult.value(t);
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public <T> NotNullLazyValue<T> createLazyValueWithPostCompute(Function0<? extends T> function0, final Function1<? super Boolean, ? extends T> function1, final Function1<? super T, Unit> function12) {
        return new LockBasedNotNullLazyValue<T>(this, function0) { // from class: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.3
            @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue
            protected RecursionDetectedResult<T> recursionDetected(boolean z) {
                Function1 function13 = function1;
                if (function13 == null) {
                    return super.recursionDetected(z);
                }
                return RecursionDetectedResult.value(function13.invoke(Boolean.valueOf(z)));
            }

            @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue
            protected void postCompute(T t) {
                function12.invoke(t);
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public <T> NullableLazyValue<T> createNullableLazyValue(Function0<? extends T> function0) {
        return new LockBasedLazyValue(this, function0);
    }

    private static <K> ConcurrentMap<K, Object> createConcurrentHashMap() {
        return new ConcurrentHashMap(3, 1.0f, 2);
    }

    protected <T> RecursionDetectedResult<T> recursionDetectedDefault() {
        throw ((IllegalStateException) sanitizeStackTrace(new IllegalStateException("Recursive call in a lazy value under " + this)));
    }

    private static class RecursionDetectedResult<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean fallThrough;
        private final T value;

        public static <T> RecursionDetectedResult<T> value(T t) {
            return new RecursionDetectedResult<>(t, false);
        }

        public static <T> RecursionDetectedResult<T> fallThrough() {
            return new RecursionDetectedResult<>(null, true);
        }

        private RecursionDetectedResult(T t, boolean z) {
            this.value = t;
            this.fallThrough = z;
        }

        public T getValue() {
            return this.value;
        }

        public boolean isFallThrough() {
            return this.fallThrough;
        }

        public String toString() {
            return isFallThrough() ? "FALL_THROUGH" : String.valueOf(this.value);
        }
    }

    private static class LockBasedLazyValue<T> implements NullableLazyValue<T> {
        private final Function0<? extends T> computable;
        private final LockBasedStorageManager storageManager;
        private volatile Object value = NotValue.NOT_COMPUTED;

        protected void postCompute(T t) {
        }

        public LockBasedLazyValue(LockBasedStorageManager lockBasedStorageManager, Function0<? extends T> function0) {
            this.storageManager = lockBasedStorageManager;
            this.computable = function0;
        }

        public boolean isComputed() {
            return (this.value == NotValue.NOT_COMPUTED || this.value == NotValue.COMPUTING) ? false : true;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x003c A[Catch: all -> 0x0085, TryCatch #1 {all -> 0x0085, blocks: (B:7:0x0012, B:9:0x0018, B:12:0x0024, B:14:0x0028, B:16:0x0037, B:17:0x003c, B:19:0x0040, B:21:0x004b, B:22:0x0050, B:26:0x0061, B:28:0x0067, B:30:0x006d, B:31:0x0073, B:32:0x007d, B:33:0x007e, B:34:0x0084, B:23:0x0054), top: B:40:0x0012, inners: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0050 A[Catch: all -> 0x0085, TRY_LEAVE, TryCatch #1 {all -> 0x0085, blocks: (B:7:0x0012, B:9:0x0018, B:12:0x0024, B:14:0x0028, B:16:0x0037, B:17:0x003c, B:19:0x0040, B:21:0x004b, B:22:0x0050, B:26:0x0061, B:28:0x0067, B:30:0x006d, B:31:0x0073, B:32:0x007d, B:33:0x007e, B:34:0x0084, B:23:0x0054), top: B:40:0x0012, inners: #0 }] */
        @Override // kotlin.jvm.functions.Function0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public T invoke() {
            /*
                r3 = this;
                java.lang.Object r0 = r3.value
                boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.NotValue
                if (r1 != 0) goto Lb
                java.lang.Object r0 = kotlin.reflect.jvm.internal.impl.utils.WrappedValues.unescapeThrowable(r0)
                return r0
            Lb:
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager r0 = r3.storageManager
                java.util.concurrent.locks.Lock r0 = r0.lock
                r0.lock()
                java.lang.Object r0 = r3.value     // Catch: java.lang.Throwable -> L85
                boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.NotValue     // Catch: java.lang.Throwable -> L85
                if (r1 != 0) goto L24
                java.lang.Object r0 = kotlin.reflect.jvm.internal.impl.utils.WrappedValues.unescapeThrowable(r0)     // Catch: java.lang.Throwable -> L85
            L1c:
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager r1 = r3.storageManager
                java.util.concurrent.locks.Lock r1 = r1.lock
                r1.unlock()
                return r0
            L24:
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$NotValue r1 = kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.NotValue.COMPUTING     // Catch: java.lang.Throwable -> L85
                if (r0 != r1) goto L3c
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$NotValue r1 = kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.NotValue.RECURSION_WAS_DETECTED     // Catch: java.lang.Throwable -> L85
                r3.value = r1     // Catch: java.lang.Throwable -> L85
                r1 = 1
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$RecursionDetectedResult r1 = r3.recursionDetected(r1)     // Catch: java.lang.Throwable -> L85
                boolean r2 = r1.isFallThrough()     // Catch: java.lang.Throwable -> L85
                if (r2 != 0) goto L3c
                java.lang.Object r0 = r1.getValue()     // Catch: java.lang.Throwable -> L85
                goto L1c
            L3c:
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$NotValue r1 = kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.NotValue.RECURSION_WAS_DETECTED     // Catch: java.lang.Throwable -> L85
                if (r0 != r1) goto L50
                r0 = 0
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$RecursionDetectedResult r0 = r3.recursionDetected(r0)     // Catch: java.lang.Throwable -> L85
                boolean r1 = r0.isFallThrough()     // Catch: java.lang.Throwable -> L85
                if (r1 != 0) goto L50
                java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Throwable -> L85
                goto L1c
            L50:
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$NotValue r0 = kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.NotValue.COMPUTING     // Catch: java.lang.Throwable -> L85
                r3.value = r0     // Catch: java.lang.Throwable -> L85
                kotlin.jvm.functions.Function0<? extends T> r0 = r3.computable     // Catch: java.lang.Throwable -> L60
                java.lang.Object r0 = r0.invoke()     // Catch: java.lang.Throwable -> L60
                r3.value = r0     // Catch: java.lang.Throwable -> L60
                r3.postCompute(r0)     // Catch: java.lang.Throwable -> L60
                goto L1c
            L60:
                r0 = move-exception
                boolean r1 = kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt.isProcessCanceledException(r0)     // Catch: java.lang.Throwable -> L85
                if (r1 != 0) goto L7e
                java.lang.Object r1 = r3.value     // Catch: java.lang.Throwable -> L85
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$NotValue r2 = kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.NotValue.COMPUTING     // Catch: java.lang.Throwable -> L85
                if (r1 != r2) goto L73
                java.lang.Object r1 = kotlin.reflect.jvm.internal.impl.utils.WrappedValues.escapeThrowable(r0)     // Catch: java.lang.Throwable -> L85
                r3.value = r1     // Catch: java.lang.Throwable -> L85
            L73:
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager r1 = r3.storageManager     // Catch: java.lang.Throwable -> L85
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$ExceptionHandlingStrategy r1 = kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.access$100(r1)     // Catch: java.lang.Throwable -> L85
                java.lang.RuntimeException r0 = r1.handleException(r0)     // Catch: java.lang.Throwable -> L85
                throw r0     // Catch: java.lang.Throwable -> L85
            L7e:
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$NotValue r1 = kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.NotValue.NOT_COMPUTED     // Catch: java.lang.Throwable -> L85
                r3.value = r1     // Catch: java.lang.Throwable -> L85
                java.lang.RuntimeException r0 = (java.lang.RuntimeException) r0     // Catch: java.lang.Throwable -> L85
                throw r0     // Catch: java.lang.Throwable -> L85
            L85:
                r0 = move-exception
                kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager r1 = r3.storageManager
                java.util.concurrent.locks.Lock r1 = r1.lock
                r1.unlock()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue.invoke():java.lang.Object");
        }

        protected RecursionDetectedResult<T> recursionDetected(boolean z) {
            return this.storageManager.recursionDetectedDefault();
        }
    }

    private static class LockBasedNotNullLazyValue<T> extends LockBasedLazyValue<T> implements NotNullLazyValue<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        public LockBasedNotNullLazyValue(LockBasedStorageManager lockBasedStorageManager, Function0<? extends T> function0) {
            super(lockBasedStorageManager, function0);
        }

        @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.LockBasedLazyValue, kotlin.jvm.functions.Function0
        public T invoke() {
            return (T) super.invoke();
        }
    }

    private static class MapBasedMemoizedFunction<K, V> implements MemoizedFunctionToNullable<K, V> {
        private final ConcurrentMap<K, Object> cache;
        private final Function1<? super K, ? extends V> compute;
        private final LockBasedStorageManager storageManager;

        public MapBasedMemoizedFunction(LockBasedStorageManager lockBasedStorageManager, ConcurrentMap<K, Object> concurrentMap, Function1<? super K, ? extends V> function1) {
            this.storageManager = lockBasedStorageManager;
            this.cache = concurrentMap;
            this.compute = function1;
        }

        @Override // kotlin.jvm.functions.Function1
        public V invoke(K k) {
            Object obj = this.cache.get(k);
            if (obj != null && obj != NotValue.COMPUTING) {
                return (V) WrappedValues.unescapeExceptionOrNull(obj);
            }
            this.storageManager.lock.lock();
            try {
                Object obj2 = this.cache.get(k);
                if (obj2 == NotValue.COMPUTING) {
                    throw recursionDetected(k);
                }
                if (obj2 != null) {
                    return (V) WrappedValues.unescapeExceptionOrNull(obj2);
                }
                AssertionError assertionErrorRaceCondition = null;
                try {
                    this.cache.put(k, NotValue.COMPUTING);
                    V vInvoke = this.compute.invoke(k);
                    Object objPut = this.cache.put(k, WrappedValues.escapeNull(vInvoke));
                    if (objPut == NotValue.COMPUTING) {
                        return vInvoke;
                    }
                    assertionErrorRaceCondition = raceCondition(k, objPut);
                    throw assertionErrorRaceCondition;
                } catch (Throwable th) {
                    if (ExceptionUtilsKt.isProcessCanceledException(th)) {
                        this.cache.remove(k);
                        throw th;
                    }
                    if (th == assertionErrorRaceCondition) {
                        throw this.storageManager.exceptionHandlingStrategy.handleException(th);
                    }
                    Object objPut2 = this.cache.put(k, WrappedValues.escapeThrowable(th));
                    if (objPut2 == NotValue.COMPUTING) {
                        throw this.storageManager.exceptionHandlingStrategy.handleException(th);
                    }
                    throw raceCondition(k, objPut2);
                }
            } finally {
                this.storageManager.lock.unlock();
            }
        }

        private AssertionError recursionDetected(K k) {
            return (AssertionError) LockBasedStorageManager.sanitizeStackTrace(new AssertionError("Recursion detected on input: " + k + " under " + this.storageManager));
        }

        private AssertionError raceCondition(K k, Object obj) {
            return (AssertionError) LockBasedStorageManager.sanitizeStackTrace(new AssertionError("Race condition detected on input " + k + ". Old value is " + obj + " under " + this.storageManager));
        }

        protected LockBasedStorageManager getStorageManager() {
            return this.storageManager;
        }
    }

    private static class MapBasedMemoizedFunctionToNotNull<K, V> extends MapBasedMemoizedFunction<K, V> implements MemoizedFunctionToNotNull<K, V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        public MapBasedMemoizedFunctionToNotNull(LockBasedStorageManager lockBasedStorageManager, ConcurrentMap<K, Object> concurrentMap, Function1<? super K, ? extends V> function1) {
            super(lockBasedStorageManager, concurrentMap, function1);
        }

        @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.MapBasedMemoizedFunction, kotlin.jvm.functions.Function1
        public V invoke(K k) {
            return (V) super.invoke(k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T extends Throwable> T sanitizeStackTrace(T t) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            if (!stackTrace[i].getClassName().startsWith(PACKAGE_NAME)) {
                break;
            }
            i++;
        }
        List listSubList = Arrays.asList(stackTrace).subList(i, length);
        t.setStackTrace((StackTraceElement[]) listSubList.toArray(new StackTraceElement[listSubList.size()]));
        return t;
    }

    private static class CacheWithNullableValuesBasedOnMemoizedFunction<K, V> extends MapBasedMemoizedFunction<KeyWithComputation<K, V>, V> {
        private CacheWithNullableValuesBasedOnMemoizedFunction(LockBasedStorageManager lockBasedStorageManager, ConcurrentMap<KeyWithComputation<K, V>, Object> concurrentMap) {
            super(lockBasedStorageManager, concurrentMap, new Function1<KeyWithComputation<K, V>, V>() { // from class: kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.CacheWithNullableValuesBasedOnMemoizedFunction.1
                @Override // kotlin.jvm.functions.Function1
                public V invoke(KeyWithComputation<K, V> keyWithComputation) {
                    return (V) ((KeyWithComputation) keyWithComputation).computation.invoke();
                }
            });
        }

        public V computeIfAbsent(K k, Function0<? extends V> function0) {
            return invoke(new KeyWithComputation(k, function0));
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public <K, V> CacheWithNotNullValues<K, V> createCacheWithNotNullValues() {
        return new CacheWithNotNullValuesBasedOnMemoizedFunction(createConcurrentHashMap());
    }

    private static class CacheWithNotNullValuesBasedOnMemoizedFunction<K, V> extends CacheWithNullableValuesBasedOnMemoizedFunction<K, V> implements CacheWithNotNullValues<K, V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        private CacheWithNotNullValuesBasedOnMemoizedFunction(LockBasedStorageManager lockBasedStorageManager, ConcurrentMap<KeyWithComputation<K, V>, Object> concurrentMap) {
            super(concurrentMap);
        }

        @Override // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.CacheWithNullableValuesBasedOnMemoizedFunction, kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues
        public V computeIfAbsent(K k, Function0<? extends V> function0) {
            return (V) super.computeIfAbsent(k, function0);
        }
    }

    private static class KeyWithComputation<K, V> {
        private final Function0<? extends V> computation;
        private final K key;

        public KeyWithComputation(K k, Function0<? extends V> function0) {
            this.key = k;
            this.computation = function0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.key.equals(((KeyWithComputation) obj).key);
        }

        public int hashCode() {
            return this.key.hashCode();
        }
    }
}
