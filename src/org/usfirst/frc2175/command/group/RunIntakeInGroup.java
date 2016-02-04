package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.RunDreamIntakeMainBeltAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunDreamIntakeSideBeltAtSpeedCommand;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RunIntakeInGroup extends CommandGroup {

    public RunIntakeInGroup(DriverStation driverStation,
            RobotSubsystems robotSubsystems) {

        addParallel(new RunDreamIntakeSideBeltAtSpeedCommand(driverStation,
                robotSubsystems, 1));
        addSequential(new RunDreamIntakeMainBeltAtSpeedCommand(driverStation,
                robotSubsystems, 1));

    }
}
