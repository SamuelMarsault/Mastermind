package view;

import model.Combination;
import model.HintLine;
import model.RoundObserver;

public class RoundAnnouncer implements RoundObserver {

    @Override
    public void reactToAttempt(Combination combination, HintLine hintLine) {
        StringBuilder combinationString = new StringBuilder();
        StringBuilder hintsString = new StringBuilder();
        for (int i = 0; i < combination.getCombinationLength(); i++) {
            combinationString.append(combination.getPawn(i)).append(" ");
            hintsString.append(hintLine.getHint(i)).append(" ");
        }
        System.out.println("Combinaison jouée : "+combinationString);
        System.out.println("Indices associés : "+hintsString);
    }

    @Override
    public void reactToRoundEnd(boolean roundWon, int score) {
        System.out.println("Fin du round !!");
        String result;
        result = roundWon ? "Bravo vous avez gagné ce round !" : "Vous avez perdu... ";
        System.out.println(result);
        System.out.println("Score : "+score+"\n");
    }
}
