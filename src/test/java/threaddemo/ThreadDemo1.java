package threaddemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadDemo1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("t1 running...");
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    log.error("t1 is interrupted");
                }
            }
        });

        t1.start();
        log.info("t1 start interrupted..");
        t1.interrupt();
    }
}
