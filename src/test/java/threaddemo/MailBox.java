package threaddemo;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MailBox {
    private static AtomicInteger ID = new AtomicInteger();
    private static Map<Integer, GuardedObject> BOXES = new Hashtable<>();

    public static GuardedObject createGuardedObject() {
        int id = initId();
        GuardedObject guardedObject = new GuardedObject(id);
        BOXES.put(id, guardedObject);
        return guardedObject;
    }

    public static void setGuardedObject() {
        int id = initId();
        GuardedObject guardedObject = new GuardedObject(id);
        Thread t = new Thread(() -> {

        });
        //t.join();
        BOXES.put(id, guardedObject);
    }

    private static int initId() {
        return ID.getAndIncrement();
    }
}
