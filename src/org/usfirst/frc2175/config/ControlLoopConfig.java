package org.usfirst.frc2175.config;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlLoopConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME =
            "ControlLoopConstants.properties";
    private final Logger log = Logger.getLogger(getClass().getName());

    private double visionTurnPID_kProportional;
    private double visionTurnPID_kIntegral;
    private double visionTurnPID_kDerivative;
    private double visionTurnPID_absTolerance;
    private double visionTurnPID_centerCamera;
    private double visionTurnPID_maxRange;
    private double visionTurnPID_minRange;

    @Override
    protected String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        visionTurnPID_kProportional = getDoublePropertyValue(
                "powertrain.visionTurnPID.kProportional", properties);

        visionTurnPID_kIntegral = getDoublePropertyValue(
                "powertrain.visionTurnPID.kIntegral", properties);

        visionTurnPID_kDerivative = getDoublePropertyValue(
                "powertrain.visionTurnPID.kDerivative", properties);

        visionTurnPID_absTolerance = getDoublePropertyValue(
                "powertrain.visionTurnPID.absTolerance", properties);

        visionTurnPID_centerCamera = getDoublePropertyValue(
                "powertrain.visionTurnPID.centerCamera", properties);

        visionTurnPID_maxRange = getDoublePropertyValue(
                "powertrain.visionTurnPID.range.max", properties);
        visionTurnPID_minRange = getDoublePropertyValue(
                "powertrain.visionTurnPID.range.min", properties);

        log.log(Level.CONFIG,
                "Setting vision turn PID: kP =" + visionTurnPID_kProportional
                        + "; kI=" + visionTurnPID_kIntegral + "; kD="
                        + visionTurnPID_kDerivative);
    }

    public double getVisionTurnPID_kProportional() {
        return -visionTurnPID_kProportional;
    }

    public double getVisionTurnPID_maxRange() {
        return visionTurnPID_maxRange;
    }

    public double getVisionTurnPID_minRange() {
        return visionTurnPID_minRange;
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
