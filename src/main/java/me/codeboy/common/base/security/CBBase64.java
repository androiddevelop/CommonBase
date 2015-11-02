package me.codeboy.common.base.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Base64 编码解码
 */
public final class CBBase64 {

    private static BASE64Decoder decoder = new BASE64Decoder();
    private static BASE64Encoder encoder = new BASE64Encoder();

    /**
     * base编码
     *
     * @param in
     *         输入字符串
     * @return 编码后字符串
     */
    public static String encode(String in) {

        if (in == null) {
            return null;
        }

        if (in.length() == 0) {
            return "";
        }

        return encoder.encode(in.getBytes());
    }

    /**
     * base解码
     *
     * @param in
     *         输入字符串
     * @return 解码后字符串
     */
    public static String decode(String in) throws IOException {

        if (in == null) {
            return null;
        }

        if (in.length() == 0) {
            return "";
        }

        return new String(decoder.decodeBuffer(in));
    }
}