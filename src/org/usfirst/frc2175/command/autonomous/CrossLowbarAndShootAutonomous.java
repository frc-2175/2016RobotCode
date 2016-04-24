package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.autonomous.block.CrossStaticDefenseBlock;
import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.command.single.SetDesiredShotCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalWithGyroCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.shooter.ShotType;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowbarAndShootAutonomous extends CommandGroup {
    public CrossLowbarAndShootAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        double liftIntakeSpeed = -.5;

        // Set the desired shot type
        addSequential(
                new SetDesiredShotCommand(robotSubsystems, ShotType.MIDDLE));

        // Drive forward
        addSequential(new CrossStaticDefenseBlock(robotSubsystems,
                robotControllers, true));

        // Drive extra distance
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 13));

        // Turn a bit
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 20, false));

        // Drive forward to avoid wall
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 24));

        // Move intake out
        addSequential(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed), .8);

        // Turn in general direction of goal
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 45, false));

        // Wait for image to process
        addSequential(new EmptyCommand(), .4);

        // Face the goal
        addSequential(new TurnToFaceGoalWithGyroCommand(robotSubsystems,
                robotControllers, visionProcessing));

        // Wait to settle ball
        addSequential(new EmptyCommand(), .4);

        // Shoot!
        addSequential(new CatapultShootGroup(robotSubsystems));
    }
}
