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
     * 
     * @param roundController
     * @param game
     */
    public GameController(RoundController roundController, Game game){
        this.roundController = roundController;
        this.game = game;
    }

    /**
     * 
     * @param window
     */
    public void setWindow(MastermindWindow window) {
        this.window = window;
    }

    /**
     * 
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
     * 
     */
    public void resetGame(){
        game.restartGame();
        nextRound();
        window.showGamePanel();
    }

    /**
     * 
     */
    public void nextRound(){
        roundController.setRound(game.nextRound());
    }

    /**
     * 
     */
    public void giveUpRound(){
        roundController.giveUpRound();
    }

    /**
     * 
     */
    public void endGame(){
        window.showEndPanel();
    }

    /**
     * 
     */
    public void returnToMenu(){
        window.showStartPanel();
    }
}
