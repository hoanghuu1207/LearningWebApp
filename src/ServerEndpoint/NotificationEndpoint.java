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
    public void onOpen(Session session, @PathParam("classID") String classID) {
        userSessions.computeIfAbsent(classID, k -> ConcurrentHashMap.newKeySet()).add(session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("classID") String classID) {
        Set<Session> sessions = userSessions.get(classID);
        if (sessions != null) {
            sessions.remove(session);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
    }

    public static void sendMessageToUser(String url, NotificationModel notificationModel) {
//        Set<Session> sessions = userSessions.get(classID);
//        if (sessions != null) {
//            for (Session session : sessions) {
//                if (session.isOpen()) {
//                    JSONObject obj = new JSONObject();
//                    obj.put("messageID", classMessageModel.getMessageID());
//                    obj.put("classroomID", classMessageModel.getClassroomID());
//                    obj.put("userID", classMessageModel.getUserID());
//                    obj.put("content", classMessageModel.getContent());
//                    obj.put("parentMessageID", classMessageModel.getParentMessageID());
//                    obj.put("senderName", classMessageModel.getSenderName());
//
//
//                    String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(classMessageModel.getCreatedAt());
//
//                    obj.put("createdAt", timeStamp);
//
//                    session.getAsyncRemote().sendText(obj.toJSONString());
//                }
//            }
//        }
    }
}
