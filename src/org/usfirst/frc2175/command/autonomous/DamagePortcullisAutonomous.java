package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamagePortcullisAutonomous extends CommandGroup {
    public DamagePortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        addSequential(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 12));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 24));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, true));
        addSequential(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 12));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 24));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 180, true));
        addSequential(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 12));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 6));
        addSequential(new RaiseBootCommand(robotSubsystems));
    }

}
