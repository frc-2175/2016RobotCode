package org.usfirst.frc2175.subsystem.vision;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;
import org.usfirst.frc2175.util.HighestArrayIndexFinder;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PhotonCannonSubsystem extends BaseSubsystem {
    private final DigitalOutput cameraLight;

    private final double cameraFov;
    private final double cameraHorizontalRes;
    private final double centerCamera;

    private final VisionProcessing visionProcessing;

    private boolean isLightOn;

    public PhotonCannonSubsystem(RobotConfig robotConfig,
            VisionProcessing visionProcessing) {
        ControlLoopConfig controlLoopConfig =
                robotConfig.getControlLoopConfig();
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        VisionProcessingConfig visionProcessingConfig =
                robotConfig.getVisionProcessingConfig();

        this.centerCamera = controlLoopConfig.getVisionTurnPID_centerCamera();

        this.cameraLight = wiringConfig.getCameraLight();

        this.cameraFov = visionProcessingConfig.getCameraFOV();
        this.cameraHorizontalRes =
                visionProcessingConfig.getCameraHorizontalRes();

        this.visionProcessing = visionProcessing;
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
        final double distance;

        double centerX = visionProcessing.getLargestContourCenterX();
        if (centerX == HighestArrayIndexFinder.NO_VALUES) {
            distance = 0;
        } else {
            distance = centerX - centerCamera;
        }

        return distance;
    }

    public double getGoalDistanceFromCenterInDegrees() {
        double degreesPerPixel = cameraFov / cameraHorizontalRes;
        return getGoalDistanceFromCenterInPixels() * degreesPerPixel;
    }
}
