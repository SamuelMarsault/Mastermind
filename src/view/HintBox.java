package view;

import javax.swing.*;
import java.awt.*;

public abstract class HintBox extends JPanel {
    static public Color defaultColor = Color.LIGHT_GRAY;
    
    /**
     * 
     * @param colors
     */
    public abstract void setHintsColor(Color[] colors);
}
