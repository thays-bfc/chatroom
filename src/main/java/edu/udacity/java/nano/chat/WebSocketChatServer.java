package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import javafx.beans.property.MapPropertyBase;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat/{username}")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String message){
            // for each onlineSession session,sendMessage(message.toJason())
            for (Map.Entry<String, Session> sessions : onlineSessions.entrySet()) {
                try {
                sessions.getValue().getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
         onlineSessions.put(session.getId(),session);
        Message message = new Message();
        sendMessageToAll(message.jsonConverter("", "", "ENTER",
                         Integer.toString(onlineSessions.size())));
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        Message message = JSON.parseObject(jsonStr, Message.class);
        sendMessageToAll(message.jsonConverter(message.getName(), message.getMessage(), "SPEAK",
                Integer.toString(onlineSessions.size())));
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        if (!onlineSessions.isEmpty()){
            onlineSessions.remove(session.getId(), session);
            Message message = new Message();
            sendMessageToAll(message.jsonConverter("", "", "LEAVE",
                    Integer.toString(onlineSessions.size())));
        }

    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {

        error.printStackTrace();
    }

}
