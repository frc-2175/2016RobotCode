package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.ExtendCatapult;
import org.usfirst.frc2175.command.single.TurnToFaceGoal;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnToFaceGoalAndShoot extends CommandGroup {

    public TurnToFaceGoalAndShoot(DriverStation driverStation,
            RobotSubsystems robotSubsystems) {

        addSequential(new TurnToFaceGoal(driverStation, robotSubsystems), 10);
        addSequential(new ExtendCatapult(driverStation, robotSubsystems));

    }
}
