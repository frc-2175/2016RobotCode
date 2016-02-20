package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageLowBarAutonomous extends CommandGroup {
    public DamageLowBarAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 36));
        addSequential(// TO-DO Fix inches as needed
                new DriveInches(robotSubsystems, robotControllers, -36));
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 30));
    }
}
