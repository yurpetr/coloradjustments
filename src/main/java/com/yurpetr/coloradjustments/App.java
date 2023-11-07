package com.yurpetr.coloradjustments;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class App {

    private JFrame frame;

    /**
     * Launch the application.
     */
    @SuppressWarnings("javadoc")
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App window = new App();
                    window.frame.setVisible(true);
                    int n = 123;
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    }

    class TestPane extends JPanel {

        TestPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(5, 10, 10, 10);
            JTextField jtf = new JTextField(2);
            jtf.setHorizontalAlignment(JTextField.CENTER);
            gbc.ipadx = 50;
            gbc.ipady = 50;
            gbc.gridy = 0;
            gbc.gridx = 0;
            jtf.setFocusable(false);
            jtf.setBackground(Color.RED);
            add(jtf, gbc);

            gbc = new GridBagConstraints();
            try {
                JLabel    jl = new JLabel();
                ImageIcon ii = new ImageIcon(
                        ImageIO.read(new File("assets/java-logo-512.png")));
                jl.setIcon(ii);
                gbc.gridy = 0;
                gbc.gridx = 0;
                add(jl, gbc);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
