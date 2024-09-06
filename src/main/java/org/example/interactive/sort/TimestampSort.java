package org.example.interactive.sort;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * The TimestampSort class sorts a list of given entries on the timestamp column.
 *
 * DESIGN TRACEABILITY: Section 4.2.2
 */
public class TimestampSort extends LogSort {
    /**
     * Sort a given log file (JSONArray) by its entry's Timestamps.
     *
     * @param entries the JSONArray of log entries of JSONObject(s)
     * @return a JSONArray of Timestamp sorted entries
     */
    public JSONArray sort(JSONArray entries) throws JSONException {
        // If log file length < 100, sort using insertion sort
        if(entries.length() < 100)
            return super.insertionSort(entries, "timestamp", SORT_TYPE_INTEGER);
        // Otherwise, sort using merge sort
        else
            return super.mergeSort(entries,  "timestamp", SORT_TYPE_INTEGER);
    }
}
