package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageLowBarAutonomous extends CommandGroup {
    public DamageLowBarAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TO-DO Fix numbers as needed
        addSequential(new DriveInches(robotSubsystems, robotControllers, 85));
        addSequential(new DriveInches(robotSubsystems, robotControllers, -90));
        addSequential(new DriveInches(robotSubsystems, robotControllers, 80));
    }
}
