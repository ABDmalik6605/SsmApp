package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* loaded from: classes2.dex */
final class CollectCollectors {
    private static final Collector<Object, ?, ImmutableList<Object>> TO_IMMUTABLE_LIST = Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$vf0n71JGqeu6B0SDNCv1WN6jcME
        @Override // java.util.function.Supplier
        public final Object get() {
            return ImmutableList.builder();
        }
    }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$UHf1W1ReHb_v0IbjrqXYRL0KLCE
        @Override // java.util.function.BiConsumer
        public final void accept(Object obj, Object obj2) {
            ((ImmutableList.Builder) obj).add((ImmutableList.Builder) obj2);
        }
    }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$IA9uBkOc6EPE5epV9aHgwlAktLg
        @Override // java.util.function.BiFunction
        public final Object apply(Object obj, Object obj2) {
            return ((ImmutableList.Builder) obj).combine((ImmutableList.Builder) obj2);
        }
    }, new Function() { // from class: com.google.common.collect.-$$Lambda$9T1NmVGx35xIbLLL0FmZQ13Zths
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((ImmutableList.Builder) obj).build();
        }
    }, new Collector.Characteristics[0]);
    private static final Collector<Object, ?, ImmutableSet<Object>> TO_IMMUTABLE_SET = Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$X_ReiLzMMdmLOHBMWZV1MhM0VVw
        @Override // java.util.function.Supplier
        public final Object get() {
            return ImmutableSet.builder();
        }
    }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$wXOTOw1G8z0PqIyYAYDEJ1yAxuU
        @Override // java.util.function.BiConsumer
        public final void accept(Object obj, Object obj2) {
            ((ImmutableSet.Builder) obj).add((ImmutableSet.Builder) obj2);
        }
    }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$i1r6VPXBf3X5kIJHWXzorrLMWbA
        @Override // java.util.function.BiFunction
        public final Object apply(Object obj, Object obj2) {
            return ((ImmutableSet.Builder) obj).combine((ImmutableSet.Builder) obj2);
        }
    }, new Function() { // from class: com.google.common.collect.-$$Lambda$PMSANYw-IJwk_7wrNWRzjOn3p-k
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((ImmutableSet.Builder) obj).build();
        }
    }, new Collector.Characteristics[0]);
    private static final Collector<Range<Comparable>, ?, ImmutableRangeSet<Comparable>> TO_IMMUTABLE_RANGE_SET = Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$U16N-8PwOz4fo5c-Vol8OK3SUx0
        @Override // java.util.function.Supplier
        public final Object get() {
            return ImmutableRangeSet.builder();
        }
    }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$cXxboVgO8LkekbP9-sbgiPoMjN0
        @Override // java.util.function.BiConsumer
        public final void accept(Object obj, Object obj2) {
            ((ImmutableRangeSet.Builder) obj).add((Range) obj2);
        }
    }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$zgQbEdPgU4dKXB2xhMNf4GcbfQk
        @Override // java.util.function.BiFunction
        public final Object apply(Object obj, Object obj2) {
            return ((ImmutableRangeSet.Builder) obj).combine((ImmutableRangeSet.Builder) obj2);
        }
    }, new Function() { // from class: com.google.common.collect.-$$Lambda$BtlUjlQmAMbKj3ap3livgXAmCTo
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((ImmutableRangeSet.Builder) obj).build();
        }
    }, new Collector.Characteristics[0]);

    CollectCollectors() {
    }

    static <T, K, V> Collector<T, ?, ImmutableBiMap<K, V>> toImmutableBiMap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$5ehNMvpQfc2Cm4DPJ8vk2BwhOrI
            @Override // java.util.function.Supplier
            public final Object get() {
                return new ImmutableBiMap.Builder();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$uaEZgJ4jFDE33UsbAHFKo6uOEV4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableBiMap.Builder) obj).put((ImmutableBiMap.Builder) function.apply(obj2), (ImmutableBiMap.Builder) function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$blaAtO--XD8VAXMTziU8JWiW0Ww
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableBiMap.Builder) obj).combine((ImmutableMap.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$O3_vJQH5SFtXbmwrHNSgKeBErL0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableBiMap.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }

    static <E> Collector<E, ?, ImmutableList<E>> toImmutableList() {
        return (Collector<E, ?, ImmutableList<E>>) TO_IMMUTABLE_LIST;
    }

    static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$FosWIl5LUev4J5RG9VOwYH1gS90
            @Override // java.util.function.Supplier
            public final Object get() {
                return new ImmutableMap.Builder();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$sIQ97mDJW8gUFx83ZQtt_vTfg7I
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableMap.Builder) obj).put(function.apply(obj2), function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$729PE7C19CRv98lm3wiTh0tAlBI
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableMap.Builder) obj).combine((ImmutableMap.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$Wg4kyVeuwZkqkLuDX0M7XTHh0i4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableMap.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }

    static <E> Collector<E, ?, ImmutableSet<E>> toImmutableSet() {
        return (Collector<E, ?, ImmutableSet<E>>) TO_IMMUTABLE_SET;
    }

    static <T, K, V> Collector<T, ?, ImmutableSortedMap<K, V>> toImmutableSortedMap(final Comparator<? super K> comparator, final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$aPZkIT_WF-qO0GlSlsqyd3UFVoc
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.lambda$toImmutableSortedMap$2(comparator);
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$0ezZhwUF5GAJUKhyPqctFXc3CAY
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableSortedMap.Builder) obj).put((ImmutableSortedMap.Builder) function.apply(obj2), (ImmutableSortedMap.Builder) function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$rHhq7tzgnNcBGZtwL3eEI7SHbww
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableSortedMap.Builder) obj).combine((ImmutableMap.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$7wvXIDTYLRn2hXiaQ5jXCq33QvA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableSortedMap.Builder) obj).build();
            }
        }, Collector.Characteristics.UNORDERED);
    }

    static /* synthetic */ ImmutableSortedMap.Builder lambda$toImmutableSortedMap$2(Comparator comparator) {
        return new ImmutableSortedMap.Builder(comparator);
    }

    static <E> Collector<E, ?, ImmutableSortedSet<E>> toImmutableSortedSet(final Comparator<? super E> comparator) {
        Preconditions.checkNotNull(comparator);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$xiNzaC9RV1RgR6D3xh9xI40myA8
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.lambda$toImmutableSortedSet$4(comparator);
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$JkiWm6aCrF3eWsF0etVTYLE_vQs
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableSortedSet.Builder) obj).add((ImmutableSortedSet.Builder) obj2);
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$zHMeuF_XiSzpwRphisN7v_9L0X4
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableSortedSet.Builder) obj).combine((ImmutableSet.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$hOTz7keUQs40Nn8saKqiU19QapI
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableSortedSet.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }

    static /* synthetic */ ImmutableSortedSet.Builder lambda$toImmutableSortedSet$4(Comparator comparator) {
        return new ImmutableSortedSet.Builder(comparator);
    }

    static <E extends Comparable<? super E>> Collector<Range<E>, ?, ImmutableRangeSet<E>> toImmutableRangeSet() {
        return (Collector<Range<E>, ?, ImmutableRangeSet<E>>) TO_IMMUTABLE_RANGE_SET;
    }

    static <T, K extends Comparable<? super K>, V> Collector<T, ?, ImmutableRangeMap<K, V>> toImmutableRangeMap(final Function<? super T, Range<K>> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$lR0VKAIjMKe54owYO6FQTIS_CNE
            @Override // java.util.function.Supplier
            public final Object get() {
                return ImmutableRangeMap.builder();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$0eOFHo4mR6Pq7dltCcLDZ9FoybY
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableRangeMap.Builder) obj).put((Range) function.apply(obj2), function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$t9hZLo9d1Rib41DnFRVYzD05ZdA
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableRangeMap.Builder) obj).combine((ImmutableRangeMap.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$bEmagIn8JeVnMBYk_G6hyaz-MHQ
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableRangeMap.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }
}
