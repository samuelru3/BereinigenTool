package com.company;

import java.io.File;

public class DeleteDir {

    public static void deleteDir(File path) {
        for (File file : path.listFiles()) {
            if (file.isDirectory())
                deleteDir(file);
            file.delete();
        }
        path.delete();
    }

//    public static void main(String[] args) {
//        deleteDir(new File("c:/downloads"));
//    }
}
