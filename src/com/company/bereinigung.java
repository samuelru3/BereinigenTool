package com.company;

import java.io.File;
import java.io.IOException;

import static com.company.apacheCommons.*;

public class bereinigung {
    static com.sun.security.auth.module.NTSystem NTSystem = new
            com.sun.security.auth.module.NTSystem();
    static String username = NTSystem.getName();

    public static void main() {
        try {
//Download Ordner Leeren
            cleanDirectory(new File("C:\\Users\\" + username + "\\Downloads"));
//Temp Ordner Leeren
//            cleanDirectory(new File("C:\\Users\\" + username + "\\AppData\\Local\\Temp"));
//Browser Daten Löschen
            cleanDirectory(new File("C:\\Users\\" + username + "\\AppData\\Local\\Mozilla\\Firefox"));
            cleanDirectory(new File("C:\\Users\\" + username + "\\AppData\\Local\\Google\\Chrome"));
            cleanDirectory(new File("C:\\Users\\" + username + "\\AppData\\Local\\Microsoft\\Windows\\TemporaryInternetFiles"));
            cleanDirectory(new File("C:\\Users\\" + username + "\\AppData\\Local\\Microsoft\\Internet Explorer"));
//Desktop Aufräumen
            desktopAufraeumen();
//Papierkorb Ordner Leeren
            cleanDirectory(new File("C:\\$Recycle.Bin"));
//dns cashe leeren
            flushdns();
//Programm beenden
            System.out.println("dfg");
            System.exit(0);
            System.out.println("sddgdfgfgdfh");
        } catch (IOException ex) {
            ex.printStackTrace();

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();

        }
    }

    public static void flushdns() {
        String command = "cmd.exe /c start cmd.exe /c start ipconfig /flushdns";
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void desktopAufraeumen() throws IOException {
        /*Desktop Ordner Leeren*/
        cleanDirectory(new File("C:\\Users\\" + username + "\\Desktop"));
        cleanDirectory(new File("C:\\Users\\Public\\Desktop"));


        /*Dateien auf Desktop kopiert*/
        File source = new File("C:\\Windows\\bereinigenTool\\Desktop");
        File dest = new File("C:\\Users\\" + username + "\\Desktop");
        desktopKopieren(source, dest);
        try {
            copyFile(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
