package org.usfirst.frc2175.learning;

import org.usfirst.frc2175.config.RobotConfig;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogPressureSensor extends AnalogInput {

    private double kVoltageAt10PSI = 0;
    private double kVoltageAt120PSI = 0;

    private double voltageConversionFactor;

    public AnalogPressureSensor(int channel, RobotConfig robotConfig) {
        super(channel);
        // TODO set voltage constants from properties
        calculateVoltageConversionFactor();
    }

    public double getPressure() {
        double pressure = getVoltage() * voltageConversionFactor;
        return pressure;
    }

    private void calculateVoltageConversionFactor() {
        voltageConversionFactor = (kVoltageAt120PSI - kVoltageAt10PSI) / 110;
    }
}
