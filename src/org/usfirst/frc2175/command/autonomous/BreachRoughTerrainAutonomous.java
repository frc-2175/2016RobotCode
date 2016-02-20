package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachRoughTerrainAutonomous extends CommandGroup {
    public BreachRoughTerrainAutonomous(RobotSubsystems robotSubsystems) {
        addSequential(// TO-DO Refine Numbers
                new DriveInches(robotSubsystems, 36));
        addSequential(// TO-DO Refine Numbers
                new DriveInches(robotSubsystems, -36));
        addSequential(// TO-DO Refine Numbers
                new DriveInches(robotSubsystems, 30));
    }
}
