package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.drivetrain.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftToHighGearCommand extends Command {
    private final DrivetrainSubsystem drivetrainSubsystem;

    public ShiftToHighGearCommand(RobotSubsystems robotSubsystems) {
        this.drivetrainSubsystem = robotSubsystems.getDrivetrainSubsystem();
        requires(drivetrainSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        drivetrainSubsystem.shiftToHighGear();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        drivetrainSubsystem.shiftToLowGear();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
