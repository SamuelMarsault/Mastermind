package view;

import javax.swing.*;
import java.awt.*;

public class NumericHintBox extends HintBox{
    private JLabel rightPlacedPawns = new JLabel("0");
    private JLabel wrongPlacedPawns = new JLabel("0");
    private Color rightPlacedColor;
    private Color wrongPlacedColor;

    /**
     * Configures numerical index display mode
     * @param rightPlacedColor
     * @param wrongPlacedColor
     */
    public NumericHintBox(Color rightPlacedColor, Color wrongPlacedColor) {
        setLayout(new FlowLayout());
        this.rightPlacedColor = rightPlacedColor;
        this.wrongPlacedColor = wrongPlacedColor;
        Font font = new Font("Arial",Font.PLAIN,22);
        rightPlacedPawns.setFont(font);
        wrongPlacedPawns.setFont(font);
        add(rightPlacedPawns);
        add(wrongPlacedPawns);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    @Override
    public void setHintsColor(Color[] colors) {
        int rightPlaced = 0;
        int wrongPlaced = 0;
        for (Color color : colors)
        {
            if(color==rightPlacedColor) rightPlaced++;
            else if (color==wrongPlacedColor) wrongPlaced++;
        }
        rightPlacedPawns.setText(String.valueOf(rightPlaced));
        wrongPlacedPawns.setText(String.valueOf(wrongPlaced));
    }
}
