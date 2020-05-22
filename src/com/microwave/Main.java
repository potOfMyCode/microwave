package com.microwave;

import com.microwave.design.ControlPanel;
import com.microwave.design.Microwave;
import com.microwave.model.waveSystem.ProducerAndEmitterWaveSystem;
import com.microwave.model.waveSystem.entity.Fan;
import com.microwave.model.waveSystem.entity.Magnetron;
import com.microwave.model.waveSystem.entity.ThermalFuse;
import com.microwave.model.waveSystem.entity.WaveGuide;
import com.microwave.model.worckingChamberSystem.WorkingChamber;
import com.microwave.model.worckingChamberSystem.entity.Door;
import com.microwave.model.worckingChamberSystem.entity.IronChamber;
import com.microwave.model.worckingChamberSystem.entity.Lamp;
import com.microwave.model.worckingChamberSystem.entity.Pallet;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Microwave microwave = createMicrowave();
        microwave.setTitle("Microwave");
        microwave.pack();
        microwave.setResizable(false);
        microwave.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        microwave.setLocationRelativeTo(null);
        microwave.setVisible(true);
    }

    private static Microwave createMicrowave() {
        return new Microwave(
                new WorkingChamber(
                        new IronChamber(567, "ironChamber1", 150),
                        new Door(678, "metalAndGlass", 120),
                        new Lamp(789, "lamp1", 15),
                        new Pallet(890, "glass", 10)
                ),
                new ProducerAndEmitterWaveSystem(
                        new Magnetron(432, "magnetron1", "magnetron-SUPER"),
                        new ThermalFuse(543, "thermalFuse1"),
                        new WaveGuide(654, "waweGuide1"),
                        new Fan(765, "fan1")
                ),
                new ControlPanel()
        );
    }
}
