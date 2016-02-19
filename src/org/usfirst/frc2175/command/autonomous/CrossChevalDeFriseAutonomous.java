package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevalDeFriseAutonomous extends CommandGroup {
    public CrossChevalDeFriseAutonomous(RobotSubsystems robotSubsystems) {
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, 12));
        addSequential(
                // TO-DO Fix Boot commands if necessary
                new LowerBootCommand(robotSubsystems));
        addParallel(// TO-DO Fix inches as needed
                new DriveInches(robotSubsystems, 24));
        addSequential(new RaiseBootCommand(robotSubsystems));

    }
}
