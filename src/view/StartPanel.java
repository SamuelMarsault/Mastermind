package view;

import controler.GameController;
import model.Mode;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    
    public StartPanel(GameController gameController) {
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel startLabel = new JLabel("Mastermind");
        startLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // Définir une taille de police pour le label
        northPanel.add(startLabel);
        add(northPanel, BorderLayout.NORTH);

        JPanel settingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints settingGrid = new GridBagConstraints();
        settingGrid.insets = new Insets(50, 40, 20, 40); // Marge entre les composants

        Font labelFont = new Font("Arial", Font.PLAIN, 20);

        // Name of player
        settingGrid.gridwidth = 1;
        settingGrid.gridx = 0;
        settingGrid.gridy = 0;
        JLabel nameLabel = new JLabel("Nom :");
        nameLabel.setFont(labelFont);
        nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        settingPanel.add(nameLabel, settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 0;
        JTextField nameTextField = new JTextField();
        nameTextField.setColumns(13); 
        nameTextField.setText("Anonyme");
        settingPanel.add(nameTextField, settingGrid);

        // Number of Round
        settingGrid.gridx = 0;
        settingGrid.gridy = 1;
        JLabel nbRoundLabel = new JLabel("Nombre de rounds :");
        nbRoundLabel.setFont(labelFont);
        settingPanel.add(nbRoundLabel, settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 1;
        JSlider roundSlider = new JSlider(1, 5, 3);
        setSlider(roundSlider);
        settingPanel.add(roundSlider, settingGrid);

        // Number of Pawn
        settingGrid.gridx = 0;
        settingGrid.gridy = 2;
        JLabel nbPawnLabel = new JLabel("Nombre de pions :");
        nbPawnLabel.setFont(labelFont);
        settingPanel.add(nbPawnLabel, settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 2;
        JSlider pawnSlider = new JSlider(4, 8, 8);
        setSlider(pawnSlider);
        settingPanel.add(pawnSlider, settingGrid);

        // Number of pawn length
        settingGrid.gridx = 0;
        settingGrid.gridy = 3;
        JLabel nbPawnLengthLabel = new JLabel("Taille de la combinaison :");
        nbPawnLengthLabel.setFont(labelFont);
        settingPanel.add(nbPawnLengthLabel, settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 3;
        JSlider pawnLenghtSlider = new JSlider(2, 6, 4);
        setSlider(pawnLenghtSlider);
        settingPanel.add(pawnLenghtSlider, settingGrid);

        // Number of attempts
        settingGrid.gridx = 0;
        settingGrid.gridy = 4;
        JLabel nbAttemptsLabel = new JLabel("Nombre de tentatives :");
        nbAttemptsLabel.setFont(labelFont);
        settingPanel.add(nbAttemptsLabel, settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 4;
        JSlider attemptSlider = new JSlider(2, 12, 10);
        setSlider(attemptSlider);
        settingPanel.add(attemptSlider, settingGrid);

        // Mode
        settingGrid.gridx = 0;
        settingGrid.gridy = 5;
        JLabel modeLabel = new JLabel("Mode :");
        modeLabel.setFont(labelFont);
        settingPanel.add(modeLabel, settingGrid);
        settingGrid.gridx = 1;
        settingGrid.gridy = 5;
        String[] mode = {"Easy", "Classique", "Numerique"};
        JList<String> modeList = new JList<>(mode);
        modeList.setFont(new Font("Arial",Font.PLAIN,15));
        modeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modeList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        modeList.setSelectedIndex(1);
        settingPanel.add(modeList, settingGrid); 

        add(settingPanel, BorderLayout.CENTER);

        JButton button = new JButton("Next");
        button.setFont( new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(actionEvent -> {
            String name = nameTextField.getText();
            if (name.isEmpty() || name.isBlank()){
                name = "anonyme";
            }
            if (pawnSlider.getValue()<pawnLenghtSlider.getValue()){
                pawnSlider.setValue(pawnLenghtSlider.getValue());
            }
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
        slider.setSnapToTicks(true);
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
