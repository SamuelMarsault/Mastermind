package model;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private SecretCombination secretCombination;
    private List<Combination> attemps;
    private List<HintLine> hintLines;
    private List<RoundObserver> observers = new ArrayList<>();
    private Settings settings;

    /**
     * Round builder (set the Round)
     * @param settings
     */
    public Round(Settings settings){
        secretCombination = new SecretCombination(settings.getCombinationLength(), settings.getPawnNumber());
        attemps = new ArrayList<>();
        hintLines = new ArrayList<>();
        this.settings = settings;
    }

    /**
     * Checks whether the combination is correct
     * @param combination
     * @return perfectMatch
     */
    public boolean checkAttempt(Combination combination){
        attemps.add(combination);
        HintLine hintLine = new HintLine(combination,secretCombination);
        hintLines.add(hintLine);
        boolean perfectMatch = hintLine.perfectMatch();
        notifyAttempt();
        if (perfectMatch || attemps.size()>=settings.getAttemptNumber()) notifyRoundEnd(perfectMatch,computeScore());
        return perfectMatch;
    }
    
    /**
     * Set the round score
     * @return score
     */
    public int computeScore(){
        int score = 0;
        if(hintLines.size()-1>=0){
            HintLine hintLine = hintLines.get(hintLines.size()-1);
            for (int i = 0; i < hintLine.hintLineLength(); i++) {
                if (hintLine.getHint(i)==Hint.RIGHT_PLACE) score+=3;
                else if (hintLine.getHint(i)==Hint.WRONG_PLACE) score+=1;
            }
        }
        if (settings.getMode()==Mode.CLASSIC) score+=4;
        return score;
    }
    
    /**
     * Abandons the current round
     */
    public void giveUpRound(){
        notifyRoundEnd(false,computeScore());
    }

    /**
     * Add classes to observe
     * @param observer
     */
    public void addObserver(RoundObserver observer){
        observers.add(observer);
    }

    /**
     * Warns of the end of a verification attempt
     */
    private void notifyAttempt(){
        int index = attemps.size()-1;
        for (RoundObserver observer:observers) {
            observer.reactToAttempt(index+1,hintLines.get(index));
        }
    }

    /**
     * Tells you when the round is over
     * @param roundWon
     * @param score
     */
    private void notifyRoundEnd(boolean roundWon, int score){
        for (RoundObserver observer:observers) {
            observer.reactToRoundEnd(roundWon,score, secretCombination);
        }
    }
}
