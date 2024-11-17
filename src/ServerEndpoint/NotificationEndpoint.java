package ServerEndpoint;

import model.ClassMessageModel;
import model.NotificationModel;
import model.UserModel;
import org.json.simple.JSONObject;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/notification/{userID}")
public class NotificationEndpoint {
    private static Map<String, Set<Session>> userSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("userID") String userID) {
        userSessions.computeIfAbsent(userID, k -> ConcurrentHashMap.newKeySet()).add(session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("userID") String userID) {
        Set<Session> sessions = userSessions.get(userID);
        if (sessions != null) {
            sessions.remove(session);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
    }

    public static void sendNotificationToUser(String userID, String url, String content) {
        Set<Session> sessions = userSessions.get(userID);
        if (sessions != null) {
            for (Session session : sessions) {
                if (session.isOpen()) {
                    JSONObject obj = new JSONObject();
                    obj.put("userID", userID);
                    obj.put("url", url);
                    obj.put("content", content);

                    session.getAsyncRemote().sendText(obj.toJSONString());
                }
            }
        }
    }
}
