package model;

public interface RoundObserver {
    void reactToAttempt(Combination combination, HintLine hintLine);
    void reactToRoundEnd(boolean roundWon, int score);
}
