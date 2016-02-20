package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.pid.VisionTurnPIDController;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToFaceGoalCommand extends Command {
    private PowertrainSubsystem powertrainSubsystem;
    private VisionTurnPIDController pidController;
    private double setpoint;

    public TurnToFaceGoalCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        this.setpoint = powertrainSubsystem.getCenterCameraXValue();

        this.pidController = robotControllers.getVisionTurnPIDController();

        requires(powertrainSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        pidController.setSetpoint(setpoint);
        SmartDashboard.putNumber("Setpoint:", setpoint);

        pidController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        System.out.println("Rectangle at: "
                + powertrainSubsystem.getLargestContourXValue());
        System.out.println("Turning to face goal at turn value: "
                + pidController.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return pidController.onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        pidController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
