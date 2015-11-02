package me.codeboy.common.base.security;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 实现https安全登录
 * 
 * @author Yuedong Li
 * 
 */
public class CBX509TrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {

	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// System.out.println("cert: " + arg0[0].toString() + ", authType: "
		// + arg1);
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
