package view;

import javax.swing.*;
import java.awt.*;

public class NumericHintBox extends HintBox{
    private JLabel rightPlacedPawns = new JLabel("0");
    private JLabel wrongPlacedPawns = new JLabel("0");
    private Color rightPlacedColor;
    private Color wrongPlacedColor;

    /**
     * NumericHintBox builder
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
        add(new JLabel(new ImageIcon(new ImageIcon(
                        this.getClass().getClassLoader().getResource("validation.png")
                ).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH))));
        add(rightPlacedPawns);
        rightPlacedPawns.setBorder(BorderFactory.createEmptyBorder(0,5,0,15));
        add(new JLabel(new ImageIcon(
                new ImageIcon(
                        this.getClass().getClassLoader().getResource("question_mark.png")
                ).getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH))));
        add(wrongPlacedPawns);
        wrongPlacedPawns.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Display the number of right and wrong placed pawns
     * @param colors
     */
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
