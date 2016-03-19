package org.usfirst.frc2175.command.single;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.shooter.CatapultShooterSubsystem;

/**
 *
 */
public class CycleDesiredShotCommand extends BaseCommand {
    private CatapultShooterSubsystem catapultSubsystem;
    private final Logger log = Logger.getLogger(getClass().getName());

    public CycleDesiredShotCommand(RobotSubsystems robotSubsystems) {
        catapultSubsystem = robotSubsystems.getCatapultShooterSubsystem();

        requires(robotSubsystems.getCatapultShooterSubsystem());
    }

    @Override
    protected void initialize() {
        super.initialize();
        catapultSubsystem.cycleShotType();

        switch (catapultSubsystem.getCurrentShot()) {
        case BATTER:
            log.log(Level.FINE, "Cycling catapult shot to BATTER");
            break;
        case MIDDLE:
            log.log(Level.FINE, "Cycling catapult shot to MIDDLE");
            break;
        case RAMP:
            log.log(Level.FINE, "Cycling catapult shot to RAMP");
            break;
        }
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
