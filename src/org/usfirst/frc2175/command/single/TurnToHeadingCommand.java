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
    private double currentHeading;
    private boolean isRelative = false;

    public TurnToHeadingCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, double degrees,
            boolean isRelative) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        this.currentHeading = powertrainSubsystem.getGyroAngle();
        this.setpoint = degrees;
        this.isRelative = isRelative;

        this.pidController = robotControllers.getGyroTurnPIDController();

        requires(powertrainSubsystem);
    }

    public TurnToHeadingCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, double degrees) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        this.currentHeading = powertrainSubsystem.getGyroAngle();
        this.setpoint = degrees;

        this.pidController = robotControllers.getGyroTurnPIDController();

        requires(powertrainSubsystem);
    }

    @Override
    protected void initialize() {
        super.initialize();
        if (isRelative) {
            setpoint = currentHeading + setpoint;
        }
        pidController.setSetpoint(setpoint);
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
