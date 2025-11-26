package com.google.common.collect;

import com.google.common.collect.ImmutableMapEntrySet;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class JdkBackedImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    private final Map<V, K> backwardDelegate;
    private final transient ImmutableList<Map.Entry<K, V>> entries;
    private final Map<K, V> forwardDelegate;

    @LazyInit
    private transient JdkBackedImmutableBiMap<V, K> inverse;

    @Override // com.google.common.collect.ImmutableMap
    boolean isPartialView() {
        return false;
    }

    static <K, V> ImmutableBiMap<K, V> create(int i, Map.Entry<K, V>[] entryArr) {
        HashMap mapNewHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(i);
        HashMap mapNewHashMapWithExpectedSize2 = Maps.newHashMapWithExpectedSize(i);
        for (int i2 = 0; i2 < i; i2++) {
            ImmutableMapEntry immutableMapEntryMakeImmutable = RegularImmutableMap.makeImmutable(entryArr[i2]);
            entryArr[i2] = immutableMapEntryMakeImmutable;
            Object objPutIfAbsent = mapNewHashMapWithExpectedSize.putIfAbsent(immutableMapEntryMakeImmutable.getKey(), immutableMapEntryMakeImmutable.getValue());
            if (objPutIfAbsent != null) {
                throw conflictException("key", immutableMapEntryMakeImmutable.getKey() + "=" + objPutIfAbsent, entryArr[i2]);
            }
            Object objPutIfAbsent2 = mapNewHashMapWithExpectedSize2.putIfAbsent(immutableMapEntryMakeImmutable.getValue(), immutableMapEntryMakeImmutable.getKey());
            if (objPutIfAbsent2 != null) {
                throw conflictException("value", objPutIfAbsent2 + "=" + immutableMapEntryMakeImmutable.getValue(), entryArr[i2]);
            }
        }
        return new JdkBackedImmutableBiMap(ImmutableList.asImmutableList(entryArr, i), mapNewHashMapWithExpectedSize, mapNewHashMapWithExpectedSize2);
    }

    private JdkBackedImmutableBiMap(ImmutableList<Map.Entry<K, V>> immutableList, Map<K, V> map, Map<V, K> map2) {
        this.entries = immutableList;
        this.forwardDelegate = map;
        this.backwardDelegate = map2;
    }

    @Override // java.util.Map
    public int size() {
        return this.entries.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableBiMap, com.google.common.collect.BiMap
    public ImmutableBiMap<V, K> inverse() {
        JdkBackedImmutableBiMap<V, K> jdkBackedImmutableBiMap = this.inverse;
        if (jdkBackedImmutableBiMap != null) {
            return jdkBackedImmutableBiMap;
        }
        JdkBackedImmutableBiMap<V, K> jdkBackedImmutableBiMap2 = new JdkBackedImmutableBiMap<>(new InverseEntries(), this.backwardDelegate, this.forwardDelegate);
        this.inverse = jdkBackedImmutableBiMap2;
        jdkBackedImmutableBiMap2.inverse = this;
        return jdkBackedImmutableBiMap2;
    }

    private final class InverseEntries extends ImmutableList<Map.Entry<V, K>> {
        @Override // com.google.common.collect.ImmutableCollection
        boolean isPartialView() {
            return false;
        }

        private InverseEntries() {
        }

        @Override // java.util.List
        public Map.Entry<V, K> get(int i) {
            Map.Entry entry = (Map.Entry) JdkBackedImmutableBiMap.this.entries.get(i);
            return Maps.immutableEntry(entry.getValue(), entry.getKey());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return JdkBackedImmutableBiMap.this.entries.size();
        }
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(@NullableDecl Object obj) {
        return this.forwardDelegate.get(obj);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }
}
