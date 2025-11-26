package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMapEntry;
import com.google.common.collect.ImmutableMapEntrySet;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    static final ImmutableMap<Object, Object> EMPTY = new RegularImmutableMap(ImmutableMap.EMPTY_ENTRY_ARRAY, null, 0);
    static final double HASH_FLOODING_FPP = 0.001d;
    static final int MAX_HASH_BUCKET_LENGTH = 8;
    static final double MAX_LOAD_FACTOR = 1.2d;
    private static final long serialVersionUID = 0;
    final transient Map.Entry<K, V>[] entries;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] table;

    @Override // com.google.common.collect.ImmutableMap
    boolean isPartialView() {
        return false;
    }

    static <K, V> ImmutableMap<K, V> fromEntries(Map.Entry<K, V>... entryArr) {
        return fromEntryArray(entryArr.length, entryArr);
    }

    static <K, V> ImmutableMap<K, V> fromEntryArray(int i, Map.Entry<K, V>[] entryArr) {
        Preconditions.checkPositionIndex(i, entryArr.length);
        if (i == 0) {
            return (RegularImmutableMap) EMPTY;
        }
        Map.Entry<K, V>[] entryArrCreateEntryArray = i == entryArr.length ? entryArr : ImmutableMapEntry.createEntryArray(i);
        int iClosedTableSize = Hashing.closedTableSize(i, MAX_LOAD_FACTOR);
        ImmutableMapEntry[] immutableMapEntryArrCreateEntryArray = ImmutableMapEntry.createEntryArray(iClosedTableSize);
        int i2 = iClosedTableSize - 1;
        for (int i3 = 0; i3 < i; i3++) {
            Map.Entry<K, V> entry = entryArr[i3];
            K key = entry.getKey();
            V value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int iSmear = Hashing.smear(key.hashCode()) & i2;
            ImmutableMapEntry immutableMapEntry = immutableMapEntryArrCreateEntryArray[iSmear];
            ImmutableMapEntry immutableMapEntryMakeImmutable = immutableMapEntry == null ? makeImmutable(entry, key, value) : new ImmutableMapEntry.NonTerminalImmutableMapEntry(key, value, immutableMapEntry);
            immutableMapEntryArrCreateEntryArray[iSmear] = immutableMapEntryMakeImmutable;
            entryArrCreateEntryArray[i3] = immutableMapEntryMakeImmutable;
            if (checkNoConflictInKeyBucket(key, immutableMapEntryMakeImmutable, immutableMapEntry) > 8) {
                return JdkBackedImmutableMap.create(i, entryArr);
            }
        }
        return new RegularImmutableMap(entryArrCreateEntryArray, immutableMapEntryArrCreateEntryArray, i2);
    }

    static <K, V> ImmutableMapEntry<K, V> makeImmutable(Map.Entry<K, V> entry, K k, V v) {
        return (entry instanceof ImmutableMapEntry) && ((ImmutableMapEntry) entry).isReusable() ? (ImmutableMapEntry) entry : new ImmutableMapEntry<>(k, v);
    }

    static <K, V> ImmutableMapEntry<K, V> makeImmutable(Map.Entry<K, V> entry) {
        return makeImmutable(entry, entry.getKey(), entry.getValue());
    }

    private RegularImmutableMap(Map.Entry<K, V>[] entryArr, ImmutableMapEntry<K, V>[] immutableMapEntryArr, int i) {
        this.entries = entryArr;
        this.table = immutableMapEntryArr;
        this.mask = i;
    }

    static int checkNoConflictInKeyBucket(Object obj, Map.Entry<?, ?> entry, @NullableDecl ImmutableMapEntry<?, ?> immutableMapEntry) {
        int i = 0;
        while (immutableMapEntry != null) {
            checkNoConflict(!obj.equals(immutableMapEntry.getKey()), "key", entry, immutableMapEntry);
            i++;
            immutableMapEntry = immutableMapEntry.getNextInKeyBucket();
        }
        return i;
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(@NullableDecl Object obj) {
        return (V) get(obj, this.table, this.mask);
    }

    @NullableDecl
    static <V> V get(@NullableDecl Object obj, @NullableDecl ImmutableMapEntry<?, V>[] immutableMapEntryArr, int i) {
        if (obj != null && immutableMapEntryArr != null) {
            for (ImmutableMapEntry<?, V> nextInKeyBucket = immutableMapEntryArr[i & Hashing.smear(obj.hashCode())]; nextInKeyBucket != null; nextInKeyBucket = nextInKeyBucket.getNextInKeyBucket()) {
                if (obj.equals(nextInKeyBucket.getKey())) {
                    return nextInKeyBucket.getValue();
                }
            }
        }
        return null;
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        Preconditions.checkNotNull(biConsumer);
        for (Map.Entry<K, V> entry : this.entries) {
            biConsumer.accept(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public int size() {
        return this.entries.length;
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<K> createKeySet() {
        return new KeySet(this);
    }

    private static final class KeySet<K, V> extends IndexedImmutableSet<K> {
        private final RegularImmutableMap<K, V> map;

        @Override // com.google.common.collect.ImmutableCollection
        boolean isPartialView() {
            return true;
        }

        KeySet(RegularImmutableMap<K, V> regularImmutableMap) {
            this.map = regularImmutableMap;
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        K get(int i) {
            return this.map.entries[i].getKey();
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        Object writeReplace() {
            return new SerializedForm(this.map);
        }

        private static class SerializedForm<K> implements Serializable {
            private static final long serialVersionUID = 0;
            final ImmutableMap<K, ?> map;

            SerializedForm(ImmutableMap<K, ?> immutableMap) {
                this.map = immutableMap;
            }

            Object readResolve() {
                return this.map.keySet();
            }
        }
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableCollection<V> createValues() {
        return new Values(this);
    }

    private static final class Values<K, V> extends ImmutableList<V> {
        final RegularImmutableMap<K, V> map;

        @Override // com.google.common.collect.ImmutableCollection
        boolean isPartialView() {
            return true;
        }

        Values(RegularImmutableMap<K, V> regularImmutableMap) {
            this.map = regularImmutableMap;
        }

        @Override // java.util.List
        public V get(int i) {
            return this.map.entries[i].getValue();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.map.size();
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        Object writeReplace() {
            return new SerializedForm(this.map);
        }

        private static class SerializedForm<V> implements Serializable {
            private static final long serialVersionUID = 0;
            final ImmutableMap<?, V> map;

            SerializedForm(ImmutableMap<?, V> immutableMap) {
                this.map = immutableMap;
            }

            Object readResolve() {
                return this.map.values();
            }
        }
    }
}
