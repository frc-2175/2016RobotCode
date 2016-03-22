package org.usfirst.frc2175.command.single;

import java.util.logging.Logger;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.pid.DriveInchesPIDController;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

/**
 *
 */
public class DriveInchesCommand extends BaseCommand {
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
        super.initialize();
        powertrainSubsystem.resetEncoders();
        pidController.setSetpoint(distance);
        pidController.enable();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        log.info("Mean encoder distance="
                + powertrainSubsystem.getMeanEncoderDistance());
        log.info("PID controller error=" + pidController.getError());
        log.info("PID controller avg error=" + pidController.getAvgError());

        return pidController.onTarget();
    }

    @Override
    protected void end() {
        super.end();

        log.info("DriveInches: end");

        log.info("Mean encoder distance="
                + powertrainSubsystem.getMeanEncoderDistance());
        log.info("PID controller error=" + pidController.getError());
        log.info("PID controller avg error=" + pidController.getAvgError());

        pidController.disable();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
