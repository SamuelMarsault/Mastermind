package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class CombinationBox extends JPanel {

    /**
     * Define the appearance of the combination box and what's inside it
     * @param combinationLenght
     * @param defaultColor
     */
    public CombinationBox(int combinationLenght, Color defaultColor){
        setLayout(new FlowLayout());
        int diameter = 35;
        for (int i = 0; i < combinationLenght; i++) {
            Circle circle = new Circle(diameter, defaultColor);
            circle.setCursor(new Cursor(12));
            circle.setPreferredSize(new Dimension(diameter, diameter));
            add(circle);
        }
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Define the appearance of the combination box for the summary
     * @param colors
     */
    public CombinationBox(Color[] colors) {
        setLayout(new FlowLayout());
        int diameter = 32;
        for (int i = 0; i < colors.length; i++) {
            Circle circle = new Circle(diameter, colors[i]);
            circle.setPreferredSize(new Dimension(diameter, diameter));
            add(circle);
        }
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Define the color of one of the circles in the combination
     * @param n
     * @param color
     */
    public void setPawnColor(int n, Color color){
        if(n<getComponents().length){
            Circle circle = (Circle) getComponent(n);
            circle.setColor(color);
        }
    }

    /**
     * Create event to retrieve or give color
     * @param mouseAdapter
     */
    public void setClickEvent(MouseAdapter mouseAdapter){
        for (Component component : getComponents())
            component.addMouseListener(mouseAdapter);
    }

    /**
     * 
     */
    public void unsetClickEvent(){
        for (Component component : getComponents()) {
            for (MouseListener l : component.getMouseListeners())
                component.removeMouseListener(l);
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
