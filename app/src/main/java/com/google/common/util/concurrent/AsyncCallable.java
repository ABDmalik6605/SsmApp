package com.google.common.util.concurrent;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface AsyncCallable<V> {
    ListenableFuture<V> call() throws Exception;
}
