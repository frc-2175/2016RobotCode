package org.usfirst.frc2175.command.single;

import java.util.logging.Logger;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
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
    private final Logger log = Logger.getLogger(getClass().getName());

    private PowertrainSubsystem powertrainSubsystem;
    private VisionTurnPIDController pidController;
    private ControlLoopConfig controlLoopConfig;

    private double setpoint;

    public TurnToFaceGoalCommand(RobotSubsystems robotSubsystems,
            RobotConfig robotConfig, RobotControllers robotControllers) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        this.controlLoopConfig = robotConfig.getControlLoopConfig();

        this.pidController = robotControllers.getVisionTurnPIDController();

        requires(powertrainSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        this.setpoint = controlLoopConfig.getVisionTurnPID_centerCamera();
        pidController.setSetpoint(setpoint);
        SmartDashboard.putNumber("Vision turn PID setpoint:", setpoint);

        pidController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

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
        log.info("Reached setpoint; disabled controller");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
