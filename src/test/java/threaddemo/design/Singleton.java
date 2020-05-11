package threaddemo.design;

public class Singleton {
    private static volatile Singleton INSTANCE = null;
    public static Singleton getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    // 1.申请内存
                    // 2.赋值
                    // 3.
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}
