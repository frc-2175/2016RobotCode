package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.group.TurnToFaceGoalAndShootGroup;
import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenShootRockWallAutonomous extends CommandGroup {
    public WeakenShootRockWallAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TODO Refine numbers
        addSequential(new DriveInches(robotSubsystems, robotControllers, 85));
        addSequential(new TurnToFaceGoalAndShootGroup(robotSubsystems,
                robotControllers));
    }
}
