package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.autonomous.block.CrossPortcullisBlock;
import org.usfirst.frc2175.command.autonomous.block.DriveUpToOuterworksBlock;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisAutonomous extends CommandGroup {
    public CrossPortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        addSequential(new DriveUpToOuterworksBlock(robotSubsystems,
                robotControllers));
        addSequential(new CrossPortcullisBlock(robotSubsystems,
                robotControllers, false));
    }

}
