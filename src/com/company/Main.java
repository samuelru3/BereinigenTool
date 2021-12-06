package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon("src/com/company/besenIcon.jpg");
        JFrame frame = new JFrame("PC Bereinigen");
        frame.setContentPane(new Gui().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());

        frame.pack();
        frame.setVisible(true);
    }
}
