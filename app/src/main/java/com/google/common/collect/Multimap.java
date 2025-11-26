package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public interface Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    void clear();

    boolean containsEntry(@NullableDecl Object obj, @NullableDecl Object obj2);

    boolean containsKey(@NullableDecl Object obj);

    boolean containsValue(@NullableDecl Object obj);

    Collection<Map.Entry<K, V>> entries();

    boolean equals(@NullableDecl Object obj);

    void forEach(BiConsumer<? super K, ? super V> biConsumer);

    Collection<V> get(@NullableDecl K k);

    int hashCode();

    boolean isEmpty();

    Set<K> keySet();

    Multiset<K> keys();

    boolean put(@NullableDecl K k, @NullableDecl V v);

    boolean putAll(Multimap<? extends K, ? extends V> multimap);

    boolean putAll(@NullableDecl K k, Iterable<? extends V> iterable);

    boolean remove(@NullableDecl Object obj, @NullableDecl Object obj2);

    Collection<V> removeAll(@NullableDecl Object obj);

    Collection<V> replaceValues(@NullableDecl K k, Iterable<? extends V> iterable);

    int size();

    Collection<V> values();

    /* renamed from: com.google.common.collect.Multimap$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$forEach(Multimap _this, final BiConsumer biConsumer) {
            Preconditions.checkNotNull(biConsumer);
            _this.entries().forEach(new Consumer() { // from class: com.google.common.collect.-$$Lambda$Multimap$7QL5swi9g_G6pz8RA22gO3HwB6o
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Map.Entry entry = (Map.Entry) obj;
                    biConsumer.accept(entry.getKey(), entry.getValue());
                }
            });
        }
    }
}
