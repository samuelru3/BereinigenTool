package com.company;

import javax.swing.*;
//ToDo Einträge zum leeren andere Verzeichnisse erstellen
//ToDo Desktop Symbole nach Löschen wieder hinzufügen

public class Main {

    public static void main(String[] args) {

        ImageIcon icon = new ImageIcon("C:\\Windows\\besenIcon.jpg");
        JFrame frame = new JFrame("PC Bereinigen");
        frame.setContentPane(new Gui().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


//    com.sun.security.auth.module.NTSystem NTSystem = new
//            com.sun.security.auth.module.NTSystem();
//        System.out.println(NTSystem.getName());
//        "C:\\Users\\"+NTSystem.getName()+"\\Desktop\\besenIcon.jpg"
