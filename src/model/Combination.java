package model;

public class Combination {
    private Pawn[] pawns;
    public Combination(Pawn[] pawns){
        this.pawns = pawns;
    }
    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }
    public Pawn getPawn(int index) {return pawns[index];}
    public int getCombinationLength() {return pawns.length;}
    public boolean containsPawn(Pawn pawn) {
        boolean contains = false;
        for (int i = 0; i < pawns.length && !contains; i++)
            contains = pawns[i].equals(pawn);
        return contains;
    }
}
