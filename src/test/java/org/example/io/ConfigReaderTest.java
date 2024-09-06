package org.example.io;

import org.example.io.ConfigReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.*;

/**
 * Another note: this specific test is deprecated. Will keep for posterity.
 * A QUICK NOTE: Not all tests will pass at once, as some tests depend on the existence of
 * the config.json file in the correct directory, while some tests depend on the absence of the
 * config.json in the correct directory, which is a contradiction.
 *
 * Test the ConfigReader class, which loads in routines from the config.json file to be used
 * in analysis.
 */
public class ConfigReaderTest {

    /**
     * Test that fetchRoutine properly identifies and returns a routine JSONObject,
     * given that the routine actually exists in the config file.
     */
    @Test
    @DisplayName("Fetch Existing Routine")
    void fetchExistingRoutine() throws JSONException {
        JSONObject expectedRoutine = new JSONObject("{ \"routineName\": \"1\", \"eventName\": \"B\", \"analysis\": { \"type\": [ \"greaterThan\", \"existsType\" ], \"component_name\": \"Y\", \"value\": 3, \"field\": \"g\" } }");
        JSONObject actualRoutine = ConfigReader.fetchRoutine("1");
        assertEquals(expectedRoutine.toString(), actualRoutine.toString());
    }

    /**
     * Test that fetchRoutine properly returns null if the user is searching for a routine
     * that does not exist in the config file.
     */
    @Test
    @DisplayName("Fetch Non-Existing Routine")
    void fetchNotExistingRoutine() {
        JSONObject actualRoutine = ConfigReader.fetchRoutine("does not exist");
        assertEquals(null, actualRoutine);
    }

    /**
     * NOTE: Remove/rename the config file to test this
     *
     * Test that the fetchRoutine function throws an IOException if the
     * config.json file is/was missing/moved.
     */
    @Test
    @DisplayName("Config File Not Found")
    void configFileNotFound() {
        JSONObject actualRoutine = ConfigReader.fetchRoutine("timeTest");
        assertNull(actualRoutine);
    }

    /**
     *
     * NOTE: Remove all contents from the config file to test this
     *
     * Test that the fetchRoutine function throws an __Exception if the
     * config.json file is blank/empty.
     */
    @Test
    @DisplayName("Config File Empty")
    void configFileEmpty() {
        JSONObject actualRoutine = ConfigReader.fetchRoutine("timeTest");
        assertNull(actualRoutine);
    }

}
