package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.command.single.TurnDegrees;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossShootPortcullisAutonomous extends CommandGroup {
    public CrossShootPortcullisAutonomous(RobotSubsystems robotSubsystems) {
        // TODO Fix number of inches as needed
        addSequential(new DriveInches(robotSubsystems, 12));
        addSequential(new RaiseBootCommand(robotSubsystems));
        // TODO Fix number of inches as needed
        addParallel(new DriveInches(robotSubsystems, 24));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
        addSequential(new TurnDegrees(robotSubsystems, 180));
        // TODO Fix number of inches as needed
        addSequential(new DriveInches(robotSubsystems, 12));
        addSequential(new RaiseBootCommand(robotSubsystems));
        // TODO Fix number of inches as needed
        addParallel(new DriveInches(robotSubsystems, 24));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new TurnDegrees(robotSubsystems, 180));
        // TODO Fix number of inches as needed
        addSequential(new DriveInches(robotSubsystems, 12));
        addSequential(new RaiseBootCommand(robotSubsystems));

    }

}
