package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WeakenShootLowBarAutonomous extends CommandGroup {

    public WeakenShootLowBarAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int extraShootLength = autonomousConfig.getExtraShootLength();
        double distanceWithShoot = travelLength + extraShootLength;

        // TODO Refine numbers if needed
        // TODO Refine angle
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceWithShoot));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 45, true));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }
}
