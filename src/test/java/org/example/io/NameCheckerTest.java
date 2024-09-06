package org.example.io;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class NameCheckerTest {

    private FileHandler fileHandler;
    String folderPathWithFiles = "src/test/java/org/example/io";

    @Before
    public void setUp(){
        fileHandler = new FileHandler();
    }

    /**
     * This is a test to make sure that a filename is taken given a folder full of files.
     * This is done by adding a file to the current folder and then testing that the file name
     * is in the folder given. The test file is then deleted.
     */

    @Test
    public void testIsNameTakenInNonEmptyFolder(){
        try {
            //Create test file
            File outputFile = new File(folderPathWithFiles + "/test.txt");
            outputFile.createNewFile();
            String existingFileName = "test.txt";
            boolean result1 = NameChecker.isNameTaken(existingFileName, folderPathWithFiles);
            //false if the name is not available
            assertFalse(result1);
            fileHandler.deleteFile(outputFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is a test to make sure that a filename is correctly marked as availible given
     * a path to an empty folder.
     */

    @Test
    public void testIsNameTakenInEmptyFolder() {
        String emptyFolderPath = "src/test/java/org/example/io/emptyFolder";
        String fileName = "test.txt";
        boolean result2 = NameChecker.isNameTaken(fileName, emptyFolderPath);
        //True if the name is available
        assertTrue(result2);
    }

    /**
     * This is a test to make sure that a filename is not taken given a folder full of files.
     * This is done by adding a file to the current folder and then testing if the file name
     * is not in the folder given. The test file is then deleted.
     */

    @Test
    public void testIsNameNotTaken() {
        String nonExistingFileName = "test2.txt";
        boolean result3 = NameChecker.isNameTaken(nonExistingFileName, folderPathWithFiles);
        assertTrue(result3);
    }
}
