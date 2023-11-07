package com.yurpetr.coloradjustments;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
    private static EyeDropper  frame;
    private static Robot       robot;
    private static Color       color;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    frame = new EyeDropper();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Create the frame.
     * 
     * @throws NativeHookException
     */
    public EyeDropper() throws NativeHookException {
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
//        setSize(frameShape.getBounds().getSize());
        setSize(260, 70);

        colorPane = new RoundedPanel(colorShape, INITIAL_PANEL_COLOR);

        colorLabel = new SmoothLabel();
        colorLabel.setForeground(RGB_LABEL_TEXT_COLOR);
        colorLabel.setFont(RGB_LABEL_FONT);
        colorLabel.setHorizontalAlignment(SwingConstants.LEFT);

//        GridBagLayout gbl = new GridBagLayout();
//        gbl.columnWeights = new double[] { 0.2, 0.8 };
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

//        JTextField jtf = new JTextField(2);
//        jtf.setHorizontalAlignment(JTextField.CENTER);
//        gbc.ipadx = 50;
//        gbc.ipady = 50;
//        gbc.gridy = 0;
//        gbc.gridx = 0;
//        jtf.setFocusable(false);
//        jtf.setBackground(Color.RED);
//        contentPane.add(jtf,gbc);

        gbc         = new GridBagConstraints();
        gbc.gridx   = GridBagConstraints.RELATIVE;
        gbc.gridy   = 0;
        gbc.weightx = 20;
        gbc.weighty = 50;
//        gbc.fill    = GridBagConstraints.HORIZONTAL;
        contentPane.add(colorLabel, gbc);

        GlobalScreen.setEventDispatcher(new SwingDispatchService());
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                        Point newLocation   = getMouseLocation();
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
        }).start();

    }

    private static Point getMouseLocation() {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        Point newLocation   = new Point(mouseLocation.x + 10, (mouseLocation.y) + 10);
        return newLocation;
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent event) {
        System.out.printf("Mouse button1 clicked: x = %d, y = %d\n", event.getX(), event.getY());

        color = robot.getPixelColor(event.getX(), event.getY());
        System.out.printf("rgb(%3d, %3d, %3d)\n", color.getRed(), color.getGreen(), color.getBlue());

        setVisible(true);
        toFront();
        requestFocus();
        repaint();
        setExtendedState(JFrame.ICONIFIED);
        setExtendedState(JFrame.NORMAL);

    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent event) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent event) {
        if (event.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            dispose();
            System.exit(1);
        }
    }

//    public @Override void toFront() {
//        int sta = super.getExtendedState() & ~JFrame.ICONIFIED & JFrame.NORMAL;
//
//        super.setExtendedState(sta);
//        super.setAlwaysOnTop(true);
//        super.toFront();
//        super.requestFocus();
//        super.setAlwaysOnTop(false);
//    }

}
