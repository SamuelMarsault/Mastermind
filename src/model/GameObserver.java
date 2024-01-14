package model;

public interface GameObserver {

    /**
     * Is called when the game starts 
     * @param roundNumber
     * @param attemptNumber
     * @param pawnNumber
     * @param combinationLenght
     * @param mode
     */
    public void reactToGameStart(int roundNumber, int attemptNumber, int pawnNumber, int combinationLenght, Mode mode);

    /**
     * Is called when the game end
     * @param score
     * @param playerName
     */
    public void reactToGameEnd(int score, String playerName);
}
