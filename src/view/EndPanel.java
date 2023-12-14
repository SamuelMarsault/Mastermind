package view;

import controler.GameController;
import model.GameObserver;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel implements GameObserver {
    public EndPanel(GameController gameController){
        add(new Label("EndPanel"),BorderLayout.SOUTH);
    }

    @Override
    public void reactToGameStart(int roundNumber, int attemptNumber, int pawnNumber) {

    }

    @Override
    public void reactToGameEnd(int score) {

    }
}
