package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachRockWallAutonomous extends CommandGroup {
    public BreachRockWallAutonomous(RobotSubsystems robotSubsystems) {
        // TODO Refine numbers
        addSequential(new DriveInches(robotSubsystems, 36));
        addSequential(new DriveInches(robotSubsystems, -36));
        addSequential(new DriveInches(robotSubsystems, 24));
    }
}
