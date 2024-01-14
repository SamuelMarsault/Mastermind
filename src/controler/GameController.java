package controler;

import model.Game;
import model.Mode;
import model.Settings;
import view.MastermindWindow;

public class GameController {
    private Game game;
    private RoundController roundController;
    private MastermindWindow window;
    
    /**
     * GameController builder
     * @param roundController
     * @param game
     */
    public GameController(RoundController roundController, Game game){
        this.roundController = roundController;
        this.game = game;
    }

    /**
     * Set MastermindWindow
     * @param window
     */
    public void setWindow(MastermindWindow window) {
        this.window = window;
    }

    /**
     * Start the game
     * @param attemptNumber
     * @param combinationLength
     * @param pawnNumber
     * @param mode
     * @param playerName
     * @param roundNumber
     */
    public void startGame(int attemptNumber, int combinationLength, int pawnNumber, Mode mode, String playerName, int roundNumber){
        game.configureGame(new Settings(attemptNumber,combinationLength,pawnNumber,mode,playerName), roundNumber);
        nextRound();
        window.showGamePanel();
    }

    /**
     * Reset the game
     */
    public void resetGame(){
        game.restartGame();
        nextRound();
        window.showGamePanel();
    }

    /**
     * Change round and give it to roundController
     */
    public void nextRound(){
        roundController.setRound(game.nextRound());
    }

    /**
     * Give up current round
     */
    public void giveUpRound(){
        roundController.giveUpRound();
    }

    /**
     * Show the end-of-game panel
     */
    public void endGame(){
        window.showEndPanel();
    }

    /**
     * Show the start panel
     */
    public void returnToMenu(){
        window.showStartPanel();
    }
}
