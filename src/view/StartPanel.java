package view;

import controler.GameController;
import model.Mode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(GameController gameController) {
        setLayout(new BorderLayout());


        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel startLabel = new JLabel("Mastermind");
        startLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        northPanel.add(startLabel);
        add(northPanel, BorderLayout.NORTH);

        JPanel settingPanel = new JPanel();
        
        settingPanel.setBorder(BorderFactory.createEmptyBorder(100, 20, 0, 20));
        settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.Y_AXIS));
        JPanel settings = new JPanel();
        GroupLayout layout = new GroupLayout(settings);
        settings.setLayout(layout);
        settingPanel.add(settings);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        Font labelFont = new Font("Arial", Font.PLAIN, 20);

        JLabel nameLabel = new JLabel("Nom :");
        nameLabel.setFont(labelFont);

        JTextField nameTextField = new JTextField();
        nameTextField.setColumns(13);
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameTextField.setText("Anonyme");

        JLabel nbRoundLabel = new JLabel("Nombre de rounds :");
        nbRoundLabel.setFont(labelFont);

        JSlider roundSlider = new JSlider(1, 5, 3);
        roundSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setSlider(roundSlider);

        JLabel nbPawnLabel = new JLabel("Nombre de pions :");
        nbPawnLabel.setFont(labelFont);

        JSlider pawnSlider = new JSlider(4, 8, 8);
        pawnSlider.setCursor(new Cursor(12));
        setSlider(pawnSlider);

        JLabel nbPawnLengthLabel = new JLabel("Taille de la combinaison :");
        nbPawnLengthLabel.setFont(labelFont);

        JSlider pawnLengthSlider = new JSlider(2, 6, 4);
        pawnLengthSlider.setCursor(new Cursor(12));
        setSlider(pawnLengthSlider);

        JLabel nbAttemptsLabel = new JLabel("Nombre de tentatives :");
        nbAttemptsLabel.setFont(labelFont);

        JSlider attemptSlider = new JSlider(2, 12, 10);
        attemptSlider.setCursor(new Cursor(12));
        setSlider(attemptSlider);

        JLabel modeLabel = new JLabel("Mode :");
        modeLabel.setFont(labelFont);

        String[] mode = {"Facile", "Classique", "Numérique"};
        JList<String> modeList = new JList<>(mode);
        modeList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        modeList.setFont(new Font("Arial", Font.PLAIN, 15));
        modeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modeList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        modeList.setSelectedIndex(1);
        modeList.setCellRenderer(new DefaultListCellRenderer());
        modeList.setBorder(new LineBorder(Color.BLACK, 1));
        modeList.setFixedCellHeight(21);
        modeList.setVisibleRowCount(mode.length);
        modeList.setSelectionBackground(Color.LIGHT_GRAY);

        JButton button = new JButton("Next");
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setCursor(new Cursor(12));
        button.addActionListener(actionEvent -> {
            String name = nameTextField.getText();
            if (name.isEmpty() || name.isBlank()) {
                name = "anonyme";
            }
            if (pawnSlider.getValue() < pawnLengthSlider.getValue()) {
                pawnSlider.setValue(pawnLengthSlider.getValue());
            }
            gameController.startGame(attemptSlider.getValue(), pawnLengthSlider.getValue(), pawnSlider.getValue(), getMode(modeList.getSelectedIndex()), name, roundSlider.getValue());
        });
        add(button, BorderLayout.SOUTH);


        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameLabel)
                .addComponent(nbRoundLabel)
                .addComponent(nbPawnLabel)
                .addComponent(nbPawnLengthLabel)
                .addComponent(nbAttemptsLabel)
                .addComponent(modeLabel))
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(roundSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(pawnSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(pawnLengthSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(attemptSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(modeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
        
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameTextField))
                .addGap(70)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nbRoundLabel)
                        .addComponent(roundSlider))
                .addGap(70)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nbPawnLabel)
                        .addComponent(pawnSlider))
                .addGap(70)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nbPawnLengthLabel)
                        .addComponent(pawnLengthSlider))
                .addGap(70)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nbAttemptsLabel)
                        .addComponent(attemptSlider))
                .addGap(70)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(modeLabel)
                        .addComponent(modeList));

        layout.setVerticalGroup(vGroup);

        add(settingPanel, BorderLayout.CENTER);
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
