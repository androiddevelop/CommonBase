package me.codeboy.common.base.example;

import me.codeboy.common.base.log.CBPrint;
import me.codeboy.common.base.net.CBHttp;
import me.codeboy.common.base.net.CBHttps;
import me.codeboy.common.base.security.CBBase64;
import me.codeboy.common.base.task.CBTaskController;
import me.codeboy.common.base.task.CBTask;
import me.codeboy.common.base.time.CBTimer;
import me.codeboy.common.base.util.CBVersionUtil;

import java.io.IOException;


/**
 * 测试
 */
public class TestExample {

    public static void main(String[] args) {
//        new TestExample().testBase64();
//        new TestExample().testHttps();
//        new TestExample().testHttp();
//        new TestExample().testVersionUtil();
//        new TestExample().testTask();
        new TestExample().testTimer();
    }

    /**
     * base64编码
     */
    public void testBase64() {
        String str = "小胖轩(codeboy.me)";
        String tmp = CBBase64.encode(str);
        CBPrint.print(tmp);
        try {
            CBPrint.print(CBBase64.decode(tmp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试https
     */
    public void testHttps() {
        String url = "https://kyfw.12306.cn/otn/leftTicket/init";
        try {
            CBPrint.print(CBHttps.getInstance().connect(url).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试http
     */
    public void testHttp() {
        String url = "http://codeboy.me";
        try {
            String abc = CBHttp.getInstance().connect(url).execute();
            CBPrint.print(abc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 版本比较
     */
    public void testVersionUtil() {
        CBPrint.print(CBVersionUtil.HasNewVersion("1.1.2", "1.2.1"));
        CBPrint.print(CBVersionUtil.HasNewVersion("1.1.2", "1.1.2"));
        CBPrint.print(CBVersionUtil.HasNewVersion("1.1.2", "1.1.2.1"));
        CBPrint.print(CBVersionUtil.HasNewVersion("1.10.2", "1.2.1.1"));
        CBPrint.print(CBVersionUtil.HasNewVersion("1.10.2", "1.1.1"));
    }

    /**
     * 多任务测试
     */
    public void testTask() {
        CBTaskController controller = new CBTaskController(10);
        for (int i = 0; i < 100; i++) {
            final int index = i;
            controller.addTask(new CBTask() {
                @Override
                public void task(boolean stopSign) {
                    try {
                        sleep((int) (Math.random() * 10000 % 1000));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CBPrint.print(index);
                }
            });
        }
        controller.startAllTasks();
    }

    /**
     * 计时器测试
     */
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