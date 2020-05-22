package com.microwave.model.convertorEnergySystem;

import com.microwave.design.LogArea;
import com.microwave.model.convertorEnergySystem.entity.HighVoltageFuse;
import com.microwave.model.convertorEnergySystem.entity.Transformator;

public class ConverterEnergySystem {
    private static final int DEFAULT_CONSUME_VOLTAGE = 220;
    private Transformator transformator;
    private HighVoltageFuse highVoltageFuse;

    public ConverterEnergySystem(Transformator transformator, HighVoltageFuse highVoltageFuse) {
        this.transformator = transformator;
        this.highVoltageFuse = highVoltageFuse;
    }

    public int convertAndTransferElectricity(LogArea logArea) {
        return convertAndTransferElectricity(DEFAULT_CONSUME_VOLTAGE, logArea);
    }

    public int convertAndTransferElectricity(int voltageLevel, LogArea logArea) {
        if (voltageLevel <= transformator.getMaxConsumeVoltage() && !highVoltageFuse.isLimitExceeded()){
            return convertToEnergy(voltageLevel, logArea);
        } else {
            return turnOffSystem(voltageLevel, logArea);
        }
    }

    private int turnOffSystem(int voltageLevel, LogArea logArea) {
        highVoltageFuse.terminateWork(voltageLevel, logArea);
        return -1;
    }

    private int convertToEnergy(int voltageLevel, LogArea logArea) {
        logArea.append("ConverterEnergy: Convert energy " + voltageLevel + "V and transfer to wave system");
        return voltageLevel*100;
    }

    public void pauseConvertingEnergy(LogArea logArea) {
        logArea.append("ConverterEnergy: pause converting energy");
    }

    public void resumeProducingEnergy(LogArea logArea) {
        logArea.append("ConverterEnergy: resume producing energy");
    }

    public void endProducingEnergy(LogArea logArea) {
        logArea.append("ConverterEnergy: end producing energy");
    }
}
