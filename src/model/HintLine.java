package model;

public class HintLine {
    private Hint[] hints;
    public HintLine(Combination combination, SecretCombination secretCombination){
        hints = new Hint[secretCombination.getCombinationLength()];
        for (int i = 0; i < hints.length; i++) {
            if(combination.getPawn(i).equals(secretCombination.getPawn(i))) hints[i] = Hint.RIGHT_PLACE;
            else if (combination.containsPawn(secretCombination.getPawn(i))) hints[i] = Hint.WRONG_PLACE;
            else hints[i] = Hint.ABSENT;
        }
    }

    public Hint getHint(int index) {
        return hints[index];
    }
}
