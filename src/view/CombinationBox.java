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
        for (int i = 0; i < combinationLenght; i++) {
            createRoundPanel(25, defaultColor, 0);
        }
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    public void createRoundPanel(int diameter, Color color, int option) {
        Circle circle = new Circle(diameter, color);
        circle.setPreferredSize(new Dimension(diameter, diameter));
        add(circle);
    }
    public void setPawnColor(int n, Color color){
        Circle circle = (Circle) getComponent(n);
        circle.setColor(color);
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
