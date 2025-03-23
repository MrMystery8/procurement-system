package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;

public class ManualPieChart extends JPanel {

    private Map<String, Double> data;
    private String title;

    public ManualPieChart(Map<String, Double> data, String title) {
        this.data = data;
        this.title = title;
        setPreferredSize(new Dimension(300, 220)); // Same as ManualBarChart
        setBackground(Color.WHITE);

        // Add a border like in ManualBarChart
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Add padding
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Basic settings (adjusted for consistency)
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 3; // Smaller pie

        // Draw title (consistent font)
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g2d.getFontMetrics();
        int titleWidth = fm.stringWidth(title);
        g2d.drawString(title, centerX - titleWidth / 2, 20);

        // Calculate total for percentages
        double total = data.values().stream().mapToDouble(Double::doubleValue).sum();

        // Draw pie slices and legend
        double startAngle = 0;
        int legendX = centerX + radius + 20;
        int legendY = centerY - (data.size() * 20) / 2; // Adjust based on number of entries

        // Use a fixed set of colors
        Color[] colors = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA, Color.ORANGE, Color.PINK};
        int colorIndex = 0;

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            double value = entry.getValue();
            double angle = (value / total) * 360;

            // Draw slice
            g2d.setColor(colors[colorIndex % colors.length]);
            g2d.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, (int) startAngle, (int) angle);

            // Draw legend item
            g2d.fillRect(legendX, legendY, 10, 10);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, 10)); // Smaller font for legend
            DecimalFormat df = new DecimalFormat("#.##"); // Format percentages
            String label = entry.getKey() + " (" + df.format((value / total) * 100) + "%)";
            g2d.drawString(label, legendX + 15, legendY + 10);

            startAngle += angle;
            legendY += 20;
            colorIndex++;
        }
    }
}