package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfile;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfileControllerHandler;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfileDrivePIDController_Left;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfileDrivePIDController_Right;
import org.usfirst.frc2175.pid.motionprofiles.MotionProfiler;

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

    private MotionProfileDrivePIDController_Left leftController;
    private MotionProfileDrivePIDController_Right rightController;

    /**
     * Constructor to run a profile imported from a csv located in
     * "/home/lvuser/profiles/"
     *
     * @param csvName
     *            Name of CSV file to import
     */
    public DriveDistanceProfileCommand(RobotConfig robotConfig,
            RobotControllers robotControllers, String csvName, int dTime) {
        MotionProfile profile =
                MotionProfiler.parseMotionProfileFromCSV(csvName, dTime);

        this.leftController =
                robotControllers.getMotionProfileDrivePIDController_Left();
        this.rightController =
                robotControllers.getMotionProfileDrivePIDController_Right();

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
        System.out.println("Left controller setpoint: "
                + leftController.getSetpoint() + ", right controller setpoint: "
                + rightController.getSetpoint());
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
