package com.concurrency.concurrency.atomic;


import com.concurrency.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 对数据进行原子性的操作改变
 */
@Slf4j
@ThreadSafe
public class AtomicExample2 {

    //更新指定类的字段
    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample2.class,"count" );

    @Getter
    public volatile int count = 100;

    public static AtomicExample2 atomicExample2 = new AtomicExample2();

    public static void main(String[] args) {
        if (updater.compareAndSet(atomicExample2, 100, 150)) {
            log.info("update success 1,{}", atomicExample2.getCount());
        }
        if (updater.compareAndSet(atomicExample2, 100, 150)) {
            log.info("update success 2,{}", atomicExample2.getCount());
        } else {
            log.info("update failed, {}", atomicExample2.getCount());
        }
    }

}
