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

public class DamageShootPortcullisAutonomous extends CommandGroup {
    public DamageShootPortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TODO Fix number of inches as needed
        addSequential(new DriveInches(robotSubsystems, robotControllers, 20));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers, 65));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
        addSequential(new RetractCatapultCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, true));
        addSequential(new DriveInches(robotSubsystems, robotControllers, 20));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers, 70));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, true));
        addSequential(new DriveInches(robotSubsystems, robotControllers, 25));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers, 55));
        addSequential(new RaiseBootCommand(robotSubsystems));
    }
}
