package org.example.interactive.sort;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * The ComponentSort class sorts a given list of entries by the component column.
 *
 * DESIGN TRACEABILITY: Subset/Extension of Section 4.2.4
 */
public class ComponentSort extends LogSort {
    /**
     * Sort a given log file (JSONArray) by its entry's component.
     *
     * @param entries the JSONArray of log entries of JSONObject(s)
     * @return a JSONArray of component sorted entries
     */
    public JSONArray sort(JSONArray entries) throws JSONException {
        // If log file length < 100, sort using insertion sort
        if(entries.length() < 100)
            return super.insertionSort(entries, "component", LogSort.SORT_TYPE_STRING);
            // Otherwise, sort using merge sort
        else
            return super.mergeSort(entries, "component", LogSort.SORT_TYPE_STRING);
    }
}
