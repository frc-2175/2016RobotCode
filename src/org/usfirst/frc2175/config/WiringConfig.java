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
    private CANTalon rollerbarIntakeLiftTalon;
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
    private double bootSpeed;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        configureDrivetrain(properties);
        configureCatapult(properties);
        configureManipulator(properties);
        configureDreamIntake(properties);
        configureRollerbarIntake(properties);
    }

    private void configureDrivetrain(Properties properties) {
        int gyroPort = getIntPropertyValue("drivetrain.gyro.port", properties);
        gyro = new AnalogGyro(gyroPort);

        boolean isLeftDriveEncoderReversed =
                getBooleanPropertyValue("drivetrain.encoder.left.isReversed",
                        properties);
        int leftDriveEncoderPortA =
                getIntPropertyValue("drivetrain.encoder.left.port.a",
                        properties);
        int leftDriveEncoderPortB =
                getIntPropertyValue("drivetrain.encoder.left.port.b",
                        properties);
        leftDriveEncoder =
                new Encoder(leftDriveEncoderPortA, leftDriveEncoderPortB,
                        isLeftDriveEncoderReversed);

        boolean isRightDriveEncoderReversed =
                getBooleanPropertyValue("drivetrain.encoder.right.isReversed",
                        properties);
        int rightDriveEncoderPortA =
                getIntPropertyValue("drivetrain.encoder.right.port.a",
                        properties);
        int rightDriveEncoderPortB =
                getIntPropertyValue("drivetrain.encoder.right.port.b",
                        properties);
        rightDriveEncoder =
                new Encoder(rightDriveEncoderPortA, rightDriveEncoderPortB,
                        isRightDriveEncoderReversed);

        int driveShiftersSolenoidPortA =
                getIntPropertyValue("drivetrain.solenoid.driveShifters.port.a",
                        properties);
        int driveShiftersSolenoidPortB =
                getIntPropertyValue("drivetrain.solenoid.driveShifters.port.b",
                        properties);
        driveShifters =
                new DoubleSolenoid(driveShiftersSolenoidPortA,
                        driveShiftersSolenoidPortB);
    }

    private void configureCatapult(Properties properties) {
        int leftCatapultSolenoidPort =
                getIntPropertyValue("catapult.solenoid.left", properties);

        leftCatapultSolenoid = new Solenoid(leftCatapultSolenoidPort);

        int rightCatapultSolenoidPort =
                getIntPropertyValue("catapult.solenoid.right", properties);
        rightCatapultSolenoid = new Solenoid(rightCatapultSolenoidPort);

        int catapultUpSwitchPort =
                getIntPropertyValue("catapult.switch.up.port", properties);
        catapultUpSwitch = new DigitalInput(catapultUpSwitchPort);

        int catapultDownSwitchPort =
                getIntPropertyValue("catapult.switch.down.port", properties);
        catapultDownSwitch = new DigitalInput(catapultDownSwitchPort);
    }

    private void configureDreamIntake(Properties properties) {
        int dreamIntakeSideBeltTalonPort =
                getIntPropertyValue("dreamIntake.talon.sideBelt.port",
                        properties);
        dreamIntakeSideBeltTalon = new Talon(dreamIntakeSideBeltTalonPort);

        int dreamIntakeMainBeltTalonPort =
                getIntPropertyValue("dreamIntake.talon.mainBelt.port",
                        properties);
        dreamIntakeMainBeltTalon = new Talon(dreamIntakeMainBeltTalonPort);

        int dreamIntakeLiftTalonPort =
                getIntPropertyValue("dreamIntake.talon.lift.port", properties);
        dreamIntakeLiftTalon = new Talon(dreamIntakeLiftTalonPort);

        int dreamIntakeLiftEncoderA =
                getIntPropertyValue("dreamIntake.encoder.port.a", properties);
        int dreamIntakeLiftEncoderB =
                getIntPropertyValue("dreamIntake.encoder.port.b", properties);
        boolean isDreamIntakeLiftEncoderReversed =
                getBooleanPropertyValue("dreamIntake.encoder.isReversed",
                        properties);
        dreamIntakeLiftEncoder =
                new Encoder(dreamIntakeLiftEncoderA, dreamIntakeLiftEncoderB,
                        isDreamIntakeLiftEncoderReversed);

        int dreamIntakeUpSwitchPort =
                getIntPropertyValue("dreamIntake.switch.up.port", properties);
        dreamIntakeUpSwitch = new DigitalInput(dreamIntakeUpSwitchPort);

        int dreamIntakeDownSwitchPort =
                getIntPropertyValue("dreamIntake.switch.down.port", properties);
        dreamIntakeDownSwitch = new DigitalInput(dreamIntakeDownSwitchPort);
    }

    private void configureRollerbarIntake(Properties properties) {
        int rollerbarIntakeRollerTalonPort =
                getIntPropertyValue("rollerbarIntake.talon.roller.port",
                        properties);
        rollerbarIntakeRollerTalon =
                new CANTalon(rollerbarIntakeRollerTalonPort);

        int rollerbarIntakeLiftTalonPort =
                getIntPropertyValue("rollerbarIntake.talon.lift.port",
                        properties);
        rollerbarIntakeRollerTalon = new CANTalon(rollerbarIntakeLiftTalonPort);

        int rollerbarIntakeTalonPort =
                getIntPropertyValue("rollerbarIntake.talon.port", properties);
        rollerbarIntakeRollerTalon = new CANTalon(rollerbarIntakeTalonPort);

        int rollerbarIntakeSolenoidForwardPort =
                getIntPropertyValue("rollerbarIntake.solenoid.forward",
                        properties);
        int rollerbarIntakeSolenoidReversePort =
                getIntPropertyValue("rollerbarIntake.solenoid.reverse",
                        properties);
        rollerbarIntakeSolenoid =
                new DoubleSolenoid(rollerbarIntakeSolenoidForwardPort,
                        rollerbarIntakeSolenoidReversePort);

        int rollerbarIntakeInSwitchPort =
                getIntPropertyValue("rollerbarIntake.switch.in.port",
                        properties);
        rollerbarIntakeInSwitch = new DigitalInput(rollerbarIntakeInSwitchPort);

        int rollerbarIntakeOutSwitchPort =
                getIntPropertyValue("rollerbarIntake.switch.out.port",
                        properties);
        rollerbarIntakeOutSwitch =
                new DigitalInput(rollerbarIntakeOutSwitchPort);
    }

    private void configureManipulator(Properties properties) {
        int bootTalonPort =
                getIntPropertyValue("manipulator.talon.boot.port", properties);
        bootTalon = new CANTalon(bootTalonPort);

        double bootSpeed =
                getDoublePropertyValue("manipulator.boot.speed", properties);
    }

    public double getBootSpeed() {
        return bootSpeed;
    }

    public CANTalon getRollerbarIntakeLiftTalon() {
        return rollerbarIntakeLiftTalon;
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