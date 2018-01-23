package me.codeboy.common.base.task;

import me.codeboy.common.base.task.impl.ICBTask;
import me.codeboy.common.base.task.listener.CBTaskListener;

import java.util.ArrayList;
import java.util.List;
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
    private LinkedBlockingQueue<ICBTask> queue = new LinkedBlockingQueue<ICBTask>(); //任务队列
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
    public void addTask(CBTask task) {
        queue.offer(task);
    }

    /**
     * 开始执行
     */
    public void startAllTasks() {
        while (queue.size() != 0 && !stopSign) {
            ICBTask task = queue.poll();
            if (task == null) {
                continue;
            }
            setAndStartTask(task);
        }
    }

    /**
     * 开始执行并且等待结束
     * @throws InterruptedException exception
     */
    public void startAllTasksAndWaiting() throws InterruptedException {
        List<ICBTask> taskList = new ArrayList<>();
        while (queue.size() != 0 && !stopSign) {
            ICBTask task = queue.poll();
            if (task == null) {
                continue;
            }
            taskList.add(task);
            setAndStartTask(task);
        }
        for(ICBTask task:taskList){
            if(!task.isTaskFinished()){
                task.waitForEnd();
            }
        }
    }

    /**
     * 设置任务的监听器并开始任务
     * @param task 任务
     */
    private void setAndStartTask(ICBTask task){
        task.setTaskListener(new CBTaskListener() {
            @Override
            public void onTaskStart() {
                lock.lock();
                if (currentExecuteTaskNumber > maxTaskNumber) {
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

    /**
     * 停止任务
     */
    public void stopAllTasks() {
        ICBTask task;
        stopSign = true;
        while ((task = queue.poll()) != null) {
            task.stopTask();
        }
    }
}
