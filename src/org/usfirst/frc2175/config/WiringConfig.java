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

    // Drivetrain
    private Talon leftDriveTalon;
    private Talon rightDriveTalon;

    // Dream intake
    private Talon dreamIntakeSideBeltTalon;
    private Talon dreamIntakeMainBeltTalon;
    private Talon dreamIntakeLiftTalon;

    // Catapult shooter
    private DoubleSolenoid leftCatapultSolenoid;
    private DoubleSolenoid rightCatapultSolenoid;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        // Drivetrain
        int leftDriveTalonPort =
                getIntPropertyValue("drivetrain.talon.left.port", properties);
        leftDriveTalon = new Talon(leftDriveTalonPort);

        int rightDriveTalonPort =
                getIntPropertyValue("drivetrain.talon.right.port", properties);
        rightDriveTalon = new Talon(rightDriveTalonPort);

        // Catapult
        int leftCatapultSolenoidForwardPort = getIntPropertyValue(
                "catapult.solenoid.left.forward", properties);
        int leftCatapultSolenoidReversePort = getIntPropertyValue(
                "catapult.solenoid.left.reverse", properties);
        leftCatapultSolenoid =
                new DoubleSolenoid(leftCatapultSolenoidForwardPort,
                        leftCatapultSolenoidReversePort);

        int rightCatapultSolenoidForwardPort = getIntPropertyValue(
                "catapult.solenoid.right.forward", properties);
        int rightCatapultSolenoidReversePort = getIntPropertyValue(
                "catapult.solenoid.right.reverse", properties);
        rightCatapultSolenoid =
                new DoubleSolenoid(rightCatapultSolenoidForwardPort,
                        rightCatapultSolenoidReversePort);

        // Dream Intake
        int dreamIntakeSideBeltTalonPort =
                getIntPropertyValue("dreamIntake.talon.sideBelt", properties);
        dreamIntakeSideBeltTalon = new Talon(dreamIntakeSideBeltTalonPort);

        int dreamIntakeMainBeltTalonPort =
                getIntPropertyValue("dreamIntake.talon.mainBelt", properties);
        dreamIntakeMainBeltTalon = new Talon(dreamIntakeMainBeltTalonPort);

        int dreamIntakeLiftTalonPort =
                getIntPropertyValue("dreamIntake.talon.lift", properties);
        dreamIntakeLiftTalon = new Talon(dreamIntakeLiftTalonPort);
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

    public Talon getDreamIntakeSideBeltTalon() {
        return dreamIntakeSideBeltTalon;
    }

    public Talon getDreamIntakeMainBeltTalon() {
        return dreamIntakeMainBeltTalon;
    }

    public Talon getDreamIntakeLiftTalon() {
        return dreamIntakeLiftTalon;
    }
}
