package org.usfirst.frc2175.command.group;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.shooter.CatapultShooterSubsystem;
import org.usfirst.frc2175.subsystem.shooter.ShotType;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class CatapultShootGroup extends CommandGroup {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final RobotSubsystems robotSubsystems;

    public CatapultShootGroup(RobotSubsystems robotSubsystems) {
        this.robotSubsystems = robotSubsystems;
    }

    @Override
    public void initialize() {
        CatapultShooterSubsystem catapultShooterSubsystem =
                robotSubsystems.getCatapultShooterSubsystem();
        double delay = catapultShooterSubsystem.getCurrentShotDelay();
        log.log(Level.FINE, "Firing catapult with delay=" + delay);

        ShotType currentShot =
                catapultShooterSubsystem.getShotTypeSelector().getCurrentShot();

        final CommandGroup command;
        switch (currentShot) {
        case BATTER:
            command = new CatapultBatterShotCommandGroup(robotSubsystems);
            break;
        case MIDDLE:
            command = new CatapultMiddleShotCommandGroup(robotSubsystems);
            break;
        case RAMP:
            command = new CatapultRampShotCommandGroup(robotSubsystems);
            break;
        default:
            command = new CatapultMiddleShotCommandGroup(robotSubsystems);
            log.severe(
                    "updateSmartDashboardShotDisplay: ShotType='" + currentShot
                            + "' not coded for!  Using MIDDLE as default.");
        }

        Scheduler.getInstance().add(command);
    }
}
