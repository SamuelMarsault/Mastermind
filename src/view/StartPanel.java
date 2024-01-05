package view;

import controler.GameController;
import model.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StartPanel extends JPanel {
    public StartPanel(GameController gameController) {
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel startLabel = new JLabel("StartPanel");
        startLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // Définir une taille de police pour le label
        northPanel.add(startLabel);
        add(northPanel, BorderLayout.NORTH);

        JPanel settingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints settingGrid = new GridBagConstraints();
        settingGrid.insets = new Insets(20, 10, 20, 10); // Marge entre les composants

        // Name of player
        settingGrid.gridx = 0;
        settingGrid.gridy = 0;
        settingPanel.add(new JLabel("Name:"), settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 0;
        settingGrid.gridwidth = 2;
        JTextField nameTextField = new JTextField();
        nameTextField.setColumns(13); 
        settingPanel.add(nameTextField, settingGrid);
        settingGrid.gridwidth = 1; 


        // Number of Round
        settingGrid.gridx = 0;
        settingGrid.gridy = 1;
        settingPanel.add(new JLabel("Number of Round:"), settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 1;
        JSlider roundSlider = new JSlider(1, 5, 3);
        setSlider(roundSlider);
        settingPanel.add(roundSlider, settingGrid);

        // Number of Pawn
        settingGrid.gridx = 0;
        settingGrid.gridy = 2;
        settingPanel.add(new JLabel("Number of Pawn:"), settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 2;
        JSlider pawnSlider = new JSlider(4, 8, 8);
        setSlider(pawnSlider);
        settingPanel.add(pawnSlider, settingGrid);

        // Number of pawn length
        settingGrid.gridx = 0;
        settingGrid.gridy = 3;
        settingPanel.add(new JLabel("Number of pawn Length:"), settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 3;
        JSlider pawnLenghtSlider = new JSlider(2, 6, 4);
        setSlider(pawnLenghtSlider);
        settingPanel.add(pawnLenghtSlider, settingGrid);

        // Number of attempts
        settingGrid.gridx = 0;
        settingGrid.gridy = 4;
        settingPanel.add(new JLabel("Number of attempts:"), settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 4;
        JSlider attemptSlider = new JSlider(2, 12, 10);
        setSlider(attemptSlider);
        settingPanel.add(attemptSlider, settingGrid);

        // Mode
        settingGrid.gridx = 0;
        settingGrid.gridy = 5;
        settingPanel.add(new JLabel("Mode:"), settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 5;
        settingGrid.gridwidth = 2;
        String[] mode = {"Easy", "Classique", "Numerique"};
        JList<String> modeList = new JList<>(mode);
        modeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modeList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        modeList.setSelectedIndex(1);
        settingPanel.add(modeList, settingGrid);
        settingGrid.gridwidth = 1; 

        add(settingPanel, BorderLayout.CENTER);

        JButton button = new JButton("Next");
        button.addActionListener(actionEvent -> {
            String name = nameTextField.getText();
            if (name.isEmpty() || name.isBlank()){
                name = "anonyme";
            }
            System.out.println(name);
            gameController.startGame(attemptSlider.getValue(), pawnLenghtSlider.getValue(), pawnSlider.getValue(), getMode(modeList.getLeadSelectionIndex()), name, roundSlider.getValue());
        });
        add(button, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void setSlider(JSlider slider){
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(1); 
        slider.setMinorTickSpacing(1); 
    }

    public Mode getMode(int indexCB){
        switch (indexCB) {
            case 0:
                return Mode.EASY;
            
            case 1:
                return Mode.CLASSIC;
        
            default:
                return Mode.NUMERIC;
        }
    }
}
