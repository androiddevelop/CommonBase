package me.codeboy.common.base.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密
 *
 * @author YD
 */
public class CBMd5Sha {
    private static MessageDigest md1; // md5加密
    private static MessageDigest md2; // sha加密

    static {
        try {
            md1 = MessageDigest.getInstance("MD5");
            md2 = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Md5加密
     *
     * @param text
     *         输入字符串
     * @return md5编码
     */
    public static String md5(String text) {
        if (text == null || text.length() == 0)
            return null;
        try {
            md1.update(text.getBytes("UTF8"));
            String pwdAfter = hex(md1.digest());
            return pwdAfter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * sha加密 此处对text进行反向，对结果再次反向
     *
     * @param text
     *         输入字符串
     * @return sha加密后的字符串
     */
    public static String sha(String text) {
        if (text == null || text.length() == 0)
            return null;
        try {
            md2.update(text.getBytes("UTF8"));
            String pwdAfter = hex(md2.digest());
            return pwdAfter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字节转换为16进制
     *
     * @param arr
     *         字节数组
     * @return 16进制字符串
     */
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
                    3));
        }
        return sb.toString();
    }
}