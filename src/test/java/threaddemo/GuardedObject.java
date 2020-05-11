package threaddemo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 保护性暂停模式
 */
@Slf4j
@Data
public class GuardedObject {
    /**
     *
     */
    private long id;

    /**
     * 结果
     */
    private Object response;

    public GuardedObject(long id) {
        this.id = id;
    }

    /**
     * 获取结果
     */
    public Object getResponse(long timeOut) {
        synchronized (this) {
            // 开始时间
            long beginTime = System.currentTimeMillis();
            // 经历时间
            long passTime = 0L;
            while (response == null) {
                try {
                    log.info("等待资源下载。。");
                    // 等待时间
                    long waitTime = timeOut - passTime;
                    if(waitTime <= 0) {
                        break;
                    }
                    this.wait(waitTime);
                    // 经历时间
                    passTime = System.currentTimeMillis() - beginTime;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public void setResponse(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }

}
