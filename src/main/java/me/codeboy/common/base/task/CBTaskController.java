package me.codeboy.common.base.task;

import me.codeboy.common.base.task.impl.CBTask;
import me.codeboy.common.base.task.listener.CBTaskListener;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 任务控制器
 * Created by YD on 1/18/16.
 */
public class CBTaskController {
    private int maxTaskNumber = 5; //最大任务数目
    private int currentExecuteTaskNumber = 0; //当前执行任务数量
    private LinkedBlockingQueue<CBTask> queue = new LinkedBlockingQueue<CBTask>(); //任务队列
    private boolean stopSign = false; //停止信号
    final Lock lock = new ReentrantLock();//锁对象
    final Condition condition = lock.newCondition();//条件

    public CBTaskController() {
    }

    public CBTaskController(int maxTaskNumber) {
        this.maxTaskNumber = maxTaskNumber;
    }

    /**
     * 添加任务
     *
     * @param task 任务
     */
    public void addTask(CBThreadTask task) {
        queue.offer(task);
    }

    /**
     * 开始执行
     */
    public void startAllTasks() {
        while (queue.size() != 0 || !stopSign) {
            CBTask task = queue.poll();
            if (task == null) {
                return;
            }
            task.setTaskListener(new CBTaskListener() {
                @Override
                public void onTaskStart() {
                    lock.lock();
                    if (currentExecuteTaskNumber >= maxTaskNumber) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    currentExecuteTaskNumber++;
                    lock.unlock();
                }

                @Override
                public void onTaskEnd() {
                    lock.lock();
                    currentExecuteTaskNumber--;
                    condition.signal();
                    lock.unlock();
                }
            });

            task.startTask();
        }
    }

    /**
     * 停止任务
     */
    public void stopAllTasks() {
        CBTask task;
        stopSign = true;
        while ((task = queue.poll()) != null) {
            task.stopTask();
        }
    }
}
