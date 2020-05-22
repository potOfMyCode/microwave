package com.microwave.model.waveSystem.entity;

import com.microwave.design.LogArea;
import com.microwave.model.convertorEnergySystem.ConverterEnergySystem;
import com.microwave.model.convertorEnergySystem.entity.VoltageException;

import java.util.Objects;

public class Magnetron {
    private long id;
    private String model;
    private String name;

    public int produceWave(ConverterEnergySystem converterEnergySystem, LogArea logArea) throws VoltageException {
        int waveAmount = converterEnergySystem.convertAndTransferElectricity(logArea);
        logArea.append(name + ": Produced " + waveAmount + " waves");
        return waveAmount;
    }

    public void pauseProducingWave(LogArea logArea, ConverterEnergySystem converterEnergySystem) {
        converterEnergySystem.pauseConvertingEnergy(logArea);
        logArea.append(name + ": producing of waves are paused");
    }

    public Magnetron() {

    }

    public Magnetron(long id, String model, String name) {
        this.id = id;
        this.model = model;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magnetron)) return false;
        Magnetron magnetron = (Magnetron) o;
        return id == magnetron.id &&
                model.equals(magnetron.model) &&
                name.equals(magnetron.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, name);
    }

    @Override
    public String toString() {
        return "Magnetron{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void resumeProducingWave(LogArea logArea, ConverterEnergySystem converterEnergySystem) {
        converterEnergySystem.resumeProducingEnergy(logArea);
        logArea.append(name + ": resume producing waves");
    }

    public void endProcess(LogArea logArea, ConverterEnergySystem converterEnergySystem) {
        converterEnergySystem.endProducingEnergy(logArea);
        logArea.append(name + ": end producing waves");
    }
}
