package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.group.CatapultShootGroup;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalWithGyroCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenSimpleDefenseAndShootSlightLeftAutonomous
        extends CommandGroup {
    public WeakenSimpleDefenseAndShootSlightLeftAutonomous(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        double liftIntakeSpeed = -.5;

        // Drive forwards
        addSequential(new WeakenSimpleDefenseAutonomous(robotSubsystems,
                robotControllers));
        // Turn
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, -20));
        // Drive a bit more (may not be necessary)
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 24));
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
                robotControllers));
        addSequential(new EmptyCommand(), .4);
        // Shoot!
        addSequential(new CatapultShootGroup(robotSubsystems));
    }

}
