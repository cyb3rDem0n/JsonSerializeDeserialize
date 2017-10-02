import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;

/**
 * Serialize a Contermeasure from a JSON response file
 * The JSON response file contain a collection of  countermeasures
 *
 * @author Giuseppe D'Agostino
 */

public class Countermeasure {


    private String mitigation;
    private String application;
    private String note;
    private String userName;
    private String validationNote;
    private String probabilityControlId;
    private String counterMeasureId;
    private String userId;

    public Countermeasure() {
    }

    public Countermeasure(String mitigation, String application, String note, String userName, String validationNote, String probabilityControlId, String counterMeasureId, String userId) {
        this.mitigation = mitigation;
        this.application = application;
        this.note = note;
        this.userName = userName;
        this.validationNote = validationNote;
        this.probabilityControlId = probabilityControlId;
        this.counterMeasureId = counterMeasureId;
        this.userId = userId;
    }

    public String getMitigation() {
        return mitigation;
    }

    public void setMitigation(String mitigation) {
        this.mitigation = mitigation;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getValidationNote() {
        return validationNote;
    }

    public void setValidationNote(String validationNote) {
        this.validationNote = validationNote;
    }

    public String getProbabilityControlId() {
        return probabilityControlId;
    }

    public void setProbabilityControlId(String probabilityControlId) {
        this.probabilityControlId = probabilityControlId;
    }

    public String getCounterMeasureId() {
        return counterMeasureId;
    }

    public void setCounterMeasureId(String counterMeasureId) {
        this.counterMeasureId = counterMeasureId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Countermeasure: " +
                "\n ==>> mitigation=" + getMitigation() +
                "\n ==>> application=" + getApplication() +
                "\n ==>> note='" + getNote() +
                "\n ==>> userName='" + getUserName() +
                "\n ==>> validationNote='" + getValidationNote() +
                "\n ==>> probabilityControlId='" + getProbabilityControlId() +
                "\n ==>> counterMeasureId='" + getCounterMeasureId() +
                "\n ==>> userId='" + userId + " \n";
    }

    final static String path = "C://Users//Giuseppe D'Agostino//eclipse-workspace//testingDataGenerator//jsonResponse.txt";

    /**
     * DESERIALIZE
     * Take json from gate request, scan for desired element, edit them
     * and try to do more stuff
     *
     * @return HashMap<Integer, Countermeasure>
     */
    @org.jetbrains.annotations.Nullable
    public static HashMap<Integer, Countermeasure> jsonManage() {
        HashMap<Integer, Countermeasure> map = new HashMap<Integer, Countermeasure>();

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // as deep as possible
        JSONObject jLevel0 = (JSONObject) jsonObject.get("body"); // body
        JSONObject jLevel1 = (JSONObject) jLevel0.get("bodyAnswers"); // bodyAnswers
        JSONArray jLevel2_Array = (JSONArray) jLevel1.get("threatResponses"); // threatResponses
        int next = 0;

        if (jLevel2_Array.size() == 0) {
            System.err.print("EMPTY JSON OBJECT");
        } else
            for (int i = 0; i < jLevel2_Array.size(); i++) {
                JSONObject allCountermeasures = (JSONObject) jLevel2_Array.get(i);
                for (int j = 0; j < allCountermeasures.size(); j++) {
                    JSONArray jCountermeasuresArray = (JSONArray) allCountermeasures.get("countermeasures");
                    for (int k = 0; k < jCountermeasuresArray.size(); k++) {
                        JSONObject jCountermeasures = (JSONObject) jCountermeasuresArray.get(k);

                        Countermeasure countermeasure = new Countermeasure(); // my model

                        countermeasure.setMitigation(jCountermeasures.get("mitigation").toString());
                        countermeasure.setApplication(jCountermeasures.get("application").toString());
                        countermeasure.setNote((String) jCountermeasures.get("note"));
                        countermeasure.setUserName((String) jCountermeasures.get("userName"));
                        countermeasure.setValidationNote((String) jCountermeasures.get("validationNote"));
                        countermeasure.setProbabilityControlId((String) jCountermeasures.get("probabilityControlId"));
                        countermeasure.setCounterMeasureId((String) jCountermeasures.get("counterMeasureId"));
                        countermeasure.setUserId((String) jCountermeasures.get("userId"));

                        //System.out.printf("N: " + k + " " +  countermeasure);


                        map.put(k, countermeasure);
                    }
                }
            }
        return map;
    }
}
