package com.yurpetr.coloradjustments.components;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class MainPane extends JPanel {


    public MainPane() {
        setLayout(new GridBagLayout());

        ColorPane initialPane = new ColorPane(ColorType.INITIAL, 0, 0);
        add(initialPane, initialPane.getGbc());

        ColorPane obtainedPane = new ColorPane(ColorType.OBTAINED, 0, 1);
        add(obtainedPane, obtainedPane.getGbc());

        ColorPane targetPane = new ColorPane(ColorType.TARGET, 0, 2);
        add(targetPane, targetPane.getGbc());

        ColorPane requiredPane = new ColorPane(ColorType.REQUIRED, 0, 3);
        add(requiredPane, requiredPane.getGbc());

        ColorFieldsData  redColorFieldsData   = new ColorFieldsData(requiredPane.getRed(),
                targetPane.getRed(), obtainedPane.getRed(), initialPane.getRed());
        ColorFieldsData  greenColorFieldsData = new ColorFieldsData(requiredPane.getGreen(),
                targetPane.getGreen(), obtainedPane.getGreen(), initialPane.getGreen());
        ColorFieldsData  blueColorFieldsData  = new ColorFieldsData(requiredPane.getBlue(),
                targetPane.getBlue(), obtainedPane.getBlue(), initialPane.getBlue());
        ChangeListener redListener          = new ChangeListener(redColorFieldsData);
        ChangeListener greenListener        = new ChangeListener(greenColorFieldsData);
        ChangeListener blueListener         = new ChangeListener(blueColorFieldsData);
        
        initialPane.addRedListener(redListener);
        initialPane.addGreenListener(greenListener);
        initialPane.addBlueListener(blueListener);

        obtainedPane.addRedListener(redListener);
        obtainedPane.addGreenListener(greenListener);
        obtainedPane.addBlueListener(blueListener);
        
        targetPane.addRedListener(redListener);
        targetPane.addGreenListener(greenListener);
        targetPane.addBlueListener(blueListener);
        

//            JButton button = new JButton("test color");
//            button.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    System.out.println(initialColor.getColor());
//                    System.out.println(obtainedColor.getColor());
//                    System.out.println(targetColor.getColor());
//
//                }
//            });
//            add(button);

    }

}