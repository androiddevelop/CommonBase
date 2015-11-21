package me.codeboy.common.base.net.constant;

/**
 * 默认配置
 * Created by yuedong on 11/21/15.
 */
public class CBDefaultConfig {
    public final static int TIMEOUT = 30000; //30s
    public final static String CHARSET = "UTF-8"; //编码方式
    public final static CBMethod METHOD = CBMethod.GET; //请求方式
    public final static boolean FOLLOW_REDIRECTS = false; //301继续请求
    public final static boolean KEEP_SESSION = true; //保持session
    public final static String PC_UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36"; //pc user agent
    public final static String MOBILE_UA = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48M) AppleWebKit/537.36 (KHTML, like Gecko)"; //mobile user agent
}
