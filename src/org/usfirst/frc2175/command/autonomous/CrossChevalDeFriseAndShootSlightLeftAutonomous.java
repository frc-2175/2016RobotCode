package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.autonomous.block.CrossChevalBlock;
import org.usfirst.frc2175.command.autonomous.block.DriveUpToOuterworksBlock;
import org.usfirst.frc2175.command.autonomous.block.TurnToCenterOfGoalBlock;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevalDeFriseAndShootSlightLeftAutonomous
        extends CommandGroup {
    public CrossChevalDeFriseAndShootSlightLeftAutonomous(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {

        // drive up to outerworks
        addSequential(new DriveUpToOuterworksBlock(robotSubsystems,
                robotControllers));
        // crosses cheval
        addSequential(
                new CrossChevalBlock(robotSubsystems, robotControllers, false));
        // turns left to get goal in shot
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 25, false));
        // aligns to shoot
        addSequential(new TurnToCenterOfGoalBlock(robotSubsystems,
                robotControllers, visionProcessing, 5));
        // shoot
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }
}
