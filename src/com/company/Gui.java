package com.company;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    private JButton buttonBereinigen;
    private JButton abbrechenButton;
    private JLabel frageLabel;
    JPanel mainPanel;

    public Gui() {
        buttonBereinigen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bereinigung.main();
            }
        });
        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//Programm beenden
                System.exit(0);
            }
        });
    }


}
