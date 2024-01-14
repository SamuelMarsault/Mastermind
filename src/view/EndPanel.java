package view;

import controler.GameController;
import model.*;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel implements GameObserver, RoundObserver {
    private JLabel scoreLabel;
    private JLabel ggLabel;
    private JPanel scrollPanel;
    private int roundsWon;

    /**
     * Create and configure end-of-game panel component
     * @param gameController
     */
    public EndPanel(GameController gameController){
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(0, 1));
        JLabel endLabel = new JLabel("Fin de la partie !");
        endLabel.setFont(new Font("Constantia", Font.BOLD, 30));

        // Centrer verticalement chaque composant dans le GridLayout
        JPanel endLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        endLabelPanel.add(endLabel);

        // Ajout d'un espace vertical entre les deux labels
        endLabelPanel.add(Box.createVerticalStrut(10));

        this.ggLabel = new JLabel("GG Joueur");
        ggLabel.setFont(new Font("Constantia", Font.PLAIN, 20));
        JPanel ggLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ggLabelPanel.add(ggLabel);

        scoreLabel = new JLabel("Score : xxx");
        scoreLabel.setFont(new Font("Constantia", Font.PLAIN, 25));
        JPanel scoreLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scoreLabelPanel.add(scoreLabel);

        // Ajout panels au GridLayout
        northPanel.add(endLabelPanel);
        northPanel.add(ggLabelPanel);
        northPanel.add(scoreLabelPanel);

        add(northPanel, BorderLayout.NORTH);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scrollPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 2);
        gridLayout.setHgap(20);
        scrollPanel.setLayout(gridLayout);
    

        JScrollPane jScrollPane = new JScrollPane(scrollPanel);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        scorePanel.add(jScrollPane);
        add(scorePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton menuButton = new JButton(resizeImage(new ImageIcon("image_jeu/house.png"),40,40));
        menuButton.setCursor(new Cursor(12));
        menuButton.addActionListener(actionEvent -> {
            gameController.returnToMenu();
        });
        buttonPanel.add(menuButton);

        JButton exitButton = new JButton(resizeImage(new ImageIcon("image_jeu/exit.png"),40,40));
        exitButton.setCursor(new Cursor(12));
        exitButton.addActionListener(actionEvent -> {
            System.exit(0);
        });
        buttonPanel.add(Box.createHorizontalStrut(223)); //Trouver meilleure méthode plus tard et augmenter taille boutton
        buttonPanel.add(exitButton);
        
        JButton restartButton = new JButton(resizeImage(new ImageIcon("image_jeu/restart.png"),40,40));
        restartButton.setCursor(new Cursor(12));
        restartButton.addActionListener(actionEvent -> {
            gameController.resetGame();
        });
        buttonPanel.add(Box.createHorizontalStrut(223)); //Trouver meilleure méthode plus tard et augmenter taille boutton
        buttonPanel.add(restartButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Change image size
     * @param image
     * @param height
     * @param width
     * @return new imageIcon
     */
    private ImageIcon resizeImage(ImageIcon image,int height, int width){
        Image originalImage = image.getImage();

        int newWidth = width;
        int newHeight = height;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
    }

    /**
     * React to game start
     * @param roundNumber
     * @param attemptNumber
     * @param pawnNumber
     * @param combinationLenght
     * @param mode
     */
    @Override
    public void reactToGameStart(int roundNumber, int attemptNumber, int pawnNumber, int combinationLenght, Mode mode) {
        scrollPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
        scrollPanel.removeAll();
        roundsWon = 0;
    }

    /**
     * React at end of game
     * @param score
     * @param playeurName
     */
    @Override
    public void reactToGameEnd(int score, String playeurName) {
         scoreLabel.setText("SCORE FINAL : "+score);
         if(roundsWon>=scrollPanel.getComponentCount()/2)
             ggLabel.setText("Bravo "+playeurName+"\nVous avez gagné");
         else
             ggLabel.setText("Vous avez perdu "+playeurName+"...\nMais ne vous découragez pas !");
    }

    /**
     * React to attempt
     * @param attemptId
     * @param hintLine
     */
    @Override
    public void reactToAttempt(int attemptId, HintLine hintLine) {}

    /**
     * React at end of round
     * @param roundWon
     * @param score
     * @param secretCombination
     */
    @Override
    public void reactToRoundEnd(boolean roundWon, int score, Combination secretCombination) {
        Color[] colors = new Color[secretCombination.getCombinationLength()];
        for (int i=0;i<colors.length;i++){
            switch (secretCombination.getPawn(i)){
                case RED -> colors[i] = Color.RED;
                case GREEN -> colors[i] = Color.GREEN;
                case BLUE -> colors[i] = Color.BLUE;
                case YELLOW -> colors[i] = Color.YELLOW;
                case BLACK -> colors[i] = Color.BLACK;
                case ORANGE -> colors[i] = Color.ORANGE;
                case PURPLE -> colors[i] = Color.MAGENTA;
                case PINK -> colors[i] = Color.PINK;
                default -> colors[i] = Color.WHITE;
            }
        }
        scrollPanel.add(new RecapRound(scrollPanel.getComponentCount()+1, colors, score, roundWon));
        if (roundWon) this.roundsWon++;
    }
}
