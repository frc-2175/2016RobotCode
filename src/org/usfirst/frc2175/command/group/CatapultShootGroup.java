package org.usfirst.frc2175.command.group;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CatapultShootGroup extends CommandGroup {
    private final Logger log = Logger.getLogger(getClass().getName());

    public CatapultShootGroup(RobotSubsystems robotSubsystems) {
        addSequential(new ExtendCatapultCommand(robotSubsystems),
                robotSubsystems.getCatapultShooterSubsystem()
                        .getWantedShotDelay());
        log.log(Level.FINE, "Firing catapult with delay " + robotSubsystems
                .getCatapultShooterSubsystem().getWantedShotDelay());
        addSequential(new RetractCatapultCommand(robotSubsystems), .1);
    }
}
