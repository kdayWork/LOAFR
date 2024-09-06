package org.example.interactive.filter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The IDFilter class filters a given list of entries by the ID column and low/high range values.
 *
 * DESIGN TRACEABILITY: Subset/Extension of Section 4.2.7
 */
public class IDFilter implements ILogFilter {
    public JSONArray filter(JSONArray entries, Object rangeLow, Object rangeHigh) throws JSONException {
        // Create a new JSONArray to hold the filtered entries
        JSONArray filteredEntries = new JSONArray();
        // Cast the range objects to an Integer because id is an int
        Integer rangeLowInt = new Integer(rangeLow.toString());
        Integer rangeHighInt = new Integer(rangeHigh.toString());
        // Loop thru all entries
        for(int i = 0; i < entries.length(); i++) {
            // Get entry
            JSONObject entry = new JSONObject(entries.get(i).toString());
            // Check if id within range, and add entry to filteredEntries
            int id = entry.getInt("id");
            if(rangeLowInt <= id && id <= rangeHighInt) {
                filteredEntries.put(entry);
            }
        }
        return filteredEntries;
    }
}
