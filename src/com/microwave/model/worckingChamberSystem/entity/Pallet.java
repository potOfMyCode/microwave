package com.microwave.model.worckingChamberSystem.entity;

public class Pallet {
    private long id;
    private String material;
    private int radius;

    public Pallet() {
    }

    public Pallet(long id, String material, int radius) {
        this.id = id;
        this.material = material;
        this.radius = radius;
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Pallet{" +
                "id=" + id +
                ", material='" + material + '\'' +
                ", radius=" + radius +
                '}';
    }
}
