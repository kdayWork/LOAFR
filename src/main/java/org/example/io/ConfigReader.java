package org.example.io;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

// Elem. 4.2.12
public class ConfigReader {
    /**
     * Given a routine name (String) return the JSONObject where the 'routineName' key
     * matches the target string. If it does not exist, return null.
     *
     * @param routineName the target routine name to find
     * @return JSONObject if found, null if not found
     */
    public static JSONObject fetchRoutine(String routineName) {
        String path = "src/main/java/org/example/config.json";
        File file = new File(path);
        try {
            // Parse string contents from the JSON file
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            // The JSON file is an array of JSON Objects
            JSONArray routines = new JSONArray(content);
            // Loop through the routines to find the matching routine
            for (int i = 0; i < routines.length(); i++) {
                JSONObject routine = new JSONObject(routines.get(i).toString());
                String name = routine.getString("routineName");
                if(name.equals(routineName)) {
                    return routine;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        // Return null if not found
        return null;
    }
}

