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
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double moveValue = driverStation.getMoveValue();
        double turnValue = driverStation.getTurnValue();
        if (moveValue == 0) {
            powertrainSubsystem.arcadeDrive(moveValue, turnValue);
        } else if (turnValue <= 0) {
            powertrainSubsystem.tankDrive(turnValue, 0);
        } else {
            powertrainSubsystem.tankDrive(0, turnValue);
        }
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
