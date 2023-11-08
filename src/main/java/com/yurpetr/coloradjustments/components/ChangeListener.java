package com.yurpetr.coloradjustments.components;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class ChangeListener implements DocumentListener {

    private ColorFieldsData colorData;

    public ChangeListener(ColorFieldsData data) {
        this.colorData = data;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changeValue();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changeValue();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        changeValue();
    }

    private void changeValue() {
        try {
            colorData.getRequired().setText(
                    String.valueOf(correctColor(Integer.parseInt(colorData.getInitial().getText()),
                            Integer.parseInt(colorData.getTarget().getText()),
                            Integer.parseInt(colorData.getObtained().getText()))));
        } catch (NumberFormatException e) {

        }
    }

    @SuppressWarnings("static-method")
    private int correctColor(int initial, int target, int obtained) {
        int required = initial * target / obtained;
        return required;
    }

}
