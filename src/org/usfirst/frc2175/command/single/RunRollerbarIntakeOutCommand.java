package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.intake.RollerbarIntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunRollerbarIntakeOutCommand extends Command {

    private double speed;
    private RollerbarIntakeSubsystem rollerbarIntakeSubsystem;

    public RunRollerbarIntakeOutCommand(RobotSubsystems robotSubsystems,
            double speed) {
        rollerbarIntakeSubsystem =
                robotSubsystems.getRollerbarIntakeSubsystem();
        this.speed = speed;

        requires(rollerbarIntakeSubsystem);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        rollerbarIntakeSubsystem.setRollerbarSpeed(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        rollerbarIntakeSubsystem.setRollerbarSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
