package model;

public interface GameObserver {
    
    /**
     * 
     * @param roundNumber
     * @param attemptNumber
     * @param pawnNumber
     * @param combinationLenght
     * @param mode
     */
    public void reactToGameStart(int roundNumber, int attemptNumber, int pawnNumber, int combinationLenght, Mode mode);

    /**
     * 
     * @param score
     * @param playerName
     */
    public void reactToGameEnd(int score, String playerName);
}
