package view;

import model.Pawn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel {
    private Color selectedColor;
    private JPanel attemptPanel;
    public GameBoard(int combinationLenght, int attemptNumber, int pawnNumber) {
        setLayout(new BorderLayout());
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
                combination = new CombinationBox(combinationLenght, Color.BLACK);
                combination.setEnabled(false);
            }
            else{
                combination = new CombinationBox(combinationLenght, Color.LIGHT_GRAY);
                combination.setEnabled(true);
            }
            attemptPanel.add(combination, constraints);
            constraints.gridwidth = 1;
            constraints.gridx = 2;
            constraints.weightx = 0;
            attemptPanel.add(new HintBox(combinationLenght), constraints);
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
        add(new Button("Reset combinaison"),BorderLayout.NORTH);
        add(attemptPanel,BorderLayout.CENTER);
    }
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
    public void prepareAttempt(int attemptId){
        for (int i = 0; i < attemptPanel.getComponents().length-1; i++) {
            if (attemptPanel.getComponent(i).getClass()==CombinationBox.class)
            {
                CombinationBox combinationBox = (CombinationBox) attemptPanel.getComponent(i);
                if(i!=attemptPanel.getComponents().length-attemptId-3)
                    combinationBox.setClickEvent(null);
                else
                    combinationBox.setClickEvent(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Circle circle = (Circle)e.getComponent();
                        if(selectedColor!=null){
                            if (selectedColor!=circle.getColor())
                                circle.setColor(selectedColor);
                            else {
                                circle.setColor(Color.LIGHT_GRAY);
                            }
                        }
                    }
                });
            }
        }
    }
}
