package com.gui.forms;

import javax.swing.*;
import java.awt.*;

public class MyForm extends JFrame {
    public MyForm(String title, int width, int height) throws HeadlessException {
        super(title);

        setSize(width, height);
        setLocationRelativeTo(null);
        //svi osim main menu-a se samo zgase, a main menu gasi cijeli program
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
