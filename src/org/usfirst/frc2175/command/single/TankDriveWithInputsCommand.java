package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.drivetrain.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveWithInputsCommand extends Command {
    private double leftValue;
    private double rightValue;
    private final DrivetrainSubsystem drivetrainSubsystem;

    public TankDriveWithInputsCommand(RobotSubsystems robotSubsystems,
            double leftValue, double rightValue) {
        this.drivetrainSubsystem = robotSubsystems.getDrivetrainSubsystem();
        requires(drivetrainSubsystem);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        drivetrainSubsystem.tankDrive(leftValue, rightValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        drivetrainSubsystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
