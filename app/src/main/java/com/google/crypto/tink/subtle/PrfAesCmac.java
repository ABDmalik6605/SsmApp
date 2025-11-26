package com.google.crypto.tink.subtle;

import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Immutable
/* loaded from: classes2.dex */
public final class PrfAesCmac implements Prf {
    private final SecretKey keySpec;
    private byte[] subKey1;
    private byte[] subKey2;

    private static Cipher instance() throws GeneralSecurityException {
        return EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
    }

    public PrfAesCmac(final byte[] key) throws GeneralSecurityException {
        Validators.validateAesKeySize(key.length);
        this.keySpec = new SecretKeySpec(key, "AES");
        generateSubKeys();
    }

    @Override // com.google.crypto.tink.prf.Prf
    public byte[] compute(final byte[] data, int outputLength) throws GeneralSecurityException {
        byte[] bArrXor;
        if (outputLength > 16) {
            throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
        }
        Cipher cipherInstance = instance();
        cipherInstance.init(1, this.keySpec);
        int iMax = Math.max(1, (int) Math.ceil(data.length / 16.0d));
        if (iMax * 16 == data.length) {
            bArrXor = Bytes.xor(data, (iMax - 1) * 16, this.subKey1, 0, 16);
        } else {
            bArrXor = Bytes.xor(AesUtil.cmacPad(Arrays.copyOfRange(data, (iMax - 1) * 16, data.length)), this.subKey2);
        }
        byte[] bArrDoFinal = new byte[16];
        for (int i = 0; i < iMax - 1; i++) {
            bArrDoFinal = cipherInstance.doFinal(Bytes.xor(bArrDoFinal, 0, data, i * 16, 16));
        }
        return Arrays.copyOf(cipherInstance.doFinal(Bytes.xor(bArrXor, bArrDoFinal)), outputLength);
    }

    private void generateSubKeys() throws GeneralSecurityException {
        Cipher cipherInstance = instance();
        cipherInstance.init(1, this.keySpec);
        byte[] bArrDbl = AesUtil.dbl(cipherInstance.doFinal(new byte[16]));
        this.subKey1 = bArrDbl;
        this.subKey2 = AesUtil.dbl(bArrDbl);
    }
}
