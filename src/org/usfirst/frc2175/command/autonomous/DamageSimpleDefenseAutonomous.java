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
        // drive over obstacle
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 148));
        // let ball settle
        addSequential(new EmptyCommand(), 0.5);
        // drive over obstacle
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -116));
        // let ball settle
        addSequential(new EmptyCommand(), 0.5);
        // drive over obstacle and end up at the end of the obstacle
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 76));
    }
}
