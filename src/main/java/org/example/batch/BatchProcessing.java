package org.example.batch;

import org.example.io.ConfigReader;
import org.example.io.FileHandler;
import org.example.analysis.Analyzer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Elem. 4.2.15, 5.7
public class BatchProcessing {

    /**
     * Uses the command line arguments to run an analysis, writes the results to a new file
     * @param args args[0] is the path to the logfile, args[1] is the name of the routine to be loaded
     */
    public static void main(String [] args) throws JSONException {
//        JSONArray logfile = FileHandler.readFile("src/main/java/org/example/file.txt");
        JSONArray logfile = FileHandler.readFile(args[0]);
//        JSONObject routine = ConfigReader.fetchRoutine("timeTest");
        JSONObject routine = ConfigReader.fetchRoutine(args[1]);

        Analyzer a = new Analyzer(routine, logfile);
        JSONArray analyzed = a.analyze();
        for(int i=0; i<analyzed.length(); i++){
            System.out.println(analyzed.get(i).toString());
        }
        FileHandler.writeFile(analyzed, args[0]);
    }
}
