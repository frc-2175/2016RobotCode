package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.intake.DreamIntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunDreamIntakeSideBeltAtSpeedCommand extends Command {
    private double speed;
    private DreamIntakeSubsystem dreamIntakeSubsystem;

    public RunDreamIntakeSideBeltAtSpeedCommand(DriverStation driverStation,
            RobotSubsystems robotSubsystems, double speed) {
        dreamIntakeSubsystem = robotSubsystems.getDreamIntakeSubsystem();
        this.speed = speed;

        requires(dreamIntakeSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        dreamIntakeSubsystem.setSideBeltSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        dreamIntakeSubsystem.setSideBeltSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
