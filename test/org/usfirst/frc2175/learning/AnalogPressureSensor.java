package org.usfirst.frc2175.learning;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogPressureSensor extends AnalogInput {

    // TODO refine constant values
    private static final double K_VOLTAGE_AT_10_PSI = 1;
    private static final double K_VOLTAGE_AT_120_PSI = 4;

    private double voltageConversionFactor;

    public AnalogPressureSensor(int channel) {
        super(channel);
        // TODO set voltage constants from properties
        calculateVoltageConversionFactor();
    }

    public double getPressure() {
        double pressure = getVoltage() * voltageConversionFactor;
        return pressure;
    }

    private void calculateVoltageConversionFactor() {
        voltageConversionFactor =
                110 / (K_VOLTAGE_AT_120_PSI - K_VOLTAGE_AT_10_PSI);
    }
}
