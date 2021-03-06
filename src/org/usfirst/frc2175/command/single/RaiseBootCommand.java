package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.manipulator.ManipulatorSubsystem;

/**
 *
 */
public class RaiseBootCommand extends BaseCommand {

    private ManipulatorSubsystem manipulatorSubsystem;

    public RaiseBootCommand(RobotSubsystems robotSubsystems) {
        this.manipulatorSubsystem = robotSubsystems.getManipulatorSubsystem();

        requires(manipulatorSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        manipulatorSubsystem.moveBootUp();

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return manipulatorSubsystem.isBootUp();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        super.end();
        manipulatorSubsystem.setBootSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
