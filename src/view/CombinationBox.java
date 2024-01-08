package view;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CombinationBox extends JPanel {
    private Color defaultColor;
    public CombinationBox(int combinationLenght, Color defaultColor){
        this.defaultColor = defaultColor;
        setLayout(new FlowLayout());
        int diameter = 25;
        for (int i = 0; i < combinationLenght; i++) {
            Circle circle = new Circle(diameter, defaultColor);
            circle.setPreferredSize(new Dimension(diameter, diameter));
            add(circle);
        }
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    public void setPawnColor(int n, Color color){
        if(n<getComponents().length){
            Circle circle = (Circle) getComponent(n);
            circle.setColor(color);
        }
    }
    public void setClickEvent(MouseAdapter mouseAdapter){
        for (Component component : getComponents()){
            component.addMouseListener(mouseAdapter);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (Component component : getComponents()){
            component.setEnabled(enabled);
        }
    }
}
