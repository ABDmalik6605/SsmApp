package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import kotlin.jvm.internal.LongCompanionObject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class CollectSpliterators {
    private CollectSpliterators() {
    }

    static <T> Spliterator<T> indexed(int i, int i2, IntFunction<T> intFunction) {
        return indexed(i, i2, intFunction, null);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Spliterator$OfInt] */
    static <T> Spliterator<T> indexed(int i, int i2, IntFunction<T> intFunction, Comparator<? super T> comparator) {
        if (comparator != null) {
            Preconditions.checkArgument((i2 & 4) != 0);
        }
        return new C1WithCharacteristics(IntStream.range(0, i).spliterator(), intFunction, i2, comparator);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.collect.CollectSpliterators$1WithCharacteristics, reason: invalid class name */
    class C1WithCharacteristics<T> implements Spliterator<T> {
        private final Spliterator.OfInt delegate;
        final /* synthetic */ Comparator val$comparator;
        final /* synthetic */ int val$extraCharacteristics;
        final /* synthetic */ IntFunction val$function;

        C1WithCharacteristics(Spliterator.OfInt ofInt, IntFunction intFunction, int i, Comparator comparator) {
            this.val$function = intFunction;
            this.val$extraCharacteristics = i;
            this.val$comparator = comparator;
            this.delegate = ofInt;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(final Consumer<? super T> consumer) {
            Spliterator.OfInt ofInt = this.delegate;
            final IntFunction intFunction = this.val$function;
            return ofInt.tryAdvance(new IntConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$1WithCharacteristics$PEvhBd0-3FrqDK24rMerJr0qQY0
                @Override // java.util.function.IntConsumer
                public final void accept(int i) {
                    consumer.accept(intFunction.apply(i));
                }
            });
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(final Consumer<? super T> consumer) {
            Spliterator.OfInt ofInt = this.delegate;
            final IntFunction intFunction = this.val$function;
            ofInt.forEachRemaining(new IntConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$1WithCharacteristics$haONaxvHdHnMhHDhe4el9FWJhDQ
                @Override // java.util.function.IntConsumer
                public final void accept(int i) {
                    consumer.accept(intFunction.apply(i));
                }
            });
        }

        @Override // java.util.Spliterator
        @NullableDecl
        public Spliterator<T> trySplit() {
            Spliterator.OfInt ofIntTrySplit = this.delegate.trySplit();
            if (ofIntTrySplit == null) {
                return null;
            }
            return new C1WithCharacteristics(ofIntTrySplit, this.val$function, this.val$extraCharacteristics, this.val$comparator);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.delegate.estimateSize();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.val$extraCharacteristics | 16464;
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            if (hasCharacteristics(4)) {
                return this.val$comparator;
            }
            throw new IllegalStateException();
        }
    }

    static <F, T> Spliterator<T> map(Spliterator<F> spliterator, Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(spliterator);
        Preconditions.checkNotNull(function);
        return new AnonymousClass1(spliterator, function);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.collect.CollectSpliterators$1, reason: invalid class name */
    static class AnonymousClass1<T> implements Spliterator<T> {
        final /* synthetic */ Spliterator val$fromSpliterator;
        final /* synthetic */ Function val$function;

        AnonymousClass1(Spliterator spliterator, Function function) {
            this.val$fromSpliterator = spliterator;
            this.val$function = function;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(final Consumer<? super T> consumer) {
            Spliterator spliterator = this.val$fromSpliterator;
            final Function function = this.val$function;
            return spliterator.tryAdvance(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$1$eqZGdw-9p-TghD1nN4nxb9Orf6s
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    consumer.accept(function.apply(obj));
                }
            });
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(final Consumer<? super T> consumer) {
            Spliterator spliterator = this.val$fromSpliterator;
            final Function function = this.val$function;
            spliterator.forEachRemaining(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$1$29eJ-8eqOuezCUOm3lykrvU05TE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    consumer.accept(function.apply(obj));
                }
            });
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator<T> spliteratorTrySplit = this.val$fromSpliterator.trySplit();
            if (spliteratorTrySplit != null) {
                return CollectSpliterators.map(spliteratorTrySplit, this.val$function);
            }
            return null;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.val$fromSpliterator.estimateSize();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.val$fromSpliterator.characteristics() & (-262);
        }
    }

    static <T> Spliterator<T> filter(Spliterator<T> spliterator, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(spliterator);
        Preconditions.checkNotNull(predicate);
        return new C1Splitr(spliterator, predicate);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.collect.CollectSpliterators$1Splitr, reason: invalid class name */
    class C1Splitr<T> implements Spliterator<T>, Consumer<T> {
        T holder = null;
        final /* synthetic */ Spliterator val$fromSpliterator;
        final /* synthetic */ Predicate val$predicate;

        C1Splitr(Spliterator spliterator, Predicate predicate) {
            this.val$fromSpliterator = spliterator;
            this.val$predicate = predicate;
        }

        @Override // java.util.function.Consumer
        public void accept(T t) {
            this.holder = t;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            while (this.val$fromSpliterator.tryAdvance(this)) {
                try {
                    if (this.val$predicate.test(this.holder)) {
                        consumer.accept(this.holder);
                        return true;
                    }
                } finally {
                    this.holder = null;
                }
            }
            return false;
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator<T> spliteratorTrySplit = this.val$fromSpliterator.trySplit();
            if (spliteratorTrySplit == null) {
                return null;
            }
            return CollectSpliterators.filter(spliteratorTrySplit, this.val$predicate);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.val$fromSpliterator.estimateSize() / 2;
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            return this.val$fromSpliterator.getComparator();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.val$fromSpliterator.characteristics() & 277;
        }
    }

    static <F, T> Spliterator<T> flatMap(Spliterator<F> spliterator, Function<? super F, Spliterator<T>> function, int i, long j) {
        Preconditions.checkArgument((i & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((i & 4) == 0, "flatMap does not support SORTED characteristic");
        Preconditions.checkNotNull(spliterator);
        Preconditions.checkNotNull(function);
        return new C1FlatMapSpliterator(null, spliterator, i, j, function);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.collect.CollectSpliterators$1FlatMapSpliterator, reason: invalid class name */
    class C1FlatMapSpliterator<T> implements Spliterator<T> {
        int characteristics;
        long estimatedSize;
        final Spliterator<F> from;

        @NullableDecl
        Spliterator<T> prefix;
        final /* synthetic */ Function val$function;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 6, insn: 0x0000: IPUT (r6 I:java.util.function.Function), (r0 I:com.google.common.collect.CollectSpliterators$1FlatMapSpliterator) (LINE:218) com.google.common.collect.CollectSpliterators.1FlatMapSpliterator.val$function java.util.function.Function, block:B:2:0x0000 */
        C1FlatMapSpliterator(Spliterator spliterator, Spliterator<T> spliterator2, Spliterator<F> spliterator3, int i, long j) {
            Function function;
            this.val$function = function;
            this.prefix = spliterator;
            this.from = spliterator2;
            this.characteristics = spliterator3;
            this.estimatedSize = i;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            Spliterator<F> spliterator;
            final Function function;
            do {
                Spliterator<T> spliterator2 = this.prefix;
                if (spliterator2 != null && spliterator2.tryAdvance(consumer)) {
                    long j = this.estimatedSize;
                    if (j == LongCompanionObject.MAX_VALUE) {
                        return true;
                    }
                    this.estimatedSize = j - 1;
                    return true;
                }
                this.prefix = null;
                spliterator = this.from;
                function = this.val$function;
            } while (spliterator.tryAdvance(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$1FlatMapSpliterator$30RzUs7cgs6aZ0EfiBg5HnZRHKI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$tryAdvance$0$CollectSpliterators$1FlatMapSpliterator(function, obj);
                }
            }));
            return false;
        }

        public /* synthetic */ void lambda$tryAdvance$0$CollectSpliterators$1FlatMapSpliterator(Function function, Object obj) {
            this.prefix = (Spliterator) function.apply(obj);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(final Consumer<? super T> consumer) {
            Spliterator<T> spliterator = this.prefix;
            if (spliterator != null) {
                spliterator.forEachRemaining(consumer);
                this.prefix = null;
            }
            Spliterator<F> spliterator2 = this.from;
            final Function function = this.val$function;
            spliterator2.forEachRemaining(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$1FlatMapSpliterator$cqUMr-vZNWDWVwB6qnt-pn3rREI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Spliterator) function.apply(obj)).forEachRemaining(consumer);
                }
            });
            this.estimatedSize = 0L;
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator spliteratorTrySplit = this.from.trySplit();
            if (spliteratorTrySplit != null) {
                int i = this.characteristics & (-65);
                long jEstimateSize = estimateSize();
                if (jEstimateSize < LongCompanionObject.MAX_VALUE) {
                    jEstimateSize /= 2;
                    this.estimatedSize -= jEstimateSize;
                    this.characteristics = i;
                }
                C1FlatMapSpliterator c1FlatMapSpliterator = new C1FlatMapSpliterator(this.prefix, spliteratorTrySplit, i, jEstimateSize, this.val$function);
                this.prefix = null;
                return c1FlatMapSpliterator;
            }
            Spliterator<T> spliterator = this.prefix;
            if (spliterator == null) {
                return null;
            }
            this.prefix = null;
            return spliterator;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            Spliterator<T> spliterator = this.prefix;
            if (spliterator != null) {
                this.estimatedSize = Math.max(this.estimatedSize, spliterator.estimateSize());
            }
            return Math.max(this.estimatedSize, 0L);
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }
    }
}
