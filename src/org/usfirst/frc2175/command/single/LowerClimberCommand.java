package org.usfirst.frc2175.command.single;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.climber.ClimberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerClimberCommand extends Command {
    private ClimberSubsystem climberSubsystem;
    private final Logger log = Logger.getLogger(getClass().getName());

    public LowerClimberCommand(RobotSubsystems robotSubsystems) {
        climberSubsystem = robotSubsystems.getClimberSubsystem();

        requires(climberSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        climberSubsystem.setClimberPosition(false);
        log.log(Level.FINE, "Lowering catapult!");

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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
