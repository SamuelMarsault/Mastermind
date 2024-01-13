package view;

import javax.swing.*;
import java.awt.*;

public abstract class HintBox extends JPanel {
    static public Color defaultColor = Color.LIGHT_GRAY;
    
    /**
     * Redefine index colors and displays according to the class
     * @param colors
     */
    public abstract void setHintsColor(Color[] colors);
}
