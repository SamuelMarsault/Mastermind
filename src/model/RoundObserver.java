package model;

public interface RoundObserver {

    /**
     * 
     * @param attemptId
     * @param hintLine
     */
    void reactToAttempt(int attemptId, HintLine hintLine);

    /**
     * 
     * @param roundWon
     * @param score
     * @param secretCombination
     */
    void reactToRoundEnd(boolean roundWon, int score, Combination secretCombination);
}
