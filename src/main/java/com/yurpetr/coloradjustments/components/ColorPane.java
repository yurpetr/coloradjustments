package com.yurpetr.coloradjustments.components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ColorPane extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private ColorValue         red, green, blue;
    private PickColor          button;
    private OperatedColor      operatedColor;

    public ColorPane(ColorType type, int gridx, int gridy) {
        super();
        setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx  = 5;
        gbc.ipady  = 5;
        gbc.gridx  = gridx;
        gbc.gridy  = gridy;
        String string = type.toString();
        setBorder(new TitledBorder(
                string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase()));
        operatedColor = new OperatedColor();
        button        = new PickColor(string.toLowerCase(), operatedColor);

        Color redColor, greenColor, blueColor;

        switch (type) {
        case INITIAL, OBTAINED, TARGET -> {
            redColor   = new Color(255, 48, 48);
            greenColor = new Color(48, 255, 48);
            blueColor  = new Color(48, 48, 255);
        }
        case REQUIRED -> {
            redColor   = new Color(128, 128, 128);
            greenColor = new Color(128, 128, 128);
            blueColor  = new Color(128, 128, 128);
        }
        default -> throw new IllegalArgumentException("Unexpected value: " + type);
        }
        red   = new ColorValue(redColor, 0, 0);
        green = new ColorValue(greenColor, 1, 0);
        blue  = new ColorValue(blueColor, 2, 0);

        add(red, red.getGbc());
        add(green, green.getGbc());
        add(blue, blue.getGbc());
        if (type != ColorType.REQUIRED) {
            add(button, button.getGbc());
        }
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public ColorValue getRed() {
        return red;
    }

    public ColorValue getGreen() {
        return green;
    }

    public ColorValue getBlue() {
        return blue;
    }

    public PickColor getButton() {
        return button;
    }

    public void addRedListener(ChangeListener listener) {
        red.getDocument().addDocumentListener(listener);
    }

    public void addGreenListener(ChangeListener listener) {
        green.getDocument().addDocumentListener(listener);
    }

    public void addBlueListener(ChangeListener listener) {
        blue.getDocument().addDocumentListener(listener);
    }

}
