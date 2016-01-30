package org.usfirst.frc2175.subsystem.drivetrain;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class DrivetrainSubsystem extends BaseSubsystem {
    private Talon leftDriveTalon;
    private Talon rightDriveTalon;

    private RobotDrive robotDrive;

    private PIDController visionTurnController;

    private double centerCameraXValue;
    private VisionProcessingConfig visionProcessingConfig;

    public DrivetrainSubsystem(RobotConfig robotConfig) {
        leftDriveTalon = robotConfig.getWiringConfig().getLeftDriveTalon();
        rightDriveTalon = robotConfig.getWiringConfig().getRightDriveTalon();

        robotDrive = new RobotDrive(leftDriveTalon, rightDriveTalon);

        visionProcessingConfig = robotConfig.getVisionProcessingConfig();
        centerCameraXValue = robotConfig.getControlLoopConfig().getVisionTurnPID_centerCamera();

        VisionTurnControllerHandler visionTurnControllerHandler = new VisionTurnControllerHandler();
        visionTurnController = new PIDController(robotConfig.getControlLoopConfig().getVisionTurnPID_kProportional(),
                robotConfig.getControlLoopConfig().getVisionTurnPID_kIntegral(),
                robotConfig.getControlLoopConfig().getVisionTurnPID_kDerivative(), visionTurnControllerHandler,
                visionTurnControllerHandler);
        visionTurnController.setAbsoluteTolerance(robotConfig.getControlLoopConfig().getVisionTurnPID_absTolerance());

        // TODO make a properties file entry for these
        visionTurnController.setOutputRange(-0.8, 0.8);
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

    public double getDistanceFromCameraCenter() {
        double[] contourCenterXArray = visionProcessingConfig.getContourCenterX();
        double contourCenterX;

        if (contourCenterXArray.length != 0) {
            contourCenterX = contourCenterXArray[0];
        } else {
            contourCenterX = 0;
            // TODO add a log message here
        }

        System.out.println("Getting distance from camera center: " + contourCenterX);
        return contourCenterX;
    }

    public double getCenterCameraXValue() {
        return centerCameraXValue;
    }

    public PIDController getVisionTurnController() {
        return visionTurnController;
    }
}
