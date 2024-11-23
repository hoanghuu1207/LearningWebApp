package ServerEndpoint;

import dao.impl.NotificationDAO;
import model.ClassMessageModel;
import model.NotificationModel;
import model.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.impl.ClassMemberService;

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

@ServerEndpoint("/student_remove_class/{userID}")
public class StudentRemoveClassEndpoint {
    private static Map<String, Set<Session>> userSessions = new ConcurrentHashMap<>();
    private ClassMemberService classMemberService = new ClassMemberService();

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
    public void onMessage(String message, Session session) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(message);
        int classID = Integer.parseInt(jsonObj.get("classID").toString());
        int studentID = Integer.parseInt(jsonObj.get("studentID").toString());

        classMemberService.deleteStudentFromClassRoleStudent(studentID, classID);
    }

    public static void removeStudentFromClass(String userID, String classID) {
        Set<Session> sessions = userSessions.get(userID);
        if (sessions != null) {
            for (Session session : sessions) {
                if (session.isOpen()) {
                    JSONObject obj = new JSONObject();
                    obj.put("classID", classID);

                    session.getAsyncRemote().sendText(obj.toJSONString());
                }
            }
        }
    }
}
