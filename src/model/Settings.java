package model;

public class Settings {
    private int attemptNumber;
    private int combinationLength;
    private int pawnNumber;
    private Mode mode;
    private String playerName;
    
    /**
     * Setting builder (retrieves the options defined in startPanel and saves them)
     * @param attemptNumber
     * @param combinationLength
     * @param pawnNumber
     * @param mode
     * @param playerName
     */
    public Settings(int attemptNumber, int combinationLength, int pawnNumber, Mode mode, String playerName){
        this.attemptNumber = attemptNumber;
        this.combinationLength = combinationLength;
        this.pawnNumber = pawnNumber;
        this.mode = mode;
        this.playerName = playerName;
    }

    /**
     * Retrieves the number of attempts for each round of the game
     * @return attemptNumber
     */
    public int getAttemptNumber() {
        return attemptNumber;
    }

    /**
     * Retrieves the player's name
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Set game mode (Hint)
     * @param mode
     */
    public void setMode(Mode mode){
        this.mode = mode;
    }
    
    /**
     * Retrieves the size of the combination
     * @return combinationLength
     */
    public int getCombinationLength() {
        return combinationLength;
    }

    /**
     * Recovers the number of pawns available at the end of an attempt
     * @return pawnNumber
     */
    public int getPawnNumber() {
        return pawnNumber;
    }

    /**
     * Recovers the game's mode (Hint)
     * @return mode
     */
    public Mode getMode() {
        return mode;
    }
}
