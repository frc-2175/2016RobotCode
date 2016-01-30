package org.usfirst.frc2175.subsystem.drivetrain;

import org.usfirst.frc2175.config.RobotConfig;
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

    private double[] contourXPosition;

    public DrivetrainSubsystem(RobotConfig robotConfig) {
        leftDriveTalon = robotConfig.getWiringConfig().getLeftDriveTalon();
        rightDriveTalon = robotConfig.getWiringConfig().getRightDriveTalon();

        robotDrive = new RobotDrive(leftDriveTalon, rightDriveTalon);

        contourXPosition = robotConfig.getVisionProcessingConfig().getContourCenterX();

        VisionTurnControllerHandler visionTurnControllerHandler = new VisionTurnControllerHandler();
        visionTurnController = new PIDController(robotConfig.getControlLoopConfig().getVisionTurnPID_kProportional(),
                robotConfig.getControlLoopConfig().getVisionTurnPID_kIntegral(),
                robotConfig.getControlLoopConfig().getVisionTurnPID_kDerivative(), visionTurnControllerHandler,
                visionTurnControllerHandler);
        visionTurnController.setAbsoluteTolerance(robotConfig.getControlLoopConfig().getVisionTurnPID_absTolerance());
        visionTurnController.setOutputRange(-0.8, 0.8); // TODO make a
                                                        // properties file entry
                                                        // for this

    }

    private class VisionTurnControllerHandler implements PIDSource, PIDOutput {

        @Override
        public void pidWrite(double output) {
            arcadeDrive(0, output);

        }

        @Override
        public double pidGet() {
            return contourXPosition[0];

        }

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
            // TODO Auto-generated method stub

        }

        @Override
        public PIDSourceType getPIDSourceType() {
            // TODO Auto-generated method stub
            return null;
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
}
