package org.example.interactive.sort;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * The IDSort class sorts a list of given entries on the ID column.
 *
 * DESIGN TRACEABILITY: Section 4.2.3
 */
public class IDSort extends LogSort {
    /**
     * Sort a given log file (JSONArray) by its entry's IDs.
     *
     * @param entries the JSONArray of log entries of JSONObject(s)
     * @return a JSONArray of ID sorted entries
     */
    public JSONArray sort(JSONArray entries) throws JSONException {
        // If log file length < 100, sort using insertion sort
        if(entries.length() < 1)
            return super.insertionSort(entries, "id", SORT_TYPE_INTEGER);
        // Otherwise, sort using merge sort
        else
            return super.mergeSort(entries, "id", SORT_TYPE_INTEGER);
    }
}
