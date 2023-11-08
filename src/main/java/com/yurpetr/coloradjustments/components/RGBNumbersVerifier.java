package com.yurpetr.coloradjustments.components;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class RGBNumbersVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
            int number = Integer.parseInt(text);
            if (number < 0 || number > 255) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}