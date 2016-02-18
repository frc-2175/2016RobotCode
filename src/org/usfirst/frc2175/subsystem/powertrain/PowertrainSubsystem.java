package org.usfirst.frc2175.subsystem.powertrain;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.PowertrainConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;
import org.usfirst.frc2175.util.TalonGroup;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
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

    private PIDController visionTurnController;

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

        VisionProcessingConfig visionProcessingConfig =
                robotConfig.getVisionProcessingConfig();
        largestContourCenterXValue =
                visionProcessingConfig.getLargestContourCenterX();

        ControlLoopConfig controlLoopConfig =
                robotConfig.getControlLoopConfig();
        centerCameraXValue = controlLoopConfig.getVisionTurnPID_centerCamera();

        VisionTurnControllerHandler visionTurnControllerHandler =
                new VisionTurnControllerHandler();
        visionTurnController = new PIDController(
                controlLoopConfig.getVisionTurnPID_kProportional(),
                controlLoopConfig.getVisionTurnPID_kIntegral(),
                controlLoopConfig.getVisionTurnPID_kDerivative(),
                visionTurnControllerHandler, visionTurnControllerHandler);
        visionTurnController.setAbsoluteTolerance(
                controlLoopConfig.getVisionTurnPID_absTolerance());

        visionTurnController.setOutputRange(
                controlLoopConfig.getVisionTurnPID_minRange(),
                controlLoopConfig.getVisionTurnPID_maxRange());
    }

    private class VisionTurnControllerHandler implements PIDSource, PIDOutput {
        @Override
        public void pidWrite(double output) {
            arcadeDrive(0, output);
        }

        @Override
        public double pidGet() {
            return getLargestContourXValue();
        }

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
            // We never need to set this, so it can be empty
        }

        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        if (shifterState == ShifterState.high
                || shifterState == ShifterState.low) {
            robotDrive.arcadeDrive(moveSpeed, rotateSpeed);
        } else {
            robotDrive.stopMotor();
        }
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        if (shifterState == ShifterState.high
                || shifterState == ShifterState.low) {
            robotDrive.tankDrive(leftSpeed, rightSpeed);
        } else {
            robotDrive.stopMotor();
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
        case low:
            pwmValues = powertrainConfig.getStateLow();
            break;
        case high:
            pwmValues = powertrainConfig.getStateHigh();
            break;
        case neutral:
            pwmValues = powertrainConfig.getStateNeutral();
            break;
        case climb:
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
        setShifterState(ShifterState.high);
    }

    public void shiftToLowGear() {
        setShifterState(ShifterState.low);
    }

    public void shiftToNeutral() {
        setShifterState(ShifterState.neutral);
    }

    public void shiftToClimb() {
        setShifterState(ShifterState.climb);
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

    public PIDController getVisionTurnController() {
        return visionTurnController;
    }

    private enum ShifterState {
        low, high, neutral, climb;
    }
}
