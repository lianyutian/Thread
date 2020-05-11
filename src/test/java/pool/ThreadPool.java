package pool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPool {
    // 任务队列
    private BlockingQueue<Runnable> taskQueue;

    // 线程集合
    private HashSet<Worker> workers = new HashSet();

    // 核心线程数
    private int coreSize;

    // 获取任务的超时时间
    private long timeout;

    private TimeUnit timeUnit;

    // 容量
    private int capcity;

    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize) {
        this.coreSize = coreSize;
        this.taskQueue = new BlockingQueue<>(coreSize);
    }

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int capcity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(capcity);
        this.rejectPolicy = rejectPolicy;
    }

    // 执行任务
    public void execute(Runnable task) {
        // 当任务数没有超过 coresize 时， 直接交给worker对象执行
        // 如果任务数超过 coresize, 加入任务队列暂存
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增 worker{}, {}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
                log.debug("加入任务队列 {}", task);
                // taskQueue.put(task);

                taskQueue.tryPut(rejectPolicy, task);
                // 1. 死等
                // 2. 带超时等待
                // 3. 让调用者放弃任务执行
                // 4. 让调用者跑出异常
                // 5. 让调用者自己执行任务
            }
        }
    }


    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    log.debug("正在执行 {}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
        }
    }
}


