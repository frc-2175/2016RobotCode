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
    private final PowertrainSubsystem powertrainSubsystem;
    private final GyroTurnPIDController pidController;
    private final double setpoint;
    private final double currentHeading;
    private final boolean isRelative;

    public TurnToHeadingCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, double degrees,
            boolean isRelative) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        this.currentHeading = powertrainSubsystem.getGyroAngle();
        this.isRelative = isRelative;

        if (this.isRelative) {
            this.setpoint = currentHeading + degrees;
        } else {
            this.setpoint = degrees;
        }

        this.pidController = robotControllers.getGyroTurnPIDController();

        requires(powertrainSubsystem);
    }

    public TurnToHeadingCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, double degrees) {
        this(robotSubsystems, robotControllers, degrees, false);
    }

    @Override
    protected void initialize() {
        super.initialize();
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
