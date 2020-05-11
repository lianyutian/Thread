package threaddemo.design;

import lombok.Getter;

/**
 * 消息类
 */
@Getter
public final class Message {
    private int id;
    private String message;

    public Message(int id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
