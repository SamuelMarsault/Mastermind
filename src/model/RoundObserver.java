package model;

public interface RoundObserver {
    void reactToAttempt(int attemptId, HintLine hintLine);
    void reactToRoundEnd(boolean roundWon, int score);
}
