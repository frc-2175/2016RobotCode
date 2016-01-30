package org.usfirst.frc2175.config;

import java.util.Properties;

public class ControlLoopConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "ControlLoopConstants.properties";

    private double visionTurnPID_kProportional;
    private double visionTurnPID_kIntegral;
    private double visionTurnPID_kDerivative;

    @Override
    protected String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        double visionTurnPID_kProportionalValue = getDoublePropertyValue("drivetrain.visionTurnPID.kProportional",
                properties);
        visionTurnPID_kProportional = visionTurnPID_kProportionalValue;

        double visionTurnPID_kIntegralValue = getDoublePropertyValue("drivetrain.visionTurnPID.kIntegral", properties);
        visionTurnPID_kIntegral = visionTurnPID_kIntegralValue;

        double visionTurnPID_kDerivativeValue = getDoublePropertyValue("drivetrain.visionTurnPID.kDerivative",
                properties);
        visionTurnPID_kDerivative = visionTurnPID_kDerivativeValue;

    }

    public double getVisionTurnPID_kProportional() {
        return visionTurnPID_kProportional;
    }

    public double getVisionTurnPID_kIntegral() {
        return visionTurnPID_kIntegral;
    }

    public double getVisionTurnPID_kDerivative() {
        return visionTurnPID_kDerivative;
    }

}
