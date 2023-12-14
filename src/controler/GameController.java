package controler;

import model.Game;
import model.Mode;
import model.Settings;
import view.MastermindWindow;

public class GameController {
    private Game game;
    private RoundController roundController;
    private MastermindWindow window;
    public GameController(RoundController roundController, Game game){
        this.roundController = roundController;
        this.game = game;
    }

    public void setWindow(MastermindWindow window) {
        this.window = window;
    }

    public void startGame(int attemptNumber, int combinationLength, int pawnNumber, Mode mode, String playerName, int roundNumber){
        game.configureGame(new Settings(attemptNumber,combinationLength,pawnNumber,mode,playerName), roundNumber);
        window.showGamePanel();
    }
}
