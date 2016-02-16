package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.drivetrain.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToFaceGoalCommand extends Command {

    private DrivetrainSubsystem drivetrainSubsystem;
    private double setpoint;

    public TurnToFaceGoalCommand(RobotSubsystems robotSubsystems) {
        drivetrainSubsystem = robotSubsystems.getDrivetrainSubsystem();
        setpoint = drivetrainSubsystem.getCenterCameraXValue();

        // requires(drivetrainSubsystem);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        drivetrainSubsystem.getVisionTurnController().setSetpoint(setpoint);
        SmartDashboard.putNumber("Setpoint:", setpoint);

        // drivetrainSubsystem.getVisionTurnController().enable();

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // System.out.println("Turning to face goal at turn value: "
        // + drivetrainSubsystem.getVisionTurnController().get());
        System.out.println("Rectangle at: "
                + drivetrainSubsystem.getLargestContourXValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return drivetrainSubsystem.getVisionTurnController().onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        drivetrainSubsystem.getVisionTurnController().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
