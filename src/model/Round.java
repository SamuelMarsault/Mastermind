package model;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private int combinationLength;
    private int attemptNumber;
    private SecretCombination secretCombination;
    private List<Combination> combinations;
    private List<HintLine> hintLines;
    private List<RoundObserver> observers = new ArrayList<>();
    public Round(int combinationLength, int attemptNumber, int pawnNumber){
        this.attemptNumber = attemptNumber;
        this.combinationLength = combinationLength;
        secretCombination = new SecretCombination(combinationLength,pawnNumber);
        combinations = new ArrayList<>();
        hintLines = new ArrayList<>();
    }
    public boolean checkAttempt(Combination combination){
        combinations.add(combination);
        HintLine hintLine = new HintLine(combination,secretCombination);
        hintLines.add(hintLine);
        return hintLine.perfectMatch();
    }
    public int computeScore(Mode mode){
        int score = 0;
        HintLine hintLine = hintLines.get(hintLines.size()-1);
        for (int i = 0; i < hintLine.hintLineLength(); i++) {
            if (hintLine.getHint(i)==Hint.RIGHT_PLACE) score+=3;
            else if (hintLine.getHint(i)==Hint.WRONG_PLACE) score+=1;
        }
        if (mode==Mode.CLASSIC) score+=4;
        return score;
    }
}
