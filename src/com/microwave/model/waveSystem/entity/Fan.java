package com.microwave.model.waveSystem.entity;

import com.microwave.design.LogArea;

public class Fan {
    private long id;
    private String model;

    public Fan() {
    }

    public Fan(long id, String model) {
        this.id = id;
        this.model = model;
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

    @Override
    public String toString() {
        return "Fan{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }

    public void turnOn(LogArea logArea) {
        logArea.append("Fan: Fan is turn on. Magnetron cools down");
    }

    public void turnOff(LogArea logArea) {
        logArea.append("Fan: Fan is turn off");
    }

    public void reducePowerForPauseMode(LogArea logArea) {
        logArea.append("Fan: power is reduced until sysem is paused");
    }

    public void resumePowerForWorkingMode(LogArea logArea) {
        logArea.append("Fan: resume power for working mode");
    }

    public void endProcess(LogArea logArea) {
        logArea.append("Fan: end cooling");
    }
}
