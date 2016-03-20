package org.usfirst.frc2175.command.group;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
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
        double delay = robotSubsystems.getCatapultShooterSubsystem()
                .getCurrentShotDelay();
        log.log(Level.FINE, "Firing catapult with delay=" + delay);

        ShotType currentShot = robotSubsystems.getCatapultShooterSubsystem()
                .getShotTypeSelector().getCurrentShot();

        switch (currentShot) {
        case BATTER:
            Scheduler.getInstance()
                    .add(new CatapultBatterShotCommandGroup(robotSubsystems));
            break;
        case MIDDLE:
            Scheduler.getInstance()
                    .add(new CatapultMiddleShotCommandGroup(robotSubsystems));
            break;
        case RAMP:
            Scheduler.getInstance()
                    .add(new CatapultRampShotCommandGroup(robotSubsystems));
            break;
        default:
            log.severe("updateSmartDashboardShotDisplay: ShotType='"
                    + currentShot + "' not coded for!");
        }
    }
}
