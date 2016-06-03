package me.codeboy.common.base.net.core;

import me.codeboy.common.base.net.constant.CBDefaultConfig;
import me.codeboy.common.base.net.constant.CBHeader;
import me.codeboy.common.base.net.constant.CBMethod;
import me.codeboy.common.base.net.util.CBParam;
import me.codeboy.common.base.util.CBStringUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络操作，可以获取网页源代码，下载网络文件
 *
 * @author Yuedong Li
 */
public class CBConnection {
    private String url = null; //请求地址
    private int timeout = CBDefaultConfig.TIMEOUT; //超时时间,默认30s
    private CBMethod method = CBDefaultConfig.METHOD; //请求方式,默认get
    private String charset = CBDefaultConfig.CHARSET; //编码方式,模式UTF-8
    private boolean followRedirects = CBDefaultConfig.FOLLOW_REDIRECTS; //是否继续请求
    private boolean keepSession = CBDefaultConfig.KEEP_SESSION; //保持session
    private boolean pauseSessionForOnce = false; //暂时不携带session信息一次
    private String cookie = null; //用户维持session的cookie
    private String data = null; //请求表单数据
    private boolean keepCharset = false; //保持编码集
    private boolean keepMethod = false; //保持请求方式
    private Map<String, String> header = new HashMap<String, String>(); //头部

    /**
     * 连接地址
     *
     * @param url url
     * @return 连接
     */
    public CBConnection connect(String url) {
        this.url = url;
        return this;
    }

    /**
     * 连接地址
     *
     * @param url     url
     * @param charset 编码
     * @return 连接
     */
    public CBConnection connect(String url, String charset) {
        this.url = url;
        this.charset = charset;
        return this;
    }

    /**
     * 保持请求编码集
     *
     * @param keepCharset 保持编码集
     * @return 连接
     */
    public CBConnection keepCharset(boolean keepCharset) {
        this.keepCharset = keepCharset;
        return this;
    }

    /**
     * 设置编码
     *
     * @param charset 编码,默认编码UTF-8
     * @return 连接
     * @see CBDefaultConfig
     */
    public CBConnection charset(String charset) {
        this.charset = charset;
        return this;
    }

    /**
     * 保持请求方式
     *
     * @param keepMethod 保持请求方式
     * @return 连接
     */
    public CBConnection keepMethod(boolean keepMethod) {
        this.keepMethod = keepMethod;
        return this;
    }

    /**
     * 请求方法
     *
     * @param method 方法,默认GET
     * @return 连接
     * @see CBMethod
     * @see CBDefaultConfig
     */
    public CBConnection method(CBMethod method) {
        this.method = method;
        return this;
    }

    /**
     * 设置请求头部中的cookie
     *
     * @param cookie cookie
     * @return 连接
     */
    public CBConnection cookie(String cookie) {
        header.put(CBHeader.COOKIE, cookie);
        return this;
    }

    /**
     * 设置请求属性
     *
     * @param followRedirect 是否在301/302的时候继续请求
     * @return 连接
     */
    public CBConnection followRedirects(boolean followRedirect) {
        this.followRedirects = followRedirect;
        return this;
    }

    /**
     * 设置请求连接与读取的超时时间
     *
     * @param timeout 时间,单位毫秒,默认30s
     * @return 连接
     * @see CBDefaultConfig
     */
    public CBConnection timeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * 设置请求头部中的host
     *
     * @param host host
     * @return 连接
     */
    public CBConnection host(String host) {
        header.put(CBHeader.HOST, host);
        return this;
    }

    /**
     * 设置请求头部中的referer
     *
     * @param referrer 来源
     * @return 连接
     */
    public CBConnection referrer(String referrer) {
        header.put(CBHeader.REFERRER, referrer);
        return this;
    }

    /**
     * 设置请求头部中的user agent
     *
     * @param userAgent ua
     * @return 连接
     */
    public CBConnection userAgent(String userAgent) {
        header.put(CBHeader.USER_AGENT, userAgent);
        return this;
    }

    /**
     * 添加默认PC代理(osx)
     *
     * @return 连接
     */
    public CBConnection addDefaultPcUserAgent() {
        header.put(CBHeader.USER_AGENT, CBDefaultConfig.PC_UA);
        return this;
    }

    /**
     * 添加默认手机代理(android)
     *
     * @return 连接
     */
    public CBConnection addDefaultMobileUserAgent() {
        header.put(CBHeader.USER_AGENT, CBDefaultConfig.MOBILE_UA);
        return this;
    }

    /**
     * 添加请求头部,此方法将会清空之前所有已经设置的头部信息
     *
     * @param header 请求头部
     * @return 连接
     * @see #header(String, String)
     */
    public CBConnection header(Map<String, String> header) {
        if (header != null) {
            this.header = header;
        } else {
            header.clear();
        }
        return this;
    }

    /**
     * 添加请求头部,仅仅添加一个,不会清空之前已经设置的头部
     *
     * @param key   请求头部名字
     * @param value 请求头部值
     * @return 连接
     * @see #header(Map)
     */
    public CBConnection header(String key, String value) {
        header.put(key, value);
        return this;
    }

    /**
     * 设置请求数据
     *
     * @param data 表单数据,格式与url传递参数格式相同
     * @return 连接
     */
    public CBConnection data(String data) {
        this.data = data;
        return this;
    }

    /**
     * 设置请求数据
     * 
     * @param data 表单数据
     * @return 连接
     */
    public CBConnection data(String... data) {
        if (data.length % 2 == 0) {
            this.data = CBParam.paramsToString(charset, data);
        }
        return this;
    }

    /**
     * 设置请求数据
     *
     * @param data 表单数据
     * @return 连接
     */
    public CBConnection data(Map<String, String> data) {
        if (data != null) {
            this.data = CBParam.paramsToString(charset, data);
        }
        return this;
    }

    /**
     * 保持session,便于请求下个连接的时候不需要再次设置cookie等值
     *
     * @param keepSession 保持session
     * @return 连接
     */
    public CBConnection keepSession(boolean keepSession) {
        this.keepSession = keepSession;
        if (!keepSession) {
            cookie = null;
        }
        return this;
    }

    /**
     * 此次连接不进行session信息携带
     *
     * @return 连接
     */
    public CBConnection doNotTakeSessionForOnce() {
        pauseSessionForOnce = true;
        return this;
    }

    /**
     * 获取网页的内容
     *
     * @return 网页/目标源码
     * @throws IOException 异常
     */
    public String execute() throws IOException {

        if (CBStringUtil.isEmptyOrNull(this.url)) {
            return null;
        }

        StringBuilder netString = new StringBuilder();
        URL url = new URL(this.url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        //设置请求方法
        conn.setRequestMethod(method.value);

        //设置头部
        for (Map.Entry<String, String> item : header.entrySet()) {
            conn.setRequestProperty(item.getKey(), item.getValue());
        }

        //保持session的把设置cookie
        if (keepSession && !CBStringUtil.isEmptyOrNull(cookie) && !header.containsKey(CBHeader.COOKIE)
                && !pauseSessionForOnce) {
            conn.setRequestProperty(CBHeader.COOKIE, cookie);
        }

        pauseSessionForOnce = false;

        //设置超时时间
        conn.setConnectTimeout(timeout);
        conn.setReadTimeout(timeout);

        //设置跳转
        conn.setInstanceFollowRedirects(followRedirects);

        if (method == CBMethod.POST && !CBStringUtil.isEmptyOrNull(data)) {
            conn.setDoOutput(true);
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
        }
        BufferedReader buff = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), charset));
        String line;
        while ((line = buff.readLine()) != null) {
            netString.append("\n");
            netString.append(line);
        }
        buff.close();

        //记录cookie值,用于下次传递
        String tmpCookie = conn.getHeaderField(CBHeader.SET_COOKIE);
        if (keepSession && CBStringUtil.isEmptyOrNull(cookie) && !CBStringUtil.isEmptyOrNull(tmpCookie)) {
            cookie = tmpCookie;
        }
        return netString.length() > 0 ? netString.substring(1) : netString.toString();
    }

    /**
     * 参数重置
     * 其中timeout,cookie,keepSession不进行重置
     *
     * @return 连接
     */
    protected CBConnection resetSomeState() {
        url = null;
        followRedirects = CBDefaultConfig.FOLLOW_REDIRECTS;
        header.clear();
        data = null;
        if (!keepMethod) {
            method = CBDefaultConfig.METHOD;
        }
        if (!keepCharset) {
            charset = CBDefaultConfig.CHARSET;
        }
        return this;
    }

    /**
     * 保存指定位置的文件
     *
     * @param file 保存文件
     * @throws IOException IO异常
     */
    public void saveToFile(File file)
            throws IOException {
        if (CBStringUtil.isEmptyOrNull(this.url)) {
            return;
        }
        URL url = new URL(this.url);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        OutputStream os = new FileOutputStream(file);
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            os.write(b, 0, len);
        }
        os.close();
        is.close();
    }
}
