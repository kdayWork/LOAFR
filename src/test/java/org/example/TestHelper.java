package org.example;

import org.json.JSONException;
import org.json.JSONObject;

public class TestHelper {

    public static JSONObject generateLogEntry(int id, int timestamp, String entryType, String component, String field, double value) throws JSONException {
        String jsonString = "{ \"id\": " + id +
                ", \"timestamp\": " + timestamp +
                ", \"entryType\": \"" + entryType +
                "\", \"component\": \"" + component +
                "\", \"field\": \"" + field +
                "\", \"value\": " + value + ", }";

        return new JSONObject(jsonString);
    }

}
