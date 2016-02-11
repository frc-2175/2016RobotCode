package org.usfirst.frc2175.config;

import java.util.Properties;

import org.usfirst.frc2175.util.TalonGroup;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
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
    private TalonGroup leftDriveTalonGroup;
    private TalonGroup rightDriveTalonGroup;

    private Encoder leftDriveEncoder;
    private Encoder rightDriveEncoder;
    private Gyro gyro;
    private DoubleSolenoid driveShifters;

    // Dream intake
    private Talon dreamIntakeSideBeltTalon;
    private Talon dreamIntakeMainBeltTalon;
    private Talon dreamIntakeLiftTalon;
    private Encoder dreamIntakeLiftEncoder;

    // Rollerbar intake
    private CANTalon rollerbarIntakeRollerTalon;
    private DoubleSolenoid rollerbarIntakeSolenoid;
    private DigitalInput rollerbarIntakeInSwitch;
    private DigitalInput rollerbarIntakeOutSwitch;

    // Catapult shooter
    private Solenoid leftCatapultSolenoid;
    private Solenoid rightCatapultSolenoid;
    private DigitalInput catapultUpSwitch;
    private DigitalInput catapultDownSwitch;
    private DigitalInput dreamIntakeUpSwitch;
    private DigitalInput dreamIntakeDownSwitch;

    // Manipulator
    private CANTalon bootTalon;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        // Drivetrain
        int leftDriveTalon1Port =
                getIntPropertyValue("drivetrain.talon.left.1.port", properties);
        CANTalon leftDriveTalon1 = new CANTalon(leftDriveTalon1Port);

        int leftDriveTalon2Port =
                getIntPropertyValue("drivetrain.talon.left.2.port", properties);
        CANTalon leftDriveTalon2 = new CANTalon(leftDriveTalon2Port);

        int leftDriveTalon3Port =
                getIntPropertyValue("drivetrain.talon.left.3.port", properties);
        CANTalon leftDriveTalon3 = new CANTalon(leftDriveTalon3Port);

        leftDriveTalonGroup = new TalonGroup(leftDriveTalon1, leftDriveTalon2,
                leftDriveTalon3);

        int rightDriveTalon1Port = getIntPropertyValue(
                "drivetrain.talon.right.1.port", properties);
        CANTalon rightDriveTalon1 = new CANTalon(rightDriveTalon1Port);

        int rightDriveTalon2Port = getIntPropertyValue(
                "drivetrain.talon.right.2.port", properties);
        CANTalon rightDriveTalon2 = new CANTalon(rightDriveTalon2Port);

        int rightDriveTalon3Port = getIntPropertyValue(
                "drivetrain.talon.right.3.port", properties);
        CANTalon rightDriveTalon3 = new CANTalon(rightDriveTalon3Port);

        rightDriveTalonGroup = new TalonGroup(rightDriveTalon1,
                rightDriveTalon2, rightDriveTalon3);

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

        int driveShiftersSolenoidPortA = getIntPropertyValue(
                "drivetrain.solenoid.driveShifters.port.a", properties);
        int driveShiftersSolenoidPortB = getIntPropertyValue(
                "drivetrain.solenoid.driveShifters.port.b", properties);
        driveShifters = new DoubleSolenoid(driveShiftersSolenoidPortA,
                driveShiftersSolenoidPortB);

        // Catapult
        int leftCatapultSolenoidForwardPort = getIntPropertyValue(
                "catapult.solenoid.left.forward", properties);

        leftCatapultSolenoid = new Solenoid(leftCatapultSolenoidForwardPort);

        int rightCatapultSolenoidForwardPort = getIntPropertyValue(
                "catapult.solenoid.right.forward", properties);
        rightCatapultSolenoid = new Solenoid(rightCatapultSolenoidForwardPort);

        int catapultUpSwitchPort =
                getIntPropertyValue("catapult.switch.up.port", properties);
        catapultUpSwitch = new DigitalInput(catapultUpSwitchPort);
        int catapultDownSwitchPort =
                getIntPropertyValue("catapult.switch.down.port", properties);
        catapultDownSwitch = new DigitalInput(catapultDownSwitchPort);

        // Dream Intake
        int dreamIntakeSideBeltTalonPort = getIntPropertyValue(
                "dreamIntake.talon.sideBelt.port", properties);
        dreamIntakeSideBeltTalon = new Talon(dreamIntakeSideBeltTalonPort);

        int dreamIntakeMainBeltTalonPort = getIntPropertyValue(
                "dreamIntake.talon.mainBelt.port", properties);
        dreamIntakeMainBeltTalon = new Talon(dreamIntakeMainBeltTalonPort);

        int dreamIntakeLiftTalonPort =
                getIntPropertyValue("dreamIntake.talon.lift.port", properties);
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
                getIntPropertyValue("rollerbarIntake.talon.port", properties);
        rollerbarIntakeRollerTalon = new CANTalon(rollerbarIntakeTalonPort);
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

        // Manipulator arms
        int bootTalonPort =
                getIntPropertyValue("manipulator.talon.boot.port", properties);
        bootTalon = new CANTalon(bootTalonPort);
    }

    public DoubleSolenoid getDriveShifters() {
        return driveShifters;
    }

    public TalonGroup getLeftDriveTalonGroup() {
        return leftDriveTalonGroup;
    }

    public TalonGroup getRightDriveTalonGroup() {
        return rightDriveTalonGroup;
    }

    public CANTalon getRollerbarIntakeRollerTalon() {
        return rollerbarIntakeRollerTalon;
    }

    public CANTalon getBootTalon() {
        return bootTalon;
    }

    public CANTalon getRollerbarIntakeTalon() {
        return rollerbarIntakeRollerTalon;
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

    public Encoder getDreamIntakeLiftEncoder() {
        return dreamIntakeLiftEncoder;
    }

    public TalonGroup getLeftDriveTalonHandler() {
        return leftDriveTalonGroup;
    }

    public TalonGroup getRightDriveTalonHandler() {
        return rightDriveTalonGroup;
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

    public Solenoid getLeftCatapultSolenoid() {
        return leftCatapultSolenoid;
    }

    public Solenoid getRightCatapultSolenoid() {
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