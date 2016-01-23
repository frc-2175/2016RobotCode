package org.usfirst.frc2175.subsystem.drivetrain;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DrivetrainSubsystem extends Subsystem {
    @Override
    protected void initDefaultCommand() {
        // TODO setDefaultCommand(new TheCommandToUse());
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
