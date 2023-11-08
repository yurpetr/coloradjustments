package com.yurpetr.coloradjustments;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.yurpetr.coloradjustments.components.TestPane;

public class App {

    private JFrame frame;

    /**
     * Launch the application.
     */
    @SuppressWarnings("javadoc")
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                App window = new App();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public App() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 528, 551);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TestPane());
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);

    }
}
