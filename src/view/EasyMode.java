package view;

import model.HintLine;

import java.awt.*;

public class EasyMode implements HintDisplayMode{

    /**
     *
     * @param hintLine
     * @return
     */
    @Override
    public Color[] convertHintLine(HintLine hintLine) {
        Color[] colors = new Color[hintLine.hintLineLength()];
        for(int i=0;i<colors.length;i++){
            switch (hintLine.getHint(i)){
                case RIGHT_PLACE -> colors[i] = Color.BLACK;
                case WRONG_PLACE -> colors[i] = Color.WHITE;
                default -> colors[i] = Color.LIGHT_GRAY;
            }
        }
        return colors;
    }
}
