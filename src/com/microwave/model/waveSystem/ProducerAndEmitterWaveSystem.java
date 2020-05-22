package com.microwave.model.waveSystem;

import com.microwave.design.LogArea;
import com.microwave.model.convertorEnergySystem.ConverterEnergySystem;
import com.microwave.model.convertorEnergySystem.entity.HighVoltageFuse;
import com.microwave.model.convertorEnergySystem.entity.Transformator;
import com.microwave.model.convertorEnergySystem.entity.VoltageException;
import com.microwave.model.waveSystem.entity.Fan;
import com.microwave.model.waveSystem.entity.Magnetron;
import com.microwave.model.waveSystem.entity.ThermalFuse;
import com.microwave.model.waveSystem.entity.WaveGuide;

public class ProducerAndEmitterWaveSystem {
    private Magnetron magnetron;
    private ThermalFuse thermalFuse;
    private WaveGuide waveGuide;
    private Fan fan;

    private ConverterEnergySystem converterEnergySystem;

    public ProducerAndEmitterWaveSystem(Magnetron magnetron, ThermalFuse thermalFuse, WaveGuide waveGuide, Fan fan) {
        this.magnetron = magnetron;
        this.thermalFuse = thermalFuse;
        this.waveGuide = waveGuide;
        this.fan = fan;
        this.converterEnergySystem = new ConverterEnergySystem(new Transformator(345, "transformator1", 360),
                new HighVoltageFuse(456, "highVoltage1"));
    }

    public void produceAndEmitWaves(LogArea logArea) throws VoltageException {
        fan.turnOn(logArea);
        int waveAmount = magnetron.produceWave(converterEnergySystem, logArea);
        if (waveAmount > thermalFuse.MAX_AMOUNT_WAVE) {
            thermalFuse.turnOffSystem(logArea);
        }
        waveGuide.emitWaves(waveAmount, logArea);
        logArea.append("WaveSystem: working now");
    }


    public void pauseProduceAndEmitWaves(LogArea logArea) {
        magnetron.pauseProducingWave(logArea, converterEnergySystem);
        waveGuide.pauseEmitWave(logArea);
        fan.reducePowerForPauseMode(logArea);
        logArea.append("WaveSystem: Producing and emitting waves are paused");
    }

    public void resumeEmitting(LogArea logArea) {
        magnetron.resumeProducingWave(logArea, converterEnergySystem);
        waveGuide.resumeEmitWave(logArea);
        fan.resumePowerForWorkingMode(logArea);
        logArea.append("WaveSystem: Resuming of emitting waves");
    }

    public void endProcess(LogArea logArea) {
        magnetron.endProcess(logArea, converterEnergySystem);
        waveGuide.endProcess(logArea);
        fan.endProcess(logArea);
        logArea.append("WaveSystem: Ending of work of producer and emitter wave system");
    }
}
