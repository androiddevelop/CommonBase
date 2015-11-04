package me.codeboy.common.base.util;

/**
 * 字符串工具
 *
 * @author Yuedong Li
 */
public class CBStringUtil {

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


    /**
     * 按照ascii(字典)顺序比较字符大小
     *
     * @param str1
     *         字符串1
     * @param str2
     *         字符串2
     * @return 0-相等 正数-大于 负数-小于
     */
    public static int compare(String str1, String str2) {
        //有一个为null的话按照非null的大处理
        if (str1 == null || str2 == null) {
            return Integer.compare((str1 == null) ? 0 : 1, (str2 == null) ? 0 : 1);
        }

        if (str1.length() == 0 || str2.length() == 0) {
            return str1.length() - str2.length();
        }

        int res = 0;
        int position = 0;
        int maxComparePosition = Math.min(str1.length(), str2.length());
        while (position < maxComparePosition) {
            char ch1 = str1.charAt(position);
            char ch2 = str2.charAt(position);
            if (ch1 == ch2) {
                position++;
                continue;
            }

            //不相等的话返回差值,代表字符串大小
            res = ch1 - ch2;
            break;
        }

        //如果最后比较结果是相等的话,说明比较长度内的字符串相等,需要比较字符串长度
        return res == 0 ? (str1.length() - str2.length()) : res;
    }


    /**
     * 本号比较(例如1.10.0与1.2.0版本比较),返回是否有新版本
     *
     * @param oldVersion
     *         旧版本
     * @param newVersion
     *         新版本
     * @return 是否有新的版本存在(是否新版本版本号大于老的版本号)
     */
    public static boolean versionCompareAndHasANewVersion(String oldVersion, String newVersion) {
        //版本号有一个不正常就返回false
        if (isEmptyOrNull(oldVersion) || isEmptyOrNull(newVersion)) {
            return false;
        }

        boolean res = false;
        String[] oldVersionSnippet = oldVersion.split("\\.");
        String[] newVersionSnippet = newVersion.split("\\.");
        int maxComparePosition = Math.min(oldVersionSnippet.length, newVersionSnippet.length);

        int i = 0;
        for (; i < maxComparePosition; i++) {
            int newNumber = Integer.parseInt(newVersionSnippet[i]);
            int oldNumber = Integer.parseInt(oldVersionSnippet[i]);

            if (newNumber == oldNumber) {
                continue;
            }

            if (newNumber > oldNumber) {
                res = true;
                break;
            }

            if (newNumber < oldNumber) {
                res = false;
                break;
            }
        }

        //前面的都相等,比较长度
        if (i == maxComparePosition) {
            res = newVersionSnippet.length > oldVersionSnippet.length;
        }

        return res;
    }


    /**
     * 含子串是否超过一定次数
     *
     * @param srcString
     *         原串
     * @param desString
     *         目标子串
     * @param number
     *         次数
     * @return 是否
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
     * @return 是否
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
}