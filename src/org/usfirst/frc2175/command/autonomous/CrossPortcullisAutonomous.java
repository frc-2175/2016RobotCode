package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.RunBootAtSpeedCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisAutonomous extends CommandGroup {

    public CrossPortcullisAutonomous(RobotSubsystems robotSubsystems) {
        // TODO Fix number of inches as needed
        addSequential(new DriveInches(robotSubsystems, 12));
        addSequential(new RaiseBootCommand(robotSubsystems));
        // TODO Refine speed
        addSequential(new RunBootAtSpeedCommand(robotSubsystems, 20));
        // TODO Fix inches as needed
        addSequential(new DriveInches(robotSubsystems, 24));
        addSequential(new LowerBootCommand(robotSubsystems));
    }
}
