package com.microwave.model.worckingChamberSystem.entity;

public class Door {
    private long id;
    private String material;
    private int radiationResistance;

    public Door() {
    }

    public Door(long id, String material, int radiationResistance) {
        this.id = id;
        this.material = material;
        this.radiationResistance = radiationResistance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getRadiationResistance() {
        return radiationResistance;
    }

    public void setRadiationResistance(int radiationResistance) {
        this.radiationResistance = radiationResistance;
    }

    @Override
    public String toString() {
        return "Door{" +
                "id=" + id +
                ", material='" + material + '\'' +
                ", radiationResistance=" + radiationResistance +
                '}';
    }
}
