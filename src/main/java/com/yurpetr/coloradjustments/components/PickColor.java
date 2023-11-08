package com.yurpetr.coloradjustments.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PickColor extends JButton {

    public static final Font   font = new Font("Consolas", Font.BOLD, 20);
    private GridBagConstraints gbc  = new GridBagConstraints();
    private OperatedColor      color;

    public PickColor(String text, OperatedColor initialColor) {
        super(String.format("Pick %s color", text));
        this.color = initialColor;
        setFont(font);
        gbc.insets    = new Insets(10, 5, 10, 5);
        gbc.gridwidth = 3;
        gbc.gridx     = 0;
        gbc.gridy     = 1;

        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.printf("Button pressed \"%s\"\n", getText());
                color.setColor(Color.RED);
            }
        });
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

}
