package com.l;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        tpt.start();
        Thread.sleep(3500);
        log.debug("停止监控");
        tpt.stop();

    }

    //监控线程
    private Thread monitorThread;
    //停止标记
    private volatile boolean stop = false;
    //是否执行过start方法
    private boolean starting = false;


    public void start() {
        synchronized (this) {
            if (starting) {
                return;
            }
            starting = true;
        }
        monitorThread = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                //是否被打断
                if (stop) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    //因为sleep出现异常后，会清除打印标记
                    //需要重新打断标记
                    current.interrupt();
                }
            }
        }, "monitor");
        monitorThread.start();
    }


    public void stop() {
        stop = true;
        monitorThread.interrupt();
    }

}
