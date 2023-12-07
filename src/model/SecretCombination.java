package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SecretCombination extends Combination{
    public SecretCombination(int combinationLength, int pawnNumber){
        super(null);
        List<Pawn> possiblePawns = new ArrayList<>();
        Pawn[] pawns = new Pawn[combinationLength];
        possiblePawns.addAll(Arrays.asList(Pawn.values()).subList(0, pawnNumber));
        Random rand = new Random();
        for (int i=0;i<combinationLength;i++){
            pawns[i] = possiblePawns.get(rand.nextInt(pawnNumber));
        }
        setPawns(pawns);
    }
}
