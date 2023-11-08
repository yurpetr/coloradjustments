package com.yurpetr.coloradjustments.components;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.event.DocumentListener;

public class TestPane extends JPanel {

    private OperatedColor initialColor  = new OperatedColor();
    private OperatedColor targetColor   = new OperatedColor();
    private OperatedColor obtainedColor = new OperatedColor();

    public TestPane() {
        setLayout(new GridBagLayout());

        ColorPane  initialPane = new ColorPane("Initial", 0, 0);
        ColorValue redInitial  = new ColorValue(Color.RED, 0, 0);
        initialPane.add(redInitial, redInitial.getGbc());
        ColorValue greenInitial = new ColorValue(Color.GREEN, 1, 0);
        initialPane.add(greenInitial, greenInitial.getGbc());
        ColorValue blueInitial = new ColorValue(Color.BLUE, 2, 0);
        initialPane.add(blueInitial, blueInitial.getGbc());
        PickColor pickInitialColor = new PickColor("initial", initialColor);
        initialPane.add(pickInitialColor, pickInitialColor.getGbc());
        add(initialPane, initialPane.getGbc());

        ColorPane  obtainedPane = new ColorPane("Obtained", 0, 1);
        ColorValue redObtained  = new ColorValue(new Color(128, 0, 0), 0, 0);
        obtainedPane.add(redObtained, redObtained.getGbc());
        ColorValue greenObtained = new ColorValue(new Color(0, 128, 0), 1, 0);
        obtainedPane.add(greenObtained, greenObtained.getGbc());
        ColorValue blueObtained = new ColorValue(new Color(0, 0, 128), 2, 0);
        obtainedPane.add(blueObtained, blueObtained.getGbc());
        PickColor pickObtainedColor = new PickColor("obtained", obtainedColor);
        obtainedPane.add(pickObtainedColor, pickObtainedColor.getGbc());
        add(obtainedPane, obtainedPane.getGbc());

        ColorPane  targetPane = new ColorPane("Target", 0, 2);
        ColorValue redTarget  = new ColorValue(new Color(255, 128, 128), 0, 0);
        targetPane.add(redTarget, redTarget.getGbc());
        ColorValue greenTarget = new ColorValue(new Color(128, 255, 128), 1, 0);
        targetPane.add(greenTarget, greenTarget.getGbc());
        ColorValue blueTarget = new ColorValue(new Color(128, 128, 255), 2, 0);
        targetPane.add(blueTarget, blueTarget.getGbc());
        PickColor pickTargetColor = new PickColor("targer", targetColor);
        targetPane.add(pickTargetColor, pickTargetColor.getGbc());
        add(targetPane, targetPane.getGbc());

        ColorPane  requiredPane = new ColorPane("Required", 0, 3);
        ColorValue redRequired  = new ColorValue(Color.GRAY, 0, 0);
        redRequired.setEditable(false);
        redRequired.setFocusable(false);
        requiredPane.add(redRequired, redRequired.getGbc());
        ColorValue greenRequired = new ColorValue(Color.GRAY, 1, 0);
        greenRequired.setEditable(false);
        greenRequired.setFocusable(false);
        requiredPane.add(greenRequired, greenRequired.getGbc());
        ColorValue blueRequired = new ColorValue(Color.GRAY, 2, 0);
        blueRequired.setEditable(false);
        blueRequired.setFocusable(false);
        requiredPane.add(blueRequired, blueRequired.getGbc());
        add(requiredPane, requiredPane.getGbc());

        ColorFieldsData  redColorFieldsData   = new ColorFieldsData(redRequired, redTarget,
                redObtained, redInitial);
        ColorFieldsData  greenColorFieldsData = new ColorFieldsData(greenRequired, greenTarget,
                greenObtained, greenInitial);
        ColorFieldsData  blueColorFieldsData  = new ColorFieldsData(blueRequired, blueTarget,
                blueObtained, blueInitial);
        DocumentListener redListener          = new ChangeListener(redColorFieldsData);
        DocumentListener greenListener        = new ChangeListener(greenColorFieldsData);
        DocumentListener blueListener         = new ChangeListener(blueColorFieldsData);

        redInitial.getDocument().addDocumentListener(redListener);
        redObtained.getDocument().addDocumentListener(redListener);
        redTarget.getDocument().addDocumentListener(redListener);

        greenInitial.getDocument().addDocumentListener(greenListener);
        greenObtained.getDocument().addDocumentListener(greenListener);
        greenTarget.getDocument().addDocumentListener(greenListener);

        blueInitial.getDocument().addDocumentListener(blueListener);
        blueObtained.getDocument().addDocumentListener(blueListener);
        blueTarget.getDocument().addDocumentListener(blueListener);

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