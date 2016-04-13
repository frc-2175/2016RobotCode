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
public class CrossStaticDefenseNoEncoderAutonomous extends CommandGroup {

    public CrossStaticDefenseNoEncoderAutonomous(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();

        // Drive forwards at .8 speed for 4 seconds
        addSequential(new ArcadeDriveWithInputsCommand(robotSubsystems, .8, 0),
                4);
    }
}
