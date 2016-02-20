package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachChevalDeFriseAutonomous extends CommandGroup {
    public BreachChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 12));
        addSequential(
                // TO-DO Fix Boot commands if necessary
                new LowerBootCommand(robotSubsystems));
        addParallel(// TO-DO Fix inches as needed
                new DriveInches(robotSubsystems, robotControllers, 24));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems, robotControllers, 180,
                true));
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 12));
        addSequential(
                // TO-DO Fix Boot commands if necessary
                new LowerBootCommand(robotSubsystems));
        addParallel(// TO-DO Fix inches as needed
                new DriveInches(robotSubsystems, robotControllers, 24));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems, robotControllers, 180,
                true));
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, robotControllers, 12));
        addSequential(
                // TO-DO Fix Boot commands if necessary
                new LowerBootCommand(robotSubsystems));
        addParallel(// TO-DO Fix inches as needed
                new DriveInches(robotSubsystems, robotControllers, 18));
        addSequential(new RaiseBootCommand(robotSubsystems));

    }
}
