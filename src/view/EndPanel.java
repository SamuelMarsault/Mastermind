package view;

import controler.GameController;
import model.GameObserver;
import model.Mode;
import model.Round;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel implements GameObserver {
    private GameController gameController;
    private JLabel scoreLabel;
    private JLabel ggLabel;

    public EndPanel(GameController gameController){
        this.gameController = gameController;
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

        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.scoreLabel = new JLabel("SCORE : xxx");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scorePanel.add(scoreLabel);
        scorePanel.setBorder(BorderFactory.createEmptyBorder(300, 0, 0, 0));
        add(scorePanel, BorderLayout.CENTER);

        //Mettre potentiellement les stats ici

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton menuButton = new JButton(resizeImage(new ImageIcon("image_jeu/house.png"),40,40));
        menuButton.addActionListener(actionEvent -> {
            
        });

        buttonPanel.add(menuButton);
        JButton restartButton = new JButton(resizeImage(new ImageIcon("image_jeu/restart.png"),40,40));
        restartButton.addActionListener(actionEvent -> {
            gameController.resetGame();
        });
        buttonPanel.add(Box.createHorizontalStrut(530)); //Trouver meilleure méthode plus tard et augmenter taille boutton
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

    }

    @Override
    public void reactToGameEnd(int score, List<Round> rounds, String playeurName) {
         scoreLabel.setText("SCORE : "+score);
         ggLabel.setText("GG "+playeurName);
    }
}
