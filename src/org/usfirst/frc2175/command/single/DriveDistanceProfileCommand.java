package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfileControllerHandler;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfiler;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfiler.MotionProfile;

/**
 * Command to drive a specific distance using a generated motion profile. The
 * profile is generated based on arguments passed to the constructor, and then
 * executed.
 *
 * @author Max Haland
 *
 */
public class DriveDistanceProfileCommand extends BaseCommand {
    private MotionProfileControllerHandler profileHandler;

    /**
     * Constructor to generate a profile based on a distance, maximum velocity,
     * maximum acceleration, and time interval between each step. The profile is
     * generated, then fed to a profile handler.
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
        MotionProfiler profiler = new MotionProfiler();
        MotionProfile profile = profiler.generateDriveDistanceMotionProfile(
                distance, maxVelocity, maxAcceleration, dTime);

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
