package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.SetMiddleBottomCatapultSolenoidCommand;
import org.usfirst.frc2175.command.single.SetMiddleTopCatapultSolenoidCommand;
import org.usfirst.frc2175.command.single.SetSideCatapultCylindersCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ExtendCatapultMiddleOnGroup extends CommandGroup {

    public ExtendCatapultMiddleOnGroup(RobotSubsystems robotSubsystems) {
        addSequential(
                new SetMiddleTopCatapultSolenoidCommand(robotSubsystems, true));
        addSequential(new SetMiddleBottomCatapultSolenoidCommand(
                robotSubsystems, true));
        addSequential(
                new SetSideCatapultCylindersCommand(robotSubsystems, true));
    }
}
