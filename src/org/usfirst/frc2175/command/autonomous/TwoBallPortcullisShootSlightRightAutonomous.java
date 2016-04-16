package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallPortcullisShootSlightRightAutonomous extends CommandGroup {
    public TwoBallPortcullisShootSlightRightAutonomous(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        double liftIntakeSpeed = robotSubsystems.getRobotConfig()
                .getIntakeConfig().getLiftIntakeSpeed();
        addSequential(new CrossPortcullisAndShootSlightRightAutonomous(
                robotSubsystems, robotControllers));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -128));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        // TODO Add command to turn to ball
        addParallel(new RunRollerbarIntakeAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed));
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, -30));
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 30));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        addSequential(new CrossPortcullisAndShootSlightRightAutonomous(
                robotSubsystems, robotControllers));
    }
}
