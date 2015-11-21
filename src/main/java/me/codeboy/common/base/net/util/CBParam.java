package me.codeboy.common.base.net.util;

import java.util.Map;

/**
 * 参数转换
 * Created by yuedong on 11/3/15.
 */
public class CBParam {
    /**
     * convert parameters to string
     *
     * @param params
     *         parameter
     * @return 拼接后的字符串
     */
    public static String paramsToString(String... params) {
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

    public static String paramsToString(Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> item : params.entrySet()) {
            sb.append("&");
            sb.append(item.getKey());
            sb.append("=");
            sb.append(item.getValue());
        }
        return sb.substring(1);
    }
}
