import controler.GameController;
import controler.RoundController;
import model.*;
import view.*;

import java.util.Scanner;

public class MastermindApp {
    public static void main(String[] args) {
        Game game = new Game();

        RoundController roundController = new RoundController();
        GameController gameController = new GameController(roundController, game);

        StartPanel startPanel = new StartPanel(gameController);
        GamePanel gamePanel = new GamePanel(roundController,gameController);
        EndPanel endPanel = new EndPanel(gameController);
        MastermindWindow window = new MastermindWindow(endPanel,gamePanel,startPanel);

        gameController.setWindow(window);
        roundController.setWindow(window);
        roundController.setGamePanel(gamePanel);

        game.addObserver(gamePanel);
        game.addObserver(endPanel);
    }
}
