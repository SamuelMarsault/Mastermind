package view;

import javax.swing.*;
import java.awt.*;

public class MastermindWindow extends JFrame {
    private StartPanel startPanel;
    private GamePanel gamePanel;
    private EndPanel endPanel;

    public MastermindWindow(EndPanel endPanel, GamePanel gamePanel, StartPanel startPanel) {
        super("Mastermind");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(700, (int) screenSize.getHeight() - 40);
        setLocation((int) (screenSize.getWidth() / 4), 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.startPanel = startPanel;
        this.gamePanel = gamePanel;
        this.endPanel = endPanel;
        startPanel.setSizeWindow(getHeight());
        showStartPanel();
        setVisible(true);
    }

    public void showStartPanel(){
        add(startPanel);
        startPanel.setVisible(true);
        gamePanel.setVisible(false);
        endPanel.setVisible(false);
    }

    public void showGamePanel() {
        add(gamePanel);
        startPanel.setVisible(false);
        gamePanel.setVisible(true);
        endPanel.setVisible(false);
    }

    public void showEndPanel() {
        add(endPanel);
        startPanel.setVisible(false);
        gamePanel.setVisible(false);
        endPanel.setVisible(true);
    }

    public StartPanel getStartPanel(){
        return startPanel;
    }

     public GamePanel getGamePanel(){
        return gamePanel;
    }

     public EndPanel getEndPanel(){
        return endPanel;
    }
}
