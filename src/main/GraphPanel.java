package main;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import data.*;

class GraphPanel extends JPanel {

    private static JLabel label; // Composite structural design pattern
    private final LinearData data;

    public GraphPanel(LinearData data) {
        this.data = data;
        setBackground(Color.WHITE);

        this.setLayout(null);

        label = new JLabel("Payment amount");
        label.setBounds(10, 25, 100, 25);
        this.add(label);

        label = new JLabel("Mounth");
        label.setBounds(460, 250, 50, 25);
        this.add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (data == null || data.getXValues().length == 0)
            return;

        double[] xValues = data.getXValues();
        double[] yValues = data.getYValues();
        int numPoints = xValues.length;

        double xMin = 1.0;
        double xMax = Integer.MIN_VALUE;
        double yMin = 0;
        double yMax = Integer.MIN_VALUE;

        for (int i = 0; i < numPoints; i++) {
            if (xValues[i] > xMax) xMax = xValues[i];
            if (yValues[i] > yMax) yMax = yValues[i] * 2;
        }

        int padding = 50;
        int graphWidth = getWidth() - 2 * padding;
        int graphHeight = getHeight() - 2 * padding;

        double xScale = (double) graphWidth / (xMax - xMin);
        double yScale = (double) graphHeight / (yMax - yMin);

        int xOffset = padding;
        int yOffset = padding;

        g.setColor(Color.BLUE);

        for (int i = 0; i < numPoints - 1; i++) {
            int x1 = xOffset + (int) ((xValues[i] - xMin) * xScale);
            int y1 = getHeight() - yOffset - (int) ((yValues[i] - yMin) * yScale);
            int x2 = xOffset + (int) ((xValues[i + 1] - xMin) * xScale);
            int y2 = getHeight() - yOffset - (int) ((yValues[i + 1] - yMin) * yScale);

            g.drawLine(x1, y1, x2, y2);
        }

        g.setColor(Color.BLACK);
        g.drawLine(xOffset, getHeight() - yOffset, xOffset + graphWidth, getHeight() - yOffset); // X-axis
        g.drawLine(xOffset, yOffset, xOffset, getHeight() - yOffset); // Y-axis
    }
}
