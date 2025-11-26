package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    static final ImmutableMultiset<Object> EMPTY = create(ImmutableList.of());
    static final double HASH_FLOODING_FPP = 0.001d;
    static final int MAX_HASH_BUCKET_LENGTH = 9;
    static final double MAX_LOAD_FACTOR = 1.0d;

    @LazyInit
    private transient ImmutableSet<E> elementSet;
    private final transient Multisets.ImmutableEntry<E>[] entries;
    private final transient int hashCode;

    @NullableDecl
    private final transient Multisets.ImmutableEntry<E>[] hashTable;
    private final transient int size;

    @Override // com.google.common.collect.ImmutableCollection
    boolean isPartialView() {
        return false;
    }

    static <E> ImmutableMultiset<E> create(Collection<? extends Multiset.Entry<? extends E>> collection) {
        Multisets.ImmutableEntry nonTerminalEntry;
        int size = collection.size();
        Multisets.ImmutableEntry[] immutableEntryArr = new Multisets.ImmutableEntry[size];
        if (size == 0) {
            return new RegularImmutableMultiset(immutableEntryArr, null, 0, 0, ImmutableSet.of());
        }
        int iClosedTableSize = Hashing.closedTableSize(size, 1.0d);
        int i = iClosedTableSize - 1;
        Multisets.ImmutableEntry[] immutableEntryArr2 = new Multisets.ImmutableEntry[iClosedTableSize];
        long j = 0;
        int i2 = 0;
        int i3 = 0;
        for (Multiset.Entry<? extends E> entry : collection) {
            Object objCheckNotNull = Preconditions.checkNotNull(entry.getElement());
            int count = entry.getCount();
            int iHashCode = objCheckNotNull.hashCode();
            int iSmear = Hashing.smear(iHashCode) & i;
            Multisets.ImmutableEntry immutableEntry = immutableEntryArr2[iSmear];
            if (immutableEntry == null) {
                nonTerminalEntry = (entry instanceof Multisets.ImmutableEntry) && !(entry instanceof NonTerminalEntry) ? (Multisets.ImmutableEntry) entry : new Multisets.ImmutableEntry(objCheckNotNull, count);
            } else {
                nonTerminalEntry = new NonTerminalEntry(objCheckNotNull, count, immutableEntry);
            }
            i2 += iHashCode ^ count;
            immutableEntryArr[i3] = nonTerminalEntry;
            immutableEntryArr2[iSmear] = nonTerminalEntry;
            j += count;
            i3++;
        }
        if (hashFloodingDetected(immutableEntryArr2)) {
            return JdkBackedImmutableMultiset.create(ImmutableList.asImmutableList(immutableEntryArr));
        }
        return new RegularImmutableMultiset(immutableEntryArr, immutableEntryArr2, Ints.saturatedCast(j), i2, null);
    }

    private static boolean hashFloodingDetected(Multisets.ImmutableEntry<?>[] immutableEntryArr) {
        for (Multisets.ImmutableEntry<?> immutableEntryNextInBucket : immutableEntryArr) {
            int i = 0;
            for (; immutableEntryNextInBucket != null; immutableEntryNextInBucket = immutableEntryNextInBucket.nextInBucket()) {
                i++;
                if (i > 9) {
                    return true;
                }
            }
        }
        return false;
    }

    private RegularImmutableMultiset(Multisets.ImmutableEntry<E>[] immutableEntryArr, Multisets.ImmutableEntry<E>[] immutableEntryArr2, int i, int i2, ImmutableSet<E> immutableSet) {
        this.entries = immutableEntryArr;
        this.hashTable = immutableEntryArr2;
        this.size = i;
        this.hashCode = i2;
        this.elementSet = immutableSet;
    }

    private static final class NonTerminalEntry<E> extends Multisets.ImmutableEntry<E> {
        private final Multisets.ImmutableEntry<E> nextInBucket;

        NonTerminalEntry(E e, int i, Multisets.ImmutableEntry<E> immutableEntry) {
            super(e, i);
            this.nextInBucket = immutableEntry;
        }

        @Override // com.google.common.collect.Multisets.ImmutableEntry
        public Multisets.ImmutableEntry<E> nextInBucket() {
            return this.nextInBucket;
        }
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object obj) {
        Multisets.ImmutableEntry<E>[] immutableEntryArr = this.hashTable;
        if (obj != null && immutableEntryArr != null) {
            for (Multisets.ImmutableEntry<E> immutableEntryNextInBucket = immutableEntryArr[Hashing.smearedHash(obj) & (immutableEntryArr.length - 1)]; immutableEntryNextInBucket != null; immutableEntryNextInBucket = immutableEntryNextInBucket.nextInBucket()) {
                if (Objects.equal(obj, immutableEntryNextInBucket.getElement())) {
                    return immutableEntryNextInBucket.getCount();
                }
            }
        }
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> immutableSet = this.elementSet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableMultiset.ElementSet elementSet = new ImmutableMultiset.ElementSet(Arrays.asList(this.entries), this);
        this.elementSet = elementSet;
        return elementSet;
    }

    @Override // com.google.common.collect.ImmutableMultiset
    Multiset.Entry<E> getEntry(int i) {
        return this.entries[i];
    }

    @Override // com.google.common.collect.ImmutableMultiset, java.util.Collection, com.google.common.collect.Multiset
    public int hashCode() {
        return this.hashCode;
    }
}
