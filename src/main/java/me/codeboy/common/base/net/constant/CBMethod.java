package me.codeboy.common.base.net.constant;

/**
 * 请求类型
 * Created by yuedong on 11/21/15.
 */
public enum CBMethod {
    GET("GET"), POST("POST");

    public String value;

    CBMethod(String value) {
        this.value = value;
    }
}
