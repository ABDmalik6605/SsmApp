package com.google.common.base;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface Function<F, T> extends java.util.function.Function<F, T> {
    @Override // java.util.function.Function
    @NullableDecl
    T apply(@NullableDecl F f);

    boolean equals(@NullableDecl Object obj);
}
