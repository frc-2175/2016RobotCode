package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.ArcadeDriveWithInputsCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReachDefenseAutonomous extends CommandGroup {

    public ReachDefenseAutonomous(RobotSubsystems robotSubsystems) {
        addSequential(
                new ArcadeDriveWithInputsCommand(robotSubsystems, 0.125, 0), 4);
    }
}
