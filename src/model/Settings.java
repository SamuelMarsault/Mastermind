package model;

public class Settings {
    private int attemptNumber;
    private int combinationLength;
    private int pawnNumber;
    private Mode mode;
    private String playerName;
    
    /**
     * 
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
     * 
     * @return attemptNumber
     */
    public int getAttemptNumber() {
        return attemptNumber;
    }

    /**
     * 
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * 
     * @param mode
     */
    public void setMode(Mode mode){
        this.mode = mode;
    }
    
    /**
     * 
     * @return combinationLength
     */
    public int getCombinationLength() {
        return combinationLength;
    }

    /**
     * 
     * @return pawnNumber
     */
    public int getPawnNumber() {
        return pawnNumber;
    }

    /**
     * 
     * @return mode
     */
    public Mode getMode() {
        return mode;
    }
}
