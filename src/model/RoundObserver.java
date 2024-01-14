package model;

public interface RoundObserver {

    /**
     * React to the end of an attempt
     * @param attemptId
     * @param hintLine
     */
    void reactToAttempt(int attemptId, HintLine hintLine);

    /**
     * React to the end of the round
     * @param roundWon
     * @param score
     * @param secretCombination
     */
    void reactToRoundEnd(boolean roundWon, int score, Combination secretCombination);
}
