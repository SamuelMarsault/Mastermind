package model;

public class Combination {
    private Pawn[] pawns;

    /**
     * 
     * @param pawns
     */
    public Combination(Pawn[] pawns){
        this.pawns = pawns;
    }

    /**
     * 
     * @param pawns
     */
    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }
    
    /**
     * 
     * @param index
     * @return pawn[index]
     */
    public Pawn getPawn(int index) {return pawns[index];}

    /**
     * 
     * @return pawn.lenght
     */
    public int getCombinationLength() {return pawns.length;}
    
    /**
     * 
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
