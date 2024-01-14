package model;

public class Combination {
    private Pawn[] pawns;

    /**
     * Combination Builder
     * @param pawns
     */
    public Combination(Pawn[] pawns){
        this.pawns = pawns;
    }

    /**
     * Define the list of counters in the combination
     * @param pawns
     */
    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }
    
    /**
     * Retrieves the list of pawns in the combination
     * @param index
     * @return pawn[index]
     */
    public Pawn getPawn(int index) {return pawns[index];}

    /**
     * Retrieves the list size of the combination
     * @return pawn.lenght
     */
    public int getCombinationLength() {return pawns.length;}
    
    /**
     * Check whether the combination contains the specified pawn
     * @param pawn
     * @return contains
     */
    public boolean containsPawn(Pawn pawn) {
        boolean contains = false;
        for (int i = 0; i < pawns.length && !contains; i++)
            contains = pawns[i].equals(pawn);
        return contains;
    }
}
