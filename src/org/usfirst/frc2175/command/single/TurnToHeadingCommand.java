package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.pid.GyroTurnPIDController;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToHeadingCommand extends BaseCommand {
    private PowertrainSubsystem powertrainSubsystem;
    private GyroTurnPIDController pidController;
    private double setpoint;
    private boolean resetGyro;

    public TurnToHeadingCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, double degrees,
            boolean resetGyro) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        this.setpoint = degrees;
        this.resetGyro = resetGyro;

        this.pidController = robotControllers.getGyroTurnPIDController();

        requires(powertrainSubsystem);
    }

    @Override
    protected void initialize() {
        super.initialize();
        pidController.setSetpoint(setpoint);
        if (resetGyro) {
            powertrainSubsystem.resetGyro();
        }
        SmartDashboard.putNumber("Setpoint:", setpoint);

        pidController.enable();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return pidController.onTarget();
    }

    @Override
    protected void end() {
        super.end();
        pidController.disable();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
