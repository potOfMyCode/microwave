package com.microwave.model.worckingChamberSystem.entity;

import com.microwave.design.LogArea;

public class Lamp {
    private long id;
    private String model;
    private int voltage;

    public Lamp() {
    }

    public Lamp(long id, String model, int voltage) {
        this.id = id;
        this.model = model;
        this.voltage = voltage;
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

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return "Lamp{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", voltage=" + voltage +
                '}';
    }

    public void turnOn(LogArea logArea) {
        logArea.append("Lamp turned on");
    }
}
