package org.example.interactive.filter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The TimestampFilter class filters a given list of entries by the timestamp column and low/high range values.
 *
 * DESIGN TRACEABILITY: Section 4.2.6
 */
public class TimestampFilter implements ILogFilter {
    public JSONArray filter(JSONArray entries, Object rangeLow, Object rangeHigh) throws JSONException {
        // Create a new JSONArray to hold the filtered entries
        JSONArray filteredEntries = new JSONArray();
        // Cast the range objects to an Integer because timestamp is an int
        Integer rangeLowInt = new Integer(rangeLow.toString());
        Integer rangeHighInt = new Integer(rangeHigh.toString());
        // Loop thru all entries
        for(int i = 0; i < entries.length(); i++) {
            // Get entry
            JSONObject entry = new JSONObject(entries.get(i).toString());
            // Check if timestamp within range, and add entry to filteredEntries
            int timestamp = entry.getInt("timestamp");
            if(rangeLowInt <= timestamp && timestamp <= rangeHighInt) {
                filteredEntries.put(entry);
            }
        }
        return filteredEntries;
    }
}
