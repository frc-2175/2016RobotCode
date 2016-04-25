package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallStaticShootForwardAutonomous extends CommandGroup {
    public TwoBallStaticShootForwardAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        double liftIntakeSpeed =
                robotConfig.getIntakeConfig().getLiftIntakeSpeed();
        addSequential(new CrossStaticDefenseAndShootForwardAutonomous(
                robotSubsystems, robotControllers, visionProcessing));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        addParallel(new RunRollerbarIntakeAtSpeedCommand(robotSubsystems,
                -liftIntakeSpeed), 2);
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -10.5));
        addSequential(new EmptyCommand(), .1);
        addSequential(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed), .25);
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                10.5));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        addSequential(new CrossStaticDefenseAndShootForwardAutonomous(
                robotSubsystems, robotControllers, visionProcessing));
    }
}
