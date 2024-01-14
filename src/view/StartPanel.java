package view;

import controler.GameController;
import model.Mode;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;

public class StartPanel extends JPanel {
    private int verticalGap = 70; 
    private GroupLayout layout;
    private GroupLayout.SequentialGroup vGroup;

    private JLabel startLabel;
    private JTextField nameTextField;
    private JSlider roundSlider;
    private JSlider pawnSlider;
    private JSlider pawnLengthSlider;
    private JSlider attemptSlider;
    private JList<String> modeList;

    private JLabel nameLabel;
    private JLabel nbRoundLabel;
    private JLabel nbPawnLabel;
    private JLabel nbPawnLengthLabel;
    private JLabel nbAttemptsLabel;
    private JLabel modeLabel;

    /**
     * StartPanel builder
     * @param gameController
     */
    public StartPanel(GameController gameController) {
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startLabel = new JLabel("Mastermind");
        startLabel.setFont(new Font("Constantia", Font.BOLD, 30));
        northPanel.add(startLabel);
        add(northPanel, BorderLayout.NORTH);

        JPanel settingPanel = new JPanel();
        settingPanel.setBorder(BorderFactory.createEmptyBorder(75, 20, 0, 20));
        settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.Y_AXIS));
        JPanel settings = new JPanel();
        layout = new GroupLayout(settings);
        settings.setLayout(layout);
        settingPanel.add(settings);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        Font labelFont = new Font("Constantia", Font.PLAIN, 20);

        nameLabel = new JLabel("Nom :");
        nameLabel.setFont(labelFont);

        nameTextField = new JTextField();
        nameTextField.setColumns(13);
        nameTextField.setFont(new Font("Constantia", Font.PLAIN, 16));
        nameTextField.setText(System.getProperty("user.name"));

        nbRoundLabel = new JLabel("Nombre de rounds :");
        nbRoundLabel.setFont(labelFont);

        roundSlider = new JSlider(1, 5, 3);
        roundSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setSlider(roundSlider);

        nbPawnLabel = new JLabel("Nombre de pions :");
        nbPawnLabel.setFont(labelFont);

        pawnSlider = new JSlider(4, 8, 8);
        pawnSlider.setCursor(new Cursor(12));
        setSlider(pawnSlider);

        nbPawnLengthLabel = new JLabel("Taille de la combinaison :");
        nbPawnLengthLabel.setFont(labelFont);

        pawnLengthSlider = new JSlider(2, 6, 4);
        pawnLengthSlider.setCursor(new Cursor(12));
        setSlider(pawnLengthSlider);

        nbAttemptsLabel = new JLabel("Nombre de tentatives :");
        nbAttemptsLabel.setFont(labelFont);

        attemptSlider = new JSlider(2, 12, 10);
        attemptSlider.setCursor(new Cursor(12));
        setSlider(attemptSlider);

        modeLabel = new JLabel("Mode :");
        modeLabel.setFont(labelFont);

        String[] mode = {"Facile", "Classique", "Numérique"};
        modeList = new JList<>(mode);
        modeList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        modeList.setFont(new Font("Constantia", Font.PLAIN, 15));
        modeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modeList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        modeList.setSelectedIndex(1);
        modeList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
        });
        modeList.setBorder(new LineBorder(Color.BLACK, 1));
        modeList.setFixedCellHeight(21);
        modeList.setVisibleRowCount(mode.length);
        modeList.setSelectionBackground(Color.LIGHT_GRAY);
        modeList.setPreferredSize(new Dimension(200, modeList.getPreferredSize().height));
        modeList.setValueIsAdjusting(true);
        modeList.setFixedCellWidth(200);

        JButton button = new JButton("Next");
        button.setFont(new Font("Constantia", Font.PLAIN, 20));
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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pawnSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pawnLengthSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(attemptSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(modeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));

        layout.setHorizontalGroup(hGroup);

        vGroup = createVGroup();  // Créez initialement le vGroup
        layout.setVerticalGroup(vGroup);

        add(settingPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Allows you to redefine the placement and order of height components in the control panel
     * @return layout.createSequentialGroup()
     */
    private GroupLayout.SequentialGroup createVGroup() {
        return layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameTextField))
                .addGap(verticalGap)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nbRoundLabel)
                        .addComponent(roundSlider))
                .addGap(verticalGap)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nbPawnLabel)
                        .addComponent(pawnSlider))
                .addGap(verticalGap)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nbPawnLengthLabel)
                        .addComponent(pawnLengthSlider))
                .addGap(verticalGap)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nbAttemptsLabel)
                        .addComponent(attemptSlider))
                .addGap(verticalGap)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(modeLabel)
                        .addComponent(modeList));
    }

    /**
     * Updates I change the placement and spacing of components in the height axis
     */
    private void updateVerticalGap() {
        GroupLayout.SequentialGroup newVGroup = createVGroup();
        layout.setVerticalGroup(newVGroup);
        vGroup = newVGroup;
    }

    /**
     * Set new spacing size for start panel components
     * @param size
     */
    public void setSizeWindow(int size) {
        this.verticalGap = (size-startLabel.getHeight()-nameLabel.getHeight()-nameTextField.getHeight()-roundSlider.getHeight()-pawnSlider.getHeight()-nbPawnLengthLabel.getHeight()-attemptSlider.getHeight()-modeList.getHeight())/12;
        updateVerticalGap();
    }

    /**
     * Allows you to define the behavior and display of JSliders
     * @param slider
     */
    public void setSlider(JSlider slider){
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(1); 
        slider.setSnapToTicks(false);
        slider.setMinorTickSpacing(1);
        slider.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int mouseX = e.getX();
                    int totalTicks = slider.getMaximum() - slider.getMinimum();
                    int tickSpacing = slider.getMajorTickSpacing();
                    int selectedTick = Math.round((float) mouseX / (float) slider.getWidth() * totalTicks / tickSpacing);
                    int value = slider.getMinimum() + selectedTick * tickSpacing;
    
                    slider.setValue(value);
                }
            }
        });
    }
    
    /**
     * Recovers the current mode
     * @param indexCB
     * @return Mode
     */
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
