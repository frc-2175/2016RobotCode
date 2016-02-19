package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.RunBootAtSpeedCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisAutonomous extends CommandGroup {

    public CrossPortcullisAutonomous(RobotSubsystems robotSubsystems) {
        addSequential(
                // TO-DO Fix number of inches as needed
                new DriveInches(robotSubsystems, 12));
        addSequential(
                // TO-DO Fix Boot commands if necessary
                new RaiseBootCommand(robotSubsystems));
        // TO-DO Refine speed
        new RunBootAtSpeedCommand(robotSubsystems, 20);
        // TO-DO Fix inches as needed
        new DriveInches(robotSubsystems, 24);
        new LowerBootCommand(robotSubsystems);

    }
}
