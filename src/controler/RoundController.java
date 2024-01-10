package controler;

import model.Combination;
import model.Pawn;
import model.Round;
import model.RoundObserver;
import view.GamePanel;
import view.MastermindWindow;

import java.util.ArrayList;
import java.util.List;

public class RoundController {
    private Round round;
    private ArrayList<RoundObserver> roundObservers = new ArrayList<>();
    private MastermindWindow window;

    public void setWindow(MastermindWindow window){
        this.window = window;
    }

    public void addRoundObserver(RoundObserver observer) { roundObservers.add(observer);}

    public void setRound(Round round){
        this.round = round;
        System.out.println("changement de round\n---------------\n------------");
        if (round != null)
            for (RoundObserver observer : roundObservers)
                round.addObserver(observer);
    }

    public void launchAttempt(Pawn[] pawns){
        System.out.println(round);
        if(round!=null)
            round.checkAttempt(new Combination(pawns));
    }
    
    public void giveUpRound(){
        round.giveUpRound();
    }
}
