package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfileControllerHandler;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfiler;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfiler.MotionProfile;

public class DriveDistanceProfileCommand extends BaseCommand {
    private MotionProfileControllerHandler profileHandler;

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
