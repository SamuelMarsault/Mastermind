package controler;

import model.Combination;
import model.Pawn;
import model.Round;
import model.RoundObserver;

import java.util.ArrayList;

public class RoundController {
    private Round round;
    private ArrayList<RoundObserver> roundObservers = new ArrayList<>();
    
    /**
     * Adds an observer
     * @param observer
     */
    public void addRoundObserver(RoundObserver observer) { roundObservers.add(observer);}

    /**
     * Set current round
     * @param round
     */
    public void setRound(Round round){
        this.round = round;
        System.out.println("changement de round\n---------------\n------------");
        if (round != null)
            for (RoundObserver observer : roundObservers)
                round.addObserver(observer);
    }

    /**
     * Give the player combination to the round class
     * @param pawns
     */
    public void launchAttempt(Pawn[] pawns){
        System.out.println(round);
        if(round!=null)
            round.checkAttempt(new Combination(pawns));
    }
    
    /**
     * Warns the round class to give up
     */
    public void giveUpRound(){
        round.giveUpRound();
    }
}
