package model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int score;
    private int roundNumber;
    private List<Round> rounds = new ArrayList<>();
    private Settings settings;
    private List<GameObserver> observers = new ArrayList<>();
    public Game(){
        rounds = new ArrayList<>();
        observers = new ArrayList<>();
    }
    public void configureGame(Settings settings, int roundNumber){
        this.settings = settings;
        this.roundNumber = roundNumber;
        rounds = new ArrayList<>();
        observers = new ArrayList<>();
    }
    public Round nextRound(){
        if (rounds.size()>=roundNumber) {
            notifyGameEnd();
            return null;
        }
        Round round = new Round(settings);
        rounds.add(round);
        return round;
    }
    public int getFinalScore(){
        int score = 0;
        for (Round round:rounds) {
            score += round.computeScore();
        }
        return score;
    }
    public void addObserver(GameObserver observer){
        observers.add(observer);
    }

    private void notifyGameStart(){
        for (GameObserver observer:observers){
            observer.reactToGameStart(roundNumber, settings.getAttemptNumber(),settings.getPawnNumber());
        }
    }

    private void notifyGameEnd(){
        int score = getFinalScore();
        for (GameObserver observer:observers) {
            observer.reactToGameEnd(score);
        }
    }
}