package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.autonomous.block.CrossStaticDefenseBlock;
import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalWithGyroCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossStaticDefenseAndShootHardRightAutonomous
        extends CommandGroup {
    public CrossStaticDefenseAndShootHardRightAutonomous(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        double liftIntakeSpeed = -.5;

        // Drive forwards
        addSequential(new CrossStaticDefenseBlock(robotSubsystems,
                robotControllers, true));
        // Drive to side goal
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 30));
        // Turn
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 20));
        // Drive a bit more (may not be necessary)
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 12));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 45));
        // Lower intake
        addSequential(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed), .8);
        addSequential(new EmptyCommand(), .4);

        // Spin Wheels In
        addSequential(new RunRollerbarIntakeAtSpeedCommand(robotSubsystems, 1),
                .25);
        addSequential(new EmptyCommand(), .4);
        // Aim at goal
        addSequential(new TurnToFaceGoalWithGyroCommand(robotSubsystems,
                robotControllers, visionProcessing));
        addSequential(new EmptyCommand(), .4);
        // Shoot!
        addSequential(new CatapultShootGroup(robotSubsystems));
        addSequential(new EmptyCommand(), .4);

    }
}
