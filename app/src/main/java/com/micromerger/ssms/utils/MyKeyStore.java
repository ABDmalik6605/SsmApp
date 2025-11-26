package com.micromerger.ssms.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.UUID;

/* loaded from: classes2.dex */
public class MyKeyStore {
    final SharedPreferences sharedPreferences;

    public MyKeyStore(final Context context) throws GeneralSecurityException, IOException {
        this.sharedPreferences = EncryptedSharedPreferences.create(context, "secret_shared_prefs", new MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(), EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
    }

    public void initDBPwd() {
        if (this.sharedPreferences.contains("database_password")) {
            return;
        }
        String string = UUID.randomUUID().toString();
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString("database_password", string);
        editorEdit.apply();
    }

    public String getDbPwd() {
        return this.sharedPreferences.getString("database_password", "random");
    }

    public void initBackupPwd() {
        if (this.sharedPreferences.contains("backup_password")) {
            return;
        }
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(secureRandom.nextInt(62)));
        }
        String string = sb.toString();
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString("backup_password", string);
        editorEdit.apply();
    }

    public String getBackupPwd() {
        return this.sharedPreferences.getString("backup_password", "random");
    }
}
