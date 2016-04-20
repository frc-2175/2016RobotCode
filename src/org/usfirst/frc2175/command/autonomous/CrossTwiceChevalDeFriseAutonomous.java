package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.autonomous.block.CrossChevalBlock;
import org.usfirst.frc2175.command.autonomous.block.DriveUpToOuterWorksBlock;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossTwiceChevalDeFriseAutonomous extends CommandGroup {
    public CrossTwiceChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        addSequential(new DriveUpToOuterWorksBlock(robotSubsystems,
                robotControllers));
        addSequential(
                new CrossChevalBlock(robotSubsystems, robotControllers, false));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, false));
        addSequential(
                new CrossChevalBlock(robotSubsystems, robotControllers, false));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, false));
        addSequential(
                new CrossChevalBlock(robotSubsystems, robotControllers, true));
    }
}
