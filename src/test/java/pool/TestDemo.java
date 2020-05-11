package pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestDemo {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(2, 1000, TimeUnit.MILLISECONDS, 10, (queue, task) -> {
            queue.put(task);
        });
        for (int i = 0; i < 15; i++) {
            pool.execute(() -> {
                int sum = 0;
                for (int j = 0; j < 1000; j++) {
                    sum += j;
                }
                try {
                    Thread.sleep(100000000000000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug(String.valueOf(sum));
            });
        }
    }
}
