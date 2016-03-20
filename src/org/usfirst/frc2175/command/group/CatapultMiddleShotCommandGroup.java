package org.usfirst.frc2175.command.group;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.shooter.ShotType;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CatapultMiddleShotCommandGroup extends CommandGroup {
    private final Logger log = Logger.getLogger(getClass().getName());

    public CatapultMiddleShotCommandGroup(RobotSubsystems robotSubsystems) {
        double delay = robotSubsystems.getCatapultShooterSubsystem()
                .getShotDelay(ShotType.MIDDLE);
        log.log(Level.FINE, "Firing catapult with delay=" + delay);

        addSequential(new ExtendCatapultCommand(robotSubsystems), delay);
        addSequential(new RetractCatapultCommand(robotSubsystems), .1);
    }
}
