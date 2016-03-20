package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.ArcadeDriveWithInputsCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
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
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();

        // TODO Refine numbers if needed
        addSequential(new ArcadeDriveWithInputsCommand(robotSubsystems, .8, 0),
                4);
    }
}
