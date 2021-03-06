package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallStaticShootSlightLeftAutonomous extends CommandGroup {
    public TwoBallStaticShootSlightLeftAutonomous(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        double liftIntakeSpeed = robotSubsystems.getRobotConfig()
                .getIntakeConfig().getLiftIntakeSpeed();
        addSequential(new CrossStaticDefenseAndShootSlightLeftAutonomous(
                robotSubsystems, robotControllers, visionProcessing));
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
        addSequential(new CrossStaticDefenseAndShootSlightLeftAutonomous(
                robotSubsystems, robotControllers, visionProcessing));
    }
}
