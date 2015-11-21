package me.codeboy.common.base.net.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 参数转换
 * Created by yuedong on 11/3/15.
 */
public class CBParam {
    /**
     * convert parameters to string
     *
     * @param charset
     *         编码
     * @param params
     *         parameter
     * @return 拼接后的字符串
     */
    public static String paramsToString(String charset, String... params) {
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < params.length - 1; i = i + 1) {
                sb.append("&");
                sb.append(URLEncoder.encode(params[i], charset));
                sb.append("=");
                sb.append(URLEncoder.encode(params[i + 1], charset));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.substring(1);
    }

    /**
     * convert parameters to string
     *
     * @param charset
     *         编码
     * @param params
     *         parameter
     * @return 拼接后的字符串
     */

    public static String paramsToString(String charset, Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        try {
            for (Map.Entry<String, String> item : params.entrySet()) {
                sb.append("&");
                sb.append(URLEncoder.encode(item.getKey(), charset));
                sb.append("=");
                sb.append(URLEncoder.encode(item.getValue(), charset));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.substring(1);
    }
}
