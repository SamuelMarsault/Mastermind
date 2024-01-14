package model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int roundNumber;
    private List<Round> rounds;
    private Settings settings;
    private List<GameObserver> observers = new ArrayList<>();

    /**
     * Game builder
     */
    public Game(){
    }

    /**
     * Allows you to configure the game at launch 
     * @param settings
     * @param roundNumber
     */
    public void configureGame(Settings settings, int roundNumber){
        this.settings = settings;
        this.roundNumber = roundNumber;
        rounds = new ArrayList<>();
        notifyGameStart();
    }

    /**
     * Move on to the next round
     * @return round or null
     */
    public Round nextRound(){
        if(rounds.size()<roundNumber){
            Round round = new Round(settings);
            rounds.add(round);
            return round;
        }
        else if (rounds.size()==roundNumber) {
            notifyGameEnd();
        }
        return null;
    }

    /**
     * Recovers the score of all combined rounds
     * @return score
     */
    public int getFinalScore(){
        int score = 0;
        for (Round round:rounds) {
            score += round.computeScore();
        }
        return score;
    }
    
    /**
     * Restarts a new game
     */
    public void restartGame(){
        configureGame(settings, roundNumber);
    }
    
    /**
     * Adds an observer
     * @param observer
     */
    public void addObserver(GameObserver observer){
        observers.add(observer);
    }

    /**
     * Calls observer method when the game starts
     */
    private void notifyGameStart(){
        for (GameObserver observer:observers){
            observer.reactToGameStart(roundNumber, settings.getAttemptNumber(), settings.getPawnNumber(), settings.getCombinationLength(), settings.getMode());
        }
    }

    /**
     * Calls observer method when the game stop
     */
    private void notifyGameEnd(){
        int score = getFinalScore();
        for (GameObserver observer:observers) {
            observer.reactToGameEnd(score, settings.getPlayerName());
        }
    }
}