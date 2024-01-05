package view;

import controler.GameController;
import controler.RoundController;
import model.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements RoundObserver,GameObserver {
    public GamePanel(RoundController roundController, GameController gameController){
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel gameLabel = new JLabel("GameLabel");
        gameLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // Définir une taille de police pour le label
        northPanel.add(gameLabel);
        add(northPanel, BorderLayout.NORTH);

        Font labelFont = new Font("Arial", Font.PLAIN, 10);
        
        JPanel gamePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gameConstraints = new GridBagConstraints();
        gameConstraints.fill = GridBagConstraints.HORIZONTAL;

        //Pour les boutton on mettra surement des images à la fin et pas du texte mais la c'est pour commencer 

        gameConstraints.gridx = 0;
        gameConstraints.gridy = 0;
        Button resetButton = new Button("reset Combinaison");
        //Ajouter action quand le reste sera présent
        gamePanel.add(resetButton, gameConstraints);

        gameConstraints.gridy = 1;
        JLabel scoreLabel = new JLabel("0");
        scoreLabel.setFont(labelFont);
        gamePanel.add(scoreLabel, gameConstraints);

        gameConstraints.gridy = 2;
        JLabel currentRoundLabel = new JLabel("1/ (a faire)");
        //a faire modifier quand finis design
        gamePanel.add(currentRoundLabel, gameConstraints);

        gameConstraints.gridx = 1;
        gameConstraints.gridy = 0;
        gameConstraints.gridwidth = 3;
        JPanel combinaisonsPanel = new JPanel();
        combinaisonsPanel.setLayout(new GridLayout(0,2));
        //!! A definir comment on veux que les pions soit, c'est à dire plein de pannel dans le pannel en FlowLayout... et pareil pour les indices et définir combien on en vois
        //!! On peux aussi faire 2 composant coller un pour la combinaison et l'autre pour les indices au lieu d'un seul qui en à 2. du coup gridwitch serais à 2 et pas 3
        gamePanel.add(combinaisonsPanel, gameConstraints);

        gameConstraints.gridx = 2;
        gameConstraints.gridy = 0;
        gameConstraints.gridwidth = 1;
        Button giveUpButton = new Button("abandonner la manche");
        //Ajouter action quand le reste sera présent
        gamePanel.add(giveUpButton, gameConstraints);

        gameConstraints.gridy = 1;
        //!! On met un FlowLayout ici pour contenir les choix de couleur possible ?

        gameConstraints.gridy = 2;
        Button ValidateButton = new Button("Valider");
        //Ajouter action quand le reste sera présent
        gamePanel.add(ValidateButton, gameConstraints);

        //!! La disposition n'est pas top on est d'accord, tu voie si j'ai oublier un composant à part la combinaison et aide ?

        //Je modifierais la position et complèterais quand on auras vu pour les question au dessus

        add(gamePanel, BorderLayout.CENTER);

        Button buttonNext = new Button("Next");
        buttonNext.setFont( new Font("Arial", Font.PLAIN, 20));
        buttonNext.addActionListener(actionEvent -> roundController.launchAttempt(new Pawn[]{}));
        add(buttonNext,BorderLayout.SOUTH);
        setVisible(true);
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
