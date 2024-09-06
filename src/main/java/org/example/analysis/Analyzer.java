package org.example.analysis;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.lang.Math;

public class Analyzer {
    private JSONObject routine;
    private JSONArray logfile;

    public Analyzer(JSONObject routine, JSONArray logfile){
        this.routine = routine;
        this.logfile = logfile;
    }

    public JSONArray analyze() throws JSONException {
        JSONObject analysis = routine.getJSONObject("analysis");
        JSONArray result = logfile;

        JSONArray types = analysis.getJSONArray("type");
        for (int i = 0; i < types.length(); i++) {
            String type = types.getString(i);
            System.out.println(type);
            if (type.equals("greaterThan")) {
                double value = analysis.getDouble("value");
                result = greaterThan(result, value);
            } else if (type.equals("lessThan")) {
                double value = analysis.getDouble("value");
                result = lessThan(result, value);
            } else if (type.equals("equals")) {
                double value = analysis.getDouble("value");
                result = equals(result, value);
            } else if (type.equals("existsComponent")) {
                String component = analysis.getString("component_name");
                result = existsComponent(result, component);
            } else if (type.equals("existsField")) {
                String field = analysis.getString("field");
                result = existsField(result, field);
            } else if (type.equals("existsType")) {
                String eventType = routine.getString("eventName");
                result = existsType(result, eventType);
            } else if (type.equals("time")) {
                int timediff = analysis.getInt("timediff");
                String type1 = analysis.getString("type1");
                String type2 = analysis.getString("type2");
                result = time(result, timediff, type1, type2);
            }
        }

        return result;
    }

    public JSONArray greaterThan(JSONArray entries, double value) throws JSONException {
        JSONArray res = new JSONArray();
        for (int i = 0; i < entries.length(); i++) {
            JSONObject entry = entries.getJSONObject(i);
            if (entry.getDouble("value") > value) {
                res.put(entry);
            }
        }
        return res;
    }

    public JSONArray lessThan(JSONArray entries, double value) throws JSONException {
        JSONArray res = new JSONArray();
        for (int i = 0; i < entries.length(); i++) {
            JSONObject entry = entries.getJSONObject(i);
            if (entry.getDouble("value") < value) {
                res.put(entry);
            }
        }
        return res;
    }

    public JSONArray equals(JSONArray entries, double value) throws JSONException {
        JSONArray res = new JSONArray();
        for (int i = 0; i < entries.length(); i++) {
            JSONObject entry = entries.getJSONObject(i);
            if (Math.abs(entry.getDouble("value") - value) < 0.0001) {
                res.put(entry);
            }
        }
        return res;
    }

    public JSONArray existsComponent(JSONArray entries, String component) throws JSONException {
        JSONArray res = new JSONArray();
        for (int i = 0; i < entries.length(); i++) {
            JSONObject entry = entries.getJSONObject(i);
            if (entry.getString("component").equals(component)) {
                res.put(entry);
            }
        }
        return res;
    }

    public JSONArray existsField(JSONArray entries, String field) throws JSONException {
        JSONArray res = new JSONArray();
        for (int i = 0; i < entries.length(); i++) {
            JSONObject entry = entries.getJSONObject(i);
            if (entry.getString("field").equals(field)) {
                res.put(entry);
            }
        }
        return res;
    }

    public JSONArray existsType(JSONArray entries, String eventType) throws JSONException {
        JSONArray res = new JSONArray();
        for (int i = 0; i < entries.length(); i++) {
            JSONObject entry = entries.getJSONObject(i);
            if (entry.getString("entryType").equals(eventType)) {
                res.put(entry);
            }
        }
        return res;
    }

    public JSONArray time(JSONArray entries, int timediff, String type1, String type2) throws JSONException {
        JSONArray res = new JSONArray();
        JSONArray type1arr = existsType(entries, type1);
        JSONArray type2arr = existsType(entries, type2);

        for (int i = 0; i < type1arr.length(); i++) {
            JSONObject entry1 = type1arr.getJSONObject(i);
            int t1 = entry1.getInt("timestamp");
            boolean valid = true;
            for (int j = 0; j < type2arr.length(); j++) {
                JSONObject entry2 = type2arr.getJSONObject(j);
                int t2 = entry2.getInt("timestamp");
                if (t2 <= t1) {
                    continue;
                }
                if (t1 + timediff >= t2) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                res.put(entry1);
            }
        }

        return res;
    }
}
