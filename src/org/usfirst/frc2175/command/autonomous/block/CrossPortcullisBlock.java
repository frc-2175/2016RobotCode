package org.usfirst.frc2175.command.autonomous.block;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisBlock extends CommandGroup {
    public CrossPortcullisBlock(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers, boolean withCaution) {
        addParallel(new LowerBootCommand(robotSubsystems));
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 20));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInchesCommand(robotSubsystems, robotControllers,
                101.4));
        addSequential(new LowerBootCommand(robotSubsystems));
    }
}
