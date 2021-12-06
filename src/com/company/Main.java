package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon("C:\\Users\\ATV-Admin\\IdeaProjects\\BereinigenTool\\src\\com\\company\\besenIcon.jpg");
        JFrame frame = new JFrame("PC Bereinigen");
        frame.setContentPane(new Gui().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
