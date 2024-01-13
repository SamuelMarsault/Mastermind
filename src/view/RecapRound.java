package view;

import javax.swing.*;
import java.awt.*;

public class RecapRound extends JPanel {
    
    public RecapRound(int roundId, Color[] colors, int score, boolean victory){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        JLabel roundLabel = new JLabel("Round "+roundId);
        roundLabel.setHorizontalAlignment(JLabel.CENTER);
        roundLabel.setFont(new Font("Constantia",Font.PLAIN,20));
        add(roundLabel, constraints);
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new CombinationBox(colors), constraints);
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 2;
        JLabel scoreLabel = new JLabel("score : "+score);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFont(new Font("Constantia",Font.PLAIN,14));
        add(scoreLabel, constraints);
        constraints.gridy = 3;
        JLabel victoryLabel = new JLabel();
        if(victory) victoryLabel.setText("Victoire");
        else victoryLabel.setText("Défaite");
        victoryLabel.setFont(new Font("Constantia",Font.PLAIN,18));
        victoryLabel.setHorizontalAlignment(JLabel.CENTER);
        add(victoryLabel, constraints);
        setVisible(true);
    }
}
