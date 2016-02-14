package org.usfirst.frc2175.pid;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

public class RobotControllers {
    private final RobotConfig robotConfig;

    private final VisionTurnPIDController visionTurnPIDController;

    public RobotControllers(RobotConfig robotConfig,
            RobotSubsystems robotSubsystems) {
        this.robotConfig = robotConfig;
        this.visionTurnPIDController = new VisionTurnPIDController(
                robotSubsystems, robotConfig);
    }

    public VisionTurnPIDController getVisionTurnPIDController() {
        return visionTurnPIDController;
    }

}
