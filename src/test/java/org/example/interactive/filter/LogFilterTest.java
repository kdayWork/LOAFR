package org.example.interactive.filter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.example.TestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Junit testing for ILogFilter()
public class LogFilterTest {

    /** Junit test for the componentFilter.
     * This method works by creating random unsorted log entries that are filtered between two range values. For component filter, it is sorted
     * alphabetically.
     * **/
    @Test
    void componentFilterWithinRange() throws JSONException {
        //Create componentFilter object
        ComponentFilter componentFilter = new ComponentFilter();

        //create random unfiltered log entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //Set rangeLow and High values to filter by
        Object rangeLow = "B";
        Object rangeHigh = "D";

        //enters the expected results into a JSONArray
        JSONArray filteredEntries = componentFilter.filter(entries, rangeLow, rangeHigh);

        //Testing
        assertEquals(3, filteredEntries.length());

        assertEquals("B", filteredEntries.getJSONObject(0).getString("component"));
        assertEquals("C", filteredEntries.getJSONObject(1).getString("component"));
        assertEquals("D", filteredEntries.getJSONObject(2).getString("component"));
    }

    /** Junit test for the componentFilter.
     * This method works by creating random unfiltered log entries that are filtered between two range values. For component filter, it is filtered
     * alphabetically. There expected output is 0, as the range is defined outside all of the components in use (No values between E to Z)
     * **/
    @Test
    void componentFilterNoEntriesWithinRange() throws JSONException {
        //Create componentFilter object
        ComponentFilter componentFilter = new ComponentFilter();

        //create random unfiltered entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //set range values to filter by
        Object rangeLow = "E";
        Object rangeHigh = "Z";

        // enters values into JSON array
        JSONArray filteredEntries = componentFilter.filter(entries, rangeLow, rangeHigh);
        // testing
        assertEquals(0, filteredEntries.length());
    }

    /** Junit test for the fieldFilter.
     * This method works by creating random unfiltered log entries that are filtered between two range values. For field filter, it is filtered
     * alphabetically.
     * **/
    @Test
    void fieldFilterWithinRange() throws JSONException {

        //Create fieldFilter object
        FieldFilter fieldFilter = new FieldFilter();

        //create random unfiltered log entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //set range values to filter by
        Object rangeLow = "S";
        Object rangeHigh = "W";

        //sets all of the entires into a JSON Array
        JSONArray filteredEntries = fieldFilter.filter(entries, rangeLow, rangeHigh);

        //testing
        assertEquals(2, filteredEntries.length());

        assertEquals("Temperature", filteredEntries.getJSONObject(0).getString("field"));
        assertEquals("Speed", filteredEntries.getJSONObject(1).getString("field"));

    }

    /** Junit test for the IDFilter.
     * This method works by creating random unfiltered log entries that are filtered between two range values. For id filter, it is filtered
     * numerically(integer).
     * **/
    @Test
    void idFilterWithinRange() throws JSONException {
        //create idFilter object
        IDFilter idFilter = new IDFilter();

        //create random unfiltered log entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //set range values to filter by
        Object rangeLow = 2;
        Object rangeHigh = 3;

        //set all of the entries into a JSON array
        JSONArray filteredEntries = idFilter.filter(entries, rangeLow, rangeHigh);

        //testing
        assertEquals(2, filteredEntries.length());

        assertEquals(2, filteredEntries.getJSONObject(0).getInt("id"));
        assertEquals(3, filteredEntries.getJSONObject(1).getInt("id"));
    }

    /** Junit test for the idFilter.
     * This method works by creating random unfiltered log entries that are filtered between two range values. For id filter, it is filtered
     * numerically (integer). The expected output here is 0, as there are no ids within the stated range
     * **/
    @Test
    void idFilterNoEntriesWithinRange() throws JSONException {
        //create idFilter object
        IDFilter idFilter = new IDFilter();

        //created random unfiltered entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //set range between two values
        Object rangeLow = 5;
        Object rangeHigh = 8;

        //set entries into json array
        JSONArray filteredEntries = idFilter.filter(entries, rangeLow, rangeHigh);

        //testing
        assertEquals(0, filteredEntries.length());
    }

    /** Junit test for the timestampFilter.
     * This method works by creating random unfiltered log entries that are filtered between two range values. For timestamp filter, it is filtered
     * numerically (integer)
     * **/
    @Test
    void timestampFilterWithinRange() throws JSONException {
        //create timestampFilter object
        TimestampFilter timestampFilter = new TimestampFilter();

        //create random unfiltered entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //set range values
        Object rangeLow = 1923;
        Object rangeHigh = 32485;

        //add values to the JSON array
        JSONArray filteredEntries = timestampFilter.filter(entries, rangeLow, rangeHigh);

        //testing
        assertEquals(3, filteredEntries.length());

        assertEquals(12054, filteredEntries.getJSONObject(0).getInt("timestamp"));
        assertEquals(1923, filteredEntries.getJSONObject(1).getInt("timestamp"));
        assertEquals(32485, filteredEntries.getJSONObject(2).getInt("timestamp"));
    }

    /** Junit test for the timestampFilter.
     * This method works by creating random unfiltered log entries that are filtered between two range values. For timestamp filter, it is filtered
     * numerically(integer). The expected output value is zero, since there are no values between the given range for timestamp
     * **/
    @Test
    void timestampFilterNoEntriesWithinRange() throws JSONException {
        //create timestampFilter object
        TimestampFilter timestampFilter = new TimestampFilter();

        //create random unfiltered entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //set range values
        Object rangeLow = 100000;
        Object rangeHigh = 200000;

        //set values into a JSON array
        JSONArray filteredEntries = timestampFilter.filter(entries, rangeLow, rangeHigh);

        //testing
        assertEquals(0, filteredEntries.length());
    }

    /** Junit test for the valueFilter.
     * This method works by creating random unfiltered log entries that are filtered between two range values. For value filter, it is filtered
     * numerically (integer)
     * **/
    @Test
    void valueFilterWithinRange() throws JSONException {
        //create valueFilter object
        ValueFilter valueFilter = new ValueFilter();

        //create random unfiltered entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //set range values
        Object rangeLow = 3.0;
        Object rangeHigh = 100.3;

        //set values into JSON array
        JSONArray filteredEntries = valueFilter.filter(entries, rangeLow, rangeHigh);

        //testing
        assertEquals(3, filteredEntries.length());

        assertEquals(100.2, filteredEntries.getJSONObject(0).getDouble("value"));
        assertEquals(3.7, filteredEntries.getJSONObject(1).getDouble("value"));
        assertEquals(76.3, filteredEntries.getJSONObject(2).getDouble("value"));
    }

    /** Junit test for the timestampFilter.
     * This method works by creating random unfiltered log entries that are filtered between two range values. For timestamp filter, it is filtered
     * numerically (integer). The output value is 0 since there are no values between the given ranges
     * **/

    @Test
    void valueFilterNoEntriesWithinRange() throws JSONException {
        //create valueFilter object
        ValueFilter valueFilter = new ValueFilter();

        //create random unfiltered entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //set range values
        Object rangeLow = 200;
        Object rangeHigh = 300.1;

        //set entires into JSON array
        JSONArray filteredEntries = valueFilter.filter(entries, rangeLow, rangeHigh);

        //testing
        assertEquals(0, filteredEntries.length());

    }

    /** Junit test for the entryFilter.
     * This method works by creating random unfiltered log entries that are filtered between two range values. For entry filter, it is filtered
     * alphabetically
     * **/
    @Test
    void entryTypeFilterWithinRange() throws JSONException {
        //create entryTypeFilter object
        EntryTypeFilter entryTypeFilter = new EntryTypeFilter();

        //create random unfiltered JSON entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //set range values
        Object rangeLow = "B";
        Object rangeHigh = "Q";

        //put JSON entries into a JSON array
        JSONArray filteredEntries = entryTypeFilter.filter(entries, rangeLow, rangeHigh);

        //testing
        assertEquals(3, filteredEntries.length());

        assertEquals("Computer", filteredEntries.getJSONObject(0).getString("entryType"));
        assertEquals("Computer", filteredEntries.getJSONObject(1).getString("entryType"));
        assertEquals("Phone", filteredEntries.getJSONObject(2).getString("entryType"));
    }

    /** Junit test for the entryFilter.
     * This method works by creating random unfiltered log entries that are filtered between two range values. For entry filter, it is filtered
     * alphabetically. The expected value here is 0, as there are no values between the given range
     * **/
    @Test
    void entryTypeFilterNoEntriesWithinRange() throws JSONException {
        //create entryTypeFilter object
        EntryTypeFilter entryTypeFilter = new EntryTypeFilter();

        //create random unfiltered log entries
        JSONArray entries = new JSONArray();
        JSONObject e1 = TestHelper.generateLogEntry(1, 12054, "Computer", "A", "Temperature", 100.2);
        JSONObject e2 = TestHelper.generateLogEntry(2, 1923, "Computer", "B", "Speed", 3.7);
        JSONObject e3 = TestHelper.generateLogEntry(3, 32485, "Router", "C", "Working", 0);
        JSONObject e4 = TestHelper.generateLogEntry(4, 87823, "Phone", "D", "Level", 76.3);
        entries.put(e1);
        entries.put(e2);
        entries.put(e3);
        entries.put(e4);

        //set range values
        Object rangeLow = "E";
        Object rangeHigh = "F";

        //set all entires into a JSON array
        JSONArray filteredEntries = entryTypeFilter.filter(entries, rangeLow, rangeHigh);

        //testing
        assertEquals(0, filteredEntries.length());
    }
}
