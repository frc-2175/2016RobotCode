package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.ArcadeDriveWithInputsCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAutonomous extends CommandGroup {

    public LowBarAutonomous(RobotSubsystems robotSubsystems) {
        addSequential(
                new ArcadeDriveWithInputsCommand(robotSubsystems, 0.25, 0), 8);
    }
}
