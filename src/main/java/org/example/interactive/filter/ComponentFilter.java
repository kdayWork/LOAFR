package org.example.interactive.filter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The ComponentFilter class filters a given list of entries by the component column and low/high range values.
 *
 * DESIGN TRACEABILITY: Subset/Extension of Section 4.2.7
 */
public class ComponentFilter implements ILogFilter {
    public JSONArray filter(JSONArray entries, Object rangeLow, Object rangeHigh) throws JSONException {
        // Create a new JSONArray to hold the filtered entries
        JSONArray filteredEntries = new JSONArray();
        // Cast the range objects to a String because component is a String
        String rangeLowString = new String(rangeLow.toString());
        String rangeHighString = new String(rangeHigh.toString());
        // Loop thru all entries
        for(int i = 0; i < entries.length(); i++) {
            // Get entry
            JSONObject entry = new JSONObject(entries.get(i).toString());
            // Check if component within range, and add entry to filteredEntries
            String component = entry.getString("component");
            if(rangeLowString.compareTo(component) <= 0 && rangeHighString.compareTo(component) >= 0) {
                filteredEntries.put(entry);
            }
        }
        return filteredEntries;
    }
}
