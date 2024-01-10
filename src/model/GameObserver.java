package model;

import java.util.List;

public interface GameObserver {
    public void reactToGameStart(int roundNumber, int attemptNumber, int pawnNumber, int combinationLenght, Mode mode);
    public void reactToGameEnd(int score, List<Round> rounds, String playerName);
}
