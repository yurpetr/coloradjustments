package com.yurpetr.coloradjustments.components;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import com.yurpetr.coloradjustments.EyeDropper;

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

        addActionListener(event ->
//        EventQueue.invokeLater(() -> 
        {
            try {
                EyeDropper frame = new EyeDropper(color);
                frame.setVisible(true);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
//        )
        );

    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

}
