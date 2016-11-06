package me.codeboy.common.base.time;

/**
 * 时间等待
 * Created by yuedong.li on 5/4/16.
 */
public class CBTimeWait {
    /**
     * 等待
     *
     * @param millis time
     */
    public static void waitTime(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待
     *
     * @param millis millis
     * @param nanos  nanos
     */
    public static void waitTime(long millis, int nanos) {
        try {
            Thread.sleep(millis, nanos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
