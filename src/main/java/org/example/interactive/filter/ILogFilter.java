package org.example.interactive.filter;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * The LogSort class is used to filter a given list of log entries using the overloaded derived class
 * function specified by the column to filter by, as well as a low and high range value to filter between.
 *
 * DESIGN TRACEABILITY: Section 4.2.4
 */
public interface ILogFilter {
    /**
     * Given a list of entries, return entries where a specific key is contained
     * within the range of [rangeLow, rangeHigh].
     *
     * @param entries the list of log entries
     * @param rangeLow the low range
     * @param rangeHigh the high range
     */
    public JSONArray filter(JSONArray entries, Object rangeLow, Object rangeHigh) throws JSONException;
}
