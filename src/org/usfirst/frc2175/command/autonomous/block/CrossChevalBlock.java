package org.usfirst.frc2175.command.autonomous.block;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevalBlock extends CommandGroup {
    public CrossChevalBlock(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, boolean stayingOnCheval) {
        addSequential(new DriveUpToOuterworksBlock(robotSubsystems,
                robotControllers));
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 12));
        addSequential(new LowerBootCommand(robotSubsystems));
        if (stayingOnCheval) {
            addSequential(new DriveInchesCommand(robotSubsystems,
                    robotControllers, 85));
        } else {
            addSequential(new DriveInchesCommand(robotSubsystems,
                    robotControllers, 105));
        }
        addSequential(new RaiseBootCommand(robotSubsystems));
    }
}
