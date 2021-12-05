package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Gui {
    private JButton buttonBereinigen;
    private JButton abbrechenButton;
    private JLabel frageLabel;
    JPanel mainPanel;

    public Gui() {
        buttonBereinigen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteDir.deleteDir(new File("C:\\Users\\ATV-Admin\\Downloads"));
            }
        });
        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//
            }
        });
    }
}
