package view;

import controler.GameController;
import controler.RoundController;
import model.*;

import javax.swing.*;

import java.awt.*;

public class GamePanel extends JPanel implements RoundObserver,GameObserver {
    private Color selectedColor;
    private JPanel gamePanel;
    private GameBoard gameBoard;
    private JLabel currentRoundLabel;
    private HintDisplayMode displayMode;
    public GamePanel(RoundController roundController, GameController gameController){
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel gameLabel = new JLabel("GameLabel");
        gameLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // Définir une taille de police pour le label
        northPanel.add(gameLabel);
        add(northPanel, BorderLayout.NORTH);

        Font labelFont = new Font("Arial", Font.PLAIN, 10);
        
        gamePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gameConstraints = new GridBagConstraints();
        gameConstraints.fill = GridBagConstraints.BOTH;

        //Pour les boutton on mettra surement des images à la fin et pas du texte mais la c'est pour commencer 

        gameConstraints.gridx = 0;
        gameConstraints.gridy = 0;
        Button resetButton = new Button("reset Combinaison");
        //Ajouter action quand le reste sera présent
        gamePanel.add(resetButton, gameConstraints);

        gameConstraints.gridx = 1;
        JLabel scoreLabel = new JLabel("0");
        scoreLabel.setFont(labelFont);
        gamePanel.add(scoreLabel, gameConstraints);

        gameConstraints.gridx = 2;
        currentRoundLabel = new JLabel();
        //a faire modifier quand finis design
        gamePanel.add(currentRoundLabel, gameConstraints);

        //Dans la boucle du nombre de tentatives : + rendre valeurs différentes longueur cobinaisons
        //Bordure provisoires à modifier + tard et régler problèmes centrage

        //!! A definir comment on veux que les pions soit, c'est à dire plein de pannel dans le pannel en FlowLayout... et pareil pour les indices et définir combien on en vois
        //!! On peux aussi faire 2 composant coller un pour la combinaison et l'autre pour les indices au lieu d'un seul qui en à 2. du coup gridwitch serais à 2 et pas 3

        gameConstraints.gridx = 0;
        gameConstraints.gridy = 2;
        gameConstraints.gridwidth = 1;
        Button giveUpButton = new Button("abandonner la manche");
        //Ajouter action quand le reste sera présent
        gamePanel.add(giveUpButton, gameConstraints);

        gameConstraints.gridx = 1;
        //!! On met un FlowLayout ici pour contenir les choix de couleur possible ?
        /*JPanel ColorPossibilities = new JPanel();
        ColorPossibilities.setLayout(new FlowLayout());

        for (int i = 0; i < 2; i++) {
            JPanel possibilitie = createRoundPanel(25, Color.BLUE, 2);
            ColorPossibilities.add(possibilitie);
        }
         for (int i = 0; i < 2; i++) {
            JPanel possibilitie = createRoundPanel(25, Color.RED, 2);
            ColorPossibilities.add(possibilitie);
        }

        gamePanel.add(ColorPossibilities, gameConstraints);
        */
        gameConstraints.gridx = 2;
        Button validateButton = new Button("Valider");
        validateButton.addActionListener(actionEvent -> roundController.launchAttempt(gameBoard.getCombination()));
        //Ajouter action quand le reste sera présent
        gamePanel.add(validateButton, gameConstraints);

        //!! La disposition n'est pas top on est d'accord, tu voie si j'ai oublier un composant à part la combinaison et aide ?

        //Je modifierais la position et complèterais quand on auras vu pour les question au dessus

        add(gamePanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    
    
    

    @Override
    public void reactToAttempt(int attemptId, HintLine hintLine) {
        gameBoard.setHints(attemptId, displayMode.convertHintLine(hintLine));
        gameBoard.prepareAttempt(attemptId);
    }

    @Override
    public void reactToRoundEnd(boolean roundWon, int score) {
        for (Component component : getComponents())
            component.setVisible(false);
    }

    @Override
    public void reactToGameStart(int roundNumber, int attemptNumber, int pawnNumber, int combinationLenght, Mode mode) {
        GridBagConstraints gameConstraints = new GridBagConstraints();
        gameConstraints.fill = GridBagConstraints.BOTH;
        gameConstraints.gridx = 0;
        gameConstraints.gridy = 1;
        gameConstraints.gridwidth = 5;
        if(mode==Mode.CLASSIC)
            displayMode = new ClassicMode();
        else
            displayMode = new EasyMode();
        gameBoard = new GameBoard(combinationLenght, attemptNumber, pawnNumber);
        gameBoard.prepareAttempt(0);
         currentRoundLabel.setText("0 / "+roundNumber);
        gamePanel.add(gameBoard, gameConstraints);
    }

    @Override
    public void reactToGameEnd(int score) {

    }
}
