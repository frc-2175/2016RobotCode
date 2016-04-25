package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallStaticShootSlightRightAutonomous extends CommandGroup {
    public TwoBallStaticShootSlightRightAutonomous(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        double liftIntakeSpeed = robotSubsystems.getRobotConfig()
                .getIntakeConfig().getLiftIntakeSpeed();
        addSequential(new CrossStaticDefenseAndShootForwardAutonomous(
                robotSubsystems, robotControllers, visionProcessing));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        // TODO Add command to turn to ball
        addParallel(new RunRollerbarIntakeAtSpeedCommand(robotSubsystems,
                -liftIntakeSpeed), 1.75);
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, -18));
        addSequential(new EmptyCommand(), .3);
        addSequential(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed), .25);
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 18));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        addSequential(new CrossStaticDefenseAndShootForwardAutonomous(
                robotSubsystems, robotControllers, visionProcessing));
    }
}
