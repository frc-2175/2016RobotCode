package org.usfirst.frc2175.pid;

import java.util.logging.Logger;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

public class RobotControllers {
    private final Logger log = Logger.getLogger(getClass().getName());

    private final RobotConfig robotConfig;

    private final VisionTurnPIDController visionTurnPIDController;
    private final GyroTurnPIDController gyroTurnPIDController;

    public RobotControllers(RobotConfig robotConfig,
            RobotSubsystems robotSubsystems) {
        log.info("Configuring class=" + getClass());

        this.robotConfig = robotConfig;
        this.visionTurnPIDController =
                new VisionTurnPIDController(robotSubsystems, robotConfig);
        this.gyroTurnPIDController =
                new GyroTurnPIDController(robotSubsystems, robotConfig);
    }

    public VisionTurnPIDController getVisionTurnPIDController() {
        return visionTurnPIDController;
    }

    public GyroTurnPIDController getGyroTurnPIDController() {
        return gyroTurnPIDController;
    }

}
