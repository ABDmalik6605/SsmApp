package org.apache.commons.lang.math;

import java.util.Random;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes3.dex */
public final class JVMRandom extends Random {
    private static final Random SHARED_RANDOM = new Random();
    private static final long serialVersionUID = 1;
    private boolean constructed;

    private static int bitsRequired(long j) {
        int i = 0;
        long j2 = j;
        while (j >= 0) {
            if (j2 == 0) {
                return i;
            }
            i++;
            j <<= 1;
            j2 >>= 1;
        }
        return 64 - i;
    }

    public JVMRandom() {
        this.constructed = false;
        this.constructed = true;
    }

    @Override // java.util.Random
    public synchronized void setSeed(long j) {
        if (this.constructed) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Random
    public synchronized double nextGaussian() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Random
    public void nextBytes(byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Random
    public int nextInt() {
        return nextInt(Integer.MAX_VALUE);
    }

    @Override // java.util.Random
    public int nextInt(int i) {
        return SHARED_RANDOM.nextInt(i);
    }

    @Override // java.util.Random
    public long nextLong() {
        return nextLong(LongCompanionObject.MAX_VALUE);
    }

    public static long nextLong(long j) {
        long jNext63bits;
        long j2;
        if (j <= 0) {
            throw new IllegalArgumentException("Upper bound for nextInt must be positive");
        }
        if (((-j) & j) == j) {
            return next63bits() >> (63 - bitsRequired(j - 1));
        }
        do {
            jNext63bits = next63bits();
            j2 = jNext63bits % j;
        } while ((jNext63bits - j2) + (j - 1) < 0);
        return j2;
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        return SHARED_RANDOM.nextBoolean();
    }

    @Override // java.util.Random
    public float nextFloat() {
        return SHARED_RANDOM.nextFloat();
    }

    @Override // java.util.Random
    public double nextDouble() {
        return SHARED_RANDOM.nextDouble();
    }

    private static long next63bits() {
        return SHARED_RANDOM.nextLong() & LongCompanionObject.MAX_VALUE;
    }
}
