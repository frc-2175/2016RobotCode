package org.usfirst.frc2175.subsystem.drivetrain;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;
import org.usfirst.frc2175.util.MultipleTalonHandler;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class DrivetrainSubsystem extends BaseSubsystem {
    private MultipleTalonHandler leftDriveSideTalons;
    private MultipleTalonHandler rightDriveSideTalons;
    private Encoder leftDriveEncoder;
    private Encoder rightDriveEncoder;
    private DoubleSolenoid driveShifters;
    private Gyro gyro;

    private RobotDrive robotDrive;

    private PIDController visionTurnController;

    private double centerCameraXValue;
    private double largestContourCenterXValue;

    public DrivetrainSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();

        leftDriveSideTalons = wiringConfig.getLeftDriveTalonHandler();
        rightDriveSideTalons = wiringConfig.getRightDriveTalonHandler();
        leftDriveEncoder = wiringConfig.getLeftDriveEncoder();
        rightDriveEncoder = wiringConfig.getRightDriveEncoder();
        driveShifters = wiringConfig.getDriveShifters();
        gyro = wiringConfig.getGyro();

        robotDrive = new RobotDrive(leftDriveSideTalons, rightDriveSideTalons);

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
            return getDistanceFromCameraCenter();
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
        robotDrive.arcadeDrive(moveSpeed, rotateSpeed);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        robotDrive.tankDrive(leftSpeed, rightSpeed);
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
        driveShifters.set(DoubleSolenoid.Value.kForward);
    }

    public void shiftToLowGear() {
        driveShifters.set(DoubleSolenoid.Value.kReverse);
    }

    public void resetGyro() {
        gyro.reset();
    }

    public double getGyroAngle() {
        return gyro.getAngle();
    }

    public double getDistanceFromCameraCenter() {
        return largestContourCenterXValue;
    }

    public double getCenterCameraXValue() {
        return centerCameraXValue;
    }

    public PIDController getVisionTurnController() {
        return visionTurnController;
    }
}
