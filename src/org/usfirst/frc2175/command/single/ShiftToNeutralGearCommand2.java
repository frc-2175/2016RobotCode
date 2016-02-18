package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftToNeutralGearCommand2 extends Command {
    private final PowertrainSubsystem powertrainSubsystem;

    public ShiftToNeutralGearCommand2(RobotSubsystems robotSubsystems) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        powertrainSubsystem.shiftToNeutral();
        ;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        powertrainSubsystem.shiftToLowGear();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
