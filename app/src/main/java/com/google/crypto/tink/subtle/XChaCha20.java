package com.google.crypto.tink.subtle;

import java.security.InvalidKeyException;
import java.util.Arrays;

/* loaded from: classes2.dex */
class XChaCha20 extends ChaCha20Base {
    @Override // com.google.crypto.tink.subtle.ChaCha20Base
    int nonceSizeInBytes() {
        return 24;
    }

    XChaCha20(byte[] key, int initialCounter) throws InvalidKeyException {
        super(key, initialCounter);
    }

    @Override // com.google.crypto.tink.subtle.ChaCha20Base
    int[] createInitialState(final int[] nonce, int counter) {
        if (nonce.length != nonceSizeInBytes() / 4) {
            throw new IllegalArgumentException(String.format("XChaCha20 uses 192-bit nonces, but got a %d-bit nonce", Integer.valueOf(nonce.length * 32)));
        }
        int[] iArr = new int[16];
        ChaCha20Base.setSigmaAndKey(iArr, hChaCha20(this.key, nonce));
        iArr[12] = counter;
        iArr[13] = 0;
        iArr[14] = nonce[4];
        iArr[15] = nonce[5];
        return iArr;
    }

    static int[] hChaCha20(final int[] key, final int[] nonce) {
        int[] iArr = new int[16];
        ChaCha20Base.setSigmaAndKey(iArr, key);
        iArr[12] = nonce[0];
        iArr[13] = nonce[1];
        iArr[14] = nonce[2];
        iArr[15] = nonce[3];
        ChaCha20Base.shuffleState(iArr);
        iArr[4] = iArr[12];
        iArr[5] = iArr[13];
        iArr[6] = iArr[14];
        iArr[7] = iArr[15];
        return Arrays.copyOf(iArr, 8);
    }
}
