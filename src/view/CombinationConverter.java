package view;

import model.Pawn;

import java.awt.*;

public class CombinationConverter {
    /**
     * Convert colors into pawns
     * @param colors
     * @return a vector containing pawns
     */
    public Pawn[] colorsToPawns(Color[] colors){
        Pawn[] pawns = new Pawn[colors.length];
        for (int i = 0; i < pawns.length; i++) {
            if (colors[i].equals(Color.RED))
                pawns[i] = Pawn.RED;
            else if (colors[i].equals(Color.GREEN))
                pawns[i] = Pawn.GREEN;
            else if (colors[i].equals(Color.BLUE))
                pawns[i] = Pawn.BLUE;
            else if (colors[i].equals(Color.YELLOW))
                pawns[i] = Pawn.YELLOW;
            else if (colors[i].equals(Color.BLACK))
                pawns[i] = Pawn.BLACK;
            else if (colors[i].equals(Color.ORANGE))
                pawns[i] = Pawn.ORANGE;
            else if (colors[i].equals(Color.MAGENTA))
                pawns[i] = Pawn.PURPLE;
            else if (colors[i].equals(Color.PINK))
                pawns[i] = Pawn.PINK;
            else if (colors[i].equals(Color.BLACK))
                pawns[i] = Pawn.BLACK;
        }
        return pawns;
    }

    /**
     * Convert pawn into color
     * @param pawn
     * @return color
     */
    public Color pawnToColor(Pawn pawn){
        Color color;
        switch (pawn){
            case RED -> color = Color.RED;
            case GREEN -> color = Color.GREEN;
            case BLUE -> color = Color.BLUE;
            case YELLOW -> color = Color.YELLOW;
            case BLACK -> color = Color.BLACK;
            case ORANGE -> color = Color.ORANGE;
            case PURPLE -> color = Color.MAGENTA;
            case PINK -> color = Color.PINK;
            default -> color = Color.WHITE;
        }
        return color;
    }
}
