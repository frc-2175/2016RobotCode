package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachRampartsAutonomous extends CommandGroup {
    public BreachRampartsAutonomous(RobotSubsystems robotSubsystems) {
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, 24));
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, -24));
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, 12));
    }

}
