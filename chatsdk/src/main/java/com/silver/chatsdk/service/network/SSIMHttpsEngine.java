package com.silver.chatsdk.service.network;

import android.content.Context;

import com.silver.chatsdk.R;
import com.silver.chatsdk.service.manager.SSIMEngine;

import java.io.InputStream;


import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;


import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * HTTPS请求
 */
public class SSIMHttpsEngine extends SSIMNetworkEngine{

    private static final String SSL_TRUST_PWD = "sslpwd";
    private static final String SSL_CERT_TYPE = "X.509";
    private static final String SSL_TYPE = "TLS";


    private APIService httpsEngine;

    private static class SingletonHolder {
        private static final SSIMHttpsEngine INSTANCE = new SSIMHttpsEngine();  //创建实例的地方
    }

    private SSIMHttpsEngine() {
    }

    public static SSIMHttpsEngine getInstance(Context ctx) {
        SSIMHttpsEngine httpsEngine = SingletonHolder.INSTANCE;

        synchronized (APIService.class) {
            if (httpsEngine.httpsEngine == null) {
                SSIMEngine engine = SSIMEngine.getInstance();

                try {
                    //获取证书信息
                    int[] certIds = {R.raw.key, R.raw.secret};
                    //获取证书管理对象
                    X509TrustManager trustManager = trustManagerFromCertificates(ctx, certIds);
                    SSLContext sctx = SSLContext.getInstance(SSL_TYPE);
                    sctx.init(null, new TrustManager[]{trustManager}, null);

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .sslSocketFactory(sctx.getSocketFactory(), trustManager)
                            .build();


                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(engine.getConfig().getHttpConfig().formatURL())
                            .client(okHttpClient)
                            .build();
                    httpsEngine.httpsEngine = retrofit.create(APIService.class);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return httpsEngine;
    }

    /**
     * 以流的方式添加证书
     */
    private static X509TrustManager trustManagerFromCertificates(Context ctx, int[] certIds) {

        try {
            CertificateFactory cf = CertificateFactory.getInstance(SSL_CERT_TYPE);
            KeyStore keyStore = newKeyStore(SSL_TRUST_PWD);
            for (int i = 0; i < certIds.length; i++) {
                InputStream input = ctx.getResources().openRawResource(certIds[i]);
                Certificate certificate = cf.generateCertificate(input);
                keyStore.setCertificateEntry(String.valueOf(i), certificate);

                input.close();
            }
            TrustManagerFactory trustManagerFac = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFac.init(keyStore);
            TrustManager[] tms = trustManagerFac.getTrustManagers();
            if (tms.length == 1 && tms[0] instanceof X509TrustManager) {
                return (X509TrustManager) tms[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过pwd初始化keystore
     */
    private static KeyStore newKeyStore(String password) {
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyStore;
    }


    @Override
   public APIService getEngine() {
        return this.httpsEngine;
    }
}
