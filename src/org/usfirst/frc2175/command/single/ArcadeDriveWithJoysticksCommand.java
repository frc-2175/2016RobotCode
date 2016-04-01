package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

/**
 *
 */
public class ArcadeDriveWithJoysticksCommand extends BaseCommand {
    private final DriverStation driverStation;
    private final PowertrainSubsystem powertrainSubsystem;
    private double previousMoveValue;
    private double lastTime;

    public ArcadeDriveWithJoysticksCommand(DriverStation driverStation,
            RobotSubsystems robotSubsystems) {
        this.driverStation = driverStation;
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();

        requires(powertrainSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        super.initialize();
        previousMoveValue = 0.0;
        lastTime = System.nanoTime() / 1000000000.0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double moveValue = driverStation.getMoveValue();
        double turnValue = driverStation.getTurnValue();
        double absoluteMoveValue = Math.abs(moveValue);

        double thisTime = System.nanoTime() / 1000000000.0;
        double timeDifference = 0.3;

        if (thisTime - lastTime > timeDifference) {
            lastTime = thisTime;
            if (absoluteMoveValue > previousMoveValue) {
                previousMoveValue = absoluteMoveValue;
            } else if (previousMoveValue - absoluteMoveValue > 0.25) {
                absoluteMoveValue = previousMoveValue - 0.15;
                previousMoveValue = absoluteMoveValue;
            }
        }

        double sign = Math.abs(moveValue) / moveValue;
        moveValue = absoluteMoveValue * sign;
        powertrainSubsystem.arcadeDrive(moveValue, turnValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        super.end();
        powertrainSubsystem.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
