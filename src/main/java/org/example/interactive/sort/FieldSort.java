package org.example.interactive.sort;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * The FieldSort class sorts a list of given entries on the field column.
 *
 * DESIGN TRACEABILITY: Subset/Extension of Section 4.2.4
 */
public class FieldSort extends LogSort {
    /**
     * Sort a given log file (JSONArray) by its entry's contents.
     *
     * @param entries the JSONArray of log entries of JSONObject(s)
     * @return a JSONArray of content sorted entries
     */
    public JSONArray sort(JSONArray entries) throws JSONException {
//        JSONArray new = new JSONArray();
        // If log file length < 100, sort using insertion sort
        if(entries.length() < 100)
            return super.insertionSort(entries, "field", LogSort.SORT_TYPE_STRING);
            // Otherwise, sort using merge sort
        else
            return super.mergeSort(entries, "field", LogSort.SORT_TYPE_STRING);
    }
}
