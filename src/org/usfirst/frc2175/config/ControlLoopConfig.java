package org.usfirst.frc2175.config;

import java.util.Properties;
import java.util.logging.Logger;

public class ControlLoopConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME =
            "controlLoopConstants.properties";
    private final Logger log = Logger.getLogger(getClass().getName());

    private double visionTurnPID_kProportional;
    private double visionTurnPID_kIntegral;
    private double visionTurnPID_kDerivative;
    private double visionTurnPID_absTolerance;
    private double visionTurnPID_centerCamera;
    private double visionTurnPID_maxRange;
    private double visionTurnPID_minRange;

    private double gyroTurnPID_kProportional;
    private double gyroTurnPID_kIntegral;
    private double gyroTurnPID_kDerivative;
    private double gyroTurnPID_absTolerance;
    private double gyroTurnPID_maxRange;
    private double gyroTurnPID_minRange;

    private double driveInchesPID_kProportional;
    private double driveInchesPID_kIntegral;
    private double driveInchesPID_kDerivative;
    private double driveInchesPID_absTolerance;
    private double driveInchesPID_maxRange;
    private double driveInchesPID_minRange;

    @Override
    protected String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {

        // VisionTurn
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

        // Gyro
        gyroTurnPID_kProportional = getDoublePropertyValue(
                "powertrain.gyroTurnPID.kProportional", properties);

        gyroTurnPID_kIntegral = getDoublePropertyValue(
                "powertrain.gyroTurnPID.kIntegral", properties);

        gyroTurnPID_kDerivative = getDoublePropertyValue(
                "powertrain.gyroTurnPID.kDerivative", properties);

        gyroTurnPID_absTolerance = getDoublePropertyValue(
                "powertrain.gyroTurnPID.absTolerance", properties);

        gyroTurnPID_maxRange = getDoublePropertyValue(
                "powertrain.gyroTurnPID.range.max", properties);

        gyroTurnPID_minRange = getDoublePropertyValue(
                "powertrain.gyroTurnPID.range.min", properties);

        // Drive Inches
        driveInchesPID_kProportional = getDoublePropertyValue(
                "powertrain.driveInchesPID.kProportional", properties);

        driveInchesPID_kIntegral = getDoublePropertyValue(
                "powertrain.driveInchesPID.kIntegral", properties);

        driveInchesPID_kDerivative = getDoublePropertyValue(
                "powertrain.driveInchesPID.kDerivative", properties);

        driveInchesPID_absTolerance = getDoublePropertyValue(
                "powertrain.driveInchesPID.absTolerance", properties);

        driveInchesPID_maxRange = getDoublePropertyValue(
                "powertrain.driveInchesPID.range.max", properties);

        driveInchesPID_minRange = getDoublePropertyValue(
                "powertrain.driveInchesPID.range.min", properties);
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

    public double getGyroTurnPID_kProportional() {
        return gyroTurnPID_kProportional;
    }

    public double getGyroTurnPID_kIntegral() {
        return gyroTurnPID_kIntegral;
    }

    public double getGyroTurnPID_kDerivative() {
        return gyroTurnPID_kDerivative;
    }

    public double getGyroTurnPID_absTolerance() {
        return gyroTurnPID_absTolerance;
    }

    public double getGyroTurnPID_maxRange() {
        return gyroTurnPID_maxRange;
    }

    public double getGyroTurnPID_minRange() {
        return gyroTurnPID_minRange;
    }

    public double getDriveInchesPID_kProportional() {
        return driveInchesPID_kProportional;
    }

    public double getDriveInchesPID_kIntegral() {
        return driveInchesPID_kIntegral;
    }

    public double getDriveInchesPID_kDerivative() {
        return driveInchesPID_kDerivative;
    }

    public double getDriveInchesPID_absTolerance() {
        return driveInchesPID_absTolerance;
    }

    public double getDriveInchesPID_maxRange() {
        return driveInchesPID_maxRange;
    }

    public double getDriveInchesPID_minRange() {
        return driveInchesPID_minRange;
    }

}
