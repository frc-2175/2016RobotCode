package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.autonomous.block.DriveUpToOuterWorksBlock;
import org.usfirst.frc2175.command.autonomous.block.TurnToCenterOfGoalBlock;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevalDeFriseAndShootForwardAutonomous extends CommandGroup {
    public CrossChevalDeFriseAndShootForwardAutonomous(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {

        // drive up to outerworks
        addSequential(
                new DriveUpToOuterWorksBlock(robotSubsystems, robotControllers),
                3);
        addSequential(new EmptyCommand(), 1);
        addSequential(new RaiseBootCommand(robotSubsystems), 2);
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 52),
                3);
        addParallel(new LowerBootCommand(robotSubsystems), 2);
        // aligns to shoot
        addSequential(new TurnToCenterOfGoalBlock(robotSubsystems,
                robotControllers, visionProcessing, 5));
        addSequential(new RunIntakeLiftAtSpeedCommand(robotSubsystems, -1), 1);
        // shoot
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }
}
