package ServerEndpoint;

import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/notificationEndpoint")
public class NotificationWebSocket {
    private static Set<Session> studentsSessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        studentsSessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        studentsSessions.remove(session);
    }

    public static void sendNotification(String message) {
        for (Session session : studentsSessions) {
            System.out.println("alo");
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(message);
            }
        }
    }
}
