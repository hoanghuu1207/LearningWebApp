package ServerEndpoint;

import model.ClassMessageModel;
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

@ServerEndpoint("/classMember/{classID}")
public class ClassMemberEndpoint {
    private static Map<String, Set<Session>> classSessions = new ConcurrentHashMap<>();
    private ClassMemberService classMemberService = new ClassMemberService();

    @OnOpen
    public void onOpen(Session session, @PathParam("classID") String classID) {
        classSessions.computeIfAbsent(classID, k -> ConcurrentHashMap.newKeySet()).add(session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("classID") String classID) {
        Set<Session> sessions = classSessions.get(classID);
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

        classMemberService.deleteStudentFromClass(studentID, classID);
    }

    public static void removeStudentFromClass(String classID, String studentID) {
        Set<Session> sessions = classSessions.get(classID);
        if (sessions != null) {
            for (Session session : sessions) {
                if (session.isOpen()) {
                    JSONObject obj = new JSONObject();
                    obj.put("studentID", studentID);

                    session.getAsyncRemote().sendText(obj.toJSONString());
                }
            }
        }
    }
}
