package view;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class Circle extends JPanel {
    private Color color;
    private int diameter;

    public Circle(int diameter, Color color){
        this.color = color;
        this.diameter = diameter;
        setBorder(new AbstractBorder() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawOval(x, y, width - 1, height - 1);
                    g2d.dispose();
                }

                @Override
                public Insets getBorderInsets(Component c) {
                    return new Insets(2, 2, 2, 2);
                }

                @Override
                public Insets getBorderInsets(Component c, Insets insets) {
                    insets.left = insets.top = insets.right = insets.bottom = 2;
                    return insets;
                }
            });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    public Color getColor() {
        return color;
    }
}
