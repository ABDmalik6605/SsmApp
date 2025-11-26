package com.micromerger.ssms.utils;

import android.content.Context;
import android.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public class Encryption {
    Cipher cipher;
    MyKeyStore myKeyStore;
    String password;
    byte[] pwBytes;
    SecretKeySpec secret;

    public Encryption(Context context) throws GeneralSecurityException, IOException {
        MyKeyStore myKeyStore = new MyKeyStore(context);
        this.myKeyStore = myKeyStore;
        String backupPwd = myKeyStore.getBackupPwd();
        this.password = backupPwd;
        this.pwBytes = backupPwd.getBytes();
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        this.secret = new SecretKeySpec(this.password.getBytes(), "AES");
    }

    public String encryptString(String string) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        this.cipher.init(1, this.secret, new IvParameterSpec(this.pwBytes));
        return Base64.encodeToString(this.cipher.doFinal(string.getBytes("UTF-8")), 0);
    }
}
