package view;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HintBox extends JPanel {
    static public Color defaultColor = Color.LIGHT_GRAY;
    public HintBox(int combinationLenght){
        setLayout(new FlowLayout());
        for (int i = 0; i < combinationLenght; i++) {
            createRoundPanel(15, defaultColor, 0);
        }
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    public void createRoundPanel(int diameter, Color color, int option) {
        Circle circle = new Circle(diameter, color);
        circle.setPreferredSize(new Dimension(diameter, diameter));
        add(circle);
    }
    public void setHintsColor(Color[] colors){
        for (int i = 0; i < colors.length; i++) {
            Circle circle = (Circle) getComponent(i);
            circle.setColor(colors[i]);
        }
    }
}
