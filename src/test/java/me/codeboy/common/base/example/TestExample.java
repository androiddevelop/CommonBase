package me.codeboy.common.base.example;

import me.codeboy.common.base.log.CBLog;
import me.codeboy.common.base.net.CBHttp;
import me.codeboy.common.base.net.CBHttps;
import me.codeboy.common.base.security.CBBase64;
import me.codeboy.common.base.util.CBVersionUtil;

import java.io.IOException;


/**
 * 测试
 */
public class TestExample {

    public static void main(String[] args) {
         new TestExample().testBase64();
//         new TestExample().testHttps();
//        new TestExample().testHttp();
//        new TestExample().testVersionUtil();
    }

    /**
     * base64编码
     */
    public void testBase64() {
        String str = "小胖轩(codeboy.me)";
        String tmp = CBBase64.encode(str);
        CBLog.print(tmp);
        try {
            CBLog.print(CBBase64.decode(tmp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试https
     */
    public void testHttps() {
        String url = "https://kyfw.12306.cn/otn/leftTicket/init";
        try {
            CBLog.print(CBHttps.getInstance().connect(url).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试http
     */
    public void testHttp() {
        String url = "http://codeboy.me";
        try {
            String abc = CBHttp.getInstance().connect(url).execute();
            CBLog.print(abc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 版本比较
     */
    public void testVersionUtil() {
        CBLog.print(CBVersionUtil.versionCompareAndHasANewVersion("1.1.2", "1.2.1"));
        CBLog.print(CBVersionUtil.versionCompareAndHasANewVersion("1.1.2", "1.1.2"));
        CBLog.print(CBVersionUtil.versionCompareAndHasANewVersion("1.1.2", "1.1.2.1"));
        CBLog.print(CBVersionUtil.versionCompareAndHasANewVersion("1.10.2", "1.2.1.1"));
        CBLog.print(CBVersionUtil.versionCompareAndHasANewVersion("1.10.2", "1.1.1"));
    }
}