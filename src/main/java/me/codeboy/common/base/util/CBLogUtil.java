package me.codeboy.common.base.util;

/**
 * 日志工具类
 *
 * @author Yuedong Li
 */
public class CBLogUtil {
    private static boolean ONOFF = false; //默认不打印

    /**
     * 打印日志
     *
     * @param info 要打印的内容
     */
    public static void log(Object info) {
        if (ONOFF) {
            System.out.println("===> " + info);
        }
    }

    /**
     * 设置开关
     *
     * @param onOff
     */
    public static void setOnOff(boolean onOff) {
        ONOFF = onOff;
    }

    /**
     * 打印
     *
     * @param obj
     */
    public static void print(Object obj) {
        System.out.println(obj);
    }
}