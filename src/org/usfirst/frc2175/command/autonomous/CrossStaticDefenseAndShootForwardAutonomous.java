package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.autonomous.block.CrossStaticDefenseBlock;
import org.usfirst.frc2175.command.autonomous.block.TurnToCenterOfGoalBlock;
import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossStaticDefenseAndShootForwardAutonomous extends CommandGroup {
    public CrossStaticDefenseAndShootForwardAutonomous(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        double liftIntakeSpeed = -.5;
        int times = 1;

        // Drive forwards
        addParallel(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed), 1);
        addSequential(new EmptyCommand(), 3);
        addSequential(new CrossStaticDefenseBlock(robotSubsystems,
                robotControllers, true));
        addSequential(new EmptyCommand(), 3);
        // Aim at goal
        addSequential(new TurnToCenterOfGoalBlock(robotSubsystems,
                robotControllers, visionProcessing, times));
        addSequential(new EmptyCommand(), 3);
        // Shoot!
        addSequential(new CatapultShootGroup(robotSubsystems));
        addSequential(new EmptyCommand(), 3);
        addSequential(new EmptyCommand(), .2);
        addSequential(
                new TurnToHeadingCommand(robotSubsystems, robotControllers, 0),
                2.8);
        addSequential(new EmptyCommand(), 3);
        addSequential(new EmptyCommand(), .3);
        addSequential(new CrossStaticDefenseBlock(robotSubsystems,
                robotControllers, false));
    }
}
