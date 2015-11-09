package me.codeboy.common.base.net;

import java.io.IOException;
import java.net.URLConnection;
import java.util.Map;

/**
 * 网络操作，可以获取网页源代码，下载网络文件
 *
 * @author Yuedong Li
 */
public abstract class CBNetRequest {
    private String userAgent; //用户代理
    private Map<String, String> header; //头部

    /**
     * 设置用户代理
     *
     * @param userAgent
     *         用户代理
     * @return CBNetRequest
     */
    public CBNetRequest setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * 添加默认PC代理(osx)
     *
     * @return CBNetRequest
     */
    public CBNetRequest addDefaultPcUserAgent() {
        this.userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36";
        return this;
    }

    /**
     * 添加默认手机代理(android)
     *
     * @return CBNetRequest
     */
    public CBNetRequest addDefaultMobileUserAgent() {
        this.userAgent = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48M) AppleWebKit/537.36 (KHTML, like Gecko) ";
        return this;
    }

    /**
     * 设置头部
     *
     * @param header
     *         头部
     * @return CBNetRequest
     */
    public CBNetRequest setRequestHeader(Map<String, String> header) {
        this.header = header;
        return this;
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
    public abstract String get(String address, String encoding) throws IOException;

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
        return post(address, "UTF-8", CBNetParam.paramsToString(params));
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
        return post(address, encoding, CBNetParam.paramsToString(params));
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
        return post(address, "UTF-8", CBNetParam.paramsToString(params));
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
        return post(address, encoding, CBNetParam.paramsToString(params));

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
    public abstract String post(String address, String encoding, String params)
            throws IOException;

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
    public abstract void saveNetworkFile(String address, String filePath)
            throws IOException;

    /**
     * 设置用户代理
     *
     * @param conn
     *         连接
     */
    protected void insertUserAgent(URLConnection conn) {
        if (userAgent != null) {
            conn.setRequestProperty("User-Agent", userAgent);
        }
    }

    /**
     * 设置用户代理
     *
     * @param conn
     *         连接
     */
    protected void insertRequestHeader(URLConnection conn) {
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }
}