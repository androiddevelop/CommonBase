package me.codeboy.common.base.net;

import me.codeboy.common.base.security.CBHostnameVerifier;
import me.codeboy.common.base.security.CBX509TrustManager;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * 网络https操作，可以获取网页源代码，下载网络文件
 *
 * @author Yuedong Li
 */
public class CBHttpsUtil {
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

    /**
     * 得到网络地址对应的字符串，也即得到网页源代码
     *
     * @param address
     *         网址
     * @return 网页源代码
     * @throws IOException
     *         IO异常
     */
    public String get(String address) throws IOException {
        return get(address, "UTF-8");
    }

    /**
     * 按照指定编码得到网络地址对应的字符串，也即得到网页源代码
     *
     * @param address
     *         网址
     * @param encoding
     *         编码
     * @return 网页源代码
     * @throws IOException
     *         IO异常
     */
    public String get(String address, String encoding) throws IOException {
        StringBuffer netString = new StringBuffer();
        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader buff = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), encoding));
        String line;
        while ((line = buff.readLine()) != null) {
            netString.append("\n");
            netString.append(line);
        }
        buff.close();
        return netString.substring(1);
    }

    /**
     * 按照指定编码得到网络地址对应的字符串
     *
     * @param address
     *         网址
     * @param params
     *         参数
     * @return 网页源代码
     * @throws IOException
     *         IO异常
     */
    public String post(String address, String... params) throws IOException {
        return post(address, "UTF-8", paramsToString(params));
    }

    /**
     * 按照指定编码得到网络地址对应的字符串
     *
     * @param address
     *         网址
     * @param encoding
     *         编码
     * @param params
     *         参数
     * @return 网页源代码
     * @throws IOException
     *         IO异常
     */
    public String post(String address, String encoding, String... params)
            throws IOException {
        return post(address, encoding, paramsToString(params));
    }

    /**
     * 按照指定编码得到网络地址对应的字符串
     *
     * @param address
     *         网址
     * @param params
     *         参数
     * @return 网页源代码
     * @throws IOException
     *         IO异常
     */
    public String post(String address, Map<String, String> params)
            throws IOException {
        return post(address, "UTF-8", paramsToString(params));
    }

    /**
     * 按照指定编码得到网络地址对应的字符串
     *
     * @param address
     *         网址
     * @param encoding
     *         编码
     * @param params
     *         参数
     * @return 网页源代码
     * @throws IOException
     *         IO异常
     */
    public String post(String address, String encoding,
                       Map<String, String> params) throws IOException {
        return post(address, encoding, paramsToString(params));

    }

    /**
     * 按照指定编码得到网络地址对应的字符串
     *
     * @param address
     *         网址
     * @param encoding
     *         编码
     * @param params
     *         参数
     * @return 网页源代码
     * @throws IOException
     *         IO异常
     */
    public String post(String address, String encoding, String paramsString)
            throws IOException {
        StringBuffer netString = new StringBuffer();
        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(paramsString.getBytes(encoding));
        BufferedReader buff = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), encoding));
        String line;
        while ((line = buff.readLine()) != null) {
            netString.append("\n");
            netString.append(line);
        }
        buff.close();
        return netString.substring(1);
    }

    /**
     * convert parameters to string
     *
     * @param params
     *         parameter
     * @return 拼接后的字符串
     */
    private String paramsToString(String... params) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < params.length - 1; i = i + 1) {
            sb.append("&");
            sb.append(params[i]);
            sb.append("=");
            sb.append(params[i + 1]);
        }
        return sb.substring(1);
    }

    /**
     * convert parameters to string
     *
     * @param params
     *         parameter
     * @return 拼接后的字符串
     */
    private String paramsToString(Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> item : params.entrySet()) {
            sb.append("&");
            sb.append(item.getKey());
            sb.append("=");
            sb.append(item.getValue());
        }
        return sb.substring(1);
    }

    /**
     * 保存指定位置的文件
     *
     * @param address
     *         网络地址
     * @param filePath
     *         保存文件路径
     * @throws IOException
     *         IO异常
     */
    public void saveNetworkFile(String address, String filePath)
            throws IOException {
        URL url = new URL(address);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        File desFile = new File(filePath);
        OutputStream os = new FileOutputStream(desFile);
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            os.write(b, 0, len);
        }
        os.close();
        is.close();
    }
}