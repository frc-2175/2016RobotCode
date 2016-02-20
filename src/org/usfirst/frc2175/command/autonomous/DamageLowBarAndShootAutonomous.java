package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageLowBarAndShootAutonomous extends CommandGroup {
    public DamageLowBarAndShootAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TODO Refine Numbers
        addSequential(new DriveInches(robotSubsystems, robotControllers, 85));
        addSequential(new DriveInches(robotSubsystems, robotControllers, -90));
        addSequential(new DriveInches(robotSubsystems, robotControllers, 80));
        addSequential(
                new TurnToFaceGoalCommand(robotSubsystems, robotControllers));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }
}
