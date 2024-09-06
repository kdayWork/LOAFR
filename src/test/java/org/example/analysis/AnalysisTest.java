package org.example.analysis;

import org.example.TestHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class AnalysisTest {

    private static JSONObject createConfig() throws JSONException {
        JSONObject routine = new JSONObject();
        routine.put("routineName", "1");
        routine.put("eventName", "A");
        JSONObject analysis = new JSONObject();
        routine.put("analysis", analysis);
        return routine;
    }

    @Test
    void greaterThan() throws JSONException {
        JSONArray entries = new JSONArray();
        JSONObject routine = createConfig();

        for(int i=0; i<3; i++){
            entries.put(TestHelper.generateLogEntry(i, i, "A", "A", "A", i));
        }

        routine.getJSONObject("analysis").put("component_name", "A");
        routine.getJSONObject("analysis").put("field", "A");
        routine.getJSONObject("analysis").put("value", 0);
        JSONArray type = new JSONArray();
        type.put("greaterThan");
        routine.getJSONObject("analysis").put("type", type);

        Analyzer analyzer = new Analyzer(routine, entries);

        JSONArray analyzed = analyzer.analyze();

        Assertions.assertEquals(2, analyzed.length());
        Assertions.assertTrue(((JSONObject)entries.get(1)).getInt("id") == ((JSONObject)analyzed.get(0)).getInt("id"));
        Assertions.assertFalse(((JSONObject)entries.get(2)).getInt("id") == ((JSONObject)analyzed.get(0)).getInt("id"));
        Assertions.assertTrue(((JSONObject)entries.get(2)).getInt("id") == ((JSONObject)analyzed.get(1)).getInt("id"));

    }

    @Test
    void lessThan() throws JSONException {
        JSONArray entries = new JSONArray();
        JSONObject routine = createConfig();

        for(int i=0; i<3; i++){
            entries.put(TestHelper.generateLogEntry(i, i, "A", "A", "A", i));
        }

        routine.getJSONObject("analysis").put("component_name", "A");
        routine.getJSONObject("analysis").put("field", "A");
        routine.getJSONObject("analysis").put("value", 2);
        JSONArray type = new JSONArray();
        type.put("lessThan");
        routine.getJSONObject("analysis").put("type", type);

        Analyzer analyzer = new Analyzer(routine, entries);

        JSONArray analyzed = analyzer.analyze();

        Assertions.assertEquals(2, analyzed.length());
        Assertions.assertTrue(((JSONObject)entries.get(0)).getInt("id") == ((JSONObject)analyzed.get(0)).getInt("id"));
        Assertions.assertFalse(((JSONObject)entries.get(1)).getInt("id") == ((JSONObject)analyzed.get(0)).getInt("id"));
        Assertions.assertTrue(((JSONObject)entries.get(1)).getInt("id") == ((JSONObject)analyzed.get(1)).getInt("id"));

    }

    @Test
    void equals() throws JSONException {
        JSONArray entries = new JSONArray();
        JSONObject routine = createConfig();

        for(int i=0; i<3; i++){
            entries.put(TestHelper.generateLogEntry(i, i, "A", "A", "A", i));
        }

        routine.getJSONObject("analysis").put("component_name", "A");
        routine.getJSONObject("analysis").put("field", "A");
        routine.getJSONObject("analysis").put("value", 2);
        JSONArray type = new JSONArray();
        type.put("equals");
        routine.getJSONObject("analysis").put("type", type);

        Analyzer analyzer = new Analyzer(routine, entries);
        JSONArray analyzed = analyzer.analyze();

        Assertions.assertEquals(1, analyzed.length());
        Assertions.assertEquals(2, ((JSONObject)analyzed.get(0)).getInt("id"));

        routine.getJSONObject("analysis").put("value", 3);
        analyzer = new Analyzer(routine, entries);
        analyzed = analyzer.analyze();

        Assertions.assertEquals(0, analyzed.length());


    }

    @Test
    void existsComponent() throws JSONException {
        JSONArray entries = new JSONArray();
        JSONObject routine = createConfig();

        for(int i=0; i<3; i++){
            entries.put(TestHelper.generateLogEntry(i, i, "A", "A", "A", i));
        }
        entries.put(TestHelper.generateLogEntry(4,4,"A","B","A",4));

        routine.getJSONObject("analysis").put("component_name", "A");
        routine.getJSONObject("analysis").put("field", "A");
        routine.getJSONObject("analysis").put("value", 2);
        JSONArray type = new JSONArray();
        type.put("existsComponent");
        routine.getJSONObject("analysis").put("type", type);

        Analyzer analyzer = new Analyzer(routine, entries);
        JSONArray analyzed = analyzer.analyze();

        Assertions.assertEquals(4, entries.length());
        Assertions.assertEquals(3, analyzed.length());
        Assertions.assertFalse( ((JSONObject)analyzed.get(2)).getInt("id") == 4);

        routine.getJSONObject("analysis").put("component_name", "B");
        analyzer = new Analyzer(routine, entries);
        analyzed = analyzer.analyze();

        Assertions.assertEquals(1, analyzed.length());
        Assertions.assertEquals(((JSONObject)analyzed.get(0)).getInt("id"), 4);
    }

    @Test
    void existsField() throws JSONException {
        JSONArray entries = new JSONArray();
        JSONObject routine = createConfig();

        for(int i=0; i<3; i++){
            entries.put(TestHelper.generateLogEntry(i, i, "A", "A", "A", i));
        }
        entries.put(TestHelper.generateLogEntry(4,4,"A","A","B",4));

        routine.getJSONObject("analysis").put("component_name", "A");
        routine.getJSONObject("analysis").put("field", "A");
        routine.getJSONObject("analysis").put("value", 2);
        JSONArray type = new JSONArray();
        type.put("existsField");
        routine.getJSONObject("analysis").put("type", type);

        Analyzer analyzer = new Analyzer(routine, entries);
        JSONArray analyzed = analyzer.analyze();

        Assertions.assertEquals(4, entries.length());
        Assertions.assertEquals(3, analyzed.length());
        Assertions.assertFalse( ((JSONObject)analyzed.get(2)).getInt("id") == 4);

        routine.getJSONObject("analysis").put("field", "B");
        analyzer = new Analyzer(routine, entries);
        analyzed = analyzer.analyze();

        Assertions.assertEquals(1, analyzed.length());
        Assertions.assertEquals(((JSONObject)analyzed.get(0)).getInt("id"), 4);
    }

    @Test
    void existsType() throws JSONException {
        JSONArray entries = new JSONArray();
        JSONObject routine = createConfig();

        for(int i=0; i<3; i++){
            entries.put(TestHelper.generateLogEntry(i, i, "A", "A", "A", i));
        }
        entries.put(TestHelper.generateLogEntry(4,4,"B","A","A",4));

        routine.getJSONObject("analysis").put("entryType", "A");
        routine.getJSONObject("analysis").put("field", "A");
        routine.getJSONObject("analysis").put("value", 2);
        JSONArray type = new JSONArray();
        type.put("existsType");
        routine.getJSONObject("analysis").put("type", type);

        Analyzer analyzer = new Analyzer(routine, entries);
        JSONArray analyzed = analyzer.analyze();

        Assertions.assertEquals(4, entries.length());
        Assertions.assertEquals(3, analyzed.length());
        Assertions.assertFalse( ((JSONObject)analyzed.get(2)).getInt("id") == 4);

        routine.put("eventName", "B");
        analyzer = new Analyzer(routine, entries);
        analyzed = analyzer.analyze();

        Assertions.assertEquals(1, analyzed.length());
        Assertions.assertEquals(((JSONObject)analyzed.get(0)).getInt("id"), 4);
    }

    @Test
    void time() throws JSONException {
        JSONArray entries = new JSONArray();
        JSONObject routine = createConfig();

        entries.put(TestHelper.generateLogEntry(1, 1, "A", "A", "A", 1));
        entries.put(TestHelper.generateLogEntry(2, 1, "A", "A", "A", 1));
        entries.put(TestHelper.generateLogEntry(3, 3, "A", "A", "A", 1));

        entries.put(TestHelper.generateLogEntry(4, 4, "B", "A", "A", 1));

        JSONArray type = new JSONArray();
        type.put("time");
        routine.getJSONObject("analysis").put("type", type);
        routine.getJSONObject("analysis").put("timediff", 2);
        routine.getJSONObject("analysis").put("type1", "A");
        routine.getJSONObject("analysis").put("type2", "B");

        Analyzer analyzer = new Analyzer(routine, entries);
        JSONArray analyzed = analyzer.analyze();

        Assertions.assertEquals(2, analyzed.length());
        Assertions.assertEquals(1, ((JSONObject)analyzed.get(0)).getInt("id"));
        Assertions.assertEquals(2, ((JSONObject)analyzed.get(1)).getInt("id"));

        entries.put(TestHelper.generateLogEntry(5, 3, "B", "A", "A", 1));

        analyzer = new Analyzer(routine, entries);
        analyzed = analyzer.analyze();

        Assertions.assertEquals(0, analyzed.length());


    }
}
