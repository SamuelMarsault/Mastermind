package view;

import model.HintLine;

import java.awt.*;

public interface HintDisplayMode {
    Color[] convertHintLine(HintLine hintLine);
}
