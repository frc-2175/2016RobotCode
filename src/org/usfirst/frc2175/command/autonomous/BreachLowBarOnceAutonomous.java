package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachLowBarOnceAutonomous extends CommandGroup {
    public BreachLowBarOnceAutonomous(RobotSubsystems robotSubsystems) {
        addSequential(
                // TO-DO Refine numbers
                new DriveInches(robotSubsystems, 24));
    }
}
