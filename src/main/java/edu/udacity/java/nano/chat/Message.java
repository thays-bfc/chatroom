package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * WebSocket message model
 */
public class Message {
    @JSONField(name = "username")
    private String name;
    @JSONField(name = "msg")
    private String message;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "onlineCount")
    private String onlineCount;

    public Message(){}

    public Message(String name, String message, String type, String onlineCount) {
        this.name = name;
        this.message = message;
        this.type = type;
        this.onlineCount = onlineCount;
    }

    public String jsonConverter(String name, String message, String type, String onlineCount){
        return JSON.toJSONString(new Message(name, message, type, onlineCount) );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }
}
