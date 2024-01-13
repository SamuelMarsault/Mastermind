package model;

public interface RoundObserver {

    /**
     * Notifies of the end of an attempt
     * @param attemptId
     * @param hintLine
     */
    void reactToAttempt(int attemptId, HintLine hintLine);

    /**
     * Notifies end of round
     * @param roundWon
     * @param score
     * @param secretCombination
     */
    void reactToRoundEnd(boolean roundWon, int score, Combination secretCombination);
}
