package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenShootRoughTerrainAutonomous extends CommandGroup {
    public WeakenShootRoughTerrainAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TODO Refine Numbers
        // TODO Change angle of turn
        addSequential(new DriveInches(robotSubsystems, robotControllers, 95));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 30, true));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }
}
