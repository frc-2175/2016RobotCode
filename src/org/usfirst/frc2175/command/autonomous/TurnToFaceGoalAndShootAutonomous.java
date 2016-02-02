package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToFaceGoalCommand;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnToFaceGoalAndShootAutonomous extends CommandGroup {

    public TurnToFaceGoalAndShootAutonomous(DriverStation driverStation,
            RobotSubsystems robotSubsystems) {

        addSequential(new TurnToFaceGoalCommand(driverStation, robotSubsystems), 10);
        addSequential(new ExtendCatapultCommand(driverStation, robotSubsystems));

    }
}
