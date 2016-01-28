package org.usfirst.frc2175.subsystem.drivetrain;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

public class DrivetrainSubsystem extends BaseSubsystem {
    // TODO add instance variables for robot components
    // (Talons, Solenoids, etc.)

    public DrivetrainSubsystem(RobotConfig robotConfig) {
        // TODO use values from robotConfig to set instance variables above
        // Then you can use those variables in the methods below!
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        // TODO Fill in
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
