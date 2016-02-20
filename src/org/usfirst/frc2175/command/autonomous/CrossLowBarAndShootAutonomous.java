package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.group.TurnToFaceGoalAndShootGroup;
import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBarAndShootAutonomous extends CommandGroup {

    public CrossLowBarAndShootAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TO-DO Refine numbers
        addSequential(new DriveInches(robotSubsystems, 24));
        addSequential(new TurnToFaceGoalAndShootGroup(robotSubsystems,
                robotControllers));
    }
}
