package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.group.RunIntakeInGroup;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.SetDesiredShotCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalWithGyroCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.shooter.ShotType;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WeakenShootLowBarAutonomous extends CommandGroup {

    public WeakenShootLowBarAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int extraShootLength = autonomousConfig.getExtraShootLength();
        int caution = autonomousConfig.getCaution();
        double distanceWithShoot =
                travelLength + extraShootLength - 2 * caution;

        // Set the desired shot type
        addSequential(
                new SetDesiredShotCommand(robotSubsystems, ShotType.MIDDLE));

        // Drive forward
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceWithShoot));

        // Turn a bit
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 20, false));

        // Spin intake to settle ball
        addParallel(new RunIntakeInGroup(robotSubsystems,
                robotConfig.getIntakeConfig()), 10);

        // Drive forward to avoid wall
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 24));

        // Turn in general direction of goal
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 45, false));

        // Wait for image to process
        addSequential(new EmptyCommand(), .4);

        // Face the goal
        addSequential(new TurnToFaceGoalWithGyroCommand(robotSubsystems,
                robotControllers));

        // Wait to settle ball
        addSequential(new EmptyCommand(), .4);

        // Shoot!
        addSequential(new CatapultShootGroup(robotSubsystems));
    }
}
