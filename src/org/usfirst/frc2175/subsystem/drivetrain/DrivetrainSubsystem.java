package org.usfirst.frc2175.subsystem.drivetrain;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class DrivetrainSubsystem extends BaseSubsystem {
    // TODO add instance variables for robot components
    // (Talons, Solenoids, etc.)

    private Talon leftDriveTalon;
    private Talon rightDriveTalon;

    private RobotDrive robotDrive;

    public DrivetrainSubsystem(RobotConfig robotConfig) {
        leftDriveTalon = robotConfig.getWiringConfig().getLeftDriveTalon();
        rightDriveTalon = robotConfig.getWiringConfig().getRightDriveTalon();

        robotDrive = new RobotDrive(leftDriveTalon, rightDriveTalon);
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        robotDrive.arcadeDrive(moveSpeed, rotateSpeed);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        // TODO Fill in
    }

    public void resetEncoders() {
        // TODO Fill in
    }

    public double getLeftEncoderDistance() {
        // TODO Fill in
        return 0;
    }

    public double getRightEncoderDistance() {
        // TODO Fill in
        return 0;
    }

    public double getMeanEncoderDistance() {
        return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2;
    }

    public void resetGyro() {
        // TODO Fill in
    }

    public double getGyroAngle() {
        // TODO Fill in
        return 0;
    }
}
