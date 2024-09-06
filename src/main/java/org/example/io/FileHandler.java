package org.example.io;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.io.FileWriter;

public class FileHandler {

    public static void writeFile(JSONArray entries, String path) {
        String outpath = path.substring(0, path.length() - 4) + ".out.txt";

        try {
            File myObj = new File(outpath);
            if (NameChecker.isNameTaken(myObj.getName(), path)) {
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getAbsolutePath());

                    FileWriter writer = new FileWriter(outpath);
                    for (int i = 0; i < entries.length(); i++) {
                        JSONObject entry = entries.getJSONObject(i);
                        writer.write(entry.getInt("id") + ",");
                        writer.write(entry.getInt("timestamp") + ",");
                        writer.write(entry.getString("entryType") + ",");
                        writer.write(entry.getString("component") + ",");
                        writer.write(entry.getString("field") + ",");
                        writer.write(entry.getDouble("value") + "\n");
                    }
                    writer.close();
                } else {
                    System.out.println("File already exists.");
                }
            } else {
                System.out.println("File name already in use.");
            }
        } catch (IOException | JSONException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFolder(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    readFile(path + "/" + file.getName());
                }
            }
        } else {
            System.out.println("The folder path provided is not valid or does not contain any files.");
        }
    }

    public static JSONArray readFile(String path) {
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            JSONArray entries = new JSONArray();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(",", 6);
                JSONObject entry = new JSONObject();

                if (data.length >= 5 && !data[2].isEmpty() && !data[3].isEmpty() && !data[4].isEmpty()) {
                    entry.put("id", Integer.parseInt(data[0]));
                    entry.put("timestamp", Integer.parseInt(data[1]));
                    entry.put("entryType", data[2]);
                    entry.put("component", data[3]);
                    entry.put("field", data[4]);
                    entry.put("value", Double.parseDouble(data[5]));

                    entries.put(entry);
                } else {
                    System.out.println("Incomplete data in line: " + line);
                }
            }
            reader.close();
            return entries;
        } catch (FileNotFoundException | JSONException e) {
            System.out.println("Error occurred: File not found.");
            e.printStackTrace();
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
    }

    public void deleteFile(String path) {
        try {
            File file_to_delete = new File(path);
            if (file_to_delete.exists()) {
                boolean deleted = file_to_delete.delete();
                if (deleted) {
                    System.out.println(path + " deleted successfully");
                } else {
                    System.err.println(path + " deletion failed");
                }
            } else {
                System.err.println("Filepath does not exist: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
