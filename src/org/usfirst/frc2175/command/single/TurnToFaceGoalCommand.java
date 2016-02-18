package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToFaceGoalCommand extends Command {

    private PowertrainSubsystem powertrainSubsystem;
    private double setpoint;

    public TurnToFaceGoalCommand(RobotSubsystems robotSubsystems) {
        powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        setpoint = powertrainSubsystem.getCenterCameraXValue();

        requires(powertrainSubsystem);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        powertrainSubsystem.getVisionTurnController().setSetpoint(setpoint);
        SmartDashboard.putNumber("Setpoint:", setpoint);

        powertrainSubsystem.getVisionTurnController().enable();

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        System.out.println("Turning to face goal at turn value: "
                + powertrainSubsystem.getVisionTurnController().get());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return powertrainSubsystem.getVisionTurnController().onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        powertrainSubsystem.getVisionTurnController().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
