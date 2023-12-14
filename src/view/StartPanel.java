package view;

import controler.GameController;
import model.Mode;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    public StartPanel(GameController gameController){
        add(new Label("StartPanel"),BorderLayout.SOUTH);
        Button button = new Button("Next");
        button.addActionListener(actionEvent -> gameController.startGame(1,1,1, Mode.CLASSIC,"test",1));
        add(button,BorderLayout.SOUTH);
    }
}
