package threaddemo.design;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class MessageQueue {
    /**
     * 存放消息队列
     */
    private static LinkedList<Message> messageList = new LinkedList<>();

    /**
     * 消息队列容量
     */
    private int capcity;

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    /**
     * 获取消息
     *
     * @return
     */
    public Message take() {
        synchronized (messageList) {
            // 检查队列是否为空
            while (messageList.isEmpty()) {
                try {
                    log.info("消息队列为空。。");
                    messageList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 获取消息
            Message message = messageList.removeLast();
            log.info("已消费消息。。");
            // 通知生产者
            messageList.notifyAll();
            return message;
        }
    }

    /**
     * 生产消息
     */
    public void put(Message message) {
        synchronized (messageList) {
            // 检查消息队列是否已满
            while (messageList.size() == capcity) {
                try {
                    log.info("消息队列满了。。");
                    messageList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            messageList.addFirst(message);
            log.info("以生产消息。。");
            messageList.notifyAll();
        }
    }
}
