package view;

import controler.GameController;
import controler.RoundController;
import model.*;

import javax.swing.*;
import javax.swing.border.AbstractBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel implements RoundObserver,GameObserver {
    Color selectedColor = Color.WHITE;
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
        JButton resetButton = new JButton(resizeImage(new ImageIcon("image_jeu/restart.png"),25,25));
        //Ajouter action quand le reste sera présent
        gamePanel.add(resetButton, gameConstraints);

        gameConstraints.gridx = 1;
        JLabel scoreLabel = new JLabel("0");
        scoreLabel.setFont(labelFont);
        gamePanel.add(scoreLabel, gameConstraints);

        gameConstraints.gridx = 2;
        JLabel currentRoundLabel = new JLabel("1/ (a faire)");
        //a faire modifier quand finis design
        gamePanel.add(currentRoundLabel, gameConstraints);

        gameConstraints.gridx = 0;
        gameConstraints.gridy = 1;
        gameConstraints.gridwidth = 3;
        JPanel combinaisonsPanel = new JPanel();
        combinaisonsPanel.setLayout(new GridLayout(0,2));
        //Dans la boucle du nombre de tentatives : + rendre valeurs différentes longueur cobinaisons
        //Bordure provisoires à modifier + tard et régler problèmes centrage
        JPanel oneCombinaison = new JPanel();
        oneCombinaison.setLayout(new FlowLayout());
        for (int i = 0; i < 4; i++) {
            JPanel possibilitie = createRoundPanel(25, Color.LIGHT_GRAY, 0);
            oneCombinaison.add(possibilitie);
        }
        oneCombinaison.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        combinaisonsPanel.add(oneCombinaison);

        JPanel oneHint = new JPanel();
        oneHint.setLayout(new GridLayout(0,2));
        for (int i = 0; i < 4; i++) {
            JPanel hintPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel hint = createRoundPanel(15, Color.LIGHT_GRAY, 1);
            hintPanel.add(hint);
            oneHint.add(hintPanel);
        }
        oneHint.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        combinaisonsPanel.add(oneHint);
        //!! A definir comment on veux que les pions soit, c'est à dire plein de pannel dans le pannel en FlowLayout... et pareil pour les indices et définir combien on en vois
        //!! On peux aussi faire 2 composant coller un pour la combinaison et l'autre pour les indices au lieu d'un seul qui en à 2. du coup gridwitch serais à 2 et pas 3
        gamePanel.add(combinaisonsPanel, gameConstraints);

        gameConstraints.gridx = 0;
        gameConstraints.gridy = 2;
        gameConstraints.gridwidth = 1;
        JButton giveUpButton = new JButton(resizeImage(new ImageIcon("image_jeu/give_up.png"),25,25));
        //Ajouter action quand le reste sera présent
        gamePanel.add(giveUpButton, gameConstraints);

        gameConstraints.gridx = 1;
        //!! On met un FlowLayout ici pour contenir les choix de couleur possible ?
        JPanel ColorPossibilities = new JPanel();
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

        gameConstraints.gridx = 2;
        JButton validateButton = new JButton(resizeImage(new ImageIcon("image_jeu/check.png"),25,25));
        //Ajouter action quand le reste sera présent
        gamePanel.add(validateButton, gameConstraints);

        //!! La disposition n'est pas top on est d'accord, tu voie si j'ai oublier un composant à part la combinaison et aide ?

        //Je modifierais la position et complèterais quand on auras vu pour les question au dessus

        add(gamePanel, BorderLayout.CENTER);

        Button buttonNext = new Button("Next");
        buttonNext.setFont( new Font("Arial", Font.PLAIN, 20));
        buttonNext.addActionListener(actionEvent -> roundController.launchAttempt(new Pawn[]{}));
        add(buttonNext,BorderLayout.SOUTH);
        setVisible(true);
    }

   
    private JPanel createRoundPanel(int diameter, Color color, int option) {
        final Color[] ovalColor = {color}; // Utilisation d'un tableau pour stocker une variable "effectivement finale"
    
        JPanel roundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;
                g.setColor(ovalColor[0]);
                g.fillOval(x, y, diameter, diameter);
            }
        };
        roundPanel.setPreferredSize(new Dimension(diameter, diameter));
    
        if (option == 0 || option == 2) {
            roundPanel.setBorder(new AbstractBorder() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawOval(x, y, width-1, height-1);
                    g2d.dispose();
                }
    
                @Override
                public Insets getBorderInsets(Component c) {
                    return new Insets(2, 2, 2, 2);
                }
    
                @Override
                public Insets getBorderInsets(Component c, Insets insets) {
                    insets.left = insets.top = insets.right = insets.bottom = 2;
                    return insets;
                }
            });
    
            roundPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (option == 0) {
                        ovalColor[0] = selectedColor;
                        roundPanel.repaint();
                    } else {
                        selectedColor = ovalColor[0];
                    }
                }
            });
        }
    
        return roundPanel;
    }

    private ImageIcon resizeImage(ImageIcon image,int height, int width){
        Image originalImage = image.getImage();

        int newWidth = width;
        int newHeight = height;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
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
