package me.codeboy.common.base.util;

/**
 * 时效锁,在指定的时间内,自动解锁
 * Created by yuedong.li on 5/4/16.
 */
public class CBTimeLock {
    private int TIME = 3000; //默认时间锁时长,单位毫秒
    private boolean locked = false; //是否上锁
    private final static int CHECK_INTERVAL = 1000; //检查时间间隔
    
    public CBTimeLock() {

    }

    public CBTimeLock(int time) {
        this.TIME = time;
    }

    /**
     * 加锁
     */
    public void lock() {
        getLock(false, TIME);
    }

    /**
     * 加锁
     *
     * @param time time
     */
    public void lock(int time) {
        getLock(false, time);
    }

    /**
     * 非时间锁,需要等待unlock
     */
    public void lockForever() {
        getLock(true, 0);
    }

    /**
     * 解锁
     */
    public void unlock() {
        locked = false;
    }

    /**
     * 是否处于锁定状态
     *
     * @return 锁的状态
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * thread sleep
     *
     * @param seconds seconds
     */
    private void threadSleep(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * get lock
     *
     * @param forever is forever lock or not a time lock
     * @param time    time
     */
    private synchronized void getLock(boolean forever, final int time) {
        //wait until unlocked
        while (locked) {
            threadSleep(CHECK_INTERVAL);
        }

        locked = true;

        if (!forever) {
            new Thread() {
                @Override
                public void run() {
                    threadSleep(time);
                    locked = false;
                }
            }.start();
        }
    }

}
