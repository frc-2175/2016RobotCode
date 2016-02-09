package org.usfirst.frc2175.config;

import java.util.Properties;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Gyro;

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
    private Encoder leftDriveEncoder;
    private Encoder rightDriveEncoder;
    private Gyro gyro;

    // Dream intake
    private Talon dreamIntakeSideBeltTalon;
    private Talon dreamIntakeMainBeltTalon;
    private Talon dreamIntakeLiftTalon;
    private Encoder dreamIntakeLiftEncoder;

    // Rollerbar intake
    private Talon rollerbarIntakeTalon;
    private DoubleSolenoid rollerbarIntakeSolenoid;
    private DigitalInput rollerbarIntakeInSwitch;
    private DigitalInput rollerbarIntakeOutSwitch;

    // Catapult shooter
    private DoubleSolenoid leftCatapultSolenoid;
    private DoubleSolenoid rightCatapultSolenoid;
    private DigitalInput catapultUpSwitch;
    private DigitalInput catapultDownSwitch;
    private DigitalInput dreamIntakeUpSwitch;
    private DigitalInput dreamIntakeDownSwitch;

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

        int gyroPort = getIntPropertyValue("drivetrain.gyro.port", properties);
        gyro = new AnalogGyro(gyroPort);

        boolean isLeftDriveEncoderReversed = getBooleanPropertyValue(
                "drivetrain.encoder.left.isReversed", properties);
        int leftDriveEncoderPortA = getIntPropertyValue(
                "drivetrain.encoder.left.port.a", properties);
        int leftDriveEncoderPortB = getIntPropertyValue(
                "drivetrain.encoder.left.port.b", properties);
        leftDriveEncoder = new Encoder(leftDriveEncoderPortA,
                leftDriveEncoderPortB, isLeftDriveEncoderReversed);

        boolean isRightDriveEncoderReversed = getBooleanPropertyValue(
                "drivetrain.encoder.right.isReversed", properties);
        int rightDriveEncoderPortA = getIntPropertyValue(
                "drivetrain.encoder.right.port.a", properties);
        int rightDriveEncoderPortB = getIntPropertyValue(
                "drivetrain.encoder.right.port.b", properties);
        rightDriveEncoder = new Encoder(rightDriveEncoderPortA,
                rightDriveEncoderPortB, isRightDriveEncoderReversed);

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

        int catapultUpSwitchPort =
                getIntPropertyValue("catapult.switch.up.port", properties);
        catapultUpSwitch = new DigitalInput(catapultUpSwitchPort);
        int catapultDownSwitchPort =
                getIntPropertyValue("catapult.switch.down.port", properties);
        catapultDownSwitch = new DigitalInput(catapultDownSwitchPort);

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

        int dreamIntakeLiftEncoderA =
                getIntPropertyValue("dreamIntake.encoder.port.a", properties);
        int dreamIntakeLiftEncoderB =
                getIntPropertyValue("dreamIntake.encoder.port.b", properties);
        boolean isDreamIntakeLiftEncoderReversed = getBooleanPropertyValue(
                "dreamIntake.encoder.isReversed", properties);
        dreamIntakeLiftEncoder = new Encoder(dreamIntakeLiftEncoderA,
                dreamIntakeLiftEncoderB, isDreamIntakeLiftEncoderReversed);

        int dreamIntakeUpSwitchPort =
                getIntPropertyValue("dreamIntake.switch.up.port", properties);
        dreamIntakeUpSwitch = new DigitalInput(dreamIntakeUpSwitchPort);
        int dreamIntakeDownSwitchPort =
                getIntPropertyValue("dreamIntake.switch.down.port", properties);
        dreamIntakeDownSwitch = new DigitalInput(dreamIntakeDownSwitchPort);

        // Rollerbar intake
        int rollerbarIntakeTalonPort =
                getIntPropertyValue("rollerbarIntake.talon", properties);
        rollerbarIntakeTalon = new Talon(rollerbarIntakeTalonPort);
        int rollerbarIntakeSolenoidForwardPort = getIntPropertyValue(
                "rollerbarIntake.solenoid.forward", properties);
        int rollerbarIntakeSolenoidReversePort = getIntPropertyValue(
                "rollerbarIntake.solenoid.reverse", properties);
        rollerbarIntakeSolenoid =
                new DoubleSolenoid(rollerbarIntakeSolenoidForwardPort,
                        rollerbarIntakeSolenoidReversePort);
        int rollerbarIntakeInSwitchPort = getIntPropertyValue(
                "rollerbarIntake.switch.in.port", properties);
        rollerbarIntakeInSwitch = new DigitalInput(rollerbarIntakeInSwitchPort);
        int rollerbarIntakeOutSwitchPort = getIntPropertyValue(
                "rollerbarIntake.switch.out.port", properties);
        rollerbarIntakeOutSwitch =
                new DigitalInput(rollerbarIntakeOutSwitchPort);
    }

    public Talon getRollerbarIntakeTalon() {
        return rollerbarIntakeTalon;
    }

    public DigitalInput getRollerbarIntakeInSwitch() {
        return rollerbarIntakeInSwitch;
    }

    public DigitalInput getRollerbarIntakeOutSwitch() {
        return rollerbarIntakeOutSwitch;
    }

    public DoubleSolenoid getRollerbarIntakeSolenoid() {
        return rollerbarIntakeSolenoid;
    }

    public DigitalInput getCatapultUpSwitch() {
        return catapultUpSwitch;
    }

    public DigitalInput getCatapultDownSwitch() {
        return catapultDownSwitch;
    }

    public DigitalInput getDreamIntakeUpSwitch() {
        return dreamIntakeUpSwitch;
    }

    public DigitalInput getDreamIntakeDownSwitch() {
        return dreamIntakeDownSwitch;
    }

    public Talon getLeftDriveTalon() {
        return leftDriveTalon;
    }

    public Talon getRightDriveTalon() {
        return rightDriveTalon;
    }

    public Encoder getLeftDriveEncoder() {
        return leftDriveEncoder;
    }

    public Encoder getRightDriveEncoder() {
        return rightDriveEncoder;
    }

    public Gyro getGyro() {
        return gyro;
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