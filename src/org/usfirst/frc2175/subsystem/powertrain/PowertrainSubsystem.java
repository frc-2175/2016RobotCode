package org.usfirst.frc2175.subsystem.powertrain;

import org.usfirst.frc2175.config.PowertrainConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;
import org.usfirst.frc2175.util.SpeedControllerGroup;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;

public class PowertrainSubsystem extends BaseSubsystem {
    private PowertrainConfig powertrainConfig;

    private SpeedControllerGroup leftDriveSideTalonGroup;
    private SpeedControllerGroup rightDriveSideTalonGroup;
    private Encoder leftDriveEncoder;
    private Encoder rightDriveEncoder;
    private Solenoid[] driveShifters;

    private AHRS fancyGyro;
    private double gyroOffset;

    private ShifterState shifterState;

    private RobotDrive robotDrive;

    public PowertrainSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        powertrainConfig = robotConfig.getPowertrainConfig();

        leftDriveSideTalonGroup = wiringConfig.getLeftDriveTalonGroup();
        rightDriveSideTalonGroup = wiringConfig.getRightDriveTalonGroup();

        leftDriveEncoder = wiringConfig.getLeftDriveEncoder();
        rightDriveEncoder = wiringConfig.getRightDriveEncoder();
        fancyGyro = wiringConfig.getFancyGyro();
        gyroOffset = 0;
        driveShifters = wiringConfig.getShifterSolenoids();

        robotDrive = new RobotDrive(leftDriveSideTalonGroup,
                rightDriveSideTalonGroup);

        setShifterState(ShifterState.LOW);
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        // If the shifters are in a state where we can drive, drive. Otherwise,
        // do nothing
        if (isDriveEngaged()) {
            robotDrive.arcadeDrive(-moveSpeed, rotateSpeed);
        }
        if (isClimberEngaged()) {
            // TODO check direction
            winchWithPTO(moveSpeed);
        }
    }

    public void driveAtZeroHeading(double speed) {
        // TODO make this a property
        double kP = 0.1;
        arcadeDrive(speed, 0 - kP * getGyroAngle());
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        // If the shifters are in a state where we can drive, drive. Otherwise,
        // do nothing
        if (isDriveEngaged()) {
            // TODO get direction from a property file
            robotDrive.tankDrive(-leftSpeed, -rightSpeed);
        }
    }

    public void winchWithPTO(double speed) {
        // If shifted to climb, set motors, else do nothing
        if (isClimberEngaged()) {
            leftDriveSideTalonGroup.set(speed);
            rightDriveSideTalonGroup.set(speed);
        }
    }

    private void applyShifterState(int[] state) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                driveShifters[i].set(false);
            } else {
                driveShifters[i].set(true);
            }
        }
    }

    private void setShifterState(ShifterState state) {

        int[] pwmValues = {};

        switch (state) {
        case LOW:
            pwmValues = powertrainConfig.getStateLow();
            break;
        case HIGH:
            pwmValues = powertrainConfig.getStateHigh();
            break;
        case CLIMB_NEUTRAL:
            pwmValues = powertrainConfig.getStateClimb_Neutral();
            break;
        case CLIMB_LOW:
            pwmValues = powertrainConfig.getStateClimb_Low();
            break;
        }
        shifterState = state;
        applyShifterState(pwmValues);

    }

    public ShifterState getShifterState() {
        return shifterState;
    }

    public void resetEncoders() {
        leftDriveEncoder.reset();
        rightDriveEncoder.reset();
    }

    public double getLeftEncoderDistance() {
        double leftEncoderDistance = leftDriveEncoder.getDistance();
        return leftEncoderDistance;
    }

    public double getRightEncoderDistance() {
        double rightEncoderDistance = rightDriveEncoder.getDistance();
        return rightEncoderDistance;
    }

    public double getMeanEncoderDistance() {
        return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2;
    }

    public void shiftToHighGear() {
        setShifterState(ShifterState.HIGH);
    }

    public void shiftToLowGear() {
        setShifterState(ShifterState.LOW);
    }

    public void shiftToClimb_Neutral() {
        setShifterState(ShifterState.CLIMB_NEUTRAL);
    }

    public void shiftToClimb_Low() {
        setShifterState(ShifterState.CLIMB_LOW);
    }

    public boolean isDriveEngaged() {
        return shifterState == ShifterState.HIGH
                || shifterState == ShifterState.LOW;
    }

    public boolean isClimberEngaged() {
        return shifterState == ShifterState.CLIMB_NEUTRAL
                || shifterState == ShifterState.CLIMB_LOW;
    }

    /**
     * Resets the gyro angle to zero. This should only be run at the beginning
     * of auton.
     */
    public void resetGyro() {
        gyroOffset = -fancyGyro.getAngle();
    }

    public double getGyroAngle() {
        return fancyGyro.getAngle() + gyroOffset;
    }

    private enum ShifterState {
        LOW, HIGH, CLIMB_NEUTRAL, CLIMB_LOW;
    }

}
