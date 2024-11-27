package ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.AssignmentsModel;
import model.SubmissionsModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.impl.AssignmentService;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/class_assignment/{userID}")
public class ClassAssignmentEndpoint {
    private static Map<String, Set<Session>> userSessions = new ConcurrentHashMap<>();
    private AssignmentService assignmentService = new AssignmentService();

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
        int userID = Integer.parseInt(jsonObj.get("userID").toString());

        assignmentService.changeAssignmentLoop(userID, classID);
    }

    public static void sendAssignment(String userID, ArrayList<AssignmentsModel> notSubmitted, Map<AssignmentsModel, SubmissionsModel> submitted, ArrayList<AssignmentsModel> overdue) {
        Set<Session> sessions = userSessions.get(userID);
        Gson gson = new GsonBuilder().create();
        if (sessions != null) {
            for (Session session : sessions) {
                if (session.isOpen()) {
                    JSONObject obj = new JSONObject();
                    obj.put("notSubmitteds", gson.toJson(notSubmitted));
                    List<Map.Entry<AssignmentsModel, SubmissionsModel>> entries = new ArrayList<>(submitted.entrySet());
                    String json = gson.toJson(entries);
                    obj.put("submittedes", json);
                    obj.put("overdues", gson.toJson(overdue));

                    session.getAsyncRemote().sendText(obj.toJSONString());
                }
            }
        }
    }
}
