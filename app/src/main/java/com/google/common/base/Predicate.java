package com.google.common.base;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface Predicate<T> extends java.util.function.Predicate<T> {
    boolean apply(@NullableDecl T t);

    boolean equals(@NullableDecl Object obj);

    @Override // java.util.function.Predicate
    boolean test(@NullableDecl T t);

    /* renamed from: com.google.common.base.Predicate$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}
