package controler;

import model.Combination;
import model.Game;
import model.Pawn;
import model.Round;

import java.io.Console;
import java.io.InputStream;
import java.util.Scanner;

public class MastermindApp {
    public static void main(String[] args) {
        Game game = new Game();
        int combinationLength = 4;
        int roundNumber = 3;
        int attemptNumber = 4;
        Round round = game.StartGame(roundNumber,combinationLength,combinationLength,attemptNumber);
        Scanner scanner = new Scanner(System.in);
        Pawn[] pawns = new Pawn[combinationLength];
        boolean victory;
        for (int i = 0; i <roundNumber ; i++) {
            int roundCount = 0;
            do{
                int c=0;
                System.out.println("Entrez votre combinaison :");
                for (String str:scanner.nextLine().split(" ")) {
                    int index = Integer.parseInt(str);
                    pawns[c] = Pawn.values()[index];
                    c++;
                }
                roundCount++;
        }while(!(victory = round.checkAttempt(new Combination(pawns))) && roundCount<attemptNumber);
            System.out.println("Victoire ? "+victory+roundCount);
        }
    }
}
