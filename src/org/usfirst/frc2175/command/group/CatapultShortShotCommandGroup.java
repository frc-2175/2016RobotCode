package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CatapultShortShotCommandGroup extends CommandGroup {

    public CatapultShortShotCommandGroup(RobotSubsystems robotSubsystems) {
        addSequential(new ExtendCatapultCommand(robotSubsystems),
                robotSubsystems.getCatapultShooterSubsystem()
                        .getShortShotDelay());
        addSequential(new RetractCatapultCommand(robotSubsystems), .1);
    }
}
