package vn.starshopvn.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint("/ws/chat")
public class ChatWebSocket {

    private static Set<Session> clients = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
        System.out.println("New connection: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = mapper.readValue(message, Map.class); // { to, message }

        String toUserId = data.get("to");
        String msg = data.get("message");

        // Tìm session của người nhận
        for (Session client : clients) {
            if (toUserId.equals(client.getUserProperties().get("userid"))) {
                client.getBasicRemote().sendText(mapper.writeValueAsString(Map.of("from", session.getUserProperties().get("userid"), "message", msg)));
                break;
            }
        }
    }


    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
        System.out.println("Connection closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Error: " + throwable.getMessage());
    }
}
