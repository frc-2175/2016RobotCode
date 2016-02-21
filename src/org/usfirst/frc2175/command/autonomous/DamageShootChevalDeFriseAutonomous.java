package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageShootChevalDeFriseAutonomous extends CommandGroup {
    public DamageShootChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TODO Refine Numbers
        addSequential(new DriveInches(robotSubsystems, robotControllers, 12));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new RaiseBootCommand(robotSubsystems));
        addSequential(new DriveInches(robotSubsystems, robotControllers, 83));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 30, true));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
        addParallel(new RetractCatapultCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        addSequential(new DriveInches(robotSubsystems, robotControllers, -100));
        addSequential(new DriveInches(robotSubsystems, robotControllers, 16));
        addSequential(new LowerBootCommand(robotSubsystems));
        addParallel(new RaiseBootCommand(robotSubsystems));
        addSequential(new DriveInches(robotSubsystems, robotControllers, 74));
    }
}
