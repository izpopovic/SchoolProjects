package com.gui.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends MyForm {
    private JButton testButton;
    private JPanel contentPane;
    private JButton btnReceptionist;
    private JButton btnExit;

    // kad radis ovdje u gui-u, pazi da je List interface,
    // on ce po defaultu uzet jawa.awt.List sto nije ono
    // kaj tebi treba
    public MainMenu() throws HeadlessException {
        super("Main Menu", 500, 500);
        //jedino main menu gasi cijelu aplikaciju
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //ne zaboravit (ak je prazan prozor, ovo je problem kaj nemas)
        setContentPane(contentPane);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                try {
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Test test = new Test();
                test.setVisible(true);
            }
        });
        btnReceptionist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Receptionist receptionist = new Receptionist();
                receptionist.setVisible(true);
            }
        });
    }
}
