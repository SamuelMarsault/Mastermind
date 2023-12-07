package model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String playerName;
    private int score;
    private int attemptNumber;
    private int pawnNumber;
    private int combinationLength;
    private int roundNumber;
    private List<Round> rounds;
    private Mode mode;
    public Game(){
        rounds = new ArrayList<>();
        mode = Mode.CLASSIC;
    }
    public Round StartGame(int roundNumber, int combinationLength, int pawnNumber, int attemptNumber){
        this.attemptNumber = attemptNumber;
        this.roundNumber = roundNumber;
        this.combinationLength = combinationLength;
        this.pawnNumber = pawnNumber;
        Round round = new Round(this.combinationLength,this.attemptNumber,this.pawnNumber);
        rounds.add(round);
        return round;
    }
    public Round nextRound(){
        if (rounds.size()>roundNumber) return null;
        Round round = new Round(this.combinationLength,this.attemptNumber,this.pawnNumber);
        rounds.add(round);
        return round;
    }
    public int getFinalScore(){
        int score = 0;
        for (Round round:rounds) {
            score += round.computeScore(mode);
        }
        return score;
    }
}
