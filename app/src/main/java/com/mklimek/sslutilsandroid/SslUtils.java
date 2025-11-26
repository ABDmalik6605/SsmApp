package com.mklimek.sslutilsandroid;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/* loaded from: classes2.dex */
public class SslUtils {
    public static SSLContext getSslContextForCertificateFile(Context context, String str) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        try {
            KeyStore keyStore = getKeyStore(context, str);
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sSLContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sSLContext;
        } catch (Exception e) {
            Log.e("SslUtilsAndroid", "cannot create http client with certificate from assets (which JellyBean lack)", e);
            throw new RuntimeException("cannot create http client with certificate from assets (which JellyBean lack)");
        }
    }

    private static KeyStore getKeyStore(Context context, String str) throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException {
        KeyStore keyStore = null;
        try {
            AssetManager assets = context.getAssets();
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream inputStreamOpen = assets.open(str);
            try {
                Certificate certificateGenerateCertificate = certificateFactory.generateCertificate(inputStreamOpen);
                Log.d("SslUtilsAndroid", "ca=" + ((X509Certificate) certificateGenerateCertificate).getSubjectDN());
                inputStreamOpen.close();
                KeyStore keyStore2 = KeyStore.getInstance(KeyStore.getDefaultType());
                try {
                    keyStore2.load(null, null);
                    keyStore2.setCertificateEntry("ca", certificateGenerateCertificate);
                    return keyStore2;
                } catch (Exception e) {
                    e = e;
                    keyStore = keyStore2;
                    Log.e("SslUtilsAndroid", "Error during getting keystore", e);
                    return keyStore;
                }
            } catch (Throwable th) {
                inputStreamOpen.close();
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }
}
