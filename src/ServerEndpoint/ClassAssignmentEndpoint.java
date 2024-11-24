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
import javax.websocket.server.ServerEndpoint;
import java.util.*;

@ServerEndpoint("/class_assignment")
public class ClassAssignmentEndpoint {
    private static Set<Session> studentsSessions = Collections.synchronizedSet(new HashSet<>());
    private AssignmentService assignmentService = new AssignmentService();

    @OnOpen
    public void onOpen(Session session) {
        studentsSessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        studentsSessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(message);
        int classID = Integer.parseInt(jsonObj.get("classID").toString());
        int userID = Integer.parseInt(jsonObj.get("userID").toString());

        assignmentService.changeAssignmentLoop(userID, classID);
    }

    public static void sendAssignment(ArrayList<AssignmentsModel> notSubmitted, Map<AssignmentsModel, SubmissionsModel> submitted, ArrayList<AssignmentsModel> overdue) {
        Gson gson = new GsonBuilder().create();
        for (Session session : studentsSessions) {
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
