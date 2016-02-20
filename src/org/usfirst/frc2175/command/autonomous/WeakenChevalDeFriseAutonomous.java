package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenChevalDeFriseAutonomous extends CommandGroup {
    public WeakenChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TODO Fix number of inches as needed
        addSequential(new DriveInches(robotSubsystems, robotControllers, 12));
        // TODO Fix Boot commands if necessary
        addSequential(new LowerBootCommand(robotSubsystems));
        // TODO Fix inches as needed
        addParallel(new DriveInches(robotSubsystems, robotControllers, 24));
        addSequential(new RaiseBootCommand(robotSubsystems));

    }
}