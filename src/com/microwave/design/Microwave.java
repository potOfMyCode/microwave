package com.microwave.design;

import com.microwave.model.waveSystem.ProducerAndEmitterWaveSystem;
import com.microwave.model.worckingChamberSystem.WorkingChamber;
import com.microwave.state.ChamberState;
import com.microwave.state.StartButtonState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static com.microwave.state.ChamberState.*;

public class Microwave extends JFrame {

    public static final String DOOR_CLOSED_VALUE = DOOR_CLOSED_NO_PROCESS.getValue();
    public static final String DOOR_OPENED_VALUE = DOOR_OPENED_NO_PROCESS.getValue();
    public static final String DOOR_OPENED_PAUSE_VALUE = DOOR_OPEN_PAUSE.getValue();
    public static final String DOOR_CLOSED_COOKING_VALUE = DOOR_CLOSED_COOKING.getValue();
    public static final String DOOR_CLOSED_COOKING_ON_PAUSE_VALUE = DOOR_CLOSED_PAUSE.getValue();
    public static final String START_BUTTON_PAUSE = StartButtonState.PAUSE.getValue();
    public static final String START_BUTTON_RESUME = StartButtonState.RESUME.getValue();
    private static final String PROCESS_FINISHED_TAKE_DISHS = "Dish is ready!";
    public static final String START_BUTTON_START = StartButtonState.START.getValue();

    private WorkingChamber workingChamber;
    private ProducerAndEmitterWaveSystem waveSystem;
    private ControlPanel controlPanel;

    private JButton doorOpener;
    private JButton workingChamberButton;
    private LogArea logArea;

    private Thread timerThread;

    private static boolean isDoorClosed = true;
    private static boolean isProcessExist = false;

    public Microwave(WorkingChamber workingChamber, ProducerAndEmitterWaveSystem waveSystem, ControlPanel controlPanel) {
        this.workingChamber = workingChamber;
        this.waveSystem = waveSystem;
        this.controlPanel = controlPanel;
        configureMicrowave();
        addEventListeners(controlPanel);
    }

    public void turnOnLight() {
        workingChamberButton.setBackground(Color.YELLOW);
        logArea.append("Turn on light");
    }

    public void turnOffLight() {
        workingChamberButton.setBackground(Color.CYAN);
        logArea.append("Turn off light");
    }

    private void addEventListeners(ControlPanel controlPanel) {
        JButton startButton = controlPanel.getStartButton();

        startButton.addActionListener(event -> {
            StartButtonState state = StartButtonState.getByStringName(startButton.getText());
            switch (Objects.requireNonNull(state)) {
                case START:
                    if (isDoorClosed && controlPanel.getIntTimerValue() > 0) {
                        workingChamberButton.setText(DOOR_CLOSED_COOKING_VALUE);
                        startProcess();
                        isProcessExist = true;
                    } else {
                        logArea.appendImportant("Door need be closed and timer value not 0");
                    }
                    break;
                case PAUSE:
                    if (isDoorClosed && controlPanel.getIntTimerValue() > 0) {
                        workingChamberButton.setText(DOOR_CLOSED_COOKING_ON_PAUSE_VALUE);
                        pause();
                    } else {
                        logArea.appendImportant("For set process to pause door need be closed and timer value not 0");
                    }
                    break;
                case RESUME:
                    if (isDoorClosed && controlPanel.getIntTimerValue() > 0) {
                        workingChamberButton.setText(DOOR_CLOSED_COOKING_VALUE);
                        resume();
                    } else {
                        logArea.appendImportant("For resuming door need be closed and timer value not 0");
                    }
                    break;
            }
        });

        doorOpener.addActionListener(e ->
        {
            ChamberState chamberState = getByString(workingChamberButton.getText());
            switch (chamberState) {
                case DOOR_CLOSED_NO_PROCESS:
                    workingChamberButton.setText(DOOR_OPENED_VALUE);
                    if (isProcessExist) {
                        pause();
                    }
                    isDoorClosed = false;
                    turnOnLight();
                    break;
                case DOOR_OPENED_NO_PROCESS:
                    workingChamberButton.setText(DOOR_CLOSED_VALUE);
                    if (isProcessExist) {
                        resume();
                    }
                    isDoorClosed = true;
                    turnOffLight();
                    break;
                case DOOR_CLOSED_COOKING:
                    workingChamberButton.setText(DOOR_OPENED_PAUSE_VALUE);
                    if (isProcessExist) {
                        pause();
                    }
                    startButton.setText(START_BUTTON_RESUME);
                    isDoorClosed = false;
                    break;
                case DOOR_CLOSED_PAUSE:
                    workingChamberButton.setText(DOOR_OPENED_PAUSE_VALUE);
                    isDoorClosed = false;
                    break;
                case DOOR_OPEN_PAUSE:
                    workingChamberButton.setText(DOOR_CLOSED_COOKING_ON_PAUSE_VALUE);
                    isDoorClosed = true;
                    break;
            }
        });

        JButton stopButton = controlPanel.getDefaultButton();
        stopButton.addActionListener(e -> {
            endProcessForInnerSystem();
            if (isProcessExist) {
                timerThread.interrupt();
                logArea.appendImportant("Working was terminated");
            }
            workingChamberButton.setText(DOOR_CLOSED_VALUE);
            turnOffLight();
            logArea.clearLog();
            controlPanel.setDefaultState();
        });
    }

    private void startProcess() {
        logArea.appendImportant("Working start");
        logArea.appendImportant("Mode: " + controlPanel.getModeBox().getSelectedItem() + ", power level: " + controlPanel.getPowerBox().getSelectedItem());
        controlPanel.getStartButton().setText(START_BUTTON_PAUSE);
        waveSystem.produceAndEmitWaves(logArea);
        workingChamber.prepearingDish(logArea);
        turnOnLight();
        startTimerThread();
    }

    private void pause() {
        logArea.appendImportant("Process of cooking on pause");
        controlPanel.getStartButton().setText(START_BUTTON_RESUME);
        waveSystem.pauseProduceAndEmitWaves(logArea);
        workingChamber.pausePrepearingDish(logArea);
        timerThread.interrupt();
    }

    private void resume() {
        logArea.appendImportant("Process resumed");
        controlPanel.getStartButton().setText(START_BUTTON_PAUSE);
        waveSystem.resumeEmitting(logArea);
        workingChamber.resumePreparing(logArea);
        startTimerThread();
    }

    private void endProcessForInnerSystem() {
        logArea.appendImportant("Process finished! Please take a dish.");
        waveSystem.endProcess(logArea);
        workingChamber.endProcess(logArea);
        isProcessExist = false;
        workingChamberButton.setText(PROCESS_FINISHED_TAKE_DISHS);
        controlPanel.getStartButton().setText(START_BUTTON_START);
    }

    private void startTimerThread() {
        timerThread = new Thread(() -> {
            try {
                int timerValue = controlPanel.getIntTimerValue();
                while (timerValue > 0) {
                    Thread.sleep(1000);
                    controlPanel.setNewTimerValue(--timerValue);
                }
                if (timerValue == 0) {
                    endProcessForInnerSystem();
                }
            } catch (InterruptedException ex) {
            }
        });
        timerThread.start();
    }



    private void configureMicrowave() {
        logArea = new LogArea();

        workingChamberButton = new JButton(DOOR_CLOSED_VALUE);
        workingChamberButton.setBackground(Color.CYAN);
        doorOpener = new JButton("open/close door");

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(workingChamberButton, BorderLayout.CENTER);
        leftPanel.add(doorOpener, BorderLayout.PAGE_END);

        add(leftPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);
        add(logArea, BorderLayout.SOUTH);
    }
}
