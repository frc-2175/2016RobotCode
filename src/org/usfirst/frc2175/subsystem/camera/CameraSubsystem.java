package org.usfirst.frc2175.subsystem.camera;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

public class CameraSubsystem extends BaseSubsystem {
    private double cameraFov;
    private double cameraHorizontalRes;
    private VisionProcessingConfig visionProcessingConfig;
    private double centerCamera;

    public CameraSubsystem(RobotConfig robotConfig) {
        ControlLoopConfig controlLoopConfig =
                robotConfig.getControlLoopConfig();

        this.centerCamera = controlLoopConfig.getVisionTurnPID_centerCamera();

        this.visionProcessingConfig = robotConfig.getVisionProcessingConfig();
        this.cameraFov = visionProcessingConfig.getCameraFOV();
        this.cameraHorizontalRes =
                visionProcessingConfig.getCameraHorizontalRes();
    }

    private double getGoalDistanceFromCenterInPixels() {
        return visionProcessingConfig.getLargestContourCenterX() - centerCamera;
    }

    public double getGoalDistanceFromCenterInDegrees() {
        double degreesPerPixel = cameraFov / cameraHorizontalRes;
        return getGoalDistanceFromCenterInPixels() * degreesPerPixel;
    }
}
