package org.example.interactive.filter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The ValueFilter class filters a given list of entries by the value column and low/high range values.
 *
 * DESIGN TRACEABILITY: Subset/Extension of Section 4.2.7
 */
public class ValueFilter implements ILogFilter {
    public JSONArray filter(JSONArray entries, Object rangeLow, Object rangeHigh) throws JSONException {
        // Create a new JSONArray to hold the filtered entries
        JSONArray filteredEntries = new JSONArray();
        // Cast the range objects to a Double because value is a Double
        Double rangeLowDouble = new Double(rangeLow.toString());
        Double rangeHighDouble = new Double(rangeHigh.toString());
        // Loop thru all entries
        for(int i = 0; i < entries.length(); i++) {
            // Get entry
            JSONObject entry = new JSONObject(entries.get(i).toString());
            // Check if value within range, and add entry to filteredEntries
            Double value = entry.getDouble("value");
            if(rangeLowDouble <= value && value <= rangeHighDouble) {
                filteredEntries.put(entry);
            }
        }
        return filteredEntries;
    }
}
