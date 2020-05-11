package threaddemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test1 {
    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify(1, 5);
        new Thread(() -> {
            wn.print("a", 1, 2);
        }, "t1").start();
        new Thread(() -> {
            wn.print("b", 2, 3);
        }, "t2").start();
        new Thread(() -> {
            wn.print("c", 3, 1);
        }, "t3").start();
    }
}
@Slf4j
class WaitNotify {
    private int flag;

    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String str, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
