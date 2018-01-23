package me.codeboy.common.base.example;

import me.codeboy.common.base.log.CBPrint;
import me.codeboy.common.base.time.CBTimer;
import me.codeboy.common.base.util.CBVersionUtil;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 计时器测试
 * Created by yuedong.li on 21/01/2018.
 */
public class TimerTest {

    @Test
    public void testTimer() {
        CBTimer timer = new CBTimer();
        timer.push();
        timer.push("total_time");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long gap = timer.pop();
        CBPrint.println("time gap1: " + gap + " ms");
        timer.push();
        long sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        gap = timer.pop();
        long totalTime = timer.pop("total_time");

        CBPrint.println("time gap2: " + gap + " ms");
        CBPrint.println("total time: " + totalTime + " ms");
    }

}
