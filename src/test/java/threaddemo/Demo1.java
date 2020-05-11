package threaddemo;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo1 {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(5);
        System.out.println(i.updateAndGet(x -> x * 10));
        while (true) {
            int i1 = i.get();
            int next = i1 * 10;
            boolean b = i.compareAndSet(i1, next);
            if (b) {
                break;
            }
        }
    }
}
