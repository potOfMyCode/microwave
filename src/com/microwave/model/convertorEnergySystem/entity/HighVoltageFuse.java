package com.microwave.model.convertorEnergySystem.entity;

import com.microwave.design.LogArea;

public class HighVoltageFuse {
    public static final String LIMIT_EXCEEDED = "Limit was exceeded. Machine will be turn off.";
    private long id;
    private String model;
    private boolean isLimitExceeded;

    public HighVoltageFuse() {
        isLimitExceeded = false;
    }

    public HighVoltageFuse(long id, String model) {
        this.id = id;
        this.model = model;
        this.isLimitExceeded = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isLimitExceeded() {
        return isLimitExceeded;
    }

    public void setLimitExceeded(boolean limitExceeded) {
        isLimitExceeded = limitExceeded;
    }

    @Override
    public String toString() {
        return "HighVoltageFuse{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", isLimitExceeded=" + isLimitExceeded +
                '}';
    }

    public void terminateWork(int voltageLevel, LogArea logArea) {
        logArea.append(LIMIT_EXCEEDED + " Voltage level: " + voltageLevel);
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new VoltageException(LIMIT_EXCEEDED);
    }
}
