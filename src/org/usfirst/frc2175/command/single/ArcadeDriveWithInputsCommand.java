package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDriveWithInputsCommand extends Command {
    private double moveValue;
    private double turnValue;
    private final PowertrainSubsystem powertrainSubsystem;

    public ArcadeDriveWithInputsCommand(RobotSubsystems robotSubsystems,
            double moveValue, double turnValue) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        requires(powertrainSubsystem);
        this.moveValue = moveValue;
        this.turnValue = turnValue;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
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
