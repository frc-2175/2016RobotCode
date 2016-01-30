package org.usfirst.frc2175.config;

import java.util.Properties;

public class ControlLoopConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME =
            "ControlLoopConstants.properties";

    private double visionTurnPID_kProportional;
    private double visionTurnPID_kIntegral;
    private double visionTurnPID_kDerivative;
    private double visionTurnPID_absTolerance;
    private double visionTurnPID_centerCamera;

    @Override
    protected String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        visionTurnPID_kProportional = getDoublePropertyValue(
                "drivetrain.visionTurnPID.kProportional", properties);

        visionTurnPID_kIntegral = getDoublePropertyValue(
                "drivetrain.visionTurnPID.kIntegral", properties);

        visionTurnPID_kDerivative = getDoublePropertyValue(
                "drivetrain.visionTurnPID.kDerivative", properties);

        visionTurnPID_absTolerance = getDoublePropertyValue(
                "drivetrain.visionTurnPID.absTolerance", properties);

        visionTurnPID_centerCamera = getDoublePropertyValue(
                "drivetrain.visionTurnPID.centerCamera", properties);
    }

    public double getVisionTurnPID_kProportional() {
        return -visionTurnPID_kProportional;
    }

    public double getVisionTurnPID_kIntegral() {
        return -visionTurnPID_kIntegral;
    }

    public double getVisionTurnPID_kDerivative() {
        return -visionTurnPID_kDerivative;
    }

    public double getVisionTurnPID_absTolerance() {
        return visionTurnPID_absTolerance;
    }

    public double getVisionTurnPID_centerCamera() {
        return visionTurnPID_centerCamera;
    }
}
