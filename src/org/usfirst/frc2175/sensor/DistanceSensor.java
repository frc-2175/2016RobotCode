package org.usfirst.frc2175.sensor;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceSensor {
    private static final double INCHES_PER_FOOT = 12;

    private final Ultrasonic ultrasonicSensor;

    public DistanceSensor(Ultrasonic ultrasonicSensor) {
        this.ultrasonicSensor = ultrasonicSensor;
        configure(ultrasonicSensor);
    }

    protected void configure(Ultrasonic ultrasonicSensor) {
        ultrasonicSensor.setAutomaticMode(true);
        ultrasonicSensor.setDistanceUnits(Unit.kInches);
        ultrasonicSensor.startLiveWindowMode();
    }

    public void updateDashboard() {
        double rangeInches = ultrasonicSensor.getRangeInches();
        double rangeFeet = convertDistanceFromInchesToFeet(rangeInches);
        SmartDashboard.putNumber("Distance:", rangeFeet);
    }

    protected double convertDistanceFromInchesToFeet(double rangeInches) {
        return rangeInches / INCHES_PER_FOOT;
    }

    public void enable() {
        ultrasonicSensor.setEnabled(true);
    }

    public void disable() {
        ultrasonicSensor.setEnabled(false);
    }
}
