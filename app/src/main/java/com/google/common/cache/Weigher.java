package com.google.common.cache;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface Weigher<K, V> {
    int weigh(K k, V v);
}
