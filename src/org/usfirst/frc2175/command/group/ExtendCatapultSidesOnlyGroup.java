package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.SetMiddleBottomCatapultSolenoidCommand;
import org.usfirst.frc2175.command.single.SetMiddleTopCatapultSolenoidCommand;
import org.usfirst.frc2175.command.single.SetSideCatapultCylindersCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ExtendCatapultSidesOnlyGroup extends CommandGroup {

    public ExtendCatapultSidesOnlyGroup(RobotSubsystems robotSubsystems) {
        addSequential(new SetMiddleTopCatapultSolenoidCommand(robotSubsystems,
                false));
        addSequential(new SetMiddleBottomCatapultSolenoidCommand(
                robotSubsystems, false));
        addSequential(
                new SetSideCatapultCylindersCommand(robotSubsystems, true));

    }
}
