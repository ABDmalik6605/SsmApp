package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.AEADBadTagException;

/* loaded from: classes2.dex */
abstract class ChaCha20Poly1305Base implements Aead {
    private final ChaCha20Base chacha20;
    private final ChaCha20Base macKeyChaCha20;

    abstract ChaCha20Base newChaCha20Instance(final byte[] key, int initialCounter) throws InvalidKeyException;

    public ChaCha20Poly1305Base(final byte[] key) throws InvalidKeyException {
        this.chacha20 = newChaCha20Instance(key, 1);
        this.macKeyChaCha20 = newChaCha20Instance(key, 0);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        if (plaintext.length > (Integer.MAX_VALUE - this.chacha20.nonceSizeInBytes()) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(plaintext.length + this.chacha20.nonceSizeInBytes() + 16);
        encrypt(byteBufferAllocate, plaintext, associatedData);
        return byteBufferAllocate.array();
    }

    private void encrypt(ByteBuffer output, final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        if (output.remaining() < plaintext.length + this.chacha20.nonceSizeInBytes() + 16) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        int iPosition = output.position();
        this.chacha20.encrypt(output, plaintext);
        output.position(iPosition);
        byte[] bArr = new byte[this.chacha20.nonceSizeInBytes()];
        output.get(bArr);
        output.limit(output.limit() - 16);
        if (associatedData == null) {
            associatedData = new byte[0];
        }
        byte[] bArrComputeMac = Poly1305.computeMac(getMacKey(bArr), macDataRfc8439(associatedData, output));
        output.limit(output.limit() + 16);
        output.put(bArrComputeMac);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        return decrypt(ByteBuffer.wrap(ciphertext), associatedData);
    }

    private byte[] decrypt(ByteBuffer ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        if (ciphertext.remaining() < this.chacha20.nonceSizeInBytes() + 16) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        int iPosition = ciphertext.position();
        byte[] bArr = new byte[16];
        ciphertext.position(ciphertext.limit() - 16);
        ciphertext.get(bArr);
        ciphertext.position(iPosition);
        ciphertext.limit(ciphertext.limit() - 16);
        byte[] bArr2 = new byte[this.chacha20.nonceSizeInBytes()];
        ciphertext.get(bArr2);
        if (associatedData == null) {
            associatedData = new byte[0];
        }
        try {
            Poly1305.verifyMac(getMacKey(bArr2), macDataRfc8439(associatedData, ciphertext), bArr);
            ciphertext.position(iPosition);
            return this.chacha20.decrypt(ciphertext);
        } catch (GeneralSecurityException e) {
            throw new AEADBadTagException(e.toString());
        }
    }

    private byte[] getMacKey(final byte[] nonce) throws GeneralSecurityException {
        byte[] bArr = new byte[32];
        this.macKeyChaCha20.chacha20Block(nonce, 0).get(bArr);
        return bArr;
    }

    private static byte[] macDataRfc8439(final byte[] aad, ByteBuffer ciphertext) {
        int length = aad.length % 16 == 0 ? aad.length : (aad.length + 16) - (aad.length % 16);
        int iRemaining = ciphertext.remaining();
        int i = iRemaining % 16;
        int i2 = (i == 0 ? iRemaining : (iRemaining + 16) - i) + length;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i2 + 16).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.put(aad);
        byteBufferOrder.position(length);
        byteBufferOrder.put(ciphertext);
        byteBufferOrder.position(i2);
        byteBufferOrder.putLong(aad.length);
        byteBufferOrder.putLong(iRemaining);
        return byteBufferOrder.array();
    }
}
