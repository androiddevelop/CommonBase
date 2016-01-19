package me.codeboy.common.base.task;


import me.codeboy.common.base.task.impl.CBITask;
import me.codeboy.common.base.task.listener.CBTaskListener;

/**
 * 任务
 * Created by YD on 1/18/16.
 */
abstract public class CBTask extends Thread implements CBITask {
    private volatile boolean stopSign = false;
    private volatile boolean finished = false;
    private CBTaskListener listener;


    @Override
    public void startTask() {
        if (listener != null) {
            listener.onTaskStart();
        }
        this.start();
    }

    @Override
    public void stopTask() {
        stopSign = true;
        interrupt();
    }

    @Override
    public boolean isTaskFinished() {
        return finished;
    }

    @Override
    public void setTaskListener(CBTaskListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        task(stopSign);
        finished = true;
        if (listener != null) {
            listener.onTaskEnd();
        }
    }

    /**
     * 任务
     *
     * @param stopSign 结束标识符,任务执行流程应依赖于该标识,如果为true,代表任务需要被结束
     */
    public abstract void task(boolean stopSign);
}
