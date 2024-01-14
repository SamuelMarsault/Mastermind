package view;

import model.HintLine;
import java.awt.*;

public interface HintDisplayMode {
    
    /**
     * Convert HintLine into colors
     * @param hintLine
     * @return a vector containing colors
     */
    Color[] convertHintLine(HintLine hintLine);
}
