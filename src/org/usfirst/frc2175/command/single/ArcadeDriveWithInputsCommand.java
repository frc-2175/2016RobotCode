package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.drivetrain.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDriveWithInputsCommand extends Command {
    private double moveValue;
    private double turnValue;
    private final DrivetrainSubsystem drivetrainSubsystem;

    public ArcadeDriveWithInputsCommand(RobotSubsystems robotSubsystems,
            double moveValue, double turnValue) {
        this.drivetrainSubsystem = robotSubsystems.getDrivetrainSubsystem();
        requires(drivetrainSubsystem);
        this.moveValue = moveValue;
        this.turnValue = turnValue;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        drivetrainSubsystem.arcadeDrive(moveValue, turnValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        drivetrainSubsystem.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
