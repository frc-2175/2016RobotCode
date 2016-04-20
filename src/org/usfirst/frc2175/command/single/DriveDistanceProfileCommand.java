package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfile;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfileControllerHandler;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfiler;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

/**
 * Command to drive a specific distance using a generated {@link MotionProfile}.
 * The profile is generated using the {@link MotionProfiler} class based on
 * arguments passed to the constructor, and then executed.
 *
 * @author Max Haland
 *
 */
public class DriveDistanceProfileCommand extends BaseCommand {
    private MotionProfileControllerHandler profileHandler;

    /**
     * Constructor to generate a profile based on a distance, with default
     * maximum velocity, maximum acceleration, time interval between each step.
     * The profile is generated, then fed to a profile handler.
     *
     * @param distance
     *            Total distance to drive
     *
     */
    public DriveDistanceProfileCommand(RobotConfig robotConfig,
            RobotControllers robotControllers, double distance) {
        MotionProfile profile =
                MotionProfiler.generateDriveDistanceMotionProfile(distance,
                        PowertrainSubsystem.MAX_LOW_GEAR_SPEED,
                        PowertrainSubsystem.MAX_AUTON_ACCELERATION);

        profileHandler = new MotionProfileControllerHandler(profile,
                robotControllers.getMotionProfileDrivePIDController_Left(),
                robotControllers.getMotionProfileDrivePIDController_Right());
    }

    /**
     * Constructor to generate a profile based on a distance, maximum velocity,
     * maximum acceleration, and default time interval between each step. The
     * profile is generated, then fed to a profile handler.
     *
     * @param distance
     *            Total distance to drive
     * @param maxVelocity
     *            Maximum velocity allowed
     * @param maxAcceleration
     *            Maximum acceleration allowed
     *
     */
    public DriveDistanceProfileCommand(RobotConfig robotConfig,
            RobotControllers robotControllers, double distance,
            double maxVelocity, double maxAcceleration) {
        MotionProfile profile =
                MotionProfiler.generateDriveDistanceMotionProfile(distance,
                        maxVelocity, maxAcceleration);

        profileHandler = new MotionProfileControllerHandler(profile,
                robotControllers.getMotionProfileDrivePIDController_Left(),
                robotControllers.getMotionProfileDrivePIDController_Right());
    }

    /**
     * Constructor to generate a profile based on a distance, maximum velocity,
     * maximum acceleration, and specified time interval between each step. The
     * profile is generated, then fed to a profile handler.
     *
     * @param distance
     *            Total distance to drive
     * @param maxVelocity
     *            Maximum velocity allowed
     * @param maxAcceleration
     *            Maximum acceleration allowed
     * @param dTime
     *            Time interval between each discrete step
     */
    public DriveDistanceProfileCommand(RobotConfig robotConfig,
            RobotControllers robotControllers, double distance,
            double maxVelocity, double maxAcceleration, int dTime) {
        MotionProfile profile =
                MotionProfiler.generateDriveDistanceMotionProfile(distance,
                        maxVelocity, maxAcceleration, dTime);

        profileHandler = new MotionProfileControllerHandler(profile,
                robotControllers.getMotionProfileDrivePIDController_Left(),
                robotControllers.getMotionProfileDrivePIDController_Right());
    }

    @Override
    protected void initialize() {
        super.initialize();
        profileHandler.enable();
    }

    @Override
    protected void execute() {
        // PID controllers handle themselves, so we don't need to do anything
        // here.
    }

    @Override
    protected boolean isFinished() {
        return profileHandler.isFinished();
    }

    @Override
    protected void end() {
        profileHandler.disable();
        super.end();
    }

    @Override
    protected void interrupted() {
        end();
    }

}
