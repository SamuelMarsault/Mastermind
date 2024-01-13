package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SecretCombination extends Combination{
    
    /**
     * 
     * @param combinationLength
     * @param pawnNumber
     */
    public SecretCombination(int combinationLength, int pawnNumber){
        super(null);
        Pawn[] pawns = new Pawn[combinationLength];
        List<Pawn> possiblePawns = new ArrayList<>(Arrays.asList(Pawn.values()).subList(0, pawnNumber));
        Random rand = new Random();
        for (int i=0;i<combinationLength;i++){
            int index = rand.nextInt(0,possiblePawns.size());
            pawns[i] = possiblePawns.get(index);
        }
        setPawns(pawns);
    }
}
