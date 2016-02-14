package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.SetMiddleBottomCatapultSolenoidCommand;
import org.usfirst.frc2175.command.single.SetMiddleTopCatapultSolenoidCommand;
import org.usfirst.frc2175.command.single.SetSideCatapultCylindersCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ExtendCatapultFullPowerGroup extends CommandGroup {

    public ExtendCatapultFullPowerGroup(RobotSubsystems robotSubsystems) {
        addParallel(new SetMiddleTopCatapultSolenoidCommand(robotSubsystems,
                false));
        addParallel(new SetMiddleBottomCatapultSolenoidCommand(robotSubsystems,
                true));
        addSequential(
                new SetSideCatapultCylindersCommand(robotSubsystems, true));

    }
}
