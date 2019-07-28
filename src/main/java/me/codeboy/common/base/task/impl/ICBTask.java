package me.codeboy.common.base.task.impl;

import me.codeboy.common.base.task.listener.CBTaskListener;

/**
 * 任务接口
 * Created by yuedong.li  on 1/18/16.
 */
public interface ICBTask {
    void startTask();  //启动任务

    void stopTask(); //停止任务

    boolean isTaskFinished(); //是否结束

    void setTaskListener(CBTaskListener listener);

    void waitForEnd()throws InterruptedException ; //等待结束
}