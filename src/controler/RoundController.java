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
     * 
     * @param observer
     */
    public void addRoundObserver(RoundObserver observer) { roundObservers.add(observer);}

    /**
     * 
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
     * 
     * @param pawns
     */
    public void launchAttempt(Pawn[] pawns){
        System.out.println(round);
        if(round!=null)
            round.checkAttempt(new Combination(pawns));
    }
    
    /**
     * 
     */
    public void giveUpRound(){
        round.giveUpRound();
    }
}
