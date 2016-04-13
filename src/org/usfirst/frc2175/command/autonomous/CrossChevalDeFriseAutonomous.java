package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.autonomous.block.CrossChevalBlock;
import org.usfirst.frc2175.command.autonomous.block.DriveUpToOuterworksBlock;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevalDeFriseAutonomous extends CommandGroup {
    public CrossChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        addSequential(new DriveUpToOuterworksBlock(robotSubsystems,
                robotControllers));
        addSequential(
                new CrossChevalBlock(robotSubsystems, robotControllers, false));
    }
}
