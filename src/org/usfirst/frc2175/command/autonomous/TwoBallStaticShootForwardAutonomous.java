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
        // Block to cross defense and shoot and drive back
        addSequential(new CrossStaticDefenseAndShootForwardAutonomous(
                robotSubsystems, robotControllers, visionProcessing));
        // Re-align to drive straight
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        // Running wheels as driving backwards
        addParallel(new RunRollerbarIntakeAtSpeedCommand(robotSubsystems,
                -liftIntakeSpeed), 2);
        // Driving backwards
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -10.5));
        // Allows ball to settle
        addSequential(new EmptyCommand(), .1);
        // Close intake
        addSequential(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed), .25);
        // Drive towards the defense
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                10.5));
        // Re-align the defense
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        // Cross defense and shoot
        addSequential(new CrossStaticDefenseAndShootForwardAutonomous(
                robotSubsystems, robotControllers, visionProcessing));
    }
}
