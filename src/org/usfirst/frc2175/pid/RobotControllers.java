package org.usfirst.frc2175.pid;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfileDrivePIDController_Left;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfileDrivePIDController_Right;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

public class RobotControllers {
    private final Logger log = Logger.getLogger(getClass().getName());

    private final RobotConfig robotConfig;

    private final VisionTurnPIDController visionTurnPIDController;
    private final GyroTurnPIDController gyroTurnPIDController;
    private final DriveInchesPIDController driveInchesPIDController;

    private final MotionProfileDrivePIDController_Left motionProfileDrivePIDController_Left;
    private final MotionProfileDrivePIDController_Right motionProfileDrivePIDController_Right;

    public RobotControllers(RobotSubsystems robotSubsystems,
            RobotConfig robotConfig, VisionProcessing visionProcessing) {
        log.info("Configuring class=" + getClass());

        try {
            this.robotConfig = robotConfig;
            this.visionTurnPIDController = new VisionTurnPIDController(
                    robotSubsystems, robotConfig, visionProcessing);
            this.gyroTurnPIDController =
                    new GyroTurnPIDController(robotSubsystems, robotConfig);
            this.driveInchesPIDController =
                    new DriveInchesPIDController(robotSubsystems, robotConfig);

            this.motionProfileDrivePIDController_Left =
                    new MotionProfileDrivePIDController_Left(robotSubsystems,
                            robotConfig);
            this.motionProfileDrivePIDController_Right =
                    new MotionProfileDrivePIDController_Right(robotSubsystems,
                            robotConfig);
        } catch (Exception e) {
            final String msg =
                    "Error instantiating a PIDController in RobotControllers:";
            log.log(Level.SEVERE, msg, e);
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

    public MotionProfileDrivePIDController_Left getMotionProfileDrivePIDController_Left() {
        return motionProfileDrivePIDController_Left;
    }

    public MotionProfileDrivePIDController_Right getMotionProfileDrivePIDController_Right() {
        return motionProfileDrivePIDController_Right;
    }

}
