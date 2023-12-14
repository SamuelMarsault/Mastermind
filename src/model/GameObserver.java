package model;

public interface GameObserver {
    public void reactToGameStart(int roundNumber, int attemptNumber,int pawnNumber);
    public void reactToGameEnd(int score);
}
