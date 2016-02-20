package org.usfirst.frc2175.subsystem.powertrain;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.PowertrainConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;
import org.usfirst.frc2175.util.TalonGroup;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class PowertrainSubsystem extends BaseSubsystem {
    private PowertrainConfig powertrainConfig;

    private TalonGroup leftDriveSideTalonGroup;
    private TalonGroup rightDriveSideTalonGroup;
    private Encoder leftDriveEncoder;
    private Encoder rightDriveEncoder;
    private Solenoid[] driveShifters;
    private Gyro gyro;

    private ShifterState shifterState;

    private RobotDrive robotDrive;

    private double centerCameraXValue;
    private double largestContourCenterXValue;

    public PowertrainSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        powertrainConfig = robotConfig.getPowertrainConfig();

        leftDriveSideTalonGroup = wiringConfig.getLeftDriveTalonHandler();
        rightDriveSideTalonGroup = wiringConfig.getRightDriveTalonHandler();
        leftDriveEncoder = wiringConfig.getLeftDriveEncoder();
        rightDriveEncoder = wiringConfig.getRightDriveEncoder();
        gyro = wiringConfig.getGyro();
        driveShifters = wiringConfig.getShifterSolenoids();

        robotDrive = new RobotDrive(leftDriveSideTalonGroup,
                rightDriveSideTalonGroup);

        VisionProcessingConfig visionProcessingConfig = robotConfig
                .getVisionProcessingConfig();
        largestContourCenterXValue = visionProcessingConfig
                .getLargestContourCenterX();

        ControlLoopConfig controlLoopConfig = robotConfig.getControlLoopConfig();
        centerCameraXValue = controlLoopConfig.getVisionTurnPID_centerCamera();
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        // If the shifters are in a state where we can drive, drive. Otherwise,
        // do nothing
        if (isDriveEngaged()) {
            robotDrive.arcadeDrive(moveSpeed, rotateSpeed);
        } else {
        }
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        // If the shifters are in a state where we can drive, drive. Otherwise,
        // do nothing
        if (isDriveEngaged()) {
            robotDrive.tankDrive(leftSpeed, rightSpeed);
        } else {
        }
    }

    public void winchWithPTO(double speed) {
        // If shifted to climb, set motors, else do nothing
        if (isClimberEngaged()) {
            leftDriveSideTalonGroup.set(speed);
            rightDriveSideTalonGroup.set(speed);
        } else {
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
        case CLIMB:
            pwmValues = powertrainConfig.getStateClimb();
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

    public void shiftToClimb() {
        setShifterState(ShifterState.CLIMB);
    }

    public boolean isDriveEngaged() {
        return shifterState == ShifterState.HIGH
                || shifterState == ShifterState.LOW;
    }

    public boolean isClimberEngaged() {
        return shifterState == ShifterState.CLIMB;
    }

    public void resetGyro() {
        gyro.reset();
    }

    public double getGyroAngle() {
        return gyro.getAngle();
    }

    public double getLargestContourXValue() {
        return largestContourCenterXValue;
    }

    public double getCenterCameraXValue() {
        return centerCameraXValue;
    }

    private enum ShifterState {
        LOW, HIGH, CLIMB;
    }
}