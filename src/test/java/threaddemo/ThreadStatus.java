package threaddemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStatus {
    private static Object lock = new Object();
    public static void main(String[] args) {
        System.out.println("haha");
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("继续执行");
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("继续执行");
            }
        }, "t2");
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            lock.notifyAll();
            log.info("继续执行");
        }

    }
}
