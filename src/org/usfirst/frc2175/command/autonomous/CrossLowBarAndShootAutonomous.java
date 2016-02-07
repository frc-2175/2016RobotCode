package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.group.TurnToFaceGoalAndShootGroup;
import org.usfirst.frc2175.command.single.ArcadeDriveWithInputsCommand;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBarAndShootAutonomous extends CommandGroup {

    public CrossLowBarAndShootAutonomous(DriverStation driverStation,
            RobotSubsystems robotSubsystems) {
        addSequential(
                new ArcadeDriveWithInputsCommand(robotSubsystems, 0.25, 0), 8);
        addSequential(new TurnToFaceGoalAndShootGroup(driverStation,
                robotSubsystems));
    }
}
