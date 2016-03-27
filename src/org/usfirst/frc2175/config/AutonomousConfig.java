package org.usfirst.frc2175.config;

import java.util.Properties;

public class AutonomousConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "autonomous.properties";
    private double platformLength;
    private int sidePlateLength;
    private int marginOfError;
    private double travelLength;
    private int caution;
    private int extraShootLength;
    private int platformBeforeCheval;
    private int platformBeforePortcullis;
    private int turnAround;
    private int distanceBetweenDefenseAndRobot;

    @Override
    protected String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        double platformLength =
                getDoublePropertyValue("platform.length", properties);
        this.platformLength = platformLength;
        int sidePlateLength =
                getIntPropertyValue("side.plate.length", properties);
        this.sidePlateLength = sidePlateLength;
        int marginOfError = getIntPropertyValue("marginOfError", properties);
        this.marginOfError = marginOfError;
        int caution = getIntPropertyValue("caution", properties);
        this.caution = caution;
        int extraShootLength =
                getIntPropertyValue("extraShootLength", properties);
        this.extraShootLength = extraShootLength;
        int platformBeforeCheval =
                getIntPropertyValue("platformBeforeCheval", properties);
        this.platformBeforeCheval = platformBeforeCheval;
        int platformBeforePortcullis =
                getIntPropertyValue("platformBeforePortcullis", properties);
        this.platformBeforePortcullis = platformBeforePortcullis;
        int turnAround = getIntPropertyValue("turnAround", properties);
        this.turnAround = turnAround;
        int distanceBetweenDefenseAndRobot = getIntPropertyValue(
                "distanceBetweenDefenseAndRobot", properties);
        this.distanceBetweenDefenseAndRobot = distanceBetweenDefenseAndRobot;
        travelLength =
                this.platformLength + this.sidePlateLength + this.marginOfError;
    }

    public double getTravelLength() {
        return travelLength;
    }

    public int getCaution() {
        return caution;
    }

    public int getExtraShootLength() {
        return extraShootLength;
    }

    public int getPlatformBeforeCheval() {
        return platformBeforeCheval;
    }

    public int getPlatformBeforePortcullis() {
        return platformBeforePortcullis;
    }

    public int getTurnAround() {
        return turnAround;
    }

    public int getDistanceBetweenDefenseAndRobot() {
        return distanceBetweenDefenseAndRobot;
    }
}
