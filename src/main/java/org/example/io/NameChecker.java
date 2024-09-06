package org.example.io;

import java.io.File;

// Elem. 4.2.17
public class NameChecker {
    public static boolean isNameTaken(String name, String folderPath) {
        try {
            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    // if wanted file name is not available
                    if (file.getName().equals(name)) {
                        return false;
                    }
                }
            }
        }
        catch ( Exception e){
            System.err.println(e.getMessage());
        }
        //if wanted file name is available
        return true;
    }
}

