package org.usfirst.frc2175.command.single;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

/**
 *
 */
public class ShiftToLowGearCommand extends BaseCommand {
    private final PowertrainSubsystem powertrainSubsystem;
    private final Logger log = Logger.getLogger(getClass().getName());

    public ShiftToLowGearCommand(RobotSubsystems robotSubsystems) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        super.initialize();
        log.log(Level.FINE, "Shifting to high gear");
        powertrainSubsystem.shiftToLowGear();
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
        super.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
