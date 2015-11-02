package me.codeboy.common.base.util;

/**
 * 字符串工具
 *
 * @author Yuedong Li
 */
public class CBStringUtil {

    /**
     * 含子串是否超过一定次数
     *
     * @param srcString
     *         原串
     * @param desString
     *         目标子串
     * @param number
     *         次数
     * @return 是否超过
     */
    public static boolean containGreaterThan(String srcString,
                                             String desString, int number) {
        boolean res = false;
        int totalNumber = containStatistics(srcString, desString);
        if (totalNumber > number)
            res = true;
        return res;
    }

    /**
     * 含子串的次数
     *
     * @param srcString
     *         原串
     * @param desString
     *         目标子串
     * @return 次数
     */
    public static int containStatistics(String srcString, String desString) {
        int length = desString.length();
        String newString = srcString.replace(desString, "");
        return (srcString.length() - newString.length()) / length;
    }

    /**
     * 含模式串是否超过一定次数
     *
     * @param srcString
     *         原串
     * @param desString
     *         目标模式串
     * @param number
     *         次数
     * @return 是否超过
     */
    public static boolean containRegexGreaterThan(String srcString,
                                                  String desString, int number) {
        boolean res = false;
        int totalNumber = containRegexStatistics(srcString, desString);
        if (totalNumber > number)
            res = true;
        return res;
    }

    /**
     * 含模式串的次数
     *
     * @param srcString
     *         原串
     * @param desString
     *         目标模式串
     * @return 次数
     */
    public static int containRegexStatistics(String srcString, String desString) {
        int number = 0;
        while (true) {
            String newString = srcString.replaceFirst(desString, "");
            if (newString.length() == srcString.length())
                break;
            number++;
            srcString = newString;
        }
        return number;
    }

    /**
     * 字符串是否为null或者长度为0
     *
     * @param in
     *         输入字符串
     * @return 是否为空或长度为0
     */
    public static boolean isEmptyOrNull(String in) {
        if (in == null || in.length() == 0) {
            return true;
        }
        return false;
    }
}
