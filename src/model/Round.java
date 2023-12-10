package model;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private SecretCombination secretCombination;
    private List<Combination> combinations;
    private List<HintLine> hintLines;
    private List<RoundObserver> observers = new ArrayList<>();
    private Settings settings;
    public Round(Settings settings){
        secretCombination = new SecretCombination(settings.getCombinationLength(), settings.getPawnNumber());
        combinations = new ArrayList<>();
        hintLines = new ArrayList<>();
        this.settings = settings;
    }
    public boolean checkAttempt(Combination combination){
        combinations.add(combination);
        HintLine hintLine = new HintLine(combination,secretCombination);
        hintLines.add(hintLine);
        boolean perfectMatch = hintLine.perfectMatch();
        notifyAttempt();
        if (perfectMatch) notifyRoundEnd(perfectMatch,computeScore());
        return perfectMatch;
    }
    public int computeScore(){
        int score = 0;
        System.out.println();
        HintLine hintLine = hintLines.get(hintLines.size()-1);
        for (int i = 0; i < hintLine.hintLineLength(); i++) {
            if (hintLine.getHint(i)==Hint.RIGHT_PLACE) score+=3;
            else if (hintLine.getHint(i)==Hint.WRONG_PLACE) score+=1;
        }
        if (settings.getMode()==Mode.CLASSIC) score+=4;
        return score;
    }
    public void addObserver(RoundObserver observer){
        observers.add(observer);
    }
    public void setMode(Mode mode){
        settings.setMode(mode);
    }
    private void notifyAttempt(){
        int index = combinations.size()-1;
        for (RoundObserver observer:observers) {
            observer.reactToAttempt(combinations.get(index),hintLines.get(index));
        }
    }
    private void notifyRoundEnd(boolean roundWon, int score){
        for (RoundObserver observer:observers) {
            observer.reactToRoundEnd(roundWon,score);
        }
    }
    private void notifyModeChanged(){
        for (RoundObserver observer:observers) {
            observer.reactToModeChanged(settings.getMode());
        }
    }
}
