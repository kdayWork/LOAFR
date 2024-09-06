package org.example.interactive.search;

import org.json.JSONArray;

/**
 * The LogSearch class searches a given logfile for the specified 'criteria' given the entries array
 *
 * DESIGN TRACEABILITY: Section 4.2.8
 */
public class LogSearch {
    // cant implement until we have the interactive log viewer to view the searched file. This should be a good start in the mean time
/*    public void search(JSONArray entries, String criteria) {

        // Keep a list of the entry values and the indices they appear at
        String[] entryValues = new String[entries.length()];
        int[] entryIndices = new int[entries.length()];

        // Copy all the key values (specified by keyName) of entries into entryValues
        for (int i = 0; i < entries.length(); i++) {
            JSONObject entry = new JSONObject(entries.get(i).toString());
            String entryValue = entry.get(keyName).toString();
            entryValues[i] = entryValue;
            entryIndices[i] = i;
        }

        for (i = 0; i < entryValues.length; i++){
          if criteria.equals(entryValues[i]){
            return criteria
          }
        }

    return
    }*/
}
