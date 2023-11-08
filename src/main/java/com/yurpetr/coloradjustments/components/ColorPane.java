package com.yurpetr.coloradjustments.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ColorPane extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    
    

    public ColorPane(String title, int gridx, int gridy) {
        super();
        setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx  = 5;
        gbc.ipady  = 5;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        setBorder(new TitledBorder(title));

    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

}
