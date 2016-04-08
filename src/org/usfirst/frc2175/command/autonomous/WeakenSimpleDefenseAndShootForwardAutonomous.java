package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.single.EmptyCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalWithGyroCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenSimpleDefenseAndShootForwardAutonomous extends CommandGroup {
    public WeakenSimpleDefenseAndShootForwardAutonomous(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // Drive forwards
        addSequential(new WeakenSimpleDefenseAutonomous(robotSubsystems,
                robotControllers));
        // Aim at goal
        addSequential(new TurnToFaceGoalWithGyroCommand(robotSubsystems,
                robotControllers));
        // Wait to settle ball
        addSequential(new EmptyCommand(), .4);
        // Shoot!
        addSequential(new CatapultShootGroup(robotSubsystems));

    }

}
