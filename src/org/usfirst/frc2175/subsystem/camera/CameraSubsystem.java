package org.usfirst.frc2175.subsystem.camera;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

public class CameraSubsystem extends BaseSubsystem {
    private static double CAMERA_FOV;
    private static double CAMERA_HORIZONTAL_RES;
    private VisionProcessingConfig visionProcessingConfig;
    private double centerCamera;

    public CameraSubsystem(RobotConfig robotConfig) {
        this.visionProcessingConfig = robotConfig.getVisionProcessingConfig();
        this.centerCamera = robotConfig.getControlLoopConfig()
                .getVisionTurnPID_centerCamera();
        this.CAMERA_FOV = visionProcessingConfig.getCameraFOV();
        this.CAMERA_HORIZONTAL_RES =
                visionProcessingConfig.getCameraHorizontalRes();
    }

    private double getGoalDistanceFromCenterInPixels() {
        return visionProcessingConfig.getLargestContourCenterX() - centerCamera;
    }

    public double getGoalDistanceFromCenterInDegrees() {
        double degreesPerPixel = CAMERA_FOV / CAMERA_HORIZONTAL_RES;
        return getGoalDistanceFromCenterInPixels() * degreesPerPixel;
    }

}
