package view;

import controler.GameController;
import controler.RoundController;
import model.*;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class GamePanel extends JLayeredPane implements RoundObserver,GameObserver {
    private JPanel gamePanel;
    private GameBoard gameBoard;
    private JLabel currentRoundLabel;
    private JLabel scoreLabel;
    private HintDisplayMode displayMode;
    private JPanel popUp;
    private GameController gameController;
    
    /**
     * Create and configure game panel component
     * @param roundController
     * @param gameController
     */
    public GamePanel(RoundController roundController, GameController gameController) throws IOException {
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel gameLabel = new JLabel("À vous de jouer !");
        gameLabel.setFont(new Font("Constantia", Font.BOLD, 30));
        northPanel.add(gameLabel);
        add(northPanel, BorderLayout.NORTH);

        JLayeredPane jLayeredPane = new JLayeredPane();
        popUp = new StartPanel(null);
        jLayeredPane.add(popUp);
        add(jLayeredPane,BorderLayout.CENTER);
        jLayeredPane.setLayer(popUp, 1);

        Font labelFont = new Font("Constantia", Font.PLAIN, 15);
        
        gamePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gameConstraints = new GridBagConstraints();
        gameConstraints.fill = GridBagConstraints.BOTH;

        gameConstraints.gridy = 0;
        gameConstraints.gridx = 0;
        gameConstraints.gridwidth = 1;

        scoreLabel = new JLabel("score : 0");
        scoreLabel.setFont(labelFont);
        gamePanel.add(scoreLabel, gameConstraints);

        gameConstraints.gridx = 4;
        gameConstraints.fill = GridBagConstraints.CENTER;
        currentRoundLabel = new JLabel();
        gamePanel.add(currentRoundLabel, gameConstraints);

        gameConstraints.fill = GridBagConstraints.BOTH;
        gameConstraints.gridwidth = 2;
        gameConstraints.gridx = 0;
        gameConstraints.gridy = 3;

        JButton giveUpButton = new JButton(resizeImage(new ImageIcon(this.getClass().getClassLoader().getResource("give_up.png")),25,25));
        giveUpButton.setCursor(new Cursor(12));
        giveUpButton.addActionListener(actionEvent -> {
            gameController.giveUpRound();
        });
        gamePanel.add(giveUpButton, gameConstraints);

        gameConstraints.gridx = 3;
        JButton validateButton = new JButton(resizeImage(new ImageIcon(this.getClass().getClassLoader().getResource("check.png")),25,25));
        validateButton.setCursor(new Cursor(12));
        validateButton.addActionListener(actionEvent -> {
            Color[] colors = gameBoard.getColors();
            boolean hasIncompleteCombination = false;

            for (int i = 0; i < colors.length && !hasIncompleteCombination; i++) {
                if (colors[i].equals(Color.WHITE) || colors[i].equals(Color.LIGHT_GRAY)) {
                    hasIncompleteCombination = true;
                }
            }

            if (hasIncompleteCombination) {
                int result = JOptionPane.showOptionDialog(
                        this,
                        "Attention ! Vous n'avez pas complété entièrement la combinaison.",
                        "Erreur combinaison",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        new Object[]{"OK"},
                        "OK"
                );
                if (result == 0) {
                    return;
                }
            }
            roundController.launchAttempt(gameBoard.getCombination());
        });
        
        gamePanel.add(validateButton, gameConstraints);

        add(gamePanel, BorderLayout.CENTER);
        this.gameController = gameController;
        setVisible(true);
    }

    /**
     * Redefine image size
     * @param image
     * @param height
     * @param width
     * @return resized imageIcon
     */
    private ImageIcon resizeImage(ImageIcon image,int height, int width){
        Image originalImage = image.getImage();

        int newWidth = width;
        int newHeight = height;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
    }

    /**
     * React to attempt
     * @param attemptId
     * @param hintLine
     */
    @Override
    public void reactToAttempt(int attemptId, HintLine hintLine) {
        gameBoard.setHints(attemptId-1, displayMode.convertHintLine(hintLine));
        gameBoard.prepareAttempt(attemptId);
    }

    /**
     * React at the end of round
     * @param roundWon
     * @param score
     * @param secretCombination
     */
    @Override
    public void reactToRoundEnd(boolean roundWon, int score, Combination secretCombination) {
        gameBoard.resetBoard();
        StringBuilder roundNumber = new StringBuilder();
        String[] oldRoundNumber = currentRoundLabel.getText().split(" ");
        roundNumber.append(Integer.parseInt(oldRoundNumber[0])+1);
        for (int i=1;i<oldRoundNumber.length;i++)
            roundNumber.append(" "+oldRoundNumber[i]);
        currentRoundLabel.setText(roundNumber.toString());

        StringBuilder scoreString = new StringBuilder();
        String[] oldScoreString = scoreLabel.getText().split(" ");
        for (int i=0;i<oldScoreString.length-1;i++)
            scoreString.append(oldScoreString[i]+" ");
        scoreString.append(Integer.parseInt(oldScoreString[oldScoreString.length-1])+score);
        scoreLabel.setText(scoreString.toString());
        this.gameController.nextRound();
    }

    /**
     * React at the start of the game
     * @param roundNumber
     * @param attemptNumber
     * @param pawnNumber
     * @param combinationLenght
     * @param mode
     */
    @Override
    public void reactToGameStart(int roundNumber, int attemptNumber, int pawnNumber, int combinationLenght, Mode mode) {
        GridBagConstraints gameConstraints = new GridBagConstraints();
        gameConstraints.fill = GridBagConstraints.BOTH;
        gameConstraints.gridx = 0;
        gameConstraints.gridy = 2;
        gameConstraints.gridwidth = 5;
        if(mode==Mode.CLASSIC)
            displayMode = new ClassicMode();
        else
            displayMode = new EasyMode();
        if(gameBoard!=null)
            gamePanel.remove(gameBoard);
        gameBoard = new GameBoard(combinationLenght, attemptNumber, pawnNumber, mode);
        currentRoundLabel.setText("1 / "+roundNumber);
        currentRoundLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        scoreLabel.setText("score : 0");
        gamePanel.add(gameBoard, gameConstraints);
    }

    /**
     * React at the end of the game
     * @param score
     * @param playeurName
     */
    @Override
    public void reactToGameEnd(int score, String playeurName) {
        gameController.endGame();
    }
}
