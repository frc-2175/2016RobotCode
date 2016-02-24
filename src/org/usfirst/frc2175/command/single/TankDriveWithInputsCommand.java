package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

/**
 *
 */
public class TankDriveWithInputsCommand extends BaseCommand {
    private double leftValue;
    private double rightValue;
    private final PowertrainSubsystem powertrainSubsystem;

    public TankDriveWithInputsCommand(RobotSubsystems robotSubsystems,
            double leftValue, double rightValue) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        requires(powertrainSubsystem);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        powertrainSubsystem.tankDrive(leftValue, rightValue);
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
        powertrainSubsystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
