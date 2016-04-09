package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalWithGyroCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenSimpleDefenseAndShootForwardAutonomous extends CommandGroup {
    public WeakenSimpleDefenseAndShootForwardAutonomous(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        double liftIntakeSpeed = -.5;

        // Drive forwards
        addSequential(new WeakenSimpleDefenseAutonomous(robotSubsystems,
                robotControllers));
        // Lower intake
        addSequential(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed), .8);

        addSequential(new EmptyCommand(), .4);
        // Spin Intake In
        addSequential(new RunRollerbarIntakeAtSpeedCommand(robotSubsystems, 1),
                .25);

        addSequential(new EmptyCommand(), .4);
        // Aim at goal
        addSequential(new TurnToFaceGoalWithGyroCommand(robotSubsystems,
                robotControllers));
        addSequential(new EmptyCommand(), .5);
        addSequential(new TurnToFaceGoalWithGyroCommand(robotSubsystems,
                robotControllers));
        addSequential(new EmptyCommand(), .5);
        addSequential(new TurnToFaceGoalWithGyroCommand(robotSubsystems,
                robotControllers));
        addSequential(new EmptyCommand(), .5);
        addSequential(new TurnToFaceGoalWithGyroCommand(robotSubsystems,
                robotControllers));
        addSequential(new EmptyCommand(), .4);
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
