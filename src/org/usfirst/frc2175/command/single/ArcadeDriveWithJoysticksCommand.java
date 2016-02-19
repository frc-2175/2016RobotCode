package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDriveWithJoysticksCommand extends Command {
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
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double moveValue = driverStation.getMoveValue();
        double turnValue = driverStation.getTurnValue();
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
        powertrainSubsystem.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
