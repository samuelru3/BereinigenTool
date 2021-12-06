package com.company;

import javax.swing.*;

public class Bilddarstellung extends JFrame {
    public Bilddarstellung() {
        super("JLabel");
        JPanel panel = new JPanel();
        ImageIcon img = new ImageIcon("besenIcon.png"); //Man sieht, mit dem jpg gehts auch und gif ebenso !
        JLabel lab = new JLabel("OK");
        lab.setIcon(img);
        lab.setIconTextGap(10);

        panel.add(lab);
        setContentPane(panel);
    }

    public static void main() {
        Bilddarstellung ml = new Bilddarstellung();
        ml.setSize(552, 406);
        ml.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ml.setVisible(true);
    }
}
