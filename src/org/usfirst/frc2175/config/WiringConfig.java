package org.usfirst.frc2175.config;

import java.util.Properties;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;

/**
 * Wiring configuration values - robot sensors and actuators port numbers.
 *
 * Set the values in the wiring properties file.
 *
 * Do not use port numbers in code, use these configuration methods.
 */
public class WiringConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "wiring.properties";

    private Talon leftDriveTalon;
    private Talon rightDriveTalon;

    private DoubleSolenoid leftCatapultSolenoid;
    private DoubleSolenoid rightCatapultSolenoid;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        // TODO implement me
        // - add private instance variables for the values
        // - add get() methods for them

        int leftDriveTalonPort = getIntPropertyValue("drivetrain.talon.left.port", properties);
        leftDriveTalon = new Talon(leftDriveTalonPort);

        int rightDriveTalonPort = getIntPropertyValue("drivetrain.talon.right.port", properties);
        rightDriveTalon = new Talon(rightDriveTalonPort);

        int leftCatapultSolenoidForwardPort = getIntPropertyValue("catapult.solenoid.left.forward", properties);
        int leftCatapultSolenoidReversePort = getIntPropertyValue("catapult.solenoid.left.reverse", properties);
        leftCatapultSolenoid = new DoubleSolenoid(leftCatapultSolenoidForwardPort, leftCatapultSolenoidReversePort);

        int rightCatapultSolenoidForwardPort = getIntPropertyValue("catapult.solenoid.right.forward", properties);
        int rightCatapultSolenoidReversePort = getIntPropertyValue("catapult.solenoid.right.reverse", properties);
        rightCatapultSolenoid = new DoubleSolenoid(rightCatapultSolenoidForwardPort, rightCatapultSolenoidReversePort);

    }

    public Talon getLeftDriveTalon() {
        return leftDriveTalon;
    }

    public Talon getRightDriveTalon() {
        return rightDriveTalon;
    }

    public DoubleSolenoid getLeftCatapultSolenoid() {
        return leftCatapultSolenoid;
    }

    public DoubleSolenoid getRightCatapultSolenoid() {
        return rightCatapultSolenoid;
    }
}
