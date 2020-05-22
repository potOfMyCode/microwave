package com.microwave.model.worckingChamberSystem;

import com.microwave.design.LogArea;
import com.microwave.model.palletRotationSystem.PalletRotationSystem;
import com.microwave.model.palletRotationSystem.RotatingElement;
import com.microwave.model.worckingChamberSystem.entity.Door;
import com.microwave.model.worckingChamberSystem.entity.IronChamber;
import com.microwave.model.worckingChamberSystem.entity.Lamp;
import com.microwave.model.worckingChamberSystem.entity.Pallet;

public class WorkingChamber {
    private IronChamber ironChamber;
    private Door door;
    private Lamp lamp;
    private Pallet pallet;

    private PalletRotationSystem palletRotationSystem;

    public WorkingChamber(IronChamber ironChamber, Door door, Lamp lamp, Pallet pallet) {
        this.ironChamber = ironChamber;
        this.door = door;
        this.lamp = lamp;
        this.pallet = pallet;
        this.palletRotationSystem = new PalletRotationSystem(new RotatingElement(234, "rotatingElement1"));
    }

    public void prepearingDish(LogArea logArea){
        logArea.append("WorkingChamber: working chamber is ready");
        lamp.turnOn(logArea);
        palletRotationSystem.rotate(pallet, logArea);
    }

    public void pausePrepearingDish(LogArea logArea) {
        palletRotationSystem.pauseRotating(pallet, logArea);
        logArea.append("WorkingChamber: Preparing dish in chamber are paused");
    }

    public void resumePreparing(LogArea logArea) {
        palletRotationSystem.resumeRotating(pallet, logArea);
        logArea.append("WorkingChamber: Resuming preparing dish");
    }

    public void endProcess(LogArea logArea) {
        palletRotationSystem.stopRotating(pallet, logArea);
        logArea.append("WorkingChamber: stop preparing dish");
    }
}
