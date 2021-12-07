package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class bereinigung {
    public static void main() {
        try {
//Download Ordner Leeren
            cleanDirectory(new File("C:\\Users\\ATV-Admin\\Downloads"));

            //Programm beenden
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    public static void cleanDirectory(File directory) throws IOException {
        String message;
        if (!directory.exists()) {
            message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        } else if (!directory.isDirectory()) {
            message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        } else {
            File[] files = directory.listFiles();
            if (files == null) {
                throw new IOException("Failed to list contents of " + directory);
            } else {
                IOException exception = null;

                for (int i = 0; i < files.length; ++i) {
                    File file = files[i];

                    try {
                        forceDelete(file);
                    } catch (IOException var6) {
                        exception = var6;
                    }
                }

                if (null != exception) {
                    throw exception;
                }
            }
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            if (!file.exists()) {
                throw new FileNotFoundException("File does not exist: " + file);
            }

            if (!file.delete()) {
                String message = "Unable to delete file: " + file;
                throw new IOException(message);
            }
        }

    }

    public static void deleteDirectory(File directory) throws IOException {
        if (directory.exists()) {
            cleanDirectory(directory);
            if (!directory.delete()) {
                String message = "Unable to delete directory " + directory + ".";
                throw new IOException(message);
            }
        }
    }
}