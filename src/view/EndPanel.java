package view;

import controler.GameController;
import model.*;

import java.util.List;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel implements GameObserver, RoundObserver {
    private JLabel scoreLabel;
    private JLabel ggLabel;
    private JPanel scrollPanel;

    public EndPanel(GameController gameController){
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(0, 1));
        JLabel endLabel = new JLabel("EndPanel - VICTORY or DEFEAT");
        endLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        // Centrer verticalement chaque composant dans le GridLayout
        JPanel endLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        endLabelPanel.add(endLabel);

        // Ajout d'un espace vertical entre les deux labels
        endLabelPanel.add(Box.createVerticalStrut(10));

        this.ggLabel = new JLabel("GG Joueur");
        ggLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Centrer verticalement chaque composant dans le GridLayout
        JPanel ggLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ggLabelPanel.add(ggLabel);

        // Ajout panels au GridLayout
        northPanel.add(endLabelPanel);
        northPanel.add(ggLabelPanel);

        add(northPanel, BorderLayout.NORTH);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scrollPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 2);
        gridLayout.setHgap(8);
        scrollPanel.setLayout(gridLayout);
        
        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        scorePanel.add(new JScrollPane(scrollPanel));
        scorePanel.add(scoreLabel);
        add(scorePanel, BorderLayout.CENTER);
        

        //Mettre potentiellement les stats ici

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton menuButton = new JButton(resizeImage(new ImageIcon("image_jeu/house.png"),40,40));
        menuButton.addActionListener(actionEvent -> {
            gameController.returnToMenu();
        });
        buttonPanel.add(menuButton);

        JButton exitButton = new JButton(resizeImage(new ImageIcon("image_jeu/exit.png"),40,40));
        exitButton.addActionListener(actionEvent -> {
            System.exit(0);
        });
        buttonPanel.add(Box.createHorizontalStrut(223)); //Trouver meilleure méthode plus tard et augmenter taille boutton
        buttonPanel.add(exitButton);
        
        JButton restartButton = new JButton(resizeImage(new ImageIcon("image_jeu/restart.png"),40,40));
        restartButton.addActionListener(actionEvent -> {
            gameController.resetGame();
        });
        buttonPanel.add(Box.createHorizontalStrut(223)); //Trouver meilleure méthode plus tard et augmenter taille boutton
        buttonPanel.add(restartButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private ImageIcon resizeImage(ImageIcon image,int height, int width){
        Image originalImage = image.getImage();

        int newWidth = width;
        int newHeight = height;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
    }

    @Override
    public void reactToGameStart(int roundNumber, int attemptNumber, int pawnNumber, int combinationLenght, Mode mode) {
        if (roundNumber == 5){
                scrollPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
        }
        else{
                scrollPanel.setBorder(BorderFactory.createEmptyBorder(160, 0, 160, 0));
        }
        scrollPanel.removeAll();
    }

    @Override
    public void reactToGameEnd(int score, List<Round> rounds, String playeurName) {
         scoreLabel.setText("SCORE : "+score);
         ggLabel.setText("GG "+playeurName);
    }

    @Override
    public void reactToAttempt(int attemptId, HintLine hintLine) {}

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
    }
}
