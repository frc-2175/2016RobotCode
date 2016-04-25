package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.command.autonomous.SaveGyroHeading;
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
    private double setpoint;
    private final double currentHeading;
    private final boolean isRelative;

    private SaveGyroHeading saveGyroHeading = null;

    public TurnToHeadingCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, double degrees,
            boolean isRelative) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        this.currentHeading = powertrainSubsystem.getGyroAngle();
        this.isRelative = isRelative;
        this.pidController = robotControllers.getGyroTurnPIDController();

        if (this.isRelative) {
            this.setpoint = currentHeading + degrees;
        } else {
            this.setpoint = degrees;
        }

        requires(powertrainSubsystem);
    }

    public TurnToHeadingCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, double degrees) {
        this(robotSubsystems, robotControllers, degrees, false);
    }

    public TurnToHeadingCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers,
            SaveGyroHeading saveGyroHeading) {
        this(robotSubsystems, robotControllers, 0, false);

        this.saveGyroHeading = saveGyroHeading;

        requires(powertrainSubsystem);
    }

    @Override
    protected void initialize() {
        super.initialize();

        if (saveGyroHeading != null) {
            setpoint = saveGyroHeading.getGyroAngle();
        }

        pidController.setSetpoint(setpoint);

        SmartDashboard.putNumber("Setpoint:", setpoint);

        pidController.enable();
        log.fine("THIS" + setpoint);
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
