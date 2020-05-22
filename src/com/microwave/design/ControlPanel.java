package com.microwave.design;

import com.microwave.state.ModeState;
import com.microwave.state.PowerState;
import com.microwave.state.StartButtonState;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public static final String START = StartButtonState.START.getValue();
    private JTextField timerValue;
    private JButton add1SecButton;
    private JButton add5SecButton;
    private JButton subtract1SecButton;
    private JButton subtract5SecButton;
    private JButton clearButton;
    private JComboBox modeBox;
    private JComboBox powerBox;
    private JButton startButton;
    private JButton defaultButton;

    public ControlPanel() {
        super(new BorderLayout());
        createControlPanel();
    }

    private void createControlPanel() {
        JPanel timerOperationsPanel = new JPanel(new BorderLayout());

        timerValue = new JTextField("0");
        timerValue.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        timerValue.setEditable(false);

        add1SecButton = new JButton("+ 1sec");
        configAddTimerButton(timerValue, add1SecButton, 1);

        subtract1SecButton = new JButton("- 1sec");
        configSubtractTimerButton(timerValue, subtract1SecButton, 1);

        add5SecButton = new JButton("+ 5sec");
        configAddTimerButton(timerValue, add5SecButton, 5);

        subtract5SecButton = new JButton("- 5sec");
        configSubtractTimerButton(timerValue, subtract5SecButton, 5);

        clearButton = new JButton("clear timer");
        clearButton.addActionListener(e -> timerValue.setText(String.valueOf(0)));

        JPanel addSubPanel = new JPanel(new GridLayout(2, 2));

        addSubPanel.add(add1SecButton);
        addSubPanel.add(subtract1SecButton);
        addSubPanel.add(add5SecButton);
        addSubPanel.add(subtract5SecButton);
        timerOperationsPanel.add(addSubPanel, BorderLayout.NORTH);
        timerOperationsPanel.add(clearButton, BorderLayout.SOUTH);

        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.add(timerValue, BorderLayout.NORTH);
        timerPanel.add(timerOperationsPanel, BorderLayout.CENTER);

        JPanel modePanel = new JPanel(new BorderLayout());
        modeBox = new JComboBox(new ModeState[]{ModeState.WARM_UP, ModeState.UNFROZE});
        modePanel.add(modeBox);

        JPanel powerPanel = new JPanel(new BorderLayout());
        powerBox = new JComboBox(new PowerState[]{PowerState.LOW, PowerState.MEDIUM, PowerState.MAX});
        powerPanel.add(powerBox);

        JPanel selectPanel = new JPanel(new BorderLayout());
        selectPanel.add(timerPanel, BorderLayout.NORTH);
        selectPanel.add(modePanel, BorderLayout.CENTER);
        selectPanel.add(powerPanel, BorderLayout.SOUTH);

        startButton = new JButton(START);

        this.add(selectPanel, BorderLayout.NORTH);
        this.add(startButton, BorderLayout.CENTER);
        defaultButton = new JButton("stop");
        this.add(defaultButton, BorderLayout.SOUTH);
    }

    private void configSubtractTimerButton(JTextField timerValue, JButton subtract30SecButton, int i) {
        subtract30SecButton.addActionListener(e -> {
            int current = getIntTimerValue();
            int newValue = current - i;
            timerValue.setText(newValue < 0 ? "0" : String.valueOf(newValue));
        });
    }

    private void configAddTimerButton(JTextField timerValue, JButton add5SecButton, int i) {
        add5SecButton.addActionListener(e -> {
            int current = getIntTimerValue();
            timerValue.setText(String.valueOf(current + i));
        });
    }

    public Integer getIntTimerValue() {
        return Integer.valueOf(timerValue.getText());
    }

    public JTextField getTimerValue() {
        return timerValue;
    }

    public JComboBox getModeBox() {
        return modeBox;
    }

    public JComboBox getPowerBox() {
        return powerBox;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getDefaultButton() {
        return defaultButton;
    }

    public void setNewTimerValue(int newValue) {
        timerValue.setText(String.valueOf(newValue));
    }

    public void setDefaultState() {
        setNewTimerValue(0);
        modeBox.setSelectedIndex(0);
        powerBox.setSelectedIndex(0);
    }
}
