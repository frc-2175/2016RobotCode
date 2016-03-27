package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.ArcadeDriveWithInputsCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WeakenSimpleDefenseNoEncoderAutonomous extends CommandGroup {

    public WeakenSimpleDefenseNoEncoderAutonomous(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // drive through obstacle
        addSequential(new ArcadeDriveWithInputsCommand(robotSubsystems, .8, 0),
                4);
    }
}
