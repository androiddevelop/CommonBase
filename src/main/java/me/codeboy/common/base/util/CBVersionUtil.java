package me.codeboy.common.base.util;

/**
 * 版本比较
 * Created by yuedong on 11/21/15.
 */
public class CBVersionUtil {
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
        if (CBStringUtil.isEmptyOrNull(oldVersion) || CBStringUtil.isEmptyOrNull(newVersion)) {
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
}
