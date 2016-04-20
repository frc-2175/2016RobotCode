package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.autonomous.block.CrossPortcullisBlock;
import org.usfirst.frc2175.command.autonomous.block.DriveUpToOuterWorksBlock;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossTwicePortcullisAutonomous extends CommandGroup {
    public CrossTwicePortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        addSequential(new DriveUpToOuterWorksBlock(robotSubsystems,
                robotControllers));
        addSequential(new CrossPortcullisBlock(robotSubsystems,
                robotControllers, false));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, false));
        addSequential(new CrossPortcullisBlock(robotSubsystems,
                robotControllers, false));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, false));
        addSequential(new CrossPortcullisBlock(robotSubsystems,
                robotControllers, true));
    }
}
