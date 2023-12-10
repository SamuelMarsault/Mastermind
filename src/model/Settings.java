package model;

public class Settings {
    private int attemptNumber;
    private int combinationLength;
    private int pawnNumber;
    private Mode mode;
    public Settings(int attemptNumber, int combinationLength, int pawnNumber, Mode mode){
        this.attemptNumber = attemptNumber;
        this.combinationLength = combinationLength;
        this.pawnNumber = pawnNumber;
        this.mode = mode;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }
    public void setMode(Mode mode){
        this.mode = mode;
    }

    public int getCombinationLength() {
        return combinationLength;
    }

    public int getPawnNumber() {
        return pawnNumber;
    }

    public Mode getMode() {
        return mode;
    }
}
