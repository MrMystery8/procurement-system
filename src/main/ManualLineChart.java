package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Map;

public class ManualLineChart extends JPanel {

    private Map<String, Double> data;
    private String title;
    private String xAxisLabel;
    private String yAxisLabel;

    public ManualLineChart(Map<String, Double> data, String title, String xAxisLabel, String yAxisLabel) {
        this.data = data;
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        setPreferredSize(new Dimension(300, 220)); // Set preferred size
        setBackground(Color.WHITE);

        // Add a border to the chart panel
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Add padding
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Basic settings (adjusted for smaller size and line chart)
        int startX = 110;   // Adjust startX if needed
        int startY = 200;  // Adjust startY if you decrease height
        int pointWidth = 10; // Width of the points on the line
        int pointGap = 60; // Horizontal gap between points (adjust as needed)

        // Find maximum value for scaling
        double maxValue = data.values().stream().max(Double::compare).orElse(0.0);

        // Draw title (smaller font)
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString(title, startX, 30);

        // Draw axes labels (smaller font)
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        g2d.drawString(yAxisLabel, 20, startY / 2); // You might need to rotate this
        g2d.drawString(xAxisLabel, startX + (data.size() * pointGap) / 2 - 20, startY + 30);

        // Draw x-axis
        g2d.setColor(Color.BLACK);
        g2d.drawLine(startX - 10, startY, startX + (int)(data.size() * pointGap), startY);

        // Draw y-axis
        g2d.setColor(Color.BLACK);
        g2d.drawLine(startX - 10, startY, startX - 10, 50); // Adjust y-coordinate for top

        // Corrected Y-Axis Label Calculation
        int numYLabels = 5; // Number of labels on the Y-axis
        double yLabelInterval = maxValue / numYLabels; // Calculate interval between labels
        int yAxisLabelY = startY;
        int yAxisLabelGap = (startY - 50) / numYLabels; // Gap between labels (pixels)

        FontMetrics fm = g2d.getFontMetrics();
        for (int i = 0; i <= numYLabels; i++) {
            double yLabelValue = i * yLabelInterval; // Calculate label value
            String yLabel = String.format("%.1f", yLabelValue); // Format with 1 decimal place
            int labelWidth = fm.stringWidth(yLabel);
            g2d.drawString(yLabel, startX - 15 - labelWidth, yAxisLabelY + 5); // Adjust x-position
            yAxisLabelY -= yAxisLabelGap;
        }

        // Draw lines and points
        int x = startX;
        Point prevPoint = null; // Keep track of the previous point

        // Sort the data entries by their string keys (e.g., month, week)
        Object[] sortedEntries = data.entrySet().toArray();
        java.util.Arrays.sort(sortedEntries, (e1, e2) -> {
            String key1 = ((Map.Entry<String, Double>) e1).getKey();
            String key2 = ((Map.Entry<String, Double>) e2).getKey();
            return key1.compareTo(key2);
        });

        for (Object entryObj : sortedEntries) {
            Map.Entry<String, Double> entry = (Map.Entry<String, Double>) entryObj;
            String label = entry.getKey();
            double value = entry.getValue();

            // Calculate point coordinates
            int pointY = startY - (int) Math.round((value / maxValue) * (startY - 50));

            // Draw the line
            if (prevPoint != null) {
                g2d.setColor(Color.BLUE);
                g2d.drawLine(prevPoint.x + pointWidth / 2, prevPoint.y, x + pointWidth / 2, pointY);
            }

            // Draw the point
            g2d.setColor(Color.BLUE);
            g2d.fillOval(x, pointY - pointWidth / 2, pointWidth, pointWidth);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(x, pointY - pointWidth / 2, pointWidth, pointWidth);

            // Draw label below the x-axis
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 10));
            int labelWidth = fm.stringWidth(label);
            int labelX = x + (pointWidth - labelWidth) / 2; // Center the label
            g2d.drawString(label, labelX, startY + 15);

            // Update prevPoint for the next iteration
            prevPoint = new Point(x, pointY);

            x += pointGap;
        }
    }
}