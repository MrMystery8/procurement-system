package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Map;

public class ManualBarChart extends JPanel {

    private Map<String, Double> data;
    private String title;
    private String xAxisLabel;
    private String yAxisLabel;

    public ManualBarChart(Map<String, Double> data, String title, String xAxisLabel, String yAxisLabel) {
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

        // Basic settings (adjusted for smaller size)
        int barWidth = 60; // Smaller bar width
        int startX = 110;   // Adjust startX if needed
        int startY = 200;  // Adjust startY if you decrease height
        int gap = 30;      // Smaller gap between bars

        // Find maximum value for scaling
        double maxValue = data.values().stream().max(Double::compare).orElse(0.0);

        // Draw title (smaller font)
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString(title, startX, 30);

        // Draw axes labels (smaller font)
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        g2d.drawString(yAxisLabel, 20, startY / 2); // You might need to rotate this
        g2d.drawString(xAxisLabel, startX + (data.size() * (barWidth + gap)) / 2 - 20, startY + 30);

        // Draw x-axis
        g2d.setColor(Color.BLACK);
        g2d.drawLine(startX - 10, startY, startX + (int) (data.size() * (barWidth + gap)), startY);

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

        // Draw bars and labels
        int x = startX;
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            String label = entry.getKey();
            double value = entry.getValue();

            // Corrected Bar Height Calculation
            int barHeight = (int) Math.round((value / maxValue) * (startY - 50));

            // Draw bar with black border
            g2d.setColor(Color.BLUE);
            g2d.fillRect(x, startY - barHeight, barWidth, barHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, startY - barHeight, barWidth, barHeight);

            // Draw label below the bar
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 10)); // Smaller font for bar labels
            int labelWidth = fm.stringWidth(label);

            // Calculate label position correctly
            int labelX = x + (barWidth - labelWidth) / 2; // Center the label
            int barLabelY = startY + 15;

            g2d.drawString(label, labelX, barLabelY);

            x += barWidth + gap;
        }
    }
}