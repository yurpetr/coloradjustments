package com.yurpetr.coloradjustments;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.yurpetr.coloradjustments.components.OperatedColor;
import com.yurpetr.coloradjustments.components.RoundedPanel;
import com.yurpetr.coloradjustments.components.SmoothLabel;

public class EyeDropper extends JFrame implements NativeMouseInputListener, NativeKeyListener {

    private static final Color TRANSPARENT_COLOR     = new Color(1.0f, 1.0f, 1.0f, 0.0f);
    private static final Color RGB_LABEL_TEXT_COLOR  = Color.WHITE;
    private static final Color INITIAL_PANEL_COLOR   = Color.GRAY;
    private static final Color EYEDROPPER_PANE_COLOR = new Color(50, 50, 50);
    private static final Font  RGB_LABEL_FONT        = new Font("Courier New", Font.BOLD, 16);
    private static final int   PAUSE_TIME            = 1000 / 120;
    private static final long  serialVersionUID      = 1L;
    private RoundedPanel       contentPane, colorPane;
    private SmoothLabel        colorLabel;
    private static Robot       robot;
    private static Color       color;
    private OperatedColor      operatedColor;
    private Thread             thread;
    protected boolean          running               = true;

    public EyeDropper(OperatedColor operatedColor) {
        this.operatedColor = operatedColor;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        final RoundRectangle2D frameShape = new RoundRectangle2D.Double(5, 5, 250, 60, 20, 20);
        final RoundRectangle2D colorShape = new RoundRectangle2D.Double(0, 0, 50, 50, 20, 20);
        setResizable(false);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setBackground(TRANSPARENT_COLOR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(getMouseLocation());

        contentPane = new RoundedPanel(frameShape, EYEDROPPER_PANE_COLOR);
        setContentPane(contentPane);
        setSize(260, 70);

        colorPane = new RoundedPanel(colorShape, INITIAL_PANEL_COLOR);

        colorLabel = new SmoothLabel();
        colorLabel.setForeground(RGB_LABEL_TEXT_COLOR);
        colorLabel.setFont(RGB_LABEL_FONT);
        colorLabel.setHorizontalAlignment(SwingConstants.LEFT);

        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(6, 6, 5, 5);
        gbc.gridx   = 0;
        gbc.gridy   = 0;
        gbc.ipadx   = 55;
        gbc.ipady   = 50;
        gbc.weightx = 40;
        gbc.weighty = 50;
        contentPane.add(colorPane, gbc);

        gbc         = new GridBagConstraints();
        gbc.gridx   = GridBagConstraints.RELATIVE;
        gbc.gridy   = 0;
        gbc.weightx = 20;
        gbc.weighty = 50;
        contentPane.add(colorLabel, gbc);

        try {
            GlobalScreen.setEventDispatcher(new SwingDispatchService());
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
            GlobalScreen.addNativeMouseListener(this);
            GlobalScreen.addNativeMouseMotionListener(this);
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (running) {
                        Point mouseLocation = getMouseLocation();
                        Point newLocation   = getNewMouseLocation(mouseLocation);
                        color = robot.getPixelColor(mouseLocation.x, mouseLocation.y);

                        colorPane.setColor(color);
                        colorLabel.setText(String.format("rgb(%3d, %3d, %3d)", color.getRed(),
                                color.getGreen(), color.getBlue()));
                        setLocation(newLocation);
                        repaint();
                        Thread.sleep(PAUSE_TIME);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();

    }

    private Point getMouseLocation() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    private static Point getNewMouseLocation(Point mouseLocation) {
        Point newLocation = new Point(mouseLocation.x + 10, (mouseLocation.y) + 10);
        return newLocation;
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent event) {

        setVisible(true);
        setExtendedState(JFrame.ICONIFIED);
        setExtendedState(JFrame.NORMAL);
        toFront();
        requestFocus();
        repaint();

    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent event) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent event) {
        if (event.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            stop();

        }

        if (event.getKeyCode() == NativeKeyEvent.VC_F1) {
            operatedColor.setColor(color);
            stop();
        }

    }

    private void stop() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        running = false;
        dispose();
    }

}
