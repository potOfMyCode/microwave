package com.microwave.model.palletRotationSystem;

public class RotatingElement {
    private long id;
    private String model;

    public RotatingElement() {
    }

    public RotatingElement(long id, String model) {
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
        return "RotatingElement{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
