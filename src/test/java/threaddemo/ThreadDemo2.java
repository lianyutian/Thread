package threaddemo;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class ThreadDemo2 {
    static int r = 0;
    public static void main(String[] args) throws InterruptedException {
        test();
    }

    private static void test() throws InterruptedException {
        Thread t = new Thread(() -> {
            log.debug("开始");
            try {
                sleep(5);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            log.debug("结束");
            r = 10;
        }, "test");
        t.start();
        t.join(2);
        log.debug("开始");
        log.info("r: {}", r);
    }
}
