package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.autonomous.block.CrossChevalBlock;
import org.usfirst.frc2175.command.autonomous.block.DriveUpToOuterworksBlock;
import org.usfirst.frc2175.command.autonomous.block.TurnToCenterOfGoalBlock;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevalDeFriseAndShootForwardAutonomous extends CommandGroup {

    public CrossChevalDeFriseAndShootForwardAutonomous(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {

        // drive up to outerworks
        addSequential(new DriveUpToOuterworksBlock(robotSubsystems,
                robotControllers));
        // crosses cheval
        addSequential(
                new CrossChevalBlock(robotSubsystems, robotControllers, false));
        // aligns to shoot
        addSequential(new TurnToCenterOfGoalBlock(robotSubsystems,
                robotControllers, 5));
        // shoot
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }

}
