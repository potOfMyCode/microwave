package com.microwave.model.waveSystem.entity;

import com.microwave.design.LogArea;

public class ThermalFuse {
    public static final int MAX_AMOUNT_WAVE = 1000000;
    private long id;
    private String model;

    public ThermalFuse() {
    }

    public ThermalFuse(long id, String model) {
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
        return "ThermalFuse{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }

    public void turnOffSystem(LogArea logArea) {
        logArea.append("Max amount of created waves was exceed! The system will turn On");
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(-1);
    }
}

