package com.google.common.collect;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import com.google.common.math.LongMath;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public final class Streams {

    public interface DoubleFunctionWithIndex<R> {
        R apply(double d, long j);
    }

    public interface FunctionWithIndex<T, R> {
        R apply(T t, long j);
    }

    public interface IntFunctionWithIndex<R> {
        R apply(int i, long j);
    }

    public interface LongFunctionWithIndex<R> {
        R apply(long j, long j2);
    }

    static /* synthetic */ Spliterator lambda$concat$0(Spliterator spliterator) {
        return spliterator;
    }

    static /* synthetic */ IntStream lambda$concat$2(IntStream intStream) {
        return intStream;
    }

    static /* synthetic */ LongStream lambda$concat$3(LongStream longStream) {
        return longStream;
    }

    static /* synthetic */ DoubleStream lambda$concat$4(DoubleStream doubleStream) {
        return doubleStream;
    }

    public static <T> Stream<T> stream(Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).stream();
        }
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    @Deprecated
    public static <T> Stream<T> stream(Collection<T> collection) {
        return collection.stream();
    }

    public static <T> Stream<T> stream(Iterator<T> it) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, 0), false);
    }

    public static <T> Stream<T> stream(Optional<T> optional) {
        return optional.isPresent() ? Stream.of(optional.get()) : Stream.of(new Object[0]);
    }

    public static <T> Stream<T> stream(java.util.Optional<T> optional) {
        return optional.isPresent() ? Stream.of(optional.get()) : Stream.of(new Object[0]);
    }

    public static IntStream stream(OptionalInt optionalInt) {
        return optionalInt.isPresent() ? IntStream.of(optionalInt.getAsInt()) : IntStream.empty();
    }

    public static LongStream stream(OptionalLong optionalLong) {
        return optionalLong.isPresent() ? LongStream.of(optionalLong.getAsLong()) : LongStream.empty();
    }

    public static DoubleStream stream(OptionalDouble optionalDouble) {
        return optionalDouble.isPresent() ? DoubleStream.of(optionalDouble.getAsDouble()) : DoubleStream.empty();
    }

    @SafeVarargs
    public static <T> Stream<T> concat(final Stream<? extends T>... streamArr) {
        ImmutableList.Builder builder = new ImmutableList.Builder(streamArr.length);
        long jSaturatedAdd = 0;
        boolean zIsParallel = false;
        int iCharacteristics = 336;
        for (Stream<? extends T> stream : streamArr) {
            zIsParallel |= stream.isParallel();
            Spliterator<? extends T> spliterator = stream.spliterator();
            builder.add((ImmutableList.Builder) spliterator);
            iCharacteristics &= spliterator.characteristics();
            jSaturatedAdd = LongMath.saturatedAdd(jSaturatedAdd, spliterator.estimateSize());
        }
        return (Stream) StreamSupport.stream(CollectSpliterators.flatMap(builder.build().spliterator(), new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$Qp_P4FaOwCMprLsr62jrkJ917uU
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Streams.lambda$concat$0((Spliterator) obj);
            }
        }, iCharacteristics, jSaturatedAdd), zIsParallel).onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$Streams$DgNK27oiP8haPn5auJ2ZT54eVf4
            @Override // java.lang.Runnable
            public final void run() {
                Streams.lambda$concat$1(streamArr);
            }
        });
    }

    static /* synthetic */ void lambda$concat$1(Stream[] streamArr) {
        for (Stream stream : streamArr) {
            stream.close();
        }
    }

    public static IntStream concat(IntStream... intStreamArr) {
        return Stream.of((Object[]) intStreamArr).flatMapToInt(new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$fKhJ8AO4z5N_HEvJr3BJeymkJEo
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Streams.lambda$concat$2((IntStream) obj);
            }
        });
    }

    public static LongStream concat(LongStream... longStreamArr) {
        return Stream.of((Object[]) longStreamArr).flatMapToLong(new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$NVrIq3MlYHe6g2j43ChnT1wuScg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Streams.lambda$concat$3((LongStream) obj);
            }
        });
    }

    public static DoubleStream concat(DoubleStream... doubleStreamArr) {
        return Stream.of((Object[]) doubleStreamArr).flatMapToDouble(new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$V0UOOLVvAzfh6Ubzu2VblhiH7lg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Streams.lambda$concat$4((DoubleStream) obj);
            }
        });
    }

    public static <A, B, R> Stream<R> zip(Stream<A> stream, Stream<B> stream2, final BiFunction<? super A, ? super B, R> biFunction) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(stream2);
        Preconditions.checkNotNull(biFunction);
        boolean z = stream.isParallel() || stream2.isParallel();
        Spliterator<A> spliterator = stream.spliterator();
        Spliterator<B> spliterator2 = stream2.spliterator();
        int iCharacteristics = spliterator.characteristics() & spliterator2.characteristics() & 80;
        final Iterator it = Spliterators.iterator(spliterator);
        final Iterator it2 = Spliterators.iterator(spliterator2);
        Stream stream3 = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Math.min(spliterator.estimateSize(), spliterator2.estimateSize()), iCharacteristics) { // from class: com.google.common.collect.Streams.1
            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super R> consumer) {
                if (!it.hasNext() || !it2.hasNext()) {
                    return false;
                }
                consumer.accept((Object) biFunction.apply(it.next(), it2.next()));
                return true;
            }
        }, z);
        stream.getClass();
        Stream stream4 = (Stream) stream3.onClose(new $$Lambda$HMLdHmOoZb2k4J7RPATd0ExEqQ(stream));
        stream2.getClass();
        return (Stream) stream4.onClose(new $$Lambda$HMLdHmOoZb2k4J7RPATd0ExEqQ(stream2));
    }

    public static <A, B> void forEachPair(Stream<A> stream, Stream<B> stream2, final BiConsumer<? super A, ? super B> biConsumer) {
        Preconditions.checkNotNull(biConsumer);
        if (stream.isParallel() || stream2.isParallel()) {
            zip(stream, stream2, new BiFunction() { // from class: com.google.common.collect.-$$Lambda$zchdiLpGn-9F8vety37cW5vt_8w
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return new Streams.TemporaryPair(obj, obj2);
                }
            }).forEach(new Consumer() { // from class: com.google.common.collect.-$$Lambda$Streams$CiY42bUjYqK5bfy48pZo8i0t5rE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Streams.TemporaryPair temporaryPair = (Streams.TemporaryPair) obj;
                    biConsumer.accept(temporaryPair.a, temporaryPair.b);
                }
            });
            return;
        }
        Iterator<A> it = stream.iterator();
        Iterator<B> it2 = stream2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            biConsumer.accept(it.next(), it2.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class TemporaryPair<A, B> {
        final A a;
        final B b;

        TemporaryPair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }

    public static <T, R> Stream<R> mapWithIndex(Stream<T> stream, final FunctionWithIndex<? super T, ? extends R> functionWithIndex) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(functionWithIndex);
        boolean zIsParallel = stream.isParallel();
        Spliterator<T> spliterator = stream.spliterator();
        if (!spliterator.hasCharacteristics(16384)) {
            final Iterator it = Spliterators.iterator(spliterator);
            Stream stream2 = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(spliterator.estimateSize(), spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.2
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    FunctionWithIndex functionWithIndex2 = functionWithIndex;
                    Object next = it.next();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.accept((Object) functionWithIndex2.apply(next, j));
                    return true;
                }
            }, zIsParallel);
            stream.getClass();
            return (Stream) stream2.onClose(new $$Lambda$HMLdHmOoZb2k4J7RPATd0ExEqQ(stream));
        }
        Stream stream3 = StreamSupport.stream(new C1Splitr(spliterator, 0L, functionWithIndex), zIsParallel);
        stream.getClass();
        return (Stream) stream3.onClose(new $$Lambda$HMLdHmOoZb2k4J7RPATd0ExEqQ(stream));
    }

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* renamed from: com.google.common.collect.Streams$1Splitr, reason: invalid class name */
    class C1Splitr<R, T> extends MapWithIndexSpliterator<Spliterator<T>, R, C1Splitr> implements Consumer<T> {

        @NullableDecl
        T holder;
        final /* synthetic */ FunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$FunctionWithIndex), (r0 I:com.google.common.collect.Streams$1Splitr) (LINE:378) com.google.common.collect.Streams.1Splitr.val$function com.google.common.collect.Streams$FunctionWithIndex, block:B:2:0x0000 */
        C1Splitr(Spliterator spliterator, Spliterator<T> spliterator2, long j) {
            FunctionWithIndex functionWithIndex;
            super(spliterator, spliterator2);
            this.val$function = functionWithIndex;
        }

        @Override // java.util.function.Consumer
        public void accept(@NullableDecl T t) {
            this.holder = t;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!this.fromSpliterator.tryAdvance(this)) {
                return false;
            }
            try {
                FunctionWithIndex functionWithIndex = this.val$function;
                T t = this.holder;
                long j = this.index;
                this.index = 1 + j;
                consumer.accept((Object) functionWithIndex.apply(t, j));
                return true;
            } finally {
                this.holder = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C1Splitr createSplit(Spliterator<T> spliterator, long j) {
            return new C1Splitr(spliterator, j, this.val$function);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Spliterator$OfInt] */
    public static <R> Stream<R> mapWithIndex(final IntStream intStream, final IntFunctionWithIndex<R> intFunctionWithIndex) {
        Preconditions.checkNotNull(intStream);
        Preconditions.checkNotNull(intFunctionWithIndex);
        boolean zIsParallel = intStream.isParallel();
        ?? Spliterator = intStream.spliterator();
        if (!Spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfInt it = Spliterators.iterator((Spliterator.OfInt) Spliterator);
            Stream stream = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Spliterator.estimateSize(), Spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.3
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    IntFunctionWithIndex intFunctionWithIndex2 = intFunctionWithIndex;
                    int iNextInt = it.nextInt();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.accept((Object) intFunctionWithIndex2.apply(iNextInt, j));
                    return true;
                }
            }, zIsParallel);
            intStream.getClass();
            return (Stream) stream.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$9a7QvnacX-q--xn8JNzXhpgqPwI
                @Override // java.lang.Runnable
                public final void run() {
                    intStream.close();
                }
            });
        }
        Stream stream2 = StreamSupport.stream(new C2Splitr(Spliterator, 0L, intFunctionWithIndex), zIsParallel);
        intStream.getClass();
        return (Stream) stream2.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$9a7QvnacX-q--xn8JNzXhpgqPwI
            @Override // java.lang.Runnable
            public final void run() {
                intStream.close();
            }
        });
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* renamed from: com.google.common.collect.Streams$2Splitr, reason: invalid class name */
    class C2Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfInt, R, C2Splitr> implements IntConsumer, Spliterator<R> {
        int holder;
        final /* synthetic */ IntFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$IntFunctionWithIndex), (r0 I:com.google.common.collect.Streams$2Splitr) (LINE:460) com.google.common.collect.Streams.2Splitr.val$function com.google.common.collect.Streams$IntFunctionWithIndex, block:B:2:0x0000 */
        C2Splitr(Spliterator.OfInt ofInt, Spliterator.OfInt ofInt2, long j) {
            IntFunctionWithIndex intFunctionWithIndex;
            super(ofInt, ofInt2);
            this.val$function = intFunctionWithIndex;
        }

        @Override // java.util.function.IntConsumer
        public void accept(int i) {
            this.holder = i;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!((Spliterator.OfInt) this.fromSpliterator).tryAdvance((IntConsumer) this)) {
                return false;
            }
            IntFunctionWithIndex intFunctionWithIndex = this.val$function;
            int i = this.holder;
            long j = this.index;
            this.index = 1 + j;
            consumer.accept((Object) intFunctionWithIndex.apply(i, j));
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C2Splitr createSplit(Spliterator.OfInt ofInt, long j) {
            return new C2Splitr(ofInt, j, this.val$function);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Spliterator$OfLong] */
    public static <R> Stream<R> mapWithIndex(final LongStream longStream, final LongFunctionWithIndex<R> longFunctionWithIndex) {
        Preconditions.checkNotNull(longStream);
        Preconditions.checkNotNull(longFunctionWithIndex);
        boolean zIsParallel = longStream.isParallel();
        ?? Spliterator = longStream.spliterator();
        if (!Spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfLong it = Spliterators.iterator((Spliterator.OfLong) Spliterator);
            Stream stream = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Spliterator.estimateSize(), Spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.4
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    LongFunctionWithIndex longFunctionWithIndex2 = longFunctionWithIndex;
                    long jNextLong = it.nextLong();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.accept((Object) longFunctionWithIndex2.apply(jNextLong, j));
                    return true;
                }
            }, zIsParallel);
            longStream.getClass();
            return (Stream) stream.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$6Fmi0v9SoDNUguh_UBRiAKfmc7E
                @Override // java.lang.Runnable
                public final void run() {
                    longStream.close();
                }
            });
        }
        Stream stream2 = StreamSupport.stream(new C3Splitr(Spliterator, 0L, longFunctionWithIndex), zIsParallel);
        longStream.getClass();
        return (Stream) stream2.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$6Fmi0v9SoDNUguh_UBRiAKfmc7E
            @Override // java.lang.Runnable
            public final void run() {
                longStream.close();
            }
        });
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* renamed from: com.google.common.collect.Streams$3Splitr, reason: invalid class name */
    class C3Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfLong, R, C3Splitr> implements LongConsumer, Spliterator<R> {
        long holder;
        final /* synthetic */ LongFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$LongFunctionWithIndex), (r0 I:com.google.common.collect.Streams$3Splitr) (LINE:538) com.google.common.collect.Streams.3Splitr.val$function com.google.common.collect.Streams$LongFunctionWithIndex, block:B:2:0x0000 */
        C3Splitr(Spliterator.OfLong ofLong, Spliterator.OfLong ofLong2, long j) {
            LongFunctionWithIndex longFunctionWithIndex;
            super(ofLong, ofLong2);
            this.val$function = longFunctionWithIndex;
        }

        @Override // java.util.function.LongConsumer
        public void accept(long j) {
            this.holder = j;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!((Spliterator.OfLong) this.fromSpliterator).tryAdvance((LongConsumer) this)) {
                return false;
            }
            LongFunctionWithIndex longFunctionWithIndex = this.val$function;
            long j = this.holder;
            long j2 = this.index;
            this.index = 1 + j2;
            consumer.accept((Object) longFunctionWithIndex.apply(j, j2));
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C3Splitr createSplit(Spliterator.OfLong ofLong, long j) {
            return new C3Splitr(ofLong, j, this.val$function);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Spliterator$OfDouble] */
    public static <R> Stream<R> mapWithIndex(final DoubleStream doubleStream, final DoubleFunctionWithIndex<R> doubleFunctionWithIndex) {
        Preconditions.checkNotNull(doubleStream);
        Preconditions.checkNotNull(doubleFunctionWithIndex);
        boolean zIsParallel = doubleStream.isParallel();
        ?? Spliterator = doubleStream.spliterator();
        if (!Spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfDouble it = Spliterators.iterator((Spliterator.OfDouble) Spliterator);
            Stream stream = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Spliterator.estimateSize(), Spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.5
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    DoubleFunctionWithIndex doubleFunctionWithIndex2 = doubleFunctionWithIndex;
                    double dNextDouble = it.nextDouble();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.accept((Object) doubleFunctionWithIndex2.apply(dNextDouble, j));
                    return true;
                }
            }, zIsParallel);
            doubleStream.getClass();
            return (Stream) stream.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$fpN4I0QQKnqoRTVgHTJgOxGrirQ
                @Override // java.lang.Runnable
                public final void run() {
                    doubleStream.close();
                }
            });
        }
        Stream stream2 = StreamSupport.stream(new C4Splitr(Spliterator, 0L, doubleFunctionWithIndex), zIsParallel);
        doubleStream.getClass();
        return (Stream) stream2.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$fpN4I0QQKnqoRTVgHTJgOxGrirQ
            @Override // java.lang.Runnable
            public final void run() {
                doubleStream.close();
            }
        });
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* renamed from: com.google.common.collect.Streams$4Splitr, reason: invalid class name */
    class C4Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfDouble, R, C4Splitr> implements DoubleConsumer, Spliterator<R> {
        double holder;
        final /* synthetic */ DoubleFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$DoubleFunctionWithIndex), (r0 I:com.google.common.collect.Streams$4Splitr) (LINE:617) com.google.common.collect.Streams.4Splitr.val$function com.google.common.collect.Streams$DoubleFunctionWithIndex, block:B:2:0x0000 */
        C4Splitr(Spliterator.OfDouble ofDouble, Spliterator.OfDouble ofDouble2, long j) {
            DoubleFunctionWithIndex doubleFunctionWithIndex;
            super(ofDouble, ofDouble2);
            this.val$function = doubleFunctionWithIndex;
        }

        @Override // java.util.function.DoubleConsumer
        public void accept(double d) {
            this.holder = d;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!((Spliterator.OfDouble) this.fromSpliterator).tryAdvance((DoubleConsumer) this)) {
                return false;
            }
            DoubleFunctionWithIndex doubleFunctionWithIndex = this.val$function;
            double d = this.holder;
            long j = this.index;
            this.index = 1 + j;
            consumer.accept((Object) doubleFunctionWithIndex.apply(d, j));
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C4Splitr createSplit(Spliterator.OfDouble ofDouble, long j) {
            return new C4Splitr(ofDouble, j, this.val$function);
        }
    }

    private static abstract class MapWithIndexSpliterator<F extends Spliterator<?>, R, S extends MapWithIndexSpliterator<F, R, S>> implements Spliterator<R> {
        final F fromSpliterator;
        long index;

        abstract S createSplit(F f, long j);

        MapWithIndexSpliterator(F f, long j) {
            this.fromSpliterator = f;
            this.index = j;
        }

        @Override // java.util.Spliterator
        public S trySplit() {
            Spliterator spliteratorTrySplit = this.fromSpliterator.trySplit();
            if (spliteratorTrySplit == null) {
                return null;
            }
            S s = (S) createSplit(spliteratorTrySplit, this.index);
            this.index += spliteratorTrySplit.getExactSizeIfKnown();
            return s;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.fromSpliterator.estimateSize();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.fromSpliterator.characteristics() & 16464;
        }
    }

    /* renamed from: com.google.common.collect.Streams$1OptionalState, reason: invalid class name */
    class C1OptionalState {
        boolean set = false;
        T value = null;

        C1OptionalState() {
        }

        void set(@NullableDecl T t) {
            this.set = true;
            this.value = t;
        }

        T get() {
            Preconditions.checkState(this.set);
            return this.value;
        }
    }

    public static <T> java.util.Optional<T> findLast(Stream<T> stream) {
        final C1OptionalState c1OptionalState = new C1OptionalState();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.addLast(stream.spliterator());
        while (!arrayDeque.isEmpty()) {
            Spliterator<T> spliterator = (Spliterator) arrayDeque.removeLast();
            if (spliterator.getExactSizeIfKnown() != 0) {
                if (spliterator.hasCharacteristics(16384)) {
                    while (true) {
                        Spliterator<T> spliteratorTrySplit = spliterator.trySplit();
                        if (spliteratorTrySplit == null || spliteratorTrySplit.getExactSizeIfKnown() == 0) {
                            break;
                        }
                        if (spliterator.getExactSizeIfKnown() == 0) {
                            spliterator = spliteratorTrySplit;
                            break;
                        }
                    }
                    spliterator.forEachRemaining(new Consumer() { // from class: com.google.common.collect.-$$Lambda$hlWN96BsfAOTz2csXahfgErSoOE
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            c1OptionalState.set(obj);
                        }
                    });
                    return java.util.Optional.of(c1OptionalState.get());
                }
                Spliterator<T> spliteratorTrySplit2 = spliterator.trySplit();
                if (spliteratorTrySplit2 == null || spliteratorTrySplit2.getExactSizeIfKnown() == 0) {
                    spliterator.forEachRemaining(new Consumer() { // from class: com.google.common.collect.-$$Lambda$hlWN96BsfAOTz2csXahfgErSoOE
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            c1OptionalState.set(obj);
                        }
                    });
                    if (c1OptionalState.set) {
                        return java.util.Optional.of(c1OptionalState.get());
                    }
                } else {
                    arrayDeque.addLast(spliteratorTrySplit2);
                    arrayDeque.addLast(spliterator);
                }
            }
        }
        return java.util.Optional.empty();
    }

    public static OptionalInt findLast(IntStream intStream) {
        java.util.Optional optionalFindLast = findLast(intStream.boxed());
        return optionalFindLast.isPresent() ? OptionalInt.of(((Integer) optionalFindLast.get()).intValue()) : OptionalInt.empty();
    }

    public static OptionalLong findLast(LongStream longStream) {
        java.util.Optional optionalFindLast = findLast(longStream.boxed());
        return optionalFindLast.isPresent() ? OptionalLong.of(((Long) optionalFindLast.get()).longValue()) : OptionalLong.empty();
    }

    public static OptionalDouble findLast(DoubleStream doubleStream) {
        java.util.Optional optionalFindLast = findLast(doubleStream.boxed());
        return optionalFindLast.isPresent() ? OptionalDouble.of(((Double) optionalFindLast.get()).doubleValue()) : OptionalDouble.empty();
    }

    private Streams() {
    }
}
