package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.intake.RollerbarIntakeSubsystem;

/**
 *
 */
public class RunIntakeLiftAtSpeedCommand extends BaseCommand {

    private RollerbarIntakeSubsystem rollerbarIntakeSubsystem;
    private double speed;

    public RunIntakeLiftAtSpeedCommand(RobotSubsystems robotSubsystems,
            double speed) {
        rollerbarIntakeSubsystem =
                robotSubsystems.getRollerbarIntakeSubsystem();
        this.speed = speed;

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        rollerbarIntakeSubsystem.setRollerbarLiftSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        super.end();
        rollerbarIntakeSubsystem.setRollerbarLiftSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
