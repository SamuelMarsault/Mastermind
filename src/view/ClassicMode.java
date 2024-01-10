package view;

import model.HintLine;

import java.awt.*;

public class ClassicMode implements HintDisplayMode{
    @Override
    public Color[] convertHintLine(HintLine hintLine) {
        Color[] colors = new Color[hintLine.hintLineLength()];
        int rightPawns = 0;
        int wrongPawns = 0;
        for(int i=0;i<hintLine.hintLineLength();i++){
            switch (hintLine.getHint(i)){
                case RIGHT_PLACE -> rightPawns++;
                case WRONG_PLACE -> wrongPawns++;
            }
            System.out.println(hintLine.getHint(i));
        }
        for(int i=0;i<colors.length;i++){
            if(i<rightPawns) colors[i] = Color.BLACK;
            else if (i<wrongPawns+rightPawns) colors[i] = Color.WHITE;
            else colors[i] = Color.LIGHT_GRAY;
        }
        return colors;
    }
}
