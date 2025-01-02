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

@ServerEndpoint("/chat/{meetingId}")
public class ChatServer {
    UserService userService = new UserService();
    // Danh sách các session kết nối WebSocket
    //private static Set<Session> clientSessions = new HashSet<>();
    //private static Map<String, Session> clientMap = new HashMap<>();
    // Khi kết nối WebSocket được mở
    private static final Map<String, Set<Session>> meetingSessions = new HashMap<>();  // Map meetingId -> Set<Session>
    private static final Map<Session, String> sessionUserMap = new HashMap<>();
    @OnOpen
    public void onOpen(@PathParam("meetingId") String meetingId, Session session) throws IOException {
        // Thêm session vào meeting room theo meetingId
        meetingSessions.computeIfAbsent(meetingId, k -> new HashSet<>()).add(session);
        System.out.println("New connection in meeting " + meetingId + ": " + session.getId());

        // Thông báo cho các client khác về thành viên mới
        //broadcastMessage(meetingId, "newParticipant", session.getId(), "User " + session.getId());

        // Gửi danh sách các thành viên hiện có cho thành viên mới tham gia
        //sendCurrentParticipants(meetingId, session);
    }

    // Khi nhận được tin nhắn từ client
    @OnMessage
    public void onMessage(@PathParam("meetingId") String meetingId, String message, Session session) throws IOException {
        System.out.println("Received message from " + session.getId() + " in meeting " + meetingId + ": " + message);
        Map<String, String> messageData = parseMessage(message);
        if (messageData == null) {
            System.err.println("Invalid message format: " + message);
            return;
        }
        String type = messageData.get("type");
        if ("register".equals(type)) {
            handleRegister(meetingId, messageData, session);
        } else if ("message".equals(type)) {
            String senderId = sessionUserMap.get(session);
            String userName = userService.getUserNameById(Integer.parseInt(senderId));
            for (Session clientSession : meetingSessions.get(meetingId)) {
                if (clientSession.isOpen()) {
                    String message1 = messageData.get("message");
                    clientSession.getBasicRemote().sendText("{\"type\":\"message\", \"sender\":\"" + userName + "\", \"message\":\"" + message1 + "\"}");
                }
            }
        } else {
            for (Session clientSession : meetingSessions.get(meetingId)) {
                if (!clientSession.getId().equals(session.getId()) && clientSession.isOpen()) {
                    clientSession.getBasicRemote().sendText(message);
                }
            }
        }
            // Gửi tin nhắn tới tất cả các client trong phòng hiện tại
//            for (Session clientSession : meetingSessions.get(meetingId)) {
//                if (clientSession.isOpen()) {
//                    clientSession.getBasicRemote().sendText("{\"type\":\"message\", \"sender\":\"User " + session.getId() + "\", \"message\":\"" + message + "\"}");
//                }
//            }

    }
    private void handleRegister(String meetingId, Map<String, String> messageData, Session session) throws IOException {
        String userId = messageData.get("userId");
        String userName = userService.getUserNameById(Integer.parseInt(userId));
        sessionUserMap.put(session, userId);

        // Thông báo cho các thành viên khác
        broadcastMessage(meetingId, "newParticipant", userId, userName);

        // Gửi danh sách các thành viên hiện tại
        sendCurrentParticipants(meetingId, session);
    }

    // Khi kết nối WebSocket đóng
    @OnClose
    public void onClose(@PathParam("meetingId") String meetingId, Session session) throws IOException {
        Set<Session> sessions = meetingSessions.get(meetingId);
        if (sessions != null) {
            sessions.remove(session);
            System.out.println("Connection closed in meeting " + meetingId + ": " + session.getId());
            String userId = sessionUserMap.remove(session);
            String userName = userService.getUserNameById(Integer.parseInt(userId));
            // Thông báo cho các client khác về thành viên đã rời đi
            broadcastMessage(meetingId, "participantLeft", userId, userName);

            // Nếu không còn session nào trong meeting, xóa meeting đó khỏi danh sách
            if (sessions.isEmpty()) {
                meetingSessions.remove(meetingId);
            }
        }
    }
    // Gửi tin nhắn tới tất cả các session trong meeting room
    private void broadcastMessage(String meetingId, String type, String senderId, String content) throws IOException {
        Set<Session> sessions = meetingSessions.get(meetingId);
        if (sessions != null) {
            for (Session s : sessions) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendText(
                            String.format("{\"type\": \"%s\", \"id\": \"%s\", \"name\": \"%s\"}", type, senderId, content)
                            //String message = "{\"type\":\"" + type + "\", \"id\":\"" + id + "\", \"name\":\"" + name + "\"}";
                    );
                }
            }
        }
    }

    // Gửi danh sách các thành viên hiện có cho thành viên mới tham gia
    private void sendCurrentParticipants(String meetingId, Session session) throws IOException {
        Set<Session> sessions = meetingSessions.get(meetingId);
        if (sessions != null) {
            for (Session s : sessions) {
                if (!s.equals(session)) {
                    String userId = sessionUserMap.get(s);
                    String userName = userService.getUserNameById(Integer.parseInt(userId));
                    session.getBasicRemote().sendText(
                            String.format("{\"type\": \"newParticipant\", \"id\": \"%s\", \"name\": \"%s\"}", userId, userName)
                    );
                }
            }
        }
    }
    private Map<String, String> parseMessage(String message) {
        try {
            // Tách tin nhắn dạng JSON thành key-value
            if (message.startsWith("{") && message.endsWith("}")) {
                message = message.substring(1, message.length() - 1); // Loại bỏ { và }
            }

            Map<String, String> messageData = new HashMap<>();
            String[] pairs = message.split(",");
            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().replace("\"", "");
                    String value = keyValue[1].trim().replace("\"", "");
                    messageData.put(key, value);
                }
            }
            return messageData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // Hàm gửi danh sách thành viên hiện có cho thành viên mới
//    private void sendCurrentParticipants(String meetingId, Session newSession) throws IOException {
//        for (Session session : meetingSessions.get(meetingId)) {
//            if (!session.getId().equals(newSession.getId()) && session.isOpen()) {
//                newSession.getBasicRemote().sendText("{\"type\":\"newParticipant\", \"id\":\"" + session.getId() + "\", \"name\":\"User " + session.getId() + "\"}");
//            }
//        }
//    }

    // Phát thông điệp tới tất cả các client trong phòng
//    private void broadcastMessage(String meetingId, String type, String id, String name) throws IOException {
//        String message = "{\"type\":\"" + type + "\", \"id\":\"" + id + "\", \"name\":\"" + name + "\"}";
//        for (Session session : meetingSessions.get(meetingId)) {
//            if (session.isOpen()) {
//                session.getBasicRemote().sendText(message);
//            }
//        }
//    }

}
