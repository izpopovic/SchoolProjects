package com.gui;

import com.gui.forms.MainMenu;

import javax.swing.*;

public class GUI {
    public static void start() {
        SwingUtilities.invokeLater(() -> {

            MainMenu mainMenu = new MainMenu();

        });
    }
}
