package model;

import java.util.HashMap;
import java.util.Map;

public class HintLine {
    private Hint[] hints;
    
    /**
     * 
     * @param combination
     * @param secretCombination
     */
    public HintLine(Combination combination, SecretCombination secretCombination){
        hints = new Hint[secretCombination.getCombinationLength()];
        Map<Pawn, Integer> secretPawn = new HashMap<>();
        Map<Pawn, Integer> combinationPawn = new HashMap<>();
        for (int i = 0; i < hints.length; i++){
            if (secretPawn.containsKey(secretCombination.getPawn(i))){
                int valeurActuelle = secretPawn.get(secretCombination.getPawn(i));
                secretPawn.put(secretCombination.getPawn(i),valeurActuelle+1);
            }
            else{
                secretPawn.put(secretCombination.getPawn(i),1);
            }
            
            combinationPawn.put(combination.getPawn(i),0);
        }

        for (int i = 0; i < hints.length; i++) {
            if (combination.getPawn(i).equals(secretCombination.getPawn(i))) {
                int valeurActuelle = combinationPawn.get(combination.getPawn(i));
                combinationPawn.put(combination.getPawn(i), valeurActuelle + 1);
            }
        }

        // Afficher les couples clé-valeur pour secretPawn
        System.out.println("secretPawn after loop:");
        for (Map.Entry<Pawn, Integer> entry : secretPawn.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        for (int i = 0; i < hints.length; i++) {
            if (combination.getPawn(i).equals(secretCombination.getPawn(i))) {
                hints[i] = Hint.RIGHT_PLACE;
            } 
            else if (secretCombination.containsPawn(combination.getPawn(i))) {
                Pawn currentPawn = combination.getPawn(i);

                // Obtenez les occurrences pour le pion courant dans les deux maps
                int combinationOccurrences = combinationPawn.get(currentPawn);
                int secretOccurrences = secretPawn.get(currentPawn);
                if (combinationOccurrences >= secretOccurrences) {
                    hints[i] = Hint.ABSENT;
                } else {
                    hints[i] = Hint.WRONG_PLACE;
                }

                // Mise à jour de combinationPawn
                combinationPawn.put(currentPawn, combinationOccurrences + 1);
            }
            else {
                hints[i] = Hint.ABSENT;
            } 
        }

    }

    /**
     * 
     * @param index
     * @return hints[index]
     */
    public Hint getHint(int index) {
        return hints[index];
    }
    
    /**
     * 
     * @return match
     */
    public boolean perfectMatch(){
        boolean match = true;
        for (int i = 0; i < hints.length && match; i++) {
            if (hints[i]!=Hint.RIGHT_PLACE) match = false;
        }
        return match;
    }

    /**
     * 
     * @return hints.length
     */
    public int hintLineLength(){
        return hints.length;
    }
}