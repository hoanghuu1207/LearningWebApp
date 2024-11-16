package controller.clients;
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

    // Danh sách các session kết nối WebSocket
    //private static Set<Session> clientSessions = new HashSet<>();
    //private static Map<String, Session> clientMap = new HashMap<>();
    // Khi kết nối WebSocket được mở
    private static Map<String, Set<Session>> meetingSessions = new HashMap<>();  // Map meetingId -> Set<Session>

    @OnOpen
    public void onOpen(@PathParam("meetingId") String meetingId, Session session) throws IOException {
        // Thêm session vào meeting room theo meetingId
        meetingSessions.computeIfAbsent(meetingId, k -> new HashSet<>()).add(session);
        System.out.println("New connection in meeting " + meetingId + ": " + session.getId());

        // Thông báo cho các client khác về thành viên mới
        broadcastMessage(meetingId, "newParticipant", session.getId(), "User " + session.getId());

        // Gửi danh sách các thành viên hiện có cho thành viên mới tham gia
        sendCurrentParticipants(meetingId, session);
    }

    // Khi nhận được tin nhắn từ client
    @OnMessage
    public void onMessage(@PathParam("meetingId") String meetingId, String message, Session session) throws IOException {
        System.out.println("Received message from " + session.getId() + " in meeting " + meetingId + ": " + message);

        String messageType = parseMessageType(message);
        if ("offer".equals(messageType) || "answer".equals(messageType) || "candidate".equals(messageType)) {
            relayMessageToPeer(meetingId, message, session);
        } else {
            // Gửi tin nhắn tới tất cả các client trong phòng hiện tại
            for (Session clientSession : meetingSessions.get(meetingId)) {
                if (clientSession.isOpen()) {
                    clientSession.getBasicRemote().sendText("{\"type\":\"message\", \"sender\":\"User " + session.getId() + "\", \"message\":\"" + message + "\"}");
                }
            }
        }
    }

    // Khi kết nối WebSocket đóng
    @OnClose
    public void onClose(@PathParam("meetingId") String meetingId, Session session) throws IOException {
        meetingSessions.get(meetingId).remove(session);
        System.out.println("Connection closed in meeting " + meetingId + ": " + session.getId());

        // Thông báo cho tất cả các client khác trong cùng phòng về thành viên đã rời đi
        broadcastMessage(meetingId, "participantLeft", session.getId(), "User " + session.getId());
    }

    // Hàm gửi danh sách thành viên hiện có cho thành viên mới
    private void sendCurrentParticipants(String meetingId, Session newSession) throws IOException {
        for (Session session : meetingSessions.get(meetingId)) {
            if (!session.getId().equals(newSession.getId()) && session.isOpen()) {
                newSession.getBasicRemote().sendText("{\"type\":\"newParticipant\", \"id\":\"" + session.getId() + "\", \"name\":\"User " + session.getId() + "\"}");
            }
        }
    }

    // Phát thông điệp tới tất cả các client trong phòng
    private void broadcastMessage(String meetingId, String type, String id, String name) throws IOException {
        String message = "{\"type\":\"" + type + "\", \"id\":\"" + id + "\", \"name\":\"" + name + "\"}";
        for (Session session : meetingSessions.get(meetingId)) {
            if (session.isOpen()) {
                session.getBasicRemote().sendText(message);
            }
        }
    }


    // Chuyển tiếp tin nhắn từ peer tới các client khác trong phòng
    private void relayMessageToPeer(String meetingId, String message, Session senderSession) throws IOException {
        for (Session session : meetingSessions.get(meetingId)) {
            if (session.isOpen() && !session.getId().equals(senderSession.getId())) {
                session.getBasicRemote().sendText(message);
            }
        }
    }

    private String parseMessageType(String message) {
        // Phân tích kiểu tin nhắn
        if (message.contains("\"type\":\"offer\"")) return "offer";
        if (message.contains("\"type\":\"answer\"")) return "answer";
        if (message.contains("\"type\":\"candidate\"")) return "candidate";
        return "unknown";
    }
}
