import controler.GameController;
import controler.RoundController;
import model.*;
import view.*;

import javax.swing.*;

public class MastermindApp {
    public static void main(String[] args) {    
        try {            
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(new JFrame());

            Game game = new Game();

            RoundController roundController = new RoundController();
            GameController gameController = new GameController(roundController, game);

            StartPanel startPanel = new StartPanel(gameController);
            GamePanel gamePanel = new GamePanel(roundController, gameController);
            EndPanel endPanel = new EndPanel(gameController);

            MastermindWindow window = new MastermindWindow(endPanel, gamePanel, startPanel);

            gameController.setWindow(window);
            roundController.addRoundObserver(gamePanel);
            roundController.addRoundObserver(endPanel);

            game.addObserver(gamePanel);
            game.addObserver(endPanel);

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
    }
}
