package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bereinigen");
        frame.setContentPane(new Gui().mainPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
