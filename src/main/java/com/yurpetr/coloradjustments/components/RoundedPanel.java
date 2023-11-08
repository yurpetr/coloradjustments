package com.yurpetr.coloradjustments.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel {

    private static final Color BORDER_COLOR = new Color(80, 80, 80);
    private Shape              shape;
    private Color              color;

    public RoundedPanel(Shape shape, Color color) {
        super(null);
        this.shape = shape;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
        revalidate();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(color);
        g2.fill(shape);
        g2.setColor(BORDER_COLOR);
        g2.setStroke(new BasicStroke(2));
        g2.draw(shape);
    }

}