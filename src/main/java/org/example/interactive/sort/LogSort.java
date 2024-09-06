package org.example.interactive.sort;

import org.example.Main;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.example.Main.*;

import javax.swing.*;

/**
 * The LogSort class is used to sort a given list of log entries using the overloaded derived class
 * function specified by the column to sort on.
 *
 * DESIGN TRACEABILITY: Section 4.2.1
 */
public abstract class LogSort {

    // Represents sorting of a Double type
    public static final int SORT_TYPE_DOUBLE = 1001;
    // Represents sorting of an Integer type
    public static final int SORT_TYPE_INTEGER = 1002;
    // Represents sorting of a String type
    public static final int SORT_TYPE_STRING = 1003;

    /**
     * Sort method to be implemented uniquely in each derived class. Sorts the
     * logfile entries on the selected key name using an optimized case sorting
     * algorithm.
     *
     * @param entries the list of log file entries
     * @return a sorted list of the log file entries
     */
    public abstract JSONArray sort(JSONArray entries) throws JSONException;

    /**
     * Compare two object types using less than/greater than or lexographical ordering. The
     * function is able to accept a general type of primitive objects to compare.
     *
     * @param a the first object
     * @param b the second object
     * @param keyType the type of object to compare
     * @return an integer representing the ordering of a and b
     */
    public static int compareToGenType(Object a, Object b, int keyType) {
        if(keyType == SORT_TYPE_DOUBLE)
            return Double.compare((new Double(a.toString())), (new Double(b.toString())));
        else if(keyType == SORT_TYPE_INTEGER)
            return (new Integer(a.toString())).compareTo((new Integer(b.toString())));
        else
            return a.toString().compareTo(b.toString());
    }

    /**
     * Given a list of log entries, sort the log entries on the given keyName (of type keyType)
     * and return the new list of sorted entries using the Insertion Sort algorithm.
     *
     * The sorting algorithm below works by first obtaining a list of `keyName` from the entries
     * and then sorting the list of `keyName`. During this sort, it builds a list of the indices after
     * sorting (similar to numpy.argsort https://numpy.org/doc/stable/reference/generated/numpy.argsort.html)
     * and places the objects in a new list given the sorted argument order.
     *
     * @param entries the list of log entries
     * @param keyName the name of the key to sort on
     * @param keyType the type of the key to sort on
     * @return a list of entries sorted on keyName
     */
    protected JSONArray insertionSort(JSONArray entries, String keyName, int keyType) throws JSONException {

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

        // Use insertion sort on the entryValues list
        for (int i = 1; i < entryValues.length; i++) {
            String key = entryValues[i];
            int indexKey = entryIndices[i];
            int j = i - 1;

            while (j >= 0 && compareToGenType((Object) entryValues[j], (Object) key, keyType) > 0) {
                entryValues[j + 1] = entryValues[j];
                entryIndices[j + 1] = entryIndices[j];
                j--;
            }
            entryValues[j + 1] = key;
            entryIndices[j + 1] = indexKey;
        }

        // Create a JSONArray to hold the sorted entries
        // Then given the indices of the JSONObjects in sorted order
        // append them to the sortedEntries array
        JSONArray sortedEntries = new JSONArray();
        for(int i = 0; i < entries.length(); i++) {
            int entryIndex = entryIndices[i];
            JSONObject entry = new JSONObject(entries.get(entryIndex).toString());
            sortedEntries.put(entry);
        }

        // Return sortedEntries (don't forget to update entries in calling function)
        return sortedEntries;
    }

    /**
     * Given a list of log entries, sort the log entries on the given keyName (of type keyType)
     * and return the new list of sorted entries using the Merge Sort algorithm.
     *
     * The sorting algorithm below works by first obtaining a list of `keyName` from the entries
     * and then sorting the list of `keyName`. During this sort, it builds a list of the indices after
     * sorting (similar to numpy.argsort https://numpy.org/doc/stable/reference/generated/numpy.argsort.html)
     * and places the objects in a new list given the sorted argument order.
     *
     * @param entries the list of log entries
     * @param keyName the name of the key to sort on
     * @param keyType the type of the key to sort on
     * @return a list of entries sorted on keyName
     */
    protected JSONArray mergeSort(JSONArray entries, String keyName, int keyType) throws JSONException {
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

        // Use merge sort on the entryValues list
        mergeSortRecursive(keyType, entryValues, entryIndices, 0, entryValues.length - 1);

        // Create a JSONArray to hold the sorted entries
        // Then given the indices of the JSONObjects in sorted order
        // append them to the sortedEntries array
        JSONArray sortedEntries = new JSONArray();
        for(int i = 0; i < entries.length(); i++) {
            int entryIndex = entryIndices[i];
            JSONObject entry = new JSONObject(entries.get(entryIndex).toString());
            sortedEntries.put(entry);
        }

        // Return sortedEntries (don't forget to update entries in calling function)
        return sortedEntries;
    }

    /**
     * Recursively divide the array of entryValues into two halves, then merge the two halves in sorted
     * order.
     *
     * @param keyType the type of Object we are sorting
     * @param entryValues the values we are sorting
     * @param entryIndices the original indices of values (updates based on entryValues sort)
     * @param left the left index of the subarray
     * @param right the right index of the subarray
     */
    private void mergeSortRecursive(int keyType, String[] entryValues, int[] entryIndices, int left, int right) {
        if(left < right) {
            // Find the middle point in the entryValues array
            int mid = left + (right - left) / 2;
            // Sort each half of the entryValues array
            mergeSortRecursive(keyType, entryValues, entryIndices, left, mid);
            mergeSortRecursive(keyType, entryValues, entryIndices, mid + 1, right);
            // Merge the two sorted halves of the entryValues
            merge(keyType, entryValues, entryIndices, left, mid, right);
        }
    }

    /**
     * Given two arrays, combine the two arrays in sorted order and then appending the remaining elements
     * from either half onto the end of the array.
     *
     * @param keyType the type of Object to merge
     * @param entryValues the values to merge
     * @param entryIndices the indices of values to merge
     * @param left the index of the left array
     * @param mid the middle point of the array
     * @param right the index of the right array
     */
    private void merge(int keyType, String[] entryValues, int[] entryIndices, int left, int mid, int right) {
        int nLeft = mid - left + 1;
        int nRight = right - mid;

        // Temp string arrays for left and right side
        String[] leftValues = new String[nLeft];
        int[] leftIndices = new int[nLeft];
        String[] rightValues = new String[nRight];
        int[] rightIndices = new int[nRight];

        // Copy data to temp string arrays
        for(int i = 0; i < nLeft; i++) {
            leftValues[i] = entryValues[left + i];
            leftIndices[i] = entryIndices[left + i];
        }
        for(int j = 0; j < nRight; j++) {
            rightValues[j] = entryValues[mid + 1 + j];
            rightIndices[j] = entryIndices[mid + 1 + j];
        }

        // Initial pointer variables
        int i = 0, j = 0;
        int k = left;

        // Merge all elements from the left and right side in sorted order
        while(i < nLeft && j < nRight) {
            if(compareToGenType((Object) leftValues[i], (Object) rightValues[j], keyType) <= 0) {
                entryValues[k] = leftValues[i];
                entryIndices[k] = leftIndices[i];
                i++;
            }
            else {
                entryValues[k] = rightValues[j];
                entryIndices[k] = rightIndices[j];
                j++;
            }
            k++;
        }

        // Append the rest of the values on the left side
        while(i < nLeft) {
            entryValues[k] = leftValues[i];
            entryIndices[k] = leftIndices[i];
            i++;
            k++;
        }

        // Append the rest of the values on the right side
        while(j < nRight) {
            entryValues[k] = rightValues[j];
            entryIndices[k] = rightIndices[j];
            j++;
            k++;
        }
    }
}
