package me.codeboy.common.base.example;

import me.codeboy.common.base.task.CBTask;
import me.codeboy.common.base.task.CBTaskController;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * 计时器测试
 * Created by yuedong.li on 21/01/2018.
 */
public class TaskTest {

    /**
     * 同步操作，等待线程结束后继续执行。
     * @throws InterruptedException exception
     */
    @Test
    public void testTaskSync() throws InterruptedException {
        Set<Integer> set = new HashSet<>();
        Set<Integer> syncSet = Collections.synchronizedSet(set);
        CBTaskController controller = new CBTaskController(10);
        sameOperate(controller,syncSet);
        controller.startAllTasksAndWaiting();
        assertEquals(100, syncSet.size());
    }

    /**
     * 异步执行，不负责线程最后的状态
     * @throws InterruptedException
     */
    @Test
    public void testTaskAsync() throws InterruptedException {
        Set<Integer> set = new HashSet<>();
        Set<Integer> asyncSet = Collections.synchronizedSet(set);
        CBTaskController controller = new CBTaskController(10);
        sameOperate(controller,asyncSet);
        controller.startAllTasks();
        //等待5s，测试结果
        Thread.sleep(3000);
        assertEquals(100, asyncSet.size());
    }

    /**
     * 相同操作
     * @param controller 控制器
     * @param set set
     */
    private void sameOperate(CBTaskController controller, final Set set){
        for (int i = 0; i < 100; i++) {
            final int index = i;
            controller.addTask(new CBTask() {
                @Override
                public void task(boolean stopSign) {
                    try {
                        sleep((int) (Math.random() * 1000 % 100));
                    } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                    }
                    set.add(index);
                }
            });
        }
    }
}
