package view;

import controler.GameController;
import controler.RoundController;
import model.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements RoundObserver,GameObserver {
    public GamePanel(RoundController roundController, GameController gameController){
        add(new Label("GamePanel"),BorderLayout.SOUTH);
        Button button = new Button("Next");
        button.addActionListener(actionEvent -> roundController.launchAttempt(new Pawn[]{}));
        add(button,BorderLayout.SOUTH);
    }

    @Override
    public void reactToAttempt(Combination combination, HintLine hintLine) {

    }

    @Override
    public void reactToRoundEnd(boolean roundWon, int score) {

    }

    @Override
    public void reactToGameStart(int roundNumber, int attemptNumber, int pawnNumber) {

    }

    @Override
    public void reactToGameEnd(int score) {

    }
}
