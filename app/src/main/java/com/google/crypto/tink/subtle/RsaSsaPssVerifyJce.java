package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class RsaSsaPssVerifyJce implements PublicKeyVerify {
    private final Enums.HashType mgf1Hash;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final Enums.HashType sigHash;

    public RsaSsaPssVerifyJce(final RSAPublicKey pubKey, Enums.HashType sigHash, Enums.HashType mgf1Hash, int saltLength) throws GeneralSecurityException {
        Validators.validateSignatureHash(sigHash);
        Validators.validateRsaModulusSize(pubKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(pubKey.getPublicExponent());
        this.publicKey = pubKey;
        this.sigHash = sigHash;
        this.mgf1Hash = mgf1Hash;
        this.saltLength = saltLength;
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
        BigInteger publicExponent = this.publicKey.getPublicExponent();
        BigInteger modulus = this.publicKey.getModulus();
        int iBitLength = ((modulus.bitLength() - 1) + 7) / 8;
        if ((modulus.bitLength() + 7) / 8 != signature.length) {
            throw new GeneralSecurityException("invalid signature's length");
        }
        BigInteger bigIntegerBytes2Integer = SubtleUtil.bytes2Integer(signature);
        if (bigIntegerBytes2Integer.compareTo(modulus) >= 0) {
            throw new GeneralSecurityException("signature out of range");
        }
        emsaPssVerify(data, SubtleUtil.integer2Bytes(bigIntegerBytes2Integer.modPow(publicExponent, modulus), iBitLength), modulus.bitLength() - 1);
    }

    private void emsaPssVerify(byte[] m, byte[] em, int emBits) throws GeneralSecurityException {
        Validators.validateSignatureHash(this.sigHash);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.sigHash));
        byte[] bArrDigest = engineFactory.digest(m);
        int digestLength = engineFactory.getDigestLength();
        int length = em.length;
        if (length < this.saltLength + digestLength + 2) {
            throw new GeneralSecurityException("inconsistent");
        }
        if (em[em.length - 1] != -68) {
            throw new GeneralSecurityException("inconsistent");
        }
        int i = (length - digestLength) - 1;
        byte[] bArrCopyOf = Arrays.copyOf(em, i);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(em, bArrCopyOf.length, bArrCopyOf.length + digestLength);
        int i2 = 0;
        while (true) {
            int i3 = i;
            MessageDigest messageDigest = engineFactory;
            byte[] bArr = bArrDigest;
            long j = (length * 8) - emBits;
            if (i2 < j) {
                if (((bArrCopyOf[i2 / 8] >> (7 - (i2 % 8))) & 1) != 0) {
                    throw new GeneralSecurityException("inconsistent");
                }
                i2++;
                i = i3;
                engineFactory = messageDigest;
                bArrDigest = bArr;
            } else {
                byte[] bArrMgf1 = SubtleUtil.mgf1(bArrCopyOfRange, i3, this.mgf1Hash);
                int length2 = bArrMgf1.length;
                byte[] bArr2 = new byte[length2];
                for (int i4 = 0; i4 < length2; i4++) {
                    bArr2[i4] = (byte) (bArrMgf1[i4] ^ bArrCopyOf[i4]);
                }
                for (int i5 = 0; i5 <= j; i5++) {
                    int i6 = i5 / 8;
                    bArr2[i6] = (byte) ((~(1 << (7 - (i5 % 8)))) & bArr2[i6]);
                }
                int i7 = 0;
                while (true) {
                    int i8 = this.saltLength;
                    if (i7 < (r6 - i8) - 2) {
                        if (bArr2[i7] != 0) {
                            throw new GeneralSecurityException("inconsistent");
                        }
                        i7++;
                    } else {
                        if (bArr2[(r6 - i8) - 2] != 1) {
                            throw new GeneralSecurityException("inconsistent");
                        }
                        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr2, length2 - i8, length2);
                        int i9 = digestLength + 8;
                        byte[] bArr3 = new byte[this.saltLength + i9];
                        System.arraycopy(bArr, 0, bArr3, 8, bArr.length);
                        System.arraycopy(bArrCopyOfRange2, 0, bArr3, i9, bArrCopyOfRange2.length);
                        if (!Bytes.equal(messageDigest.digest(bArr3), bArrCopyOfRange)) {
                            throw new GeneralSecurityException("inconsistent");
                        }
                        return;
                    }
                }
            }
        }
    }
}
