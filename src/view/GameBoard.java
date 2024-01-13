package view;

import model.Mode;
import model.Pawn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class GameBoard extends JPanel {
    private Color selectedColor;
    private Color inactiveColor = Color.LIGHT_GRAY;
    private Color defaultColor = Color.WHITE;
    private JPanel attemptPanel;
    private int currentAttempt;
    
    /**
     * 
     * @param combinationLenght
     * @param attemptNumber
     * @param pawnNumber
     * @param mode
     */
    public GameBoard(int combinationLenght, int attemptNumber, int pawnNumber, Mode mode) {
        setLayout(new BorderLayout());

        //Les tentatives
        attemptPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        for (int i = 0; i < attemptNumber; i++) {
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridwidth = 2;
            constraints.gridy = i;
            constraints.gridx = 0;
            constraints.weightx = 1;
            CombinationBox combination;
            if(i!=attemptNumber-1){
                combination = new CombinationBox(combinationLenght, inactiveColor);
                combination.setEnabled(false);
            }
            else{
                combination = new CombinationBox(combinationLenght, defaultColor);
                combination.setEnabled(true);
            }
            attemptPanel.add(combination, constraints);
            constraints.gridwidth = 1;
            constraints.gridx = 2;
            constraints.weightx = 0;
            HintBox hintBox;
            if(mode==Mode.NUMERIC) hintBox = new NumericHintBox(Color.BLACK, Color.WHITE);
            else hintBox = new ClassicHintBox(combinationLenght);
            attemptPanel.add(hintBox, constraints);
        }
        constraints.gridwidth = 3;
        constraints.gridy = attemptNumber;
        constraints.gridx = 0;
        CombinationBox palette = new CombinationBox(pawnNumber, Color.WHITE);
        setUpPalette(palette);
        palette.setClickEvent(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedColor = ((Circle)e.getComponent()).getColor();
            }
        });
        attemptPanel.add(palette,constraints);
        JButton resetComb = new JButton("Reset combinaison");
        resetComb.setCursor(new Cursor(12));
        resetComb.setFont(new Font("Constantia",Font.PLAIN,15));
        resetComb.addActionListener(
                actionEvent -> {
                    CombinationBox combinationBox = (CombinationBox) attemptPanel.getComponent(currentAttempt);
                    for (int i=0;i<combinationLenght;i++)
                        combinationBox.setPawnColor(i,defaultColor);
                }
        );
        this.prepareAttempt(0);
        add(resetComb,BorderLayout.NORTH);
        add(attemptPanel,BorderLayout.CENTER);
    }

    /**
     * 
     * @param palette
     */
    public void setUpPalette(CombinationBox palette){
        Color color;
        for (int i=0;i<Pawn.values().length;i++){
            Pawn pawn = Pawn.values()[i];
            switch (pawn){
                case RED -> color = Color.RED;
                case GREEN -> color = Color.GREEN;
                case BLUE -> color = Color.BLUE;
                case YELLOW -> color = Color.YELLOW;
                case BLACK -> color = Color.BLACK;
                case ORANGE -> color = Color.ORANGE;
                case PURPLE -> color = Color.MAGENTA;
                case PINK -> color = Color.PINK;
                default -> color = Color.WHITE;
            }
            palette.setPawnColor(i,color);
        }
    }
    
    /**
     * 
     * @param attemptId
     */
    public void prepareAttempt(int attemptId){
        currentAttempt = attemptPanel.getComponents().length-3-attemptId*2;
        int oldAttempt = attemptPanel.getComponents().length-3-(attemptId-1)*2;
        if(currentAttempt<0)
            return;
            CombinationBox combinationBox = (CombinationBox) attemptPanel.getComponent(currentAttempt);
            CombinationBox oldCombinationBox = (CombinationBox) attemptPanel.getComponent(oldAttempt);
            
            for (int i = 0; i < combinationBox.getComponents().length; i++) {
                Circle circle = (Circle) oldCombinationBox.getComponent(i);
            
                if (attemptId == 0) {
                    combinationBox.setPawnColor(i, defaultColor);
                } else {
                    combinationBox.setPawnColor(i, circle.getColor());
                    oldCombinationBox.unsetClickEvent();
                }
            }
            
        combinationBox.setClickEvent(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Circle circle = (Circle)e.getComponent();
                if(selectedColor!=null){
                    if (selectedColor!=circle.getColor())
                        circle.setColor(selectedColor);
                    else {
                        circle.setColor(defaultColor);
                    }
                }
            }
        });
    }

    /**
     * 
     * @param hintsId
     * @param colors
     */
    public void setHints(int hintsId, Color[] colors){
        int index = attemptPanel.getComponents().length-2-hintsId*2;
        if(index>0){
            HintBox hintBox = (HintBox) attemptPanel.getComponent(index);
            hintBox.setHintsColor(colors);
        }
    }

    /**
     * 
     * @return pawns
     */
    public Pawn[] getCombination(){
        CombinationBox combinationBox = (CombinationBox) attemptPanel.getComponent(currentAttempt);
        Pawn[] pawns = new Pawn[combinationBox.getComponents().length];
        for (int i=0;i<combinationBox.getComponents().length;i++){
            Circle circle = (Circle) combinationBox.getComponent(i);
            //Il n'était pas possible de mettre de switch car Color n'est pas une énum
            if (circle.getColor().equals(Color.RED))
                pawns[i] = Pawn.RED;
            else if (circle.getColor().equals(Color.GREEN))
                pawns[i] = Pawn.GREEN;
            else if (circle.getColor().equals(Color.BLUE))
                pawns[i] = Pawn.BLUE;
            else if (circle.getColor().equals(Color.YELLOW))
                pawns[i] = Pawn.YELLOW;
            else if (circle.getColor().equals(Color.BLACK))
                pawns[i] = Pawn.BLACK;
            else if (circle.getColor().equals(Color.ORANGE))
                pawns[i] = Pawn.ORANGE;
            else if (circle.getColor().equals(Color.MAGENTA))
                pawns[i] = Pawn.PURPLE;
            else if (circle.getColor().equals(Color.PINK))
                pawns[i] = Pawn.PINK;
            else if (circle.getColor().equals(Color.BLACK))
                pawns[i] = Pawn.BLACK;
        }
        return pawns;
    }

    /**
     * 
     * @return color
     */
    public Color[] getColor(){
        CombinationBox combinationBox = (CombinationBox) attemptPanel.getComponent(currentAttempt);
        Color[] color = new Color[combinationBox.getComponents().length];
        for (int i=0;i<combinationBox.getComponents().length;i++){
            Circle circle = (Circle) combinationBox.getComponent(i);
            color[i] = circle.getColor();
        }
        return color;
    }

    /**
     * 
     */
    public void resetBoard(){
        for (int i = 0; i < attemptPanel.getComponents().length-1; i++) {
            if (attemptPanel.getComponent(i).getClass()==CombinationBox.class){
                CombinationBox combinationBox = (CombinationBox) attemptPanel.getComponent(i);
                for (int j = 0; j < combinationBox.getComponents().length; j++)
                    combinationBox.setPawnColor(j,inactiveColor);
                    combinationBox.setClickEvent(null);
            }
            else {
                HintBox hintBox = (HintBox) attemptPanel.getComponent(i);
                Color[] colors = new Color[hintBox.getComponents().length];
                Arrays.fill(colors, inactiveColor);
                hintBox.setHintsColor(colors);
            }
        }
        for (int i = 0; i < attemptPanel.getComponents().length-1; i+=2) {
            CombinationBox combinationBox = (CombinationBox) attemptPanel.getComponent(i);
            combinationBox.unsetClickEvent();
        }
        prepareAttempt(0);
    }
}
