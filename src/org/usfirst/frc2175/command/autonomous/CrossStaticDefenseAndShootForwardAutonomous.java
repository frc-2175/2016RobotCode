package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.autonomous.block.CrossStaticDefenseBlock;
import org.usfirst.frc2175.command.autonomous.block.PutBallInCorrectPlacement;
import org.usfirst.frc2175.command.autonomous.block.TurnToCenterOfGoalBlock;
import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossStaticDefenseAndShootForwardAutonomous extends CommandGroup {
    public CrossStaticDefenseAndShootForwardAutonomous(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        int times = 3;

        final SaveGyroHeading saveGyroAngleCommand =
                new SaveGyroHeading(robotSubsystems, robotControllers);

        // Drive forwards
        addSequential(new CrossStaticDefenseBlock(robotSubsystems,
                robotControllers, true));
        addSequential(saveGyroAngleCommand, .05);
        addSequential(new EmptyCommand(), .2);
        // open up pathway for catapult to shoot
        addSequential(new PutBallInCorrectPlacement(robotSubsystems,
                robotControllers));
        // Aim at goal
        addSequential(new TurnToCenterOfGoalBlock(robotSubsystems,
                robotControllers, visionProcessing, times));
        // Shoot!
        addSequential(new CatapultShootGroup(robotSubsystems));
        addSequential(new EmptyCommand(), 1);

        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, saveGyroAngleCommand));
        addSequential(new EmptyCommand(), .3);
        addSequential(new CrossStaticDefenseBlock(robotSubsystems,
                robotControllers, false));
    }
}
