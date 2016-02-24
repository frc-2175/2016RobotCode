package org.usfirst.frc2175.command.single;

import java.util.logging.Logger;

import org.usfirst.frc2175.pid.DriveInchesPIDController;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveInchesCommand extends Command {
    private final Logger log = Logger.getLogger(getClass().getName());

    private final PowertrainSubsystem powertrainSubsystem;
    private final DriveInchesPIDController pidController;
    private final double distance;

    public DriveInchesCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, double distance) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        requires(powertrainSubsystem);

        this.distance = distance;

        this.pidController = robotControllers.getDriveInchesPIDController();

        log.finer("DriveInchesCommand distance=" + distance);
    }

    @Override
    protected void initialize() {
        powertrainSubsystem.resetEncoders();
        pidController.setSetpoint(distance);
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
        pidController.disable();
    }

    @Override
    protected void interrupted() {
        end();
    }
}