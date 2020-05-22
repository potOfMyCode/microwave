package com.microwave.model.worckingChamberSystem.entity;

public class IronChamber {
    private long id;
    private String model;
    private int radiationResistance;

    public IronChamber() {
    }

    public IronChamber(long id, String model, int radiationResistance) {
        this.id = id;
        this.model = model;
        this.radiationResistance = radiationResistance;
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

    public int getRadiationResistance() {
        return radiationResistance;
    }

    public void setRadiationResistance(int radiationResistance) {
        this.radiationResistance = radiationResistance;
    }

    @Override
    public String toString() {
        return "IronChamber{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", radiationResistance=" + radiationResistance +
                '}';
    }
}
