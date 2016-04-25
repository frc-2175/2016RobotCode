package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class SaveGyroHeading extends Command {
    private PowertrainSubsystem powertrainSubsystem;
    private double gyroAngle;

    public SaveGyroHeading(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        gyroAngle = powertrainSubsystem.getGyroAngle();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

    public double getGyroAngle() {
        return gyroAngle;
    }
}
