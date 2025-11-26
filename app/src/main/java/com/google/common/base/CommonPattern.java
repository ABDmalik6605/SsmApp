package com.google.common.base;

/* loaded from: classes2.dex */
abstract class CommonPattern {
    public abstract boolean equals(Object obj);

    abstract int flags();

    public abstract int hashCode();

    abstract CommonMatcher matcher(CharSequence charSequence);

    abstract String pattern();

    public abstract String toString();

    CommonPattern() {
    }
}
