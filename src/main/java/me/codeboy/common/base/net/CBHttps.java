package me.codeboy.common.base.net;

import me.codeboy.common.base.net.core.CBConnection;
import me.codeboy.common.base.security.CBHostnameVerifier;
import me.codeboy.common.base.security.CBX509TrustManager;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.security.GeneralSecurityException;

/**
 * https网络操作
 *
 * @author Yuedong Li
 */
public class CBHttps extends CBConnection {

    //设置ssl证书信任
    static {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            X509TrustManager[] xtmArray = new X509TrustManager[]{new CBX509TrustManager()};
            sslContext.init(null, xtmArray, new java.security.SecureRandom());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        if (sslContext != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
                    .getSocketFactory());
        }
        HttpsURLConnection
                .setDefaultHostnameVerifier(new CBHostnameVerifier());
    }


    private static CBHttps httpsClient;

    public static CBConnection getInstance() {
        if (httpsClient == null) {
            httpsClient = new CBHttps();
        }
        httpsClient.resetSomeState();
        return httpsClient;
    }

    public static CBConnection getNewInstance() {
        return new CBHttps();
    }
}
