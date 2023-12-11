package controler;

import model.*;
import view.Announcer;

import java.util.Scanner;

public class MastermindApp {
    public static void main(String[] args) {
        Game game = new Game();
        Announcer announcer = new Announcer();
        game.addObserver(announcer);
        int combinationLength = 4;
        int roundNumber = 3;
        int attemptNumber = 4;
        int pawnNumber = 4;
        Scanner scanner = new Scanner(System.in);
        Pawn[] pawns = new Pawn[combinationLength];
        game.configureGame(new Settings(attemptNumber,combinationLength,pawnNumber,Mode.CLASSIC,"Test"), 3);
        for (int i = 0; i <roundNumber ; i++) {
            int roundCount = 0;
            Round round = game.nextRound();
            round.addObserver(announcer);
            do{
                int c=0;
                System.out.println("Entrez votre combinaison :");
                for (String str:scanner.nextLine().split(" ")) {
                    int index = Integer.parseInt(str);
                    pawns[c] = Pawn.values()[index];
                    c++;
                }
                roundCount++;
        }while(!round.checkAttempt(new Combination(pawns)) && roundCount<attemptNumber);
        }
        game.nextRound();
    }
}
