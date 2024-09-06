package org.example.interactive.delete;

import java.io.*;


/**
 * The LogDelete class deletes a given logfile specified by the path
 *
 * DESIGN TRACEABILITY: Section 4.2.9
 */
public class LogDelete {
    public static void LogDelete(String path) {
        try {
            File fileToBeDeleted = new File(path);

            if (fileToBeDeleted.exists()) {
                boolean deleted = fileToBeDeleted.delete();

                if (deleted == true) {
                    System.out.println(path + " deleted successfully");
                } else {
                    System.err.println(path + " deletion failed");
                }
            }
            else {
                System.err.println("Filepath does not exist: " + path);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
