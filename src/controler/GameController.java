package controler;

import model.Game;
import model.Mode;
import model.Settings;
import view.MastermindWindow;

public class GameController {
    private Game game;
    private RoundController roundController;
    private MastermindWindow window;
    private Settings oldSettings;

    public GameController(RoundController roundController, Game game){
        this.roundController = roundController;
        this.game = game;
    }

    public void setWindow(MastermindWindow window) {
        this.window = window;
    }

    public void startGame(int attemptNumber, int combinationLength, int pawnNumber, Mode mode, String playerName, int roundNumber){
        this.oldSettings = new Settings(attemptNumber,combinationLength,pawnNumber,mode,playerName);
        game.configureGame(oldSettings, roundNumber);
        nextRound();
        window.showGamePanel();
    }

    public void resetGame(){
        game.restartGame();
        nextRound();
        window.showGamePanel();
    }

    public void nextRound(){
        roundController.setRound(game.nextRound());
    }

    public void giveUpRound(){
        roundController.giveUpRound();
    }

    public void endGame(){
        window.showEndPanel();
    }
}
