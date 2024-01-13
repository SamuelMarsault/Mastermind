package view;

import model.HintLine;
import java.awt.*;

public interface HintDisplayMode {
    
    /**
     * 
     * @param hintLine
     * @return
     */
    Color[] convertHintLine(HintLine hintLine);
}
