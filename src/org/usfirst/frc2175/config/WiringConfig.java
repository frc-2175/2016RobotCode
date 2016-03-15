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
import edu.wpi.first.wpilibj.Ultrasonic;
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

    // powertrain
    private TalonGroup leftDriveTalonGroup;
    private TalonGroup rightDriveTalonGroup;
    private Encoder leftDriveEncoder;
    private Encoder rightDriveEncoder;
    private Gyro gyro;
    private Ultrasonic ultrasonicSensor;

    // Shifters
    private Solenoid[] shifterSolenoids;

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
    private DigitalInput isBootUpSwitch;
    private DigitalInput isBootDownSwitch;
    private Encoder bootEncoder;
    private double bootSpeed;

    // Climber
    private Solenoid climberSolenoid;
    private DigitalInput climberUpSwitch;
    private DigitalInput climberExtendedSwitch;

    // cameras
    private String camera1IP;
    private String camera2IP;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        configurePowertrain(properties);
        configureCatapult(properties);
        configureManipulator(properties);
        configureDreamIntake(properties);
        configureRollerbarIntake(properties);
        configureShifters(properties);
        configureClimber(properties);
        configureCameras(properties);
    }

    private void configureShifters(Properties properties) {
        int[] solenoidPorts = getIntArrayPropertyValue(
                "shifter.solenoid.portArray", properties);
        shifterSolenoids = new Solenoid[solenoidPorts.length];

        for (int i = 0; i < solenoidPorts.length; i++) {
            Solenoid solenoid = new Solenoid(solenoidPorts[i]);
            shifterSolenoids[i] = solenoid;
        }
    }

    private void configurePowertrain(Properties properties) {
        int gyroPort =
                getIntPropertyValue("powertrain.analog.gyro.port", properties);
        gyro = new AnalogGyro(gyroPort);

        boolean isLeftDriveEncoderReversed = getBooleanPropertyValue(
                "powertrain.digital.encoder.left.isReversed", properties);
        int leftDriveEncoderPortA = getIntPropertyValue(
                "powertrain.digital.encoder.left.port.a", properties);
        int leftDriveEncoderPortB = getIntPropertyValue(
                "powertrain.digital.encoder.left.port.b", properties);
        leftDriveEncoder = new Encoder(leftDriveEncoderPortA,
                leftDriveEncoderPortB, isLeftDriveEncoderReversed);

        boolean isRightDriveEncoderReversed = getBooleanPropertyValue(
                "powertrain.digital.encoder.right.isReversed", properties);
        int rightDriveEncoderPortA = getIntPropertyValue(
                "powertrain.digital.encoder.right.port.a", properties);
        int rightDriveEncoderPortB = getIntPropertyValue(
                "powertrain.digital.encoder.right.port.b", properties);
        rightDriveEncoder = new Encoder(rightDriveEncoderPortA,
                rightDriveEncoderPortB, isRightDriveEncoderReversed);

        int leftDriveTalon1Port =
                getIntPropertyValue("powertrain.talon.left.1.port", properties);
        CANTalon leftDriveTalon1 = new CANTalon(leftDriveTalon1Port);

        int leftDriveTalon2Port =
                getIntPropertyValue("powertrain.talon.left.2.port", properties);
        CANTalon leftDriveTalon2 = new CANTalon(leftDriveTalon2Port);

        int leftDriveTalon3Port =
                getIntPropertyValue("powertrain.talon.left.3.port", properties);
        CANTalon leftDriveTalon3 = new CANTalon(leftDriveTalon3Port);

        leftDriveTalonGroup = new TalonGroup(leftDriveTalon1, leftDriveTalon2,
                leftDriveTalon3);

        int rightDriveTalon1Port = getIntPropertyValue(
                "powertrain.talon.right.1.port", properties);
        CANTalon rightDriveTalon1 = new CANTalon(rightDriveTalon1Port);

        int rightDriveTalon2Port = getIntPropertyValue(
                "powertrain.talon.right.2.port", properties);
        CANTalon rightDriveTalon2 = new CANTalon(rightDriveTalon2Port);

        int rightDriveTalon3Port = getIntPropertyValue(
                "powertrain.talon.right.3.port", properties);
        CANTalon rightDriveTalon3 = new CANTalon(rightDriveTalon3Port);

        rightDriveTalonGroup = new TalonGroup(rightDriveTalon1,
                rightDriveTalon2, rightDriveTalon3);

        int ultrasonicSensorPingPort = getIntPropertyValue(
                "powertrain.digital.ultrasonic.port.ping", properties);
        int ultrasonicSensorEchoPort = getIntPropertyValue(
                "powertrain.digital.ultrasonic.port.echo", properties);
        ultrasonicSensor = new Ultrasonic(ultrasonicSensorPingPort,
                ultrasonicSensorEchoPort);
    }

    private void configureCatapult(Properties properties) {
        int leftCatapultSolenoidPort =
                getIntPropertyValue("catapult.solenoid.left", properties);

        leftCatapultSolenoid = new Solenoid(leftCatapultSolenoidPort);

        int rightCatapultSolenoidPort =
                getIntPropertyValue("catapult.solenoid.right", properties);
        rightCatapultSolenoid = new Solenoid(rightCatapultSolenoidPort);

        int catapultUpSwitchPort = getIntPropertyValue(
                "catapult.digital.switch.up.port", properties);
        catapultUpSwitch = new DigitalInput(catapultUpSwitchPort);

        int catapultDownSwitchPort = getIntPropertyValue(
                "catapult.digital.switch.down.port", properties);
        catapultDownSwitch = new DigitalInput(catapultDownSwitchPort);
    }

    private void configureDreamIntake(Properties properties) {
        int dreamIntakeSideBeltTalonPort = getIntPropertyValue(
                "dreamIntake.talon.sideBelt.port", properties);
        dreamIntakeSideBeltTalon = new Talon(dreamIntakeSideBeltTalonPort);

        int dreamIntakeMainBeltTalonPort = getIntPropertyValue(
                "dreamIntake.talon.mainBelt.port", properties);
        dreamIntakeMainBeltTalon = new Talon(dreamIntakeMainBeltTalonPort);

        int dreamIntakeLiftTalonPort =
                getIntPropertyValue("dreamIntake.talon.lift.port", properties);
        dreamIntakeLiftTalon = new Talon(dreamIntakeLiftTalonPort);

        int dreamIntakeLiftEncoderA = getIntPropertyValue(
                "dreamIntake.digital.encoder.port.a", properties);
        int dreamIntakeLiftEncoderB = getIntPropertyValue(
                "dreamIntake.digital.encoder.port.b", properties);
        boolean isDreamIntakeLiftEncoderReversed = getBooleanPropertyValue(
                "dreamIntake.digital.encoder.isReversed", properties);
        dreamIntakeLiftEncoder = new Encoder(dreamIntakeLiftEncoderA,
                dreamIntakeLiftEncoderB, isDreamIntakeLiftEncoderReversed);

        int dreamIntakeUpSwitchPort = getIntPropertyValue(
                "dreamIntake.digital.switch.up.port", properties);
        dreamIntakeUpSwitch = new DigitalInput(dreamIntakeUpSwitchPort);

        int dreamIntakeDownSwitchPort = getIntPropertyValue(
                "dreamIntake.digital.switch.down.port", properties);
        dreamIntakeDownSwitch = new DigitalInput(dreamIntakeDownSwitchPort);
    }

    private void configureRollerbarIntake(Properties properties) {
        int rollerbarIntakeRollerTalonPort = getIntPropertyValue(
                "rollerbarIntake.talon.roller.port", properties);
        rollerbarIntakeRollerTalon =
                new CANTalon(rollerbarIntakeRollerTalonPort);

        int rollerbarIntakeLiftTalonPort = getIntPropertyValue(
                "rollerbarIntake.talon.lift.port", properties);
        rollerbarIntakeLiftTalon = new CANTalon(rollerbarIntakeLiftTalonPort);

        int rollerbarIntakeSolenoidForwardPort = getIntPropertyValue(
                "rollerbarIntake.solenoid.forward", properties);
        int rollerbarIntakeSolenoidReversePort = getIntPropertyValue(
                "rollerbarIntake.solenoid.reverse", properties);
        rollerbarIntakeSolenoid =
                new DoubleSolenoid(rollerbarIntakeSolenoidForwardPort,
                        rollerbarIntakeSolenoidReversePort);

        int rollerbarIntakeInSwitchPort = getIntPropertyValue(
                "rollerbarIntake.digital.switch.in.port", properties);
        rollerbarIntakeInSwitch = new DigitalInput(rollerbarIntakeInSwitchPort);

        int rollerbarIntakeOutSwitchPort = getIntPropertyValue(
                "rollerbarIntake.digital.switch.out.port", properties);
        rollerbarIntakeOutSwitch =
                new DigitalInput(rollerbarIntakeOutSwitchPort);
    }

    private void configureManipulator(Properties properties) {
        int bootTalonPort =
                getIntPropertyValue("manipulator.talon.boot.port", properties);
        bootTalon = new CANTalon(bootTalonPort);

        int isBootUpSwitchPort = getIntPropertyValue(
                "manipulator.digital.switch.boot.up.port", properties);
        isBootUpSwitch = new DigitalInput(isBootUpSwitchPort);

        int isBootDownSwitchPort = getIntPropertyValue(
                "manipulator.digital.switch.boot.down.port", properties);
        isBootDownSwitch = new DigitalInput(isBootDownSwitchPort);

        int bootEncoderA = getIntPropertyValue(
                "manipulator.digital.encoder.port.a", properties);
        int bootEncoderB = getIntPropertyValue(
                "manipulator.digital.encoder.port.b", properties);
        boolean isBootEncoderReversed = getBooleanPropertyValue(
                "manipulator.digital.encoder.isReversed", properties);
        bootEncoder =

                new Encoder(bootEncoderA, bootEncoderB, isBootEncoderReversed);
    }

    private void configureClimber(Properties properties) {
        int climberSolenoidPort =
                getIntPropertyValue("climber.solenoid", properties);
        climberSolenoid = new Solenoid(climberSolenoidPort);

        int climberUpSwitchPort =
                getIntPropertyValue("climber.digital.switch.up", properties);
        climberUpSwitch = new DigitalInput(climberUpSwitchPort);
        int climberExtendedSwitchPort = getIntPropertyValue(
                "climber.digital.switch.extended", properties);
        climberExtendedSwitch = new DigitalInput(climberExtendedSwitchPort);
    }

    private void configureCameras(Properties properties) {
        camera1IP = getStringPropertyValue("camera.axis1.IP", properties);
        camera2IP = getStringPropertyValue("camera.axis2.IP", properties);
    }

    public CANTalon getRollerbarIntakeLiftTalon() {
        return rollerbarIntakeLiftTalon;
    }

    public TalonGroup getLeftDriveTalonGroup() {
        return leftDriveTalonGroup;
    }

    public DigitalInput getIsBootUpSwitch() {
        return isBootUpSwitch;
    }

    public DigitalInput getIsBootDownSwitch() {
        return isBootDownSwitch;
    }

    public Encoder getBootEncoder() {
        return bootEncoder;
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

    public Solenoid[] getShifterSolenoids() {
        return shifterSolenoids;
    }

    public Solenoid getClimberSolenoid() {
        return climberSolenoid;
    }

    public DigitalInput getClimberUpSwitch() {
        return climberUpSwitch;
    }

    public DigitalInput getClimberExtendedSwitch() {
        return climberExtendedSwitch;
    }

    public String getCamera1IP() {
        return camera1IP;
    }

    public String getCamera2IP() {
        return camera2IP;
    }

    public Ultrasonic getUltrasonicSensor() {
        return ultrasonicSensor;
    }

}