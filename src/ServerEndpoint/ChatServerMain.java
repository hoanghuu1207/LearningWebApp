package ServerEndpoint;

import service.impl.UserService;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.websocket.*;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

@ServerEndpoint("/meet/{meetingId}")
public class ChatServerMain {
    private static final Map<String, Set<Session>> meetingSessions = new HashMap<>();
    private static final Map<Session, String> sessionUserMap = new HashMap<>();
    private static final Map<String, Session> userSessions = new HashMap<>();
    private static volatile String currentScreenSharer = null;
    private final UserService userService = new UserService();

    @OnOpen
    public void onOpen(@PathParam("meetingId") String meetingId, Session session) throws IOException {
        // Thêm session vào meeting room theo meetingId
        meetingSessions.computeIfAbsent(meetingId, k -> new HashSet<>()).add(session);
        System.out.println("New connection in meeting " + meetingId + ": " + session.getId());
    }

    // Khi nhận được tin nhắn từ client
    @OnMessage
    public void onMessage(@PathParam("meetingId") String meetingId, String message, Session session) throws IOException {
        System.out.println("Received message from " + session.getId() + " in meeting " + meetingId + ": " + message);
        JSONObject json = new JSONObject(message);
        String type = json.getString("type");
        String to = json.optString("id", null);
        String userId = sessionUserMap.get(session);
        json.put("from", userId);
        String updatedMessage = json.toString();

        switch (type) {
            case "register":
                handleRegister(meetingId, json, session);
                break;
            case "message":
                handleChatMessage(meetingId, json, session);
                break;
            case "offer":
            case "answer":
            case "candidate":
//                if (to != null && userSessions.containsKey(to)) {
//                    sendMessage(userSessions.get(to), updatedMessage);
//                } else {
//                    broadcast(meetingId,updatedMessage, session);
//                }
                broadcast(meetingId,updatedMessage, session);
                break;
            case "broadcast":
                handleScreenShare(meetingId, json, session);
                break;
            default:
                System.out.println("Unknown message type: " + type);
        }
    }

    // Khi kết nối WebSocket đóng
    @OnClose
    public void onClose(@PathParam("meetingId") String meetingId, Session session) throws IOException {
        Set<Session> sessions = meetingSessions.get(meetingId);
        if (sessions != null) {
            sessions.remove(session);
            System.out.println("Connection closed in meeting " + meetingId + ": " + session.getId());
            String userId = sessionUserMap.remove(session);
            userSessions.remove(userId);
            String userName = userService.getUserNameById(Integer.parseInt(userId));
            broadcastMessage(meetingId, "participantLeft", userId, userName,session);
            if (sessions.isEmpty()) meetingSessions.remove(meetingId);
        }

        if (session.getId().equals(currentScreenSharer)) {
            currentScreenSharer = null;
            broadcastScreenShareStatus(meetingId, null, session);
        }
    }
    private void handleRegister(String meetingId, JSONObject json, Session session) throws IOException {
        String userId = json.getString("userId");
        sessionUserMap.put(session, userId);
        userSessions.put(userId, session);
        String userName = userService.getUserNameById(Integer.parseInt(userId));

        broadcastMessage(meetingId, "newParticipant", userId, userName, session);
        sendExistingParticipants(meetingId, session);
        if (currentScreenSharer != null) {
            sendMessage(session, "{\"type\": \"screen_share\", \"id\": \"" + currentScreenSharer + "\"}");
        }
    }
    private void handleChatMessage(String meetingId, JSONObject json, Session session) throws IOException {
        String senderId = sessionUserMap.get(session);
        String userName = userService.getUserNameById(Integer.parseInt(senderId));
        String chatMessage = json.getString("message");

        for (Session clientSession : meetingSessions.get(meetingId)) {
            if (clientSession.isOpen()) {
                sendMessage(clientSession, String.format(
                        "{\"type\":\"message\", \"sender\":\"%s\", \"message\":\"%s\"}",
                        userName, chatMessage));
            }
        }
    }
    private void handleScreenShare(String meetingId, JSONObject json, Session session) throws IOException {
        String userId = sessionUserMap.get(session); // Retrieve the userId for the session
        if (userId == null) {
            System.err.println("Unable to find userId for session: " + session.getId());
            return;
        }
        boolean isSharing = json.getBoolean("isSharing");
        currentScreenSharer = isSharing ? userId : null; // Set currentScreenSharer to userId
        broadcastScreenShareStatus(meetingId, currentScreenSharer, session);
    }
    private void broadcastScreenShareStatus(String meetingId, String screenSharerId, Session sender) throws IOException {
        for (Session session : meetingSessions.get(meetingId)) {
            if (session.isOpen() && !session.equals(sender)) {
                sendMessage(session, String.format("{\"type\": \"screen_share\", \"id\": \"%s\"}", screenSharerId));
            }
        }
    }

    // Hàm gửi danh sách thành viên hiện có cho thành viên mới
    private void sendExistingParticipants(String meetingId, Session session) throws IOException {
        Set<Session> sessions = meetingSessions.get(meetingId);
        for (Session s : sessions) {
            if (!s.equals(session)) {
                String userId = sessionUserMap.get(s);
                String userName = userService.getUserNameById(Integer.parseInt(userId));
                sendMessage(session, String.format("{\"type\": \"newParticipant\", \"id\": \"%s\", \"name\": \"%s\"}", userId, userName));
            }
        }
    }

    private void broadcastMessage(String meetingId, String type, String userId, String content, Session s) throws IOException {
        for (Session session : meetingSessions.get(meetingId)) {
            if (session.isOpen() && !s.equals(session)) {
                sendMessage(session, String.format("{\"type\": \"%s\", \"id\": \"%s\", \"name\": \"%s\"}", type, userId, content));
            }
        }
    }

    private void sendMessage(Session session, String message) throws IOException {
        if (session.isOpen()) {
            synchronized (session) {
                session.getBasicRemote().sendText(message);
            }
        }
    }
    private void broadcast(String meetingId, String message, Session sender) {
        Set<Session> sessions = meetingSessions.get(meetingId); // Get sessions for the specific meetingId
        if (sessions != null) {
            for (Session session : sessions) {
                if (!session.equals(sender) && session.isOpen()) {
                    try {
                        sendMessage(session, message);
                    } catch (IOException e) {
                        System.err.println("Error broadcasting message to session " + session.getId() + ": " + e.getMessage());
                    }
                }
            }
        }
    }

}
