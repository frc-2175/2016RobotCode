package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.autonomous.block.PutBallInCorrectPlacement;
import org.usfirst.frc2175.command.autonomous.block.TurnToCenterOfGoalBlock;
import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossStaticDefenseAndShootForwardAutonomous extends CommandGroup {
    public CrossStaticDefenseAndShootForwardAutonomous(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        double liftIntakeSpeed = -.5;
        int times = 5;

        // Drive forwards
        addSequential(new CrossStaticDefenseAutonomous(robotSubsystems,
                robotControllers));
        // open up pathway for catapult to shoot
        addSequential(new PutBallInCorrectPlacement(robotSubsystems,
                robotControllers));
        // let things settle
        addSequential(new EmptyCommand(), .4);
        // Aim at goal
        addSequential(new TurnToCenterOfGoalBlock(robotSubsystems,
                robotControllers, visionProcessing, times));
        // Shoot!
        addSequential(new CatapultShootGroup(robotSubsystems));
        addSequential(new EmptyCommand(), 2);
        addSequential(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                -liftIntakeSpeed), .8);
        // addSequential(
        // new TurnToHeadingCommand(robotSubsystems, robotControllers, 0));
        // addSequential(
        // new DriveInchesCommand(robotSubsystems, robotControllers, -148),
        // 8);
    }
}
