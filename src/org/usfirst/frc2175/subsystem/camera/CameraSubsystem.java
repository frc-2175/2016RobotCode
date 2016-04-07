package org.usfirst.frc2175.subsystem.camera;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraSubsystem extends BaseSubsystem {
    private VisionProcessingConfig visionProcessingConfig;

    private DigitalOutput cameraLight;

    private boolean isLightOn;

    private double cameraFov;
    private double cameraHorizontalRes;
    private double centerCamera;

    public CameraSubsystem(RobotConfig robotConfig) {
        ControlLoopConfig controlLoopConfig =
                robotConfig.getControlLoopConfig();

        this.cameraLight = robotConfig.getWiringConfig().getCameraLight();
        this.isLightOn = false;

        this.centerCamera = controlLoopConfig.getVisionTurnPID_centerCamera();

        this.visionProcessingConfig = robotConfig.getVisionProcessingConfig();
        this.cameraFov = visionProcessingConfig.getCameraFOV();
        this.cameraHorizontalRes =
                visionProcessingConfig.getCameraHorizontalRes();

    }

    public void turnLightOn() {
        isLightOn = true;
        updateLight();
    }

    public void turnLightOff() {
        isLightOn = false;
        updateLight();
    }

    public void toggleLight() {
        isLightOn = !isLightOn;
        updateLight();
    }

    public void updateLight() {
        // RoboRIO is dumb and false means on
        cameraLight.set(!isLightOn);
        SmartDashboard.putBoolean("Light", isLightOn);
    }

    public boolean isLightOn() {
        return isLightOn;
    }

    private double getGoalDistanceFromCenterInPixels() {
        return visionProcessingConfig.getLargestContourCenterX() - centerCamera;
    }

    public double getGoalDistanceFromCenterInDegrees() {
        double degreesPerPixel = cameraFov / cameraHorizontalRes;
        return getGoalDistanceFromCenterInPixels() * degreesPerPixel;
    }
}
