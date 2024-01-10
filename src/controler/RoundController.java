package controler;

import model.Combination;
import model.Pawn;
import model.Round;
import view.GamePanel;
import view.MastermindWindow;

public class RoundController {
    private Round round;
    private GamePanel gamePanel;
    private MastermindWindow window;

    public void setWindow(MastermindWindow window){
        this.window = window;
    }

    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setRound(Round round){
        this.round = round;
        System.out.println("changement de round\n---------------\n------------");
        if (round != null)
            round.addObserver(gamePanel);
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
