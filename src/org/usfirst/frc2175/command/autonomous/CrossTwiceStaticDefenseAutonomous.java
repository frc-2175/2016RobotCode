package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.autonomous.block.DriveUpToOuterWorksBlock;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossTwiceStaticDefenseAutonomous extends CommandGroup {
    public CrossTwiceStaticDefenseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // drive up to outerworks
        addSequential(new DriveUpToOuterWorksBlock(robotSubsystems,
                robotControllers));
        // cross static defense
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                111.4));
        // cross static defense
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -111.4));
        // cross static defense
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                81.4));
    }
}
