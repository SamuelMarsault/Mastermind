package model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String playerName;
    private int score;
    private int roundNumber;
    private List<Round> rounds;
    private Mode mode;
    private Settings settings;
    public Game(){
        rounds = new ArrayList<>();
    }
    public void configureGame(Settings settings, int roundNumber){
        this.settings = settings;
        this.roundNumber = roundNumber;
    }
    public Round nextRound(){
        if (rounds.size()>roundNumber) return null;
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
}
