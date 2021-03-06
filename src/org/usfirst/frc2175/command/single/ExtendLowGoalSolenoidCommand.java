package org.usfirst.frc2175.command.single;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.shooter.CatapultShooterSubsystem;

/**
 * AKA punch ball command
 */
public class ExtendLowGoalSolenoidCommand extends BaseCommand {
    private CatapultShooterSubsystem catapultSubsystem;
    private final Logger log = Logger.getLogger(getClass().getName());

    public ExtendLowGoalSolenoidCommand(RobotSubsystems robotSubsystems) {
        catapultSubsystem = robotSubsystems.getCatapultShooterSubsystem();

        requires(robotSubsystems.getCatapultShooterSubsystem());
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        super.initialize();
        catapultSubsystem.setLowGoalPosition(true);
        log.log(Level.FINE, "Extending low goal solenoid!");

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
        catapultSubsystem.setLowGoalPosition(false);
        super.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
