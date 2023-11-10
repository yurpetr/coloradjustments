package com.yurpetr.coloradjustments.examples;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JComponent;

public class MyJComponentDecorator extends JComponent implements MyListener {
    protected JComponent child;

    public MyJComponentDecorator(JComponent c) {
        child = c;
        this.setLayout(new BorderLayout());
        this.add(child);
    }

    @Override
    public void eventDispatched(AWTEvent e) {
        System.out.println(e);
    }

    @Override
    public void addGlobalListener() {
        final MyJComponentDecorator component = new MyJComponentDecorator(child);
        Toolkit.getDefaultToolkit().addAWTEventListener(component, AWTEvent.MOUSE_MOTION_EVENT_MASK
                | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
        this.add(component);
    }

}