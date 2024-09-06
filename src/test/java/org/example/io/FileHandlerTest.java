package org.example.io;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class FileHandlerTest {

    private FileHandler fileHandler;
    private String testFolderPath = "src/test/java/org/example/io";
    private String testFilePath = testFolderPath + "/testFile.txt";

    @BeforeEach

    public void setUp(){
        fileHandler = new FileHandler();
    }

    /**
     * This is a test to check that the data from an entry is being written into a new file
     * correctly. It works by creating an entry and writing it to a new test file where
     * it then ensures that the data was written into the new test file correctly.
     */

    @Test
    public void testWriteFile() throws JSONException {
        //test data
        JSONArray entries = new JSONArray();
        JSONObject entry = new JSONObject();
        entry.put("id", 1);
        entry.put("timestamp", 1234);
        entry.put("entryType", "Test");
        entry.put("component", "TestComponent");
        entry.put("field", "TestField");
        entry.put("value", 99);

        entries.put(entry);

        try {
            //Write entry to file
            fileHandler.writeFile(entries, testFilePath);
            File outputFile = new File(testFolderPath + "/testFile.out.txt");
            //output the path to the file that was just made
            System.out.println("File created: " + outputFile.getAbsolutePath());
            assertTrue(outputFile.exists());
            // delete test file
            fileHandler.deleteFile(outputFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This is a test to check that the data from an entry is being read from a file
     * correctly. It works by creating an entry and writing it to a new test file where
     * it then compares the test entry and what was read form the test file to ensure
     * that it is the same.
     */

    @Test
    public void testReadFile() throws JSONException {
        //test data
        JSONArray entries = new JSONArray();
        JSONObject entry = new JSONObject();
        entry.put("id", 1);
        entry.put("timestamp", 1234);
        entry.put("entryType", "Test");
        entry.put("component", "TestComponent");
        entry.put("field", "TestField");
        entry.put("value", 99);

        entries.put(entry);

        try {
            //write data to a new file
            fileHandler.writeFile(entries, testFilePath);
            File outputFile = new File(testFolderPath + "/testFile.out.txt");
            JSONArray readEntries = fileHandler.readFile(testFolderPath + "/testFile.out.txt");
            //check that the data written to the new file is the same as the test data
            assertEquals(entries.toString(), readEntries.toString());
            // delete test file
            fileHandler.deleteFile(outputFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This is the test to make sure that a file is deleted given a correct path. This is
     * done by creating a test file and deleting it and checking to make sure that it does
     * not exist anymore.
     */

    @Test
    public void testDeleteFile(){
        try{
            //create test file to delete
            Files.createFile(Paths.get(testFilePath));
        } catch(IOException e) {
            e.printStackTrace();
        }
        //delete test file
        fileHandler.deleteFile(testFilePath);

        File deletedFile = new File(testFilePath);
        //check to make sure the delete file does not exist anymore
        assertFalse(deletedFile.exists());
    }
}
