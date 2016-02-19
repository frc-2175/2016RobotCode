package org.usfirst.frc2175.command.single;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftToClimbGearCommand extends Command {
    private final PowertrainSubsystem powertrainSubsystem;
    private final Logger log = Logger.getLogger(getClass().getName());

    public ShiftToClimbGearCommand(RobotSubsystems robotSubsystems) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        powertrainSubsystem.shiftToClimb();
        log.log(Level.FINE, "Shifting to climbing gear");
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
        log.log(Level.FINE, "Shifting to low gear");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
