package com.microwave.model.convertorEnergySystem.entity;

public class Transformator {
    private long id;
    private String model;
    private int maxConsumeVoltage;

    public Transformator() {
    }

    public Transformator(long id, String model, int maxConsumeVoltage) {
        this.id = id;
        this.model = model;
        this.maxConsumeVoltage = maxConsumeVoltage;
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

    public int getMaxConsumeVoltage() {
        return maxConsumeVoltage;
    }

    public void setMaxConsumeVoltage(int maxConsumeVoltage) {
        this.maxConsumeVoltage = maxConsumeVoltage;
    }

    @Override
    public String toString() {
        return "Transformator{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", maxConsumeVoltage=" + maxConsumeVoltage +
                '}';
    }
}
