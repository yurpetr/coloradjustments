package com.yurpetr.coloradjustments.components;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OperatedColor {

    private Color                 color;
    private PropertyChangeSupport mPcs = new PropertyChangeSupport(this);

    public OperatedColor() {
        color = Color.GRAY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        Color oldColor = this.color;
        this.color = color;
        mPcs.firePropertyChange("color", oldColor, color);
    }
    public void
    addPropertyChangeListener(PropertyChangeListener listener) {
        mPcs.addPropertyChangeListener(listener);
    }
    
    public void
    removePropertyChangeListener(PropertyChangeListener listener) {
        mPcs.removePropertyChangeListener(listener);
    }
}
