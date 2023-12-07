package model;

public class Combination {
    private Pawn[] pawns;
    public Combination(Pawn[] pawns){
        this.pawns = pawns;
    }
    public int getCombinationLength() {return pawns.length;}
    public Pawn getPawn(int index) {return pawns[index];}
    public boolean containsPawn(Pawn pawn) {
        boolean contains = false;
        for (int i = 0; i < pawns.length && !contains; i++)
            contains = pawns[i].equals(pawn);
        return contains;
    }

    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }
}
