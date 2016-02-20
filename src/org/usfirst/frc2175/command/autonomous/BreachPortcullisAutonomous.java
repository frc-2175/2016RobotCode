package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.TurnDegrees;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachPortcullisAutonomous extends CommandGroup {
    public BreachPortcullisAutonomous(RobotSubsystems robotSubsystems) {
        addSequential(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, 12));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, 24));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnDegrees(robotSubsystems, 180));
        addSequential(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, 12));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, 24));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnDegrees(robotSubsystems, 180));
        addSequential(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, 12));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(
                // TODO Fix number of inches as needed
                new DriveInches(robotSubsystems, 6));
        addSequential(new RaiseBootCommand(robotSubsystems));
    }

}
