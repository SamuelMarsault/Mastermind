package view;

import model.*;

public class Announcer implements RoundObserver, GameObserver {

    @Override
    public void reactToAttempt(int attemptId, HintLine hintLine) {
        StringBuilder combinationString = new StringBuilder();
        StringBuilder hintsString = new StringBuilder();
        for (int i = 0; i < hintLine.hintLineLength(); i++) {
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
    public void reactToModeChanged(Mode mode) {

    }

    @Override
    public void reactToGameStart(int roundNumber, int attemptNumber, int pawnNumber, int combinationLenght) {

    }

    @Override
    public void reactToGameEnd(int score) {
        System.out.println("Fin de la partie");
        System.out.println("Voici le score final : "+score);
    }
}
