package org.example;

import org.example.interactive.sort.EntryTypeSort;
import org.example.io.ConfigReader;
import org.example.io.FileHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.example.interactive.sort.LogSort;

public class Main {
    public static void main(String[] args) throws JSONException {
        testLogSort();
    }

    public static void printEntries(JSONArray entries) throws JSONException {
        System.out.println("ID   Timestamp");
        for(int i = 0; i < entries.length(); i++) {
            JSONObject entry = new JSONObject(entries.get(i).toString());
            System.out.println(entry.toString());
        }
    }

    public static void testLogSort() throws JSONException {
        JSONArray entries = FileHandler.readFile("src/main/java/org/example/file.txt");
        System.out.println("Regular:");
        printEntries(entries);

        LogSort sortStrategy = new EntryTypeSort();
        entries = sortStrategy.sort(entries);
        System.out.println("\nSorted:");
        printEntries(entries);
    }

    public static void testFetchRoutine() {
        JSONObject routine = ConfigReader.fetchRoutine("1");

        if(routine != null) {
            System.out.println(routine.toString());
        }
        else {
            System.out.println("Could not find routine");
        }
    }
}