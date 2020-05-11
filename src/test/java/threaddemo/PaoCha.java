package threaddemo;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PaoCha {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                log.info("开始烧开水。。");
                TimeUnit.SECONDS.sleep(15);
                log.info("水烧开了。。");
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "shaoshui");

        Thread t2 = new Thread(() -> {
            try {
                log.info("开始准备茶叶等。。");
                TimeUnit.SECONDS.sleep(4);
                log.info("准备事项完毕。。");
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "zashi");
        t1.start();
        t2.start();
        t1.join();
        log.info("开始泡茶。。");
    }

}
