package me.codeboy.common.base.time;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 计时器,用于简单记录时间间隔,例如代码执行时间
 * Created by yuedong.li on 06/11/2016.
 */
public class CBTimer {
    private Map<String, Long> times = new HashMap<>();
    private final static String TIME_KEY = "CB_TIMER_%d"; //内置计时前缀
    private long count = 0;

    /**
     * 记录当前时间
     */
    public synchronized void push() {
        times.put(String.format(Locale.CHINA, TIME_KEY, ++count), System.currentTimeMillis());
    }

    /**
     * 将当前时间与key进行关联,以便后续可以根据key获取push时间
     * <p>key值不要以CB_TIMER开头,CB_TIMER为系统内置计时key,以CB_TIMER开头的key将自动被过滤掉</p>
     *
     * @param key key
     */
    public void push(String key) {
        push(key, System.currentTimeMillis());
    }

    /**
     * 将指定时间与key进行关联
     * <p>key值不要以CB_TIMER开头,CB_TIMER为系统内置计时key,以CB_TIMER开头的key将自动被过滤掉</p>
     *
     * @param key key
     */
    public void push(String key, long time) {
        if (key.startsWith("CB_TIMER")) {
            return;
        }
        times.put(key, time);
    }

    /**
     * 获取最后一个push到当前的时间
     * {@link CBTimer#push()}
     *
     * @return 相应时间
     */
    public long pop() {
        String key = String.format(Locale.CHINA, TIME_KEY, count);
        return pop(key);
    }

    /**
     * 获取最后一个push key到当前的时间
     * {@link CBTimer#push(String)}
     *
     * @param key key
     * @return 相应时间
     */
    public long pop(String key) {
        return pop(key, System.currentTimeMillis());
    }

    /**
     * 获取最后一个push key到指定时间之间的时间
     * {@link CBTimer#push(String)}
     *
     * @param key key
     * @return 相应时间
     */
    public long pop(String key, long time) {
        if (!times.containsKey(key)) {
            return -1L;
        }
        long recordTime = times.get(key);
        times.remove(key);
        return time - recordTime;
    }

    /**
     * 是个有key的相关记录值
     *
     * @param key key
     * @return 是否含有
     */
    public boolean containsKey(String key) {
        return times.containsKey(key);
    }

    /**
     * 获取指定key对应的时间,如果key不存在,返回null
     *
     * @param key key
     * @return 相应时间
     */
    public Long get(String key) {
        return times.get(key);
    }

    /**
     * 获取最后一次push的时间
     *
     * @return 时间, 单位毫秒
     */
    public Long get() {
        return times.get(String.format(Locale.CHINA, TIME_KEY, count));
    }
}
