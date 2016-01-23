package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveWithJoysticks extends Command {

    public TankDriveWithJoysticks() {
        requires(Robot.drivetrainSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double leftValue = Robot.oi.getLeftValue();
        double rightValue = Robot.oi.getRightValue();
        Robot.drivetrainSubsystem.tankDrive(leftValue, rightValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.drivetrainSubsystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
