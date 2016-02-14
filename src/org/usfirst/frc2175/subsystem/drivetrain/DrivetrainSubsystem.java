package org.usfirst.frc2175.subsystem.drivetrain;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;
import org.usfirst.frc2175.util.TalonGroup;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class DrivetrainSubsystem extends BaseSubsystem {
    private TalonGroup leftDriveSideTalonGroup;
    private TalonGroup rightDriveSideTalonGroup;
    private Encoder leftDriveEncoder;
    private Encoder rightDriveEncoder;
    private DoubleSolenoid driveShifters;
    private Gyro gyro;

    private RobotDrive robotDrive;

    private double centerCameraXValue;
    private double largestContourCenterXValue;

    public DrivetrainSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();

        leftDriveSideTalonGroup = wiringConfig.getLeftDriveTalonHandler();
        rightDriveSideTalonGroup = wiringConfig.getRightDriveTalonHandler();
        leftDriveEncoder = wiringConfig.getLeftDriveEncoder();
        rightDriveEncoder = wiringConfig.getRightDriveEncoder();
        driveShifters = wiringConfig.getDriveShifters();
        gyro = wiringConfig.getGyro();

        robotDrive = new RobotDrive(leftDriveSideTalonGroup, rightDriveSideTalonGroup);

        VisionProcessingConfig visionProcessingConfig =
                robotConfig.getVisionProcessingConfig();
        largestContourCenterXValue =
                visionProcessingConfig.getLargestContourCenterX();

        ControlLoopConfig controlLoopConfig =
                robotConfig.getControlLoopConfig();
        centerCameraXValue = controlLoopConfig.getVisionTurnPID_centerCamera();
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
}
