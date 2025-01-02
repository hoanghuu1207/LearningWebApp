package ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.AssignmentsModel;
import model.SubmissionUI;
import model.SubmissionsModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.impl.AssignmentService;
import service.impl.SubmissionService;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/teacher_assignment/{userID}")
public class TeacherAssignmentEndpoint {
    private static Map<String, Set<Session>> userSessions = new ConcurrentHashMap<>();
    private SubmissionService submissionService = new SubmissionService();

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
        int assignmentID = Integer.parseInt(jsonObj.get("assignmentID").toString());
        int userID = Integer.parseInt(jsonObj.get("userID").toString());

        System.out.println(userID + " " + assignmentID);

        submissionService.getSubmissionAssignment(userID, assignmentID);
    }

    public static void sendSubmission(String userID, ArrayList<SubmissionUI> submissionUIs) {
        Set<Session> sessions = userSessions.get(userID);
        Gson gson = new GsonBuilder().create();
        if (sessions != null) {
            for (Session session : sessions) {
                if (session.isOpen()) {
                    JSONObject obj = new JSONObject();
                    obj.put("submissions", gson.toJson(submissionUIs));

                    session.getAsyncRemote().sendText(obj.toJSONString());
                }
            }
        }
    }
}
