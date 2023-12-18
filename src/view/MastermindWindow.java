package view;

import controler.GameController;
import controler.RoundController;
import model.GameObserver;

import javax.swing.*;
import java.awt.*;

public class MastermindWindow extends JFrame {
    private StartPanel startPanel;
    private GamePanel gamePanel;
    private EndPanel endPanel;
    public MastermindWindow(EndPanel endPanel, GamePanel gamePanel, StartPanel startPanel){
        super("Mastermind");
        setSize( 500, 600 );
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.startPanel = startPanel;
        this.gamePanel = gamePanel;
        this.endPanel = endPanel;
        add(startPanel);
        setVisible(true);
    }
    public void showStartPanel(){
        startPanel.setVisible(true);
        gamePanel.setVisible(false);
        endPanel.setVisible(false);
    }
    public void showGamePanel(){
        startPanel.setVisible(false);
        gamePanel.setVisible(true);
        endPanel.setVisible(false);
    }
    public void showEndPanel(){
        startPanel.setVisible(false);
        gamePanel.setVisible(false);
        endPanel.setVisible(true);
    }


}
