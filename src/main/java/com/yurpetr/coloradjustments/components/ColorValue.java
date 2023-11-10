package com.yurpetr.coloradjustments.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.InputVerifier;
import javax.swing.JTextField;

public class ColorValue extends JTextField {

    public static final InputVerifier verifier = new RGBNumbersVerifier();
    public static final Font          font     = new Font("Consolas", Font.BOLD, 20);
    private GridBagConstraints        gbc      = new GridBagConstraints();

    public ColorValue(Color color, int gridx, int gridy) {
        super("128", 3);
        setInputVerifier(verifier);
        setHorizontalAlignment(JTextField.CENTER);
        setBackground(color);
        setForeground(Color.WHITE);
        setFont(font);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx  = 30;
        gbc.ipady  = 30;
        gbc.gridx  = gridx;
        gbc.gridy  = gridy;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public void setColor(Color color) {
        setBackground(color);
    }

}
