package com.company;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static com.company.apacheCommons.cleanDirectory;


public class bereinigung {
    static com.sun.security.auth.module.NTSystem NTSystem = new
            com.sun.security.auth.module.NTSystem();
    static String username = NTSystem.getName();

    public static void main() {
        try {
            /*Download Ordner Leeren*/
            cleanDirectory(new File("C:\\Users\\" + username + "\\Downloads"));
            /*Desktop Aufräumen*/
            desktopAufraeumen();
            /*Papierkorb Ordner Leeren*/
            cleanDirectory(new File("C:\\$Recycle.Bin"));
            /*Programm beenden*/
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    public static void desktopAufraeumen() throws IOException {
        /*Desktop Ordner Leeren*/
        cleanDirectory(new File("C:\\Users\\" + username + "\\Desktop"));
        cleanDirectory(new File("C:\\Users\\Public\\Desktop"));
        /*Dateien auf Desktop kopieren*/
        File source = new File("C:\\Windows\\bereinigenTool\\Desktop");
        File dest = new File("C:\\Users\\" + username + "\\Desktop");
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
