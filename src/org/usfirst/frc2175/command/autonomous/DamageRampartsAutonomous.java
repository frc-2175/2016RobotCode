package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageRampartsAutonomous extends CommandGroup {
    public DamageRampartsAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TODO Fix number of inches as needed
        addSequential(new DriveInches(robotSubsystems, robotControllers, 24));
        // TODO Fix number of inches as needed
        addSequential(new DriveInches(robotSubsystems, robotControllers, -24));
        // TODO Fix number of inches as needed
        addSequential(new DriveInches(robotSubsystems, robotControllers, 12));
    }

}
