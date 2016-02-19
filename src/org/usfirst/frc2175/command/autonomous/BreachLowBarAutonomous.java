package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachLowBarAutonomous extends CommandGroup {
    public BreachLowBarAutonomous(RobotSubsystems robotSubsystems) {
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, 36));
        addSequential(// TO-DO Fix inches as needed
                new DriveInches(robotSubsystems, -36));
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, 30));
    }
}
