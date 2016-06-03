package me.codeboy.common.base.log;

/**
 * 日志工具
 *
 * @author Yuedong Li
 */
public class CBPrint {
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
     * @param onOff 日志开关
     */
    public static void setOnOff(boolean onOff) {
        ONOFF = onOff;
    }

    /**
     * 打印
     *
     * @param obj 打印对象
     */
    public static void print(Object obj) {
        System.out.print(obj);
    }

    /**
     * 打印
     *
     * @param obj 打印对象
     */
    public static void println(Object obj) {
        System.out.println(obj);
    }
}