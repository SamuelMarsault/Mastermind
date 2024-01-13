package view;

import javax.swing.*;
import java.awt.*;

public class ClassicHintBox extends HintBox{

    /**
     * 
     * @param combinationLength
     */
    public ClassicHintBox(int combinationLength) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        int diameter = 25;
        for (int i = 0; i < combinationLength; i++) {
            Circle circle = new Circle(diameter, defaultColor);
            circle.setPreferredSize(new Dimension(diameter, diameter));
            add(circle);
        }
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    
    @Override
    public void setHintsColor(Color[] colors) {
        Circle circle;
        for (int i = 0; i < getComponents().length; i++) {
            circle = (Circle) getComponent(i);
            circle.setColor(colors[i]);
        }
    }
}
