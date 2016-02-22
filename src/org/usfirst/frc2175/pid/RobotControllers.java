package org.usfirst.frc2175.pid;

import java.util.logging.Logger;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

public class RobotControllers {
    private final Logger log = Logger.getLogger(getClass().getName());

    private final RobotConfig robotConfig;

    private final VisionTurnPIDController visionTurnPIDController;
    private final GyroTurnPIDController gyroTurnPIDController;
    private final DriveInchesPIDController driveInchesPIDController;

    public RobotControllers(RobotSubsystems robotSubsystems,
            RobotConfig robotConfig) {
        log.info("Configuring class=" + getClass());

        try {
            this.robotConfig = robotConfig;
            this.visionTurnPIDController =
                    new VisionTurnPIDController(robotSubsystems, robotConfig);
            this.gyroTurnPIDController =
                    new GyroTurnPIDController(robotSubsystems, robotConfig);
            this.driveInchesPIDController =
                    new DriveInchesPIDController(robotSubsystems, robotConfig);
        } catch (Exception e) {
            log.severe(e.toString());
            throw e;
        }
    }

    public VisionTurnPIDController getVisionTurnPIDController() {
        return visionTurnPIDController;
    }

    public GyroTurnPIDController getGyroTurnPIDController() {
        return gyroTurnPIDController;
    }

    public DriveInchesPIDController getDriveInchesPIDController() {
        return driveInchesPIDController;
    }

}
