package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageRockWallAutonomous extends CommandGroup {
    public DamageRockWallAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TODO Refine numbers
        addSequential(new DriveInches(robotSubsystems, robotControllers, 36));
        addSequential(new DriveInches(robotSubsystems, robotControllers, -36));
        addSequential(new DriveInches(robotSubsystems, robotControllers, 24));
    }
}
