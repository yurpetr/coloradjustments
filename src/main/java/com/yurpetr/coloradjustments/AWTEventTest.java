package com.yurpetr.coloradjustments;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AWTEventTest {

    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLayout(new FlowLayout());

            MyJComponentDecorator button = new MyJComponentDecorator(new JButton("A Button"));
            button.addGlobalListener();
            frame.getContentPane().add(button, null);

            frame.setAlwaysOnTop(true);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
