package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.EmptyCommand;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DamageSimpleDefenseAutonomous extends CommandGroup {

    public DamageSimpleDefenseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {

        // TODO Fix numbers as needed
        // TODO add descriptive comments for each command
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 148));
        addSequential(new EmptyCommand(), 0.5);
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -116));
        addSequential(new EmptyCommand(), 0.5);
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 76));
    }
}
