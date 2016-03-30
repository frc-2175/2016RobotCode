package org.usfirst.frc2175.subsystem.camera;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

public class CameraSubsystem extends BaseSubsystem {
    private VisionProcessingConfig visionProcessingConfig;
    private double centerCamera;
    private static double CAMERA_FOV;

    public CameraSubsystem(RobotConfig robotConfig) {
        this.visionProcessingConfig = robotConfig.getVisionProcessingConfig();
        this.centerCamera = robotConfig.getControlLoopConfig()
                .getVisionTurnPID_centerCamera();
        this.CAMERA_FOV = visionProcessingConfig.getCameraFOV();
    }

    private double getGoalDistanceFromCenterInPixels() {
        return visionProcessingConfig.getLargestContourCenterX() - centerCamera;
    }

    public double getGoalDistanceFromCenterInDegrees() {
        // TODO figure out the math for this
        return getGoalDistanceFromCenterInPixels();
    }

}
