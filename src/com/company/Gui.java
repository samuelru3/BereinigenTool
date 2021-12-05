package com.company;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Gui {
    private JButton buttonBereinigen;
    private JButton abbrechenButton;
    private JLabel frageLabel;
    JPanel mainPanel;

    public Gui() {
        buttonBereinigen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileUtils.cleanDirectory(new File("C:\\Users\\ATV-Admin\\Downloads"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
