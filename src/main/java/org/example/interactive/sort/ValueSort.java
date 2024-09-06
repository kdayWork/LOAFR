package org.example.interactive.sort;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * The ValueSort class sorts a list of given entries on the value column.
 *
 * DESIGN TRACEABILITY: Subset/Extension of Section 4.2.4
 */
public class ValueSort extends LogSort {
    /**
     * Sort a given log file (JSONArray) by its entry's value.
     *
     * @param entries the JSONArray of log entries of JSONObject(s)
     * @return a JSONArray of value sorted entries
     */
    public JSONArray sort(JSONArray entries) throws JSONException {
        // If log file length < 100, sort using insertion sort
        if(entries.length() < 1)
            return super.insertionSort(entries, "value", SORT_TYPE_DOUBLE);
            // Otherwise, sort using merge sort
        else
            return super.mergeSort(entries, "value", SORT_TYPE_DOUBLE);
    }
}
