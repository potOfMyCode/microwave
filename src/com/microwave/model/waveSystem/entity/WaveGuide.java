package com.microwave.model.waveSystem.entity;

import com.microwave.design.LogArea;

import java.util.Objects;

public class WaveGuide {
    private long id;
    private String model;

    public WaveGuide() { }

    public WaveGuide(long id, String model) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WaveGuide)) return false;
        WaveGuide waveGuide = (WaveGuide) o;
        return id == waveGuide.id &&
                model.equals(waveGuide.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }

    @Override
    public String toString() {
        return "WaveGuide{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }

    public void emitWaves(int waveAmount, LogArea logArea) {
        logArea.append("WaveGuide: " + waveAmount + "will be emitted in each second of process to prepare dish");
    }

    public void pauseEmitWave(LogArea logArea) {
        logArea.append("WaveGuide: emitting of waves are paused");
    }

    public void resumeEmitWave(LogArea logArea) {
        logArea.append("WaveGuide: resume emitting waves");
    }

    public void endProcess(LogArea logArea) {
        logArea.append("WaveGuide: end emitting waves");
    }
}
