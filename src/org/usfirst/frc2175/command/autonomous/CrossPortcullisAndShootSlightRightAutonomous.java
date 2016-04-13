package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.autonomous.block.CrossPortcullisBlock;
import org.usfirst.frc2175.command.autonomous.block.DriveUpToOuterworksBlock;
import org.usfirst.frc2175.command.autonomous.block.TurnToCenterOfGoalBlock;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisAndShootSlightRightAutonomous extends CommandGroup {

    public CrossPortcullisAndShootSlightRightAutonomous(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {

        // drive up to outerworks
        addSequential(new DriveUpToOuterworksBlock(robotSubsystems,
                robotControllers));
        // crosses portcullis
        addSequential(new CrossPortcullisBlock(robotSubsystems,
                robotControllers, false));
        // turns left to get goal in shot
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, -25, false));
        // aligns to shoot
        addSequential(new TurnToCenterOfGoalBlock(robotSubsystems,
                robotControllers, 5));
        // shoot
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }

}
