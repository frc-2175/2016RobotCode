package org.usfirst.frc2175.subsystem.vision;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PhotonCannonSubsystem extends BaseSubsystem {
    private final DigitalOutput cameraLight;

    private boolean isLightOn;

    public PhotonCannonSubsystem(RobotConfig robotConfig,
            VisionProcessing visionProcessing) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();

        this.cameraLight = wiringConfig.getCameraLight();
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
}
