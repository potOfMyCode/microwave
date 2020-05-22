package com.microwave.model.palletRotationSystem;

import com.microwave.design.LogArea;
import com.microwave.model.worckingChamberSystem.entity.Pallet;

public class PalletRotationSystem {
    private RotatingElement rotatingElement;

    public PalletRotationSystem(RotatingElement rotatingElement) {
        this.rotatingElement = rotatingElement;
    }

    public void rotate (Pallet pallet, LogArea logArea){
        logArea.append("PalletRotationSystem: Rotating of pallet started");
    }

    public void pauseRotating(Pallet pallet, LogArea logArea) {
        logArea.append("PalletRotationSystem: Rotating system was paused");
    }

    public void resumeRotating(Pallet pallet, LogArea logArea) {
        logArea.append("PalletRotationSystem: Rotating system was resumed");
    }

    public void stopRotating(Pallet pallet, LogArea logArea) {
        logArea.append("PalletRotationSystem: Rotating system was stoped");
    }
}
